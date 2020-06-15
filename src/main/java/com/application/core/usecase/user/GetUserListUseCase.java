package com.application.core.usecase.user;

import com.application.core.model.dto.UserDto;
import com.application.data.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetUserListUseCase {
    // dependency injection
    public final UserGateway userGateway;

    @SneakyThrows
    public List<UserDto> execute() {
        // call the gateway to the database
        return userGateway.getUserList();
    }

}

