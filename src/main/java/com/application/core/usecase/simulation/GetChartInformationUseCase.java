package com.application.core.usecase.simulation;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.BranchRecordDto;
import com.application.core.model.dto.ChartInformationDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.counter.shipment.ShipmentCounter;
import com.application.core.usecase.util.counter.shipment.ShipmentCounterImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetChartInformationUseCase {
    private final BranchGateway branchGateway;
    private final ShipmentForBranchGateway shipmentForBranchGateway;
    private final ShipmentCounter shipmentCounter;

    public GetChartInformationUseCase(BranchGateway branchGateway, ShipmentForBranchGateway shipmentForBranchGateway,
                                      ShipmentCounterImpl shipmentCounter) {
        this.branchGateway = branchGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.shipmentCounter = shipmentCounter;
    }

    public ChartInformationDto execute(ChartInformationDto chartInformationDto) {
        List<BranchDto> branchDtoList = branchGateway.fildAllActive();
        List<BranchRecordDto> branchRecords = new ArrayList<>();
        LocalDateTime requestDateTime = chartInformationDto.getRequestDate().atStartOfDay();
        branchDtoList.forEach(branchDto -> {
            // we get all the routes off that branch and that shipment if is Active
            List<ShipmentForBranchDto> shipmentForBranchDtoList = shipmentForBranchGateway
                    .finalAllByBranchIdAndShipmentActive(branchDto);
            // now we obtain the list of quantities in each hour of the day
            branchRecords.add(getBranchRecord(branchDto, requestDateTime, shipmentForBranchDtoList));
        });
        chartInformationDto.setBranchRecords(branchRecords);
        return chartInformationDto;
    }

    public BranchRecordDto getBranchRecord(BranchDto branch, LocalDateTime requestDateTime, List<ShipmentForBranchDto> routeList) {
        List<Integer> quantityByHour = new ArrayList<>();
        for (int hour = 0; hour <= 23; hour++) {
            quantityByHour.add(shipmentCounter.countShipmentsInRange(routeList, requestDateTime.plusHours(hour), branch));
        }
        return new BranchRecordDto()
                .setIdBranch(branch.getIdBranch())
                .setBranchName(branch.getName())
                .setCapacity(branch.getCapacity())
                .setQuantityPerHour(quantityByHour);
    }
}
