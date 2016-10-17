package com.carrot.base.androidbase.activity.handle;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.ImagePreviewActivity_;
import com.carrot.base.androidbase.client.AreaInformationClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.image.UILImageLoader;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;
import com.carrot.base.androidbase.vo.result.UpdateResult;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by victor on 9/7/16.
 */
@EActivity
public abstract class BaseHandlerActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @RestService
    AreaInformationClient areaInformationClient;


    @Bean
    SSErrorHandler ssErrorHandler;

    //新增时不可编辑的字段
    public FormEditText[] addDisableList;
    //修改时不可编辑的字段
    public FormEditText[] updateDisableList;
    //完成时不可编辑的字段
    public FormEditText[] finishDisableList;

    //修改时不可编辑的下拉选项
    public Spinner[] updateDisabledSpinnerList;
    //完成时不可编辑的下拉选项
    public Spinner[] finishDisabledSpinnerList;

    //所有的增加图片的按钮，finish的需要隐藏
    public ImageView[] imageAddButtonList;

    public OpenDateVo[] openDateEditTextList;
    public OpenDateVo[] openTimeEditTextList;

    public ImageChooseVo[] openChooseImageList;

    public ShowWithSpinnerVo[] showBySpinnerList;

    class OpenDateVo{

        FormEditText editText;

        //哪些状态需要显示, 1:update, 10:add+update
        int status;
        public static final int UPDATE = 10;
        public static final int UPDATE_ADD = 1;

        public OpenDateVo(FormEditText et, int s){
            editText = et;
            status = s;
        }

    }

    //保存状态, 0: add, 1: update
    int saveStatus = 0;


    ProgressDialog progress;
    //保存需要提交的对象，开始如果为空说明为新增，否则为修改
    @Extra("extraTaskBaseVo")
    TaskBaseVo taskBaseVo;


    //未完成：1； 已完成：2，app新增：0
    @Extra("isFinished")
    int isFinished = 0;

    Context context;
    Resources resources;

    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.tb_tool_bar)
    Toolbar toolbar;

    //选中的日期文本，弹出的日期控件选择后，需要修改改显示的值
    FormEditText etSelectedDate;
    //选中的时间文本，弹出的时间控件选择后，需要修改改显示的值
    FormEditText etSelectedTime;

    private MenuItem saveItem = null;

    /**
     * 需要验证的字段
     */
    FormEditText[] allValidateFields;

    void afterInitView(String title, Context context, Resources resources){

        areaInformationClient.setRestErrorHandler(ssErrorHandler);
        setErrorHandler();


        this.context = context;
        this.resources = resources;

        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        this.setTitle(title);


        setValidateList();

        initDropDownList();

        //获取对应的entity
        getObject();

        //处理可编辑状态
        updateDisabled();

        //初始化需要打开日期选择控件的list
        initOpenDateList();
        initOpenTimeList();

        //初始化需要打开相册、拍照的list
        initOpenChooseImageList();

        //下拉框选择后，某些组件需要隐藏显示
        initHideBySpinner();
    }

    public abstract void setValidateList();

    public abstract void setErrorHandler();

    /**
     *
     * @param id
     * @return
     */
    public int getSelectedAreaIndexByID(int id){

        if(DataInstance.getInstance().areaInformationResults == null
                || DataInstance.getInstance().areaInformationResults.length == 0){
            return -1;
        }

        int rtn = -1;
        for(int i = 0; i < DataInstance.getInstance().areaInformationResults.length; i ++){
            AreaInformationResult areaInformationResult = DataInstance.getInstance().areaInformationResults[i];
            if(id == areaInformationResult.id){
                rtn = i;
                break;
            }
        }
        return rtn;
    }

    /**
     *
     * @param item
     * @return
     */
    public int getSelectedAreaIndex(String item){

        if(DataInstance.getInstance().areaInformationResults == null
                || DataInstance.getInstance().areaInformationResults.length == 0){
            return -1;
        }

        if(item == null){
            return 0;
        }

        int rtn = -1;
        for(int i = 0; i < DataInstance.getInstance().areaInformationResults.length; i ++){
            AreaInformationResult areaInformationResult = DataInstance.getInstance().areaInformationResults[i];
            if(item.equals(areaInformationResult.id+"")){
                rtn = i;
                break;
            }
        }
        return rtn;
    }


    void initHideBySpinner(){

        if(showBySpinnerList == null){
            return;
        }

        for(final ShowWithSpinnerVo ss : showBySpinnerList){
            ss.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(ss.spinner.getSelectedItem().toString().equals(ss.showText)){
                        ss.showLayout.setVisibility(View.VISIBLE);
                    }else{
                        ss.showLayout.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
        }

    }

    private void initOpenDateList(){
        if(openDateEditTextList != null){
            for(final OpenDateVo item : openDateEditTextList){
                item.editText.setInputType(InputType.TYPE_NULL);
                item.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus) {
                            int currentStatus = isFinished == 0 ? 0 : (isFinished == 1 ? 1 : 10);

                            if(item.status > currentStatus){
                                openDatePicker(item.editText);
                            }
                        }
                    }
                });
            }
        }
    }
    private void initOpenTimeList(){
        if(openTimeEditTextList != null){
            for(final OpenDateVo item : openTimeEditTextList){
                item.editText.setInputType(InputType.TYPE_NULL);
                item.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus) {
                            int currentStatus = isFinished == 0 ? 0 : (isFinished == 1 ? 1 : 10);

                            if(item.status > currentStatus){
                                openTimePicker(item.editText);
                            }
                        }
                    }
                });
            }
        }
    }

    private void initOpenChooseImageList(){

        if(openChooseImageList != null){
            for(final ImageChooseVo item : openChooseImageList){
                item.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showChooseImage(item.photoInfoList, item.flowLayout);
                    }
                });
            }
        }
    }



    /**
     * 初始化下拉框
     */
    abstract void initDropDownList();

    /**
     * 获取entity,并标记saveStatus状态
     */
    @Background
    void getObject(){
        showLoading();

        if(taskBaseVo == null){

        }else{
            this.saveStatus = 1;
            getEntityFromServer();
        }

        refreshView();
        dissmisLoading();
    }

    abstract void getEntityFromServer();

    private void refreshView(){
        refreshViewAfterGetEntity();

        updateDisabled();
    }

    /**
     * 获得server后的entity后，刷新View
     */
    @UiThread
    abstract void refreshViewAfterGetEntity();

    /**
     * 处理不可编辑状态的控件，已完成、未完成
     */
    @UiThread
    void updateDisabled(){

        FormEditText[] editList = null;

        Spinner[] spinnerList = null;

        switch (this.isFinished){
            case 0:
                editList = addDisableList;
                break;

            case 1:
                editList = updateDisableList;
                spinnerList = updateDisabledSpinnerList;
                break;

            case 2://已完成
                editList = finishDisableList;
                spinnerList = finishDisabledSpinnerList;

                //隐藏所有的增加图片的按钮
                if(imageAddButtonList != null){
                    for (ImageView addBtn : imageAddButtonList){
                        addBtn.setVisibility(ImageView.GONE);
                    }
                }

                //隐藏右上角保存
                if(saveItem != null){
                    saveItem.setVisible(false);
                }

                break;
        }


        //下拉框显示为文本
        if(spinnerList != null){
            for(Spinner spinner : spinnerList){
                spinner.setEnabled(false);
                spinner.setClickable(false);
                spinner.setBackgroundResource(R.drawable.spinner_text);

            }

        }

        //文本输入框不可编辑
        if(editList != null){
            for(FormEditText editText : editList){
                editText.setInputType(InputType.TYPE_NULL);
                editText.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }


    @OptionsMenuItem(R.id.menu_task_item_save)
    void singleInjection(MenuItem item) {
        saveItem = item;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_item_save:

                if(validate()){
                    conform();
                }else{
                    alert("提示", "请输入必填项！", SweetAlertDialog.WARNING_TYPE, null);
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

        UpdateResult result = save();


        if(result != null && result.code == 1){
            alert("成功", "保存成功", SweetAlertDialog.SUCCESS_TYPE, new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent();
                    setResult(ResultCodeConstant.RESULT_CODE_REFRESH, intent);
                    finish();
                }
            });
        }else{
            alert("失败", "保存失败", SweetAlertDialog.ERROR_TYPE, null);
        }

        dissmisLoading();
    }



    /**
     * 新增
     */
    abstract UpdateResult save();


    void getImageFromURL(String urls, org.apmem.tools.layouts.FlowLayout imageContent){
        if(urls != null){
            for (String url : urls.split(";")){

                View view = getViewFromURL(url);
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
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }


    void setDropDownListAdapter(Spinner spinner, AreaInformationResult[] values){
        if(values == null){
            return;
        }
        ArrayAdapter<AreaInformationResult> adapter = new ArrayAdapter<AreaInformationResult>(this, android.R.layout.simple_spinner_item, values);
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

                        finishGetPhoto(resultList, imageList, imageContent);
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

                        finishGetPhoto(resultList, imageList, imageContent);
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

        boolean exFlag = false;

        for (FormEditText field: allValidateFields) {
            exFlag = false;

            if(showBySpinnerList != null){
                for(ShowWithSpinnerVo showWithSpinnerVo : showBySpinnerList){
                    if(showWithSpinnerVo.showLayout.getVisibility() == View.GONE){//隐藏后，不需要验证其中的项
                        for(FormEditText editText : showWithSpinnerVo.exValidateList){
                            if(editText.getId() == field.getId()){
                                exFlag = true;
                                break;
                            }
                        }
                    }
                    if(exFlag == true){
                        break;
                    }
                }

            }
            if(exFlag == false){
                allValidate = field.testValidity() && allValidate;
            }
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

    void openDatePicker(FormEditText etSelectedDate){

        this.etSelectedDate = etSelectedDate;
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
    void openTimePicker(FormEditText etSelectedTime){

        this.etSelectedTime = etSelectedTime;
        Calendar now = Calendar.getInstance();
        TimePickerDialog dpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                true
        );
        dpd.show(getFragmentManager(), "Timepickerdialog");
    }

    /**
     * 日期选择后的回调
     * @param view
     * @param year
     * @param monthOfYear
     * @param dayOfMonth
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        if(etSelectedDate != null){

            etSelectedDate.setText(year+"-"+((monthOfYear+1) > 9 ? (monthOfYear+1) : "0" + (monthOfYear+1))+"-"+(dayOfMonth > 9 ? dayOfMonth : "0"+dayOfMonth));
        }
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        if(etSelectedTime != null){
            etSelectedTime.setText(DateUtils.getCurrentYYYY_MM_DD() + " " + hourOfDay + ":" + minute + ":00");
        }
    }


    class ImageChooseVo{
        public ImageView imageView;
        public List<PhotoInfo> photoInfoList;
        public org.apmem.tools.layouts.FlowLayout flowLayout;

        public ImageChooseVo(ImageView iv, List<PhotoInfo> list, org.apmem.tools.layouts.FlowLayout fl){
            imageView = iv;
            photoInfoList = list;
            flowLayout = fl;
        }
    }

    /**
     * 某些控件需要根据下拉框具体的选值显示或隐藏
     */
    class ShowWithSpinnerVo {
        public Spinner spinner;
        public LinearLayout showLayout;
        public String showText;
        public FormEditText[] exValidateList;

        public ShowWithSpinnerVo(Spinner sp, LinearLayout ll, String tx, FormEditText[] ex){
            spinner = sp;
            showLayout = ll;
            showText = tx;
            if(ex == null){
                exValidateList = new FormEditText[]{};
            }else{
                exValidateList = ex;
            }
        }
    }

    /**
     *
     * @param title
     * @param msg
     * @param type  SweetAlertDialog.ERROR_TYPE
     */
    @UiThread
    public void alert(String title, String msg, int type, SweetAlertDialog.OnSweetClickListener confirmClickListener){
        final SweetAlertDialog dialog = new SweetAlertDialog(this, type)
                        .setTitleText(title)
                        .setContentText(msg)
                .setConfirmText("确定");
        if(confirmClickListener != null){
            dialog.setConfirmClickListener(confirmClickListener);
        }
        dialog.show();
    }


    /**
     * 获取图片的View，包括图片和删除按钮
     * @param url
     * @return
     */
    public View getViewFromURL(String url){

        if(url == null || url.trim().equals("") || context == null || resources == null){
            return null;
        }

        View view = null;

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
            view = getImageViewForForm(bitmap, null, null, null, url);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return view;
    }

    public View getImageViewForForm(Bitmap bitmap, final PhotoInfo pi, final List<PhotoInfo> picList,
                                    final org.apmem.tools.layouts.FlowLayout contentView, final String url){

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View promptView = layoutInflater.inflate(R.layout.view_image, null);


        ImageView imageView = (ImageView) promptView.findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePreviewActivity_.intent(context).url(url).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
            }
        });

        ImageView deleteButton = (ImageView) promptView.findViewById(R.id.btnDelete);

        if(isFinished == 2){
            deleteButton.setVisibility(View.GONE);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pi != null){
                    openRemoveImageDialog(pi, picList, contentView, promptView);
                }

            }
        });

        imageView.setImageBitmap(bitmap);


//        ImageView imageView = new ImageView(context);
//
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//
//
//        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, resources.getDisplayMetrics());
//
//        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, resources.getDisplayMetrics());
//
//        imageView.setLayoutParams(new GridView.LayoutParams(width, width));
//        imageView.setPadding(padding, padding, padding, padding);
//
//        imageView.setImageBitmap(bitmap);

        return promptView;
    }

    @UiThread
    public void openRemoveImageDialog(final PhotoInfo pi, final List<PhotoInfo> picList,
                                      final org.apmem.tools.layouts.FlowLayout contentView, final View promptView){
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText("是否要删除该图片？").setConfirmText("确定")
                .showCancelButton(true).setCancelText("取消")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.cancel();
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        picList.remove(pi);

                        contentView.removeView(promptView);

                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }


    /**
     *
     * @param resultList
     * @param picList   最终的图片列表
     * @param contentView
     */
    public void finishGetPhoto(List<PhotoInfo> resultList, List<PhotoInfo> picList,
                                      org.apmem.tools.layouts.FlowLayout contentView){

        BitmapFactory.Options opts=new BitmapFactory.Options();
        opts.inDither=false;                     //Disable Dithering mode
        opts.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared
        opts.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future
        opts.inTempStorage=new byte[32 * 1024];


        for (final PhotoInfo pi : resultList){

            picList.add(pi);

            File file = new File(pi.getPhotoPath());

            if(file.exists()){
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


//                BitmapFactory.decodeStream(fis, null, opts);
                Bitmap bitmap = BitmapFactory.decodeStream(fis, null, opts);//BitmapFactory.decodeFile(file.getAbsolutePath());

                View view = getImageViewForForm(bitmap, pi, picList, contentView, pi.getPhotoPath());

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Log.i("sslog", "image clicked, " + pi.getPhotoPath());
                        ImagePreviewActivity_.intent(context).url(pi.getPhotoPath()).flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                    }
                });

                view.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

//                        Log.i("sslog", "image long clicked, " + pi.getPhotoPath());

                        return false;
                    }
                });

                contentView.addView(view);
            }
        }
    }
}
