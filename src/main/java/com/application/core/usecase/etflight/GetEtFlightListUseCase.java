package com.application.core.usecase.etflight;

import com.application.core.model.dto.*;
import com.application.data.gateway.EtFlightGateway;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetEtFlightListUseCase {
    public final EtFlightGateway etFlightGateway;

    public GetEtFlightListUseCase(EtFlightGateway etFlightGateway)
    {
        this.etFlightGateway = etFlightGateway;
    }

    public List<EtFlightDto> execute() {
        // call gateway to persist massive flights
        return etFlightGateway.findAll();
    }

}
