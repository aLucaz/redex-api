package com.application.rest.api.request.registerShipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterShipmentPackageWrapper {
    @NotNull
    private Integer idPackagingType;
    @NotNull
    private Integer idPackageCategory;
    @NotNull
    private Float weight;
    @NotNull
    private Float dimensionX;
    @NotNull
    private Float dimensionY;
    @NotNull
    private Float dimensionZ;
    @NotNull
    private String description;
    @NotNull
    private Boolean isDangerous;
}
