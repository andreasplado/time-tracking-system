package com.logines.schedule.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date){
        Format formatter = new SimpleDateFormat("dd-MMM-yy");
        return formatter.format(date);
    }
}
