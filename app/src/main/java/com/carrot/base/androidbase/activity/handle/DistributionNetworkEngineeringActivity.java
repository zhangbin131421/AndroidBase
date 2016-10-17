package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.DistributionNetworkEngineeringClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.DistributionNetworkEngineeringResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.apmem.tools.layouts.FlowLayout;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_distribution_network_engineering)
@OptionsMenu(R.menu.task_item)
public class DistributionNetworkEngineeringActivity extends BaseHandlerActivity{



    List<PhotoInfo> inspectPicList = new ArrayList<>();


    DistributionNetworkEngineeringResult distributionNetworkEngineeringResult;

    @RestService
    DistributionNetworkEngineeringClient distributionNetworkEngineeringClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.et_engineering_name)
    FormEditText etEngineeringName;
    @ViewById(R.id.et_engineering_num)
    FormEditText etEngineeringNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_execution_company)
    FormEditText etExecutionCompany;
    @ViewById(R.id.et_work_content)
    FormEditText etWorkContent;
    @ViewById(R.id.et_work_place)
    FormEditText etWorkPlace;
    @ViewById(R.id.et_stop_scope)
    FormEditText etStopScope;
    @ViewById(R.id.et_stop_time)
    FormEditText etStopTime;
    @ViewById(R.id.et_work_licensor)
    FormEditText etWorkLicensor;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.et_work_invoice_num)
    FormEditText etWorkInvoiceNum;
    @ViewById(R.id.et_execution_responsible)
    FormEditText etExecutionResponsible;
    @ViewById(R.id.et_work_responsible)
    FormEditText etWorkResponsible;
    @ViewById(R.id.et_work_content2)
    FormEditText etWorkContent2;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_actual_stop_time)
    FormEditText etActualStopTime;
    @ViewById(R.id.et_end_stop_time)
    FormEditText etEndStopTime;
    @ViewById(R.id.et_inspector)
    FormEditText etInspector;

    @ViewById(R.id.ll_inspect)
    org.apmem.tools.layouts.FlowLayout llInspect;


    @ViewById(R.id.btn_add_image_inspect)
    ImageView btnAddImageInspect;

    @ViewById(R.id.et_inspect)
    FormEditText etInspect;

    @ViewById(R.id.et_complete)
    FormEditText etComplete;
    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;
    @ViewById(R.id.spn_is_handled)
    Spinner spnIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_3_1, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etEngineeringName ,etEngineeringNum ,etExecutionCompany ,
                etWorkContent ,etWorkPlace ,etStopScope ,etStopTime ,etWorkLicensor};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etEngineeringName,etEngineeringNum,etExecutionCompany,etWorkContent,etWorkPlace,etStopScope,etStopTime,etWorkLicensor,etBeginHandleTime,etWorkInvoiceNum,etExecutionResponsible,etWorkResponsible,etWorkContent2,etSafetyMeasure,etActualStopTime,etEndStopTime,etInspector,etInspect,etComplete,etEndHandleTime,etUnhandleReason,};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etStopTime, OpenDateVo.UPDATE),
            new OpenDateVo(etActualStopTime, OpenDateVo.UPDATE),
                new OpenDateVo(etEndStopTime, OpenDateVo.UPDATE),
            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{
                new ShowWithSpinnerVo(spnIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };



        imageAddButtonList = new ImageView[] {btnAddImageInspect,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageInspect, inspectPicList, llInspect),
        };

    }

    @Override
    public void setErrorHandler(){
        distributionNetworkEngineeringClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        distributionNetworkEngineeringResult = distributionNetworkEngineeringClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(distributionNetworkEngineeringResult == null){

            distributionNetworkEngineeringResult = new DistributionNetworkEngineeringResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.assignmentTime));
            etTaskNum.setText(distributionNetworkEngineeringResult.taskNum);
            etEngineeringName.setText(distributionNetworkEngineeringResult.engineeringName);
            etEngineeringNum.setText(distributionNetworkEngineeringResult.engineeringNum);
            spnAreaName.setSelection(getSelectedAreaIndexByID(distributionNetworkEngineeringResult.areaID));
            etExecutionCompany.setText(distributionNetworkEngineeringResult.executionCompany);
            etWorkContent.setText(distributionNetworkEngineeringResult.workContent);
            etWorkPlace.setText(distributionNetworkEngineeringResult.workPlace);
            etStopScope.setText(distributionNetworkEngineeringResult.stopScope);
            etStopTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.stopTime));
            etWorkLicensor.setText(distributionNetworkEngineeringResult.workLicensor);
            etBeginHandleTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.beginHandleTime));
            etWorkInvoiceNum.setText(distributionNetworkEngineeringResult.workInvoiceNum);
            etExecutionResponsible.setText(distributionNetworkEngineeringResult.executionResponsible);
            etWorkResponsible.setText(distributionNetworkEngineeringResult.workResponsible);
            etWorkContent2.setText(distributionNetworkEngineeringResult.workContent2);
            etSafetyMeasure.setText(distributionNetworkEngineeringResult.safetyMeasure);
            etActualStopTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.actualStopTime));
            etEndStopTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.endStopTime));
            etInspector.setText(distributionNetworkEngineeringResult.inspector);
            etInspect.setText(distributionNetworkEngineeringResult.inspect);
            etComplete.setText(distributionNetworkEngineeringResult.complete);
            etEndHandleTime.setText(getYYYYMMDD(distributionNetworkEngineeringResult.endHandleTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, distributionNetworkEngineeringResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(distributionNetworkEngineeringResult.unhandleReason);

            getImage();
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(distributionNetworkEngineeringResult.inspectPic, llInspect);

    }

    UpdateResult save(){

        if(distributionNetworkEngineeringResult.iD == 0){
            distributionNetworkEngineeringResult.assignByUserID = userPrefs.id().get();
            distributionNetworkEngineeringResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = distributionNetworkEngineeringResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, DistributionNetworkEngineeringResult.InspectPic, inspectPicList, this);

        return distributionNetworkEngineeringClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.distributionNetworkEngineeringResult.assignmentTime = etAssignmentTime.getText().toString();
            this.distributionNetworkEngineeringResult.taskNum = etTaskNum.getText().toString();
            this.distributionNetworkEngineeringResult.engineeringName = etEngineeringName.getText().toString();
            this.distributionNetworkEngineeringResult.engineeringNum = etEngineeringNum.getText().toString();
            this.distributionNetworkEngineeringResult.areaName = spnAreaName.getSelectedItem().toString();

            this.distributionNetworkEngineeringResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.distributionNetworkEngineeringResult.executionCompany = etExecutionCompany.getText().toString();
            this.distributionNetworkEngineeringResult.workContent = etWorkContent.getText().toString();
            this.distributionNetworkEngineeringResult.workPlace = etWorkPlace.getText().toString();
            this.distributionNetworkEngineeringResult.stopScope = etStopScope.getText().toString();
            this.distributionNetworkEngineeringResult.stopTime = etStopTime.getText().toString();
            this.distributionNetworkEngineeringResult.workLicensor = etWorkLicensor.getText().toString();
            this.distributionNetworkEngineeringResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.distributionNetworkEngineeringResult.workInvoiceNum = etWorkInvoiceNum.getText().toString();
            this.distributionNetworkEngineeringResult.executionResponsible = etExecutionResponsible.getText().toString();
            this.distributionNetworkEngineeringResult.workResponsible = etWorkResponsible.getText().toString();
            this.distributionNetworkEngineeringResult.workContent2 = etWorkContent2.getText().toString();
            this.distributionNetworkEngineeringResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.distributionNetworkEngineeringResult.actualStopTime = etActualStopTime.getText().toString();
            this.distributionNetworkEngineeringResult.endStopTime = etEndStopTime.getText().toString();
            this.distributionNetworkEngineeringResult.inspector = etInspector.getText().toString();
            this.distributionNetworkEngineeringResult.inspect = etInspect.getText().toString();
            this.distributionNetworkEngineeringResult.complete = etComplete.getText().toString();
            this.distributionNetworkEngineeringResult.endHandleTime = etEndHandleTime.getText().toString();
            this.distributionNetworkEngineeringResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.distributionNetworkEngineeringResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
