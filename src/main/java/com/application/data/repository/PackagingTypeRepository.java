package com.application.data.repository;

import com.application.core.model.business.PackagingType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PackagingTypeRepository extends CrudRepository<PackagingType, Integer> {
    List<PackagingType> findAll();
}
