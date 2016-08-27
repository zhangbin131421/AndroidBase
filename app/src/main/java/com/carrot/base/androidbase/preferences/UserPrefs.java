package com.carrot.base.androidbase.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by victor on 8/21/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface UserPrefs {
//    {"ID":2,"Name":"Leo si","Phone":"15001768927","Role":"2","IsValid":1,
// "CreateTime":"\/Date(1471753316033)\/","UpdateTime":"\/Date(1471753316033)\/","Message":"OK","Code":1}

    @DefaultInt(-1)
    public int id();

    @DefaultInt(-1)
    public int isValid();

    @DefaultInt(-1)
    public int code();

    @DefaultString("")
    public String name();

    @DefaultString("")
    public String role();


    @DefaultString("")
    public String createTime();
    @DefaultString("")
    public String updateTime();


    @DefaultString("")
    public String phone();

    @DefaultString("")
    public String message();

}

