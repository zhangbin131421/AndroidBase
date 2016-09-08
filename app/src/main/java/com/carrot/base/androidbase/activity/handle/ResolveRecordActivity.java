package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.ResolveRecordResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
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
    org.apmem.tools.layouts.FlowLayout et_ResolveContent;
    @ViewById(R.id.et_WorkDate)
    FormEditText et_WorkDate;
    @ViewById(R.id.et_EndHandleTime)
    FormEditText et_EndHandleTime;
    @ViewById(R.id.et_IsHandled)
    FormEditText et_IsHandled;
    @ViewById(R.id.et_UnhandleReason)
    FormEditText et_UnhandleReason;
    @ViewById(R.id.et_BeginHandleTime)
    FormEditText et_BeginHandleTime;



    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_2_4, getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime, et_TaskNum, et_DefectPlace,
                et_DefectContent, et_EndTime, et_SafetyMeasure, et_WorkInvoiceNum, et_StopScope,
                et_Applier, et_WorkLicensor, et_WorkPrincipal, et_StopTime, et_EndStopTime,
                et_StopPeople, et_Worker, et_OperationInvoiceNum, et_WorkInstruction, et_WorkDate,
                et_EndHandleTime, et_IsHandled, et_UnhandleReason, et_BeginHandleTime};



    }


    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(et_WorkType, TypeUtils.WORK_TYPE);
    }

    @Override
    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            resolveRecordResult = resolveRecordClient.getById(taskBaseVo.id);
        }

        refreshView();
        dissmisLoading();
    }


    @UiThread
    void refreshView(){
        if(resolveRecordResult == null){

            resolveRecordResult = new ResolveRecordResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            //test start

             etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             et_TaskNum.setText("");
             et_DefectPlace.setText("defect place");
             et_DefectContent.setText("defect content");
             et_EndTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             et_SafetyMeasure.setText("safe me");
             et_WorkInvoiceNum.setText("w i n");
             et_StopScope.setText("ss");
             et_Applier.setText("applier");
             et_WorkLicensor.setText("w l");
             et_WorkPrincipal.setText("w p");
             et_StopTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             et_EndStopTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             et_StopPeople.setText("s p");
             et_Worker.setText("worker");
             et_OperationInvoiceNum.setText("o i n");
             et_WorkInstruction.setText("w i");
             et_WorkDate.setText(DateUtils.getCurrentYYYY_MM_DD());
            et_EndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            et_BeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             et_IsHandled.setText(0+"");
             et_UnhandleReason.setText("unreason");
            //test end
        }else{

            etAssignmentTime.setText(resolveRecordResult.assignmentTime);
            et_TaskNum.setText(resolveRecordResult.taskNum);


            et_DefectPlace.setText(resolveRecordResult.defectPlace);
            et_DefectContent.setText(resolveRecordResult.defectContent);
            et_EndTime.setText(resolveRecordResult.endTime);
            et_SafetyMeasure.setText(resolveRecordResult.safetyMeasure);
            et_WorkType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.WORK_TYPE, resolveRecordResult.workType));
            et_WorkInvoiceNum.setText(resolveRecordResult.workInvoiceNum);
            et_StopScope.setText(resolveRecordResult.stopScope);
            et_Applier.setText(resolveRecordResult.applier);
            et_WorkLicensor.setText(resolveRecordResult.workLicensor);
            et_WorkPrincipal.setText(resolveRecordResult.workPrincipal);
            et_StopTime.setText(resolveRecordResult.stopTime);
            et_EndStopTime.setText(resolveRecordResult.endStopTime);
            et_StopPeople.setText(resolveRecordResult.stopPeople);
            et_Worker.setText(resolveRecordResult.worker);
            et_OperationInvoiceNum.setText(resolveRecordResult.operationInvoiceNum);
            et_WorkInstruction.setText(resolveRecordResult.workInstruction);
//            et_ResolveContent.setText(resolveRecordResult.resolveContent);
            et_WorkDate.setText(resolveRecordResult.workDate);
            et_EndHandleTime.setText(resolveRecordResult.endHandleTime);
            et_IsHandled.setText(resolveRecordResult.isHandled+"");
            et_UnhandleReason.setText(resolveRecordResult.unhandleReason);
            et_BeginHandleTime.setText(resolveRecordResult.beginHandleTime);


            getImage();


            this.saveStatus = 1;
        }

    }


    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(resolveRecordResult.resolveContentPic, et_ResolveContent);

    }



    @Click(R.id.btn_add_image)
    void addImageLocal(){


        super.showChooseImage(defectContentPicList, et_ResolveContent);

    }


    /**
     * 新增
     */
    @Override
    void add(){

        resolveRecordResult.assignByUserID = userPrefs.id().get();
        resolveRecordResult.userId = userPrefs.id().get();

        resolveRecordClient.add(resolveRecordResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){

        MultiValueMap<String, Object> data = null;
        try {
            data = resolveRecordResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, ResolveRecordResult.ResolveContentPic, defectContentPicList, this);

        resolveRecordClient.update(data);

    }


    @Override
    boolean validate(){

        if(super.validate()) {

            this.resolveRecordResult.assignmentTime = etAssignmentTime.getText().toString();

            this.resolveRecordResult.taskNum = et_TaskNum.getText().toString();

            this.resolveRecordResult.defectPlace = et_DefectPlace.getText().toString();

            this.resolveRecordResult.defectContent = et_DefectContent.getText().toString();

            this.resolveRecordResult.endTime = et_EndTime.getText().toString();

            this.resolveRecordResult.safetyMeasure = et_SafetyMeasure.getText().toString();

            this.resolveRecordResult.beginHandleTime = et_BeginHandleTime.getText().toString();

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

            this.resolveRecordResult.workDate = et_WorkDate.getText().toString();

            this.resolveRecordResult.endHandleTime = et_EndHandleTime.getText().toString();

            this.resolveRecordResult.isHandled = Integer.parseInt(et_IsHandled.getText().toString());

            this.resolveRecordResult.unhandleReason = et_UnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }
}
