package com.carrot.base.androidbase;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.carrot.base.androidbase.activity.SettingActivity_;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.activity.Type2Activity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.client.AreaInformationClient;
import com.carrot.base.androidbase.client.BusinessAuditeClient;
import com.carrot.base.androidbase.client.CarManagementClient;
import com.carrot.base.androidbase.client.CollectResolveTroubleClient;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.client.CrossTestClient;
import com.carrot.base.androidbase.client.DistributionNetworkEngineeringClient;
import com.carrot.base.androidbase.client.EarthResistanceTestClient;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ExtendBussinessSetupClient;
import com.carrot.base.androidbase.client.LineBrokenManagementClient;
import com.carrot.base.androidbase.client.MeterTroubleClient;
import com.carrot.base.androidbase.client.OrderHandleClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.client.SpecialSecurityCheckClient;
import com.carrot.base.androidbase.client.StopStartElectricClient;
import com.carrot.base.androidbase.client.TotalPerformanceTestClient;
import com.carrot.base.androidbase.client.VoltageMeasurementClient;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.error.SSErrorWithoutDialogHandler;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.GeneratorUtils;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.CountResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.main)
public class MainActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MainActivity";


    @RestService
    CoreMeterTestClient coreMeterTestClient;
    @RestService
    TotalPerformanceTestClient totalPerformanceTestClient;
    @RestService
    EquipmentCheckClient equipmentCheckClient;
    @RestService
    ResolveRecordClient resolveRecordClient;
    @RestService
    CrossTestClient crossTestClient;
    @RestService
    VoltageMeasurementClient voltageMeasurementClient;
    @RestService
    EarthResistanceTestClient earthResistanceTestClient;
    @RestService
    SpecialSecurityCheckClient specialSecurityCheckClient;


    @RestService
    LineBrokenManagementClient lineBrokenManagementClient;
    @RestService
    CollectResolveTroubleClient collectResolveTroubleClient;
    @RestService
    ExtendBussinessSetupClient extendBussinessSetupClient;
    @RestService
    MeterTroubleClient meterTroubleClient;
    @RestService
    OrderHandleClient orderHandleClient;
    @RestService
    BusinessAuditeClient businessAuditeClient;
    @RestService
    StopStartElectricClient stopStartElectricClient;


    @RestService
    DistributionNetworkEngineeringClient distributionNetworkEngineeringClient;


    @RestService
    CarManagementClient carManagementClient;

    boolean isSupportedBade = false;

    @Pref
    UserPrefs_ userPrefs;


    @ViewById(R.id.tb_main_tool_bar)
    Toolbar toolbar;

    @Bean
    SSErrorHandler ssErrorHandler;

    @Bean
    SSErrorWithoutDialogHandler ssErrorWithoutDialogHandler;

    @AfterViews
    void bindAdapter(){

        checkIsSupportedByVersion();

        coreMeterTestClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        totalPerformanceTestClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        equipmentCheckClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        resolveRecordClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        crossTestClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        voltageMeasurementClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        earthResistanceTestClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        specialSecurityCheckClient.setRestErrorHandler(ssErrorWithoutDialogHandler);


        lineBrokenManagementClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        collectResolveTroubleClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        extendBussinessSetupClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        meterTroubleClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        orderHandleClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        businessAuditeClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        stopStartElectricClient.setRestErrorHandler(ssErrorWithoutDialogHandler);


        distributionNetworkEngineeringClient.setRestErrorHandler(ssErrorWithoutDialogHandler);

        carManagementClient.setRestErrorHandler(ssErrorWithoutDialogHandler);;


        this.setTitle("农电外勤通系统");

        setSupportActionBar(toolbar);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainCardAdapter(TypeUtils.getAllItems());
        mRecyclerView.setAdapter(mAdapter);

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
            int c1 = getUnFinish1();
            int c2 = getUnFinish2();
            int c3 = getUnFinish3();
            int c4 = getUnFinish4();

            int count = c1+c2+c3+c4;

//            try{
//                Bundle extra =new Bundle();
//                extra.putString("package", "com.carrot.base.androidbase");
//                extra.putString("class", "com.carrot.base.androidbase.MainActivity_");
//                extra.putInt("badgenumber", count);
//                getApplicationContext().getContentResolver().call(Uri.parse( "content://com.huawei.android.launcher.settings/badge/"), "change_launcher_badge", null, extra);
//
//            }catch (Exception e){
//
//            }

        }
    }

    public void checkIsSupportedByVersion(){
        try {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo("com.huawei.android.launcher",
                    0);
            if(info.versionCode>=63029){
                isSupportedBade = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } }

    int getUnFinish1(){

        CountResult c11 = lineBrokenManagementClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c12 = collectResolveTroubleClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c13 = extendBussinessSetupClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c14 = meterTroubleClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c15 = orderHandleClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c16 = businessAuditeClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c17 = stopStartElectricClient.getUnFinishedByUserId(userPrefs.id().get());

        int count = (c11 == null ?  0 : c11.count)
                + (c12 == null ? 0 : c12.count)
                + (c13 == null ? 0 : c13.count)
                + (c14 == null ? 0 : c14.count)
                + (c15 == null ? 0 : c15.count)
                + (c16 == null ? 0 : c16.count)
                + (c17 == null ? 0 : c17.count);

        if(count > 0){
            showProduct(View.VISIBLE, count, 0);
        }else{
            showProduct(View.INVISIBLE, 0, 0);
        }
        return count;
    }

    int getUnFinish2(){


        CountResult c21 = coreMeterTestClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c22 = totalPerformanceTestClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c23 = equipmentCheckClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c24 = resolveRecordClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c25 = crossTestClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c26 = voltageMeasurementClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c27 = earthResistanceTestClient.getUnFinishedByUserId(userPrefs.id().get());
        CountResult c28 = specialSecurityCheckClient.getUnFinishedByUserId(userPrefs.id().get());


        int count = (c21 == null ?  0 : c21.count)
                + (c22 == null ? 0 : c22.count)
                + (c23 == null ? 0 : c23.count)
                + (c24 == null ? 0 : c24.count)
                + (c25 == null ? 0 : c25.count)
                + (c26 == null ? 0 : c26.count)
                + (c27 == null ? 0 : c27.count)
                + (c28 == null ? 0 : c28.count);

        if(count > 0){
            showProduct(View.VISIBLE, count, 1);
        }else{
            showProduct(View.INVISIBLE, 0, 1);
        }
        return count;
    }

    int getUnFinish3(){

        CountResult c31 = distributionNetworkEngineeringClient.getUnFinishedByUserId(userPrefs.id().get());

        int count = (c31 == null ?  0 : c31.count);

        if(count > 0){
            showProduct(View.VISIBLE, count, 2);
        }else{
            showProduct(View.INVISIBLE, 0, 2);
        }
        return count;
    }

    int getUnFinish4(){

//        CountResult c41 = carManagementClient.getUnFinishedByUserId(userPrefs.id().get());

//        int count = (c41 == null ?  0 : c41.count);

//        if(count > 0){
//            showProduct(View.VISIBLE, count, 3);
//        }else{
//            showProduct(View.INVISIBLE, 0, 3);
//        }
        return 0;
    }

    @UiThread
    void showProduct(int visibility, int count, int index){

        MainCardAdapter.DataObjectHolder holder = (MainCardAdapter.DataObjectHolder) mRecyclerView.findViewHolderForAdapterPosition(index);
        if(holder != null){
            holder.flag.setVisibility(visibility);
            holder.flag.setText(count+"");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        ((MainCardAdapter) mAdapter).setOnItemClickListener(new MainCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                TypeVo typeVo = ((MainCardAdapter) mAdapter).getItem(position);

                if(typeVo.subTypes == null || typeVo.subTypes.size() == 0){
                    TaskListActivity_.intent(getApplicationContext())
                            .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .typeVo(null)
                            .subTypeVo(typeVo)
                            .start();

                }else{
                    Type2Activity_.intent(getApplicationContext())
                            .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .typeVo(typeVo).start();
                }


            }
        });

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();

        GeneratorUtils.getInstance().generate();

        if(userPrefs.name().get().equals("")){
            LoginActivity_.intent(MainActivity.this).start();

        }
    }

    @OptionsItem(R.id.menu_main_setting)
    void openSetting(){
        SettingActivity_.intent(MainActivity.this).start();
    }

}
