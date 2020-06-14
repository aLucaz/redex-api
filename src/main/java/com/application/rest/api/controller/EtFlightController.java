package com.application.rest.api.controller;

import com.application.core.usecase.etflight.RegisterMassiveEtFlightUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-et-flight")
@RequiredArgsConstructor
public class EtFlightController {
    private final RegisterMassiveEtFlightUseCase registerMassiveEtFlight;

    @PostMapping("/register-massive")
    public ResponseEntity<Object> registerUser() {
        // call use case
        registerMassiveEtFlight.execute();
        // return ok response
        return new ApiResponse<>().ok();
    }
}
