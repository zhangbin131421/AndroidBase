package com.carrot.base.androidbase.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.utils.TypeUtils;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EFragment(R.layout.fragment_task_list)
public class TaskListFragment extends Fragment {

    @FragmentArg("status")
    String status;


    @FragmentArg
    TypeVo typeVo;

    @FragmentArg
    TypeVo subTypeVo;


    @ViewById(R.id.rv_fragment_task_list_rv)
    RecyclerView mRecyclerView;

    @ViewById(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private TaskCardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "TaskListFragment";

    @AfterViews
    void bindAdapter(){

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TaskCardAdapter(TestUtils.getAllTasks(status));
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

        // Load complete
        onItemsLoadComplete();
    }

    void onItemsLoadComplete() {
        // Update the adapter and notify data set changed
        // ...

        // Stop refresh animation
        mAdapter.clear();
        mAdapter.addAll(TestUtils.getAllTasks(status));
        mSwipeRefreshLayout.setRefreshing(false);
    }

//    void openItem(){
//        switch (subTypeVo.getName()){
//            case "线损管理":
//                LineBrokenManagementActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "采集消缺":
//                CollectResolveTroubleActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "业扩报装":
//                ExtendBussinessSetupActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "表计故障":
//                MeterTroubleActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "工单处理":
//                OrderHandleActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "营业普查":
//                BusinessAuditeActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            case "停复电":
//                StopStartElectricActivity_.intent(getActivity())
//                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                        .start();
//                break;
//            default:
//                Toast.makeText(getActivity(), "开发中....", Toast.LENGTH_SHORT).show();
//        }
//
//    }

}
