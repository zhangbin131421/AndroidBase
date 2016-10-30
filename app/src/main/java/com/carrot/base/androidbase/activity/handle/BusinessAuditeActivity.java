package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.BusinessAuditeClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.BusinessAuditeResult;
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
@EActivity(R.layout.activity_business_audite)
@OptionsMenu(R.menu.task_item)
public class BusinessAuditeActivity extends BaseHandlerActivity{





    BusinessAuditeResult businessAuditeResult;

    @RestService
    BusinessAuditeClient businessAuditeClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_audite_content)
    Spinner spnAuditeContent;
    @ViewById(R.id.et_audite_scope)
    FormEditText etAuditeScope;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_begin_audite_time)
    FormEditText etBeginAuditeTime;
    @ViewById(R.id.et_audite_household_num)
    FormEditText etAuditeHouseholdNum;
    @ViewById(R.id.et_audite_result)
    FormEditText etAuditeResult;
    @ViewById(R.id.et_end_audite_time)
    FormEditText etEndAuditeTime;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;


    @ViewById(R.id.et_worker)
    FormEditText etWorker;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;




    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;


    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_6, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etSafetyMeasure,};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etSafetyMeasure,etEndTime,
                etAuditeScope,etEndTime,etBeginAuditeTime,etEndAuditeTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etAuditeScope,
                etSafetyMeasure,etBeginAuditeTime,etAuditeHouseholdNum,etAuditeResult,
                etEndAuditeTime,etUnhandleReason,etEndTime,etWorker};

        updateDisabledSpinnerList = new Spinner[] {spnAuditeContent};
        finishDisabledSpinnerList = new Spinner[] {spnAuditeContent,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
//            new OpenDateVo(etBeginAuditeTime, OpenDateVo.UPDATE),
//                new OpenDateVo(etEndAuditeTime, OpenDateVo.UPDATE),
//                new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {        };

    }

    @Override
    public void setErrorHandler(){
        businessAuditeClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAuditeContent, TypeUtils.AUDITE_CONTENT);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        businessAuditeResult = businessAuditeClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(businessAuditeResult == null){

            businessAuditeResult = new BusinessAuditeResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(getYYYYMMDD(businessAuditeResult.assignmentTime));
            etTaskNum.setText(businessAuditeResult.taskNum);
            spnAuditeContent.setSelection(TypeUtils.getSelectedIndex(TypeUtils.AUDITE_CONTENT, businessAuditeResult.auditeContent));
            etAuditeScope.setText(businessAuditeResult.auditeScope);
            etSafetyMeasure.setText(businessAuditeResult.safetyMeasure);
            etBeginAuditeTime.setText(getYYYYMMDD(businessAuditeResult.beginAuditeTime));
            etAuditeHouseholdNum.setText(businessAuditeResult.auditeHouseholdNum);
            etAuditeResult.setText(businessAuditeResult.auditeResult);
            etEndAuditeTime.setText(getYYYYMMDD(businessAuditeResult.endAuditeTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, businessAuditeResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(businessAuditeResult.unhandleReason);

            etEndTime.setText(getYYYYMMDD(businessAuditeResult.endTime));
            etWorker.setText(businessAuditeResult.worker);
            getImage();
        }

        etEndAuditeTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        if(businessAuditeResult.worker == null || businessAuditeResult.worker.equals("")){
            etWorker.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){


    }

    UpdateResult save(){

        if(businessAuditeResult.iD == 0){
            businessAuditeResult.assignByUserID = userPrefs.id().get();
            businessAuditeResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = businessAuditeResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return businessAuditeClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.businessAuditeResult.assignmentTime = etAssignmentTime.getText().toString();
            this.businessAuditeResult.taskNum = etTaskNum.getText().toString();
            this.businessAuditeResult.auditeContent = spnAuditeContent.getSelectedItem().toString();

            this.businessAuditeResult.auditeScope = etAuditeScope.getText().toString();
            this.businessAuditeResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.businessAuditeResult.beginAuditeTime = etBeginAuditeTime.getText().toString();

            this.businessAuditeResult.auditeHouseholdNum = etAuditeHouseholdNum.getText().toString();


            this.businessAuditeResult.auditeResult = etAuditeResult.getText().toString();
            this.businessAuditeResult.endAuditeTime = etEndAuditeTime.getText().toString();
            this.businessAuditeResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.businessAuditeResult.unhandleReason = etUnhandleReason.getText().toString();

            this.businessAuditeResult.worker = etWorker.getText().toString();
            this.businessAuditeResult.endTime = etEndTime.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
