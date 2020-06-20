package com.application.rest.api.controller;

import com.application.core.model.dto.PathDto;
import com.application.core.usecase.shipment.GenerateRouteUseCase;
import com.application.data.parser.RouteParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.GenerateRouteRequest;
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

    @PostMapping("/generate-route")
    public ResponseEntity<Object> generateRoute(@Valid @RequestBody GenerateRouteRequest request) {
        PathDto route = generateRouteUseCase.execute(RouteParser.mapToDto(request));
        return new ApiResponse<>().ok(route);
    }
}
