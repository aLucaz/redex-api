package com.application.data.repository;

import com.application.core.model.business.Branch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchRepository extends CrudRepository<Branch, Integer> {
    Branch findByFriendlyId(String friendlyId);
    List<Branch> findAllByIsActive(Integer active);
}
