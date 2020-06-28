package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.EmployeeGateway;
import com.application.data.gateway.UserGateway;
import com.application.data.parser.EmployeeParser;
import com.application.shared.exception.custom.EntityDuplicatedException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {
    // dependency injection
    public final UserGateway userGateway;
    public final EmployeeGateway employeeGateway;

    public RegisterUserUseCase(UserGateway userGateway, EmployeeGateway employeeGateway) {
        this.userGateway = userGateway;
        this.employeeGateway = employeeGateway;
    }

    @SneakyThrows
    public UserDto execute(UserDto userDto, boolean isEventListener) {
        if (userGateway.existInDataBase(userDto.getEmail()))
            if (isEventListener)
                return null;
            else
                throw new EntityDuplicatedException(UserDto.class, "email", userDto.getEmail());
        // call the gateway to the database
        UserDto userDtoResponse = userGateway.persist(userDto).setIdBranch(userDto.getIdBranch());
        employeeGateway.persist(EmployeeParser.mapToDto(userDtoResponse));
        return userDtoResponse;
    }

}
