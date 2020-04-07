package com.logines.schedule.utils;

public class CustomStringUtils {

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
