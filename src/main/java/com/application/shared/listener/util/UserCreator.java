package com.application.shared.listener.util;

import com.application.core.model.dto.UserDto;

public interface UserCreator {
    UserDto createUser(String email, String firstName, String lastName, Integer role);
    UserDto createUser(String email, String firstName, String lastName, Integer role, Integer branch);
}
