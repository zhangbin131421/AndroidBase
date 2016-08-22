package com.carrot.base.androidbase.vo;

import java.util.Date;

/**
 * 线损管理
 * Created by victor on 8/22/16.
 */
public class LineBrokenManagementVo {


    //任务派发
    private Date AssignmentTime;
    //任务编号
    private String TaskNum;
    //台区名称
    private String AreaName;
    //线损率
    private String BrokenRate;
    //安全措施
    private String SafetyMeasure;

    //现场处理
    private Date BeginHandleTime;
    //不合格原因
    private String UnqualifiedReason;
    //处理内容
    private String HandleContent;
    //处理人员
    private int UserID;

    //处理结果
    private Date EndHandleTiem;
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

    public String getBrokenRate() {
        return BrokenRate;
    }

    public void setBrokenRate(String brokenRate) {
        BrokenRate = brokenRate;
    }

    public String getSafetyMeasure() {
        return SafetyMeasure;
    }

    public void setSafetyMeasure(String safetyMeasure) {
        SafetyMeasure = safetyMeasure;
    }

    public Date getBeginHandleTime() {
        return BeginHandleTime;
    }

    public void setBeginHandleTime(Date beginHandleTime) {
        BeginHandleTime = beginHandleTime;
    }

    public String getUnqualifiedReason() {
        return UnqualifiedReason;
    }

    public void setUnqualifiedReason(String unqualifiedReason) {
        UnqualifiedReason = unqualifiedReason;
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

    public Date getEndHandleTiem() {
        return EndHandleTiem;
    }

    public void setEndHandleTiem(Date endHandleTiem) {
        EndHandleTiem = endHandleTiem;
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
