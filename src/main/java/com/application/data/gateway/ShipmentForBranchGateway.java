package com.application.data.gateway;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.parser.BranchParser;
import com.application.data.parser.ShipmentForBranchParser;
import com.application.data.repository.ShipmentForBranchRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public List<ShipmentForBranchDto> getRoutesInRange(LocalDate requestDate, Integer branchId) {
        return null;
    }

    public List<ShipmentForBranchDto> finalAllByBranchIdAndShipmentActive(BranchDto branchDto) {
        List<ShipmentForBranch> shipmentForBranchList = repository.findAllByBranch(BranchParser.mapToRow(branchDto));
        Predicate<ShipmentForBranch> byShipmentActive = ShipmentForBranch ->
                ShipmentForBranch.getShipment().getIsActive();

        List<ShipmentForBranch> shipmentForBranchListFiltered = shipmentForBranchList
                .stream()
                .filter(byShipmentActive)
                .collect(Collectors.toList());
        return ShipmentForBranchParser.mapToDtoListFromRowList(shipmentForBranchListFiltered);
    }
}
