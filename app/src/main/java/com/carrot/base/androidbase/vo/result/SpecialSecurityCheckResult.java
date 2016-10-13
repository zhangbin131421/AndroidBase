


// *********************SpecialSecurityCheck Result ********************** 
// *********************专项安全检查        ********************** 



package com.carrot.base.androidbase.vo.result;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;

/**
 * Created by victor on 9/1/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpecialSecurityCheckResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int iD;

    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;

    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;

    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;

    public static final String AssignmentTime = "AssignmentTime";
    @JsonProperty(value=AssignmentTime)
    public String assignmentTime;

    public static final String TaskNum = "TaskNum";
    @JsonProperty(value=TaskNum)
    public String taskNum;

//    public static final String BeginTime = "BeginTime";
//    @JsonProperty(value=BeginTime)
//    public String beginTime;

    public static final String CheckScope = "CheckScope";
    @JsonProperty(value=CheckScope)
    public String checkScope;


    public static final String CheckContent = "CheckContent";
    @JsonProperty(value=CheckContent)
    public String checkContent;


//    public static final String EndTime = "EndTime";
//    @JsonProperty(value=EndTime)
//    public String endTime;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String ExistIssue = "ExistIssue";
    @JsonProperty(value=ExistIssue)
    public String existIssue;

    public static final String CheckDate = "CheckDate";
    @JsonProperty(value=CheckDate)
    public String checkDate;

    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;

    public static final String Checker = "Checker";
    @JsonProperty(value=Checker)
    public String checker;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(SpecialSecurityCheckResult.ID, this.iD+"");
        rtn.add(SpecialSecurityCheckResult.UserID, this.userID+"");
        rtn.add(SpecialSecurityCheckResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(SpecialSecurityCheckResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
//        rtn.add(SpecialSecurityCheckResult.BeginTime, this.beginTime == null ? "" : this.beginTime.getBytes("UTF-8"));
//        rtn.add(SpecialSecurityCheckResult.EndTime, this.endTime == null ? "" : this.endTime.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.ExistIssue, this.existIssue == null ? "" : this.existIssue.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.CheckDate, this.checkDate == null ? "" : this.checkDate.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.IsHandled, this.isHandled+"");
        rtn.add(SpecialSecurityCheckResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));

        rtn.add(SpecialSecurityCheckResult.CheckContent, this.checkContent == null ? "" : this.checkContent.getBytes("UTF-8"));
        rtn.add(SpecialSecurityCheckResult.CheckScope, this.checkScope == null ? "" : this.checkScope.getBytes("UTF-8"));

        rtn.add(SpecialSecurityCheckResult.Checker, this.checker == null ? "" : this.checker.getBytes("UTF-8"));
        return rtn;
    }
}
