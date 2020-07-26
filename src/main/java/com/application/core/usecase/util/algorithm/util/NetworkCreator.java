package com.application.core.usecase.util.algorithm.util;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.EtFlightDto;
import com.application.core.usecase.util.algorithm.structure.Network;

import java.util.List;

public interface NetworkCreator {
    Network createNetwork(List<BranchDto> branchDtoList, List<EtFlightDto> etFlightDtoList);
}
