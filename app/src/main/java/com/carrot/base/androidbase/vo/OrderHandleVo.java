package com.carrot.base.androidbase.vo;

import java.util.Date;

/**
 * 工单处理
 * Created by victor on 8/22/16.
 */
public class OrderHandleVo {



    //任务派发
    private Date AssignmentTime;
    //任务编号
    private String TaskNum;
    //工单内容
    private String OrderContent;

    //现场处理
    private Date BeginHandleTime;
    //处理内容
    private String HandleContent;
    //处理人员
    private int UserID;

    //处理结果
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

    public String getOrderContent() {
        return OrderContent;
    }

    public void setOrderContent(String orderContent) {
        OrderContent = orderContent;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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
