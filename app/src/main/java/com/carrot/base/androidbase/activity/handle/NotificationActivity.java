package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.NotificationClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.NotificationResult;
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
@EActivity(R.layout.activity_notification)
@OptionsMenu(R.menu.task_item)
public class NotificationActivity extends BaseHandlerActivity{



    List<PhotoInfo> notificationPicPicList = new ArrayList<>();


    NotificationResult notificationResult;

    @RestService
    NotificationClient notificationClient;



    @ViewById(R.id.et_i_d)
    FormEditText etID;
    @ViewById(R.id.et_user_i_d)
    FormEditText etUserID;
    @ViewById(R.id.et_notification_title)
    FormEditText etNotificationTitle;
    @ViewById(R.id.et_notification_content)
    FormEditText etNotificationContent;

    @ViewById(R.id.ll_notification_pic)
    org.apmem.tools.layouts.FlowLayout llNotificationPic;


    @ViewById(R.id.btn_add_image_notification_pic)
    ImageView btnAddImageNotificationPic;





    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_4_2, getApplicationContext(), getResources());

    }


    public void setValidateList(){

//
//        FormEditText etID;
//        FormEditText etUserID;
//        FormEditText etNotificationTitle;
//        FormEditText etNotificationContent;
//        org.apmem.tools.layouts.FlowLayout llNotificationPic;
//        ImageView btnAddImageNotificationPic;
//        FormEditText etNotificationPic;

        allValidateFields = new FormEditText[] {};

        addDisableList = new FormEditText[] {etID,etUserID,etNotificationTitle,etNotificationContent};

        updateDisableList = new FormEditText[] {etID,etUserID,etNotificationTitle,etNotificationContent};

        finishDisableList = new FormEditText[] {etID,etUserID,etNotificationTitle,etNotificationContent};

        updateDisabledSpinnerList = new Spinner[] {};
        finishDisabledSpinnerList = new Spinner[] {};

        openDateEditTextList = new OpenDateVo[] {
        };

        showBySpinnerList = new ShowWithSpinnerVo[]{};



        imageAddButtonList = new ImageView[] {btnAddImageNotificationPic,        };
        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {
                new ImageChooseVo(btnAddImageNotificationPic, notificationPicPicList, llNotificationPic),
        };

    }

    @Override
    public void setErrorHandler(){
        notificationClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
    }


    void getEntityFromServer(){
        notificationResult = notificationClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(notificationResult == null){

            notificationResult = new NotificationResult();



        }else{

            etID.setText(notificationResult.iD+"");
            etUserID.setText(notificationResult.userName == null ? "" : notificationResult.userName);
            etNotificationTitle.setText(notificationResult.notificationTitle);
            etNotificationContent.setText(notificationResult.notificationContent);
//            etNotificationPic.setText(notificationResult.notificationPic);

            getImage();
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(notificationResult.notificationPic, llNotificationPic);

    }

    UpdateResult save(){

        if(notificationResult.iD == 0){
            notificationResult.assignByUserID = userPrefs.id().get();
            notificationResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = notificationResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, NotificationResult.NotificationPicPic, notificationPicPicList, this);

        return null;
//        return notificationClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {


            this.notificationResult.notificationTitle = etNotificationTitle.getText().toString();
            this.notificationResult.notificationContent = etNotificationContent.getText().toString();
//            this.notificationResult.notificationPic = etNotificationPic.getText().toString();


            return true;
        }{
            return false;
        }
    }

}
