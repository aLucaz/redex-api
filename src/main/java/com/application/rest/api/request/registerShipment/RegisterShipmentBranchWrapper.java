package com.application.rest.api.request.registerShipment;

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
public class RegisterShipmentBranchWrapper {
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime arrivalDate;
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime departureDate;
    @NotNull
    private Integer originCity;
    @NotNull
    private Integer destinationCity;
    @NotNull
    private String flightFriendlyId;
}
