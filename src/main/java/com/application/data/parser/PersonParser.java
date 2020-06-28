package com.application.data.parser;

import com.application.core.model.business.*;
import com.application.core.model.dto.*;
import com.application.rest.api.request.registerShipment.*;

import java.time.*;

public class PersonParser {
    public static PersonDto mapToDtoCustomer(RegisterShipmentRequest request) {
        return new PersonDto()
                .setFirstName(request.getCustomerFirstName())
                .setLastName(request.getCustomerLastName())
                .setDocumentType(request.getCustomerDocumentType())
                .setDocument(request.getCustomerDocument())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy())
                .setPhone(request.getCustomerPhone());
    }
    public static PersonDto mapToDtoReceiver(RegisterShipmentRequest request) {
        return new PersonDto()
                .setFirstName(request.getReceiverFirstName())
                .setLastName(request.getReceiverLastName())
                .setDocumentType(request.getReceiverDocumentType())
                .setDocument(request.getReceiverDocument())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy())
                .setPhone(request.getReceiverPhone());
    }
    public static Person mapToRow(PersonDto personDto) {
        return new Person()
                .setFirstName(personDto.getFirstName())
                .setLastName(personDto.getLastName())
                .setDocumentType(new DocumentType().setIdDocumentType(personDto.getDocumentType()))
                .setDocumentId(personDto.getDocument())
                .setPhone(personDto.getPhone())
                .setLastModifiedBy(personDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(personDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now());
    }
}
