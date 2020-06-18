package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.EmployeeGateway;
import com.application.data.gateway.UserGateway;
import com.application.data.parser.UserParser;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetUserListUseCase {
    // dependency injection
    public final UserGateway userGateway;
    public final EmployeeGateway employeeGateway;

    @SneakyThrows
    public List<UserDto> execute() {

        return UserParser.mapToDto(employeeGateway.findAll());
    }

}

