package com.application.data.parser;

import com.application.core.model.business.Person;
import com.application.core.model.business.PersonType;
import com.application.core.model.business.Shipment;
import com.application.core.model.business.ShipmentForPerson;
import com.application.core.model.dto.ShipmentForPersonDto;

import java.util.ArrayList;
import java.util.List;

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
}
