package com.carrot.base.androidbase.vo;

import java.util.Date;

/**
 * 采集消缺
 * Created by victor on 8/22/16.
 */
public class CollectResolveTroubleVo {


    //任务派发
    private Date AssignmentTime;
    //任务编号
    private String TaskNum;
    //台区名称
    private String AreaName;
    //故障地址
    private String TroubleAddress;
    //安全措施
    private String SafetyMeasure;
    //结束时间
    private Date EndTime;

    //现场处理
    private Date BeginHandleTime;
    //故障原因
    private String TroubleReason;
    //处理内容
    private String HandleContent;

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

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getTroubleAddress() {
        return TroubleAddress;
    }

    public void setTroubleAddress(String troubleAddress) {
        TroubleAddress = troubleAddress;
    }

    public String getSafetyMeasure() {
        return SafetyMeasure;
    }

    public void setSafetyMeasure(String safetyMeasure) {
        SafetyMeasure = safetyMeasure;
    }

    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        EndTime = endTime;
    }

    public Date getBeginHandleTime() {
        return BeginHandleTime;
    }

    public void setBeginHandleTime(Date beginHandleTime) {
        BeginHandleTime = beginHandleTime;
    }

    public String getTroubleReason() {
        return TroubleReason;
    }

    public void setTroubleReason(String troubleReason) {
        TroubleReason = troubleReason;
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
