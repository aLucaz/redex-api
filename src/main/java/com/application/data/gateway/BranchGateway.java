package com.application.data.gateway;

import com.application.core.model.business.Branch;
import com.application.core.model.dto.BranchDto;
import com.application.data.parser.BranchParser;
import com.application.data.repository.BranchRepository;
import com.application.shared.Constant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BranchGateway {
    private final BranchRepository repository;

    public BranchGateway(BranchRepository repository) {
        this.repository = repository;
    }

    public Branch findByIdBranch(Integer idBranch){
        return  repository.findByIdBranch(idBranch);
    }

    public List<BranchDto> findAll(){
        // get all business models
        List<Branch> branchList = (List<Branch>) repository.findAll();
        // return dto list
        return BranchParser.mapToDtoList(branchList);
    }

    public List<BranchDto> fildAllActive(){
        List<Branch> branchList = repository.findAllByIsActive(Constant.ACTIVE);
        return BranchParser.mapToDtoList(branchList);
    }

    public Boolean askIfIsActive(String friendlyId){
        Branch branch = repository.findByFriendlyId(friendlyId);
        return branch.getIsActive().equals(Constant.ACTIVE);
    }

    public String findNameOf(String friendlyId){
        return repository.findByFriendlyId(friendlyId).getName();
    }

    public Integer findIdOf(String friendlyId){
        return repository.findByFriendlyId(friendlyId).getIdBranch();
    }

    public String findContinentOf(String friendlyId){
        return repository.findByFriendlyId(friendlyId).getContinent();
    }

    public BranchDto findById(Integer branchId){
        Optional<Branch> branchOptional = repository.findById(branchId);
        return branchOptional.map(BranchParser::mapToDto).orElse(null);
    }

    public Branch persist(Branch branch){
        return repository.save(branch);
    }
}
