package com.application.data.repository;

import com.application.core.model.business.Package;
import org.springframework.data.repository.CrudRepository;

public interface PackageRepository extends CrudRepository<Package, Integer> {
}
