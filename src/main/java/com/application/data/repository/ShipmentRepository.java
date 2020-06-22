package com.application.data.repository;

import com.application.core.model.business.Shipment;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
}
