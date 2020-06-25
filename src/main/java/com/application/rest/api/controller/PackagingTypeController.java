package com.application.rest.api.controller;

import com.application.core.model.dto.PackagingTypeDto;
import com.application.core.usecase.packagingType.GetPackagingTypeListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api-packagingType")
@RequiredArgsConstructor
public class PackagingTypeController {
    private final GetPackagingTypeListUseCase getPackagingTypeListUseCase;



    @GetMapping("/list")
    public ResponseEntity<Object> getPackagingTypeList() {
        // call use case
        List<PackagingTypeDto> packagingTypeDto = getPackagingTypeListUseCase.execute();
        // return ok response
        return new ApiResponse<>().ok(packagingTypeDto);
    }


}
