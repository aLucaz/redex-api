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
public class GetOntimeReportRequest {
    @NotNull
    @JsonFormat(pattern = Constant.DATE_FORMAT)
    private LocalDate startDate;
    @NotNull
    @JsonFormat(pattern = Constant.DATE_FORMAT)
    private LocalDate endDate;
    @NotNull
    private Boolean ofSimulated;
}
