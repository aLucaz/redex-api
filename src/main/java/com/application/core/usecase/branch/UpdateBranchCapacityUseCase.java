package com.application.core.usecase.branch;

import com.application.core.model.business.Branch;
import com.application.data.gateway.BranchGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UpdateBranchCapacityUseCase {
    private final BranchGateway branchGateway;

    public void execute(Integer idBranch, Integer capacity){
        Branch branch = branchGateway.findByIdBranch(idBranch);
        branch.setCapacity(capacity);
        branchGateway.persist(branch);
    }
}
