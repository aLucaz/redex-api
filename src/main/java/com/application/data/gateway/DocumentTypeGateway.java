package com.application.data.gateway;

import com.application.core.model.business.DocumentType;
import com.application.core.model.dto.DocumentTypeDto;
import com.application.data.parser.DocumentTypeParser;
import com.application.data.repository.DocumentTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentTypeGateway {
    private final DocumentTypeRepository repository;

    public DocumentTypeGateway(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    public List<DocumentTypeDto> findAll(){
        List<DocumentType> documentTypeList = (List<DocumentType>) repository.findAll();
        return DocumentTypeParser.mapToDtoList(documentTypeList);
    }
}
