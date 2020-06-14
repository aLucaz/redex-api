package com.application.core.usecase.util.algorithm.util;

import com.application.core.usecase.util.algorithm.structure.Node;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pair implements Comparable<Pair> {
    private Node node;
    private Time time;

    public Pair(Node node, Time time) {
        this.node = node;
        this.time = time;
    }

    @Override
    public int compareTo(Pair otherPair) {
        return time.compareTo(otherPair.getTime());
    }
}
