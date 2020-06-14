package com.application.shared.exception.custom;

import com.application.shared.exception.resource.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityDuplicatedException extends RuntimeException {

    public EntityDuplicatedException(Class<?> currentClass, Object... searchParams) {
        super(EntityDuplicatedException.generateMessage(currentClass.getSimpleName(),
                Converter.toMap(String.class, String.class, searchParams)));
    }

    @SneakyThrows
    public static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " is duplicated for the parameters: " + new ObjectMapper().writeValueAsString(searchParams);
    }
}
