package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EarthResistanceTestClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.EarthResistanceTestResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
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
@EActivity(R.layout.activity_earth_resistance_test)
@OptionsMenu(R.menu.task_item)
public class EarthResistanceTestActivity extends BaseHandlerActivity{



    List<PhotoInfo> testResistanceValuePicList = new ArrayList<>();


    EarthResistanceTestResult earthResistanceTestResult;

    @RestService
    EarthResistanceTestClient earthResistanceTestClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_earth_place)
    FormEditText etEarthPlace;
    @ViewById(R.id.et_earth_equipment_name)
    FormEditText etEarthEquipmentName;
    @ViewById(R.id.et_resistance_value)
    FormEditText etResistanceValue;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.spn_wether)
    Spinner spnWether;

    @ViewById(R.id.ll_test_resistance_value)
    org.apmem.tools.layouts.FlowLayout llTestResistanceValue;


    @ViewById(R.id.btn_add_image_test_resistance_value)
    ImageView btnAddImageTestResistanceValue;

    @ViewById(R.id.et_test_resistance_value)
    FormEditText etTestResistanceValue;

    @ViewById(R.id.et_test_date)
    FormEditText etTestDate;
    @ViewById(R.id.et_tester)
    FormEditText etTester;
    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;


    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_2_7, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etEarthPlace,etEarthEquipmentName,etResistanceValue,etEndHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etEarthPlace,etEarthEquipmentName,etResistanceValue,etSafetyMeasure,etBeginHandleTime,etTestResistanceValue,etTestDate,etTester,etEndHandleTime,etUnhandleReason,};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnWether,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etBeginHandleTime, OpenDateVo.UPDATE),
            new OpenDateVo(etTestDate, OpenDateVo.UPDATE),
            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {btnAddImageTestResistanceValue,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageTestResistanceValue, testResistanceValuePicList, llTestResistanceValue),
        };

    }

    @Override
    public void setErrorHandler(){
        earthResistanceTestClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnWether, TypeUtils.WEATHERS);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        earthResistanceTestResult = earthResistanceTestClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(earthResistanceTestResult == null){

            earthResistanceTestResult = new EarthResistanceTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(earthResistanceTestResult.assignmentTime);
            etTaskNum.setText(earthResistanceTestResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndex(earthResistanceTestResult.areaName));
            etEarthPlace.setText(earthResistanceTestResult.earthPlace);
            etEarthEquipmentName.setText(earthResistanceTestResult.earthEquipmentName);
            etResistanceValue.setText(earthResistanceTestResult.resistanceValue);
            etSafetyMeasure.setText(earthResistanceTestResult.safetyMeasure);
            etBeginHandleTime.setText(earthResistanceTestResult.beginHandleTime);
            spnWether.setSelection(TypeUtils.getSelectedIndex(TypeUtils.WEATHERS, earthResistanceTestResult.wether));
            etTestResistanceValue.setText(earthResistanceTestResult.testResistanceValue);
            etTestDate.setText(earthResistanceTestResult.testDate);
            etTester.setText(earthResistanceTestResult.tester);
            etEndHandleTime.setText(earthResistanceTestResult.endHandleTime);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, earthResistanceTestResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(earthResistanceTestResult.unhandleReason);

            getImage();
        }

        if(earthResistanceTestResult.tester == null || earthResistanceTestResult.tester.equals("")){
            etTester.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(earthResistanceTestResult.testResistanceValuePic, llTestResistanceValue);

    }

    UpdateResult save(){

        if(earthResistanceTestResult.iD == 0){
            earthResistanceTestResult.assignByUserID = userPrefs.id().get();
            earthResistanceTestResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = earthResistanceTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, EarthResistanceTestResult.TestResistanceValuePic, testResistanceValuePicList, this);

        return earthResistanceTestClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.earthResistanceTestResult.assignmentTime = etAssignmentTime.getText().toString();
            this.earthResistanceTestResult.taskNum = etTaskNum.getText().toString();
            this.earthResistanceTestResult.areaName = spnAreaName.getSelectedItem().toString();

            this.earthResistanceTestResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.earthResistanceTestResult.earthPlace = etEarthPlace.getText().toString();
            this.earthResistanceTestResult.earthEquipmentName = etEarthEquipmentName.getText().toString();
            this.earthResistanceTestResult.resistanceValue = etResistanceValue.getText().toString();
            this.earthResistanceTestResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.earthResistanceTestResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.earthResistanceTestResult.wether = spnWether.getSelectedItem().toString();

            this.earthResistanceTestResult.testResistanceValue = etTestResistanceValue.getText().toString();
            this.earthResistanceTestResult.testDate = etTestDate.getText().toString();
            this.earthResistanceTestResult.tester = etTester.getText().toString();
            this.earthResistanceTestResult.endHandleTime = etEndHandleTime.getText().toString();
            this.earthResistanceTestResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.earthResistanceTestResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
