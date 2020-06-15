package com.application.rest.api.controller;

import com.application.core.model.dto.UserDto;
import com.application.core.usecase.user.GetUserListUseCase;
import com.application.core.usecase.user.RegisterUserUseCase;
import com.application.data.parser.UserParser;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.RegisterUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-user")
@RequiredArgsConstructor
public class UserController {
    // dependency injection
    private final RegisterUserUseCase registerUser;
    private final GetUserListUseCase getUserList;

    @PostMapping("/register-user")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        // call use case
        UserDto userDto = registerUser.execute(UserParser.mapToDto(request));
        // return ok response
        return new ApiResponse<>().ok(userDto);
    }

    @GetMapping("/list-user")
    public ResponseEntity<Object> getUserList() {
        // call use case
        List<UserDto> userDtoList = getUserList.execute();
        // return ok response
        return new ApiResponse<>().ok(userDtoList);
    }
}
