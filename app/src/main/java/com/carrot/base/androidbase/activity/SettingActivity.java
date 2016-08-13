package com.carrot.base.androidbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.preferences.MyPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.w3c.dom.Text;

/**
 * Created by victor on 8/13/16.
 */
@EActivity(R.layout.activity_setting)
@OptionsMenu(R.menu.setting)
public class SettingActivity extends AppCompatActivity {

    @ViewById(R.id.tv_setting_username)
    TextView tvUsername;

    @ViewById(R.id.tb_setting_tool_bar)
    Toolbar toolbar;

    @Pref
    MyPrefs_ myPrefs;

    @AfterViews
    void initPage(){
        setSupportActionBar(toolbar);
    }

    @Click(R.id.btn_setting_logout)
    void logout(){
        myPrefs.clear();
        finish();
    }

    @Click(R.id.btn_setting_change_psd)
    void changePsd(){
        ChangePsdActivity_.intent(SettingActivity.this).start();
    }
}
