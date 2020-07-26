package com.application.data.gateway;

import com.application.core.model.business.Person;
import com.application.core.model.dto.PersonDto;
import com.application.data.parser.PersonParser;
import com.application.data.repository.PersonRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonGateway {

    private final PersonRepository repository;

    public PersonGateway(PersonRepository repository) {
        this.repository = repository;
    }

    public Boolean existInDataBase(PersonDto personDto) {
        Person person = PersonParser.mapToRow(personDto);
        return repository.findByDocumentIdAndDocumentType(person.getDocumentId(), person.getDocumentType()) != null;
    }

    public PersonDto getPersonInformation(PersonDto personDto) {
        Person person = PersonParser.mapToRow(personDto);
        return PersonParser.mapToDto(repository.findByDocumentIdAndDocumentType(person.getDocumentId(), person.getDocumentType()));
    }

    public PersonDto persist(PersonDto personDto) {
        Person person = PersonParser.mapToRow(personDto);
        return PersonParser.mapToDto(repository.save(person));
    }

    public PersonDto findById(Integer idPerson){
        return PersonParser.mapToDto(repository.findByIdPerson(idPerson));
    }
}
