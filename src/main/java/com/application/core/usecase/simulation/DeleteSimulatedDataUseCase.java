package com.application.core.usecase.simulation;


import com.application.core.model.business.*;
import com.application.core.model.dto.*;
import com.application.data.gateway.*;
import com.application.data.parser.*;
import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteSimulatedDataUseCase {
    private final IncidentGateway incidentGateway;
    private final ShipmentGateway shipmentGateway;

    public void execute() {
        List<Shipment> shipmentList = shipmentGateway.findAllSimulatedAndActive();
        List<Incident> incidentList = incidentGateway.findAllSimulatedAndActive();

        for(Shipment shipment : shipmentList){
            shipment.setIsActive(false);
        }
        for(Incident incident : incidentList){
            incident.setIsActive(false);
        }

        shipmentGateway.saveAll(shipmentList);
        incidentGateway.saveAllIncidents(IncidentParser.mapToDtoList(incidentList));

    }
}
