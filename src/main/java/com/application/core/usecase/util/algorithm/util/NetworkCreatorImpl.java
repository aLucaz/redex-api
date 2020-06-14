package com.application.core.usecase.util.algorithm.util;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.usecase.util.algorithm.structure.Edge;
import com.application.core.usecase.util.algorithm.structure.Network;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class NetworkCreatorImpl implements NetworkCreator {

    @Override
    public Network createNetwork(List<BranchDto> branches, List<EtFlightDto> flights) {
        HashMap<String, List<Edge>> neighborsNetwork = new HashMap<>();
        for(EtFlightDto flight : flights){
            Edge edge = new Edge()
                    .setFlightFriendlyId(flight.getFriendlyId())
                    .setArrivalPoint(flight.getArrivalPoint())
                    .setArrivalTime(flight.getArrivalTime())
                    .setDepartureTime(flight.getDepartureTime())
                    .setElapsedTime(DrStrange.getElapsedTime(flight.getDepartureTime(), flight.getArrivalTime()));
            neighborsNetwork.computeIfAbsent(flight.getDeparturePoint(), key -> new ArrayList<>()).add(edge);
        }
        return new Network(neighborsNetwork);
    }
}
