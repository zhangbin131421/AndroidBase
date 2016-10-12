package com.carrot.base.androidbase.activity.taskList;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskCardDistributionNetworkEngineeringAdapter;
import com.carrot.base.androidbase.client.DistributionNetworkEngineeringClient;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.result.DistributionNetworkEngineeringResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list_no_add)
public class TaskListDistributionNetworkEngineeringActivity extends TaskListBaseActivity {

    @RestService
    DistributionNetworkEngineeringClient distributionNetworkEngineeringClient;

    private TaskCardDistributionNetworkEngineeringAdapter mAdapter;

    @AfterViews
    void initTabs(){

        afterInitTabs();

        distributionNetworkEngineeringClient.setRestErrorHandler(ssErrorHandler);

    }


    void clearAdapter(){
        mAdapter.clear();
    }

    void newAdapter(){
        mAdapter = new TaskCardDistributionNetworkEngineeringAdapter(new ArrayList<DistributionNetworkEngineeringResult>());
    }


    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        final Context context = this;

        ((TaskCardDistributionNetworkEngineeringAdapter) mAdapter).setOnItemClickListener(new TaskCardDistributionNetworkEngineeringAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TaskBaseVo taskBaseVo = new TaskBaseVo();
                taskBaseVo.id = mAdapter.getItem(position).iD;
                TypeUtils.openItem(subTypeVo.name, context, taskBaseVo, ACTIVITY_REQUEST_CODE, status.equals("已完成") ? 2 : 1);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        refreshItems();
    }

    void addAllAdapter(List list){
        mAdapter.addAll(list);
    }

    List<DistributionNetworkEngineeringResult> getResultListFromServer(){
        return distributionNetworkEngineeringClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);
    }


}
