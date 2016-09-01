package com.carrot.base.androidbase.activity.handle;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.TextArrayRes;

import java.util.Date;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_core_meter_test)
@OptionsMenu(R.menu.task_item)
public class CoreMeterTestActivity extends AppCompatActivity{


    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;

    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraCoreMeterTestResult")
    CoreMeterTestResult coreMeterTestResult;


    @ViewById(R.id.et_assignment_time)
    EditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    EditText etTaskNum;
    @ViewById(R.id.et_area_name)
    EditText etAreaName;
    @ViewById(R.id.et_protect_line)
    EditText etProtectLine;
    @ViewById(R.id.et_type)
    EditText etType;
    @ViewById(R.id.et_safety_measure)
    EditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    EditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    EditText etBeginHandleTime;
    @ViewById(R.id.et_wether)
    EditText etWether;
    @ViewById(R.id.et_test_way)
    EditText etTestWay;
    @ViewById(R.id.et_a_testing)
    EditText etATesting;
    @ViewById(R.id.et_b_testing)
    EditText etBTesting;
    @ViewById(R.id.et_c_testing)
    EditText etCTesting;
    @ViewById(R.id.et_test_result)
    EditText etTestResult;
    @ViewById(R.id.et_handle_content)
    EditText etHandleContent;
    @ViewById(R.id.et_tester)
    EditText etTester;
    @ViewById(R.id.et_testing_time)
    EditText etTestingTime;
    @ViewById(R.id.et_end_handle_time)
    EditText etEndHandleTime;
    @ViewById(R.id.et_is_handled)
    EditText etIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    EditText etUnhandleReason;

    @StringArrayRes(R.array.constant_ares_name)
    String[] areaNameList;


    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(coreMeterTestResult == null){
            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
        }

        for(String areaName : areaNameList){
            Toast.makeText(CoreMeterTestActivity.this, areaName, Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:
                Toast.makeText(CoreMeterTestActivity.this, "SAVE!", Toast.LENGTH_SHORT).show();

                return true;
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void confirm(){

    }

}
