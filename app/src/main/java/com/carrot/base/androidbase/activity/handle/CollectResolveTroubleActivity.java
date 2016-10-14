package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.CollectResolveTroubleClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CollectResolveTroubleResult;
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
@EActivity(R.layout.activity_collect_resolve_trouble)
@OptionsMenu(R.menu.task_item)
public class CollectResolveTroubleActivity extends BaseHandlerActivity{



    List<PhotoInfo> handleContentPicList = new ArrayList<>();


    CollectResolveTroubleResult collectResolveTroubleResult;

    @RestService
    CollectResolveTroubleClient collectResolveTroubleClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.et_trouble_address)
    FormEditText etTroubleAddress;
    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;
    @ViewById(R.id.spn_trouble_reason)
    Spinner spnTroubleReason;

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

    @ViewById(R.id.et_username)
    FormEditText etUsername;
    @ViewById(R.id.et_ammeter_no)
    FormEditText etAmmeterNo;
    @ViewById(R.id.et_property_no)
    FormEditText etPropertyNo;
    @ViewById(R.id.et_status)
    FormEditText etStatus;
    @ViewById(R.id.et_worker)
    FormEditText etWorker;


    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;


    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_2, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,etSafetyMeasure,
                etUsername,etTroubleAddress,etAmmeterNo,etPropertyNo,etStatus,etEndTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etTroubleAddress,
                etSafetyMeasure,etEndTime,etBeginHandleTime,etHandleContent,etEndHandleTime,
                etUnhandleReason,
                etUsername,etAmmeterNo, etPropertyNo, etStatus, etWorker};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName};
        finishDisabledSpinnerList = new Spinner[] {spnAreaName,spnTroubleReason,spnIsHandled,};

        openDateEditTextList = new OpenDateVo[] {
            new OpenDateVo(etEndTime, OpenDateVo.UPDATE_ADD),
            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE_ADD),
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
        collectResolveTroubleClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnTroubleReason, TypeUtils.TROUBLE_REASON);
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
    }


    void getEntityFromServer(){
        collectResolveTroubleResult = collectResolveTroubleClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(collectResolveTroubleResult == null){

            collectResolveTroubleResult = new CollectResolveTroubleResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndTime.setText(DateUtils.getEndTime());

        }else{

            etAssignmentTime.setText(collectResolveTroubleResult.assignmentTime);
            etTaskNum.setText(collectResolveTroubleResult.taskNum);
            spnAreaName.setSelection(getSelectedAreaIndex(collectResolveTroubleResult.areaName));
            etTroubleAddress.setText(collectResolveTroubleResult.troubleAddress);
            etSafetyMeasure.setText(collectResolveTroubleResult.safetyMeasure);
            etEndTime.setText(collectResolveTroubleResult.endTime);
            etBeginHandleTime.setText(collectResolveTroubleResult.beginHandleTime);
            spnTroubleReason.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TROUBLE_REASON, collectResolveTroubleResult.troubleReason));
            etHandleContent.setText(collectResolveTroubleResult.handleContent);
            etEndHandleTime.setText(collectResolveTroubleResult.endHandleTime);
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, collectResolveTroubleResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(collectResolveTroubleResult.unhandleReason);


            etUsername.setText(collectResolveTroubleResult.username);
            etAmmeterNo.setText(collectResolveTroubleResult.ammeterNo);
            etPropertyNo.setText(collectResolveTroubleResult.propertyNo);
            etStatus.setText(collectResolveTroubleResult.status);
            etWorker.setText(collectResolveTroubleResult.worker);

            getImage();
        }
        if(collectResolveTroubleResult.worker == null || collectResolveTroubleResult.worker.equals("")){
            etWorker.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(collectResolveTroubleResult.handleContentPic, llHandleContent);

    }

    UpdateResult save(){

        if(collectResolveTroubleResult.iD == 0){
            collectResolveTroubleResult.assignByUserID = userPrefs.id().get();
            collectResolveTroubleResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = collectResolveTroubleResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, CollectResolveTroubleResult.HandleContentPic, handleContentPicList, this);

        return collectResolveTroubleClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.collectResolveTroubleResult.assignmentTime = etAssignmentTime.getText().toString();
            this.collectResolveTroubleResult.taskNum = etTaskNum.getText().toString();
            this.collectResolveTroubleResult.areaName = spnAreaName.getSelectedItem().toString();

            this.collectResolveTroubleResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;
            this.collectResolveTroubleResult.troubleAddress = etTroubleAddress.getText().toString();
            this.collectResolveTroubleResult.safetyMeasure = etSafetyMeasure.getText().toString();
            this.collectResolveTroubleResult.endTime = etEndTime.getText().toString();
            this.collectResolveTroubleResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.collectResolveTroubleResult.troubleReason = spnTroubleReason.getSelectedItem().toString();

            this.collectResolveTroubleResult.handleContent = etHandleContent.getText().toString();
            this.collectResolveTroubleResult.endHandleTime = etEndHandleTime.getText().toString();
            this.collectResolveTroubleResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;



            this.collectResolveTroubleResult.username = etUsername.getText().toString();
            this.collectResolveTroubleResult.ammeterNo = etAmmeterNo.getText().toString();
            this.collectResolveTroubleResult.propertyNo = etPropertyNo.getText().toString();
            this.collectResolveTroubleResult.status = etStatus.getText().toString();
            this.collectResolveTroubleResult.worker = etWorker.getText().toString();

            this.collectResolveTroubleResult.unhandleReason = etUnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
