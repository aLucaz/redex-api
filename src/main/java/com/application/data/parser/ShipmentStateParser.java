package com.application.data.parser;

import com.application.core.model.business.ShipmentState;
import com.application.core.model.dto.ShipmentStateDto;

import java.util.List;
import java.util.ArrayList;

public class ShipmentStateParser {
    public static ShipmentStateDto mapToDto(ShipmentState shipmentState) {
        return new ShipmentStateDto()
                .setIdShipmentState(shipmentState.getIdShipmentState())
                .setFriendlyId(shipmentState.getFriendlyId())
                .setName(shipmentState.getName())
                .setDescription(shipmentState.getDescription());
    }

    public static List<ShipmentStateDto> mapToDtoList(List<ShipmentState> shipmentStateList) {
        List<ShipmentStateDto> shipmentStateDtoList = new ArrayList<>();
        for(ShipmentState ss : shipmentStateList){
            shipmentStateDtoList.add(mapToDto(ss));
        }
        return shipmentStateDtoList;
    }
}
