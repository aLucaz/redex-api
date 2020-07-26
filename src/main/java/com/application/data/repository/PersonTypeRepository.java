package com.application.data.repository;

import com.application.core.model.business.PersonType;
import org.springframework.data.repository.CrudRepository;

public interface PersonTypeRepository extends CrudRepository<PersonType, Integer> {
    PersonType findByFriendlyId(String friendlyId);
}
