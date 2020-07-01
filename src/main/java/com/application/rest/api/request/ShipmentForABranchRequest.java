package com.application.rest.api.request;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import lombok.experimental.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShipmentForABranchRequest {
    @NotNull
    private Integer idBranch;
}
