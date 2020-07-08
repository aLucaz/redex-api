package com.application.core.usecase.util.counter.incident;

import com.application.core.model.dto.IncidentDto;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentCounter {
    Integer countIncidentsOfDateHour(LocalDateTime dateTime, List<IncidentDto> incidents, String incidentType);
}
