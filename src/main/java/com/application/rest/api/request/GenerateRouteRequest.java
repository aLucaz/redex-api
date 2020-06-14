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
public class GenerateRouteRequest {
    @NotNull
    private String startPoint;
    @NotNull
    private String endPoint;
    @NotNull
    @JsonFormat(pattern = Constant.DATE_TIME_FORMAT)
    private LocalDateTime requestDateTime;
    @NotNull
    private Integer sameContinent;
}
