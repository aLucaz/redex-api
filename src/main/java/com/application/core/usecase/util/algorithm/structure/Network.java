package com.application.core.usecase.util.algorithm.structure;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Network {
    private HashMap<String, List<Edge>> neighbors;
}


