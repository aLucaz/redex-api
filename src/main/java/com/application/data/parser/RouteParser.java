package com.application.data.parser;

import com.application.core.model.dto.RouteDto;
import com.application.core.model.dto.ShipmentRequestDto;
import com.application.rest.api.request.GenerateRouteRequest;

public class RouteParser {
    public static RouteDto mapToDto(GenerateRouteRequest request){
        return new RouteDto()
                .setStartPoint(request.getStartPoint())
                .setEndPoint(request.getEndPoint())
                .setRequestDateTime(request.getRequestDateTime())
                .setSameContinent(request.getSameContinent());
    }

    public static RouteDto mapToDto(ShipmentRequestDto shipmentRequestDto){
        return new RouteDto()
                .setStartPoint(shipmentRequestDto.getFrom())
                .setEndPoint(shipmentRequestDto.getTo())
                .setRequestDateTime(shipmentRequestDto.getRequestDateTime())
                .setSameContinent(shipmentRequestDto.getSameContinent());
    }
}
