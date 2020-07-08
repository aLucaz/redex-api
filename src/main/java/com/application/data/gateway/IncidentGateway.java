package com.application.data.gateway;

import com.application.core.model.business.Incident;
import com.application.core.model.dto.IncidentDto;
import com.application.data.parser.IncidentParser;
import com.application.data.repository.IncidentRepository;
import com.application.shared.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class IncidentGateway {
    private final IncidentRepository repository;

    public IncidentGateway(IncidentRepository repository) {
        this.repository = repository;
    }

    public void saveAllSimulated(List<IncidentDto> incidentDtoList) {
        repository.saveAll(IncidentParser.mapToRowList(incidentDtoList));
    }

    public void saveAllIncidents(List<IncidentDto> incidentDtoList) {
        repository.saveAll(IncidentParser.mapToRowList(incidentDtoList));
    }

    public List<IncidentDto> findIncidentsOfBranchAndDate(String branchFriendlyId, LocalDate date, String incidentType) {
        List<Incident> incidentList = repository.findAllByIncidentTypeAndBranchFriendlyIdAndIncidentDateTimeBetween(incidentType, branchFriendlyId, LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date, LocalTime.MAX));
        return IncidentParser.mapToDtoList(incidentList);
    }
    public List<Incident> findAllSimulatedAndActive(){
        return repository.findAllByIsSimulatedAndIsActive(Constant.IS_ACTIVE,Constant.IS_ACTIVE);
    }
}
