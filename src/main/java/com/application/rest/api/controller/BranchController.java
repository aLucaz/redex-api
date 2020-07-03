package com.application.rest.api.controller;

import com.application.core.model.dto.BranchDto;
import com.application.core.usecase.branch.UpdateBranchCapacityUseCase;
import com.application.core.usecase.branch.GetBranchListUseCase;
import com.application.core.usecase.shipment.GetShipmentForABranchUseCase;
import com.application.rest.ApiResponse;
import com.application.rest.api.request.ShipmentForABranchRequest;
import com.application.rest.api.request.UpdateBranchCapacityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.List;

@RestController
@RequestMapping("/api-branch")
@RequiredArgsConstructor
public class BranchController {
    private final GetBranchListUseCase getBranchList;
    private final GetShipmentForABranchUseCase getShipmentForABranchUseCase;
    private final UpdateBranchCapacityUseCase updateBranchCapacityUseCase;

    @GetMapping("/list-branch")
    public ResponseEntity<Object> getBranchList() {
        List<BranchDto> branchDtoList = getBranchList.execute();
        return new ApiResponse<>().ok(branchDtoList);
    }

    @PostMapping("/shipments")
    public ResponseEntity<Object> getShipmentShipmentForBranchList(
            @Valid @RequestBody ShipmentForABranchRequest request) {
        BranchDto branchDto =
                getShipmentForABranchUseCase.execute(request.getIdBranch());
        return new ApiResponse<>().ok(branchDto);
    }
    @PostMapping("/update-capacity")
    public ResponseEntity<Object> updateCapacity(
            @Valid @RequestBody UpdateBranchCapacityRequest request) {
        updateBranchCapacityUseCase.execute(request.getIdBranch(),request.getCapacity());
        return new ApiResponse<>().ok();
    }
}
