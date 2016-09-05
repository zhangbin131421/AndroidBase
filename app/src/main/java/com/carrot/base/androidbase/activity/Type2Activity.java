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
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.adapter.Type2Adapter;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
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

    @AfterViews
    void bindAdapter(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new Type2Adapter(typeVo.getSubTypes());
        mRecyclerView.setAdapter(mAdapter);


        //TODO test
        useTimer();
    }

    @Pref
    UserPrefs_ userPrefs;

    @RestService
    EquipmentCheckClient equipmentCheckClient;
    @RestService
    ResolveRecordClient resolveRecordClient;

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
            List<TaskBaseVo> ecList = equipmentCheckClient.getByUserId(userPrefs.id().get(), 0);

            if(ecList != null && ecList.size() > 0){
                showEquipmentCheckFlag(View.VISIBLE);
            }else{
                showEquipmentCheckFlag(View.INVISIBLE);
            }

            List<TaskBaseVo> rrList = resolveRecordClient.getByUserId(userPrefs.id().get(), 0);
            if(rrList != null && rrList.size() > 0){
                showResolveRecordFlag(View.VISIBLE);
            }else{
                showResolveRecordFlag(View.INVISIBLE);
            }
        }
    }

    @UiThread
    void showEquipmentCheckFlag(int visibility){

        Type2Adapter.DataObjectHolder holder = (Type2Adapter.DataObjectHolder) mRecyclerView.findViewHolderForAdapterPosition(2);
        if(holder != null){
            holder.flag.setVisibility(visibility);
        }
    }
    @UiThread
    void showResolveRecordFlag(int visibility){

        Type2Adapter.DataObjectHolder holder = (Type2Adapter.DataObjectHolder) mRecyclerView.findViewHolderForAdapterPosition(3);
        if(holder != null){
            holder.flag.setVisibility(visibility);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        setTitle(typeVo.getName());
        ((Type2Adapter) mAdapter).setOnItemClickListener(new Type2Adapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TaskListActivity_.intent(getApplicationContext())
                        .typeVo(typeVo)
                        .subTypeVo(typeVo.getSubTypes().get(position))
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK).start();

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
