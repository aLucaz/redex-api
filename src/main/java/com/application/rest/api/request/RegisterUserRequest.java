package com.application.rest.api.request;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterUserRequest {
    @NotNull
    private Integer idRole;
    @NotNull
    private Integer idBranch;
    @NotNull
    private Integer idDocumentType;
    @NotNull
    private String email;
    @NotNull
    @JsonFormat(pattern = Constant.DATE_FORMAT)
    private LocalDate birthday;
    @NotNull
    private String genre;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Boolean isActive;
    @NotNull
    private String documentId;
    @NotNull
    private String phone;
    @NotNull
    private String password;
    @NotNull
    private String lastModifiedBy;
    @NotNull
    private String registeredBy;
}
