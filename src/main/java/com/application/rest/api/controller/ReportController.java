package com.application.rest.api.controller;

import com.application.core.model.dto.report.CompilationReportDto;
import com.application.core.model.dto.report.OnTimeReportDto;
import com.application.core.usecase.report.GetCompilationReportUseCase;
import com.application.core.usecase.report.GetOntimeReportUseCase;
import com.application.data.parser.ReportParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.GetCompilationReportRequest;
import com.application.rest.api.request.GetOntimeReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-report")
@RequiredArgsConstructor
public class ReportController {
    private final GetCompilationReportUseCase getCompilationReport;
    private final GetOntimeReportUseCase getOntimeReport;

    @PostMapping("/get-compilation")
    public ResponseEntity<Object> getCompilationReport(@Valid @RequestBody GetCompilationReportRequest request) {
        CompilationReportDto reportDto = getCompilationReport.execute(ReportParser.mapToCompilationReportDto(request));
        return new ApiResponse<>().ok(reportDto);
    }

    @PostMapping("/ontime-compilation")
    public ResponseEntity<Object> getOntimeReport(@Valid @RequestBody GetOntimeReportRequest request){
        OnTimeReportDto reportDto = getOntimeReport.execute(ReportParser.mapToOnTimeReportDto(request));
        return new ApiResponse<>().ok(reportDto);
    }

}
