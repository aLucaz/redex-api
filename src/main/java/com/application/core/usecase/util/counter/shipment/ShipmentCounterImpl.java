package com.application.core.usecase.util.counter.shipment;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.shared.Constant;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class ShipmentCounterImpl implements ShipmentCounter {

    @Override
    public Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime requestDateTime, BranchDto requestBranch) {
        // routes can be empty
        if (routes.size() == 0)
            return Constant.NUMBER_OF_SHIPMENTS_EMPTY;
        // how many packages are in that arrivalDateTime range!!
        Predicate<ShipmentForBranchDto> byDateTimeInterval = ShipmentForBranchDto ->
                (ShipmentForBranchDto.getCurrentArrivalDateTime().isBefore(requestDateTime) ||
                        ShipmentForBranchDto.getCurrentArrivalDateTime().isEqual(requestDateTime)) &&
                        ShipmentForBranchDto.getCurrentDepartureDateTime().isAfter(requestDateTime) &&
                        ShipmentForBranchDto.getIdBranch().equals(requestBranch.getIdBranch());
        List<ShipmentForBranchDto> routesFiltered = routes
                .stream()
                .filter(byDateTimeInterval)
                .collect(Collectors.toList());
        return routesFiltered.size();
    }

    @Override
    public Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch) {
        // routes can be empty
        if (routes.size() == 0)
            return Constant.NUMBER_OF_SHIPMENTS_EMPTY;
        // how many packages are in that arrivalDateTime range!!
        Predicate<ShipmentForBranchDto> byDateTimeInterval = ShipmentForBranchDto ->
                (!ShipmentForBranchDto.getIdBranch().equals(requestBranch.getIdBranch())) &&
                        (ShipmentForBranchDto.getCurrentArrivalDateTime().isBefore(arrivalDateTime) &&
                                (ShipmentForBranchDto.getCurrentDepartureDateTime().isBefore(arrivalDateTime) ||
                                        ShipmentForBranchDto.getCurrentDepartureDateTime().isEqual(arrivalDateTime))) ||
                        (ShipmentForBranchDto.getCurrentArrivalDateTime().isAfter(departureDateTime) &&
                                (ShipmentForBranchDto.getCurrentDepartureDateTime().isAfter(departureDateTime)));

        List<ShipmentForBranchDto> routesFiltered = routes
                .stream()
                .filter(byDateTimeInterval)
                .collect(Collectors.toList());
        // we remove all that meets the predicate
        routes.removeAll(routesFiltered);
        return routes.size();
    }
}
