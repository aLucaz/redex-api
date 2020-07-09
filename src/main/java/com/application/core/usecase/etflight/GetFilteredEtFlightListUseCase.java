package com.application.core.usecase.etflight;

import com.application.core.model.dto.*;
import com.application.data.gateway.EtFlightGateway;
import com.application.rest.api.request.*;
import com.application.shared.*;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;

@Service
public class GetFilteredEtFlightListUseCase {
    public final EtFlightGateway etFlightGateway;

    public GetFilteredEtFlightListUseCase(EtFlightGateway etFlightGateway)
    {
        this.etFlightGateway = etFlightGateway;
    }


    public List<EtFlightDto> execute(EtFlightFiltersDto filters) {
        if(filters.getDeparturePoint().equals(Constant.ALL_BRANCHES) ){
            return etFlightGateway.findAllByEtFlightDateAndIsActive(
                    filters.getEtFlightDate(),
                    Constant.ACTIVE);
        }
        else{
            return etFlightGateway.findAllByEtFlightDateAndDeparturePointAndIsActive(
                    filters.getEtFlightDate(),
                    filters.getDeparturePoint(),
                    Constant.ACTIVE);
        }
    }
}