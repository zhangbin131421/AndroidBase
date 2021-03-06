package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.CrossTestClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CrossTestResult;
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
@EActivity(R.layout.activity_cross_test)
@OptionsMenu(R.menu.task_item)
public class CrossTestActivity extends BaseHandlerActivity{



    List<PhotoInfo> earthDistancePicList = new ArrayList<>();
    List<PhotoInfo> crossDistancePicList = new ArrayList<>();


    CrossTestResult crossTestResult;

    @RestService
    CrossTestClient crossTestClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_cross_point)
    FormEditText etCrossPoint;
    @ViewById(R.id.et_cross_name)
    FormEditText etCrossName;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.ll_earth_distance)
    org.apmem.tools.layouts.FlowLayout llEarthDistance;


    @ViewById(R.id.btn_add_image_earth_distance)
    ImageView btnAddImageEarthDistance;

    @ViewById(R.id.et_earth_distance)
    FormEditText etEarthDistance;


    @ViewById(R.id.ll_cross_distance)
    org.apmem.tools.layouts.FlowLayout llCrossDistance;


    @ViewById(R.id.btn_add_image_cross_distance)
    ImageView btnAddImageCrossDistance;

    @ViewById(R.id.et_cross_distance)
    FormEditText etCrossDistance;

    @ViewById(R.id.spn_is_qualified)
    Spinner spnIsQualified;
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

    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;


    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_2_5, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etCrossPoint,etCrossName,etEndHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etCrossPoint,
                                etCrossName, etSafetyMeasure, etCrossDistance, etEarthDistance, etModificationOpinion,
                                etTestTime, etTester, etEndHandleTime, etUnhandleReason};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName, spnIsHandled, spnIsQualified};

        openDateEditTextList = new OpenDateVo[] {
                new OpenDateVo(etTestTime, OpenDateVo.UPDATE),
                new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE)
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };


        imageAddButtonList = new ImageView[] {btnAddImageEarthDistance,btnAddImageCrossDistance,};

        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
            new ImageChooseVo(btnAddImageEarthDistance, earthDistancePicList, llEarthDistance),

            new ImageChooseVo(btnAddImageCrossDistance, crossDistancePicList, llCrossDistance),
        };

    }

    @Override
    public void setErrorHandler(){
        crossTestClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnIsQualified, TypeUtils.QUALIFIED);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        crossTestResult = crossTestClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(crossTestResult == null){

            crossTestResult = new CrossTestResult();
            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndHandleTime.setText(DateUtils.getEndTime());


        }else{

            etAssignmentTime.setText(getYYYYMMDD(crossTestResult.assignmentTime));
            etTaskNum.setText(crossTestResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(crossTestResult.areaID));
            etCrossPoint.setText(crossTestResult.crossPoint);
            etCrossName.setText(crossTestResult.crossName);
            etSafetyMeasure.setText(crossTestResult.safetyMeasure);
            etBeginHandleTime.setText(getYYYYMMDD(crossTestResult.beginHandleTime));
            etEarthDistance.setText(crossTestResult.earthDistance);
            etCrossDistance.setText(crossTestResult.crossDistance);
            spnIsQualified.setSelection(TypeUtils.getSelectedIndex(TypeUtils.QUALIFIED, crossTestResult.isQualified));
            etModificationOpinion.setText(crossTestResult.modificationOpinion);
            etTestTime.setText(getYYYYMMDD(crossTestResult.testTime));
            etTester.setText(crossTestResult.tester);
            etEndHandleTime.setText(getYYYYMMDD(crossTestResult.endHandleTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, crossTestResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(crossTestResult.unhandleReason);

            getImage();
        }

        if(crossTestResult.tester == null || crossTestResult.tester.equals("")){
            etTester.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(crossTestResult.earthDistancePic, llEarthDistance);
        super.getImageFromURL(crossTestResult.crossDistancePic, llCrossDistance);

    }

    UpdateResult save(){

        if(crossTestResult.id == 0){
            crossTestResult.assignByUserID = userPrefs.id().get();
            crossTestResult.userId = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = crossTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, CrossTestResult.EarthDistancePic, earthDistancePicList, this);

        FileUtils.addImageToData(data, CrossTestResult.CrossDistancePic, crossDistancePicList, this);

        return crossTestClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.crossTestResult.assignmentTime = etAssignmentTime.getText().toString();
            this.crossTestResult.taskNum = etTaskNum.getText().toString();
            this.crossTestResult.areaName = spnAreaName.getSelectedItem().toString();
            this.crossTestResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;

            this.crossTestResult.crossPoint = etCrossPoint.getText().toString();
            this.crossTestResult.crossName = etCrossName.getText().toString();
            this.crossTestResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.crossTestResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.crossTestResult.earthDistance = etEarthDistance.getText().toString();
            this.crossTestResult.crossDistance = etCrossDistance.getText().toString();
            this.crossTestResult.isQualified = spnIsQualified.getSelectedItem().toString();

            this.crossTestResult.modificationOpinion = etModificationOpinion.getText().toString();
            this.crossTestResult.testTime = etTestTime.getText().toString();
            this.crossTestResult.tester = etTester.getText().toString();
            this.crossTestResult.endHandleTime = etEndHandleTime.getText().toString();
            this.crossTestResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.crossTestResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
