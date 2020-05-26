package com.application.rest.api.controller;

import com.application.core.model.dto.UserDto;
import com.application.core.usecase.user.RegisterUserUseCase;
import com.application.data.parser.UserParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserUseCase registerUser;

    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        // call use case
        UserDto userDto = registerUser.execute(UserParser.mapToDto(request));
        // return ok response
        return new ApiResponse<>().ok(userDto);
    }
}
