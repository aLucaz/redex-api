package com.application.core.usecase.etflight;


import com.application.data.gateway.EtFlightGateway;
import org.springframework.stereotype.Service;

@Service
public class RegisterMassiveEtFlightUseCase {
    public final EtFlightGateway etFlightGateway;

    public RegisterMassiveEtFlightUseCase(EtFlightGateway etFlightGateway) {
        this.etFlightGateway = etFlightGateway;
    }

    public void execute() {
        // call gateway to persist massive flights
        etFlightGateway.persistMassive();
    }

}
