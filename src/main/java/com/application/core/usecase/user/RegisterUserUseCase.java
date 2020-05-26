package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.UserGateway;
import com.application.exception.custom.EntityDuplicatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterUserUseCase {

    public final UserGateway userGateway;

    public UserDto execute(UserDto userDto){
        if (userGateway.existInDataBase(userDto.getEmail()))
            throw new EntityDuplicatedException(UserDto.class, "email", userDto.getEmail());
        // call the gateway for the database
        return userGateway.persist(userDto);
    }

}
