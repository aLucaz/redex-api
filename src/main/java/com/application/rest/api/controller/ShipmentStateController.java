package com.application.rest.api.controller;

import com.application.core.model.dto.ShipmentStateDto;
import com.application.core.usecase.shipmentState.GetShipmentStateListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/api-shipmentState")
@RequiredArgsConstructor
public class ShipmentStateController {
    private final GetShipmentStateListUseCase getShipmentStateListUseCase;

    @GetMapping("/list")
    public ResponseEntity<Object> getShipmentStateList() {
        List<ShipmentStateDto> shipmentStateDtoList = getShipmentStateListUseCase.execute();
        return new ApiResponse<>().ok(shipmentStateDtoList);
    }
}
