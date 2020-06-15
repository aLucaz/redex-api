package com.application.core.usecase.util.algorithm;

import com.application.core.model.dto.RouteDto;
import com.application.core.usecase.util.algorithm.structure.EvaluationFunction;
import com.application.core.usecase.util.algorithm.structure.Node;
import com.application.core.usecase.util.algorithm.structure.Problem;
import com.application.core.usecase.util.algorithm.util.DrStrange;
import com.application.core.usecase.util.algorithm.util.Frontier;
import com.application.shared.Constant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

@Setter
@Getter
@Component
public class AstarAlgorithmExecutorImpl implements AlgorithmExecutor {
    @Override
    public List<RouteDto> execute(Problem problem) {
        // TODO: add heuristic to the evaluation function
        EvaluationFunction evaluationFunction = Node::getTotalTime;
        Frontier frontier = new Frontier(evaluationFunction, new PriorityQueue<>());
        frontier.add(new Node(problem.getStartPoint(),
                Constant.DEFAULT_ACTION,
                null,
                DrStrange.getZeroTime(),
                DrStrange.getZeroTime(),
                DrStrange.getZeroTime()));

        Set<String> explored = new HashSet<>();
        while (!frontier.isEmpty()) {
            Node node = frontier.pop();
            explored.add(node.getPerformedAction());
            if (problem.goalTest(node.getCurrentState()))
                return getPath(node);
            for (String possibleAction : node.getParent() == null ? problem.allActionsFrom(node) : problem.possibleActionsFrom(node)) {
                Node child = node.giveBirth(problem, possibleAction);
                if (!explored.contains(possibleAction) && !frontier.contains(child)) {
                    frontier.add(child);
                } else if (frontier.contains(child)) {
                    if (evaluationFunction.evaluate(child).compareTo(frontier.getValueOf(child)) < 0) {
                        frontier.replace(child);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<RouteDto> getPath(Node node) {
        List<RouteDto> path = new ArrayList<>();
        while (node != null) {
            if (node.getParent() != null)
                path.add(new RouteDto()
                        .setFlightFriendlyId(node.getPerformedAction())
                        .setStartPoint(node.getParent().getCurrentState())
                        .setEndPoint(node.getCurrentState())
                        .setTransportTime(node.getTransportTime().toString())
                        .setWaitingTime(node.getWaitingTime().toString())
                        .setTotalTime(node.getTotalTime().toString()));
            node = node.getParent();
        }
        Collections.reverse(path);
        return path;
    }


}
