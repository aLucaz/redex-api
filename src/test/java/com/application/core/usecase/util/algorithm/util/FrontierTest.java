package com.application.core.usecase.util.algorithm.util;

import com.application.core.usecase.util.algorithm.structure.EvaluationFunction;
import com.application.core.usecase.util.algorithm.structure.Node;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.*;

class FrontierTest {

    @Test
    void add() {
        EvaluationFunction evaluationFunction = Node::getTotalTime;
        Frontier frontier = new Frontier(evaluationFunction, new PriorityQueue<>());
        Node node1 = new Node().setTotalTime(DrStrange.fromStringToTime("18:30"));
        Node node2 = new Node().setTotalTime(DrStrange.fromStringToTime("14:30"));
        Node node3 = new Node().setTotalTime(DrStrange.fromStringToTime("12:30"));
        Node node4 = new Node().setTotalTime(DrStrange.fromStringToTime("11:30"));
        Node node5 = new Node().setTotalTime(DrStrange.fromStringToTime("8:30"));
        frontier.add(node1);
        frontier.add(node2);
        frontier.add(node3);
        frontier.add(node4);
        frontier.add(node5);

        System.out.println(Objects.requireNonNull(frontier.getPriorityQueue().poll()).getTime().toString());
        System.out.println(Objects.requireNonNull(frontier.getPriorityQueue().poll()).getTime().toString());
        System.out.println(Objects.requireNonNull(frontier.getPriorityQueue().poll()).getTime().toString());
        System.out.println(Objects.requireNonNull(frontier.getPriorityQueue().poll()).getTime().toString());
        System.out.println(Objects.requireNonNull(frontier.getPriorityQueue().poll()).getTime().toString());


//        for (Pair pair : frontier.getPriorityQueue()){
//            System.out.println(pair.getTime().toString());
//        }

    }
}