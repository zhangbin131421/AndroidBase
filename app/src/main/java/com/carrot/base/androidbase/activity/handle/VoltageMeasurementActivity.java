package com.carrot.base.androidbase.activity.handle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.VoltageMeasurementClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.VoltageMeasurementResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.annotations.AfterTextChange;
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
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_voltage_measurement)
@OptionsMenu(R.menu.task_item)
public class VoltageMeasurementActivity extends BaseHandlerActivity{



    List<PhotoInfo> currentAPicList = new ArrayList<>();
    List<PhotoInfo> currentBPicList = new ArrayList<>();
    List<PhotoInfo> currentCPicList = new ArrayList<>();
    List<PhotoInfo> zeoLineCurrentPicList = new ArrayList<>();
    List<PhotoInfo> headerVoltagePicList = new ArrayList<>();
    List<PhotoInfo> footerVoltagePicList = new ArrayList<>();


    VoltageMeasurementResult voltageMeasurementResult;

    @RestService
    VoltageMeasurementClient voltageMeasurementClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_config_a)
    FormEditText etConfigA;
    @ViewById(R.id.et_config_b)
    FormEditText etConfigB;
    @ViewById(R.id.et_config_c)
    FormEditText etConfigC;
    @ViewById(R.id.et_rated_current)
    FormEditText etRatedCurrent;
    @ViewById(R.id.et_power_householder)
    FormEditText etPowerHouseholder;
    @ViewById(R.id.et_power_capacity)
    FormEditText etPowerCapacity;
    @ViewById(R.id.et_householder)
    FormEditText etHouseholder;
    @ViewById(R.id.et_householder_capacity)
    FormEditText etHouseholderCapacity;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.spn_period)
    Spinner spnPeriod;

    @ViewById(R.id.ll_current_a)
    org.apmem.tools.layouts.FlowLayout llCurrentA;


    @ViewById(R.id.btn_add_image_current_a)
    ImageView btnAddImageCurrentA;

    @ViewById(R.id.et_current_a)
    FormEditText etCurrentA;


    @ViewById(R.id.ll_current_b)
    org.apmem.tools.layouts.FlowLayout llCurrentB;


    @ViewById(R.id.btn_add_image_current_b)
    ImageView btnAddImageCurrentB;

    @ViewById(R.id.et_current_b)
    FormEditText etCurrentB;


    @ViewById(R.id.ll_current_c)
    org.apmem.tools.layouts.FlowLayout llCurrentC;


    @ViewById(R.id.btn_add_image_current_c)
    ImageView btnAddImageCurrentC;

    @ViewById(R.id.et_current_c)
    FormEditText etCurrentC;


    @ViewById(R.id.ll_zeo_line_current)
    org.apmem.tools.layouts.FlowLayout llZeoLineCurrent;


    @ViewById(R.id.btn_add_image_zeo_line_current)
    ImageView btnAddImageZeoLineCurrent;

    @ViewById(R.id.et_zeo_line_current)
    FormEditText etZeoLineCurrent;

    @ViewById(R.id.et_load_rate)
    FormEditText etLoadRate;
    @ViewById(R.id.et_imbalance_rate)
    FormEditText etImbalanceRate;

    @ViewById(R.id.ll_header_voltage)
    org.apmem.tools.layouts.FlowLayout llHeaderVoltage;


    @ViewById(R.id.btn_add_image_header_voltage)
    ImageView btnAddImageHeaderVoltage;

    @ViewById(R.id.et_header_voltage)
    FormEditText etHeaderVoltage;


    @ViewById(R.id.ll_footer_voltage)
    org.apmem.tools.layouts.FlowLayout llFooterVoltage;


    @ViewById(R.id.btn_add_image_footer_voltage)
    ImageView btnAddImageFooterVoltage;

    @ViewById(R.id.et_footer_voltage)
    FormEditText etFooterVoltage;

    @ViewById(R.id.spn_is_out_of_limit)
    Spinner spnIsOutOfLimit;
    @ViewById(R.id.et_modification_opinion)
    FormEditText etModificationOpinion;
    @ViewById(R.id.et_test_time)
    FormEditText etTestTime;
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
        super.afterInitView(TypeUtils.TYPE_2_6, getApplicationContext(), getResources());

    }

    @AfterTextChange({R.id.et_current_a,R.id.et_current_b,R.id.et_current_c})
    void afterTextChangedOnHelloTextView() {
        if(etCurrentA.getText() == null || etCurrentB.getText() == null || etCurrentC.getText() == null ||
                etCurrentA.getText().toString().equals("") || etCurrentB.getText().toString().equals("") || etCurrentC.getText().toString().equals("")){
            return;
        }

        try{

            double a = Double.parseDouble(etCurrentA.getText().toString());
            double b = Double.parseDouble(etCurrentB.getText().toString());
            double c = Double.parseDouble(etCurrentC.getText().toString());

            double max = -1;
            double min = 9999999;
            if(a > b){
                max = a > c ? a : c;
                min = b > c ? c : b;
            }else{
                max = b > c ? b : c;
                min = a > c ? c : a;
            }

//            负载率（%）	公式：（1+2+3）*220/1000/4*100
//            三相不平衡率（%）	公式：（最大相电流—最小相电流）/最大相电流*100

            double loadRate = (a+b+c)*220*100/1000/4;
            double imbalanceRate = (max-min)*10000/max;

            Log.i("sslog", max+", " + min + ", " + loadRate + ", " + imbalanceRate);
            etLoadRate.setText(String.format("%.2f", loadRate/100));
            etImbalanceRate.setText(String.format("%.2f", imbalanceRate/100));
        }catch (Exception e){
            alert("错误", "请输入数字", SweetAlertDialog.ERROR_TYPE, null);
        }

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason, etHouseholder, etPowerHouseholder};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etConfigA,etConfigB,etConfigC,etRatedCurrent,etPowerHouseholder,etEndHandleTime,
                etPowerCapacity,etHouseholder,etHouseholderCapacity,etEndTime,etLoadRate,etImbalanceRate};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etConfigA,etConfigB,etConfigC,etRatedCurrent,etPowerHouseholder,etPowerCapacity,etHouseholder,etHouseholderCapacity,etSafetyMeasure,etEndTime,etBeginHandleTime,etCurrentA,etCurrentB,etCurrentC,etZeoLineCurrent,etLoadRate,etImbalanceRate,etHeaderVoltage,etFooterVoltage,etModificationOpinion,etTestTime,etTester,etEndHandleTime,etUnhandleReason,};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnPeriod,spnIsOutOfLimit,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
//            new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
            new OpenDateVo(etTestTime, OpenDateVo.UPDATE),
//            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {btnAddImageCurrentA,btnAddImageCurrentB,btnAddImageCurrentC,btnAddImageZeoLineCurrent,btnAddImageHeaderVoltage,btnAddImageFooterVoltage,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageCurrentA, currentAPicList, llCurrentA),

                new ImageChooseVo(btnAddImageCurrentB, currentBPicList, llCurrentB),

                new ImageChooseVo(btnAddImageCurrentC, currentCPicList, llCurrentC),

                new ImageChooseVo(btnAddImageZeoLineCurrent, zeoLineCurrentPicList, llZeoLineCurrent),

                new ImageChooseVo(btnAddImageHeaderVoltage, headerVoltagePicList, llHeaderVoltage),

                new ImageChooseVo(btnAddImageFooterVoltage, footerVoltagePicList, llFooterVoltage),
        };

    }

    @Override
    public void setErrorHandler(){
        voltageMeasurementClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnPeriod, TypeUtils.TIME_PERIOD);
        setDropDownListAdapter(spnIsOutOfLimit, TypeUtils.OUT_LIMIT);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        voltageMeasurementResult = voltageMeasurementClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(voltageMeasurementResult == null){

            voltageMeasurementResult = new VoltageMeasurementResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndTime.setText(DateUtils.getEndTime());

        }else{

            etAssignmentTime.setText(getYYYYMMDD(voltageMeasurementResult.assignmentTime));
            etTaskNum.setText(voltageMeasurementResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(voltageMeasurementResult.areaID));
            etConfigA.setText(voltageMeasurementResult.configA);
            etConfigB.setText(voltageMeasurementResult.configB);
            etConfigC.setText(voltageMeasurementResult.configC);
            etRatedCurrent.setText(voltageMeasurementResult.ratedCurrent);
            etPowerHouseholder.setText(voltageMeasurementResult.powerHouseholder);
            etPowerCapacity.setText(voltageMeasurementResult.powerCapacity);
            etHouseholder.setText(voltageMeasurementResult.householder);
            etHouseholderCapacity.setText(voltageMeasurementResult.householderCapacity);
            etSafetyMeasure.setText(voltageMeasurementResult.safetyMeasure);
            etEndTime.setText(getYYYYMMDD(voltageMeasurementResult.endTime));
            etBeginHandleTime.setText(getYYYYMMDD(voltageMeasurementResult.beginHandleTime));
            spnPeriod.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TIME_PERIOD, voltageMeasurementResult.period));
            etCurrentA.setText(voltageMeasurementResult.currentA);
            etCurrentB.setText(voltageMeasurementResult.currentB);
            etCurrentC.setText(voltageMeasurementResult.currentC);
            etZeoLineCurrent.setText(voltageMeasurementResult.zeoLineCurrent);
            etLoadRate.setText(voltageMeasurementResult.loadRate);
            etImbalanceRate.setText(voltageMeasurementResult.imbalanceRate);
            etHeaderVoltage.setText(voltageMeasurementResult.headerVoltage);
            etFooterVoltage.setText(voltageMeasurementResult.footerVoltage);
            spnIsOutOfLimit.setSelection(TypeUtils.getSelectedIndex(TypeUtils.OUT_LIMIT, voltageMeasurementResult.isOutOfLimit));
            etModificationOpinion.setText(voltageMeasurementResult.modificationOpinion);
            etTestTime.setText(getYYYYMMDD(voltageMeasurementResult.testTime));
            etTester.setText(voltageMeasurementResult.tester);
            etEndHandleTime.setText(getYYYYMMDD(voltageMeasurementResult.endHandleTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, voltageMeasurementResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(voltageMeasurementResult.unhandleReason);

            getImage();
        }

        if(voltageMeasurementResult.tester == null || voltageMeasurementResult.tester.equals("")){
            etTester.setText(voltageMeasurementResult.tester);
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(voltageMeasurementResult.currentAPic, llCurrentA);
        super.getImageFromURL(voltageMeasurementResult.currentBPic, llCurrentB);
        super.getImageFromURL(voltageMeasurementResult.currentCPic, llCurrentC);
        super.getImageFromURL(voltageMeasurementResult.zeoLineCurrentPic, llZeoLineCurrent);
        super.getImageFromURL(voltageMeasurementResult.headerVoltagePic, llHeaderVoltage);
        super.getImageFromURL(voltageMeasurementResult.footerVoltagePic, llFooterVoltage);

    }

    UpdateResult save(){

        if(voltageMeasurementResult.iD == 0){
            voltageMeasurementResult.assignByUserID = userPrefs.id().get();
            voltageMeasurementResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = voltageMeasurementResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentAPic, currentAPicList, this);

        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentBPic, currentBPicList, this);

        FileUtils.addImageToData(data, VoltageMeasurementResult.CurrentCPic, currentCPicList, this);

        FileUtils.addImageToData(data, VoltageMeasurementResult.ZeoLineCurrentPic, zeoLineCurrentPicList, this);

        FileUtils.addImageToData(data, VoltageMeasurementResult.HeaderVoltagePic, headerVoltagePicList, this);

        FileUtils.addImageToData(data, VoltageMeasurementResult.FooterVoltagePic, footerVoltagePicList, this);

        return voltageMeasurementClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.voltageMeasurementResult.assignmentTime = etAssignmentTime.getText().toString();
            this.voltageMeasurementResult.taskNum = etTaskNum.getText().toString();
            this.voltageMeasurementResult.areaName = spnAreaName.getSelectedItem().toString();

            this.voltageMeasurementResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.voltageMeasurementResult.configA = etConfigA.getText().toString();
            this.voltageMeasurementResult.configB = etConfigB.getText().toString();
            this.voltageMeasurementResult.configC = etConfigC.getText().toString();
            this.voltageMeasurementResult.ratedCurrent = etRatedCurrent.getText().toString();
            this.voltageMeasurementResult.powerHouseholder = etPowerHouseholder.getText().toString();
            this.voltageMeasurementResult.powerCapacity = etPowerCapacity.getText().toString();
            this.voltageMeasurementResult.householder = etHouseholder.getText().toString();
            this.voltageMeasurementResult.householderCapacity = etHouseholderCapacity.getText().toString();
            this.voltageMeasurementResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.voltageMeasurementResult.endTime = etEndTime.getText().toString();
            this.voltageMeasurementResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.voltageMeasurementResult.period = spnPeriod.getSelectedItem().toString();

            this.voltageMeasurementResult.currentA = etCurrentA.getText().toString();
            this.voltageMeasurementResult.currentB = etCurrentB.getText().toString();
            this.voltageMeasurementResult.currentC = etCurrentC.getText().toString();
            this.voltageMeasurementResult.zeoLineCurrent = etZeoLineCurrent.getText().toString();
            this.voltageMeasurementResult.loadRate = etLoadRate.getText().toString();
            this.voltageMeasurementResult.imbalanceRate = etImbalanceRate.getText().toString();
            this.voltageMeasurementResult.headerVoltage = etHeaderVoltage.getText().toString();
            this.voltageMeasurementResult.footerVoltage = etFooterVoltage.getText().toString();
            this.voltageMeasurementResult.isOutOfLimit = spnIsOutOfLimit.getSelectedItem().toString();

            this.voltageMeasurementResult.modificationOpinion = etModificationOpinion.getText().toString();
            this.voltageMeasurementResult.testTime = etTestTime.getText().toString();
            this.voltageMeasurementResult.tester = etTester.getText().toString();
            this.voltageMeasurementResult.endHandleTime = etEndHandleTime.getText().toString();
            this.voltageMeasurementResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.voltageMeasurementResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
