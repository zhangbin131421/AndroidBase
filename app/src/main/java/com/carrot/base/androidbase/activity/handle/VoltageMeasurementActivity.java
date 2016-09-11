
package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.VoltageMeasurementClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.VoltageMeasurementResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
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
@EActivity(R.layout.activity_voltage_measurement)
@OptionsMenu(R.menu.task_item)
public class VoltageMeasurementActivity extends BaseHandlerActivity{



    List<PhotoInfo> currentAList = new ArrayList<>();

    List<PhotoInfo> currentBList = new ArrayList<>();

    List<PhotoInfo> currentCList = new ArrayList<>();

    List<PhotoInfo> zeoLineCurrentList = new ArrayList<>();

    List<PhotoInfo> headerVoltageList = new ArrayList<>();

    List<PhotoInfo> footerVoltageList = new ArrayList<>();


    VoltageMeasurementResult voltageMeasurementResult;

    @RestService
    VoltageMeasurementClient voltageMeasurementClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;

    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;

    @ViewById(R.id.etAreaName)
    Spinner etAreaName;

    @ViewById(R.id.etConfigA)
    FormEditText etConfigA;

    @ViewById(R.id.etConfigB)
    Spinner etConfigB;

    @ViewById(R.id.etConfigC)
    Spinner etConfigC;

    @ViewById(R.id.etRatedCurrent)
    FormEditText etRatedCurrent;

    @ViewById(R.id.etPowerHouseholder)
    FormEditText etPowerHouseholder;

    @ViewById(R.id.etPowerCapacity)
    FormEditText etPowerCapacity;

    @ViewById(R.id.etHouseholder)
    FormEditText etHouseholder;

    @ViewById(R.id.etHouseholderCapacity)
    FormEditText etHouseholderCapacity;

    @ViewById(R.id.etSafetyMeasure)
    Spinner etSafetyMeasure;

    @ViewById(R.id.etEndTime)
    FormEditText etEndTime;

    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.etPeriod)
    Spinner etPeriod;

    @ViewById(R.id.etCurrentA_content)
    org.apmem.tools.layouts.FlowLayout etCurrentAContent;

    @ViewById(R.id.etCurrentB_content)
    org.apmem.tools.layouts.FlowLayout etCurrentBContent;

    @ViewById(R.id.etCurrentC_content)
    org.apmem.tools.layouts.FlowLayout etCurrentCContent;

    @ViewById(R.id.etZeoLineCurrent_content)
    org.apmem.tools.layouts.FlowLayout etZeoLineCurrentContent;

    @ViewById(R.id.etLoadRate)
    FormEditText etLoadRate;

    @ViewById(R.id.etImbalanceRate)
    FormEditText etImbalanceRate;

    @ViewById(R.id.etHeaderVoltage_content)
    org.apmem.tools.layouts.FlowLayout etHeaderVoltageContent;

    @ViewById(R.id.etFooterVoltage_content)
    org.apmem.tools.layouts.FlowLayout etFooterVoltageContent;

    @ViewById(R.id.etIsOutOfLimit)
    Spinner etIsOutOfLimit;

    @ViewById(R.id.etModificationOpinion)
    FormEditText etModificationOpinion;

    @ViewById(R.id.etTestTime)
    FormEditText etTestTime;

    @ViewById(R.id.etTester)
    FormEditText etTester;

    @ViewById(R.id.etEndHandleTime)
    FormEditText etEndHandleTime;

    @ViewById(R.id.etIsHandled)
    Spinner etIsHandled;

    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;


    @AfterViews
    void bindAdapter(){

        super.afterInitView("交叉跨越测量", getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime,etTaskNum,etConfigA,etRatedCurrent,etPowerHouseholder,etPowerCapacity,etHouseholder,etHouseholderCapacity,etEndTime,etBeginHandleTime,etLoadRate,etImbalanceRate,etModificationOpinion,etTestTime,etTester,etEndHandleTime,etUnhandleReason};
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etConfigB, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etConfigC, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etSafetyMeasure, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etPeriod, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsOutOfLimit, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

    }


    @Override
    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            voltageMeasurementResult = voltageMeasurementClient.getById(taskBaseVo.id);
        }

        refreshView();
        dissmisLoading();
    }


    @UiThread
    void refreshView(){
        if(voltageMeasurementResult == null){

            voltageMeasurementResult = new VoltageMeasurementResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(voltageMeasurementResult.assignmentTime);
            etTaskNum.setText(voltageMeasurementResult.taskNum);
            etAreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.areaName));
            etConfigA.setText(voltageMeasurementResult.configA);
            etConfigB.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.configB));
            etConfigC.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.configC));
            etRatedCurrent.setText(voltageMeasurementResult.ratedCurrent);
            etPowerHouseholder.setText(voltageMeasurementResult.powerHouseholder+"");
            etPowerCapacity.setText(voltageMeasurementResult.powerCapacity);
            etHouseholder.setText(voltageMeasurementResult.householder+"");
            etHouseholderCapacity.setText(voltageMeasurementResult.householderCapacity);
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.safetyMeasure));
            etEndTime.setText(voltageMeasurementResult.endTime);
            etBeginHandleTime.setText(voltageMeasurementResult.beginHandleTime);
            etPeriod.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.period));
            etLoadRate.setText(voltageMeasurementResult.loadRate);
            etImbalanceRate.setText(voltageMeasurementResult.imbalanceRate);
            etIsOutOfLimit.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, voltageMeasurementResult.isOutOfLimit));
            etModificationOpinion.setText(voltageMeasurementResult.modificationOpinion);
            etTestTime.setText(voltageMeasurementResult.testTime);
            etTester.setText(voltageMeasurementResult.tester);
            etEndHandleTime.setText(voltageMeasurementResult.endHandleTime);
            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, voltageMeasurementResult.isHandled));
            etUnhandleReason.setText(voltageMeasurementResult.unhandleReason);

            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(voltageMeasurementResult.currentAPic, etCurrentAContent);

        super.getImageFromURL(voltageMeasurementResult.currentBPic, etCurrentBContent);

        super.getImageFromURL(voltageMeasurementResult.currentCPic, etCurrentCContent);

        super.getImageFromURL(voltageMeasurementResult.zeoLineCurrentPic, etZeoLineCurrentContent);

        super.getImageFromURL(voltageMeasurementResult.headerVoltagePic, etHeaderVoltageContent);

        super.getImageFromURL(voltageMeasurementResult.footerVoltagePic, etFooterVoltageContent);

    }


    @Click(R.id.btn_add_imageCurrentA)
    void addImageCurrentA(){

        super.showChooseImage(currentAList, etCurrentAContent);

    }


    @Click(R.id.btn_add_imageCurrentB)
    void addImageCurrentB(){

        super.showChooseImage(currentBList, etCurrentBContent);

    }


    @Click(R.id.btn_add_imageCurrentC)
    void addImageCurrentC(){

        super.showChooseImage(currentCList, etCurrentCContent);

    }


    @Click(R.id.btn_add_imageZeoLineCurrent)
    void addImageZeoLineCurrent(){

        super.showChooseImage(zeoLineCurrentList, etZeoLineCurrentContent);

    }


    @Click(R.id.btn_add_imageHeaderVoltage)
    void addImageHeaderVoltage(){

        super.showChooseImage(headerVoltageList, etHeaderVoltageContent);

    }


    @Click(R.id.btn_add_imageFooterVoltage)
    void addImageFooterVoltage(){

        super.showChooseImage(footerVoltageList, etFooterVoltageContent);

    }



    /**
     * 新增
     */
    @Override
    void add(){

        voltageMeasurementResult.assignByUserID = userPrefs.id().get();
        voltageMeasurementResult.userId = userPrefs.id().get();

        voltageMeasurementClient.add(voltageMeasurementResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){

        MultiValueMap<String, Object> data = null;
        try {
            data = voltageMeasurementResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentAPic, currentAList, this);
        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentBPic, currentBList, this);
        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentCPic, currentCList, this);
        FileUtils.addImageToData(data, VoltageMeasurementResult.ZeoLineCurrentPic, zeoLineCurrentList, this);
        FileUtils.addImageToData(data, VoltageMeasurementResult.HeaderVoltagePic, headerVoltageList, this);
        FileUtils.addImageToData(data, VoltageMeasurementResult.FooterVoltagePic, footerVoltageList, this);
        voltageMeasurementClient.update(data);

    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.voltageMeasurementResult.assignmentTime = etAssignmentTime.getText().toString();

            this.voltageMeasurementResult.taskNum = etTaskNum.getText().toString();

            this.voltageMeasurementResult.areaName = etAreaName.getSelectedItem().toString();

            this.voltageMeasurementResult.configA = etConfigA.getText().toString();

            this.voltageMeasurementResult.configB = etConfigB.getSelectedItem().toString();

            this.voltageMeasurementResult.configC = etConfigC.getSelectedItem().toString();

            this.voltageMeasurementResult.ratedCurrent = etRatedCurrent.getText().toString();

            this.voltageMeasurementResult.powerHouseholder = Integer.parseInt(etPowerHouseholder.getText().toString());

            this.voltageMeasurementResult.powerCapacity = etPowerCapacity.getText().toString();

            this.voltageMeasurementResult.householder = Integer.parseInt(etHouseholder.getText().toString());

            this.voltageMeasurementResult.householderCapacity = etHouseholderCapacity.getText().toString();

            this.voltageMeasurementResult.safetyMeasure = etSafetyMeasure.getSelectedItem().toString();

            this.voltageMeasurementResult.endTime = etEndTime.getText().toString();

            this.voltageMeasurementResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.voltageMeasurementResult.period = etPeriod.getSelectedItem().toString();


            this.voltageMeasurementResult.loadRate = etLoadRate.getText().toString();

            this.voltageMeasurementResult.imbalanceRate = etImbalanceRate.getText().toString();

            this.voltageMeasurementResult.isOutOfLimit = etIsOutOfLimit.getSelectedItem().toString();

            this.voltageMeasurementResult.modificationOpinion = etModificationOpinion.getText().toString();

            this.voltageMeasurementResult.testTime = etTestTime.getText().toString();

            this.voltageMeasurementResult.tester = etTester.getText().toString();

            this.voltageMeasurementResult.endHandleTime = etEndHandleTime.getText().toString();

            this.voltageMeasurementResult.isHandled = etIsHandled.getSelectedItem().toString();

            this.voltageMeasurementResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}