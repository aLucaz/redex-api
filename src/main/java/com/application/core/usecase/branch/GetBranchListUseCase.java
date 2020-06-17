package com.application.core.usecase.branch;

import com.application.core.model.dto.BranchDto;
import com.application.data.gateway.BranchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetBranchListUseCase {
    private final BranchGateway branchGateway;

    public List<BranchDto> execute(){
        return branchGateway.fildAllActive();
    }
}
