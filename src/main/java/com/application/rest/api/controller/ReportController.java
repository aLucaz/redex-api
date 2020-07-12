package com.application.rest.api.controller;

import com.application.core.model.dto.report.CompilationReportDto;
import com.application.core.usecase.report.GetCompilationReportUseCase;
import com.application.data.parser.ReportParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.GetCompilationReportRequest;
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

    @PostMapping("/get-compilation")
    public ResponseEntity<Object> getCompilationReport(@Valid @RequestBody GetCompilationReportRequest request) {
        CompilationReportDto reportDto = getCompilationReport.execute(ReportParser.mapToCompilationReportDto(request));
        return new ApiResponse<>().ok(reportDto);
    }
}
