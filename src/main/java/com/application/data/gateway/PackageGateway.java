package com.application.data.gateway;

import com.application.core.model.business.Package;
import com.application.core.model.dto.PackageDto;
import com.application.data.parser.PackageParser;
import com.application.data.repository.PackageRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PackageGateway {
    private final PackageRepository repository;

    public PackageGateway(PackageRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    public void persist(List<PackageDto> packageDtoList) {
        List<Package> packageList = PackageParser.mapToRowList(packageDtoList);
        repository.saveAll(packageList);
    }
}
