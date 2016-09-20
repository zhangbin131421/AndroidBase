package com.carrot.base.androidbase;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.carrot.base.androidbase.activity.SettingActivity_;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.activity.Type2Activity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.client.AreaInformationClient;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
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
    EquipmentCheckClient equipmentCheckClient;
    @RestService
    ResolveRecordClient resolveRecordClient;


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


        equipmentCheckClient.setRestErrorHandler(ssErrorWithoutDialogHandler);
        resolveRecordClient.setRestErrorHandler(ssErrorWithoutDialogHandler);

        this.setTitle("农电外勤通系统");

        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainCardAdapter(TypeUtils.getAllItems(getResources()));
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
            CountResult ecCountVo = equipmentCheckClient.getUnFinishedByUserId(userPrefs.id().get());
            CountResult rrList = resolveRecordClient.getUnFinishedByUserId(userPrefs.id().get());
            int count = ecCountVo.count + rrList.count;

            if(count > 0){
                showProduct(View.VISIBLE, count);
            }else{
                showProduct(View.INVISIBLE, 0);
            }
        }
    }

    @UiThread
    void showProduct(int visibility, int count){

        MainCardAdapter.DataObjectHolder holder = (MainCardAdapter.DataObjectHolder) mRecyclerView.findViewHolderForAdapterPosition(1);
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

                if(typeVo.getSubTypes() == null || typeVo.getSubTypes().size() == 0){
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
