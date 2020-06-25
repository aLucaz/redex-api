package com.application.core.usecase.packagingType;

import com.application.core.model.dto.PackagingTypeDto;
import com.application.data.gateway.PackagingTypeGateway;
import com.application.data.parser.PackagingTypeParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class GetPackagingTypeListUseCase {
    private final PackagingTypeGateway packagingTypeGateway;
    @SneakyThrows
    public List<PackagingTypeDto> execute() {

        return PackagingTypeParser.mapToDtoList(packagingTypeGateway.findAll());
    }
}
