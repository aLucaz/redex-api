package com.application.core.usecase.util.classificator;

import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.report.DetailHourDto;

import java.util.List;

public interface HourlyClassificator {
    List<DetailHourDto> classificateByHourOfDay(List<IncidentDto> incidents, List<ShipmentForBranchDto> routes);
}
