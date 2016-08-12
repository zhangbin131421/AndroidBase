package com.carrot.base.androidbase;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carrot.base.androidbase.preferences.MyPrefs;
import com.carrot.base.androidbase.preferences.MyPrefs_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    @Pref
    MyPrefs_ myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("currentLoginUser: " + myPrefs.currentUsername().get().toString());
        if(myPrefs.currentUsername().get().equals("")){
            LoginActivity_.intent(this).start();
        }
    }
}
