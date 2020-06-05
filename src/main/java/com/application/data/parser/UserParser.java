package com.application.data.parser;

import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.rest.api.request.RegisterUserRequest;


public class UserParser {
    public static UserDto mapToDto(User user){
        return new UserDto()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName());
    }

    public static User mapToRow(UserDto userDto){
        return new User()
                .setEmail(userDto.getEmail())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setPassword((userDto.getPassword()));
    }

    public static UserDto mapToDto(RegisterUserRequest request){
        return new UserDto()
                .setLastName(request.getLastName())
                .setFirstName(request.getFirstName())
                .setEmail(request.getEmail())
                .setPassword(request.getPassword());
    }
}
