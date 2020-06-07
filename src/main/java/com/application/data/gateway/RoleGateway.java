package com.application.data.gateway;

import com.application.core.model.business.Role;
import com.application.data.repository.RoleRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleGateway {
    private final RoleRepository repository;

    public RoleGateway(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByIdRole(Integer idRole){
        return repository.findByIdRole(idRole);
    }
}
