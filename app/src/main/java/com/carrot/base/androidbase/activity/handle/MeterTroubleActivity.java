package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.MeterTroubleClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.MeterTroubleResult;
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
@EActivity(R.layout.activity_meter_trouble)
@OptionsMenu(R.menu.task_item)
public class MeterTroubleActivity extends BaseHandlerActivity{



    List<PhotoInfo> troubleReasonPicList = new ArrayList<>();
    List<PhotoInfo> handleContentPicList = new ArrayList<>();


    MeterTroubleResult meterTroubleResult;

    @RestService
    MeterTroubleClient meterTroubleClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_household_num)
    FormEditText etHouseholdNum;
    @ViewById(R.id.et_meter_num)
    FormEditText etMeterNum;
    @ViewById(R.id.et_trouble_address)
    FormEditText etTroubleAddress;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.ll_trouble_reason)
    org.apmem.tools.layouts.FlowLayout llTroubleReason;


    @ViewById(R.id.btn_add_image_trouble_reason)
    ImageView btnAddImageTroubleReason;

    @ViewById(R.id.et_trouble_reason)
    FormEditText etTroubleReason;


    @ViewById(R.id.ll_handle_content)
    org.apmem.tools.layouts.FlowLayout llHandleContent;


    @ViewById(R.id.btn_add_image_handle_content)
    ImageView btnAddImageHandleContent;

    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;

    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_worker)
    FormEditText etWorker;

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_4, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etHouseholdNum,etMeterNum,etTroubleAddress,etEndTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etHouseholdNum,
                etMeterNum,etTroubleAddress,etSafetyMeasure,etBeginHandleTime,etTroubleReason,
                etHandleContent,etEndHandleTime,etUnhandleReason,
                etEndTime, etWorker};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
                new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {btnAddImageTroubleReason,btnAddImageHandleContent,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageTroubleReason, troubleReasonPicList, llTroubleReason),

                new ImageChooseVo(btnAddImageHandleContent, handleContentPicList, llHandleContent),
        };

    }

    @Override
    public void setErrorHandler(){
        meterTroubleClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        meterTroubleResult = meterTroubleClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(meterTroubleResult == null){

            meterTroubleResult = new MeterTroubleResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(meterTroubleResult.assignmentTime);
            etTaskNum.setText(meterTroubleResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(meterTroubleResult.areaID));
            etHouseholdNum.setText(meterTroubleResult.householdNum);
            etMeterNum.setText(meterTroubleResult.meterNum);
            etTroubleAddress.setText(meterTroubleResult.troubleAddress);
            etSafetyMeasure.setText(meterTroubleResult.safetyMeasure);
            etBeginHandleTime.setText(meterTroubleResult.beginHandleTime);
            etTroubleReason.setText(meterTroubleResult.troubleReason);
            etHandleContent.setText(meterTroubleResult.handleContent);
            etEndHandleTime.setText(meterTroubleResult.endHandleTime);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, meterTroubleResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(meterTroubleResult.unhandleReason);

            etEndTime.setText(meterTroubleResult.endTime);
            etWorker.setText(meterTroubleResult.worker);

            getImage();
        }

        if(meterTroubleResult.worker == null || meterTroubleResult.worker.equals("")){
            etWorker.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(meterTroubleResult.troubleReasonPic, llTroubleReason);
        super.getImageFromURL(meterTroubleResult.handleContentPic, llHandleContent);

    }

    UpdateResult save(){

        if(meterTroubleResult.iD == 0){
            meterTroubleResult.assignByUserID = userPrefs.id().get();
            meterTroubleResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = meterTroubleResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, MeterTroubleResult.TroubleReasonPic, troubleReasonPicList, this);

        FileUtils.addImageToData(data, MeterTroubleResult.HandleContentPic, handleContentPicList, this);

        return meterTroubleClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.meterTroubleResult.assignmentTime = etAssignmentTime.getText().toString();
            this.meterTroubleResult.taskNum = etTaskNum.getText().toString();
            this.meterTroubleResult.areaName = spnAreaName.getSelectedItem().toString();

            this.meterTroubleResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.meterTroubleResult.householdNum = etHouseholdNum.getText().toString();
            this.meterTroubleResult.meterNum = etMeterNum.getText().toString();
            this.meterTroubleResult.troubleAddress = etTroubleAddress.getText().toString();
            this.meterTroubleResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.meterTroubleResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.meterTroubleResult.troubleReason = etTroubleReason.getText().toString();
            this.meterTroubleResult.handleContent = etHandleContent.getText().toString();
            this.meterTroubleResult.endHandleTime = etEndHandleTime.getText().toString();
            this.meterTroubleResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.meterTroubleResult.unhandleReason = etUnhandleReason.getText().toString();


            this.meterTroubleResult.endTime = etEndTime.getText().toString();
            this.meterTroubleResult.worker = etWorker.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
