package com.application.shared.exception.custom;

import com.application.shared.exception.resource.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Map;

public class BranchNotAvailableException extends RuntimeException {

    public BranchNotAvailableException(Class<?> currentClass, Object... params) {
        super(BranchNotAvailableException.generateMessage(currentClass.getSimpleName(),
                Converter.toMap(String.class, String.class, params)));
    }

    @SneakyThrows
    public static String generateMessage(String entity, Map<String, String> params) {
        return StringUtils.capitalize(entity) + "Is not avialable with parameters " + new ObjectMapper().writeValueAsString(params);
    }


}
