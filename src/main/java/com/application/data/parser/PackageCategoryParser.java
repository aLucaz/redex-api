package com.application.data.parser;

import com.application.core.model.business.*;
import com.application.core.model.dto.*;

import java.util.*;

public class PackageCategoryParser {
    public static List<PackageCategoryDto> mapToDtoList(List<PackageCategory> packageCategoryList) {
        List<PackageCategoryDto> packageCategoryDtoList = new ArrayList<>();
        for (PackageCategory packageCategory : packageCategoryList) {
            packageCategoryDtoList.add(mapToDto(packageCategory));
        }
        return packageCategoryDtoList;
    }
    public static PackageCategoryDto mapToDto(PackageCategory packageCategory) {
        return new PackageCategoryDto()
                .setIdPackageCategory(packageCategory.getIdPackageCategory())
                .setName(packageCategory.getName())
                .setDescription(packageCategory.getDescription())
                .setLastModifiedBy(packageCategory.getLastModifiedBy())
                .setLastModifiedDate(packageCategory.getLastModifiedDate())
                .setRegisteredBy(packageCategory.getRegisteredBy())
                .setRegisteredDate(packageCategory.getRegisteredDate())
                ;
    }
}
