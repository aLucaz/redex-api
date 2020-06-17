package com.application.core.usecase.role;

import com.application.core.model.dto.RoleDto;
import com.application.data.gateway.RoleGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetRoleListUseCase {
    private final RoleGateway roleGateway;

    public List<RoleDto> execute(){
        return roleGateway.findAll();
    }
}
