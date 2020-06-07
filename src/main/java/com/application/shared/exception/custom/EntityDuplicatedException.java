package com.application.shared.exception.custom;

import com.application.shared.exception.resource.Converter;
import org.springframework.util.StringUtils;

import java.util.Map;

public class EntityDuplicatedException extends RuntimeException {

    public EntityDuplicatedException(Class<?> currentClass, Object... searchParams) {
        super(EntityDuplicatedException.generateMessage(currentClass.getSimpleName(),
                Converter.toMap(String.class, String.class, searchParams)));
    }

    public static String generateMessage(String entity, Map<String, String> searchParams) {
        return StringUtils.capitalize(entity) + " is duplicated for the parameters " + searchParams;
    }
}
