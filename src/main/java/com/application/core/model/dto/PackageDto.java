package com.application.core.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PackageDto {
    private Integer idPackage;
    private Integer idShipment;
    private Integer idPackageCategory;
    private Integer idPackagingType;
    private Float weight;
    private Float dimensionX;
    private Float dimensionY;
    private Float dimensionZ;
    private String description;
    private Boolean isDangerous;
}
