package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.carrot.base.androidbase.MainActivity;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.image.UILImageLoader;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;
import org.androidannotations.annotations.res.TextArrayRes;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.File;
import java.util.Date;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_core_meter_test)
@OptionsMenu(R.menu.task_item)
public class CoreMeterTestActivity extends AppCompatActivity{


    public static final String IMAGE_TYPE = "image/*";


    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;

    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;

    CoreMeterTestResult coreMeterTestResult;

    ProgressDialog progress;

    @Pref
    UserPrefs_ userPrefs;


    @ViewById(R.id.et_assignment_time)
    EditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    EditText etTaskNum;
    @ViewById(R.id.et_area_name)
    EditText etAreaName;
    @ViewById(R.id.et_protect_line)
    EditText etProtectLine;
    @ViewById(R.id.et_type)
    EditText etType;
    @ViewById(R.id.et_safety_measure)
    EditText etSafetyMeasure;
    @ViewById(R.id.et_end_time)
    EditText etEndTime;
    @ViewById(R.id.et_begin_handle_time)
    EditText etBeginHandleTime;
    @ViewById(R.id.et_wether)
    EditText etWether;
    @ViewById(R.id.et_test_way)
    EditText etTestWay;
    @ViewById(R.id.et_a_testing)
    org.apmem.tools.layouts.FlowLayout etATesting;
    @ViewById(R.id.et_b_testing)
    EditText etBTesting;
    @ViewById(R.id.et_c_testing)
    EditText etCTesting;
    @ViewById(R.id.et_test_result)
    EditText etTestResult;
    @ViewById(R.id.et_handle_content)
    EditText etHandleContent;
    @ViewById(R.id.et_tester)
    EditText etTester;
    @ViewById(R.id.et_testing_time)
    EditText etTestingTime;
    @ViewById(R.id.et_end_handle_time)
    EditText etEndHandleTime;
    @ViewById(R.id.et_is_handled)
    EditText etIsHandled;
    @ViewById(R.id.et_unhandle_reason)
    EditText etUnhandleReason;

    @StringArrayRes(R.array.constant_ares_name)
    String[] areaNameList;

    //保存状态, 0: add, 1: update
    private int saveStatus = 0;


    @RestService
    CoreMeterTestClient coreMeterTestClient; //Inject it


    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progress = new ProgressDialog(this);

        getObject();
    }


    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            coreMeterTestResult = coreMeterTestClient.getById(taskBaseVo.id);
        }

        refreshView();
    }

    @UiThread
    void showLoading(){
        progress.setTitle("Loading");
        progress.show();
    }


    @UiThread
    void refreshView(){
        if(coreMeterTestResult == null){

            coreMeterTestResult = new CoreMeterTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            //test start
            etEndTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etTestingTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etBeginHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());
            etEndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());

            etTaskNum.setText(DateUtils.getCurrentSecond());
            etAreaName.setText("area"+DateUtils.getCurrentSecond2());
            etProtectLine.setText("productionline"+DateUtils.getCurrentSecond2());
            etType.setText("type"+DateUtils.getCurrentSecond2());
            etSafetyMeasure.setText("safetyM"+DateUtils.getCurrentSecond2());
            etWether.setText("wether"+DateUtils.getCurrentSecond2());
            etTestWay.setText("testway"+DateUtils.getCurrentSecond2());
//            etATesting.setText("atest"+DateUtils.getCurrentSecond2());
            etBTesting.setText("btest"+DateUtils.getCurrentSecond2());
            etCTesting.setText("ctest"+DateUtils.getCurrentSecond2());
            etTestResult.setText("testResult"+DateUtils.getCurrentSecond2());
            etHandleContent.setText("措施"+DateUtils.getCurrentSecond2());
            etTester.setText("tester"+DateUtils.getCurrentSecond2());
            etIsHandled.setText("0");
            etUnhandleReason.setText("未处理原因"+DateUtils.getCurrentSecond2());
            //test end
        }else{

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


            etEndTime.setText(coreMeterTestResult.endTime);
            etTestingTime.setText(coreMeterTestResult.testingTime);
            etBeginHandleTime.setText(coreMeterTestResult.beginHandleTime);
            etEndHandleTime.setText(coreMeterTestResult.endHandleTime);

            etTaskNum.setText(coreMeterTestResult.taskNum);
            etAreaName.setText(coreMeterTestResult.areaName);
            etProtectLine.setText(coreMeterTestResult.protectLine);
            etType.setText(coreMeterTestResult.type);
            etSafetyMeasure.setText(coreMeterTestResult.safetyMeasure);
            etWether.setText(coreMeterTestResult.wether);
            etTestWay.setText(coreMeterTestResult.testWay);
//            etATesting.setText(coreMeterTestResult.aTesting);
            etBTesting.setText(coreMeterTestResult.bTesting);
            etCTesting.setText(coreMeterTestResult.cTesting);
            etTestResult.setText(coreMeterTestResult.testResult);
            etHandleContent.setText(coreMeterTestResult.handleContent);
            etTester.setText(coreMeterTestResult.tester);
            etIsHandled.setText(coreMeterTestResult.isHandled+"");
            etUnhandleReason.setText(coreMeterTestResult.unhandleReason);

            this.saveStatus = 1;
        }
        if(progress != null){
            progress.dismiss();
        }
    }

    @Click(R.id.btn_add_image)
    void addImage(){
        Log.i("sslog", "add image");

//带配置
        FunctionConfig config = new FunctionConfig.Builder()
                .setMutiSelectMaxSize(8)
                .setEnableRotate(true)
                .setEnableCamera(true)
                .build();
        GalleryFinal.openGalleryMuti(1, config, new GalleryFinal.OnHanlderResultCallback(){
            /**
             * 处理成功
             * @param reqeustCode
             * @param resultList
             */
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList){

                for (PhotoInfo pi : resultList){

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



                        etATesting.addView(imageView);
                    }
                }
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:

                confirm();
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
    void confirm(){

        if(validate() == false){
            return;
        }

        if(saveStatus == 0){ //add
            coreMeterTestResult.assignByUserID = userPrefs.id().get();
            coreMeterTestResult.userId = userPrefs.id().get();
//            coreMeterTestResult.createdTime = DateUtils.getCurrent();

            coreMeterTestClient.add(coreMeterTestResult);

        }else{ //update

            MultiValueMap<String, Object> data = coreMeterTestResult.parseToMultiValueMap();

            coreMeterTestClient.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);

            coreMeterTestClient.update(data);

//            coreMeterTestClient.update(coreMeterTestResult);

        }

        Intent intent = new Intent();
        setResult(ResultCodeConstant.RESULT_CODE_REFRESH, intent);
        finish();
    }

    boolean validate(){

        String error = "不能为空";

//        if(etAssignmentTime.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "任务派发"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.coreMeterTestResult.assignmentTime = etAssignmentTime.getText().toString();

        if(etTaskNum.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "任务编号"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.taskNum = etTaskNum.getText().toString();

        if(etAreaName.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "台区名称"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.areaName = etAreaName.getText().toString();

        if(etProtectLine.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "保护线路"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.protectLine = etProtectLine.getText().toString();

        if(etType.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "型号"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.type = etType.getText().toString();

        if(etSafetyMeasure.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "安全措施"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.safetyMeasure = etSafetyMeasure.getText().toString();

        if(etEndTime.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "结束时间"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.endTime = etEndTime.getText().toString();

        if(etBeginHandleTime.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "测量情况"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.beginHandleTime = etBeginHandleTime.getText().toString();

        if(etWether.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "天气"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.wether = etWether.getText().toString();

        if(etTestWay.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "试跳方法"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.testWay = etTestWay.getText().toString();

//        if(etATesting.getText().toString().equals("")){
//            Toast.makeText(getApplicationContext(), "A相接地试跳（mA)"+error, Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        this.coreMeterTestResult.aTesting = etATesting.getText().toString();

        if(etBTesting.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "B相接地试跳（mA)"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.bTesting = etBTesting.getText().toString();

        if(etCTesting.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "C相接地试跳（mA)"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.cTesting = etCTesting.getText().toString();

        if(etTestResult.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "检测结果"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.testResult = etTestResult.getText().toString();

        if(etHandleContent.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "采取措施"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.handleContent = etHandleContent.getText().toString();

        if(etTester.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "测试人"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.tester = etTester.getText().toString();

        if(etTestingTime.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "试跳日期"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.testingTime = etTestingTime.getText().toString();

        if(etEndHandleTime.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "任务结束"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.endHandleTime = etEndHandleTime.getText().toString();

        if(etIsHandled.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "已处理"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.isHandled = Integer.parseInt(etIsHandled.getText().toString());

        if(etUnhandleReason.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "未处理"+error, Toast.LENGTH_SHORT).show();
            return false;
        }
        this.coreMeterTestResult.unhandleReason = etUnhandleReason.getText().toString();

        return true;
    }

}
