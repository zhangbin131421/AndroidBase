package com.carrot.base.androidbase.fragment;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
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


    @ViewById(R.id.lv_task_list_tasks)
    ListView lvTasks;

    @Bean
    TaskListAdapter taskListAdapter;

    @AfterViews
    void bindAdapter(){
        lvTasks.setAdapter(taskListAdapter);
    }

}
