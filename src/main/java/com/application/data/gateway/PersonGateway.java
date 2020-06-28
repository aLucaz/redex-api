package com.application.data.gateway;

import com.application.core.model.business.*;
import com.application.core.model.dto.*;
import com.application.data.parser.*;
import com.application.data.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

@Repository
public class PersonGateway {

    private final PersonRepository repository;
    public PersonGateway(PersonRepository repository) {
        this.repository = repository;
    }

    public Person findByDocument(Integer documentTypeId, String documentId) {

        //NOTA: Da error al buscar por el tipo de documento. Corregir
        //return repository.findByDocumentTypeAndDocumentId(documentTypeId,documentId);
        return repository.findByDocumentId(documentId);
    }

    @SneakyThrows
    public Person persist(PersonDto personDto) {
        Person person = PersonParser.mapToRow(personDto);
        return repository.save(person);
    }
}
