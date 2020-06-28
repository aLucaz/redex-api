package com.application.rest.api.controller;

import com.application.core.model.dto.PathDto;
import com.application.core.usecase.shipment.GenerateRouteUseCase;
import com.application.core.usecase.shipment.RegisterShipmentUseCase;
import com.application.data.parser.*;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.GenerateRouteRequest;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;
import com.application.shared.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-shipment")
@RequiredArgsConstructor
public class ShipmentController {
    private final GenerateRouteUseCase generateRouteUseCase;
    private final RegisterShipmentUseCase registerShipmentUseCase;

    @PostMapping("/generate-route")
    public ResponseEntity<Object> generateRoute(@Valid @RequestBody GenerateRouteRequest request) {
        PathDto route = generateRouteUseCase.execute(RouteParser.mapToDto(request), Constant.IS_NOT_A_SIMULATION);
        return new ApiResponse<>().ok(route);
    }

    @PostMapping("/register-shipment")
    public ResponseEntity<Object> registerShipment(@Valid @RequestBody RegisterShipmentRequest request) {
        registerShipmentUseCase.execute(
                ShipmentParser.mapToDto(request),
                ShipmentForBranchParser.mapToDto(request),
                PackageParser.mapToDto(request),
                PersonParser.mapToDtoCustomer(request),
                PersonParser.mapToDtoReceiver(request)
        );
        return new ApiResponse<>().ok();
    }
}
