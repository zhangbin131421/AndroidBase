package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.TotalPerformanceTestClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.TotalPerformanceTestResult;

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
@EActivity(R.layout.activity_total_performance_test)
@OptionsMenu(R.menu.task_item)
public class TotalPerformanceTestActivity extends BaseHandlerActivity{

    List<PhotoInfo> electricityBPicList = new ArrayList<>();
    List<PhotoInfo> electricityDPicList = new ArrayList<>();


    TotalPerformanceTestResult totalPerformanceTestResult;

    @RestService
    TotalPerformanceTestClient totalPerformanceTestClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;
    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;
    @ViewById(R.id.etAreaName)
    Spinner etAreaName;
    @ViewById(R.id.etProtectLine)
    Spinner etProtectLine;
    @ViewById(R.id.etType)
    Spinner etType;
    @ViewById(R.id.etSafetyMeasure)
    Spinner etSafetyMeasure;
    @ViewById(R.id.etEndTime)
    FormEditText etEndTime;
    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.etElectricityA)
    FormEditText etElectricityA;
    @ViewById(R.id.etElectricityB)
    org.apmem.tools.layouts.FlowLayout etElectricityB;
    @ViewById(R.id.etElectricityC)
    FormEditText etElectricityC;
    @ViewById(R.id.etElectricityD)
    org.apmem.tools.layouts.FlowLayout etElectricityD;
    @ViewById(R.id.etOperateTime)
    FormEditText etOperateTime;
    @ViewById(R.id.etTestTime)
    FormEditText etTestTime;
    @ViewById(R.id.etTestResult)
    Spinner etTestResult;
    @ViewById(R.id.etHandleContent)
    FormEditText etHandleContent;
    @ViewById(R.id.etTester)
    FormEditText etTester;
    @ViewById(R.id.etEndHandleTime)
    FormEditText etEndHandleTime;
    @ViewById(R.id.etIsHandled)
    FormEditText etIsHandled;
    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;

    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_2_3, getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime,etTaskNum,etEndTime,etBeginHandleTime,
                etElectricityA,etElectricityC,etOperateTime,etTestTime,etHandleContent,
                etTester,etEndHandleTime,etIsHandled,etUnhandleReason};
    }


    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, TypeUtils.AREA);

        setDropDownListAdapter(etProtectLine, TypeUtils.PRODUCTION_LINE);

        setDropDownListAdapter(etType, TypeUtils.TYPE_TPT);

        setDropDownListAdapter(etSafetyMeasure, TypeUtils.SAFETY_MEASURE);

        setDropDownListAdapter(etTestResult, TypeUtils.TEST_RESULT);
    }


    @Override
    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            totalPerformanceTestResult = totalPerformanceTestClient.getById(taskBaseVo.id);
        }

        refreshView();
        dissmisLoading();
    }


    @UiThread
    void refreshView(){
        if(totalPerformanceTestResult == null){

            totalPerformanceTestResult = new TotalPerformanceTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

            //test start


            //test end
        }else{
            etAssignmentTime.setText(totalPerformanceTestResult.assignmentTime);
            etTaskNum.setText(totalPerformanceTestResult.taskNum);
            etAreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.AREA, totalPerformanceTestResult.areaName));
            etProtectLine.setSelection(TypeUtils.getSelectedIndex(TypeUtils.PRODUCTION_LINE, totalPerformanceTestResult.protectLine));
            etType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TPT, totalPerformanceTestResult.type));
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.SAFETY_MEASURE, totalPerformanceTestResult.safetyMeasure));
            etEndTime.setText(totalPerformanceTestResult.endTime);

            etBeginHandleTime.setText(totalPerformanceTestResult.beginHandleTime);
            etElectricityA.setText(totalPerformanceTestResult.electricityA);
//            etElectricityB.setText(totalPerformanceTestResult.assignmentTime);
            etElectricityC.setText(totalPerformanceTestResult.electricityC);
//            etElectricityD.setText(totalPerformanceTestResult.assignmentTime);
            etOperateTime.setText(totalPerformanceTestResult.operateTime);
            etTestTime.setText(totalPerformanceTestResult.testTime);
            etTestResult.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TEST_RESULT, totalPerformanceTestResult.testResult));
            etHandleContent.setText(totalPerformanceTestResult.handleContent);
            etTester.setText(totalPerformanceTestResult.tester);

            etEndHandleTime.setText(totalPerformanceTestResult.endHandleTime);
            etIsHandled.setText(totalPerformanceTestResult.isHandled);
            etUnhandleReason.setText(totalPerformanceTestResult.unhandleReason);
            getImage();

            this.saveStatus = 1;
        }

    }


    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(totalPerformanceTestResult.ElectricityBPic, etElectricityB);
        super.getImageFromURL(totalPerformanceTestResult.ElectricityDPic, etElectricityD);

    }


    @Click(R.id.btn_add_b_image)
    void addImageB(){

        super.showChooseImage(electricityBPicList, etElectricityB);

    }

    @Click(R.id.btn_add_d_image)
    void addImageC(){

        super.showChooseImage(electricityDPicList, etElectricityD);

    }


    /**
     * 新增
     */
    @Override
    void add(){

        totalPerformanceTestResult.assignByUserID = userPrefs.id().get();
        totalPerformanceTestResult.userId = userPrefs.id().get();

        totalPerformanceTestClient.add(totalPerformanceTestResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){

        MultiValueMap<String, Object> data = null;
        try {
            data = totalPerformanceTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, TotalPerformanceTestResult.ElectricityBPic, electricityBPicList, this);
        FileUtils.addImageToData(data, TotalPerformanceTestResult.ElectricityDPic, electricityDPicList, this);

        totalPerformanceTestClient.update(data);

    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.totalPerformanceTestResult.assignmentTime = etAssignmentTime.getText().toString();
            this.totalPerformanceTestResult.taskNum = etTaskNum.getText().toString();
            this.totalPerformanceTestResult.areaName = etAreaName.getSelectedItem().toString();
            this.totalPerformanceTestResult.protectLine = etProtectLine.getSelectedItem().toString();
            this.totalPerformanceTestResult.type = etType.getSelectedItem().toString();
            this.totalPerformanceTestResult.safetyMeasure = etSafetyMeasure.getSelectedItem().toString();
            this.totalPerformanceTestResult.endTime = etEndTime.getText().toString();
            this.totalPerformanceTestResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.totalPerformanceTestResult.electricityA = etElectricityA.getText().toString();
            this.totalPerformanceTestResult.electricityC = etElectricityC.getText().toString();
            this.totalPerformanceTestResult.operateTime = etOperateTime.getText().toString();
            this.totalPerformanceTestResult.testTime = etTestTime.getText().toString();
            this.totalPerformanceTestResult.testResult = etTestResult.getSelectedItem().toString();
            this.totalPerformanceTestResult.handleContent = etHandleContent.getText().toString();
            this.totalPerformanceTestResult.tester = etTester.getText().toString();
            this.totalPerformanceTestResult.endHandleTime = etEndHandleTime.getText().toString();
            this.totalPerformanceTestResult.isHandled = etIsHandled.getText().toString();
            this.totalPerformanceTestResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
