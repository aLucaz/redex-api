package com.application.rest.api.request.registerShipment;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterShipmentRequest {

    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime shippingDate;
    @NotNull
    private Integer origin;
    @JsonUnwrapped
    private List<RegisterShipmentPackageWrapper> packages;
    @NotNull
    private Integer destination;
    @NotNull
    private Float price;
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime arrivalDate;
    @NotNull
    private Integer scaleNumbers;
    @NotNull
    private List<RegisterShipmentBranchWrapper> tripPlan;
    @NotNull
    private String lastModifiedBy;
    @NotNull
    private String registeredBy;




}


