package com.carrot.base.androidbase.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.TaskListActivity;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.client.EquipmentCheckClient;
import com.carrot.base.androidbase.constant.ResultCodeConstant;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EFragment(R.layout.fragment_task_list)
public class TaskListFragment extends Fragment {

    private static final int ACTIVITY_REQUEST_CODE = 1002;

    @FragmentArg("status")
    String status;


    @Pref
    UserPrefs_ userPrefs;

    @FragmentArg
    TypeVo typeVo;


    @FragmentArg
    TypeVo subTypeVo;


    @RestService
    CoreMeterTestClient coreMeterTestClient;

    @RestService
    EquipmentCheckClient equipmentCheckClient;

//    ProgressDialog progress;

    @ViewById(R.id.rv_fragment_task_list_rv)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TaskCardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "TaskListFragment";

    @AfterViews
    @Background
    void bindAdapter(){

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        switch (subTypeVo.getName()){
            case TypeUtils.TYPE_1_1:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_2:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_3:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_4:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_5:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_6:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_1_7:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            //======================================
            case TypeUtils.TYPE_2_1:{//OK
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_2:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_3:{//OK
                mAdapter = new TaskCardAdapter(equipmentCheckClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_4:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_5:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_6:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_7:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            case TypeUtils.TYPE_2_8:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            //====================
            case TypeUtils.TYPE_3_1:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }
            //=====================
            case TypeUtils.TYPE_4_1:{
                mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));
                break;
            }

        }
        uiInit();
    }

    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        ((TaskCardAdapter) mAdapter).setOnItemClickListener(new TaskCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TypeUtils.openItem(subTypeVo.getName(), getActivity(), mAdapter.getItem(position), ACTIVITY_REQUEST_CODE);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
    }

    @Background
    public void refreshItems() {
        // Load items
        // ...

//        showLoading();

        List<TaskBaseVo> resultList = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);//

        // Load complete
        onItemsLoadComplete(resultList);
    }


    @OnActivityResult(ACTIVITY_REQUEST_CODE)
    protected void onActivity3Result(int resultCode){
//        Toast.makeText(getActivity(), "asdfasdf", Toast.LENGTH_SHORT).show();
        Log.i("ssLog", "save ");


//        if(resultCode == ResultCodeConstant.RESULT_CODE_REFRESH){
//            refreshItems();
//        }
    }

    @UiThread
    void onItemsLoadComplete(List<TaskBaseVo> list) {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mAdapter.clear();
        mAdapter.addAll(list);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @UiThread
    void showLoading(){

//        if(progress == null){
//            progress = new ProgressDialog(getActivity());
//        }
//
//        progress.setTitle("Loading");
//        progress.show();

    }
    @UiThread
    void alert(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
