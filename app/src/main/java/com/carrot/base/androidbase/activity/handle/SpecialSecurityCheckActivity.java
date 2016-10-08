


package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.SpecialSecurityCheckClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.SpecialSecurityCheckResult;
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

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_special_security_check)
@OptionsMenu(R.menu.task_item)
public class SpecialSecurityCheckActivity extends BaseHandlerActivity{




    SpecialSecurityCheckResult specialSecurityCheckResult;

    @RestService
    SpecialSecurityCheckClient specialSecurityCheckClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;

    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;

    @ViewById(R.id.etBeginTime)
    FormEditText etBeginTime;

    @ViewById(R.id.etEndTime)
    FormEditText etEndTime;

    @ViewById(R.id.etSafetyMeasure)
    Spinner etSafetyMeasure;

    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.etExistIssue)
    FormEditText etExistIssue;

    @ViewById(R.id.etUserID)
    FormEditText etUserID;

    @ViewById(R.id.etCheckDate)
    FormEditText etCheckDate;

    @ViewById(R.id.etEndHandleTime)
    FormEditText etEndHandleTime;

    @ViewById(R.id.etIsHandled)
    Spinner etIsHandled;

    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;


    @AfterViews
    void bindAdapter(){

        super.afterInitView("专项安全检查", getApplicationContext(), getResources());

    }


    public void setValidateList(){

        allValidateFields = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginTime,etEndTime,etBeginHandleTime,etExistIssue,etUserID,etCheckDate,etEndHandleTime,etUnhandleReason};


    }

    @Override
    public void setErrorHandler(){
        specialSecurityCheckClient.setRestErrorHandler(ssErrorHandler);
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etSafetyMeasure, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

    }



    @Background
    void getEntityFromServer(){
        specialSecurityCheckResult = specialSecurityCheckClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(specialSecurityCheckResult == null){

            specialSecurityCheckResult = new SpecialSecurityCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(specialSecurityCheckResult.assignmentTime);
            etTaskNum.setText(specialSecurityCheckResult.taskNum);
            etBeginTime.setText(specialSecurityCheckResult.beginTime);
            etEndTime.setText(specialSecurityCheckResult.endTime);
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, specialSecurityCheckResult.safetyMeasure));
            etBeginHandleTime.setText(specialSecurityCheckResult.beginHandleTime);
            etExistIssue.setText(specialSecurityCheckResult.existIssue);
            etUserID.setText(specialSecurityCheckResult.userID+"");
            etCheckDate.setText(specialSecurityCheckResult.checkDate);
            etEndHandleTime.setText(specialSecurityCheckResult.endHandleTime);

            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, specialSecurityCheckResult.isHandled == 2 ? "未处理" : "已处理"));

            etUnhandleReason.setText(specialSecurityCheckResult.unhandleReason);

            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

    }



    /**
     * 新增
     */
    @Override
    UpdateResult save(){

        if(specialSecurityCheckResult.id == 0){
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



    @Override
    boolean validate(){

        if(super.validate()) {

            this.specialSecurityCheckResult.assignmentTime = etAssignmentTime.getText().toString();

            this.specialSecurityCheckResult.taskNum = etTaskNum.getText().toString();

            this.specialSecurityCheckResult.beginTime = etBeginTime.getText().toString();

            this.specialSecurityCheckResult.endTime = etEndTime.getText().toString();

            this.specialSecurityCheckResult.safetyMeasure = etSafetyMeasure.getSelectedItem().toString();

            this.specialSecurityCheckResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.specialSecurityCheckResult.existIssue = etExistIssue.getText().toString();

            this.specialSecurityCheckResult.userID = Integer.parseInt(etUserID.getText().toString());

            this.specialSecurityCheckResult.checkDate = etCheckDate.getText().toString();

            this.specialSecurityCheckResult.endHandleTime = etEndHandleTime.getText().toString();

            this.specialSecurityCheckResult.isHandled = etIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.specialSecurityCheckResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}