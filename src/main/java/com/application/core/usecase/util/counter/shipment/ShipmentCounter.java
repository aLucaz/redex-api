package com.application.core.usecase.util.counter.shipment;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.ShipmentForBranchDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipmentCounter {
    Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime requestDateTime);
    Integer countShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch);
    List<ShipmentForBranchDto> giveMeTheShipmentsInRange(List<ShipmentForBranchDto> routes, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime, BranchDto requestBranch);
}
