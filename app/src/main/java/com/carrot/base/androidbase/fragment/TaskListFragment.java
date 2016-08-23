package com.carrot.base.androidbase.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.TaskListActivity_;
import com.carrot.base.androidbase.activity.handle.CollectResolveTroubleActivity_;
import com.carrot.base.androidbase.activity.handle.LineBrokenManagementActivity_;
import com.carrot.base.androidbase.adapter.MainCardAdapter;
import com.carrot.base.androidbase.adapter.TaskCardAdapter;
import com.carrot.base.androidbase.adapter.TaskListAdapter;
import com.carrot.base.androidbase.adapter.Type2Adapter;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.Extra;
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

    private RecyclerView.Adapter mAdapter;
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

                openItem();
            }
        });
    }

    void openItem(){
        switch (subTypeVo.getName()){
            case "线损管理":
                LineBrokenManagementActivity_.intent(getActivity())
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "采集消缺":
                CollectResolveTroubleActivity_.intent(getActivity())
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
        }

    }

}
