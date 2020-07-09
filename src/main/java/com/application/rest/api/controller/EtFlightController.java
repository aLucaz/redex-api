package com.application.rest.api.controller;

import com.application.core.model.business.*;
import com.application.core.model.dto.*;
import com.application.core.usecase.etflight.*;
import com.application.data.parser.*;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/api-et-flight")
@RequiredArgsConstructor
public class EtFlightController {
    private final RegisterMassiveEtFlightUseCase registerMassiveEtFlight;
    private final GetEtFlightListUseCase getEtFlightListUseCase;
    private final UpdateFlightCapacityUseCase updateFlightCapacityUseCase;
    private final GetFilteredEtFlightListUseCase getFilteredEtFlightListUseCase;

    @PostMapping("/register-massive")
    public ResponseEntity<Object> registerUser() {
        // call use case
        registerMassiveEtFlight.execute();
        // return ok response
        return new ApiResponse<>().ok();
    }
    @GetMapping("/list")
    public ResponseEntity<Object> getList() {
        // call use case
        List<EtFlightDto> etFlightDtoList = getEtFlightListUseCase.execute();
        // return ok response
        return new ApiResponse<>().ok(etFlightDtoList);
    }
    @PostMapping("/update-capacity")
    public ResponseEntity<Object> updateCapacity(
            @Valid @RequestBody UpdateFlightCapacityRequest request) {
        // call use case
        updateFlightCapacityUseCase.execute(request.getFriendlyId(), request.getCapacity());
        // return ok response
        return new ApiResponse<>().ok();
    }

    @PostMapping("/filtered-list")
    public ResponseEntity<Object> getFilteredList(@Valid @RequestBody GetFilteredEtFlightListRequest request) {

        List<EtFlightDto> etFlightDtoList = getFilteredEtFlightListUseCase.execute(
                EtFlightParser.mapToDtoFilters(request));
        return new ApiResponse<>().ok(etFlightDtoList);
    }
}
