package com.application.data.gateway;

import com.application.core.model.business.ShipmentState;
import com.application.data.repository.ShipmentStateRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShipmentStateGateway {
    private final ShipmentStateRepository repository;

    public ShipmentStateGateway(ShipmentStateRepository repository) {
        this.repository = repository;
    }

    public Integer getDefaultShipmentState(String defaultFriendlyId) {
        return repository.getByFriendlyId(defaultFriendlyId).getIdShipmentState();
    }

    public List<ShipmentState> findAll() {
        return repository.findAll();
    }

    public Integer findIdOf(String friendlyId) {
        return repository.getByFriendlyId(friendlyId).getIdShipmentState();
    }

}
