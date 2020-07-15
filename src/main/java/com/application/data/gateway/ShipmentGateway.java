package com.application.data.gateway;

import com.application.core.model.business.Shipment;
import com.application.core.model.dto.PathDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGenerator;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGeneratorImpl;
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
    private final ReferenceCodeGenerator referenceCodeGenerator;

    public ShipmentGateway(ShipmentRepository repository, ReferenceCodeGeneratorImpl referenceCodeGenerator) {
        this.repository = repository;
        this.referenceCodeGenerator = referenceCodeGenerator;
    }

    @SneakyThrows
    public ShipmentDto persist(ShipmentDto shipmentDto, ShipmentForBranchDto shipmentForBranchDto) {
        Shipment shipment = ShipmentParser.mapToRow(shipmentDto
                .setTotalWaitingTime(shipmentForBranchDto.getTotalWaitingTime())
                .setTotalTransportTime(shipmentForBranchDto.getTotalTransportTime())
                .setTotalDelayTime(shipmentForBranchDto.getTotalDelayTime()));
        return ShipmentParser.mapToDto(repository.save(shipment));
    }

    public void persistWithPath(PathDto path, Boolean isSimulated, Integer shipmentStateId, Boolean sameContinent) {
        Shipment shipment = ShipmentParser.mapToRow(path, shipmentStateId, isSimulated, sameContinent);
        shipment.setReferenceCode(referenceCodeGenerator.generateReferenceCode());
        repository.save(shipment);
    }

    public List<ShipmentDto> getAllSimulatedShipments() {
        return ShipmentParser.mapToDtoList(repository.findAllByIsSimulatedAndIsActive(Constant.IS_A_SIMULATION, Constant.IS_ACTIVE));
    }

    public List<ShipmentDto> findAllActive() {
        return ShipmentParser.mapToDtoList(repository.findAllByIsActive(Constant.IS_ACTIVE));
    }

    public List<Shipment> findAllSimulatedAndActive() {
        return repository.findAllByIsSimulatedAndIsActive(Constant.IS_ACTIVE, Constant.IS_ACTIVE);
    }


    public void saveAll(List<Shipment> shipmentList) {
        repository.saveAll(shipmentList);
    }

    public ShipmentDto findByReferenceCode(String referenceCode) {
        return ShipmentParser.mapToDto(repository.findByReferenceCode(referenceCode));
    }
}
