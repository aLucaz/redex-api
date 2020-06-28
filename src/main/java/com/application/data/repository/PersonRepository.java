package com.application.data.repository;

import com.application.core.model.business.*;
import org.springframework.data.repository.*;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    //Person findByDocumentTypeAndDocumentId(Integer idDocumentType,String documentId);
    Person findByDocumentId(String documentId);
}
