package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.UserGateway;
import com.application.shared.Constant;
import com.application.shared.exception.custom.EntityDuplicatedException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCase {
    // dependency injection
    public final UserGateway userGateway;

    public RegisterUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @SneakyThrows
    public UserDto execute(UserDto userDto) {
        if (userGateway.existInDataBase(userDto.getEmail()))
            throw new EntityDuplicatedException(UserDto.class, "email", userDto.getEmail());
        // call the gateway to the database
        return userGateway.persist(userDto);
    }

}
