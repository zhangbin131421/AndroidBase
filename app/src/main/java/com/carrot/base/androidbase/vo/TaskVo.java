package com.carrot.base.androidbase.vo;

/**
 * Created by victor on 8/12/16.
 */
public class TaskVo {

    public String taskName;

    public String taskStatus;

    public String createTime;

    public TaskVo(String taskName, String taskStatus, String createTime){
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.createTime = createTime;
    }
}
