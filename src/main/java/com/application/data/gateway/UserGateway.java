package com.application.data.gateway;

import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.data.parser.UserParser;
import com.application.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserGateway {
    private final UserRepository repository;

    public UserDto persist(UserDto userDto){
        //map the db instance
        User user = UserParser.mapToRow(userDto);
        //save using repository
        return UserParser.mapToDto(repository.save(user));
    }

    public boolean existInDataBase(String email){
        return repository.findByEmail(email) != null;
    }
}
