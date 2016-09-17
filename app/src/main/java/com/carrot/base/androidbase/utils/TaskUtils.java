package com.carrot.base.androidbase.utils;

/**
 * Created by victor on 9/17/16.
 */
public class TaskUtils {

    public static String generatTaskNum(){
        return "T" + DateUtils.getCurrentSecond();
    }
}
