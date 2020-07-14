package com.application.rest.api.controller;

import com.application.core.model.dto.SearchShipmentDto;
import com.application.core.usecase.client.SearchShipmentUseCase;
import com.application.data.parser.ClientParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.SearchShipmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-client")
@RequiredArgsConstructor
public class ClientController {

    private final SearchShipmentUseCase searchShipment;

    @GetMapping("/search-shipment")
    public ResponseEntity<Object> searchShipment(@Valid @RequestBody SearchShipmentRequest request) {
        SearchShipmentDto response = searchShipment.execute(ClientParser.mapToDto(request));
        return new ApiResponse<>().ok(response);
    }
}
