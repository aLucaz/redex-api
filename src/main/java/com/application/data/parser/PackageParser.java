package com.application.data.parser;

import com.application.core.model.business.PackageCategory;
import com.application.core.model.business.PackagingType;
import com.application.core.model.business.Shipment;
import com.application.core.model.dto.PackageDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentPackageWrapper;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;
import com.application.core.model.business.Package;

import java.util.ArrayList;
import java.util.List;

public class PackageParser {

    public static List<PackageDto> mapToDto(RegisterShipmentRequest request) {
        List<PackageDto> lista = new ArrayList<PackageDto>();
        for(RegisterShipmentPackageWrapper wra : request.getPackages()){
            lista.add(
                    new PackageDto()
                            .setIdPackagingType(wra.getIdPackagingType())
                            .setIdPackageCategory(wra.getIdPackageCategory())
                            .setDescription(wra.getDescription())
            );

        }
        return lista;
    }

    public static List<Package> mapToRow(List<PackageDto> packageDtoList) {
        List<Package> lista = new ArrayList<Package>();
        for(PackageDto packageDto : packageDtoList){
            lista.add(
                    new Package()
                            .setIdPackaging(new PackagingType().setIdPackagingType(packageDto.getIdPackagingType()))
                            .setIdCategory(new PackageCategory().setIdPackageCategory(packageDto.getIdPackageCategory()))
                            .setDescription(packageDto.getDescription())
                            .setIdShipment(new Shipment().setIdShipment(packageDto.getIdShipment()))
            );

        }
        return lista;
    }
}
