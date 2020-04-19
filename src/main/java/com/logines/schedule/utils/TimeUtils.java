package com.logines.schedule.utils;

import java.util.concurrent.TimeUnit;

public class TimeUtils {
    public static String millisecondToFullTime(long millisecond) {
        return timeUnitToFullTime(millisecond, TimeUnit.MILLISECONDS);
    }

    public static String secondToFullTime(long second) {
        return timeUnitToFullTime(second, TimeUnit.SECONDS);
    }

    public static String timeUnitToFullTime(long time, TimeUnit timeUnit) {
        long day = timeUnit.toDays(time);
        long hour = timeUnit.toHours(time) % 24;
        long minute = timeUnit.toMinutes(time) % 60;
        long second = timeUnit.toSeconds(time) % 60;
        if (day > 0) {
            return String.format("%dday %02d:%02d:%02d", day, hour, minute, second);
        } else if (hour > 0) {
            return String.format("%d:%02d:%02d", hour, minute, second);
        } else if (minute > 0) {
            return String.format("%d:%02d", minute, second);
        } else {
            return String.format("%02d", second);
        }
    }
}

