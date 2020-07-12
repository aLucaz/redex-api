package com.application.data.parser;

import com.application.core.model.dto.report.CompilationReportDto;
import com.application.rest.api.request.GetCompilationReportRequest;

public class ReportParser {
    public static CompilationReportDto mapToCompilationReportDto(GetCompilationReportRequest reportRequest){
        return new CompilationReportDto()
                .setStartDate(reportRequest.getStartDate())
                .setEndDate(reportRequest.getEndDate());
    }
}
