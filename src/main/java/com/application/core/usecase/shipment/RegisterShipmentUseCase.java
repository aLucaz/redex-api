package com.application.core.usecase.shipment;

import com.application.core.model.dto.PackageDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGenerator;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGeneratorImpl;
import com.application.data.gateway.PackageGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterShipmentUseCase {
    ShipmentGateway shipmentGateway;
    ShipmentForBranchGateway shipmentForBranchGateway;
    ShipmentStateGateway shipmentStateGateway;
    PackageGateway packageGateway;
    ReferenceCodeGenerator referenceCodeGenerator;

    public RegisterShipmentUseCase(ShipmentGateway shipmentGateway, ShipmentForBranchGateway shipmentForBranchGateway,
                                   PackageGateway packageGateway, ShipmentStateGateway shipmentStateGateway,
                                   ReferenceCodeGeneratorImpl referenceCodeGenerator) {
        this.shipmentGateway = shipmentGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.packageGateway = packageGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.referenceCodeGenerator = referenceCodeGenerator;
    }

    public void execute(ShipmentDto shipmentDto, List<ShipmentForBranchDto> shipmentForBranchDtoList, List<PackageDto> packageDtoList) {
        ShipmentDto shipmentResponse = shipmentGateway.persist(shipmentDto.setReferenceCode(referenceCodeGenerator.generateReferenceCode()));
        for (ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList) {
            shipmentForBranchDto.setIdShipment(shipmentResponse.getIdShipment());
            shipmentForBranchDto.setIdShipmentState(shipmentStateGateway.getDefaultShipmentState(Constant.DEFAULT_SHIPMENT_STATE_FRIENDLY_ID));
        }
        shipmentForBranchGateway.persist(shipmentForBranchDtoList);
        for (PackageDto packageDto : packageDtoList) {
            packageDto.setIdShipment(shipmentResponse.getIdShipment());
        }
        packageGateway.persist(packageDtoList);
    }
}
