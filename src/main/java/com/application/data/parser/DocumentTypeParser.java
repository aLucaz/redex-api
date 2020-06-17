package com.application.data.parser;

import com.application.core.model.business.DocumentType;
import com.application.core.model.dto.DocumentTypeDto;

import java.util.ArrayList;
import java.util.List;

public class DocumentTypeParser {
    public static DocumentTypeDto mapToDto(DocumentType documentType){
        return new DocumentTypeDto()
                .setIdDocumentType(documentType.getIdDocumentType())
                .setName(documentType.getName());
    }

    public static List<DocumentTypeDto> mapToDtoList(List<DocumentType> documentTypeList){
        List<DocumentTypeDto> documentTypeDtoList = new ArrayList<>();
        for (DocumentType documentType : documentTypeList){
            documentTypeDtoList.add(mapToDto(documentType));
        }
        return documentTypeDtoList;
    }
}
