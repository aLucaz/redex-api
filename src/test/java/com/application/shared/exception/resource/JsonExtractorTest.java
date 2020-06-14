package com.application.shared.exception.resource;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonExtractorTest {

    @Test
    void extractJsonFromString() {
        String text  = "hola amigos {\"id\" : \"123\"}";
        System.out.println(JsonExtractor.extractJsonFromString(text));

    }
}