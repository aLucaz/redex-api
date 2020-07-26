package com.application.data.parser;

import com.application.core.model.business.DocumentType;
import com.application.core.model.business.Person;
import com.application.core.model.dto.PersonDto;
import com.application.core.model.dto.SearchShipmentDto;
import com.application.rest.api.request.registerShipment.RegisterShipmentRequest;

import java.time.LocalDateTime;

public class PersonParser {
    public static PersonDto mapToDtoCustomer(RegisterShipmentRequest request) {
        return new PersonDto()
                .setFirstName(request.getCustomerFirstName())
                .setLastName(request.getCustomerLastName())
                .setDocumentType(request.getCustomerDocumentType())
                .setDocumentId(request.getCustomerDocument())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy())
                .setPhone(request.getCustomerPhone());
    }

    public static PersonDto mapToDtoReceiver(RegisterShipmentRequest request) {
        return new PersonDto()
                .setFirstName(request.getReceiverFirstName())
                .setLastName(request.getReceiverLastName())
                .setDocumentType(request.getReceiverDocumentType())
                .setDocumentId(request.getReceiverDocument())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy())
                .setPhone(request.getReceiverPhone());
    }

    public static Person mapToRow(PersonDto personDto) {
        return new Person()
                .setFirstName(personDto.getFirstName())
                .setLastName(personDto.getLastName())
                .setDocumentType(new DocumentType().setIdDocumentType(personDto.getDocumentType()))
                .setDocumentId(personDto.getDocumentId())
                .setPhone(personDto.getPhone())
                .setLastModifiedBy(personDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(personDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now());
    }

    public static PersonDto mapToDto(Person person) {
        return new PersonDto()
                .setIdPerson(person.getIdPerson())
                .setFirstName(person.getFirstName())
                .setLastName(person.getLastName())
                .setDocumentType(person.getDocumentType().getIdDocumentType())
                .setDocumentId(person.getDocumentId())
                .setPhone(person.getPhone());
    }
}
