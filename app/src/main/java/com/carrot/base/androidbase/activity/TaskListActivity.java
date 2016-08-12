package com.carrot.base.androidbase.activity;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TabWidget;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.adapter.TaskListAdapter;
import com.carrot.base.androidbase.adapter.TaskListFragmentAdapter;
import com.carrot.base.androidbase.fragment.TaskListFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_task_list)
@OptionsMenu(R.menu.task_list)
public class TaskListActivity extends AppCompatActivity {

    @ViewById(R.id.tl_task_list_sliding_tabs)
    TabLayout tlTabs;

    @ViewById(R.id.vp_task_list_viewpager)
    ViewPager vpViewPage;

//    @RootContext
//    Context context;

    @AfterViews
    void initAdapter(){
        vpViewPage.setAdapter(new TaskListFragmentAdapter(getSupportFragmentManager(), TaskListActivity.this));
        tlTabs.setupWithViewPager(vpViewPage);
    }

}
