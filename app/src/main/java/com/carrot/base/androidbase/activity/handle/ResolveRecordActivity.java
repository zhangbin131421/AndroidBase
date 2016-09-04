package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.ImageUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.ResolveRecordResult;
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
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 8/22/16.
 */
@EActivity(R.layout.activity_resolve_record)
@OptionsMenu(R.menu.task_item)
public class ResolveRecordActivity extends AppCompatActivity{


    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;


    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;


    List<PhotoInfo> defectContentPicList;

    ResolveRecordResult resolveRecordResult;


    @RestService
    ResolveRecordClient resolveRecordClient;


    ProgressDialog progress;


    //保存状态, 0: add, 1: update
    private int saveStatus = 0;

    @Pref
    UserPrefs_ userPrefs;


    @ViewById(R.id.et_AssignmentTime)
    EditText etAssignmentTime;
    @ViewById(R.id.et_TaskNum)
    EditText et_TaskNum;
    @ViewById(R.id.et_DefectPlace)
    EditText et_DefectPlace;
    @ViewById(R.id.et_DefectContent)
    EditText et_DefectContent;
    @ViewById(R.id.et_EndTime)
    EditText et_EndTime;
    @ViewById(R.id.et_SafetyMeasure)
    EditText et_SafetyMeasure;
    @ViewById(R.id.et_WorkType)
    Spinner et_WorkType;
    @ViewById(R.id.et_WorkInvoiceNum)
    EditText et_WorkInvoiceNum;
    @ViewById(R.id.et_StopScope)
    EditText et_StopScope;
    @ViewById(R.id.et_Applier)
    EditText et_Applier;
    @ViewById(R.id.et_WorkLicensor)
    EditText et_WorkLicensor;
    @ViewById(R.id.et_WorkPrincipal)
    EditText et_WorkPrincipal;
    @ViewById(R.id.et_StopTime)
    EditText et_StopTime;
    @ViewById(R.id.et_EndStopTime)
    EditText et_EndStopTime;
    @ViewById(R.id.et_StopPeople)
    EditText et_StopPeople;
    @ViewById(R.id.et_Worker)
    EditText et_Worker;
    @ViewById(R.id.et_OperationInvoiceNum)
    EditText et_OperationInvoiceNum;
    @ViewById(R.id.et_WorkInstruction)
    EditText et_WorkInstruction;
    @ViewById(R.id.et_ResolveContent)
    org.apmem.tools.layouts.FlowLayout et_ResolveContent;
    @ViewById(R.id.et_WorkDate)
    EditText et_WorkDate;
    @ViewById(R.id.et_EndHandleTime)
    EditText et_EndHandleTime;
    @ViewById(R.id.et_IsHandled)
    EditText et_IsHandled;
    @ViewById(R.id.et_UnhandleReason)
    EditText et_UnhandleReason;
    @ViewById(R.id.et_BeginHandleTime)
    EditText et_BeginHandleTime;



    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.setTitle(TypeUtils.TYPE_2_4);

        defectContentPicList = new ArrayList<>();


        //下拉选择框
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, TypeUtils.WORK_TYPE);
        et_WorkType.setAdapter(adapter);


        getObject();
    }


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
//             et_WorkType.setText("work type");
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
//             et_ResolveContent.setText("r c");
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


            getImageFromURL();


            this.saveStatus = 1;
        }

    }


    @Background
    void getImageFromURL(){
        if(resolveRecordResult.resolveContentPic != null){
            for (String url : resolveRecordResult.resolveContentPic.split(";")){

                ImageView imageView = ImageUtils.getImageViewFromURL(url,getApplicationContext(), getResources());
                addImage(imageView);
            }
        }
    }

    @UiThread
    void addImage(ImageView imageView){
        if(et_ResolveContent != null && imageView != null){
            et_ResolveContent.addView(imageView);
        }
    }


    @UiThread
    void showLoading(){
        if(progress == null){
            progress = new ProgressDialog(this);
        }
        if(progress.isShowing()){
            return;
        }
        progress.setTitle("Loading");
        progress.show();
    }

    @UiThread
    void dissmisLoading(){
        if(progress != null){
            progress.dismiss();
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

    @Click(R.id.btn_add_image)
    void addImageLocal(){
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

                ImageView imageView = ImageUtils.getImageViewForForm(getApplicationContext(), getResources(), bitmap);

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

                et_ResolveContent.addView(imageView);
            }
        }
    }

    @Background
    void conform(){

        showLoading();

        if(validate() == false){
            dissmisLoading();
            return;
        }

        if(saveStatus == 0){ //add
            resolveRecordResult.assignByUserID = userPrefs.id().get();
            resolveRecordResult.userId = userPrefs.id().get();

            resolveRecordClient.add(resolveRecordResult);

        }else{ //update

            MultiValueMap<String, Object> data = null;
            data = resolveRecordResult.parseToMultiValueMap();

            resolveRecordClient.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);


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


                    data.add(ResolveRecordResult.ResolveContentPic, contentsAsResource);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //TODO

            resolveRecordClient.update(data);
        }

        dissmisLoading();

        Intent intent = new Intent();
        setResult(ResultCodeConstant.RESULT_CODE_REFRESH, intent);
        finish();
    }


    boolean validate(){

        String error = "不能为空";

        //TODO

        if(etAssignmentTime.getText().toString().equals("")){
            alert("任务派发"+error);
            return false;
        }
        this.resolveRecordResult.assignmentTime = etAssignmentTime.getText().toString();


        if(et_TaskNum.getText().toString().equals("")){
            alert("任务编号"+error);
            return false;
        }
        this.resolveRecordResult.taskNum = et_TaskNum.getText().toString();


        if(et_DefectPlace.getText().toString().equals("")){
            alert("缺陷位置"+error);
            return false;
        }
        this.resolveRecordResult.defectPlace = et_DefectPlace.getText().toString();


        if(et_DefectContent.getText().toString().equals("")){
            alert("缺陷内容"+error);
            return false;
        }
        this.resolveRecordResult.defectContent = et_DefectContent.getText().toString();


        if(et_EndTime.getText().toString().equals("")){
            alert("结束时间"+error);
            return false;
        }
        this.resolveRecordResult.endTime = et_EndTime.getText().toString();


        if(et_SafetyMeasure.getText().toString().equals("")){
            alert("安全措施"+error);
            return false;
        }
        this.resolveRecordResult.safetyMeasure = et_SafetyMeasure.getText().toString();


        if(et_BeginHandleTime.getText().toString().equals("")){
            alert("现场处理"+error);
            return false;
        }
        this.resolveRecordResult.beginHandleTime = et_BeginHandleTime.getText().toString();


        if(et_WorkType.getSelectedItem().toString().equals("")){
            alert("工作性质"+error);
            return false;
        }
        this.resolveRecordResult.workType = et_WorkType.getSelectedItem().toString();


        if(et_WorkInvoiceNum.getText().toString().equals("")){
            alert("工作票号"+error);
            return false;
        }
        this.resolveRecordResult.workInvoiceNum = et_WorkInvoiceNum.getText().toString();


        if(et_StopScope.getText().toString().equals("")){
            alert("停电范围"+error);
            return false;
        }
        this.resolveRecordResult.stopScope = et_StopScope.getText().toString();


        if(et_Applier.getText().toString().equals("")){
            alert("申请人"+error);
            return false;
        }
        this.resolveRecordResult.applier = et_Applier.getText().toString();


        if(et_WorkLicensor.getText().toString().equals("")){
            alert("工作许可人"+error);
            return false;
        }
        this.resolveRecordResult.workLicensor = et_WorkLicensor.getText().toString();


        if(et_WorkPrincipal.getText().toString().equals("")){
            alert("工作负责人"+error);
            return false;
        }
        this.resolveRecordResult.workPrincipal = et_WorkPrincipal.getText().toString();


        if(et_StopTime.getText().toString().equals("")){
            alert("停电时间"+error);
            return false;
        }
        this.resolveRecordResult.stopTime = et_StopTime.getText().toString();


        if(et_EndStopTime.getText().toString().equals("")){
            alert("送电时间"+error);
            return false;
        }
        this.resolveRecordResult.endStopTime = et_EndStopTime.getText().toString();


        if(et_StopPeople.getText().toString().equals("")){
            alert("停送电人员"+error);
            return false;
        }
        this.resolveRecordResult.stopPeople = et_StopPeople.getText().toString();


        if(et_Worker.getText().toString().equals("")){
            alert("工作人员"+error);
            return false;
        }
        this.resolveRecordResult.worker = et_Worker.getText().toString();


        if(et_OperationInvoiceNum.getText().toString().equals("")){
            alert("操作票编号"+error);
            return false;
        }
        this.resolveRecordResult.operationInvoiceNum = et_OperationInvoiceNum.getText().toString();


        if(et_WorkInstruction.getText().toString().equals("")){
            alert("作业指导书"+error);
            return false;
        }
        this.resolveRecordResult.workInstruction = et_WorkInstruction.getText().toString();


//        if(et_ResolveContent.getText().toString().equals("")){
//            alert("消缺情况"+error);
//            return false;
//        }
//        this.resolveRecordResult.resolveContent = et_ResolveContent.getText().toString();


        if(et_WorkDate.getText().toString().equals("")){
            alert("工作日期"+error);
            return false;
        }
        this.resolveRecordResult.workDate = et_WorkDate.getText().toString();


        if(et_EndHandleTime.getText().toString().equals("")){
            alert("任务结束"+error);
            return false;
        }
        this.resolveRecordResult.endHandleTime = et_EndHandleTime.getText().toString();

        if(et_IsHandled.getText().toString().equals("")){
            alert("已处理"+error);
            return false;
        }
        this.resolveRecordResult.isHandled = Integer.parseInt(et_IsHandled.getText().toString());

        if(et_UnhandleReason.getText().toString().equals("")){
            alert("未处理"+error);
            return false;
        }
        this.resolveRecordResult.unhandleReason = et_UnhandleReason.getText().toString();



        return true;
    }


    @UiThread
    void alert(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }


}
