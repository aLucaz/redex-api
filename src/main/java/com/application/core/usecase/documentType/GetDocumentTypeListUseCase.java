package com.application.core.usecase.documentType;

import com.application.core.model.dto.DocumentTypeDto;
import com.application.data.gateway.DocumentTypeGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetDocumentTypeListUseCase {
    private final DocumentTypeGateway documentTypeGateway;

    public List<DocumentTypeDto> execute(){
        return documentTypeGateway.findAll();
    }
}
