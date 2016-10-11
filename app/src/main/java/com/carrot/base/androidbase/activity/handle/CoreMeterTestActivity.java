package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TaskUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
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
@EActivity(R.layout.activity_core_meter_test)
@OptionsMenu(R.menu.task_item)
public class CoreMeterTestActivity extends BaseHandlerActivity{


    CoreMeterTestResult coreMeterTestResult;

    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.et_area_name)
    Spinner etAreaName;
    @ViewById(R.id.et_protect_line)
    FormEditText etProtectLine;
    @ViewById(R.id.et_type)
    FormEditText etType;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.et_wether)
    Spinner etWether;
    @ViewById(R.id.et_test_way)
    Spinner etTestWay;
    @ViewById(R.id.btn_add_a_image)
    ImageView addAImage;
    @ViewById(R.id.btn_add_b_image)
    ImageView addBImage;
    @ViewById(R.id.btn_add_c_image)
    ImageView addCImage;
    @ViewById(R.id.aTestingPicContent)
    org.apmem.tools.layouts.FlowLayout aTestingPicContent;
    @ViewById(R.id.et_atesting)
    FormEditText aTesting;
    @ViewById(R.id.bTestingPicContent)
    org.apmem.tools.layouts.FlowLayout bTestingPicContent;
    @ViewById(R.id.et_btesting)
    FormEditText bTesting;
    @ViewById(R.id.cTestingPicContent)
    org.apmem.tools.layouts.FlowLayout cTestingPicContent;
    @ViewById(R.id.et_ctesting)
    FormEditText cTesting;
    @ViewById(R.id.et_test_result)
    Spinner etTestResult;
    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;
    @ViewById(R.id.handleContentll)
    LinearLayout handleContentll;
    @ViewById(R.id.et_tester)
    FormEditText etTester;
    @ViewById(R.id.et_testing_time)
    FormEditText etTestingTime;
    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;
    @ViewById(R.id.et_is_handled)
    Spinner etIsHandled;

    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    List<PhotoInfo> aTestingImgList = new ArrayList<>();
    List<PhotoInfo> bTestingImgList = new ArrayList<>();
    List<PhotoInfo> cTestingImgList = new ArrayList<>();


    @RestService
    CoreMeterTestClient coreMeterTestClient; //Inject it


    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_2_1, getApplicationContext(), getResources());

    }


    public void setValidateList(){

        allValidateFields = new FormEditText[] {etUnhandleReason, etHandleContent};


        addDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime, etBeginHandleTime};

        updateDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime, etBeginHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etProtectLine, etType,
                etSafetyMeasure, etEndTime, etBeginHandleTime, etHandleContent, etTester,
                etTestingTime, etEndHandleTime, etUnhandleReason,aTesting,bTesting,cTesting};

        updateDisabledSpinnerList = new Spinner[] {};
        finishDisabledSpinnerList = new Spinner[] {etWether, etTestWay, etTestResult, etIsHandled, etAreaName};

        imageAddButtonList = new ImageView[] {addAImage,addBImage,addCImage};

        openDateEditTextList = new OpenDateVo[] {
//                new OpenDateVo(etEndTime, 1),
                new OpenDateVo(etEndHandleTime, 10),
//                new OpenDateVo(etBeginHandleTime, 1),
                new OpenDateVo(etTestingTime, 10),
        };

        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(addAImage, aTestingImgList, aTestingPicContent),
                new ImageChooseVo(addBImage, bTestingImgList, bTestingPicContent),
                new ImageChooseVo(addCImage, cTestingImgList, cTestingPicContent)
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(etIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason}),
                new ShowWithSpinnerVo(etTestResult, handleContentll, "不合格", new FormEditText[]{etHandleContent})
        };

    }

    @Override
    public void setErrorHandler(){
        coreMeterTestClient.setRestErrorHandler(ssErrorHandler);
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etWether, TypeUtils.WEATHERS);

        setDropDownListAdapter(etTestWay, TypeUtils.TEST_WAY);

        setDropDownListAdapter(etTestResult, TypeUtils.TEST_RESULT);


        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);
        setDropDownListAdapter(etAreaName, DataInstance.getInstance().areaInformationResults);
    }

    @Background
    void getEntityFromServer(){
        coreMeterTestResult = coreMeterTestClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){

        if(coreMeterTestResult == null){

            coreMeterTestResult = new CoreMeterTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

            etTaskNum.setHint(TaskUtils.generatTaskNum());
            etEndTime.setText(DateUtils.getEndTime());

            etTester.setText(userPrefs.name().get());
            etTestingTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etBeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            etEndTime.setText(coreMeterTestResult.endTime);
            etTestingTime.setText(coreMeterTestResult.testingTime);
            etBeginHandleTime.setText(coreMeterTestResult.beginHandleTime);

            if(coreMeterTestResult.endHandleTime == null || coreMeterTestResult.endHandleTime.equals("")){

            }else{
                etEndHandleTime.setText(coreMeterTestResult.endHandleTime);
            }


            etTaskNum.setText(coreMeterTestResult.taskNum);
            etAreaName.setSelection(getSelectedAreaIndex(coreMeterTestResult.areaName));
            etProtectLine.setText(coreMeterTestResult.protectLine);
            etType.setText(coreMeterTestResult.type);
            etSafetyMeasure.setText(coreMeterTestResult.safetyMeasure);
            etWether.setSelection(TypeUtils.getSelectedIndex(TypeUtils.WEATHERS, coreMeterTestResult.wether));
            etTestWay.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TEST_WAY, coreMeterTestResult.testWay));
            etTestResult.setSelection(TypeUtils.getSelectedIndex(TypeUtils.CHECK_TYPE, coreMeterTestResult.testResult));
            etHandleContent.setText(coreMeterTestResult.handleContent);

            if(coreMeterTestResult.tester == null || coreMeterTestResult.tester.equals("")){
                etTester.setText(userPrefs.name().get());
            }else{
                etTester.setText(coreMeterTestResult.tester);
            }

            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, coreMeterTestResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(coreMeterTestResult.unhandleReason);

            aTesting.setText(coreMeterTestResult.aTesting);
            bTesting.setText(coreMeterTestResult.bTesting);
            cTesting.setText(coreMeterTestResult.cTesting);
            getImage();

            this.saveStatus = 1;
        }
        if(progress != null){
            progress.dismiss();
        }
    }


    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(coreMeterTestResult.aTestingPic, aTestingPicContent);
        super.getImageFromURL(coreMeterTestResult.bTestingPic, bTestingPicContent);
        super.getImageFromURL(coreMeterTestResult.cTestingPic, cTestingPicContent);

    }

    /**
     * 新增
     */
    @Override
    UpdateResult save(){

        if(coreMeterTestResult.id == 0){
            coreMeterTestResult.assignByUserID = userPrefs.id().get();
            coreMeterTestResult.userId = userPrefs.id().get();
            coreMeterTestResult.createdTime = DateUtils.getCurrentYYYY_MM_DD();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = coreMeterTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, CoreMeterTestResult.ATestingPic, aTestingImgList, this);
        FileUtils.addImageToData(data, CoreMeterTestResult.BTestingPic, bTestingImgList, this);
        FileUtils.addImageToData(data, CoreMeterTestResult.CTestingPic, cTestingImgList, this);

        return coreMeterTestClient.update(data);
    }


    @Override
    boolean validate(){

        if(super.validate()) {

            this.coreMeterTestResult.taskNum = etTaskNum.getText().toString();

            this.coreMeterTestResult.areaName = etAreaName.getSelectedItem().toString();
            this.coreMeterTestResult.areaID = ((AreaInformationResult)etAreaName.getSelectedItem()).id;

            this.coreMeterTestResult.protectLine = etProtectLine.getText().toString();

            this.coreMeterTestResult.type = etType.getText().toString();

            this.coreMeterTestResult.safetyMeasure = etSafetyMeasure.getText().toString();

            this.coreMeterTestResult.endTime = etEndTime.getText().toString();

            this.coreMeterTestResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.coreMeterTestResult.wether = etWether.getSelectedItem().toString();

            this.coreMeterTestResult.testWay = etTestWay.getSelectedItem().toString();

            this.coreMeterTestResult.testResult = etTestResult.getSelectedItem().toString();

            this.coreMeterTestResult.handleContent = etHandleContent.getText().toString();

            this.coreMeterTestResult.tester = etTester.getText().toString();

            this.coreMeterTestResult.testingTime = etTestingTime.getText().toString();

            this.coreMeterTestResult.endHandleTime = etEndHandleTime.getText().toString();

            this.coreMeterTestResult.isHandled = etIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.coreMeterTestResult.assignmentTime = etAssignmentTime.getText().toString();

            this.coreMeterTestResult.aTesting = aTesting.getText().toString();
            this.coreMeterTestResult.bTesting = bTesting.getText().toString();
            this.coreMeterTestResult.cTesting = cTesting.getText().toString();

            if(this.coreMeterTestResult.isHandled == 1){//已处理
                this.coreMeterTestResult.unhandleReason = "";

            }else if(this.coreMeterTestResult.isHandled == 2) {//未处理
                this.coreMeterTestResult.unhandleReason = etUnhandleReason.getText().toString();

            }

            return true;
        }{
            return false;
        }
    }

}
