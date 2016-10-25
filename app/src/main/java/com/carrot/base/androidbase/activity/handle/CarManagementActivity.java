package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.CarManagementClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CarManagementResult;
import com.carrot.base.androidbase.vo.result.CarResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_car_management)
@OptionsMenu(R.menu.task_item)
public class CarManagementActivity extends BaseHandlerActivity{





    CarManagementResult carManagementResult;

    @RestService
    CarManagementClient carManagementClient;



    @ViewById(R.id.et_apply_time)
    FormEditText etApplyTime;
    @ViewById(R.id.et_apply_num)
    FormEditText etApplyNum;
    @ViewById(R.id.spn_car_id)
    Spinner spnCarID;
    @ViewById(R.id.et_arrival_place)
    FormEditText etArrivalPlace;
    @ViewById(R.id.et_drive_out_time)
    FormEditText etDriveOutTime;
    @ViewById(R.id.et_back_time)
    FormEditText etBackTime;
    @ViewById(R.id.et_start_distance_code)
    FormEditText etStartDistanceCode;
    @ViewById(R.id.et_end_distance_code)
    FormEditText etEndDistanceCode;
    @ViewById(R.id.et_cost)
    FormEditText etCost;
    @ViewById(R.id.et_apply_status)
    FormEditText etApplyStatus;

    @Extra("cars")
    String[] cars;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_4_1, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {};

        addDisableList = new FormEditText[] {etApplyTime,etApplyNum,etApplyStatus};

        updateDisableList = new FormEditText[] {etApplyTime,etApplyNum,
                etArrivalPlace,etDriveOutTime,etBackTime,etStartDistanceCode,
                etCost,etEndDistanceCode,etApplyStatus};

        finishDisableList = new FormEditText[] {etApplyTime,etApplyNum,etArrivalPlace,etCost,
                etDriveOutTime,etBackTime,etStartDistanceCode,etEndDistanceCode,etApplyStatus};

        updateDisabledSpinnerList = new Spinner[] {spnCarID};
        finishDisabledSpinnerList = new Spinner[] {spnCarID};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etDriveOutTime, OpenDateVo.UPDATE),
            new OpenDateVo(etBackTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{};



        imageAddButtonList = new ImageView[] {        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {        };

    }

    @Override
    public void setErrorHandler(){
        carManagementClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){


        //下拉选择框
        setDropDownListAdapter(spnCarID, cars);
//        setDropDownListAdapter(spnCost, TypeUtils.CAR_COST);
//        setDropDownListAdapter(spnApplyStatus, TypeUtils.APPLY_STATUS);
    }


    void getEntityFromServer(){
        carManagementResult = carManagementClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(carManagementResult == null){

            carManagementResult = new CarManagementResult();


        }else{

            etApplyTime.setText(getYYYYMMDD(carManagementResult.applyTime));
            etApplyNum.setText(carManagementResult.applyNum);
            spnCarID.setSelection(TypeUtils.getSelectedIndex(cars, carManagementResult.carID));
            etArrivalPlace.setText(carManagementResult.arrivalPlace);
            etDriveOutTime.setText(getYYYYMMDD(carManagementResult.driveOutTime));
            etBackTime.setText(getYYYYMMDD(carManagementResult.backTime));
            etStartDistanceCode.setText(carManagementResult.startDistanceCode);
            etEndDistanceCode.setText(carManagementResult.endDistanceCode);
//            spnCost.setSelection(TypeUtils.getSelectedIndex(TypeUtils.CAR_COST, carManagementResult.cost));
            etCost.setText(carManagementResult.cost);
//            spnApplyStatus.setSelection(TypeUtils.getSelectedIndex(TypeUtils.APPLY_STATUS, carManagementResult.applyStatus));
            etApplyStatus.setText(carManagementResult.applyStatus);
            getImage();
        }

        if(carManagementResult.applyStatus == null || carManagementResult.applyStatus.equals("")){
            etApplyStatus.setText("申请中");
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){


    }

    UpdateResult save(){

        if(carManagementResult.iD == 0){
            carManagementResult.assignByUserID = userPrefs.id().get();
            carManagementResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = carManagementResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return carManagementClient.add(carManagementResult);
    }


    boolean validate(){

        if(super.validate()) {

            this.carManagementResult.applyTime = etApplyTime.getText().toString();
            this.carManagementResult.applyNum = etApplyNum.getText().toString();
            this.carManagementResult.carID = spnCarID.getSelectedItem().toString();
            this.carManagementResult.arrivalPlace = etArrivalPlace.getText().toString();
            this.carManagementResult.driveOutTime = etDriveOutTime.getText().toString();
            this.carManagementResult.backTime = etBackTime.getText().toString();
            this.carManagementResult.startDistanceCode = etStartDistanceCode.getText().toString();
            this.carManagementResult.endDistanceCode = etEndDistanceCode.getText().toString();
            this.carManagementResult.cost = etCost.getText().toString();

            this.carManagementResult.applyStatus = etApplyStatus.getText().toString();



            return true;
        }{
            return false;
        }
    }

}
