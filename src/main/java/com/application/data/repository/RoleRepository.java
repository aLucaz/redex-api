package com.application.data.repository;

import com.application.core.model.business.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByIdRole(Integer idRole);
}
