package com.carrot.base.androidbase.activity.handle;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.andreabaccega.widget.FormEditText;
import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.client.OrderHandleClient;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.utils.DateUtils;
import com.carrot.base.androidbase.utils.FileUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.OrderHandleResult;
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
@EActivity(R.layout.activity_order_handle)
@OptionsMenu(R.menu.task_item)
public class OrderHandleActivity extends BaseHandlerActivity{



    List<PhotoInfo> handleContentPicList = new ArrayList<>();


    OrderHandleResult orderHandleResult;

    @RestService
    OrderHandleClient orderHandleClient;



    @ViewById(R.id.et_assignment_time)
    FormEditText etAssignmentTime;
    @ViewById(R.id.et_task_num)
    FormEditText etTaskNum;
    @ViewById(R.id.et_order_content)
    FormEditText etOrderContent;
    @ViewById(R.id.et_begin_handle_time)
    FormEditText etBeginHandleTime;

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

    @ViewById(R.id.ll_is_handler)
    LinearLayout llIsHandler;


    @ViewById(R.id.et_user_add)
    FormEditText etUserAdd;
    @ViewById(R.id.et_user_name)
    FormEditText etUserName;
    @ViewById(R.id.et_user_no)
    FormEditText etUserNo;
    @ViewById(R.id.et_end_time)
    FormEditText etEndTime;


    @ViewById(R.id.spn_area_name)
    Spinner spnAreaName;
    @ViewById(R.id.spn_type)
    Spinner spnType;

    @ViewById(R.id.et_worker)
    FormEditText etWorker;

    @AfterViews
    void bindAdapter(){
        super.afterInitView(TypeUtils.TYPE_1_5, getApplicationContext(), getResources());

    }


    public void setValidateList(){
        allValidateFields = new FormEditText[] {etUnhandleReason};

        addDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,};

        updateDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etBeginHandleTime,
                etUserNo,etUserName,etUserAdd,etOrderContent,etEndTime,etEndHandleTime};

        finishDisableList = new FormEditText[] {etAssignmentTime,etTaskNum,etOrderContent,etBeginHandleTime,
                etHandleContent,etEndHandleTime,etUnhandleReason,
        etUserAdd, etUserName, etUserNo, etEndTime,etWorker};

        updateDisabledSpinnerList = new Spinner[] {spnAreaName,spnType};
        finishDisabledSpinnerList = new Spinner[] {spnIsHandled,spnAreaName, spnType};

        openDateEditTextList = new OpenDateVo[] {
//            new OpenDateVo(etEndHandleTime, OpenDateVo.UPDATE),
//                new OpenDateVo(etEndTime, OpenDateVo.UPDATE),
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
        orderHandleClient.setRestErrorHandler(ssErrorHandler);
    }

    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(spnIsHandled, TypeUtils.TYPE_HANDLER);
        setDropDownListAdapter(spnAreaName, DataInstance.getInstance().areaInformationResults);
        setDropDownListAdapter(spnType, TypeUtils.ORDER_TYPE);
    }


    void getEntityFromServer(){
        orderHandleResult = orderHandleClient.getById(taskBaseVo.id);
    }

    void refreshViewAfterGetEntity(){
        if(orderHandleResult == null){

            orderHandleResult = new OrderHandleResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        }else{

            etAssignmentTime.setText(getYYYYMMDD(orderHandleResult.assignmentTime));
            etTaskNum.setText(orderHandleResult.taskNum);
            etOrderContent.setText(orderHandleResult.orderContent);
            etBeginHandleTime.setText(getYYYYMMDD(orderHandleResult.beginHandleTime));
            etHandleContent.setText(orderHandleResult.handleContent);
            etEndHandleTime.setText(getYYYYMMDD(orderHandleResult.endHandleTime));
            spnIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_HANDLER, orderHandleResult.isHandled == 2 ? "未处理" : "已处理"));
            etUnhandleReason.setText(orderHandleResult.unhandleReason);

            etUserName.setText(orderHandleResult.userName);
            etUserNo.setText(orderHandleResult.userNo);
            etUserAdd.setText(orderHandleResult.userAdd);
            etEndTime.setText(getYYYYMMDD(orderHandleResult.endTime));
            etWorker.setText(orderHandleResult.worker);


            spnType.setSelection(TypeUtils.getSelectedIndex(TypeUtils.ORDER_TYPE, orderHandleResult.type));
            spnAreaName.setSelection(getSelectedAreaIndexByID(orderHandleResult.areaID));

            getImage();
        }

        etEndHandleTime.setText(DateUtils.getCurrentYYYY_MM_DD());

        if(orderHandleResult.worker == null || orderHandleResult.worker.equals("")){
            etWorker.setText(userPrefs.name().get());
        }
    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(orderHandleResult.handleContentPic, llHandleContent);

    }

    UpdateResult save(){

        if(orderHandleResult.iD == 0){
            orderHandleResult.assignByUserID = userPrefs.id().get();
            orderHandleResult.userID = userPrefs.id().get();
        }

        MultiValueMap<String, Object> data = null;
        try {
            data = orderHandleResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        FileUtils.addImageToData(data, OrderHandleResult.HandleContentPic, handleContentPicList, this);

        return orderHandleClient.update(data);
    }


    boolean validate(){

        if(super.validate()) {

            this.orderHandleResult.assignmentTime = etAssignmentTime.getText().toString();
            this.orderHandleResult.taskNum = etTaskNum.getText().toString();
            this.orderHandleResult.orderContent = etOrderContent.getText().toString();
            this.orderHandleResult.beginHandleTime = etBeginHandleTime.getText().toString();
            this.orderHandleResult.handleContent = etHandleContent.getText().toString();
            this.orderHandleResult.endHandleTime = etEndHandleTime.getText().toString();
            this.orderHandleResult.isHandled = spnIsHandled.getSelectedItem().toString().equals("已处理") ? 1 : 2;

            this.orderHandleResult.unhandleReason = etUnhandleReason.getText().toString();

            this.orderHandleResult.userAdd = etUserAdd.getText().toString();
            this.orderHandleResult.userName = etUserName.getText().toString();
            this.orderHandleResult.userNo = etUserNo.getText().toString();
            this.orderHandleResult.endTime = etEndTime.getText().toString();


            this.orderHandleResult.areaName = spnAreaName.getSelectedItem().toString();

            this.orderHandleResult.areaID = ((AreaInformationResult)spnAreaName.getSelectedItem()).id;

            this.orderHandleResult.type = spnType.getSelectedItem().toString();
            this.orderHandleResult.worker = etWorker.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
