package com.application.core.usecase.util.algorithm;

import com.application.core.model.dto.RouteDto;
import com.application.core.usecase.util.algorithm.structure.Node;
import com.application.core.usecase.util.algorithm.structure.Problem;

import java.util.List;

public interface AlgorithmExecutor {
    List<RouteDto> execute(Problem problem);

    List<RouteDto> getPath(Node node);
}
