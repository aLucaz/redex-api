package com.application.core.usecase.util.detector.incident;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.shared.Constant;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class IncidentDetectorImpl implements IncidentDetector {

    @Override
    public IncidentDto detectIncident(List<ShipmentForBranchDto> tripPlans, LocalDateTime arrivalDateTime, BranchDto requestBranch) {
        // how many packages are in that arrivalDateTime range!!
        Predicate<ShipmentForBranchDto> byDateTimeInterval = ShipmentForBranchDto ->
                (ShipmentForBranchDto.getCurrentArrivalDateTime().isBefore(arrivalDateTime) ||
                        ShipmentForBranchDto.getCurrentArrivalDateTime().isEqual(arrivalDateTime)) &&
                        ShipmentForBranchDto.getCurrentDepartureDateTime().isAfter(arrivalDateTime) &&
                        ShipmentForBranchDto.getIdBranch().equals(requestBranch.getIdBranch());
        List<ShipmentForBranchDto> routesFiltered = tripPlans
                .stream()
                .filter(byDateTimeInterval)
                .collect(Collectors.toList());
        // now we compare the sizes
        if (requestBranch.getCapacity() < routesFiltered.size())
            return new IncidentDto()
                    .setBranchFriendlyId(requestBranch.getFriendlyId())
                    .setIncidentDateTime(arrivalDateTime)
                    .setIsActive(Constant.IS_ACTIVE)
                    .setIsSimulated(Constant.IS_A_SIMULATION);
        return null;
    }
}
