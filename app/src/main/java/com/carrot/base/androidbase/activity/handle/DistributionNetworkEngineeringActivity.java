

package com.carrot.base.androidbase.activity.handle;

import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.DistributionNetworkEngineeringClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.DistributionNetworkEngineeringResult;

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
@EActivity(R.layout.activity_distribution_network_engineering)
@OptionsMenu(R.menu.task_item)
public class DistributionNetworkEngineeringActivity extends BaseHandlerActivity{



    List<PhotoInfo> inspectList = new ArrayList<>();


    DistributionNetworkEngineeringResult distributionNetworkEngineeringResult;

    @RestService
    DistributionNetworkEngineeringClient distributionNetworkEngineeringClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;

    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;

    @ViewById(R.id.etEngineeringName)
    FormEditText etEngineeringName;

    @ViewById(R.id.etEngineeringNum)
    FormEditText etEngineeringNum;

    @ViewById(R.id.etAreaName)
    Spinner etAreaName;

    @ViewById(R.id.etExecutionCompany)
    Spinner etExecutionCompany;

    @ViewById(R.id.etWorkContent)
    FormEditText etWorkContent;

    @ViewById(R.id.etWorkPlace)
    FormEditText etWorkPlace;

    @ViewById(R.id.etStopScope)
    FormEditText etStopScope;

    @ViewById(R.id.etStopTime)
    FormEditText etStopTime;

    @ViewById(R.id.etWorkLicensor)
    FormEditText etWorkLicensor;

    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.etWorkInvoiceNum)
    FormEditText etWorkInvoiceNum;

    @ViewById(R.id.etExecutionResponsible)
    FormEditText etExecutionResponsible;

    @ViewById(R.id.etWorkResponsible)
    FormEditText etWorkResponsible;

    @ViewById(R.id.etWorkContent2)
    FormEditText etWorkContent2;

    @ViewById(R.id.etSafetyMeasure)
    FormEditText etSafetyMeasure;

    @ViewById(R.id.etActualStopTime)
    FormEditText etActualStopTime;

    @ViewById(R.id.etEndStopTime)
    FormEditText etEndStopTime;

    @ViewById(R.id.etInspector)
    FormEditText etInspector;

    @ViewById(R.id.etInspect_content)
    org.apmem.tools.layouts.FlowLayout etInspectContent;

    @ViewById(R.id.etComplete)
    FormEditText etComplete;

    @ViewById(R.id.etEndHandleTime)
    FormEditText etEndHandleTime;

    @ViewById(R.id.etIsHandled)
    Spinner etIsHandled;

    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;


    @AfterViews
    void bindAdapter(){

        super.afterInitView("农配网工程", getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime,etTaskNum,etEngineeringName,etEngineeringNum,etWorkContent,etWorkPlace,etStopScope,etStopTime,etWorkLicensor,etBeginHandleTime,etWorkInvoiceNum,etExecutionResponsible,etWorkResponsible,etWorkContent2,etSafetyMeasure,etActualStopTime,etEndStopTime,etInspector,etComplete,etEndHandleTime,etUnhandleReason};
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etExecutionCompany, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

    }




    @Background
    void getEntityFromServer(){
        distributionNetworkEngineeringResult = distributionNetworkEngineeringClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(distributionNetworkEngineeringResult == null){

            distributionNetworkEngineeringResult = new DistributionNetworkEngineeringResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(distributionNetworkEngineeringResult.assignmentTime);
            etTaskNum.setText(distributionNetworkEngineeringResult.taskNum);
            etEngineeringName.setText(distributionNetworkEngineeringResult.engineeringName);
            etEngineeringNum.setText(distributionNetworkEngineeringResult.engineeringNum);
            etAreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, distributionNetworkEngineeringResult.areaName));
            etExecutionCompany.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, distributionNetworkEngineeringResult.executionCompany));
            etWorkContent.setText(distributionNetworkEngineeringResult.workContent);
            etWorkPlace.setText(distributionNetworkEngineeringResult.workPlace);
            etStopScope.setText(distributionNetworkEngineeringResult.stopScope);
            etStopTime.setText(distributionNetworkEngineeringResult.stopTime);
            etWorkLicensor.setText(distributionNetworkEngineeringResult.workLicensor);
            etBeginHandleTime.setText(distributionNetworkEngineeringResult.beginHandleTime);
            etWorkInvoiceNum.setText(distributionNetworkEngineeringResult.workInvoiceNum);
            etExecutionResponsible.setText(distributionNetworkEngineeringResult.executionResponsible);
            etWorkResponsible.setText(distributionNetworkEngineeringResult.workResponsible);
            etWorkContent2.setText(distributionNetworkEngineeringResult.workContent2);
            etSafetyMeasure.setText(distributionNetworkEngineeringResult.safetyMeasure);
            etActualStopTime.setText(distributionNetworkEngineeringResult.actualStopTime);
            etEndStopTime.setText(distributionNetworkEngineeringResult.endStopTime);
            etInspector.setText(distributionNetworkEngineeringResult.inspector);
            etComplete.setText(distributionNetworkEngineeringResult.complete);
            etEndHandleTime.setText(distributionNetworkEngineeringResult.endHandleTime);
            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, distributionNetworkEngineeringResult.isHandled));
            etUnhandleReason.setText(distributionNetworkEngineeringResult.unhandleReason);


            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(distributionNetworkEngineeringResult.inspect, etInspectContent);

    }


    @Click(R.id.btn_add_imageInspect)
    void addImageInspect(){

        super.showChooseImage(inspectList, etInspectContent);

    }



    /**
     * 新增
     */
    @Override
    void add(){

        distributionNetworkEngineeringResult.assignByUserID = userPrefs.id().get();
        distributionNetworkEngineeringResult.userID = userPrefs.id().get();

        distributionNetworkEngineeringClient.add(distributionNetworkEngineeringResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){

        MultiValueMap<String, Object> data = null;
        try {
            data = distributionNetworkEngineeringResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, DistributionNetworkEngineeringResult.Inspect, inspectList, this);
        distributionNetworkEngineeringClient.update(data);

    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.distributionNetworkEngineeringResult.assignmentTime = etAssignmentTime.getText().toString();

            this.distributionNetworkEngineeringResult.taskNum = etTaskNum.getText().toString();

            this.distributionNetworkEngineeringResult.engineeringName = etEngineeringName.getText().toString();

            this.distributionNetworkEngineeringResult.engineeringNum = etEngineeringNum.getText().toString();

            this.distributionNetworkEngineeringResult.areaName = etAreaName.getSelectedItem().toString();

            this.distributionNetworkEngineeringResult.executionCompany = etExecutionCompany.getSelectedItem().toString();

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

            this.distributionNetworkEngineeringResult.complete = etComplete.getText().toString();

            this.distributionNetworkEngineeringResult.endHandleTime = etEndHandleTime.getText().toString();

            this.distributionNetworkEngineeringResult.isHandled = etIsHandled.getSelectedItem().toString();

            this.distributionNetworkEngineeringResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}