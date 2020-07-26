package com.application.data.parser;

import com.application.core.model.business.*;
import com.application.core.model.dto.*;

import java.util.*;

public class PackagingTypeParser {

    public static List<PackagingTypeDto> mapToDtoList(List<PackagingType> packagingTypeList) {
        List<PackagingTypeDto> packagingTypeDtoList = new ArrayList<>();
        for (PackagingType packagingType : packagingTypeList) {
            packagingTypeDtoList.add(mapToDto(packagingType));
        }
        return packagingTypeDtoList;
    }
    public static PackagingTypeDto mapToDto(PackagingType packagingType) {
        return new PackagingTypeDto()
                .setIdPackagingType(packagingType.getIdPackagingType())
                .setName(packagingType.getName())
                .setDescription(packagingType.getDescription())
                .setPrice(packagingType.getPrice())
                .setLastModifiedBy(packagingType.getLastModifiedBy())
                .setLastModifiedDate(packagingType.getLastModifiedDate())
                .setRegisteredBy(packagingType.getRegisteredBy())
                .setRegisteredDate(packagingType.getRegisteredDate())
                ;
    }
}
