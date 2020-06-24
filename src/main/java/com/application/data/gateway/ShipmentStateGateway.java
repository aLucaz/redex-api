package com.application.data.gateway;

import com.application.data.repository.ShipmentStateRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ShipmentStateGateway {
    private final ShipmentStateRepository repository;

    public ShipmentStateGateway(ShipmentStateRepository repository) {
        this.repository = repository;
    }

    public Integer getDefaultShipmentState(String defaultFriendlyId){
        return repository.getByFriendlyId(defaultFriendlyId).getIdShipmentState();
    }
}
