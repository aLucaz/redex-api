package com.application.data.gateway;

import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.data.parser.UserParser;
import com.application.data.repository.UserRepository;
import com.application.data.util.hashing.PasswordEncoderImpl;
import com.application.data.util.hashing.PasswordEnconder;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.util.Hashtable;
import java.util.List;

@Repository
public class UserGateway {
    private final UserRepository repository;
    private final PasswordEnconder encoder;

    public UserGateway(UserRepository repository, PasswordEncoderImpl encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
 
    @SneakyThrows
    public UserDto persist(UserDto userDto) {
        //map the db instance
        User user = UserParser.mapToRow(userDto);
        //hashing the password from DTO
        Hashtable<String, String> hashtable = encoder.hash(userDto.getPassword());
        user.setPasswordHash(hashtable.get("passwordHash"));
        user.setPasswordSalt(hashtable.get("passwordSalt"));
        //save using repository
        return UserParser.mapToDto(repository.save(user));
    }

    public boolean existInDataBase(String email) {
        return repository.findByEmail(email) != null;
    }

    @SneakyThrows
    public List<UserDto> getUserList() {
        List<User> userList = repository.findAll();
        return UserParser.mapToDtoList(userList);
    }

    @SneakyThrows
    public User getUserByEmail(String email) {
        User user = repository.findByEmail(email);
        return user;
    }



}
