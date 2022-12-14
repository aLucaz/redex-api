package com.application.data.gateway;

import com.application.core.model.business.Incident;
import com.application.core.model.dto.IncidentDto;
import com.application.data.parser.IncidentParser;
import com.application.data.repository.IncidentRepository;
import com.application.shared.Constant;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        List<Incident> incidentList = repository.findAllByIncidentTypeAndBranchFriendlyIdAndIncidentDateTimeBetweenAndIsActive(
                incidentType,
                branchFriendlyId,
                LocalDateTime.of(date, LocalTime.MIN),
                LocalDateTime.of(date, LocalTime.MAX),
                Constant.ACTIVEB);
        return IncidentParser.mapToDtoList(incidentList);
    }

    public List<Incident> findAllSimulatedAndActive() {
        return repository.findAllByIsSimulatedAndIsActive(Constant.IS_ACTIVE, Constant.IS_ACTIVE);
    }

    public List<IncidentDto> findAllValidIncidentsInRange(String incidentType, String friendlyId,
                                                          LocalDateTime start, LocalDateTime end,
                                                          Boolean ofSimulated) {
        List<Incident> incidentList = repository.findAllByIsActiveAndIsSimulatedAndIncidentTypeAndBranchFriendlyIdAndIncidentDateTimeBetween(
                Constant.IS_ACTIVE,
                ofSimulated,
                incidentType,
                friendlyId,
                start,
                end
        );
        if (incidentList.size() == 0)
            return new ArrayList<>();
        return IncidentParser.mapToDtoList(incidentList);
    }
}
