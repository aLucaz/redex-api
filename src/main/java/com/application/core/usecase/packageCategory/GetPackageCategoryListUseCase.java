package com.application.core.usecase.packageCategory;


import com.application.core.model.dto.PackageCategoryDto;
import com.application.data.gateway.PackageCategoryGateway;
import com.application.data.parser.PackageCategoryParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetPackageCategoryListUseCase {
    private final PackageCategoryGateway packageCategoryGateway;
    @SneakyThrows
    public List<PackageCategoryDto> execute() {

        return PackageCategoryParser.mapToDtoList(packageCategoryGateway.findAll());
    }
}
