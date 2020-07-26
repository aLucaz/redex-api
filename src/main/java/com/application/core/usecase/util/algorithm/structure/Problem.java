package com.application.core.usecase.util.algorithm.structure;

import com.application.core.usecase.util.algorithm.util.DrStrange;
import com.application.core.usecase.util.algorithm.util.Time;
import com.application.shared.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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

    public List<String> possibleActionsFrom(Node me) {
        List<String> actions = new ArrayList<>();
        List<Edge> neighbors = network.getNeighbors().getOrDefault(me.getCurrentState(), new ArrayList<>());
        HashMap<String, String> arrivalMap = getArrivalInformationOf(me.getParent(), me.getPerformedAction());
        for (Edge neighbor : neighbors) {
            /*
             * I cant take flights that departure:
             * * in the same date of arrivalDate and before of arrivalTime OR
             * * before the date of arrivalDate */
            if (!DrStrange.dateTimeIsBefore(neighbor.getDepartureTime(), neighbor.getFlightDate(), arrivalMap.get("arrivalTime"), arrivalMap.get("arrivalDate")) &&
                    !DrStrange.dateIsBefore(neighbor.getFlightDate(), arrivalMap.get("arrivalDate")))
                actions.add(neighbor.getFlightFriendlyId());
        }
        return actions;
    }

    public List<String> allActionsFrom(Node me) {
        List<String> actions = new ArrayList<>();
        List<Edge> neighbors = network.getNeighbors().getOrDefault(me.getCurrentState(), new ArrayList<>());
        for (Edge neighbor : neighbors) {
            actions.add(neighbor.getFlightFriendlyId());
        }
        return actions;
    }

    public HashMap<String, String> getArrivalInformationOf(Node node, String performedAction) {
        HashMap<String, String> arrivalInformation = new HashMap<>();
        List<Edge> destinations = network.getNeighbors().get(node.getCurrentState());

        for (Edge destiny : destinations) {
            if (destiny.getFlightFriendlyId().equals(performedAction)) {
                arrivalInformation.put("arrivalTime", destiny.getArrivalTime());
                arrivalInformation.put("arrivalDate", DrStrange.addTimeToDate(destiny.getFlightDate(), destiny.getDepartureTime(), destiny.getArrivalTime()));
            }
        }
        return arrivalInformation;
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
        String departureDate = destiny.getFlightDate();
        if (parent.getPerformedAction().equals(Constant.DEFAULT_ACTION)) {
            //obtain the first arrival time
            String firstArrivalTime = requestDateTime.toLocalTime().toString();
            String firstArrivalDate = requestDateTime.toLocalDate().toString();
            return DrStrange.getElapsedTime(firstArrivalTime, firstArrivalDate, departureTime, departureDate);
        } else {
            Edge parentEdge = searchEdgeOf(parent.getParent().getCurrentState(), parent.getPerformedAction());
            //obtain the time which parent arrived
            String parentDepartureTime = parentEdge.getDepartureTime();
            String parentArrivalTime = parentEdge.getArrivalTime();
            String parentArrivalDate = parentEdge.getFlightDate();
            if (DrStrange.fromStringToTime(parentDepartureTime).compareTo(DrStrange.fromStringToTime(parentArrivalTime)) >= 0)
                parentArrivalDate = LocalDate.parse(parentArrivalDate, Constant.DATE_FORMATTER).plusDays(1).toString();
            return DrStrange.getElapsedTime(parentArrivalTime, parentArrivalDate, departureTime, departureDate);
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
