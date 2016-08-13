package com.carrot.base.androidbase.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.fragment.TaskListFragment_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list)
public class TaskListActivity extends AppCompatActivity {


    @ViewById(R.id.tl_task_list_tabs)
    TabLayout tlTabs;

    @ViewById(R.id.vp_task_list_pager)
    ViewPager vpPager;

//    @RootContext
//    Context context;
//
//    @Bean
    TaskListFragmentAdapter taskListFragmentAdapter;

    @AfterViews
    void initTabs(){
        taskListFragmentAdapter = new TaskListFragmentAdapter(getSupportFragmentManager(), getApplicationContext());
        vpPager.setAdapter(taskListFragmentAdapter);
//        tlTabs.
    }

}
