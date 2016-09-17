package com.carrot.base.androidbase.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by victor on 9/1/16.
 */
public class DateUtils {

    public static final int DEAD_LINE_DATE = 7;

    static SimpleDateFormat formatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat formatSecond = new SimpleDateFormat("yyyyMMddHHmmss");
    static SimpleDateFormat formatSecond2 = new SimpleDateFormat("HHmmss");


    public static String getCurrentYYYY_MM_DD(){
        return formatYYYYMMDD.format(new Date());
    }

    public static String getEndTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, DEAD_LINE_DATE);  // number of days to add
        return formatToYYYY_MM_DD(c.getTime());
    }

    public static String formatToYYYY_MM_DD(Date date){
        return formatYYYYMMDD.format(date);
    }

    public static String format(Date date){
        return format.format(date);
    }

    public static String getCurrent(){
        return format.format(new Date());
    }


    public static String getCurrentSecond(){
        return formatSecond.format(new Date());
    }
    public static String getCurrentSecond2(){
        return formatSecond2.format(new Date());
    }
}
