package com.carrot.base.androidbase.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;


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
public class TaskListFragmentAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[] = new String[] { "未完成", "已完成"};

    private final SparseArray<TaskListFragment> mFragmentList = new SparseArray<>();

    Context context;

    TypeVo typeVo;
    TypeVo subTypeVo;

    public TaskListFragmentAdapter(FragmentManager fm, Context context,
                                   TypeVo typeVo, TypeVo subTypeVo){
        super(fm);
        this.context = context;

        this.typeVo = typeVo;
        this.subTypeVo = subTypeVo;

        mFragmentList.put(0, TaskListFragment_.builder().status(tabTitles[0]).typeVo(typeVo).subTypeVo(subTypeVo).build());

        mFragmentList.put(1, TaskListFragment_.builder().status(tabTitles[1]).typeVo(typeVo).subTypeVo(subTypeVo).build());

//        this.instantiateItem(this, 0);

    }
    public void refreshData(){
        for (int i = 0; i < mFragmentList.size(); i++) {
            TaskListFragment fragment = mFragmentList.get(i);
            fragment.refreshItems();
        }
    }

    @Override
    public Parcelable saveState()
    {
        return null;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TaskListFragment fragment = (TaskListFragment) super.instantiateItem(container, position);
        mFragmentList.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragmentList.remove(position);
        super.destroyItem(container, position, object);
    }

    public TaskListFragment getRegisteredFragment(int position) {
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
