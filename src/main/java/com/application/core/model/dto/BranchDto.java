package com.application.core.model.dto;

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
public class BranchDto {
    private Integer idBranch;
    private String name;
    private String friendlyId;
    private Integer capacity;
    private Integer quantity;
    private Integer inTransitOut;
    private Integer inTransitIn;
    private Integer toDeliver;
    private String continent;
    private String latitude;
    private String longitude;
    private Integer isActive;
    List<ShipmentForBranchDto> shipments;
}
