package com.logines.schedule.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date){
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
