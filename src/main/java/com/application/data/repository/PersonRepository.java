package com.application.data.repository;

import com.application.core.model.business.DocumentType;
import com.application.core.model.business.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    //Person findByDocumentTypeAndDocumentId(Integer idDocumentType,String documentId);
    Person findByDocumentIdAndDocumentType(String documentId, DocumentType documentType);
    Person findByDocumentId(String documentId);
    Person findByIdPerson(Integer idPerson);
}
