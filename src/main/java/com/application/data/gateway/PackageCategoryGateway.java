package com.application.data.gateway;

import com.application.core.model.business.PackageCategory;
import com.application.data.repository.PackageCategoryRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PackageCategoryGateway {
    private final PackageCategoryRepository repository;

    public PackageCategoryGateway(PackageCategoryRepository repository) {
        this.repository = repository;
    }
    @SneakyThrows
    public List<PackageCategory> findAll() {
        List<PackageCategory> packageCategoryList = repository.findAll();
        return packageCategoryList;
    }
}
