package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.UserGateway;
import com.application.shared.exception.custom.EntityDuplicatedException;
import com.application.shared.Constant;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserUseCase {
    // dependency injection
    public final UserGateway userGateway;

    @SneakyThrows
    public UserDto execute(UserDto userDto){
        if (userGateway.existInDataBase(userDto.getEmail()))
            throw new EntityDuplicatedException(UserDto.class, "email", userDto.getEmail());
        // email is valid by default
        userDto.setValidEmail(Constant.DEFAULT_VALID_EMAIL);
        // call the gateway to the database
        return userGateway.persist(userDto);
    }

}
