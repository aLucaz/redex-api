package com.application.core.usecase.shipment;

import com.application.core.model.dto.PackageDto;
import com.application.core.model.dto.PersonDto;
import com.application.core.model.dto.ShipmentCoreDto;
import com.application.core.model.dto.ShipmentDto;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.ShipmentForPersonDto;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGenerator;
import com.application.core.usecase.util.generator.referenceCode.ReferenceCodeGeneratorImpl;
import com.application.data.gateway.EtFlightGateway;
import com.application.data.gateway.PackageGateway;
import com.application.data.gateway.PersonGateway;
import com.application.data.gateway.PersonTypeGateway;
import com.application.data.gateway.ShipmentForBranchGateway;
import com.application.data.gateway.ShipmentForPersonGateway;
import com.application.data.gateway.ShipmentGateway;
import com.application.data.gateway.ShipmentStateGateway;
import com.application.shared.Constant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RegisterShipmentUseCase {
    private final ShipmentGateway shipmentGateway;
    private final ShipmentForBranchGateway shipmentForBranchGateway;
    private final ShipmentStateGateway shipmentStateGateway;
    private final PackageGateway packageGateway;
    private final ReferenceCodeGenerator referenceCodeGenerator;
    private final PersonGateway personGateway;
    private final ShipmentForPersonGateway shipmentForPersonGateway;
    private final PersonTypeGateway personTypeGateway;
    private final EtFlightGateway etFlightGateway;

    public RegisterShipmentUseCase(ShipmentGateway shipmentGateway, ShipmentForBranchGateway shipmentForBranchGateway,
                                   PackageGateway packageGateway, ShipmentStateGateway shipmentStateGateway,
                                   ReferenceCodeGeneratorImpl referenceCodeGenerator, PersonGateway personGateway,
                                   ShipmentForPersonGateway shipmentForPersonGateway, PersonTypeGateway personTypeGateway,
                                   EtFlightGateway etFlightGateway) {
        this.shipmentGateway = shipmentGateway;
        this.shipmentForBranchGateway = shipmentForBranchGateway;
        this.packageGateway = packageGateway;
        this.shipmentStateGateway = shipmentStateGateway;
        this.referenceCodeGenerator = referenceCodeGenerator;
        this.personGateway = personGateway;
        this.shipmentForPersonGateway = shipmentForPersonGateway;
        this.personTypeGateway = personTypeGateway;
        this.etFlightGateway = etFlightGateway;
    }

    public ShipmentCoreDto execute(ShipmentDto shipmentDto, List<ShipmentForBranchDto> shipmentForBranchDtoList,
                                   List<PackageDto> packageDtoList, PersonDto customerPersonDto, PersonDto receiverPersonDto) {
        // first we save all the persons that not are already in the database
        HashMap<String, PersonDto> peopleProcessed = persistAndGetPeopleInformation(customerPersonDto, receiverPersonDto);
        // now we save the shipment information
        ShipmentDto shipmentResponse = shipmentGateway.persist(
                shipmentDto.setReferenceCode(referenceCodeGenerator.generateReferenceCode()),
                shipmentForBranchDtoList.get(shipmentForBranchDtoList.size() - 1)
        );
        //now we save all the people related to the shipment
        List<ShipmentForPersonDto> shipmentForPersonList = getShipmentForPersonInformation(peopleProcessed, shipmentResponse);
        shipmentForPersonGateway.persistAll(shipmentForPersonList);
        // now we save all the route related to the shipment
        for (ShipmentForBranchDto shipmentForBranchDto : shipmentForBranchDtoList) {
            shipmentForBranchDto.setIdShipment(shipmentResponse.getIdShipment());
            if (shipmentForBranchDtoList.indexOf(shipmentForBranchDto) == 0)
                shipmentForBranchDto.setIdShipmentState(shipmentStateGateway.getDefaultShipmentState(Constant.IN_TRANSIT_OUT));
            else
                shipmentForBranchDto.setIdShipmentState(shipmentStateGateway.getDefaultShipmentState(Constant.IN_TRANSIT_IN));
            // now we add quantity to the flights
            etFlightGateway.updateQuantity(shipmentForBranchDto.getFlightFriendlyId());
        }
        shipmentForBranchGateway.persist(shipmentForBranchDtoList);

        // now we save all the packages of the shipment
        for (PackageDto packageDto : packageDtoList) {
            packageDto.setIdShipment(shipmentResponse.getIdShipment());
        }
        packageGateway.persist(packageDtoList);
        //finally if all is OK we return coreDto
        return new ShipmentCoreDto().setReferenceCode(shipmentResponse.getReferenceCode());
    }

    public HashMap<String, PersonDto> persistAndGetPeopleInformation(PersonDto customerPersonDto, PersonDto receiverPersonDto) {
        HashMap<String, PersonDto> peopleProcessed = new HashMap<>();
        PersonDto customerPersonResponse;
        PersonDto receiverPersonResponse;

        if (!personGateway.existInDataBase(customerPersonDto)) {
            customerPersonResponse = personGateway.persist(customerPersonDto);
        } else {
            customerPersonResponse = personGateway.getPersonInformation(customerPersonDto);
        }
        if (!personGateway.existInDataBase(receiverPersonDto)) {
            receiverPersonResponse = personGateway.persist(receiverPersonDto);
        } else {
            receiverPersonResponse = personGateway.getPersonInformation(receiverPersonDto);
        }

        peopleProcessed.put(Constant.CUSTOMER_PERSON_FRIENDLY_ID, customerPersonResponse);
        peopleProcessed.put(Constant.RECEIVER_PERSON_FRIENDLY_ID, receiverPersonResponse);
        return peopleProcessed;
    }

    public List<ShipmentForPersonDto> getShipmentForPersonInformation(HashMap<String, PersonDto> peopleProcessed, ShipmentDto shipmentResponse) {
        List<ShipmentForPersonDto> shipmentForBranchDtoList = new ArrayList<>();
        peopleProcessed.forEach((friendlyId, personDto) -> {
            ShipmentForPersonDto shipmentForPersonDto = new ShipmentForPersonDto()
                    .setIdShipment(shipmentResponse.getIdShipment())
                    .setIdPerson(personDto.getIdPerson())
                    .setIdPersonType(personTypeGateway.findIdByFriendlyId(friendlyId));
            shipmentForBranchDtoList.add(shipmentForPersonDto);
        });
        return shipmentForBranchDtoList;
    }
}
