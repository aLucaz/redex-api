
package com.application.rest.api.controller;


import com.application.core.model.dto.*;
import com.application.core.usecase.shipment.*;
import com.application.data.parser.*;
import com.application.rest.*;
import com.application.rest.api.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.*;


@RestController
@RequestMapping("/api-shipmentForBranch")
@RequiredArgsConstructor
public class ShipmentForBranchController {
    private final UpdateShipmentStateUseCase updateShipmentStateUseCase;
    private final UpdateShipmentForBranchUseCase updateShipmentForBranchUseCase ;

    @PostMapping("/update-shipmentState")
    public ResponseEntity<Object> updateShipmentState(
            @RequestBody List<@Valid UpdateShipmentStateRequest> requestList) {

        updateShipmentStateUseCase.execute(ShipmentForBranchParser.mapToDto(requestList));
        return new ApiResponse<>().ok();
    }
    @PostMapping("/update-shipmentForBranch-state")
    public ResponseEntity<Object> updateShipmentForBranchState(
            @RequestBody List<@Valid UpdateShipmentForBranchStateRequest> requestList) {

        updateShipmentForBranchUseCase.execute(ShipmentForBranchParser.mapToSFBDto(requestList));
        return new ApiResponse<>().ok();
    }

}
