package com.application.data.gateway;

import com.application.core.model.dto.IncidentDto;
import com.application.data.parser.IncidentParser;
import com.application.data.repository.IncidentRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IncidentGateway {
    private final IncidentRepository repository;

    public IncidentGateway(IncidentRepository repository) {
        this.repository = repository;
    }

    public void saveAllSimulated(List<IncidentDto> incidentDtoList){
        repository.saveAll(IncidentParser.mapToRowList(incidentDtoList));
    }
}
