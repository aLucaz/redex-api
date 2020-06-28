package com.application.core.usecase.util.generator.referenceCode;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ReferenceCodeGeneratorImpl implements ReferenceCodeGenerator {
    @Override
    public String generateReferenceCode() {
//         TODO: separate by probabilities
//        Integer randomPercentage = new Random().nextInt(100);
//        String [] uuidList = UUID.randomUUID().toString().toUpperCase().split(Constant.UUID_SEPARATOR);
        return UUID.randomUUID().toString().toUpperCase().substring(0, 8);
    }
}
