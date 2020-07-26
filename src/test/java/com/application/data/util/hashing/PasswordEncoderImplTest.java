package com.application.data.util.hashing;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Hashtable;

class PasswordEncoderImplTest {
    public static final Logger logger = LoggerFactory.getLogger(PasswordEncoderImplTest.class);

    @SneakyThrows
    @Test
    void hash() {
        PasswordEncoderImpl encoder = new PasswordEncoderImpl();
        String password = "password123";
        Hashtable<String, String> hashtable = encoder.hash(password);
        logger.info(hashtable.get("passwordHash"));
        logger.info(hashtable.get("passwordSalt"));
        String passwordTwo = "password123";
        hashtable = encoder.hash(passwordTwo);
        logger.info(hashtable.get("passwordHash"));
        logger.info(hashtable.get("passwordSalt"));
    }
}