package com.carrot.base.androidbase.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.carrot.base.androidbase.fragment.TaskListFragment;
import com.carrot.base.androidbase.fragment.TaskListFragment_;
import com.carrot.base.androidbase.vo.TypeVo;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
public class TaskListFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "未完成", "已完成"};

    private final List<TaskListFragment> mFragmentList = new ArrayList<>();

    Context context;

    TypeVo typeVo;
    TypeVo subTypeVo;

    public TaskListFragmentAdapter(FragmentManager fm, Context context,
                                   TypeVo typeVo, TypeVo subTypeVo){
        super(fm);
        this.context = context;

        this.typeVo = typeVo;
        this.subTypeVo = subTypeVo;

        mFragmentList.add(TaskListFragment_.builder().status(tabTitles[0]).typeVo(typeVo).subTypeVo(subTypeVo).build());

        mFragmentList.add(TaskListFragment_.builder().status(tabTitles[1]).typeVo(typeVo).subTypeVo(subTypeVo).build());


    }
    public void refreshData(){
        for (TaskListFragment fragment: mFragmentList) {
            fragment.refreshItems();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
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
