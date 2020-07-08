package com.application.data.gateway;

import com.application.core.model.business.Shipment;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.data.parser.ShipmentParser;
import com.application.data.repository.ShipmentRepository;
import com.application.shared.Constant;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

;

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

    public void persistWithPath(PathDto path, Integer shipmentStateId){
        Shipment shipment = ShipmentParser.mapToRow(path, shipmentStateId);
        repository.save(shipment);
    }

    public void saveAllSimulatedShipments(List<PathDto> pathDtoList, Integer shipmentStateId) {
        List<Shipment> shipmentList = ShipmentParser.mapToRowList(pathDtoList, shipmentStateId);
        repository.saveAll(shipmentList);
    }

    public List<ShipmentDto> getAllSimulatedShipments(){
        return ShipmentParser.mapToDtoList(repository.findAllByIsSimulatedAndIsActive(Constant.IS_A_SIMULATION, Constant.IS_ACTIVE));
    }

    public List<ShipmentDto> findAllActive(){
        return ShipmentParser.mapToDtoList(repository.findAllByIsActive(Constant.IS_ACTIVE));
    }

    public List<Shipment> findAllSimulatedAndActive(){
        return repository.findAllByIsSimulatedAndIsActive(Constant.IS_ACTIVE,Constant.IS_ACTIVE);
    }


    public void saveAll(List<Shipment> shipmentList) {
        repository.saveAll(shipmentList);
    }
}
