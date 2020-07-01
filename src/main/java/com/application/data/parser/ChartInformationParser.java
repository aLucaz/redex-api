package com.application.data.parser;

import com.application.core.model.dto.ChartInformationDto;
import com.application.rest.api.request.GetChartInformationRequest;

public class ChartInformationParser {
    public static ChartInformationDto mapToDto (GetChartInformationRequest chartInformationRequest){
        return new ChartInformationDto()
                .setRequestDate(chartInformationRequest.getRequestDate());
    }

}
