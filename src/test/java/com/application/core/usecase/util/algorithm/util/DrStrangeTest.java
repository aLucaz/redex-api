package com.application.core.usecase.util.algorithm.util;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DrStrangeTest {
    public static final Logger logger = LoggerFactory.getLogger(DrStrangeTest.class);

    @Test
    void getElapsedTime() {
        String result = DrStrange.getElapsedTime("22:47", "08:31");
        logger.info(result);
    }

    @Test
    void fromStringToProperFormat() {
    }

    @Test
    void fromSecondsToTime() {
    }
}