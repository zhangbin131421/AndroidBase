package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.LineBrokenManagementClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.LineBrokenManagementResult;
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
@EActivity(R.layout.activity_line_broken_management)
@OptionsMenu(R.menu.task_item)
public class LineBrokenManagementActivity extends BaseHandlerActivity{



    List<PhotoInfo> handleContentPicList = new ArrayList<>();


    LineBrokenManagementResult lineBrokenManagementResult;

    @RestService
    LineBrokenManagementClient lineBrokenManagementClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_broken_rate)
    FormEditText etBrokenRate;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.spn_unqualified_reason)
    Spinner spnUnqualifiedReason;

    @ViewById(R.id.ll_handle_content)
    org.apmem.tools.layouts.FlowLayout llHandleContent;


    @ViewById(R.id.btn_add_image_handle_content)
    ImageView btnAddImageHandleContent;

    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;

    @ViewById(R.id.et_end_handle_tiem)
    FormEditText etEndHandleTiem;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;


    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_1, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBrokenRate,etSafetyMeasure,etBeginHandleTime,etHandleContent,etEndHandleTiem,etUnhandleReason,};

        updateDisabledSpinnerList = new Spinner[] {};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnUnqualifiedReason,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etEndHandleTiem, OpenDateVo.UPDATE_ADD),
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
        lineBrokenManagementClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnUnqualifiedReason, TypeUtils.LB_NOOK_REASON);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        lineBrokenManagementResult = lineBrokenManagementClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(lineBrokenManagementResult == null){

            lineBrokenManagementResult = new LineBrokenManagementResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(lineBrokenManagementResult.assignmentTime);
            etTaskNum.setText(lineBrokenManagementResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndex(lineBrokenManagementResult.areaName));
            etBrokenRate.setText(lineBrokenManagementResult.brokenRate);
            etSafetyMeasure.setText(lineBrokenManagementResult.safetyMeasure);
            etBeginHandleTime.setText(lineBrokenManagementResult.beginHandleTime);
            spnUnqualifiedReason.setSelection(TypeUtils.getSelectedIndex(TypeUtils.LB_NOOK_REASON, lineBrokenManagementResult.unqualifiedReason));
            etHandleContent.setText(lineBrokenManagementResult.handleContent);
            etEndHandleTiem.setText(lineBrokenManagementResult.endHandleTiem);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, lineBrokenManagementResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(lineBrokenManagementResult.unhandleReason);

            getImage();
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(lineBrokenManagementResult.handleContentPic, llHandleContent);

    }

    UpdateResult save(){

        if(lineBrokenManagementResult.iD == 0){
            lineBrokenManagementResult.assignByUserID = userPrefs.id().get();
            lineBrokenManagementResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = lineBrokenManagementResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, LineBrokenManagementResult.HandleContentPic, handleContentPicList, this);

        return lineBrokenManagementClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.lineBrokenManagementResult.assignmentTime = etAssignmentTime.getText().toString();
            this.lineBrokenManagementResult.taskNum = etTaskNum.getText().toString();
            this.lineBrokenManagementResult.areaName = spnAreaName.getSelectedItem().toString();

            this.lineBrokenManagementResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.lineBrokenManagementResult.brokenRate = etBrokenRate.getText().toString();
            this.lineBrokenManagementResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.lineBrokenManagementResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.lineBrokenManagementResult.unqualifiedReason = spnUnqualifiedReason.getSelectedItem().toString();

            this.lineBrokenManagementResult.handleContent = etHandleContent.getText().toString();
            this.lineBrokenManagementResult.endHandleTiem = etEndHandleTiem.getText().toString();
            this.lineBrokenManagementResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.lineBrokenManagementResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
