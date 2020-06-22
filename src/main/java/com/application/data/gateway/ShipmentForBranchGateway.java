package com.application.data.gateway;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.parser.ShipmentForBranchParser;
import com.application.data.parser.ShipmentParser;
import com.application.data.repository.ShipmentForBranchRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.Hashtable;
import java.util.List;

@Repository
public class ShipmentForBranchGateway {
    private final ShipmentForBranchRepository repository;

    public ShipmentForBranchGateway(ShipmentForBranchRepository repository) {
        this.repository = repository;
    }


    @SneakyThrows
    public void persist(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = ShipmentForBranchParser.mapToRow(shipmentForBranchDtoList);
        repository.saveAll(shipmentForBranchList);
    }
}
