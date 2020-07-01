package com.application.data.repository;

import com.application.core.model.business.Incident;
import org.springframework.data.repository.CrudRepository;

public interface IncidentRepository extends CrudRepository<Incident, Integer> {
}
