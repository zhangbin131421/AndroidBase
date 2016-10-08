package com.carrot.base.androidbase.activity.taskList;

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

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskCardResolveRecordAdapter;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.client.AreaInformationClient;
import com.carrot.base.androidbase.client.ResolveRecordClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.preferences.DataInstance;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.ResolveRecordResult;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EActivity
public abstract class TaskListBaseActivity extends AppCompatActivity {

    static final int ACTIVITY_REQUEST_CODE = 1001;

    @Pref
    UserPrefs_ userPrefs;

    @Bean
    SSErrorHandler ssErrorHandler;

    @RestService
    AreaInformationClient areaInformationClient;

    @ViewById(R.id.rv_fragment_task_list_rv)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

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


    void afterInitTabs(){

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
                clearAdapter();
                refreshItems();
            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setTitle(subTypeVo.name + "任务列表");

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        newAdapter();

        this.uiInit();

        getAllData();
    }

    abstract void uiInit();

    abstract void clearAdapter();

    abstract void newAdapter();

    /**
     * 获取表单的相关数据
     */
    @Background
    void getAllData(){
        DataInstance.getInstance().areaInformationResults = areaInformationClient.getByUserId(userPrefs.id().get());
    }

    @UiThread
    void onItemsLoadComplete(List list) {

        clearAdapter();
        addAllAdapter(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    abstract void addAllAdapter(List list);

    abstract List getResultListFromServer();


    @Background
    public void refreshItems(){
        onItemsLoadComplete(getResultListFromServer());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityReenter(resultCode, data);
        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
            refreshItems();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_task_list_new:

                TypeUtils.openItem(subTypeVo.name, this, null, ACTIVITY_REQUEST_CODE, 0);

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
