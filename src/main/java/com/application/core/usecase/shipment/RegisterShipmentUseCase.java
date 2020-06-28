package com.application.core.usecase.shipment;

import com.application.core.model.business.*;
import com.application.core.model.dto.PackageDto;
import com.application.core.model.dto.PersonDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.data.gateway.*;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterShipmentUseCase {
    ShipmentGateway shipmentGateway;
    ShipmentForBranchGateway shipmentForBranchGateway;
    ShipmentStateGateway shipmentStateGateway;
    PackageGateway packageGateway;
    PersonGateway personGateway;

    public RegisterShipmentUseCase(ShipmentGateway shipmentGateway, ShipmentForBranchGateway shipmentForBranchGateway,
                                   PackageGateway packageGateway, ShipmentStateGateway shipmentStateGateway,
                                   PersonGateway personGateway){
        this.shipmentGateway = shipmentGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.packageGateway = packageGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.personGateway = personGateway;
    }

    public void execute(ShipmentDto shipmentDto, List<ShipmentForBranchDto> shipmentForBranchDtoList,
                        List<PackageDto> packageDtoList, PersonDto customerPersonDto, PersonDto receiverPersonDto) {

        //NOTA: Tambien se deberia buscar por el tipo de documento
        Person customer = personGateway.findByDocument(customerPersonDto.getDocumentType(),customerPersonDto.getDocument());
        Person receiver = personGateway.findByDocument(receiverPersonDto.getDocumentType(),receiverPersonDto.getDocument());

        if(customer == null){ customer = personGateway.persist(customerPersonDto);}
        if(receiver == null){ receiver = personGateway.persist(receiverPersonDto);}

        ShipmentDto shipmentResponse = shipmentGateway.persist(shipmentDto);
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
