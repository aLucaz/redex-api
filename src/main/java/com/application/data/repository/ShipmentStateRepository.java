package com.application.data.repository;

import com.application.core.model.business.ShipmentState;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentStateRepository extends CrudRepository<ShipmentState, Integer> {
    ShipmentState getByFriendlyId(String friendlyId);
    List<ShipmentState> findAll();
    ShipmentState findByIdShipmentState(Integer id);
}
