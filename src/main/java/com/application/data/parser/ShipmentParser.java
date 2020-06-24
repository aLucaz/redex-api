package com.application.data.parser;

import com.application.core.model.business.Shipment;
import com.application.core.model.dto.ShipmentDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.time.LocalDateTime;

public class ShipmentParser {
    public static ShipmentDto mapToDto(RegisterShipmentRequest request) {
        return new ShipmentDto()
                .setPrice(request.getPrice())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy());
    }

    public static Shipment mapToRow(ShipmentDto shipmentDto) {
        return new Shipment()
                .setPrice(shipmentDto.getPrice())
                .setLastModifiedBy(shipmentDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(shipmentDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now());
    }

    public static ShipmentDto mapToDto(Shipment shipment) {
        return new ShipmentDto()
                .setPrice(shipment.getPrice())
                .setIdShipment(shipment.getIdShipment());
    }
}
