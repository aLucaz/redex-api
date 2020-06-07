package com.application.rest.api.request;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserRequest {
    @NotNull
    private Integer idRole;
    @NotNull
    private String email;
    private String validEmail;
    @NotNull
    private String username;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String password;
    @NotNull
    private String lastModifiedBy;
    @NotNull
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime lastModifiedDate;
    @NotNull
    private String registeredBy;
    @NotNull
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime registeredDate;
}
