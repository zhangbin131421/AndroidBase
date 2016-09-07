package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.ImageUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.EquipmentCheckResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_equipment_check)
@OptionsMenu(R.menu.task_item)
public class EquipmentCheckActivity extends BaseHandlerActivity{



    List<PhotoInfo> defectContentPicList = new ArrayList<>();;

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
    FormEditText etCheckScope;

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

    @ViewById(R.id.et_defect_content)
    org.apmem.tools.layouts.FlowLayout etDefectContent;

    @ViewById(R.id.et_defect_level)
    Spinner etDefectLevel;

    @ViewById(R.id.et_handle_content)
    FormEditText etHandleContent;

    @ViewById(R.id.et_check_people)
    FormEditText etCheckpeople;

    @ViewById(R.id.et_check_time)
    FormEditText etCheckTime;

    @ViewById(R.id.et_end_handle_time)
    FormEditText etEndHandleTime;

    @ViewById(R.id.et_is_handled)
    FormEditText etIsHandled;

    @ViewById(R.id.et_unhandle_reason)
    FormEditText etUnhandleReason;



    @AfterViews
    void bindAdapter(){

        super.afterInitView(TypeUtils.TYPE_2_3);

        //下拉选择框
        setDropDownListAdapter(etCheckType, TypeUtils.CHECK_TYPE);

        setDropDownListAdapter(etExistDefect, TypeUtils.EXIST_DEFECT);

        setDropDownListAdapter(etDefectLevel, TypeUtils.DEFECT_LEVEL);

        allFields = new FormEditText[] {etAssignmentTime, etTaskNum, etCheckScope, etSafetyMeasure, etEndTime,
                etBeginHandleTime, etDefectPlace, etHandleContent, etCheckpeople, etCheckTime,
                etEndHandleTime, etIsHandled, etUnhandleReason};

        getObject();

    }


    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            equipmentCheckResult = equipmentCheckClient.getById(taskBaseVo.id);
        }

        refreshView();
        dissmisLoading();
    }


    @UiThread
    void refreshView(){
        if(equipmentCheckResult == null){

            equipmentCheckResult = new EquipmentCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            //test start

             etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etTaskNum.setText("Task num"+ DateUtils.getCurrentYYYY_MM_DD());

             etCheckScope.setText("checkscope");
             etSafetyMeasure.setText("safeme");
             etEndTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etBeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
//             etExistDefect.setText("defect");
             etDefectPlace.setText("place");
//             etDefectContent.setText("content");
//             etDefectLevel.setText("level");
             etHandleContent.setText("content");
             etCheckpeople.setText("people");
             etCheckTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etEndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etIsHandled.setText(0+"");
             etUnhandleReason.setText("unreason");
            //test end
        }else{

            etAssignmentTime.setText(equipmentCheckResult.assignmentTime);
            etTaskNum.setText(equipmentCheckResult.taskNum);

//            etCheckType.setText(equipmentCheckResult.checkType);
            etCheckType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.CHECK_TYPE, equipmentCheckResult.checkType));
            etCheckScope.setText(equipmentCheckResult.checkScope);
            etSafetyMeasure.setText(equipmentCheckResult.safetyMeasure);
            etEndTime.setText(equipmentCheckResult.endTime);
            etBeginHandleTime.setText(equipmentCheckResult.beginHandleTime);
//            etExistDefect.setText(equipmentCheckResult.existDefect);
            etExistDefect.setSelection(TypeUtils.getSelectedIndex(TypeUtils.EXIST_DEFECT, equipmentCheckResult.existDefect));
            etDefectPlace.setText(equipmentCheckResult.defectPlace);
//            etDefectContent.setText(equipmentCheckResult.defectContent);
//            etDefectLevel.setText(equipmentCheckResult.defectLevel);

            etDefectLevel.setSelection(TypeUtils.getSelectedIndex(TypeUtils.DEFECT_LEVEL, equipmentCheckResult.defectLevel));
            etHandleContent.setText(equipmentCheckResult.handleContent);
            etCheckpeople.setText(equipmentCheckResult.checkPeople);
            etCheckTime.setText(equipmentCheckResult.checkTime);
            etEndHandleTime.setText(equipmentCheckResult.endHandleTime);
            etIsHandled.setText(equipmentCheckResult.isHandled);
            etUnhandleReason.setText(equipmentCheckResult.unhandleReason);

            getImageFromURL();


            this.saveStatus = 1;
        }

    }

    @Background
    void getImageFromURL(){
        if(equipmentCheckResult.defectContentPic != null){
            for (String url : equipmentCheckResult.defectContentPic.split(";")){

                ImageView imageView = ImageUtils.getImageViewFromURL(url,getApplicationContext(), getResources());
                addImage(imageView);
             }
        }
    }

    @UiThread
    void addImage(ImageView imageView){
        if(etDefectContent != null && imageView != null){
            etDefectContent.addView(imageView);
        }
    }

    @Click(R.id.btn_add_image)
    void addImageLocal(){
        Log.i("sslog", "equipment check activity add image");

        super.showChooseImage(defectContentPicList, etDefectContent);

    }



    /**
     * 新增
     */
    @Override
    void add(){
        super.add();

        equipmentCheckResult.assignByUserID = userPrefs.id().get();
        equipmentCheckResult.userId = userPrefs.id().get();

        equipmentCheckClient.add(equipmentCheckResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){
        super.update();

        MultiValueMap<String, Object> data = null;
        data = equipmentCheckResult.parseToMultiValueMap();

        equipmentCheckClient.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);

        FileUtils.addImageToData(data, "DefectContentPic", defectContentPicList, this);

        equipmentCheckClient.update(data);
    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.equipmentCheckResult.assignmentTime = etAssignmentTime.getText().toString();

            this.equipmentCheckResult.taskNum = etTaskNum.getText().toString();

            this.equipmentCheckResult.checkType = etCheckType.getSelectedItem().toString();

            this.equipmentCheckResult.checkScope = etCheckScope.getText().toString();

            this.equipmentCheckResult.safetyMeasure = etSafetyMeasure.getText().toString();

            this.equipmentCheckResult.endTime = etEndTime.getText().toString();

            this.equipmentCheckResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.equipmentCheckResult.existDefect = etExistDefect.getSelectedItem().toString();

            this.equipmentCheckResult.defectPlace = etDefectPlace.getText().toString();

            this.equipmentCheckResult.defectLevel = etDefectLevel.getSelectedItem().toString();

            this.equipmentCheckResult.handleContent = etHandleContent.getText().toString();

            this.equipmentCheckResult.checkPeople = etCheckpeople.getText().toString();

            this.equipmentCheckResult.checkTime = etCheckTime.getText().toString();

            this.equipmentCheckResult.endHandleTime = etEndHandleTime.getText().toString();

            this.equipmentCheckResult.isHandled = etIsHandled.getText().toString();

            this.equipmentCheckResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
