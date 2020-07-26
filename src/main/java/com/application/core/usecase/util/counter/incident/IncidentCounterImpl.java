package com.application.core.usecase.util.counter.incident;

import com.application.core.model.dto.IncidentDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class IncidentCounterImpl implements IncidentCounter {
    @Override
    public Integer countIncidentsOfDateHour(LocalDateTime dateTime, List<IncidentDto> incidents, String incidentType) {
        Integer numberOfIncidents = 0;
        for (IncidentDto incident : incidents) {
            if (incident.getIncidentType().equals(incidentType) && incident.getIncidentDateTime().getHour() == dateTime.getHour()) {
                numberOfIncidents++;
            }
        }
        return numberOfIncidents;
    }

}
