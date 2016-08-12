package com.carrot.base.androidbase.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.carrot.base.androidbase.item.task.TaskItemView;
import com.carrot.base.androidbase.item.task.TaskItemView_;
import com.carrot.base.androidbase.utils.TestUtils;
import com.carrot.base.androidbase.vo.TaskVo;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
@EBean
public class TaskListAdapter extends BaseAdapter {

    List<TaskVo> tasks;

    @RootContext
    Context context;

    @AfterInject
    void initAdapter(){
        tasks = TestUtils.getAllTasks("未完成");
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int i) {
        return tasks.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TaskItemView taskItemView;

        if(view == null){
            taskItemView = TaskItemView_.build(context);
        }else{
            taskItemView = (TaskItemView) view;
        }

        taskItemView.bind((TaskVo) getItem(i));
        return taskItemView;
    }
}
