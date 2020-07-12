package com.application.core.usecase.report;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.report.CompilationReportDto;
import com.application.core.model.dto.report.DetailCompilationDto;
import com.application.core.usecase.util.classificator.DailyClassificator;
import com.application.core.usecase.util.classificator.DailyClassificatorImpl;
import com.application.data.gateway.BranchGateway;
import com.application.data.gateway.IncidentGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GetCompilationReportUseCase {
    private final BranchGateway branchGateway;
    private final IncidentGateway incidentGateway;
    private final ShipmentForBranchGateway shipmentForBranchGateway;
    private final ShipmentStateGateway shipmentStateGateway;
    private final DailyClassificator dailyClassificator;

    public GetCompilationReportUseCase(BranchGateway branchGateway, IncidentGateway incidentGateway,
                                       ShipmentForBranchGateway shipmentForBranchGateway,
                                       ShipmentStateGateway shipmentStateGateway,
                                       DailyClassificatorImpl dailyClassificator) {
        this.branchGateway = branchGateway;
        this.incidentGateway = incidentGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.dailyClassificator = dailyClassificator;
    }

    public CompilationReportDto execute(CompilationReportDto reportDto) {
        List<BranchDto> branches = branchGateway.fildAllActive();
        List<DetailCompilationDto> detailCompilationDtoList = new ArrayList<>();

        for (BranchDto branch : branches) {
            DetailCompilationDto detailCompilation = new DetailCompilationDto()
                    .setIdBranch(branch.getIdBranch())
                    .setNameBranch(branch.getName())
                    .setCapacity(branch.getCapacity());
            // now we get the incidents in that range of dates in this branch
            // incidents have to be active (not deleted), not simulated and branch incident type
            List<IncidentDto> incidents = incidentGateway.findAllValidIncidentsInRange(
                    Constant.BRANCH_CAPACITY_INCIDENT,
                    branch.getFriendlyId(),
                    LocalDateTime.of(reportDto.getStartDate(), LocalTime.MIN),
                    LocalDateTime.of(reportDto.getEndDate(), LocalTime.MAX));
            // now we get the routes where shipments were in that range of dates in this branch
            // its have to be active shipment(not deleted), not simulated, and finished State
            List<ShipmentForBranchDto> routes = shipmentForBranchGateway.findAllValidRoutesInRange(
                    branch.getIdBranch(),
                    LocalDateTime.of(reportDto.getStartDate(), LocalTime.MIN),
                    LocalDateTime.of(reportDto.getEndDate(), LocalTime.MAX),
                    shipmentStateGateway.findIdOf(Constant.FINISHED));

            if (incidents != null)
                detailCompilation.setQuantityOfIncidents(incidents.size());
            if (routes != null)
                detailCompilation.setQuantityOfShipments(routes.size());
            // now we classificate all of this results
            int numberOfDays = Math.toIntExact(reportDto.getStartDate().until(reportDto.getEndDate(), ChronoUnit.DAYS));
            List<LocalDate> rangeDates = Stream.iterate(reportDto.getStartDate(), date -> date.plusDays(1)).limit(numberOfDays + 1).collect(Collectors.toList());
            // we save the day detail information
            detailCompilation.setDaysDetail(dailyClassificator.classificateByDateAndHour(rangeDates, incidents, routes));
            // and we save the branch detail
            detailCompilationDtoList.add(detailCompilation);
        }

        return new CompilationReportDto()
                .setBranchesDetail(detailCompilationDtoList);
    }
}
