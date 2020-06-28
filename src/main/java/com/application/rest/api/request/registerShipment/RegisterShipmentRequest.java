package com.application.rest.api.request.registerShipment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterShipmentRequest {
    @NotNull
    private List<RegisterShipmentPackageWrapper> packages;
    @NotNull
    private List<RegisterShipmentBranchWrapper> tripPlan;
    @NotNull
    private Float price;
    @NotNull
    private String lastModifiedBy;
    @NotNull
    private String registeredBy;
    @NotNull
    private String customerFirstName;
    @NotNull
    private String customerLastName;
    @NotNull
    private Integer customerDocumentType;
    @NotNull
    private String customerDocument;
    @NotNull
    private String customerPhone;
    @NotNull
    private String receiverFirstName;
    @NotNull
    private String receiverLastName;
    @NotNull
    private Integer receiverDocumentType;
    @NotNull
    private String receiverDocument;
    @NotNull
    private String receiverPhone;

}


