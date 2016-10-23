package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.TotalPerformanceTestClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.TotalPerformanceTestResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
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
    FormEditText etProtectLine;
    @ViewById(R.id.etType)
    FormEditText etType;
    @ViewById(R.id.etSafetyMeasure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.etEndTime)
    FormEditText etEndTime;
    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.etElectricityA)
    FormEditText etElectricityA;
    @ViewById(R.id.etElectricityB)
    FormEditText etElectricityB;
    @ViewById(R.id.etElectricityC)
    FormEditText etElectricityC;
    @ViewById(R.id.etElectricityD)
    FormEditText etElectricityD;
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
    Spinner etIsHandled;
    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.btn_add_b_image)
    ImageView imageAddB;
    @ViewById(R.id.etElectricityBPic)
    org.apmem.tools.layouts.FlowLayout etElectricityBPicLL;

    @ViewById(R.id.btn_add_d_image)
    ImageView imageAddD;
    @ViewById(R.id.etElectricityDPic)
    org.apmem.tools.layouts.FlowLayout etElectricityDPicLL;

    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;

    @ViewById(R.id.llTestResult)
    LinearLayout llTestResult;

    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_2_2, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etSafetyMeasure, etType, etProtectLine, etAssignmentTime,
                etEndTime, etElectricityA,etElectricityB,etElectricityC,etElectricityD,etOperateTime,
                etTestTime,etHandleContent, etTester,etEndHandleTime,etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etBeginHandleTime, etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etBeginHandleTime, etSafetyMeasure,
                etProtectLine,etType,etEndTime,etEndHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etEndTime,etBeginHandleTime,etProtectLine,
                etElectricityA,etElectricityC,etOperateTime,etTestTime,etHandleContent, etSafetyMeasure,etType,
                etTester,etEndHandleTime,etUnhandleReason,etElectricityB,etElectricityD};

        updateDisabledSpinnerList = new Spinner[] {etAreaName};
        finishDisabledSpinnerList = new Spinner[] {etIsHandled, etTestResult,etAreaName};

        imageAddButtonList = new ImageView[] {imageAddB, imageAddD};

        openDateEditTextList = new OpenDateVo[] {
//                new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
                new OpenDateVo(etOperateTime, OpenDateVo.UPDATE),
//                new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
                new OpenDateVo(etTestTime, OpenDateVo.UPDATE)
        };

        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(imageAddB, electricityBPicList, etElectricityBPicLL),
                new ImageChooseVo(imageAddD, electricityDPicList, etElectricityDPicLL)
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(etTestResult, llTestResult, "不合格", new FormEditText[]{etHandleContent}),
                new ShowWithSpinnerVo(etIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };
    }


    @Override
    public void setErrorHandler(){
        totalPerformanceTestClient.setRestErrorHandler(ssErrorHandler);
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, DataInstance.getInstance().areaInformationResults);

//        setDropDownListAdapter(etProtectLine, TypeUtils.PRODUCTION_LINE);
//
//        setDropDownListAdapter(etType, TypeUtils.TYPE_TPT);
//
//        setDropDownListAdapter(etSafetyMeasure, TypeUtils.SAFETY_MEASURE);

        setDropDownListAdapter(etTestResult, TypeUtils.TEST_RESULT);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);
    }



    @Background
    void getEntityFromServer(){
        totalPerformanceTestResult = totalPerformanceTestClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(totalPerformanceTestResult == null){

            totalPerformanceTestResult = new TotalPerformanceTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

            etEndTime.setText(DateUtils.getEndTime());

            etTester.setText(userPrefs.name().get());
        }else{
            etAssignmentTime.setText(getYYYYMMDD(totalPerformanceTestResult.assignmentTime));
            etTaskNum.setText(totalPerformanceTestResult.taskNum);
            etAreaName.setSelection(getSelectedAreaIndexByID(totalPerformanceTestResult.areaID));
            etProtectLine.setText(totalPerformanceTestResult.protectLine);
            etType.setText(totalPerformanceTestResult.type);
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.SAFETY_MEASURE, totalPerformanceTestResult.safetyMeasure));
            etEndTime.setText(getYYYYMMDD(totalPerformanceTestResult.endTime));

            etBeginHandleTime.setText(getYYYYMMDD(totalPerformanceTestResult.beginHandleTime));
            etElectricityA.setText(totalPerformanceTestResult.electricityA);
            etElectricityB.setText(totalPerformanceTestResult.electricityB);
            etElectricityC.setText(totalPerformanceTestResult.electricityC);
            etElectricityD.setText(totalPerformanceTestResult.electricityD);
            etOperateTime.setText(getYYYYMMDD(totalPerformanceTestResult.operateTime));
            etTestTime.setText(getYYYYMMDD(totalPerformanceTestResult.testTime));
            etTestResult.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TEST_RESULT, totalPerformanceTestResult.testResult));
            etHandleContent.setText(totalPerformanceTestResult.handleContent);
            etTester.setText(totalPerformanceTestResult.tester);

            etEndHandleTime.setText(getYYYYMMDD(totalPerformanceTestResult.endHandleTime));
            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, totalPerformanceTestResult.isHandled == 2 ? "未处理" : "已处理"));

            etUnhandleReason.setText(totalPerformanceTestResult.unhandleReason);
            getImage();

            this.saveStatus = 1;
        }

        etEndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        if(totalPerformanceTestResult.tester == null || totalPerformanceTestResult.tester.equals("")){
            etTester.setText(userPrefs.name().get());
        }
    }


    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(totalPerformanceTestResult.electricityBPic, etElectricityBPicLL);
        super.getImageFromURL(totalPerformanceTestResult.electricityDPic, etElectricityDPicLL);

    }

    /**
     * 新增
     */
    @Override
    UpdateResult save(){

        if(totalPerformanceTestResult.id == 0){
            totalPerformanceTestResult.assignByUserID = userPrefs.id().get();
            totalPerformanceTestResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = totalPerformanceTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, TotalPerformanceTestResult.ElectricityBPic, electricityBPicList, this);
        FileUtils.addImageToData(data, TotalPerformanceTestResult.ElectricityDPic, electricityDPicList, this);

        return totalPerformanceTestClient.update(data);
    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.totalPerformanceTestResult.assignmentTime = etAssignmentTime.getText().toString();
            this.totalPerformanceTestResult.taskNum = etTaskNum.getText().toString();
            this.totalPerformanceTestResult.areaName = etAreaName.getSelectedItem().toString();
            this.totalPerformanceTestResult.areaID = ((AreaInformationResult)etAreaName.getSelectedItem()).id;
            this.totalPerformanceTestResult.protectLine = etProtectLine.getText().toString();
            this.totalPerformanceTestResult.type = etType.getText().toString();
            this.totalPerformanceTestResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.totalPerformanceTestResult.endTime = etEndTime.getText().toString();
            this.totalPerformanceTestResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.totalPerformanceTestResult.electricityA = etElectricityA.getText().toString();
            this.totalPerformanceTestResult.electricityB = etElectricityB.getText().toString();
            this.totalPerformanceTestResult.electricityC = etElectricityC.getText().toString();
            this.totalPerformanceTestResult.electricityD = etElectricityD.getText().toString();
            this.totalPerformanceTestResult.operateTime = etOperateTime.getText().toString();
            this.totalPerformanceTestResult.testTime = etTestTime.getText().toString();
            this.totalPerformanceTestResult.testResult = etTestResult.getSelectedItem().toString();
            this.totalPerformanceTestResult.handleContent = etHandleContent.getText().toString();
            this.totalPerformanceTestResult.tester = etTester.getText().toString();
            this.totalPerformanceTestResult.endHandleTime = etEndHandleTime.getText().toString();
            this.totalPerformanceTestResult.isHandled = etIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;
            this.totalPerformanceTestResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
