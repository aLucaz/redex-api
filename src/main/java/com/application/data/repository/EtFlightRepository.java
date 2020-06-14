package com.application.data.repository;

import com.application.core.model.business.EtFlight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EtFlightRepository extends CrudRepository<EtFlight, Integer> {
    List<EtFlight> findAllByEtFlightDateBetween(String etFlightDate, String etFlightDate2);
    List<EtFlight> findAllByEtFlightDateBetweenAndIsActive(String etFlightDate, String etFlightDate2, Integer active);
}
