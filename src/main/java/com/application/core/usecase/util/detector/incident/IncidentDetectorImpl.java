package com.application.core.usecase.util.detector.incident;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.counter.shipment.ShipmentCounter;
import com.application.core.usecase.util.counter.shipment.ShipmentCounterImpl;
import com.application.shared.Constant;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class IncidentDetectorImpl implements IncidentDetector {

    private final ShipmentCounter shipmentCounter;

    public IncidentDetectorImpl(ShipmentCounterImpl shipmentCounter) {
        this.shipmentCounter = shipmentCounter;
    }

    @Override
    public IncidentDto detectIncident(List<ShipmentForBranchDto> tripPlans, LocalDateTime arrivalDateTime, BranchDto requestBranch) {
        Integer numberOfShipmentsInRange = shipmentCounter.countShipmentsInRange(tripPlans, arrivalDateTime, requestBranch);
        if (requestBranch.getCapacity() < numberOfShipmentsInRange)
            return new IncidentDto()
                    .setBranchFriendlyId(requestBranch.getFriendlyId())
                    .setIncidentDateTime(arrivalDateTime)
                    .setIsActive(Constant.IS_ACTIVE)
                    .setIsSimulated(Constant.IS_A_SIMULATION);
        return null;
    }
}
