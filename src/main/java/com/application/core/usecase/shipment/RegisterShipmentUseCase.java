package com.application.core.usecase.shipment;

import com.application.core.model.business.Package;
import com.application.core.model.business.Shipment;
import com.application.core.model.dto.*;
import com.application.data.gateway.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterShipmentUseCase {
    ShipmentGateway shipmentGateway;
    ShipmentForBranchGateway shipmentForBranchGateway;
    PackageGateway packageGateway;

    public RegisterShipmentUseCase(ShipmentGateway shipmentGateway, ShipmentForBranchGateway shipmentForBranchGateway,PackageGateway packageGateway) {
        this.shipmentGateway = shipmentGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.packageGateway = packageGateway;
    }

    public void execute(ShipmentDto shipmentDto,List<ShipmentForBranchDto> shipmentForBranchDtoList, List<PackageDto> packageDtoList) {
        ShipmentDto shipmentResponse = shipmentGateway.persist(shipmentDto);
        for(ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList){
            shipmentForBranchDto.setIdShipment(shipmentResponse.getIdShipment());
        }
        shipmentForBranchGateway.persist(shipmentForBranchDtoList);
        for(PackageDto packageDto : packageDtoList){
            packageDto.setIdShipment(shipmentResponse.getIdShipment());
        }
        packageGateway.persist(packageDtoList);
    }
}
