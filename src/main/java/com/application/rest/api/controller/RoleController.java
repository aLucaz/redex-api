package com.application.rest.api.controller;

import com.application.core.model.dto.RoleDto;
import com.application.core.usecase.role.GetRoleListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-role")
@RequiredArgsConstructor
public class RoleController {
    private final GetRoleListUseCase getRoleList;

    @GetMapping("/list-role")
    public ResponseEntity<Object> getRoleList() {
        List<RoleDto> roleDtoList = getRoleList.execute();
        return new ApiResponse<>().ok(roleDtoList);
    }
}
