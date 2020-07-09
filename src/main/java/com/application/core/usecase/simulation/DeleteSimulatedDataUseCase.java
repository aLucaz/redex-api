package com.application.core.usecase.simulation;


import com.application.core.model.business.EtFlight;
import com.application.core.model.business.Incident;
import com.application.core.model.business.Shipment;
import com.application.data.gateway.EtFlightGateway;
import com.application.data.gateway.IncidentGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.parser.IncidentParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteSimulatedDataUseCase {
    private final IncidentGateway incidentGateway;
    private final ShipmentGateway shipmentGateway;
    private final EtFlightGateway etFlightGateway;

    public void execute() {
        List<Shipment> shipmentList = shipmentGateway.findAllSimulatedAndActive();
        List<Incident> incidentList = incidentGateway.findAllSimulatedAndActive();
        // we delete all the simulated flights quantity
        List<EtFlight> etFlightList = getFlightsToDelete(shipmentList);

        if (!shipmentList.isEmpty()){
            for (Shipment shipment : shipmentList) {
                shipment.setIsActive(false);
            }
            shipmentGateway.saveAll(shipmentList);
        }

        if (!incidentList.isEmpty()){
            for (Incident incident : incidentList) {
                incident.setIsActive(false);
            }
            incidentGateway.saveAllIncidents(IncidentParser.mapToDtoList(incidentList));
        }

        if (!etFlightList.isEmpty())
            etFlightGateway.saveAll(etFlightList);
    }

    public List<EtFlight> getFlightsToDelete(List<Shipment> shipmentList){
        HashMap<String, Integer> simulatedFlights = new HashMap<>();

        shipmentList.forEach(shipment -> {
            shipment.getShipmentForBranches().forEach(shipmentForBranch -> {
                String flightFriendlyId = shipmentForBranch.getFlightFriendlyId();
                if (simulatedFlights.containsKey(flightFriendlyId)){
                    Integer quantity = simulatedFlights.get(flightFriendlyId);
                    simulatedFlights.put(flightFriendlyId, quantity + 1);
                }else{
                    simulatedFlights.put(flightFriendlyId, 1);
                }
            });
        });

        List<EtFlight> etFlightList = etFlightGateway.findAllOfList(new ArrayList<>(simulatedFlights.keySet()));

        etFlightList.forEach(etFlight -> {
            Integer actualQuantity = etFlight.getQuantity();
            Integer simulatedQuantity = simulatedFlights.get(etFlight.getFriendlyId());
            if (actualQuantity < simulatedQuantity)
                etFlight.setQuantity(0);
            else
                etFlight.setQuantity(actualQuantity - simulatedQuantity);
        });

        return etFlightList;
    }
}
