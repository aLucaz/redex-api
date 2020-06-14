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
public class EtFlightDto {
    // for the database
    private Integer idEdFlight;
    private String friendlyId;
    private String departurePoint;
    private String arrivalPoint;
    private String departureTime;
    private String arrivalTime;
    private String etFlightDate;
    private Integer capacity;
    private Integer quantity;
    private Integer isActive;
}
