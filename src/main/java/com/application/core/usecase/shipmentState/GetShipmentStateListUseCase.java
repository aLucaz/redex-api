package com.application.core.usecase.shipmentState;

import com.application.core.model.dto.ShipmentStateDto;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.data.parser.ShipmentStateParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetShipmentStateListUseCase {
    private final ShipmentStateGateway shipmentStateGateway;

    public List<ShipmentStateDto> execute(){
        return ShipmentStateParser.mapToDtoList(shipmentStateGateway.findAll());
    }
}
