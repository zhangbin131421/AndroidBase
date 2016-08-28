package com.carrot.base.androidbase.fragment;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.client.CoreMeterTestClient;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
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

    ProgressDialog progress;

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
        mAdapter = new TaskCardAdapter(coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0));//
        uiInit();
    }

    @UiThread
    void uiInit(){
        mRecyclerView.setAdapter(mAdapter);

        ((TaskCardAdapter) mAdapter).setOnItemClickListener(new TaskCardAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TypeUtils.openItem(subTypeVo.getName(), getActivity());
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

    void refreshItems() {
        // Load items
        // ...

        showLoading();

        List<TaskBaseVo> resultList = coreMeterTestClient.getByUserId(userPrefs.id().get(), status.equals("已完成") ? 1 : 0);//

        // Load complete
        onItemsLoadComplete(resultList);
    }

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

        progress.setTitle("Loading");
        progress.show();

    }
    @UiThread
    void alert(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
