package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.ExtendBussinessSetupClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.ExtendBussinessSetupResult;
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
@EActivity(R.layout.activity_extend_bussiness_setup)
@OptionsMenu(R.menu.task_item)
public class ExtendBussinessSetupActivity extends BaseHandlerActivity{



    List<PhotoInfo> handleContentPicList = new ArrayList<>();


    ExtendBussinessSetupResult extendBussinessSetupResult;

    @RestService
    ExtendBussinessSetupClient extendBussinessSetupClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.spn_extend_type)
    Spinner spnExtendType;
    @ViewById(R.id.et_setup_address)
    FormEditText etSetupAddress;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;

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
    @ViewById(R.id.et_worker)
    FormEditText etWorker;


    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_3, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etSetupAddress,etEndTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etSetupAddress,
                etSafetyMeasure,etEndTime,etBeginHandleTime,etHandleContent,
                etEndHandleTime,etUnhandleReason,etWorker};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName,spnExtendType};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnExtendType,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {btnAddImageHandleContent,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageHandleContent, handleContentPicList, llHandleContent),
        };

    }

    @Override
    public void setErrorHandler(){
        extendBussinessSetupClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnExtendType, TypeUtils.EXTEND_TYPE);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        extendBussinessSetupResult = extendBussinessSetupClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(extendBussinessSetupResult == null){

            extendBussinessSetupResult = new ExtendBussinessSetupResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndTime.setText(DateUtils.getEndTime());

        }else{

            etAssignmentTime.setText(getYYYYMMDD(extendBussinessSetupResult.assignmentTime));
            etTaskNum.setText(extendBussinessSetupResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(extendBussinessSetupResult.areaID));
            spnExtendType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.EXTEND_TYPE, extendBussinessSetupResult.extendType));
            etSetupAddress.setText(extendBussinessSetupResult.setupAddress);
            etSafetyMeasure.setText(extendBussinessSetupResult.safetyMeasure);
            etEndTime.setText(getYYYYMMDD(extendBussinessSetupResult.endTime));
            etBeginHandleTime.setText(getYYYYMMDD(extendBussinessSetupResult.beginHandleTime));
            etHandleContent.setText(extendBussinessSetupResult.handleContent);
            etWorker.setText(extendBussinessSetupResult.worker);
            etEndHandleTime.setText(getYYYYMMDD(extendBussinessSetupResult.endHandleTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, extendBussinessSetupResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(extendBussinessSetupResult.unhandleReason);

            getImage();
        }

        if(extendBussinessSetupResult.worker == null || extendBussinessSetupResult.worker.equals("")){
            etWorker.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(extendBussinessSetupResult.handleContentPic, llHandleContent);

    }

    UpdateResult save(){

        if(extendBussinessSetupResult.iD == 0){
            extendBussinessSetupResult.assignByUserID = userPrefs.id().get();
            extendBussinessSetupResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = extendBussinessSetupResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, ExtendBussinessSetupResult.HandleContentPic, handleContentPicList, this);

        return extendBussinessSetupClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.extendBussinessSetupResult.assignmentTime = etAssignmentTime.getText().toString();
            this.extendBussinessSetupResult.taskNum = etTaskNum.getText().toString();
            this.extendBussinessSetupResult.areaName = spnAreaName.getSelectedItem().toString();

            this.extendBussinessSetupResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.extendBussinessSetupResult.extendType = spnExtendType.getSelectedItem().toString();

            this.extendBussinessSetupResult.setupAddress = etSetupAddress.getText().toString();
            this.extendBussinessSetupResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.extendBussinessSetupResult.endTime = etEndTime.getText().toString();
            this.extendBussinessSetupResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.extendBussinessSetupResult.handleContent = etHandleContent.getText().toString();
            this.extendBussinessSetupResult.endHandleTime = etEndHandleTime.getText().toString();
            this.extendBussinessSetupResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.extendBussinessSetupResult.unhandleReason = etUnhandleReason.getText().toString();
            this.extendBussinessSetupResult.worker = etWorker.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
