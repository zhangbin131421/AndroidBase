package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.StopStartElectricClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.StopStartElectricResult;
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
@EActivity(R.layout.activity_stop_start_electric)
@OptionsMenu(R.menu.task_item)
public class StopStartElectricActivity extends BaseHandlerActivity{



    List<PhotoInfo> isHandledPicList = new ArrayList<>();


    StopStartElectricResult stopStartElectricResult;

    @RestService
    StopStartElectricClient stopStartElectricClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_stop_start_electric_address)
    FormEditText etStopStartElectricAddress;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.spn_handle_type)
    Spinner spnHandleType;
    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;

//    @ViewById(R.id.ll_is_handled)
//    org.apmem.tools.layouts.FlowLayout llIsHandled;


//    @ViewById(R.id.btn_add_image_is_handled)
//    ImageView btnAddImageIsHandled;

    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;

    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;


    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;

    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;

    @ViewById(R.id.et_worker)
    FormEditText etWorder;


    @ViewById(R.id.btn_add_image)
    ImageView imageAdd;


    @ViewById(R.id.et_handle_content_pic)
    org.apmem.tools.layouts.FlowLayout llHandleContentPic;



    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_7, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,
                etStopStartElectricAddress,etEndTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etStopStartElectricAddress,
                etBeginHandleTime,etEndHandleTime,etUnhandleReason,
        etEndTime, etHandleContent, etWorder};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName, spnHandleType,spnIsHandled};

        openDateEditTextList = new OpenDateVo[] {
                new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
                new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] { imageAdd       };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(imageAdd, isHandledPicList, llHandleContentPic),
        };

    }

    @Override
    public void setErrorHandler(){
        stopStartElectricClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnHandleType, TypeUtils.SS_HANDLER);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        stopStartElectricResult = stopStartElectricClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(stopStartElectricResult == null){

            stopStartElectricResult = new StopStartElectricResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(stopStartElectricResult.assignmentTime);
            etTaskNum.setText(stopStartElectricResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(stopStartElectricResult.areaID));
            etStopStartElectricAddress.setText(stopStartElectricResult.stopStartElectricAddress);
            etBeginHandleTime.setText(stopStartElectricResult.beginHandleTime);
            spnHandleType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.SS_HANDLER, stopStartElectricResult.handleType));
            etEndHandleTime.setText(stopStartElectricResult.endHandleTime);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, stopStartElectricResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(stopStartElectricResult.unhandleReason);

            etEndTime.setText(stopStartElectricResult.endTime);
            etHandleContent.setText(stopStartElectricResult.handleContent);
            etWorder.setText(stopStartElectricResult.worker);



            getImage();
        }

        if(stopStartElectricResult.worker == null || stopStartElectricResult.worker.equals("")){
            etWorder.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(stopStartElectricResult.handleContentPic, llHandleContentPic);

    }

    UpdateResult save(){

        if(stopStartElectricResult.iD == 0){
            stopStartElectricResult.assignByUserID = userPrefs.id().get();
            stopStartElectricResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = stopStartElectricResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, StopStartElectricResult.HandleContentPic, isHandledPicList, this);

        return stopStartElectricClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.stopStartElectricResult.assignmentTime = etAssignmentTime.getText().toString();
            this.stopStartElectricResult.taskNum = etTaskNum.getText().toString();
            this.stopStartElectricResult.areaName = spnAreaName.getSelectedItem().toString();

            this.stopStartElectricResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.stopStartElectricResult.stopStartElectricAddress = etStopStartElectricAddress.getText().toString();
            this.stopStartElectricResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.stopStartElectricResult.handleType = spnHandleType.getSelectedItem().toString();

            this.stopStartElectricResult.endHandleTime = etEndHandleTime.getText().toString();
            this.stopStartElectricResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;
            this.stopStartElectricResult.unhandleReason = etUnhandleReason.getText().toString();

            this.stopStartElectricResult.endTime = etEndTime.getText().toString();
            this.stopStartElectricResult.handleContent = etHandleContent.getText().toString();
            this.stopStartElectricResult.worker = etWorder.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
