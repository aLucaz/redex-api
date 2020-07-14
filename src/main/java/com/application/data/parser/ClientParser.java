package com.application.data.parser;

import com.application.core.model.dto.SearchShipmentDto;
import com.application.rest.api.request.SearchShipmentRequest;

public class ClientParser {
    public static SearchShipmentDto mapToDto(SearchShipmentRequest request){
        return new SearchShipmentDto()
                .setReferenceCode(request.getReferenceCode());
    }
}
