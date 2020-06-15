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
                .setDocument(user.getDocument())
                .setDocumentType(user.getDocumentType())
                .setBirthday(user.getBirthday())
                .setGenre(user.getGenre())
                .setUsername(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setIsActive(user.getIsActive())
                .setMaidenName(user.getMaidenName())
                .setLastModifiedBy(user.getLastModifiedBy())
                .setLastModifiedDate(user.getLastModifiedDate())
                .setRegisteredBy(user.getRegisteredBy())
                .setRegisteredDate(user.getRegisteredDate());
    }

    public static User mapToRow(UserDto userDto){
        return new User()
                .setRole(new Role().setIdRole(userDto.getIdRole()))
                .setEmail(userDto.getEmail())
                .setDocument(userDto.getDocument())
                .setDocumentType(userDto.getDocumentType())
                .setBirthday(userDto.getBirthday())
                .setGenre(userDto.getGenre())
                .setUsername(userDto.getUsername())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setIsActive(userDto.getIsActive())
                .setMaidenName(userDto.getMaidenName())
                .setLastModifiedBy(userDto.getLastModifiedBy())
                .setLastModifiedDate(userDto.getLastModifiedDate())
                .setRegisteredBy(userDto.getRegisteredBy())
                .setRegisteredDate(userDto.getRegisteredDate());
    }

    public static UserDto mapToDto(RegisterUserRequest request){
        return new UserDto()
                .setIdRole(request.getIdRole())
                .setEmail(request.getEmail())
                .setDocument(request.getDocument())
                .setDocumentType(request.getDocumentType())
                .setBirthday(request.getBirthday())
                .setGenre(request.getGenre())
                .setUsername(request.getUsername())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setIsActive(request.getIsActive())
                .setMaidenName(request.getMaidenName())
                .setPassword(request.getPassword())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setLastModifiedDate(request.getLastModifiedDate())
                .setRegisteredBy(request.getRegisteredBy())
                .setRegisteredDate(request.getRegisteredDate());
    }
}
