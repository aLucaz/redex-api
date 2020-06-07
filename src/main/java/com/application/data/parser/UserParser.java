package com.application.data.parser;

import com.application.core.model.business.Role;
import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.rest.api.request.RegisterUserRequest;


public class UserParser {
    public static UserDto mapToDto(User user){
        return new UserDto()
                .setIdUser(user.getIdUser())
                .setIdRole(user.getRole().getIdRole())
                .setEmail(user.getEmail())
                .setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

    public static User mapToRow(UserDto userDto){
        return new User()
                .setRole(new Role().setIdRole(userDto.getIdRole()))
                .setEmail(userDto.getEmail())
                .setValidEmail(userDto.getValidEmail())
                .setUsername(userDto.getUsername())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setLastModifiedBy(userDto.getLastModifiedBy())
                .setLastModifiedDate(userDto.getLastModifiedDate())
                .setRegisteredBy(userDto.getRegisteredBy())
                .setRegisteredDate(userDto.getRegisteredDate());
    }

    public static UserDto mapToDto(RegisterUserRequest request){
        return new UserDto()
                .setIdRole(request.getIdRole())
                .setEmail(request.getEmail())
                .setUsername(request.getUsername())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setPassword(request.getPassword())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setLastModifiedDate(request.getLastModifiedDate())
                .setRegisteredBy(request.getRegisteredBy())
                .setRegisteredDate(request.getRegisteredDate());
    }
}
