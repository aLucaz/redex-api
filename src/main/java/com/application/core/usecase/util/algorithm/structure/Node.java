package com.application.core.usecase.util.algorithm.structure;

import com.application.core.usecase.util.algorithm.util.DrStrange;
import com.application.core.usecase.util.algorithm.util.Time;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Node implements Comparable<Node>{
    private String currentState;
    private String performedAction;
    private Node parent;
    private Time transportTime;
    private Time waitingTime;
    private Time totalTime;

    public Node(String currentState, String performedAction, Node parent, Time transportTime, Time waitingTime, Time totalTime) {
        this.currentState = currentState;
        this.performedAction = performedAction; // to get here
        this.parent = parent;
        this.transportTime = transportTime; // from start until me
        this.waitingTime = waitingTime; // from start until my parent
        this.totalTime = totalTime; // from start until me TOTAL
    }

    @Override
    public int compareTo(Node otherNode) {
        return this.totalTime.compareTo(otherNode.getTotalTime());
    }

    public Node giveBirth(Problem problem, String choosedAction){
        String newState = problem.newStateFrom(currentState, choosedAction);
        HashMap<String, String> newTimeCosts = problem.updateTimeCosts(this, choosedAction);
        return new Node(
                newState,
                choosedAction,
                this,
                DrStrange.fromStringToTime(newTimeCosts.get("transportTime")),
                DrStrange.fromStringToTime(newTimeCosts.get("waitingTime")),
                DrStrange.fromStringToTime(newTimeCosts.get("totalTime"))
        );
    }


}
