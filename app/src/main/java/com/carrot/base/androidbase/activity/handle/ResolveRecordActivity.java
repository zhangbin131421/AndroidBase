package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.ResolveRecordResult;
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
@EActivity(R.layout.activity_resolve_record)
@OptionsMenu(R.menu.task_item)
public class ResolveRecordActivity extends BaseHandlerActivity{

    List<PhotoInfo> defectContentPicList = new ArrayList<>();

    ResolveRecordResult resolveRecordResult;

    @RestService
    ResolveRecordClient resolveRecordClient;

    @ViewById(R.id.et_AssignmentTime)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_TaskNum)
    FormEditText et_TaskNum;
    @ViewById(R.id.et_DefectPlace)
    FormEditText et_DefectPlace;
    @ViewById(R.id.et_DefectContent)
    FormEditText et_DefectContent;
    @ViewById(R.id.et_EndTime)
    FormEditText et_EndTime;
    @ViewById(R.id.et_SafetyMeasure)
    FormEditText et_SafetyMeasure;
    @ViewById(R.id.et_WorkType)
    Spinner et_WorkType;
    @ViewById(R.id.et_WorkInvoiceNum)
    FormEditText et_WorkInvoiceNum;
    @ViewById(R.id.et_StopScope)
    FormEditText et_StopScope;
    @ViewById(R.id.et_Applier)
    FormEditText et_Applier;
    @ViewById(R.id.et_WorkLicensor)
    FormEditText et_WorkLicensor;
    @ViewById(R.id.et_WorkPrincipal)
    FormEditText et_WorkPrincipal;
    @ViewById(R.id.et_StopTime)
    FormEditText et_StopTime;
    @ViewById(R.id.et_EndStopTime)
    FormEditText et_EndStopTime;
    @ViewById(R.id.et_StopPeople)
    FormEditText et_StopPeople;
    @ViewById(R.id.et_Worker)
    FormEditText et_Worker;
    @ViewById(R.id.et_OperationInvoiceNum)
    FormEditText et_OperationInvoiceNum;
    @ViewById(R.id.et_WorkInstruction)
    FormEditText et_WorkInstruction;
    @ViewById(R.id.et_ResolveContent)
    FormEditText et_ResolveContent;
    @ViewById(R.id.et_ResolveContentPic)
    org.apmem.tools.layouts.FlowLayout et_ResolveContentPic;
    @ViewById(R.id.et_WorkDate)
    FormEditText et_WorkDate;
    @ViewById(R.id.et_EndHandleTime)
    FormEditText et_EndHandleTime;
    @ViewById(R.id.et_IsHandled)
    Spinner et_IsHandled;
    @ViewById(R.id.et_UnhandleReason)
    FormEditText et_UnhandleReason;
//    @ViewById(R.id.et_BeginHandleTime)
//    FormEditText et_BeginHandleTime;


    @ViewById(R.id.etAreaName)
    Spinner etAreaName;
    @ViewById(R.id.btn_add_image)
    ImageView imageAdd;


    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;

    @AfterViews
    void bindAdapter(){


        super.afterInitView(TypeUtils.TYPE_2_4, getApplicationContext(), getResources());
    }


    public void setValidateList(){



        allValidateFields = new FormEditText[] {et_DefectPlace,
                et_DefectContent, et_EndTime, et_SafetyMeasure,et_WorkDate};

        addDisableList = new FormEditText[] {etAssignmentTime, et_TaskNum, et_SafetyMeasure, et_EndTime,
                et_EndHandleTime};

        updateDisableList = new FormEditText[] {etAssignmentTime, et_TaskNum, et_DefectPlace,
                et_DefectContent, et_SafetyMeasure, et_EndTime,et_EndHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime, et_TaskNum, et_DefectPlace,
                et_DefectContent, et_EndTime, et_SafetyMeasure, et_WorkInvoiceNum, et_StopScope,
                et_Applier, et_WorkLicensor, et_WorkPrincipal, et_StopTime, et_EndStopTime,
                et_StopPeople, et_Worker, et_OperationInvoiceNum, et_WorkInstruction, et_WorkDate,
//                et_EndHandleTime,et_BeginHandleTime,
                et_UnhandleReason, et_ResolveContent,et_EndHandleTime};

        updateDisabledSpinnerList = new Spinner[] {etAreaName};
        finishDisabledSpinnerList = new Spinner[] {etAreaName, et_WorkType, et_IsHandled};

        imageAddButtonList = new ImageView[] {imageAdd};

        openDateEditTextList = new OpenDateVo[] {
//                new OpenDateVo(et_BeginHandleTime, 10),
                new OpenDateVo(et_WorkDate, OpenDateVo.UPDATE)
//                ,new OpenDateVo(et_EndHandleTime, 10)
        };
        openTimeEditTextList = new OpenDateVo[] {
                new OpenDateVo(et_StopTime, OpenDateVo.UPDATE),
                new OpenDateVo(et_EndStopTime, OpenDateVo.UPDATE)
        };

        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(imageAdd, defectContentPicList, et_ResolveContentPic)
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(et_IsHandled, llIsHandler, "未处理", new FormEditText[]{et_UnhandleReason})
        };


    }

    @Override
    public void setErrorHandler(){
        resolveRecordClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, DataInstance.getInstance().areaInformationResults);

        setDropDownListAdapter(et_WorkType, TypeUtils.WORK_TYPE);


        setDropDownListAdapter(et_IsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        resolveRecordResult = resolveRecordClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(resolveRecordResult == null){

            resolveRecordResult = new ResolveRecordResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

            et_EndTime.setText(DateUtils.getEndTime());

            et_WorkDate.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(getYYYYMMDD(resolveRecordResult.assignmentTime));
            et_TaskNum.setText(resolveRecordResult.taskNum);


            et_DefectPlace.setText(resolveRecordResult.defectPlace);
            et_DefectContent.setText(resolveRecordResult.defectContent);
            et_EndTime.setText(getYYYYMMDD(resolveRecordResult.endTime));
            et_SafetyMeasure.setText(resolveRecordResult.safetyMeasure);
            et_WorkType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.WORK_TYPE, resolveRecordResult.workType));
            et_WorkInvoiceNum.setText(resolveRecordResult.workInvoiceNum);
            et_StopScope.setText(resolveRecordResult.stopScope);
            et_Applier.setText(resolveRecordResult.applier);
            et_WorkLicensor.setText(resolveRecordResult.workLicensor);
            et_WorkPrincipal.setText(resolveRecordResult.workPrincipal);
            et_StopTime.setText(getYYYYMMDD(resolveRecordResult.stopTime));
            et_EndStopTime.setText(getYYYYMMDD(resolveRecordResult.endStopTime));
            et_StopPeople.setText(resolveRecordResult.stopPeople);
            et_Worker.setText(resolveRecordResult.worker);
            et_OperationInvoiceNum.setText(resolveRecordResult.operationInvoiceNum);
            et_WorkInstruction.setText(resolveRecordResult.workInstruction);
            et_ResolveContent.setText(resolveRecordResult.resolveContent);
            if(resolveRecordResult.workDate!=null){
                et_WorkDate.setText(getYYYYMMDD(resolveRecordResult.workDate));
            }else{
                et_WorkDate.setText(DateUtils.getCurrentYYYY_MM_DD());
            }

//            et_EndHandleTime.setText(resolveRecordResult.endHandleTime);
            et_IsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, resolveRecordResult.isHandled == 2 ? "未处理" : "已处理"));

            et_UnhandleReason.setText(resolveRecordResult.unhandleReason);
//            et_BeginHandleTime.setText(resolveRecordResult.beginHandleTime);
            etAreaName.setSelection(getSelectedAreaIndexByID(resolveRecordResult.areaID));



            getImage();


            this.saveStatus = 1;
        }

        et_EndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());

    }


    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(resolveRecordResult.resolveContentPic, et_ResolveContentPic);

    }

    /**
     * 新增
     */
    UpdateResult save(){

        if(resolveRecordResult.id == 0){
            resolveRecordResult.assignByUserID = userPrefs.id().get();
            resolveRecordResult.userId = userPrefs.id().get();
            resolveRecordResult.assignmentTime = DateUtils.getCurrentYYYY_MM_DD();
        }


        MultiValueMap<String, Object> data = null;
        try {
            data = resolveRecordResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, ResolveRecordResult.ResolveContentPic, defectContentPicList, this);

        return resolveRecordClient.update(data);

    }


    boolean validate(){

        if(super.validate()) {

            this.resolveRecordResult.assignmentTime = etAssignmentTime.getText().toString();

            this.resolveRecordResult.taskNum = et_TaskNum.getText().toString();

            this.resolveRecordResult.defectPlace = et_DefectPlace.getText().toString();

            this.resolveRecordResult.defectContent = et_DefectContent.getText().toString();

            this.resolveRecordResult.endTime = et_EndTime.getText().toString();

            this.resolveRecordResult.safetyMeasure = et_SafetyMeasure.getText().toString();

            AreaInformationResult obj = (AreaInformationResult)etAreaName.getSelectedItem();

            if(obj != null){
                this.resolveRecordResult.areaName = obj.areaName;
                this.resolveRecordResult.areaID = obj.id;
            }

//            this.resolveRecordResult.beginHandleTime = et_BeginHandleTime.getText().toString();

            this.resolveRecordResult.workType = et_WorkType.getSelectedItem().toString();

            this.resolveRecordResult.workInvoiceNum = et_WorkInvoiceNum.getText().toString();

            this.resolveRecordResult.stopScope = et_StopScope.getText().toString();

            this.resolveRecordResult.applier = et_Applier.getText().toString();

            this.resolveRecordResult.workLicensor = et_WorkLicensor.getText().toString();

            this.resolveRecordResult.workPrincipal = et_WorkPrincipal.getText().toString();

            this.resolveRecordResult.stopTime = et_StopTime.getText().toString();

            this.resolveRecordResult.endStopTime = et_EndStopTime.getText().toString();

            this.resolveRecordResult.stopPeople = et_StopPeople.getText().toString();

            this.resolveRecordResult.worker = et_Worker.getText().toString();

            this.resolveRecordResult.operationInvoiceNum = et_OperationInvoiceNum.getText().toString();

            this.resolveRecordResult.workInstruction = et_WorkInstruction.getText().toString();

            this.resolveRecordResult.resolveContent = et_ResolveContent.getText().toString();

            this.resolveRecordResult.workDate = et_WorkDate.getText().toString();

//            this.resolveRecordResult.endHandleTime = et_EndHandleTime.getText().toString();

            this.resolveRecordResult.isHandled = et_IsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.resolveRecordResult.unhandleReason = et_UnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }
}
