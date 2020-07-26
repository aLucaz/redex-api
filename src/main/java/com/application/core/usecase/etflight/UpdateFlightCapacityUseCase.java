package com.application.core.usecase.etflight;


import com.application.core.model.business.*;
import com.application.data.gateway.EtFlightGateway;
import org.springframework.stereotype.Service;

@Service
public class UpdateFlightCapacityUseCase {
    public final EtFlightGateway etFlightGateway;

    public UpdateFlightCapacityUseCase(EtFlightGateway etFlightGateway) {
        this.etFlightGateway = etFlightGateway;
    }

    public void execute(String idFlight, Integer capacity) {
        // call gateway to persist massive flights
        EtFlight etFlight = etFlightGateway.findByIdEdFlight(idFlight);
        etFlight.setCapacity(capacity);
        etFlightGateway.persist(etFlight);
    }

}