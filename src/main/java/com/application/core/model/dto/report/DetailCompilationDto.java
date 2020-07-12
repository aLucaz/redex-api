package com.application.core.model.dto.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DetailCompilationDto {
    private Integer idBranch;
    private String nameBranch;
    private Integer capacity;
    private Integer quantityOfIncidents;
    private Integer quantityOfShipments;
    private List<DetailDayDto> daysDetail;
}
