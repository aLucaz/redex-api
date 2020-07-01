package com.application.data.repository;

import com.application.core.model.business.Shipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    List<Shipment> findAllByIsSimulatedAndIsActive(Boolean isSimulated, Boolean isActive);
}
