package com.application.core.model.dto;

import com.application.shared.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class IncidentDto {
    private Integer idIncident;
    private String flightFriendlyId;
    private String branchFriendlyId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime incidentDateTime;
    private Boolean isSimulated;
    private Boolean isActive;
    private String incidentType;
}
