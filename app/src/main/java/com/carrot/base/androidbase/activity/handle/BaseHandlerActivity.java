package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by victor on 9/7/16.
 */
@EActivity
public class BaseHandlerActivity extends AppCompatActivity {

    //保存状态, 0: add, 1: update
    int saveStatus = 0;


    ProgressDialog progress;
    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;


    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;


    /**
     * 需要验证的字段
     */
    FormEditText[] allFields;

    void afterInitView(String title){

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle(title);
    }

    void setDropDownListAdapter(Spinner spinner, String[] values){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        spinner.setAdapter(adapter);
    }


    boolean validate(){

        boolean allValidate = true;

        for (FormEditText field: allFields) {
            allValidate = field.testValidity() && allValidate;
        }

        return allValidate;
    }
}
