package com.application.shared;

public class Constant {
    /*
    Password hashing constants
    */
    public static final Integer SALT_LENGTH = 15;
    public static final Integer PBEKEY_ITERATION_NUMBER = 10;
    public static final Integer PBEKEY_LENGTH = 512;
    public static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA512";
    /*
    Email validated by defaul
    */
    public static final Integer DEFAULT_VALID_EMAIL = 1;
    /*
    Date and datetime format
    */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /*
    Failed argument validation message
    */
    public static  final String ARGUMENT_NOT_VALID_EXCEPTION = "La validacion fallo mano, revisa tus parametros";
}
