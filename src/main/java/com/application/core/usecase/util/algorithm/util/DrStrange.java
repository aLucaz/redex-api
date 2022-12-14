package com.application.core.usecase.util.algorithm.util;

import com.application.shared.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DrStrange {

    public static Time getElapsedTime(String fromTime, String fromDate, String toTime, String toDate) {
        String fromDateTimeStr = fromDate + ' ' + fromTime;
        LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeStr, Constant.PROP_DATE_TIME_FORMATTER);
        String toDateTimeStr = toDate + ' ' + toTime;
        LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeStr, Constant.PROP_DATE_TIME_FORMATTER);

        LocalDateTime tempDateTime = LocalDateTime.from(fromDateTime);
        Integer elapsedSeconds = Math.toIntExact(tempDateTime.until(toDateTime, ChronoUnit.SECONDS));
        return fromNoLimitSecondsToTime(elapsedSeconds);
    }

    public static String getElapsedTime(String from, String to) {
        Time fromTime = fromStringToTime(from);
        Time toTime = fromStringToTime(to);
        return getElapsedTime(fromTime, toTime);
    }

    private static String getElapsedTime(Time fromTime, Time toTime) {
        Integer elapsedSeconds = toTime.substractSeconds(fromTime);
        Time elapsedTime = fromSecondsToTime(elapsedSeconds);
        return elapsedTime.toString();
    }

    public static Integer getElapsedTimeInSeconds(String from, String to) {
        Time elapsedTime = fromStringToTime(getElapsedTime(from, to));
        return elapsedTime.toSeconds();
    }

    public static Time fromStringToTime(String timeString) {
        String[] timeStringList = timeString.split(Constant.TIME_SEPARATOR);
        return new Time(Integer.parseInt(timeStringList[0]), Integer.parseInt(timeStringList[1]));
    }

    public static Time fromSecondsToTime(Integer seconds) {
        Integer hours = (seconds / (Constant.MINUTES_P_HOUR * Constant.SECONDS_P_MINUTE)) % Constant.HOURS_P_DAY;
        Integer minutes = (seconds / Constant.SECONDS_P_MINUTE) % Constant.MINUTES_P_HOUR;
        return new Time(hours, minutes);
    }

    public static Time getZeroTime() {
        return new Time(0, 0);
    }

    public static Time fromNoLimitSecondsToTime(Integer seconds) {
        Integer hours = seconds / (Constant.MINUTES_P_HOUR * Constant.SECONDS_P_MINUTE);
        Integer minutes = (seconds / Constant.SECONDS_P_MINUTE) % Constant.MINUTES_P_HOUR;
        return new Time(hours, minutes);
    }

    public static Time getNoLimitSum(Time base, Time other) {
        Integer result = base.toSeconds() + other.toSeconds();
        return fromNoLimitSecondsToTime(result);
    }

    public static Time getNoLimitSum(String base, String other) {
        Time baseTime = fromStringToTime(base);
        Time otherTime = fromStringToTime(other);
        return getNoLimitSum(baseTime, otherTime);
    }


    public static String addTimeToDate(String flightDateStr, String departureTimeStr, String arrivalTimeStr) {
        Time departureTime = fromStringToTime(departureTimeStr);
        Time arrivalTime = fromStringToTime(arrivalTimeStr);
        LocalDate flightDate = LocalDate.parse(flightDateStr, Constant.DATE_FORMATTER);
        if (departureTime.compareTo(arrivalTime) >= 0)
            flightDate = flightDate.plusDays(Constant.DATE_DIFFERENCE_FLIGHT);
        return flightDate.toString();
    }

    public static Boolean dateTimeIsBefore(String departureTimeStr, String departureDate, String arrivalTimeStr, String arrivalDate) {
        if (departureDate.equals(arrivalDate)) {
            Time departureTime = fromStringToTime(departureTimeStr);
            Time arrivalTime = fromStringToTime(arrivalTimeStr);
            return departureTime.compareTo(arrivalTime) < 0;
        }
        return false;
    }

    public static Boolean dateIsBefore(String departureDateStr, String arrivalDateStr) {
        LocalDate departureDate = LocalDate.parse(departureDateStr, Constant.DATE_FORMATTER);
        LocalDate arrivalDate = LocalDate.parse(arrivalDateStr, Constant.DATE_FORMATTER);
        return departureDate.isBefore(arrivalDate);
    }

    public static Boolean smallerThan(String thisTime, String thatTime){
        Time thisTimeProp = fromStringToTime(thisTime);
        Time thatTimeProp = fromStringToTime(thatTime);
        return thisTimeProp.compareTo(thatTimeProp) < 0;
    }
}
