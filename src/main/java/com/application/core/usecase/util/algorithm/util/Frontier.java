package com.application.core.usecase.util.algorithm.util;

import com.application.core.usecase.util.algorithm.structure.EvaluationFunction;
import com.application.core.usecase.util.algorithm.structure.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.PriorityQueue;

@Setter
@Getter
public class Frontier {
    private EvaluationFunction evaluationFunction;
    private PriorityQueue<Pair> priorityQueue;

    public Frontier(EvaluationFunction evaluationFunction, PriorityQueue<Pair> priorityQueue) {
        this.evaluationFunction = evaluationFunction;
        this.priorityQueue = priorityQueue;
    }

    public void add(Node node) {
        priorityQueue.add(new Pair(node, evaluationFunction.evaluate(node)));
    }

    public Boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public Node pop() {
        return Objects.requireNonNull(priorityQueue.poll()).getNode();
    }

    public Boolean contains(Node node) {
        for (Pair pair : priorityQueue) {
            if (node.getPerformedAction().equals(pair.getNode().getPerformedAction())) {
                return true;
            }
        }
        return false;
    }

    public Time getValueOf(Node node) {
        for (Pair pair : priorityQueue) {
            if (node.getPerformedAction().equals(pair.getNode().getPerformedAction())) {
                return pair.getTime();
            }
        }
        return null;
    }

    public void replace(Node node) {
        priorityQueue.removeIf(pair -> pair.getNode().getPerformedAction().equals(node.getPerformedAction()));
        priorityQueue.add(new Pair(node, evaluationFunction.evaluate(node)));
    }
}
