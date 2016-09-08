package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.image.UILImageLoader;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.ImageUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by victor on 9/7/16.
 */
@EActivity
public abstract class BaseHandlerActivity extends AppCompatActivity {

    //保存状态, 0: add, 1: update
    int saveStatus = 0;


    ProgressDialog progress;
    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;

    Context context;
    Resources resources;

    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;


    /**
     * 需要验证的字段
     */
    FormEditText[] allFields;

    void afterInitView(String title, Context context, Resources resources){

        this.context = context;
        this.resources = resources;

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle(title);

        initDropDownList();

        getObject();
    }

    /**
     * 初始化下拉框
     */
    abstract void initDropDownList();

    /**
     * 获取entity
     */
    abstract void getObject();


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:

                if(validate()){
                    conform();
                }

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

    /**
     * 提交表单
     */
    @Background
    void conform(){

        showLoading();

        if(saveStatus == 0){ //add

            add();
        }else{ //update

            update();
        }

        dissmisLoading();

        Intent intent = new Intent();
        setResult(ResultCodeConstant.RESULT_CODE_REFRESH, intent);
        finish();
    }



    /**
     * 新增
     */
    abstract void add();

    /**
     * 更新
     */
    abstract void update();

    void getImageFromURL(String urls, org.apmem.tools.layouts.FlowLayout imageContent){
        if(urls != null){
            for (String url : urls.split(";")){

                View view = ImageUtils.getViewFromURL(url, context, resources);
                addImage(view, imageContent);
            }
        }
    }

    @UiThread
    void addImage(View view, org.apmem.tools.layouts.FlowLayout imageContent){
        if(imageContent != null && view != null){
            imageContent.addView(view);
        }
    }

    void setDropDownListAdapter(Spinner spinner, String[] values){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        spinner.setAdapter(adapter);
    }

    /**
     * 点击添加图片按钮后，弹出的拍照、相册界面
     */
    void showChooseImage(final List<PhotoInfo> imageList, final org.apmem.tools.layouts.FlowLayout imageContent){
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

        if(GalleryFinal.getCoreConfig() == null){
            initGrally();
        }

        btnAdd1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                alertD.dismiss();
                //带配置
                GalleryFinal.openCamera(1, config, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

                        FileUtils.finishGetPhoto(context, resources, resultList, imageList, imageContent);
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

                        FileUtils.finishGetPhoto(context, resources, resultList, imageList, imageContent);
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


    /**
     * 验证输入信息
     * @return
     */
    boolean validate(){

        boolean allValidate = true;

        for (FormEditText field: allFields) {
            allValidate = field.testValidity() && allValidate;
        }

        return allValidate;
    }


    private void initGrally() {
        //设置主题
//ThemeConfig.CYAN
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
//配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

//配置imageloader
        ImageLoader imageLoader = new UILImageLoader();


        CoreConfig coreConfig = new CoreConfig.Builder(getApplicationContext(), imageLoader, theme)
//                .setDebug(BuildConfig.DEBUG)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }


    @UiThread
    void showLoading(){
        if(progress == null){
            progress = new ProgressDialog(this);
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
}
