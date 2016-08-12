package com.carrot.base.androidbase.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.carrot.base.androidbase.fragment.TaskListFragment;
import com.carrot.base.androidbase.fragment.TaskListFragment_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

/**
 * Created by victor on 8/12/16.
 */
public class TaskListFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "未完成", "已完成"};


    Context context;

    public TaskListFragmentAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        return TaskListFragment_.builder().status(tabTitles[position]).build();
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
