package com.application.data.repository;

import com.application.core.model.business.Branch;
import com.application.core.model.business.ShipmentForBranch;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ShipmentForBranchRepository extends CrudRepository<ShipmentForBranch, Integer> {
    List<ShipmentForBranch> findAllByBranch(Branch branch);
    List<ShipmentForBranch> findAllByBranchIdBranchAndCurrentArrivalDateTimeBetween(Integer idBranch, LocalDateTime start, LocalDateTime end);
    List<ShipmentForBranch> findByBranchIdBranchAndShipmentIsActive(Integer idBranch,Boolean isActive);
    ShipmentForBranch findByShipmentIdShipmentAndBranchIdBranch(Integer idShipment,Integer idBranch);
}
