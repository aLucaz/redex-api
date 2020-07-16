package com.application.core.usecase.util.detector.incident;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.counter.shipment.ShipmentCounter;
import com.application.core.usecase.util.counter.shipment.ShipmentCounterImpl;
import com.application.data.gateway.EtFlightGateway;
import com.application.shared.Constant;
import com.application.shared.Galactus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class IncidentDetectorImpl implements IncidentDetector {

    private final ShipmentCounter shipmentCounter;
    private final EtFlightGateway etFlightGateway;

    public IncidentDetectorImpl(ShipmentCounterImpl shipmentCounter, EtFlightGateway etFlightGateway) {
        this.shipmentCounter = shipmentCounter;
        this.etFlightGateway = etFlightGateway;
    }

    @Override
    public IncidentDto detectIncident(List<ShipmentForBranchDto> tripPlans, LocalDateTime arrivalDateTime,
                                      LocalDateTime departureDatetime, BranchDto requestBranch, Boolean isASimulation) {
        List<ShipmentForBranchDto> routesInRange = shipmentCounter.giveMeTheShipmentsInRange(tripPlans, arrivalDateTime, departureDatetime, requestBranch);
        // we add this range in the evaluation
        ShipmentForBranchDto thisRange = new ShipmentForBranchDto()
                .setCurrentArrivalDateTime(arrivalDateTime)
                .setCurrentDepartureDateTime(departureDatetime);

        if (routesInRange != null) {
            /* I have to evaluate again if :
             * 1. when i arrive there is an incident
             * 2. when someone arrives there is an incident
             * */
            routesInRange.add(thisRange);
            Collections.sort(routesInRange);
            for (ShipmentForBranchDto shipmentForBranchDto : routesInRange) {
                Integer numberOfShipments = shipmentCounter.countShipmentsInRange(routesInRange, shipmentForBranchDto.getCurrentArrivalDateTime());
                if (requestBranch.getCapacity() < numberOfShipments)
                    return new IncidentDto()
                            .setBranchFriendlyId(requestBranch.getFriendlyId())
                            .setIncidentDateTime(shipmentForBranchDto.getCurrentArrivalDateTime())
                            .setIsActive(Constant.IS_ACTIVE)
                            .setIsSimulated(isASimulation)
                            .setIncidentType(Constant.BRANCH_CAPACITY_INCIDENT);
            }
        }
        return null;
    }

    @Override
    public IncidentDto detectIncident(String flightFriendlyId, Boolean isASimulation) {
        EtFlightDto etFlightDto = etFlightGateway.findByFriendlyId(flightFriendlyId);
        if (etFlightDto.getCapacity() <= etFlightDto.getQuantity())
            return new IncidentDto()
                    .setFlightFriendlyId(flightFriendlyId)
                    .setIncidentDateTime(Galactus.unifyDateTime(etFlightDto.getEtFlightDate(), etFlightDto.getDepartureTime()))
                    .setIsActive(Constant.IS_ACTIVE)
                    .setIsSimulated(isASimulation)
                    .setIncidentType(Constant.FLIGHT_CAPACITY_INCIDENT);
        return null;
    }
}
