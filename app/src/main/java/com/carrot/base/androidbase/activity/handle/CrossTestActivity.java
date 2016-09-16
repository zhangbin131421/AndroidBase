

        package com.carrot.base.androidbase.activity.handle;

        import android.widget.Spinner;

        import com.andreabaccega.widget.FormEditText;
        import com.carrot.base.androidbase.R;
        import com.carrot.base.androidbase.client.CrossTestClient;
        import com.carrot.base.androidbase.utils.DateUtils;
        import com.carrot.base.androidbase.utils.FileUtils;
        import com.carrot.base.androidbase.utils.TypeUtils;
        import com.carrot.base.androidbase.vo.result.CrossTestResult;

        import org.androidannotations.annotations.AfterViews;
        import org.androidannotations.annotations.Background;
        import org.androidannotations.annotations.Click;
        import org.androidannotations.annotations.EActivity;
        import org.androidannotations.annotations.OptionsMenu;
        import org.androidannotations.annotations.UiThread;
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
@EActivity(R.layout.activity_cross_test)
@OptionsMenu(R.menu.task_item)
public class CrossTestActivity extends BaseHandlerActivity{



    List<PhotoInfo> earthDistanceList = new ArrayList<>();

    List<PhotoInfo> crossDistanceList = new ArrayList<>();


    CrossTestResult crossTestResult;

    @RestService
    CrossTestClient crossTestClient;


    @ViewById(R.id.etAssignmentTime)
    FormEditText etAssignmentTime;

    @ViewById(R.id.etTaskNum)
    FormEditText etTaskNum;

    @ViewById(R.id.etAreaName)
    Spinner etAreaName;

    @ViewById(R.id.etCrossPoint)
    FormEditText etCrossPoint;

    @ViewById(R.id.etCrossName)
    FormEditText etCrossName;

    @ViewById(R.id.etSafetyMeasure)
    Spinner etSafetyMeasure;

    @ViewById(R.id.etBeginHandleTime)
    FormEditText etBeginHandleTime;


    @ViewById(R.id.etEarthDistance_content)
    org.apmem.tools.layouts.FlowLayout etEarthDistanceContent;

    @ViewById(R.id.etCrossDistance_content)
    org.apmem.tools.layouts.FlowLayout etCrossDistanceContent;

    @ViewById(R.id.etIsQualified)
    Spinner etIsQualified;

    @ViewById(R.id.etModificationOpinion)
    FormEditText etModificationOpinion;

    @ViewById(R.id.etTestTime)
    FormEditText etTestTime;

    @ViewById(R.id.etTester)
    FormEditText etTester;

    @ViewById(R.id.etEndHandleTime)
    FormEditText etEndHandleTime;

    @ViewById(R.id.etIsHandled)
    Spinner etIsHandled;

    @ViewById(R.id.etUnhandleReason)
    FormEditText etUnhandleReason;


    @AfterViews
    void bindAdapter(){

        super.afterInitView("交叉跨越测量", getApplicationContext(), getResources());

        allFields = new FormEditText[] {etAssignmentTime,etTaskNum,etCrossPoint,etCrossName,etBeginHandleTime,etModificationOpinion,etTestTime,etTester,etEndHandleTime,etUnhandleReason};
    }

    @Override
    void initDropDownList(){
        //下拉选择框
        setDropDownListAdapter(etAreaName, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etSafetyMeasure, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsQualified, TypeUtils.TYPE_TEST);

        setDropDownListAdapter(etIsHandled, TypeUtils.TYPE_HANDLER);

    }



    @Background
    void getEntityFromServer(){
        crossTestResult = crossTestClient.getById(taskBaseVo.id);
    }

    @UiThread
    void refreshViewAfterGetEntity(){
        if(crossTestResult == null){

            crossTestResult = new CrossTestResult();

            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());


        }else{

            etAssignmentTime.setText(crossTestResult.assignmentTime);
            etTaskNum.setText(crossTestResult.taskNum);
            etAreaName.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, crossTestResult.areaName));
            etCrossPoint.setText(crossTestResult.crossPoint);
            etCrossName.setText(crossTestResult.crossName);
            etSafetyMeasure.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, crossTestResult.safetyMeasure));
            etBeginHandleTime.setText(crossTestResult.beginHandleTime);
            etIsQualified.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, crossTestResult.isQualified));
            etModificationOpinion.setText(crossTestResult.modificationOpinion);
            etTestTime.setText(crossTestResult.testTime);
            etTester.setText(crossTestResult.tester);
            etEndHandleTime.setText(crossTestResult.endHandleTime);
            etIsHandled.setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, crossTestResult.isHandled));
            etUnhandleReason.setText(crossTestResult.unhandleReason);

            getImage();

            this.saveStatus = 1;
        }

    }

    /**
     * update,打开页面后，获取当前数据，并获取网络图片
     */
    @Background
    void getImage(){

        super.getImageFromURL(crossTestResult.earthDistancePic, etEarthDistanceContent);

        super.getImageFromURL(crossTestResult.crossDistancePic, etCrossDistanceContent);

    }


    @Click(R.id.btn_add_imageEarthDistance)
    void addImageEarthDistance(){

        super.showChooseImage(earthDistanceList, etEarthDistanceContent);

    }


    @Click(R.id.btn_add_imageCrossDistance)
    void addImageCrossDistance(){

        super.showChooseImage(crossDistanceList, etCrossDistanceContent);

    }



    /**
     * 新增
     */
    @Override
    void add(){

        crossTestResult.assignByUserID = userPrefs.id().get();
        crossTestResult.userId = userPrefs.id().get();

        crossTestClient.add(crossTestResult);
    }

    /**
     * 更新
     */
    @Override
    void update(){

        MultiValueMap<String, Object> data = null;
        try {
            data = crossTestResult.parseToMultiValueMap();
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }

        FileUtils.addImageToData(data, CrossTestResult.EarthDistancePic, earthDistanceList, this);
        FileUtils.addImageToData(data, CrossTestResult.CrossDistancePic, crossDistanceList, this);
        crossTestClient.update(data);

    }



    @Override
    boolean validate(){

        if(super.validate()) {

            this.crossTestResult.assignmentTime = etAssignmentTime.getText().toString();

            this.crossTestResult.taskNum = etTaskNum.getText().toString();

            this.crossTestResult.areaName = etAreaName.getSelectedItem().toString();

            this.crossTestResult.crossPoint = etCrossPoint.getText().toString();

            this.crossTestResult.crossName = etCrossName.getText().toString();

            this.crossTestResult.safetyMeasure = etSafetyMeasure.getSelectedItem().toString();

            this.crossTestResult.beginHandleTime = etBeginHandleTime.getText().toString();

            this.crossTestResult.isQualified = etIsQualified.getSelectedItem().toString();

            this.crossTestResult.modificationOpinion = etModificationOpinion.getText().toString();

            this.crossTestResult.testTime = etTestTime.getText().toString();

            this.crossTestResult.tester = etTester.getText().toString();

            this.crossTestResult.endHandleTime = etEndHandleTime.getText().toString();

            this.crossTestResult.isHandled = etIsHandled.getSelectedItem().toString();

            this.crossTestResult.unhandleReason = etUnhandleReason.getText().toString();

            return true;
        }{
            return false;
        }
    }

}
