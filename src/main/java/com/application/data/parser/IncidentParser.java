package com.application.data.parser;

import com.application.core.model.business.Incident;
import com.application.core.model.dto.IncidentDto;

import java.util.ArrayList;
import java.util.List;

public class IncidentParser {
    public static Incident mapToRow(IncidentDto incidentDto) {
        return new Incident()
                .setBranchFriendlyId(incidentDto.getBranchFriendlyId())
                .setFlightFriendlyId(incidentDto.getFlightFriendlyId())
                .setIncidentDateTime(incidentDto.getIncidentDateTime())
                .setIncidentType(incidentDto.getIncidentType())
                .setIsActive(incidentDto.getIsActive())
                .setIsSimulated(incidentDto.getIsSimulated());
    }

    public static List<Incident> mapToRowList(List<IncidentDto> incidentDtoList) {
        List<Incident> incidentList = new ArrayList<>();
        for (IncidentDto incidentDto : incidentDtoList) {
            incidentList.add(mapToRow(incidentDto));
        }
        return incidentList;
    }

    public static IncidentDto mapToDto(Incident incident){
        return new IncidentDto()
                .setBranchFriendlyId(incident.getBranchFriendlyId())
                .setFlightFriendlyId(incident.getFlightFriendlyId())
                .setIncidentDateTime(incident.getIncidentDateTime())
                .setIncidentType(incident.getIncidentType())
                .setIsActive(incident.getIsActive())
                .setIsSimulated(incident.getIsSimulated());
    }

    public static List<IncidentDto> mapToDtoList(List<Incident> incidentList){
        List<IncidentDto> incidentDtoList = new ArrayList<>();
        for (Incident incident : incidentList) {
            incidentDtoList.add(mapToDto(incident));
        }
        return incidentDtoList;
    }

}
