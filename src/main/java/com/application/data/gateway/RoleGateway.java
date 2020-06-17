package com.application.data.gateway;

import com.application.core.model.business.Role;
import com.application.core.model.dto.RoleDto;
import com.application.data.parser.RoleParser;
import com.application.data.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleGateway {
    private final RoleRepository repository;

    public RoleGateway(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findByIdRole(Integer idRole){
        return repository.findByIdRole(idRole);
    }

    public List<RoleDto> findAll(){
        List<Role> roleList = (List<Role>) repository.findAll();
        return RoleParser.mapToDtoList(roleList);
    }
}
