package com.carrot.base.androidbase.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.handle.CoreMeterTestActivity_;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.client.AreaInformationClient;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.client.CrossTestClient;
import com.carrot.base.androidbase.client.DistributionNetworkEngineeringClient;
import com.carrot.base.androidbase.client.EarthResistanceTestClient;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.client.SpecialSecurityCheckClient;
import com.carrot.base.androidbase.client.TotalPerformanceTestClient;
import com.carrot.base.androidbase.client.VoltageMeasurementClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list)
public class TaskListActivity extends AppCompatActivity {

    private static final int ACTIVITY_REQUEST_CODE = 1001;

    @Pref
    UserPrefs_ userPrefs;

    @RestService
    CoreMeterTestClient coreMeterTestClient;

    @RestService
    EquipmentCheckClient equipmentCheckClient;
    @RestService
    ResolveRecordClient resolveRecordClient;
    @RestService
    TotalPerformanceTestClient totalPerformanceTestClient;
    @RestService
    CrossTestClient crossTestClient;
    @RestService
    VoltageMeasurementClient voltageMeasurementClient;
    @RestService
    EarthResistanceTestClient earthResistanceTestClient;
    @RestService
    SpecialSecurityCheckClient specialSecurityCheckClient;
    @RestService
    DistributionNetworkEngineeringClient distributionNetworkEngineeringClient;


    @RestService
    AreaInformationClient areaInformationClient;

    @ViewById(R.id.rv_fragment_task_list_rv)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TaskCardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @ViewById(R.id.tl_task_list_tabs)
    TabLayout tlTabs;

    @ViewById(R.id.vp_task_list_pager)
    ViewPager vpPager;

    @ViewById(R.id.tb_task_list_tool_bar)
    Toolbar toolbar;

    @Extra
    TypeVo typeVo;

    @Extra
    TypeVo subTypeVo;

    String status = "未完成";


    TaskListFragmentAdapter taskListFragmentAdapter;

    @AfterViews
    void initTabs(){


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        taskListFragmentAdapter = new TaskListFragmentAdapter(getSupportFragmentManager(),
                this, typeVo, subTypeVo);
        vpPager.setAdapter(taskListFragmentAdapter);


        tlTabs.setupWithViewPager(vpPager);

        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                status = taskListFragmentAdapter.tabTitles[position];
                mAdapter.clear();
                refreshItems();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTitle(subTypeVo.getName() + "任务列表");

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new TaskCardAdapter(new ArrayList<TaskBaseVo>());

        this.uiInit();

        getAllData();
    }

    /**
     * 获取表单的相关数据
     */
    @Background
    void getAllData(){
        DataInstance.getInstance().areaInformationResults = areaInformationClient.getByUserId(userPrefs.id().get());
    }
    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        final Context context = this;

        ((TaskCardAdapter) mAdapter).setOnItemClickListener(new TaskCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TypeUtils.openItem(subTypeVo.getName(), context, mAdapter.getItem(position), ACTIVITY_REQUEST_CODE, status.equals("已完成") ? 2 : 1);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });

        refreshItems();
    }

    @Background
    public void refreshItems() {

//        showLoading();

        List<TaskBaseVo> resultList = getResultListFromServer();//

        // Load complete
        onItemsLoadComplete(resultList);
    }

    @UiThread
    void onItemsLoadComplete(List<TaskBaseVo> list) {

        // Stop refresh animation
        mAdapter.clear();
        mAdapter.addAll(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    List<TaskBaseVo> getResultListFromServer(){
        List<TaskBaseVo> list = null;

        switch (subTypeVo.getName()){
            case TypeUtils.TYPE_1_1:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_2:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_3:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_4:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_5:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_6:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_1_7:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            //======================================
            case TypeUtils.TYPE_2_1:{//OK
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_2:{//OK
                list = totalPerformanceTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_3:{//OK
                list = equipmentCheckClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_4:{//OK
                list = resolveRecordClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_5:{//ok
                list = crossTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_6:{//ok
                list = voltageMeasurementClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_7:{//ok
                list = earthResistanceTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            case TypeUtils.TYPE_2_8:{
                list = specialSecurityCheckClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            //====================
            case TypeUtils.TYPE_3_1:{
                list = distributionNetworkEngineeringClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }
            //=====================
            case TypeUtils.TYPE_4_1:{
                list = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
                break;
            }

        }

        return list;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("ssLog", "save ");
        super.onActivityReenter(resultCode, data);
        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
//            taskListFragmentAdapter.refreshData();
            refreshItems();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_list_new:

                TypeUtils.openItem(subTypeVo.getName(), this, null, ACTIVITY_REQUEST_CODE, 0);

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

}
