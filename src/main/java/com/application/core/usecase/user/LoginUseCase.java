package com.application.core.usecase.user;


import com.application.core.model.business.Employee;
import com.application.core.model.business.User;
import com.application.core.model.dto.UserDto;
import com.application.data.gateway.EmployeeGateway;
import com.application.data.gateway.UserGateway;
import com.application.data.parser.UserParser;
import com.application.data.util.hashing.PasswordEncoderImpl;
import com.application.data.util.hashing.PasswordEncoder;
import com.application.shared.Constant;
import com.application.shared.exception.custom.EntityNotFoundException;
import com.application.shared.exception.custom.IncorrectPasswordException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class LoginUseCase {
    // dependency injection
    public final UserGateway userGateway;
    public final EmployeeGateway employeeGateway;

    public LoginUseCase(UserGateway userGateway, EmployeeGateway employeeGateway) {
        this.employeeGateway = employeeGateway;
        this.userGateway = userGateway;
    }

    @SneakyThrows
    public UserDto execute(UserDto userDto) {
        PasswordEncoder encoder = new PasswordEncoderImpl();
        // call the gateway to the database
        if (!userGateway.existInDataBase(userDto.getEmail()))
            throw new EntityNotFoundException(UserDto.class, "email", userDto.getEmail());

        User user = userGateway.getUserByEmail(userDto.getEmail());
        Hashtable<String, String> hashtable = encoder.hash(userDto.getPassword(), user.getPasswordSalt());

        if (!user.getPasswordHash().equals(hashtable.get("passwordHash")))
            throw new IncorrectPasswordException(Constant.INCORRECT_PASSWORD_MSG);

        Employee employee = employeeGateway.findByUser(user.getIdUser());
        return UserParser.mapToDto(employee);
    }
}
