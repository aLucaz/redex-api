package com.application.data.parser;

import com.application.core.model.business.Role;
import com.application.core.model.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

public class RoleParser {
    public static RoleDto mapToDto(Role role){
        return new RoleDto()
                .setIdRole(role.getIdRole())
                .setName(role.getName());
    }

    public static List<RoleDto> mapToDtoList(List<Role> roleList){
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role: roleList){
            roleDtoList.add(mapToDto(role));
        }
        return roleDtoList;
    }
}
