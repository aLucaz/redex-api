package com.application.core.usecase.util.algorithm.util;

import com.application.shared.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Time implements Comparable<Time> {
    private Integer hours;
    private Integer minutes;
    private Integer seconds;

    public Time(Integer hours, Integer minutes, Integer seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Time(Integer hours, Integer minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public Integer toSeconds() {
        return hours * Constant.MINUTES_P_HOUR * Constant.SECONDS_P_MINUTE + minutes * Constant.SECONDS_P_MINUTE;
    }

    public Integer substractSeconds(Time other) {
        Integer result = this.toSeconds() - other.toSeconds();
        if (result < 0)
            return result + Constant.SECONDS_P_DAY;
        return result;
    }

    public String toString() {
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes);
    }

    @Override
    public int compareTo(Time otherTime) {
        return this.toSeconds() - otherTime.toSeconds();
    }
}
