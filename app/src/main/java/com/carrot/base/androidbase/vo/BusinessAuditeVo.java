package com.carrot.base.androidbase.vo;

import java.util.Date;

/**
 * 营业普查
 * Created by victor on 8/22/16.
 */
public class BusinessAuditeVo {


    //任务派发
    private Date AssignmentTime;
    //任务编号
    private String TaskNum;
    //普查内容
    private String AuditeContent;
    //普查范围
    private String AuditeScope;
    //安全措施
    private String SafetyMeasure;

    //普查情况
    private Date BeginAuditeTime;
    //普查户数
    private String AuditeHouseholdNum;
    //检查情况
    private String AuditeResult;

    //处理结果
    private Date EndAuditeTime;
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

    public String getAuditeContent() {
        return AuditeContent;
    }

    public void setAuditeContent(String auditeContent) {
        AuditeContent = auditeContent;
    }

    public String getAuditeScope() {
        return AuditeScope;
    }

    public void setAuditeScope(String auditeScope) {
        AuditeScope = auditeScope;
    }

    public String getSafetyMeasure() {
        return SafetyMeasure;
    }

    public void setSafetyMeasure(String safetyMeasure) {
        SafetyMeasure = safetyMeasure;
    }

    public Date getBeginAuditeTime() {
        return BeginAuditeTime;
    }

    public void setBeginAuditeTime(Date beginAuditeTime) {
        BeginAuditeTime = beginAuditeTime;
    }

    public String getAuditeHouseholdNum() {
        return AuditeHouseholdNum;
    }

    public void setAuditeHouseholdNum(String auditeHouseholdNum) {
        AuditeHouseholdNum = auditeHouseholdNum;
    }

    public String getAuditeResult() {
        return AuditeResult;
    }

    public void setAuditeResult(String auditeResult) {
        AuditeResult = auditeResult;
    }

    public Date getEndAuditeTime() {
        return EndAuditeTime;
    }

    public void setEndAuditeTime(Date endAuditeTime) {
        EndAuditeTime = endAuditeTime;
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
