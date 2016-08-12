package com.carrot.base.androidbase;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.carrot.base.androidbase.preferences.MyPrefs_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {


    @Pref
    MyPrefs_ myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        if(myPrefs.currentUsername().get().equals("")){
            LoginActivity_.intent(MainActivity.this).start();

        }
    }


    @OptionsItem(R.id.menu_main_setting)
    void myMethod() {
        System.out.println("----setting-----");
    }
}
