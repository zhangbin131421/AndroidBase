package com.carrot.base.androidbase.activity;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.taskList.TaskListBusinessAuditeActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListCarManagementActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListCollectResolveTroubleActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListCoreMeterTestActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListCrossTestActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListDistributionNetworkEngineeringActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListEarthResistanceTestActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListEquipmentCheckActivity;
import com.carrot.base.androidbase.activity.taskList.TaskListEquipmentCheckActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListExtendBussinessSetupActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListLineBrokenManagementActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListMeterTroubleActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListOrderHandleActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListResolveRecordActivity;
import com.carrot.base.androidbase.activity.taskList.TaskListResolveRecordActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListSpecialSecurityCheckActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListStopStartElectricActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListTotalPerformanceTestActivity_;
import com.carrot.base.androidbase.activity.taskList.TaskListVoltageMeasurementActivity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.adapter.Type2Adapter;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.CountResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by victor on 8/22/16.
 */

@EActivity(R.layout.active_type_2)
@OptionsMenu(R.menu.type_2)
public class Type2Activity extends AppCompatActivity {



    @ViewById(R.id.rv_type_2)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "Type2Activity";

    @Extra
    TypeVo typeVo;

    @ViewById(R.id.tb_type_2_tool_bar)
    Toolbar toolbar;


    @Pref
    UserPrefs_ userPrefs;

    @RestService
    EquipmentCheckClient equipmentCheckClient;
    @RestService
    ResolveRecordClient resolveRecordClient;

    @Bean
    SSErrorHandler ssErrorHandler;

    @AfterViews
    void bindAdapter(){

        equipmentCheckClient.setRestErrorHandler(ssErrorHandler);
        resolveRecordClient.setRestErrorHandler(ssErrorHandler);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Type2Adapter(typeVo.subTypes);
        mRecyclerView.setAdapter(mAdapter);


        //TODO test
        useTimer();
    }


    //TODO 定时刷新未完成列表，后期去掉，换成推送
    @Background
    public void useTimer() {
        Timer mTimer = new Timer();
//        mTimer.cancel();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                Log.i("Timer", "Calls");
                getUnHandled();
            }
        }, 1000, 30000);
    }


    @Background
    void getUnHandled(){
        if(userPrefs.id().get() > 0){
            CountResult ecCountVo = equipmentCheckClient.getUnFinishedByUserId(userPrefs.id().get());

            if(ecCountVo != null && ecCountVo.count > 0){
                showFlag(View.VISIBLE, ecCountVo.count, 2);
            }else{
                showFlag(View.INVISIBLE, 0, 2);
            }

            CountResult rrCountVo = resolveRecordClient.getUnFinishedByUserId(userPrefs.id().get());
            if(rrCountVo != null && rrCountVo.count > 0){
                showFlag(View.VISIBLE, rrCountVo.count, 3);
            }else{
                showFlag(View.INVISIBLE, 0, 3);
            }
        }
    }

    @UiThread
    void showFlag(int visibility, int count, int postion){

        Type2Adapter.DataObjectHolder holder = (Type2Adapter.DataObjectHolder) mRecyclerView.findViewHolderForAdapterPosition(postion);
        if(holder != null){
            holder.flag.setVisibility(visibility);
            holder.flag.setText(count+"");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        setTitle(typeVo.name);
        ((Type2Adapter) mAdapter).setOnItemClickListener(new Type2Adapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                switch (typeVo.subTypes.get(position).name){
                    case TypeUtils.TYPE_1_1:{
                        TaskListLineBrokenManagementActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_2:{
                        TaskListCollectResolveTroubleActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_3:{
                        TaskListExtendBussinessSetupActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_4:{
                        TaskListMeterTroubleActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_5:{
                        TaskListOrderHandleActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_6:{
                        TaskListBusinessAuditeActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_1_7:{
                        TaskListStopStartElectricActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_1:{
                        TaskListCoreMeterTestActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_2:{
                        TaskListTotalPerformanceTestActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_3:{
                        TaskListEquipmentCheckActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_4:{
                        TaskListResolveRecordActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_5:{
                        TaskListCrossTestActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_6:{
                        TaskListVoltageMeasurementActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_7:{
                        TaskListEarthResistanceTestActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_2_8:{
                        TaskListSpecialSecurityCheckActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_3_1:{
                        TaskListDistributionNetworkEngineeringActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                    case TypeUtils.TYPE_4_1:{
                        TaskListCarManagementActivity_.intent(getApplicationContext())
                                .typeVo(typeVo)
                                .subTypeVo(typeVo.subTypes.get(position))
                                .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();
                        break;
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
}
