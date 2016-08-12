package com.carrot.base.androidbase.preferences;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by victor on 8/12/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface MyPrefs {

    @DefaultString("")
    String currentUsername();


}
