package com.application.core.usecase.util.algorithm.structure;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.usecase.util.algorithm.util.DrStrange;
import com.application.core.usecase.util.algorithm.util.Time;
import com.application.shared.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Problem {
    private String startPoint;
    private String goalPoint;
    private LocalDateTime requestDateTime;
    private Network network;

    public Boolean goalTest(String point) {
        return goalPoint.equals(point);
    }

    public List<String> possibleActions(String of) {
        List<String> actions = new ArrayList<>();
        List<Edge> neighbors = network.getNeighbors().get(of);
        for (Edge neighbor : neighbors) {
            actions.add(neighbor.getFlightFriendlyId());
        }
        return actions;
    }

    public String newStateFrom(String previousState, String action) {
        List<Edge> destinations = network.getNeighbors().get(previousState);
        for (Edge destiny : destinations) {
            if (destiny.getFlightFriendlyId().equals(action))
                return destiny.getArrivalPoint();
        }
        return Constant.DESTINY_NOT_FOUND;
    }

    public HashMap<String, String> updateTimeCosts(Node parentNode, String choosedAction) {
        HashMap<String, String> updatedTimeCosts = new HashMap<>();
        List<Edge> destinations = network.getNeighbors().get(parentNode.getCurrentState());

        for (Edge destiny : destinations) {
            if (destiny.getFlightFriendlyId().equals(choosedAction)) {
                Time elapsedTime = DrStrange.fromStringToTime(destiny.getElapsedTime());
                Time waitingTime = calculateWaitingTimeOf(parentNode, destiny);
                //calculate times
                String updatedTransportTime = DrStrange.getNoLimitSum(parentNode.getTransportTime(), elapsedTime).toString();
                String updatedWaitingTime = DrStrange.getNoLimitSum(parentNode.getWaitingTime(), waitingTime).toString();
                String updatedTotalTime = DrStrange.getNoLimitSum(updatedTransportTime, updatedWaitingTime).toString();
                updatedTimeCosts.put("transportTime", updatedTransportTime);
                updatedTimeCosts.put("waitingTime", updatedWaitingTime);
                updatedTimeCosts.put("totalTime", updatedTotalTime);
            }
        }
        return updatedTimeCosts;
    }

    public Time calculateWaitingTimeOf(Node parent, Edge destiny) {
        // the time where i want to departure
        String departureTime = destiny.getDepartureTime();
        if (parent.getPerformedAction().equals(Constant.DEFAULT_ACTION)) {
            //obtain the first arrival time
            String firstArrivalTime = requestDateTime.toLocalTime().toString();
            return DrStrange.fromStringToTime(DrStrange.getElapsedTime(firstArrivalTime, departureTime));
        } else {
            Edge parentEdge = searchEdgeOf(parent.getParent().getCurrentState(), parent.getPerformedAction());
            //obtain the time which parent arrived
            String parentArrivalTime = parentEdge.getArrivalTime();
            return DrStrange.fromStringToTime(DrStrange.getElapsedTime(parentArrivalTime, departureTime));
        }
    }

    public Edge searchEdgeOf(String currentState, String actionPerformed) {
        List<Edge> destinations = network.getNeighbors().get(currentState);
        for (Edge destiny : destinations) {
            if (destiny.getFlightFriendlyId().equals(actionPerformed)) {
                return destiny;
            }
        }
        return null;
    }
}
