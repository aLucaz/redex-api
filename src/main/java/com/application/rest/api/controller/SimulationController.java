package com.application.rest.api.controller;

import com.application.core.model.dto.ChartInformationDto;
import com.application.core.usecase.simulation.GetChartInformationUseCase;
import com.application.core.usecase.simulation.RunSimulationUseCase;
import com.application.data.parser.ChartInformationParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.GetChartInformationRequest;
import com.application.shared.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-simulation")
@RequiredArgsConstructor
public class SimulationController {
    private final RunSimulationUseCase runSimulationUseCase;
    private final GetChartInformationUseCase getChartInformationUseCase;

    @PostMapping("/run-simulation")
    public ResponseEntity<Object> runSimulation(@RequestParam(Constant.REQUEST_FILE_NAME) MultipartFile file) {
        runSimulationUseCase.execute(file);
        return new ApiResponse<>().ok();
    }

    @PostMapping("/get-chart-information")
    public ResponseEntity<Object> getInformation(@Valid @RequestBody GetChartInformationRequest request) {
        ChartInformationDto chartInformationDto = getChartInformationUseCase.execute(ChartInformationParser.mapToDto(request));
        return new ApiResponse<>().ok(chartInformationDto);
    }
}
