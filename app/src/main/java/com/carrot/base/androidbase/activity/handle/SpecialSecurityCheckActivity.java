package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.SpecialSecurityCheckClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.SpecialSecurityCheckResult;
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
@EActivity(R.layout.activity_special_security_check)
@OptionsMenu(R.menu.task_item)
public class SpecialSecurityCheckActivity extends BaseHandlerActivity{





    SpecialSecurityCheckResult specialSecurityCheckResult;

    @RestService
    SpecialSecurityCheckClient specialSecurityCheckClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.et_begin_time)
    FormEditText etBeginTime;
    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.et_exist_issue)
    FormEditText etExistIssue;
    @ViewById(R.id.et_check_date)
    FormEditText etCheckDate;
//    @ViewById(R.id.et_end_handle_time)
//    FormEditText etEndHandleTime;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;



    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_2_8, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginTime,etEndTime,etSafetyMeasure,etBeginHandleTime,etExistIssue,etCheckDate,etUnhandleReason,};

        updateDisabledSpinnerList = new Spinner[] {};
        finishDisabledSpinnerList = new Spinner[] {spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etBeginTime, OpenDateVo.UPDATE),
            new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
            new OpenDateVo(etBeginHandleTime, OpenDateVo.UPDATE),
            new OpenDateVo(etCheckDate, OpenDateVo.UPDATE),
//            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE_ADD),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {        };

    }

    @Override
    public void setErrorHandler(){
        specialSecurityCheckClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        specialSecurityCheckResult = specialSecurityCheckClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(specialSecurityCheckResult == null){

            specialSecurityCheckResult = new SpecialSecurityCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndTime.setText(DateUtils.getEndTime());

        }else{

            etAssignmentTime.setText(specialSecurityCheckResult.assignmentTime);
            etTaskNum.setText(specialSecurityCheckResult.taskNum);
            etBeginTime.setText(specialSecurityCheckResult.beginTime);
            etEndTime.setText(specialSecurityCheckResult.endHandleTime);
            etSafetyMeasure.setText(specialSecurityCheckResult.safetyMeasure);
            etBeginHandleTime.setText(specialSecurityCheckResult.beginHandleTime);
            etExistIssue.setText(specialSecurityCheckResult.existIssue);
            etCheckDate.setText(specialSecurityCheckResult.checkDate);
//            etEndHandleTime.setText(specialSecurityCheckResult.endHandleTime);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, specialSecurityCheckResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(specialSecurityCheckResult.unhandleReason);

            getImage();
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){


    }

    UpdateResult save(){

        if(specialSecurityCheckResult.iD == 0){
            specialSecurityCheckResult.assignByUserID = userPrefs.id().get();
            specialSecurityCheckResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = specialSecurityCheckResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return specialSecurityCheckClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.specialSecurityCheckResult.assignmentTime = etAssignmentTime.getText().toString();
            this.specialSecurityCheckResult.taskNum = etTaskNum.getText().toString();
            this.specialSecurityCheckResult.beginTime = etBeginTime.getText().toString();
//            this.specialSecurityCheckResult.endTime = etEndTime.getText().toString();
            this.specialSecurityCheckResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.specialSecurityCheckResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.specialSecurityCheckResult.existIssue = etExistIssue.getText().toString();
            this.specialSecurityCheckResult.checkDate = etCheckDate.getText().toString();
            this.specialSecurityCheckResult.endHandleTime = etEndTime.getText().toString();
            this.specialSecurityCheckResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.specialSecurityCheckResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
