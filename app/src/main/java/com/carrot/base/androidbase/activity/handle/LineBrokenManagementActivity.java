package com.carrot.base.androidbase.activity.handle;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.util.MultiValueMap;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.LineBrokenManagementClient;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.EquipmentCheckResult;
import com.carrot.base.androidbase.vo.result.LineBrokenManagementResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

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


    List<PhotoInfo> handleContentPicList = new ArrayList<>();;

    LineBrokenManagementResult lineBrokenManagementResult;

    @RestService
    LineBrokenManagementClient lineBrokenManagementClient;


    @ViewById(R.id.et_AssignmentTime)
    FormEditText et_AssignmentTime;
    @ViewById(R.id.et_TaskNum)
    FormEditText et_TaskNum;
    @ViewById(R.id.et_AreaName)
    Spinner et_AreaName;
    @ViewById(R.id.et_BrokenRate)
    FormEditText et_BrokenRate;
    @ViewById(R.id.et_SafetyMeasure)
    Spinner et_SafetyMeasure;
    @ViewById(R.id.et_BeginHandleTime)
    FormEditText et_BeginHandleTime;
    @ViewById(R.id.et_UnqualifiedReason)
    Spinner et_UnqualifiedReason;
    @ViewById(R.id.et_HandleContent)
    org.apmem.tools.layouts.FlowLayout et_HandleContent;
    @ViewById(R.id.et_UserID)
    FormEditText et_UserID;
    @ViewById(R.id.et_EndHandleTiem)
    FormEditText et_EndHandleTiem;
    @ViewById(R.id.et_IsHandled)
    FormEditText et_IsHandled;
    @ViewById(R.id.et_UnhandleReason)
    FormEditText et_UnhandleReason;

    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_1_1, getApplicationContext(), getResources());

    }


    public void setValidateList(){

        allFields = new FormEditText[] {et_AssignmentTime, et_TaskNum,
                et_BrokenRate, et_BeginHandleTime,
                et_UserID, et_EndHandleTiem, et_IsHandled, et_UnhandleReason};

    }


    @Override
    public void setErrorHandler(){
        lineBrokenManagementClient.setRestErrorHandler(ssErrorHandler);
    }


    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(et_AreaName, TypeUtils.AREA);

        setDropDownListAdapter(et_SafetyMeasure, TypeUtils.SAFETY_MEASURE);

        setDropDownListAdapter(et_UnqualifiedReason, TypeUtils.UNQUALIFIED_REASON);
    }



    @Background
    void getEntityFromServer(){
        lineBrokenManagementResult = lineBrokenManagementClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(lineBrokenManagementResult == null){

            lineBrokenManagementResult = new LineBrokenManagementResult();

            et_AssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{
            et_AssignmentTime.setText(lineBrokenManagementResult.assignmentTime);
            et_TaskNum.setText(lineBrokenManagementResult.taskNum);
            et_AreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.AREA, lineBrokenManagementResult.areaName));
            et_BrokenRate.setText(lineBrokenManagementResult.brokenRate);
            et_SafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.SAFETY_MEASURE, lineBrokenManagementResult.safetyMeasure));
            et_BeginHandleTime.setText(lineBrokenManagementResult.beginHandleTime);
            et_UnqualifiedReason.setSelection(TypeUtils.getSelectedIndex(TypeUtils.UNQUALIFIED_REASON, lineBrokenManagementResult.unqualifiedReason));
//            et_HandleContent.setText(lineBrokenManagementResult.handleContent);
            et_UserID.setText(lineBrokenManagementResult.userId);
            et_EndHandleTiem.setText(lineBrokenManagementResult.endHandleTiem);
            et_IsHandled.setText(lineBrokenManagementResult.isHandled);
            et_UnhandleReason.setText(lineBrokenManagementResult.unhandleReason);



            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(lineBrokenManagementResult.handleContentPic, et_HandleContent);

    }


    @Click(R.id.btn_add_image)
    void addImageLocal(){

        super.showChooseImage(handleContentPicList, et_HandleContent);

    }



    /**
     * 新增
     */
    @Override
    UpdateResult save(){

        if(lineBrokenManagementResult.id == 0){
            lineBrokenManagementResult.assignByUserID = userPrefs.id().get();
            lineBrokenManagementResult.userId = userPrefs.id().get();
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

    @Override
    boolean validate(){

        if(super.validate()) {

            this.lineBrokenManagementResult.assignmentTime = et_AssignmentTime.getText().toString();
            this.lineBrokenManagementResult.taskNum = et_TaskNum.getText().toString();
            this.lineBrokenManagementResult.areaName = et_AreaName.getSelectedItem().toString();
            this.lineBrokenManagementResult.brokenRate = et_BrokenRate.getText().toString();
            this.lineBrokenManagementResult.safetyMeasure = et_SafetyMeasure.getSelectedItem().toString();
            this.lineBrokenManagementResult.beginHandleTime = et_BeginHandleTime.getText().toString();
            this.lineBrokenManagementResult.unqualifiedReason = et_UnqualifiedReason.getSelectedItem().toString();
//            this.lineBrokenManagementResult.handleContent = et_HandleContent.getText().toString();
            this.lineBrokenManagementResult.userId = Integer.parseInt(et_UserID.getText().toString());
            this.lineBrokenManagementResult.endHandleTiem = et_EndHandleTiem.getText().toString();
            this.lineBrokenManagementResult.isHandled = et_IsHandled.getText().toString();
            this.lineBrokenManagementResult.unhandleReason = et_UnhandleReason.getText().toString();


            return true;
        }{
            return false;
        }
    }


}
