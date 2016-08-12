package com.carrot.base.androidbase.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;
import android.widget.Switch;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskListAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list)
public class TaskListActivity extends AppCompatActivity {

    @ViewById(R.id.swc_task_list_status)
    Switch swcStatus;

    @ViewById(R.id.lv_task_list_tasks)
    ListView lvTasks;

    @Bean
    TaskListAdapter taskListAdapter;

    @AfterViews
    void bindAdapter(){
        lvTasks.setAdapter(taskListAdapter);
    }

}
