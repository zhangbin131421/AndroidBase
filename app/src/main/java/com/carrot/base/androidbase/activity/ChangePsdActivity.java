package com.carrot.base.androidbase.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.carrot.base.androidbase.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/13/16.
 */
@EActivity(R.layout.activity_change_password)
@OptionsMenu(R.menu.change_password)
public class ChangePsdActivity extends AppCompatActivity {

    @ViewById(R.id.tb_change_psd_bar)
    Toolbar toolbar;

    @AfterViews
    void initPage(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
