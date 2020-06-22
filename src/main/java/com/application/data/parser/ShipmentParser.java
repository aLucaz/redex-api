package com.application.data.parser;

import com.application.core.model.business.Shipment;
import com.application.core.model.dto.ShipmentDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.time.LocalDateTime;

public class ShipmentParser {
    public static ShipmentDto mapToDto(RegisterShipmentRequest request) {
        return new ShipmentDto()
                .setPrice(request.getPrice())
                .setOrigin(request.getOrigin())
                .setDestination(request.getDestination())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy());
                //.setReferenceCode(request.getReferenceCode());
    }
    public static Shipment mapToRow(ShipmentDto shipmentDto) {
        return new Shipment()
                .setPrice(shipmentDto.getPrice())
                .setLastModifiedBy(shipmentDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(shipmentDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now());
                //.setOrigin(shipmentDto.getOrigin())
                //.setDestination(shipmentDto.getDestination());
    }

    public static ShipmentDto mapToDto(Shipment shipment) {
        return new ShipmentDto()
                .setPrice(shipment.getPrice())
                .setIdShipment(shipment.getIdShipment());
                //.setOrigin(shipment.getOrigin())
                //.setDestination(shipment.getDestination());
    }
}
