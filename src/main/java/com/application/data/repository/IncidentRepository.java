package com.application.data.repository;

import com.application.core.model.business.Incident;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {
    List<Incident> findAllByIncidentTypeAndBranchFriendlyIdAndIncidentDateTimeBetween(String incidentType, String branchFriendlyId, LocalDateTime start, LocalDateTime end);
    List<Incident> findAllByIsSimulatedAndIsActive(Boolean isSimulated, Boolean isActive);
}
