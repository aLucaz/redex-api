package com.application.data.gateway;

import com.application.core.model.business.PackagingType;
import com.application.data.repository.PackagingTypeRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PackagingTypeGateway {
    private final PackagingTypeRepository repository;

    public PackagingTypeGateway(PackagingTypeRepository repository) {
        this.repository = repository;
    }
    @SneakyThrows
    public List<PackagingType> findAll() {
        List<PackagingType> packagingTypeList = repository.findAll();
        return packagingTypeList;
    }
}
