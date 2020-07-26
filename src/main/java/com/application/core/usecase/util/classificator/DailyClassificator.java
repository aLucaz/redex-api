package com.application.core.usecase.util.classificator;

import com.application.core.model.dto.IncidentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.report.DetailDayDto;

import java.time.LocalDate;
import java.util.List;

public interface DailyClassificator {
    List<DetailDayDto> classificateByDateAndHour(List<LocalDate> dateRange , List<IncidentDto> incidents, List<ShipmentForBranchDto> routes);
}
