package com.application.data.parser;

import com.application.core.model.business.DocumentType;
import com.application.core.model.business.Employee;
import com.application.core.model.business.Role;
import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.rest.api.request.LoginRequest;
import com.application.rest.api.request.RegisterUserRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class UserParser {
    public static UserDto mapToDto(User user) {
        return new UserDto()
                .setIdUser(user.getIdUser())
                .setIdRole(user.getRole().getIdRole())
                .setRoleName(user.getRole().getName())
                .setIdDocumentType(user.getDocumentType().getIdDocumentType())
                .setEmail(user.getEmail())
                .setBirthday(user.getBirthday())
                .setGenre(user.getGenre())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setIsActive(user.getIsActive())
                .setDocumentId(user.getDocumentId())
                .setPhone(user.getPhone())
                .setLastModifiedBy(user.getLastModifiedBy())
                .setLastModifiedDate(user.getLastModifiedDate())
                .setRegisteredBy(user.getRegisteredBy())
                .setRegisteredDate(user.getRegisteredDate());
    }

    public static User mapToRow(UserDto userDto) {
        return new User()
                .setRole(new Role().setIdRole(userDto.getIdRole()))
                .setDocumentType(new DocumentType().setIdDocumentType(userDto.getIdDocumentType()))
                .setEmail(userDto.getEmail())
                .setBirthday(userDto.getBirthday())
                .setGenre(userDto.getGenre())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setIsActive(userDto.getIsActive())
                .setDocumentId(userDto.getDocumentId())
                .setPhone(userDto.getPhone())
                .setLastModifiedBy(userDto.getLastModifiedBy())
                .setLastModifiedDate(LocalDateTime.now())
                .setRegisteredBy(userDto.getRegisteredBy())
                .setRegisteredDate(LocalDateTime.now());
    }

    public static UserDto mapToDto(RegisterUserRequest request) {
        return new UserDto()
                .setIdRole(request.getIdRole())
                .setIdBranch(request.getIdBranch())
                .setIdDocumentType(request.getIdDocumentType())
                .setEmail(request.getEmail())
                .setBirthday(request.getBirthday())
                .setGenre(request.getGenre())
                .setFirstName(request.getFirstName())
                .setLastName(request.getLastName())
                .setIsActive(request.getIsActive())
                .setDocumentId(request.getDocumentId())
                .setPhone(request.getPhone())
                .setPassword(request.getPassword())
                .setLastModifiedBy(request.getLastModifiedBy())
                .setRegisteredBy(request.getRegisteredBy());
    }

    public static List<UserDto> mapToDtoList(List<User> userList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            userDtoList.add(mapToDto(user));
        }
        return userDtoList;
    }

    public static UserDto mapToDto(LoginRequest request) {
        return new UserDto()
                .setEmail(request.getEmail())
                .setPassword(request.getPassword());
    }

    public static UserDto mapToDto(Employee employee) {
        return new UserDto()
                .setIdUser(employee.getUser().getIdUser())
                .setIdRole(employee.getUser().getRole().getIdRole())
                .setRoleName(employee.getUser().getRole().getName())
                .setIdDocumentType(employee.getUser().getDocumentType().getIdDocumentType())
                .setIdBranch(employee.getBranch().getIdBranch())
                .setBranchName(employee.getBranch().getName())
                .setEmail(employee.getUser().getEmail())
                .setBirthday(employee.getUser().getBirthday())
                .setGenre(employee.getUser().getGenre())
                .setFirstName(employee.getUser().getFirstName())
                .setLastName(employee.getUser().getLastName())
                .setIsActive(employee.getUser().getIsActive())
                .setDocumentId(employee.getUser().getDocumentId())
                .setPhone(employee.getUser().getPhone())
                .setLastModifiedBy(employee.getUser().getLastModifiedBy())
                .setLastModifiedDate(employee.getUser().getLastModifiedDate())
                .setRegisteredBy(employee.getUser().getRegisteredBy())
                .setRegisteredDate(employee.getUser().getRegisteredDate());
    }

    public static List<UserDto> mapToDto(List<Employee> employeeList) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            userDtoList.add(mapToDto(employee));
        }
        return userDtoList;
    }

    public static UserDto mapToDto(User user, Employee employee) {
        return new UserDto()
                .setIdRole(user.getRole().getIdRole())
                .setIdBranch(employee.getBranch().getIdBranch())
                .setIdDocumentType(user.getDocumentType().getIdDocumentType())
                .setEmail(user.getEmail())
                .setBirthday(user.getBirthday())
                .setGenre(user.getGenre())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setIsActive(user.getIsActive())
                .setDocumentId(user.getDocumentId())
                .setPhone(user.getPhone())
                .setLastModifiedBy(user.getLastModifiedBy())
                .setRegisteredBy(user.getRegisteredBy());
    }
}
