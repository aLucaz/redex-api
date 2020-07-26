package com.application.data.gateway;

import com.application.data.repository.PersonTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersonTypeGateway {
    private final PersonTypeRepository repository;

    public PersonTypeGateway(PersonTypeRepository repository) {
        this.repository = repository;
    }

    public Integer findIdByFriendlyId(String friendlyId){
        return repository.findByFriendlyId(friendlyId).getIdPersonType();
    }

}
