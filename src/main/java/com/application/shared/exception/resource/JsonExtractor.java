package com.application.shared.exception.resource;

import com.application.shared.Constant;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JsonExtractor {
    public static String extractJsonFromString(String text){
        return text.substring(text.indexOf(Constant.DELIMITER_OPEN), text.indexOf(Constant.DELIMITER_CLOSE) + 1);
    }
}
