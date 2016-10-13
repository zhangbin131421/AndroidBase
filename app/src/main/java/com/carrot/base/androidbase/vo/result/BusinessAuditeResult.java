


// *********************BusinessAudite Result ********************** 
// *********************营业普查        ********************** 



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
public class BusinessAuditeResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int iD;

    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;

    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;

    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;

    public static final String Worker = "Worker";
    @JsonProperty(value=Worker)
    public String worker;

    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;

    public static final String AssignmentTime = "AssignmentTime";
    @JsonProperty(value=AssignmentTime)
    public String assignmentTime;

    public static final String TaskNum = "TaskNum";
    @JsonProperty(value=TaskNum)
    public String taskNum;

    public static final String AuditeContent = "AuditeContent";
    @JsonProperty(value=AuditeContent)
    public String auditeContent;

    public static final String AuditeScope = "AuditeScope";
    @JsonProperty(value=AuditeScope)
    public String auditeScope;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginAuditeTime = "BeginAuditeTime";
    @JsonProperty(value=BeginAuditeTime)
    public String beginAuditeTime;

    public static final String AuditeHouseholdNum = "AuditeHouseholdNum";
    @JsonProperty(value=AuditeHouseholdNum)
    public String auditeHouseholdNum;

    public static final String AuditeResult = "AuditeResult";
    @JsonProperty(value=AuditeResult)
    public String auditeResult;

    public static final String EndAuditeTime = "EndAuditeTime";
    @JsonProperty(value=EndAuditeTime)
    public String endAuditeTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(BusinessAuditeResult.ID, this.iD+"");
        rtn.add(BusinessAuditeResult.UserID, this.userID+"");
        rtn.add(BusinessAuditeResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(BusinessAuditeResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.AuditeContent, this.auditeContent == null ? "" : this.auditeContent.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.AuditeScope, this.auditeScope == null ? "" : this.auditeScope.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.BeginAuditeTime, this.beginAuditeTime == null ? "" : this.beginAuditeTime.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.AuditeHouseholdNum, this.auditeHouseholdNum == null ? "" : this.auditeHouseholdNum.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.AuditeResult, this.auditeResult == null ? "" : this.auditeResult.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.EndAuditeTime, this.endAuditeTime == null ? "" : this.endAuditeTime.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.IsHandled, this.isHandled+"");
        rtn.add(BusinessAuditeResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));


        rtn.add(BusinessAuditeResult.EndTime, this.endTime == null ? "" : this.endTime.getBytes("UTF-8"));
        rtn.add(BusinessAuditeResult.Worker, this.worker == null ? "" : this.worker.getBytes("UTF-8"));

        return rtn;
    }
}
