package com.application.data.parser;

import com.application.core.model.business.Incident;
import com.application.core.model.dto.IncidentDto;
import com.application.shared.Constant;

import java.util.ArrayList;
import java.util.List;

public class IncidentParser {
    public static Incident mapToRow(IncidentDto incidentDto){
        return  new Incident()
                .setBranchFriendlyId(incidentDto.getBranchFriendlyId())
                .setFlightFriendlyId(incidentDto.getFlightFriendlyId())
                .setIncidentDateTime(incidentDto.getIncidentDateTime())
                .setIncidentType(Constant.CAPACITY_INCIDENT)
                .setIsActive(incidentDto.getIsActive())
                .setIsSimulated(incidentDto.getIsSimulated());
    }

    public static List<Incident> mapToRowList(List<IncidentDto> incidentDtoList){
        List<Incident> incidentList = new ArrayList<>();
        for (IncidentDto incidentDto: incidentDtoList) {
            incidentList.add(mapToRow(incidentDto));
        }
        return incidentList;
    }
}
