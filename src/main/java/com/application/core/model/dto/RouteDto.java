package com.application.core.model.dto;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouteDto {
    //
    private String startPoint;
    private String endPoint;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime requestDateTime;
    private Integer sameContinent;
    //
    private String flightFriendlyId;
    private String transportTime;
    private String waitingTime;
    private String totalTime;
    //
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime departureDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime arrivalDateTime;
    private String startCity;
    private String endCity;
}
