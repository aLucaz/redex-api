package com.application.rest.api.controller;

import com.application.core.usecase.simulation.RunSimulationUseCase;
import com.application.rest.ApiResponse;
import com.application.shared.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api-simulation")
@RequiredArgsConstructor
public class SimulationController {
    private final RunSimulationUseCase runSimulationUseCase;

    @PostMapping("/run-simulation")
    public ResponseEntity<Object> runSimulation(@RequestParam(Constant.REQUEST_FILE_NAME) MultipartFile file){
        runSimulationUseCase.execute(file);
        return new ApiResponse<>().ok();
    }
}
