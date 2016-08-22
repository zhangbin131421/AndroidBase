package com.carrot.base.androidbase.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by victor on 8/21/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface UserPrefs {
//    {"ID":2,"Name":"Leo si","Level":"1","Phone":"15001768927","IsValid":1,"Message":"OK","Code":1}

    @DefaultInt(-1)
    int id();

    @DefaultInt(-1)
    int isValid();

    @DefaultInt(-1)
    int code();

    @DefaultString("")
    String name();

    @DefaultString("")
    String level();

    @DefaultString("")
    String phone();

    @DefaultString("")
    String message();

}

