package com.application.rest.api.controller;

import com.application.core.model.dto.DocumentTypeDto;
import com.application.core.usecase.documentType.GetDocumentTypeListUseCase;
import com.application.rest.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api-document-type")
@RequiredArgsConstructor
public class DocumentTypeController {
    private final GetDocumentTypeListUseCase getDocumentTypeList;

    @GetMapping("/list-document-type")
    public ResponseEntity<Object> getDocumentTypeList(){
        List<DocumentTypeDto> documentTypeDtoList = getDocumentTypeList.execute();
        return new ApiResponse<>().ok(documentTypeDtoList);
    }
}
