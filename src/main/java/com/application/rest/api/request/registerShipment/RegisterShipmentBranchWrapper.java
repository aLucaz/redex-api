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
    private LocalDateTime currentDepartureDateTime;
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime currentArrivalDateTime;
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime futureArrivalDateTime;
    @NotNull
    private Integer startCityId;
    @NotNull
    private Integer endCityId;
    @NotNull
    private String flightFriendlyId;
    @NotNull
    private Integer sequence;
    @NotNull
    private String waitingTime;
    @NotNull
    private String transportTime;
    @NotNull
    private String totalTime;
}
