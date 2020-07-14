package com.application.data.parser;

import com.application.core.model.business.Person;
import com.application.core.model.business.PersonType;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForBranch;
import com.application.core.model.business.ShipmentForPerson;
import com.application.core.model.dto.ShipmentForBranchDto;
import com.application.core.model.dto.ShipmentForPersonDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShipmentForPersonParser {

    public static ShipmentForPerson mapToRow(ShipmentForPersonDto shipmentForPersonDto) {
        return new ShipmentForPerson()
                .setShipment(new Shipment().setIdShipment(shipmentForPersonDto.getIdShipment()))
                .setPerson(new Person().setIdPerson(shipmentForPersonDto.getIdPerson()))
                .setPersonType(new PersonType().setIdPersonType(shipmentForPersonDto.getIdPersonType()));
    }

    public static List<ShipmentForPerson> mapToRowList(List<ShipmentForPersonDto> shipmentForPersonDtoList) {
        List<ShipmentForPerson> shipmentForPersonList = new ArrayList<>();
        for (ShipmentForPersonDto shipmentForPersonDto : shipmentForPersonDtoList) {
            ShipmentForPerson shipmentForPerson = mapToRow(shipmentForPersonDto);
            shipmentForPersonList.add(shipmentForPerson);
        }
        return shipmentForPersonList;
    }

    public static ShipmentForPersonDto mapToDto(ShipmentForPerson shipmentForPerson){
        return new ShipmentForPersonDto()
                .setIdPerson(shipmentForPerson.getPerson().getIdPerson())
                .setIdPersonType(shipmentForPerson.getPersonType().getIdPersonType())
                .setPersonTypeName(shipmentForPerson.getPersonType().getName())
                .setIdShipmentForPerson(shipmentForPerson.getId())
                .setIdShipment(shipmentForPerson.getShipment().getIdShipment());
    }

    public static Set<ShipmentForPersonDto> mapToDtoSet(Set<ShipmentForPerson> shipmentForPersonSet) {
        // its possible to receive null Here
        if (shipmentForPersonSet == null)
            return null;
        // continue with normal process
        Set<ShipmentForPersonDto> shipmentForPersonDtoSet = new HashSet<>();
        for (ShipmentForPerson shipmentForPerson : shipmentForPersonSet) {
            shipmentForPersonDtoSet.add(mapToDto(shipmentForPerson));
        }
        return shipmentForPersonDtoSet;
    }
}
