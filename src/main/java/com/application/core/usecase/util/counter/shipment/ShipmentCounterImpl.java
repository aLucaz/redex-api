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
    public Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime requestDateTime) {
        // routes can be empty
        if (routes == null || routes.size() == 0)
            return Constant.NUMBER_OF_SHIPMENTS_EMPTY;
        // how many packages are in that arrivalDateTime range!!
        Predicate<ShipmentForBranchDto> byDateTimeInterval = ShipmentForBranchDto ->
                (ShipmentForBranchDto.getCurrentArrivalDateTime().isBefore(requestDateTime) ||
                        ShipmentForBranchDto.getCurrentArrivalDateTime().isEqual(requestDateTime)) &&
                        ShipmentForBranchDto.getCurrentDepartureDateTime().isAfter(requestDateTime);
        List<ShipmentForBranchDto> routesFiltered = routes
                .stream()
                .filter(byDateTimeInterval)
                .collect(Collectors.toList());
        return routesFiltered.size();
    }

    @Override
    public Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch) {
        // routes can be empty
        if (routes == null || routes.size() == 0)
            return Constant.NUMBER_OF_SHIPMENTS_EMPTY;
        routes.removeIf(route -> isOutOfDateRange(route, arrivalDateTime, departureDateTime, requestBranch));
        return routes.size();
    }

    @Override
    public List<ShipmentForBranchDto> giveMeTheShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch) {
        // routes can be empty
        if (routes == null || routes.size() == 0)
            return null;
        routes.removeIf(route -> isOutOfDateRange(route, arrivalDateTime, departureDateTime, requestBranch));
        return routes;
    }


    public Boolean isOutOfDateRange(ShipmentForBranchDto shipmentForBranchDto, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch) {
        return (shipmentForBranchDto.getIdBranch().equals(requestBranch.getIdBranch())) &&
                ((shipmentForBranchDto.getCurrentArrivalDateTime().isBefore(arrivalDateTime) &&
                        (shipmentForBranchDto.getCurrentDepartureDateTime().isBefore(arrivalDateTime) ||
                                shipmentForBranchDto.getCurrentDepartureDateTime().isEqual(arrivalDateTime))) ||
                        (shipmentForBranchDto.getCurrentArrivalDateTime().isAfter(departureDateTime) &&
                                (shipmentForBranchDto.getCurrentDepartureDateTime().isAfter(departureDateTime))));
    }
}
