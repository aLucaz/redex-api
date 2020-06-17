package com.application.rest.api.controller;

import com.application.core.model.dto.BranchDto;
import com.application.core.model.dto.UserDto;
import com.application.core.usecase.branch.GetBranchListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-branch")
@RequiredArgsConstructor
public class BranchController {
    private final GetBranchListUseCase getBranchList;

    @GetMapping("/list-branch")
    public ResponseEntity<Object> getBranchList(){
        List<BranchDto> branchDtoList = getBranchList.execute();
        return new ApiResponse<>().ok(branchDtoList);
    }
}
