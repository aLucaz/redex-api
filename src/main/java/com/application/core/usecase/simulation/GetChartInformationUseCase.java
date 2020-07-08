package com.application.core.usecase.simulation;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.BranchRecordDto;
import com.application.core.model.dto.ChartInformationDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.usecase.util.counter.incident.IncidentCounter;
import com.application.core.usecase.util.counter.incident.IncidentCounterImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.IncidentGateway;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetChartInformationUseCase {
    private final BranchGateway branchGateway;
    private final IncidentGateway incidentGateway;
    private final IncidentCounter incidentCounter;

    public GetChartInformationUseCase(BranchGateway branchGateway, IncidentGateway incidentGateway,
                                      IncidentCounterImpl incidentCounter) {
        this.branchGateway = branchGateway;
        this.incidentGateway = incidentGateway;
        this.incidentCounter = incidentCounter;
    }

    public ChartInformationDto execute(ChartInformationDto chartInformationDto) {
        List<BranchDto> branchDtoList = branchGateway.fildAllActive();
        List<BranchRecordDto> branchRecords = new ArrayList<>();
        LocalDateTime requestDateTime = chartInformationDto.getRequestDate().atStartOfDay();
        branchDtoList.forEach(branchDto -> {
            // we get all the routes off that branch and that shipment if is Active
            List<IncidentDto> incidents = incidentGateway.findIncidentsOfBranchAndDate(branchDto.getFriendlyId(),
                    chartInformationDto.getRequestDate(), Constant.BRANCH_CAPACITY_INCIDENT);
            // now we obtain the list of quantities in each hour of the day
            branchRecords.add(getBranchRecord(branchDto, requestDateTime, incidents));
        });
        chartInformationDto.setBranchRecords(branchRecords);
        return chartInformationDto;
    }

    // TODO change this to the new form of information
    public BranchRecordDto getBranchRecord(BranchDto branch, LocalDateTime requestDateTime, List<IncidentDto> incidents) {
        List<Integer> quantityByHour = new ArrayList<>();
        for (int hour = 0; hour <= 23; hour++) {
            quantityByHour.add(incidentCounter.countIncidentsOfDateHour(requestDateTime.plusHours(hour), incidents, Constant.BRANCH_CAPACITY_INCIDENT));
        }
        return new BranchRecordDto()
                .setIdBranch(branch.getIdBranch())
                .setBranchName(branch.getName())
                .setCapacity(branch.getCapacity())
                .setQuantityPerHour(quantityByHour);
    }
}
