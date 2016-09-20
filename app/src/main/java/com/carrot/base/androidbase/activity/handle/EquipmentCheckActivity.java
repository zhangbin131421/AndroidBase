package com.carrot.base.androidbase.activity.handle;

import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TaskUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.EquipmentCheckResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.core.NestedRuntimeException;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_equipment_check)
@OptionsMenu(R.menu.task_item)
public class EquipmentCheckActivity extends BaseHandlerActivity{



    List<PhotoInfo> defectContentPicList = new ArrayList<>();

    EquipmentCheckResult equipmentCheckResult;

    @RestService
    EquipmentCheckClient equipmentCheckClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;

    @ViewById(R.id.et_check_type)
    Spinner etCheckType;

    @ViewById(R.id.et_check_scope)
    Spinner etCheckScope;

    @ViewById(R.id.et_safety_measure)
    FormEditText etSafetyMeasure;

    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;

    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;

    @ViewById(R.id.et_exist_defect)
    Spinner etExistDefect;

    @ViewById(R.id.et_defect_place)
    FormEditText etDefectPlace;

    @ViewById(R.id.et_defect_content_pic)
    org.apmem.tools.layouts.FlowLayout etDefectContentPic;

    @ViewById(R.id.et_defect_content)
    FormEditText etDefectContent;

    @ViewById(R.id.et_defect_level)
    Spinner etDefectLevel;

    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;

    @ViewById(R.id.et_check_people)
    FormEditText etCheckpeople;


    @ViewById(R.id.et_report)
    Spinner etReport;

    @ViewById(R.id.et_check_time)
    FormEditText etCheckTime;

    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;

    @ViewById(R.id.et_is_handled)
    Spinner etIsHandled;

    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;

    @ViewById(R.id.ll_has_defect)
    LinearLayout llHasDefect;

    @ViewById(R.id.llIsHandler)
    LinearLayout llIsHandler;


    @ViewById(R.id.btn_add_image)
    ImageView imageAdd;

    @AfterViews
    void bindAdapter(){

        equipmentCheckClient.setRestErrorHandler(ssErrorHandler);

        allFields = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime,
                etBeginHandleTime, etDefectPlace, etHandleContent, etDefectContent, etCheckpeople, etCheckTime,
                etEndHandleTime, etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime};

        updateDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime};

        finishDisableList = new FormEditText[] {etAssignmentTime, etTaskNum, etSafetyMeasure, etEndTime,
                etBeginHandleTime, etDefectPlace, etHandleContent, etCheckpeople, etCheckTime,
                etEndHandleTime, etUnhandleReason, etDefectContent};

        updateDisabledSpinnerList = new Spinner[] {etCheckType, etCheckScope};
        finishDisabledSpinnerList = new Spinner[] {etCheckType, etCheckScope, etExistDefect, etIsHandled, etDefectLevel, etReport};

        imageAddButtonList = new ImageView[] {imageAdd};

        openDateEditTextList = new OpenDateVo[] {
                new OpenDateVo(etEndTime, 1),
                new OpenDateVo(etCheckTime, 10),
                new OpenDateVo(etEndHandleTime, 10),
                new OpenDateVo(etBeginHandleTime, 10)
        };

        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(imageAdd, defectContentPicList, etDefectContentPic)
        };

        showBySpinnerList = new ShowBySpinnerVo[]{
                new ShowBySpinnerVo(etExistDefect, llHasDefect, "有", new FormEditText[]{etDefectPlace, etHandleContent, etDefectContent}),
                new ShowBySpinnerVo(etIsHandled, llIsHandler, "未处理", new FormEditText[]{etUnhandleReason})
        };

        super.afterInitView(TypeUtils.TYPE_2_3, getApplicationContext(), getResources());

    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etCheckType, TypeUtils.CHECK_TYPE);

        setDropDownListAdapter(etExistDefect, TypeUtils.EXIST_DEFECT);

        setDropDownListAdapter(etDefectLevel, TypeUtils.DEFECT_LEVEL);

        setDropDownListAdapter(etReport, TypeUtils.TASK_REPORT);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

        setDropDownListAdapter(etCheckScope, DataInstance.getInstance().areaInformationResults);
    }


    void getEntityFromServer(){
        equipmentCheckResult = equipmentCheckClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(equipmentCheckResult == null){

            equipmentCheckResult = new EquipmentCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etTaskNum.setText(TaskUtils.generatTaskNum());
            etSafetyMeasure.setText("一人监督一人操作");
            etEndTime.setText(DateUtils.getEndTime());

            etCheckpeople.setText(userPrefs.name().get());
            etCheckTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(equipmentCheckResult.assignmentTime.substring(0, 10));
            etTaskNum.setText(equipmentCheckResult.taskNum);

            etCheckType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.CHECK_TYPE, equipmentCheckResult.checkType));
            etCheckScope.setSelection(getSelectedAreaIndex(equipmentCheckResult.checkScope));
            etSafetyMeasure.setText(equipmentCheckResult.safetyMeasure);
            etEndTime.setText(equipmentCheckResult.endTime.substring(0, 10));
            if(equipmentCheckResult.beginHandleTime == null || equipmentCheckResult.beginHandleTime.equals("")){
                etBeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            }else{
                etBeginHandleTime.setText(equipmentCheckResult.beginHandleTime.substring(0, 10));
            }

            etExistDefect.setSelection(TypeUtils.getSelectedIndex(TypeUtils.EXIST_DEFECT, equipmentCheckResult.existDefect));
            etDefectPlace.setText(equipmentCheckResult.defectPlace);
            etDefectContent.setText(equipmentCheckResult.defectContent);

            etReport.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TASK_REPORT, equipmentCheckResult.isReportPlan == 0 ? "否" : "是"));

            etDefectLevel.setSelection(TypeUtils.getSelectedIndex(TypeUtils.DEFECT_LEVEL, equipmentCheckResult.defectLevel));
            etHandleContent.setText(equipmentCheckResult.handleContent);

            if(equipmentCheckResult.checkPeople == null || equipmentCheckResult.checkPeople.equals("")){
                etCheckpeople.setText(userPrefs.name().get());
            }else{
                etCheckpeople.setText(equipmentCheckResult.checkPeople);
            }

            if(equipmentCheckResult != null && equipmentCheckResult.checkTime != null){
                etCheckTime.setText(equipmentCheckResult.checkTime.substring(0, 10));
            }else{
                etCheckTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            }
            if(equipmentCheckResult.endHandleTime == null || equipmentCheckResult.endHandleTime.equals("")){
                etEndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            }else{
                etEndHandleTime.setText(equipmentCheckResult.endHandleTime.substring(0, 10));
            }

            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, equipmentCheckResult.isHandled == 2 ? "未处理" : "已处理"));


            etUnhandleReason.setText(equipmentCheckResult.unhandleReason);

            getImage();
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(equipmentCheckResult.defectContentPic, etDefectContentPic);

    }

    void save(){

        if(equipmentCheckResult.id == 0){
            equipmentCheckResult.assignByUserID = userPrefs.id().get();
            equipmentCheckResult.userId = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = equipmentCheckResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, EquipmentCheckResult.DefectContentPic, defectContentPicList, this);

        equipmentCheckClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.equipmentCheckResult.assignmentTime = etAssignmentTime.getText().toString();

            this.equipmentCheckResult.taskNum = etTaskNum.getText().toString();

            this.equipmentCheckResult.checkType = etCheckType.getSelectedItem().toString();

            this.equipmentCheckResult.checkScope = ((AreaInformationResult)etCheckScope.getSelectedItem()).id+"";

            this.equipmentCheckResult.safetyMeasure = etSafetyMeasure.getText().toString();

            this.equipmentCheckResult.endTime = etEndTime.getText().toString();

            this.equipmentCheckResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.equipmentCheckResult.existDefect = etExistDefect.getSelectedItem().toString();

            if(!this.equipmentCheckResult.existDefect.equals("无") ){
                this.equipmentCheckResult.defectPlace = etDefectPlace.getText().toString();
                this.equipmentCheckResult.defectContent = etDefectContent.getText().toString();
                this.equipmentCheckResult.defectLevel = etDefectLevel.getSelectedItem().toString();
                this.equipmentCheckResult.handleContent = etHandleContent.getText().toString();
                this.equipmentCheckResult.isReportPlan = etReport.getSelectedItem().toString().equals("否") ? 0 : 1;

            }else{
                this.equipmentCheckResult.defectPlace = "";
                this.equipmentCheckResult.defectContent = "";
                this.equipmentCheckResult.defectLevel = "";
                this.equipmentCheckResult.handleContent = "";
                this.equipmentCheckResult.isReportPlan = -1;
            }

            this.equipmentCheckResult.checkPeople = etCheckpeople.getText().toString();
            this.equipmentCheckResult.checkTime = etCheckTime.getText().toString();

            this.equipmentCheckResult.endHandleTime = etEndHandleTime.getText().toString();

            this.equipmentCheckResult.isHandled = etIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            if(this.equipmentCheckResult.isHandled == 1){//已处理
                this.equipmentCheckResult.unhandleReason = "";

            }else if(this.equipmentCheckResult.isHandled == 2) {//未处理
                this.equipmentCheckResult.unhandleReason = etUnhandleReason.getText().toString();

            }



            return true;
        }{
            return false;
        }
    }

}
