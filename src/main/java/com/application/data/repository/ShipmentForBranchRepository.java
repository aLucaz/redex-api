package com.application.data.repository;

import com.application.core.model.business.Branch;
import com.application.core.model.business.ShipmentForBranch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShipmentForBranchRepository extends CrudRepository<ShipmentForBranch, Integer> {
    List<ShipmentForBranch> findAllByBranch(Branch branch);
    List<ShipmentForBranch> findByBranchIdBranchAndShipmentIsActive(Integer idBranch,Boolean isActive);
}
