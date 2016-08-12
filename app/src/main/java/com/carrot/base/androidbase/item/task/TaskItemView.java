package com.carrot.base.androidbase.item.task;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.TaskVo;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by victor on 8/12/16.
 */
@EViewGroup(R.layout.item_task_list)
public class TaskItemView extends LinearLayout{

    @ViewById(R.id.tv_task_list_item_task_name)
    TextView tvTaskName;

    @ViewById(R.id.tv_task_list_item_task_time)
    TextView  tvTaskTime;

    @ViewById(R.id.tv_task_list_item_status)
    TextView tvTaskStatus;

    public TaskItemView(Context context){
        super(context);
    }

    public void bind(TaskVo task){
        tvTaskName.setText(task.taskName);
        tvTaskTime.setText(task.createTime);
        tvTaskStatus.setText(task.taskStatus);
    }
}
