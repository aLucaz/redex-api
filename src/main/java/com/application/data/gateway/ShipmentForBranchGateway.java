package com.application.data.gateway;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.parser.ShipmentForBranchParser;
import com.application.data.repository.ShipmentForBranchRepository;
import com.application.shared.*;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipmentForBranchGateway {
    private final ShipmentForBranchRepository repository;

    public ShipmentForBranchGateway(ShipmentForBranchRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public void persist(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = ShipmentForBranchParser.mapToRowList(shipmentForBranchDtoList);
        repository.saveAll(shipmentForBranchList);
    }
    @SneakyThrows
    public List<ShipmentForBranch> getShipmentForBranchList(Integer idBranch) {
        return repository.findByBranchIdBranchAndShipmentIsActive(idBranch, Constant.ACTIVEB);
    }
}
