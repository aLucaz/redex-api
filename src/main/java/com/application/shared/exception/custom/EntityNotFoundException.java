package com.application.shared.exception.custom;

import com.application.shared.exception.resource.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> currentClass, Object... searchParams) {
        super(EntityNotFoundException.generateMessage(currentClass.getSimpleName(),
                Converter.toMap(String.class, String.class, searchParams)));
    }

    @SneakyThrows
    public static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " was not found for the parameters: " + new ObjectMapper().writeValueAsString(searchParams);
    }
}
