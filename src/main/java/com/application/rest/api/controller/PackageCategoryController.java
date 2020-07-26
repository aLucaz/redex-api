package com.application.rest.api.controller;

import com.application.core.model.dto.PackageCategoryDto;
import com.application.core.usecase.packageCategory.GetPackageCategoryListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api-packageCategory")
@RequiredArgsConstructor
public class PackageCategoryController {

    private final GetPackageCategoryListUseCase getPackageCategoryListUseCase;

    @GetMapping("/list")
    public ResponseEntity<Object> getPackageCategoryList() {
        // call use case
        List<PackageCategoryDto> packagingCategoryDto = getPackageCategoryListUseCase.execute();
        // return ok response
        return new ApiResponse<>().ok(packagingCategoryDto);
    }
}
