package com.application.data.repository;

import com.application.core.model.business.ShipmentState;
import org.springframework.data.repository.CrudRepository;

public interface ShipmentStateRepository extends CrudRepository<ShipmentState, Integer> {
    ShipmentState getByFriendlyId(String friendlyId);
}
