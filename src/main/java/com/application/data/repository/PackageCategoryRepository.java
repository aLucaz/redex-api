package com.application.data.repository;

import com.application.core.model.business.PackageCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PackageCategoryRepository extends CrudRepository<PackageCategory, Integer> {
    List<PackageCategory> findAll();
}
