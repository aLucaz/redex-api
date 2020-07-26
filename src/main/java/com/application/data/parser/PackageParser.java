package com.application.data.parser;

import com.application.core.model.business.Package;
import com.application.core.model.business.PackageCategory;
import com.application.core.model.business.PackagingType;
import com.application.core.model.business.Shipment;
import com.application.core.model.dto.PackageDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentPackageWrapper;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.util.ArrayList;
import java.util.List;

public class PackageParser {

    public static List<PackageDto> mapToDto(RegisterShipmentRequest request) {
        List<PackageDto> packageDtoList = new ArrayList<>();
        for (RegisterShipmentPackageWrapper wrapper : request.getPackages()) {
            packageDtoList.add(
                    new PackageDto()
                            .setIdPackagingType(wrapper.getIdPackagingType())
                            .setIdPackageCategory(wrapper.getIdPackageCategory())
                            .setWeight(wrapper.getWeight())
                            .setDimensionX(wrapper.getDimensionX())
                            .setDimensionY(wrapper.getDimensionY())
                            .setDimensionZ(wrapper.getDimensionZ())
                            .setDescription(wrapper.getDescription())
                            .setIsDangerous(wrapper.getIsDangerous())
            );
        }
        return packageDtoList;
    }

    public static List<Package> mapToRowList(List<PackageDto> packageDtoList) {
        List<Package> packageList = new ArrayList<>();
        for (PackageDto packageDto : packageDtoList) {
            packageList.add(
                    new Package()
                            .setShipment(new Shipment().setIdShipment(packageDto.getIdShipment()))
                            .setPackagingType(new PackagingType().setIdPackagingType(packageDto.getIdPackagingType()))
                            .setPackageCategory(new PackageCategory().setIdPackageCategory(packageDto.getIdPackageCategory()))
                            .setWeight(packageDto.getWeight())
                            .setDimensionX(packageDto.getDimensionX())
                            .setDimensionY(packageDto.getDimensionY())
                            .setDimensionZ(packageDto.getDimensionZ())
                            .setDescription(packageDto.getDescription())
                            .setIsDangerous(packageDto.getIsDangerous())
            );
        }
        return packageList;
    }
}
