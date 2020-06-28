package com.application.core.usecase.util.generator.referenceCode;

import org.junit.jupiter.api.Test;

class referenceCodeGeneratorImplTest {


    @Test
    void generateReferenceCode() {
        ReferenceCodeGeneratorImpl referenceCodeGenerator = new ReferenceCodeGeneratorImpl();
        System.out.println(referenceCodeGenerator.generateReferenceCode());
        System.out.println(referenceCodeGenerator.generateReferenceCode());
        System.out.println(referenceCodeGenerator.generateReferenceCode());
    }
}