package com.carrot.base.androidbase.vo;

import java.util.Date;

/**
 * 停复电
 * Created by victor on 8/22/16.
 */
public class StopStartElectricVo {



    //工单派发
    private Date AssignmentTime;
    //工单编号
    private String TaskNum;
    //台区名称
    private String AreaName;
    //停、复电地址
    private String StopStartElectricAddress;

    //现场处理时间
    private Date BeginHandleTime;
    //处理内容
    private String HandleContent;

    //处理结果时间
    private Date EndHandleTime;
    //已处理
    private int IsHandled;
    //未处理
    private String UnhandleReason;

    public Date getAssignmentTime() {
        return AssignmentTime;
    }

    public void setAssignmentTime(Date assignmentTime) {
        AssignmentTime = assignmentTime;
    }

    public String getTaskNum() {
        return TaskNum;
    }

    public void setTaskNum(String taskNum) {
        TaskNum = taskNum;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getStopStartElectricAddress() {
        return StopStartElectricAddress;
    }

    public void setStopStartElectricAddress(String stopStartElectricAddress) {
        StopStartElectricAddress = stopStartElectricAddress;
    }

    public Date getBeginHandleTime() {
        return BeginHandleTime;
    }

    public void setBeginHandleTime(Date beginHandleTime) {
        BeginHandleTime = beginHandleTime;
    }

    public String getHandleContent() {
        return HandleContent;
    }

    public void setHandleContent(String handleContent) {
        HandleContent = handleContent;
    }

    public Date getEndHandleTime() {
        return EndHandleTime;
    }

    public void setEndHandleTime(Date endHandleTime) {
        EndHandleTime = endHandleTime;
    }

    public int getIsHandled() {
        return IsHandled;
    }

    public void setIsHandled(int isHandled) {
        IsHandled = isHandled;
    }

    public String getUnhandleReason() {
        return UnhandleReason;
    }

    public void setUnhandleReason(String unhandleReason) {
        UnhandleReason = unhandleReason;
    }
}
