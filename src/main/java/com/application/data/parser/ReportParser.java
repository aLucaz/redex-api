package com.application.data.parser;

import com.application.core.model.dto.report.CompilationReportDto;
import com.application.core.model.dto.report.OnTimeReportDto;
import com.application.rest.api.request.GetCompilationReportRequest;
import com.application.rest.api.request.GetOntimeReportRequest;

public class ReportParser {
    public static CompilationReportDto mapToCompilationReportDto(GetCompilationReportRequest reportRequest){
        return new CompilationReportDto()
                .setStartDate(reportRequest.getStartDate())
                .setEndDate(reportRequest.getEndDate())
                .setOfSimulated(reportRequest.getOfSimulated());
    }

    public static OnTimeReportDto mapToOnTimeReportDto(GetOntimeReportRequest reportRequest){
        return new OnTimeReportDto()
                .setStartDate(reportRequest.getStartDate())
                .setEndDate(reportRequest.getEndDate())
                .setOfSimulated(reportRequest.getOfSimulated());
    }
}
