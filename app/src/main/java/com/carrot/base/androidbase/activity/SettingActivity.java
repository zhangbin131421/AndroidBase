package com.carrot.base.androidbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.preferences.UserPrefs_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by victor on 8/13/16.
 */
@EActivity(R.layout.activity_setting)
@OptionsMenu(R.menu.setting)
public class SettingActivity extends AppCompatActivity {

    @ViewById(R.id.tv_setting_username)
    TextView tvUsername;
    @ViewById(R.id.tv_setting_dept)
    TextView tvDept;
    @ViewById(R.id.tv_setting_device_id)
    TextView tvDeviceId;

    @ViewById(R.id.tb_setting_tool_bar)
    Toolbar toolbar;

    @Pref
    UserPrefs_ userPrefs;

    @AfterViews
    void initPage(){

        tvUsername.setText(userPrefs.name().get());

        tvDept.setText(userPrefs.dept().get());
        tvDeviceId.setText(userPrefs.deviceID().get());

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Click(R.id.btn_setting_logout)
    void logout(){
        userPrefs.clear();
        finish();
    }

    @Click(R.id.btn_setting_change_psd)
    void changePsd(){
        ChangePsdActivity_.intent(SettingActivity.this).start();
    }
}
