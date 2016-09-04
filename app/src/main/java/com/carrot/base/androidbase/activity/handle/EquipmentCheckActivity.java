package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_equipment_check)
@OptionsMenu(R.menu.task_item)
public class EquipmentCheckActivity extends AppCompatActivity{


    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;


    List<PhotoInfo> defectContentPicList;

    EquipmentCheckResult equipmentCheckResult;


    @RestService
    EquipmentCheckClient equipmentCheckClient;


    ProgressDialog progress;


    //保存状态, 0: add, 1: update
    private int saveStatus = 0;

    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;

    @ViewById(R.id.et_assignment_time)
    EditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    EditText etTaskNum;

    @ViewById(R.id.et_check_type)
    EditText etCheckType;

    @ViewById(R.id.et_check_scope)
    EditText etCheckScope;

    @ViewById(R.id.et_safety_measure)
    EditText etSafetyMeasure;

    @ViewById(R.id.et_end_time)
    EditText etEndTime;

    @ViewById(R.id.et_begin_handle_time)
    EditText etBeginHandleTime;

    @ViewById(R.id.et_exist_defect)
    EditText etExistDefect;

    @ViewById(R.id.et_defect_place)
    EditText etDefectPlace;

    @ViewById(R.id.et_defect_content)
    org.apmem.tools.layouts.FlowLayout etDefectContent;

    @ViewById(R.id.et_defect_level)
    EditText etDefectLevel;

    @ViewById(R.id.et_handle_content)
    EditText etHandleContent;

    @ViewById(R.id.et_check_people)
    EditText etCheckpeople;

    @ViewById(R.id.et_check_time)
    EditText etCheckTime;

    @ViewById(R.id.et_end_handle_time)
    EditText etEndHandleTime;

    @ViewById(R.id.et_is_handled)
    EditText etIsHandled;

    @ViewById(R.id.et_unhandle_reason)
    EditText etUnhandleReason;



    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle(TypeUtils.TYPE_2_3);

        defectContentPicList = new ArrayList<>();

        progress = new ProgressDialog(this);

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
    }


    @UiThread
    void refreshView(){
        if(equipmentCheckResult == null){

            equipmentCheckResult = new EquipmentCheckResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            //test start

             etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etTaskNum.setText("Task num"+ DateUtils.getCurrentYYYY_MM_DD());
             etCheckType.setText("type");
             etCheckScope.setText("checkscope");
             etSafetyMeasure.setText("safeme");
             etEndTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etBeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
             etExistDefect.setText("defect");
             etDefectPlace.setText("place");
//             etDefectContent.setText("content");
             etDefectLevel.setText("level");
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

            etCheckType.setText(equipmentCheckResult.checkType);
            etCheckScope.setText(equipmentCheckResult.checkScope);
            etSafetyMeasure.setText(equipmentCheckResult.safetyMeasure);
            etEndTime.setText(equipmentCheckResult.endTime);
            etBeginHandleTime.setText(equipmentCheckResult.beginHandleTime);
            etExistDefect.setText(equipmentCheckResult.existDefect);
            etDefectPlace.setText(equipmentCheckResult.defectPlace);
//            etDefectContent.setText(equipmentCheckResult.defectContent);
            etDefectLevel.setText(equipmentCheckResult.defectLevel);
            etHandleContent.setText(equipmentCheckResult.handleContent);
            etCheckpeople.setText(equipmentCheckResult.checkPeople);
            etCheckTime.setText(equipmentCheckResult.checkTime);
            etEndHandleTime.setText(equipmentCheckResult.endHandleTime);
            etIsHandled.setText(equipmentCheckResult.isHandled);
            etUnhandleReason.setText(equipmentCheckResult.unhandleReason);

            this.saveStatus = 1;
        }
        if(progress != null){
            progress.dismiss();
        }
    }


    @UiThread
    void showLoading(){
        progress.setTitle("Loading");
        progress.show();
    }


    @Click(R.id.btn_add_image)
    void addImage(){
        Log.i("sslog", "equipment check activity add image");

        //带配置
        final FunctionConfig config = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(8)
                .setEnableRotate(true)
                .setEnableCamera(true)
                .build();

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptView = layoutInflater.inflate(R.layout.dialog_photo, null);


        final AlertDialog alertD = new AlertDialog.Builder(this).create();
        Button btnAdd1 = (Button) promptView.findViewById(R.id.btn_take_photo);

        Button btnAdd2 = (Button) promptView.findViewById(R.id.btn_grally);


        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertD.dismiss();
                //带配置
                GalleryFinal.openCamera(1, config, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        finishGetPhoto(resultList);

                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });

            }
        });

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alertD.dismiss();
                GalleryFinal.openGalleryMuti(1, config, new GalleryFinal.OnHanlderResultCallback(){
                    /**
                     * 处理成功
                     * @param reqeustCode
                     * @param resultList
                     */
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList){
                        finishGetPhoto(resultList);

                    }

                    /**
                     * 处理失败或异常
                     * @param requestCode
                     * @param errorMsg
                     */
                    public void onHanlderFailure(int requestCode, String errorMsg){

                    }
                });
            }
        });

        alertD.setView(promptView);

        alertD.show();
    }

    void finishGetPhoto(List<PhotoInfo> resultList){
        for (PhotoInfo pi : resultList){

            defectContentPicList.add(pi);

            File file = new File(pi.getPhotoPath());

            if(file.exists()){
                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                ImageView imageView = new ImageView(getApplicationContext());

                imageView.setImageBitmap(bitmap);

                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                Log.i("sslog", "width:"+bitmap.getWidth() + ", height" +bitmap.getHeight());

                int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
                int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60*(bitmap.getHeight()/bitmap.getWidth()), getResources().getDisplayMetrics());

                int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());

                imageView.setLayoutParams(new GridView.LayoutParams(width, width));
                imageView.setPadding(padding, padding, padding, padding);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("sslog", "image clicked");
                    }
                });

                imageView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        Log.i("sslog", "image long clicked");

                        return false;
                    }
                });

                etDefectContent.addView(imageView);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:

                conform();
                return true;
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Background
    void conform(){
        if(validate() == false){
            return;
        }

        if(saveStatus == 0){ //add
            equipmentCheckResult.assignByUserID = userPrefs.id().get();
            equipmentCheckResult.userId = userPrefs.id().get();

            equipmentCheckClient.add(equipmentCheckResult);

        }else{ //update

            MultiValueMap<String, Object> data = equipmentCheckResult.parseToMultiValueMap();

            equipmentCheckClient.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);

            //defectContentPicList image
            List<File> files = new ArrayList<>();

            try {
                for(int i = 0; i < defectContentPicList.size(); i++){
                    PhotoInfo pi = defectContentPicList.get(i);
                    File file = new File(pi.getPhotoPath());
                    files.add(file);

                    final String filename = file.getName();


                    byte[] bytes = new byte[(int) file.length()];


                    BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));

                    buf.read(bytes, 0, bytes.length);
                    buf.close();


                    ByteArrayResource contentsAsResource = new ByteArrayResource(bytes){
                        @Override
                        public String getFilename(){
                            return filename;
                        }
                    };;


                    data.add("DefectContentPic", contentsAsResource);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TODO
//            data.add("DefectContentPic", files);

            equipmentCheckClient.update(data);
        }

        Intent intent = new Intent();
        setResult(ResultCodeConstant.RESULT_CODE_REFRESH, intent);
        finish();
    }


    boolean validate(){

        String error = "不能为空";

        //TODO

//        if(etAssignmentTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "任务派发"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.assignmentTime = etAssignmentTime.getText().toString();
//
//
//        if(etTaskNum.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "任务编号"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.taskNum = etTaskNum.getText().toString();
//
//
//        if(etCheckType.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "巡视种类"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.checkType = etCheckType.getText().toString();
//
//
//        if(etCheckScope.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "巡视范围"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.checkScope = etCheckScope.getText().toString();
//
//
//        if(etSafetyMeasure.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "安全措施"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.safetyMeasure = etSafetyMeasure.getText().toString();
//
//
//        if(etEndTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "结束时间"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.endTime = etEndTime.getText().toString();
//
//
//        if(etBeginHandleTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "巡视内容"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.beginHandleTime = etBeginHandleTime.getText().toString();
//
//
//        if(etExistDefect.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "存在问题"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.existDefect = etExistDefect.getText().toString();
//
//
//        if(etDefectPlace.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "缺陷位置"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.defectPlace = etDefectPlace.getText().toString();
//
//
////        if(etDefectContent.getText().toString().equals("")){
////            Toast.makeText(getApplicationContext(), "缺陷内容"+error, Toast.LENGTH_SHORT).show();
////            return false;
////        }
////        this.equipmentCheckResult.defectContent = etDefectContent.getText().toString();
//
//
//        if(etDefectLevel.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "缺陷等级"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.defectLevel = etDefectLevel.getText().toString();
//
//
//        if(etHandleContent.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "处理情况"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.handleContent = etHandleContent.getText().toString();
//
//
//        if(etCheckpeople.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "巡视人员"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.checkPeople = etCheckpeople.getText().toString();
//
//
//        if(etCheckTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "巡视日期"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.checkTime = etCheckTime.getText().toString();
//
//
//        if(etEndHandleTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "任务结束"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.endHandleTime = etEndHandleTime.getText().toString();
//
//
//        if(etIsHandled.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "已处理"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.isHandled = etIsHandled.getText().toString();
//
//
//        if(etUnhandleReason.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "未处理"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.equipmentCheckResult.unhandleReason = etUnhandleReason.getText().toString();



        return true;
    }
}
