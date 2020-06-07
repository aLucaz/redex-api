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
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private Integer idRole;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String email;
    private String validEmail;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String username;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String firstName;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String lastName;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String password;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String lastModifiedBy;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime lastModifiedDate;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    private String registeredBy;
    @NotNull(message = Constant.NOT_EMPTY_MESSAGE)
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime registeredDate;
}
