package com.application.data.parser;

import com.application.core.model.business.Branch;
import com.application.core.model.dto.BranchDto;

import java.util.ArrayList;
import java.util.List;

public class BranchParser {

    public static BranchDto mapToDto(Branch branch) {
        return new BranchDto()
                .setIdBranch(branch.getIdBranch())
                .setName(branch.getName())
                .setFriendlyId(branch.getFriendlyId())
                .setCapacity(branch.getCapacity())
                .setQuantity(branch.getQuantity())
                .setContinent(branch.getContinent())
                .setLatitude(branch.getLatitude())
                .setLongitude(branch.getLongitude())
                .setIsActive(branch.getIsActive());
    }

    public static List<BranchDto> mapToDtoList(List<Branch> branchList) {
        List<BranchDto> branchDtoList = new ArrayList<>();
        for (Branch branch : branchList) {
            branchDtoList.add(mapToDto(branch));
        }
        return branchDtoList;
    }

    public static Branch mapToRow(BranchDto branchDto){
        return new Branch()
                .setIdBranch(branchDto.getIdBranch());
    }
}
