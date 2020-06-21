package com.application.shared;

import java.time.format.DateTimeFormatter;

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
    public static final String PROP_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String PROP_TIME_FORMAT = "HH:mm";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
    public static final DateTimeFormatter PROP_TIME_FORMATTER = DateTimeFormatter.ofPattern(PROP_TIME_FORMAT);
    public static final DateTimeFormatter PROP_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PROP_DATE_TIME_FORMAT);
    public static final String TIME_SEPARATOR = ":";
    public static final Integer HOURS_P_DAY = 24;
    public static final Integer MINUTES_P_HOUR = 60;
    public static final Integer SECONDS_P_MINUTE = 60;
    public static final Integer SECONDS_P_DAY = HOURS_P_DAY * MINUTES_P_HOUR * SECONDS_P_MINUTE;

    /*
    Failed argument validation message
    */
    public static final String ARGUMENT_NOT_VALID_EXCEPTION = "La validacion fallo mano, revisa tus parametros";
    /*
     * Csv Reader Parameters
     * */
    public static final String CSV_FILE_PATH = "src/main/java/com/application/shared/charge/etFlight.txt";
    public static final String DELIMITER = ",";
    /*
     * Business logic about the range time
     * */
    public static final Integer SAME_CONTINENT_AVAILABLE_DAYS = 1;
    public static final Integer ANOTHER_CONTINENT_AVAILABLE_DAYS = 2;
    public static final Integer HOURS_OFF_TO_REQUEST_PATH = 0;
    public static final Integer DATE_DIFFERENCE_FLIGHT = 1;
    /*
     * */
    public static final Integer TRUE = 1;
    public static final Integer FALSE = 0;
    /*
     * Algorithms constants*/
    public static final String DEFAULT_ACTION = "START_POINT";
    public static final String DESTINY_NOT_FOUND = "DESTINY_NOT_FOUND";
    /*
     * Active constatnt*/
    public static final Integer ACTIVE = 1;
    /*
     * Json open and closed*/
    public static final String DELIMITER_OPEN = "{";
    public static final String DELIMITER_CLOSE = "}";
    /*
    * CORS allowed methods*/
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    public static final String DELETE = "DELETE";

    /*
    * EXCEPTION default messages*/
    public static final String ROUTE_NOT_FOUND_MSG = "La ruta no ha sido encontrada";
}
