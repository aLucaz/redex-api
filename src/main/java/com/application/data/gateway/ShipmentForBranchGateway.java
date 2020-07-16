package com.application.data.gateway;

import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.parser.ShipmentForBranchParser;
import com.application.data.repository.ShipmentForBranchRepository;
import com.application.shared.Constant;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
    public List<ShipmentForBranch> persist(List<ShipmentForBranchDto> shipmentForBranchDtoList) {
        List<ShipmentForBranch> shipmentForBranchList = ShipmentForBranchParser.mapToRowList(shipmentForBranchDtoList);
        return (List<ShipmentForBranch>) repository.saveAll(shipmentForBranchList);
    }

    @SneakyThrows
    public List<ShipmentForBranch> getShipmentForBranchList(Integer idBranch) {
        return repository.findByBranchIdBranchAndShipmentIsActive(idBranch, Constant.ACTIVEB);
    }

    public List<ShipmentForBranchDto> findAllValidTripPlans(BranchDto branchDto, LocalDateTime stimatedDateTime, Boolean saveAsSimulated) {
        LocalDate stimatedDate = stimatedDateTime.toLocalDate();
        List<ShipmentForBranch> shipmentForBranchList = repository.findAllByBranchIdBranchAndCurrentArrivalDateTimeBetween(
                branchDto.getIdBranch(),
                LocalDateTime.of(stimatedDate.minusDays(1), LocalTime.MIN),
                LocalDateTime.of(stimatedDate.plusDays(1), LocalTime.MAX)
        );

        if (shipmentForBranchList.size() == 0)
            return null;

        Predicate<ShipmentForBranch> byShipmentActive = ShipmentForBranch ->
                ShipmentForBranch.getShipment().getIsActive() &&
                        ShipmentForBranch.getShipment().getIsSimulated().equals(saveAsSimulated);

        List<ShipmentForBranch> shipmentForBranchListFiltered = shipmentForBranchList
                .stream()
                .filter(byShipmentActive)
                .collect(Collectors.toList());
        return ShipmentForBranchParser.mapToDtoListFromRowList(shipmentForBranchListFiltered);
    }

    public List<ShipmentForBranchDto> findAllValidRoutesInRange(Integer idBranch, LocalDateTime start,
                                                                LocalDateTime end, Integer idShipmentState,
                                                                Boolean ofSimulated){
        List<ShipmentForBranch> shipmentForBranchList = repository.findAllByBranchIdBranchAndShipmentStateIdShipmentStateAndCurrentArrivalDateTimeBetween(
                idBranch,
                idShipmentState,
                start,
                end
        );

        if (shipmentForBranchList.size() == 0)
            return new ArrayList<>();

        Predicate<ShipmentForBranch> byShipmentActive = ShipmentForBranch ->
                ShipmentForBranch.getShipment().getIsActive() &&
                ShipmentForBranch.getShipment().getIsSimulated().equals(ofSimulated);

        List<ShipmentForBranch> shipmentForBranchListFiltered = shipmentForBranchList
                .stream()
                .filter(byShipmentActive)
                .collect(Collectors.toList());

        return ShipmentForBranchParser.mapToDtoListFromRowList(shipmentForBranchListFiltered);
    }

    public ShipmentForBranch findByShipmentAndBranch(Integer idShipment, Integer idBranch) {
        return repository.findByShipmentIdShipmentAndBranchIdBranch(idShipment, idBranch);
    }

    @SneakyThrows
    public void update(List<ShipmentForBranch> shipmentForBranchList) {
        repository.saveAll(shipmentForBranchList);
    }

    @SneakyThrows
    public void update(ShipmentForBranch shipmentForBranch) {
        repository.save(shipmentForBranch);
    }

    public List<ShipmentForBranch> findByFlightList(List<String> flightFriendlyIds) {
        return repository.findByFlightFriendlyIdIn(flightFriendlyIds);
    }
}
