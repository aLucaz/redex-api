package com.application.shared;

import java.time.LocalDateTime;

public class Galactus {
    public static LocalDateTime parseRequestFormatToLocalDateTime(String date, String time){
        String dateFormatted = date.substring(0,4) + '-' + date.substring(4,6) + '-' + date.substring(6,8);
        return LocalDateTime.parse(dateFormatted + ' ' + time, Constant.PROP_DATE_TIME_FORMATTER);
    }

    public static LocalDateTime unifyDateTime(String date, String time){
        String dateTimeStr = date + ' ' + time;
        return LocalDateTime.parse(dateTimeStr, Constant.PROP_DATE_TIME_FORMATTER);
    }
}
