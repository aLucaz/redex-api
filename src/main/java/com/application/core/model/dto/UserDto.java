package com.application.core.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class UserDto {
    private Integer idUser;
    private Integer idRole;
    private String email;
    private Integer validEmail;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private String registeredBy;
    private LocalDateTime registeredDate;
}
