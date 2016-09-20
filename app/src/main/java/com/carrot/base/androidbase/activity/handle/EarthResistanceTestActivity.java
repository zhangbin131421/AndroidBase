

package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EarthResistanceTestClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.EarthResistanceTestResult;

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
@EActivity(R.layout.activity_earth_resistance_test)
@OptionsMenu(R.menu.task_item)
public class EarthResistanceTestActivity extends BaseHandlerActivity{



    List<PhotoInfo> testResistanceValueList = new ArrayList<>();


    EarthResistanceTestResult earthResistanceTestResult;

    @RestService
    EarthResistanceTestClient earthResistanceTestClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;

    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;

    @ViewById(R.id.etAreaName)
    Spinner etAreaName;

    @ViewById(R.id.etEarthPlace)
    FormEditText etEarthPlace;

    @ViewById(R.id.etEarthEquipmentName)
    FormEditText etEarthEquipmentName;

    @ViewById(R.id.etResistanceValue)
    FormEditText etResistanceValue;

    @ViewById(R.id.etSafetyMeasure)
    Spinner etSafetyMeasure;

    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.etWether)
    Spinner etWether;

    @ViewById(R.id.etTestResistanceValue_content)
    org.apmem.tools.layouts.FlowLayout etTestResistanceValueContent;

    @ViewById(R.id.etTestDate)
    FormEditText etTestDate;

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

        super.afterInitView("接地电阻测量", getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime,etTaskNum,etEarthPlace,etEarthEquipmentName,etResistanceValue,etBeginHandleTime,etTestDate,etTester,etEndHandleTime,etUnhandleReason};
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etSafetyMeasure, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etWether, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

    }



    @Background
    void getEntityFromServer(){
        earthResistanceTestResult = earthResistanceTestClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(earthResistanceTestResult == null){

            earthResistanceTestResult = new EarthResistanceTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(earthResistanceTestResult.assignmentTime);
            etTaskNum.setText(earthResistanceTestResult.taskNum);
            etAreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, earthResistanceTestResult.areaName));
            etEarthPlace.setText(earthResistanceTestResult.earthPlace);
            etEarthEquipmentName.setText(earthResistanceTestResult.earthEquipmentName);
            etResistanceValue.setText(earthResistanceTestResult.resistanceValue);
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, earthResistanceTestResult.safetyMeasure));
            etBeginHandleTime.setText(earthResistanceTestResult.beginHandleTime);
            etWether.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, earthResistanceTestResult.wether));
            etTestDate.setText(earthResistanceTestResult.testDate);
            etTester.setText(earthResistanceTestResult.tester);
            etEndHandleTime.setText(earthResistanceTestResult.endHandleTime);
            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, earthResistanceTestResult.isHandled));
            etUnhandleReason.setText(earthResistanceTestResult.unhandleReason);

            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(earthResistanceTestResult.testResistanceValuePic, etTestResistanceValueContent);

    }


    @Click(R.id.btn_add_imageTestResistanceValue)
    void addImageTestResistanceValue(){

        super.showChooseImage(testResistanceValueList, etTestResistanceValueContent);

    }



    /**
     * 新增
     */
    @Override
    void save(){

        if(earthResistanceTestResult.iD == 0){
            earthResistanceTestResult.assignByUserID = userPrefs.id().get();
            earthResistanceTestResult.userID = userPrefs.id().get();
        }


        MultiValueMap<String, Object> data = null;
        try {
            data = earthResistanceTestResult.parseToMultiValueMap();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, EarthResistanceTestResult.TestResistanceValuePic, testResistanceValueList, this);
        earthResistanceTestClient.update(data);
    }


    @Override
    boolean validate(){

        if(super.validate()) {

            this.earthResistanceTestResult.assignmentTime = etAssignmentTime.getText().toString();

            this.earthResistanceTestResult.taskNum = etTaskNum.getText().toString();

            this.earthResistanceTestResult.areaName = etAreaName.getSelectedItem().toString();

            this.earthResistanceTestResult.earthPlace = etEarthPlace.getText().toString();

            this.earthResistanceTestResult.earthEquipmentName = etEarthEquipmentName.getText().toString();

            this.earthResistanceTestResult.resistanceValue = etResistanceValue.getText().toString();

            this.earthResistanceTestResult.safetyMeasure = etSafetyMeasure.getSelectedItem().toString();

            this.earthResistanceTestResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.earthResistanceTestResult.wether = etWether.getSelectedItem().toString();

            this.earthResistanceTestResult.testDate = etTestDate.getText().toString();

            this.earthResistanceTestResult.tester = etTester.getText().toString();

            this.earthResistanceTestResult.endHandleTime = etEndHandleTime.getText().toString();

            this.earthResistanceTestResult.isHandled = etIsHandled.getSelectedItem().toString();

            this.earthResistanceTestResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}