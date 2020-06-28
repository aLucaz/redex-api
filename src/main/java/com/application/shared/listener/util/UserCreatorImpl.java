package com.application.shared.listener.util;

import com.application.core.model.dto.UserDto;
import com.application.shared.Constant;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserCreatorImpl implements UserCreator {
    @Override
    public UserDto createUser(String email, String firstName, String lastName, Integer role) {
        return new UserDto()
                .setIdRole(role)
                .setIdBranch(1)
                .setIdDocumentType(1)
                .setEmail(email)
                .setBirthday(LocalDate.parse("1990-09-09", Constant.DATE_FORMATTER))
                .setGenre(Constant.FAMALE_GENRE)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setIsActive(Boolean.TRUE)
                .setDocumentId(Constant.DOCUMENT_ID_DEFAULT)
                .setPhone(Constant.PHONE_DEFAULT)
                .setPassword(Constant.PASSWORD_DEFAULT)
                .setLastModifiedBy(Constant.SYSTEM)
                .setRegisteredBy(Constant.SYSTEM);
    }
}
