package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
import com.carrot.base.androidbase.vo.result.EquipmentCheckResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_equipment_check)
@OptionsMenu(R.menu.task_item)
public class EquipmentCheckActivity extends AppCompatActivity{


    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;

    EquipmentCheckResult equipmentCheckResult;


    @RestService
    EquipmentCheckClient equipmentCheckClient;


    ProgressDialog progress;


    //保存状态, 0: add, 1: update
    private int saveStatus = 0;

    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;

    @ViewById(R.id.et_assignment_time)
    EditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    EditText etTaskNum;

    @ViewById(R.id.et_check_type)
    EditText etCheckType;

    @ViewById(R.id.et_check_scope)
    EditText etCheckScope;

    @ViewById(R.id.et_safety_measure)
    EditText etSafetyMeasure;

    @ViewById(R.id.et_end_time)
    EditText etEndTime;

    @ViewById(R.id.et_begin_handle_time)
    EditText etBeginHandleTime;

    @ViewById(R.id.et_exist_defect)
    EditText etExistDefect;

    @ViewById(R.id.et_defect_place)
    EditText etDefectPlace;

    @ViewById(R.id.et_defect_content)
    EditText etDefectContent;

    @ViewById(R.id.et_defect_level)
    EditText etDefectLevel;

    @ViewById(R.id.et_handle_content)
    EditText etHandleContent;

    @ViewById(R.id.et_check_people)
    EditText etCheckpeople;

    @ViewById(R.id.et_check_time)
    EditText etCheckTime;

    @ViewById(R.id.et_end_handle_time)
    EditText etEndHandleTime;

    @ViewById(R.id.et_is_handled)
    EditText etIsHandled;

    @ViewById(R.id.et_unhandle_reason)
    EditText etUnhandleReason;



    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle(TypeUtils.TYPE_2_3);

        progress = new ProgressDialog(this);

        getObject();

    }


    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            equipmentCheckResult = equipmentCheckClient.getById(taskBaseVo.id);
        }

        refreshView();
    }


    @UiThread
    void refreshView(){
        if(equipmentCheckResult == null){

            equipmentCheckResult = new EquipmentCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            //test start
            //test end
        }else{

            etAssignmentTime.setText(equipmentCheckResult.assignmentTime);
            etTaskNum.setText(equipmentCheckResult.taskNum);



            this.saveStatus = 1;
        }
        if(progress != null){
            progress.dismiss();
        }
    }


    @UiThread
    void showLoading(){
        progress.setTitle("Loading");
        progress.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:
                Toast.makeText(EquipmentCheckActivity.this, "SAVE!", Toast.LENGTH_SHORT).show();
                conform();
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

    void conform(){

    }
}
