package com.application.data.gateway;

import com.application.core.model.business.Shipment;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.data.parser.ShipmentParser;
import com.application.data.repository.ShipmentRepository;
import com.application.shared.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;;import java.util.List;

@Repository
public class ShipmentGateway {
    private final ShipmentRepository repository;

    public ShipmentGateway(ShipmentRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public ShipmentDto persist(ShipmentDto shipmentDto) {
        Shipment shipment = ShipmentParser.mapToRow(shipmentDto);
        return ShipmentParser.mapToDto(repository.save(shipment));
    }

    public void saveAllSimulatedShipments(List<PathDto> pathDtoList, Integer shipmentStateId){
        List<Shipment> shipmentList = ShipmentParser.mapToRowList(pathDtoList, shipmentStateId);
        repository.saveAll(shipmentList);
    }


}
