


// *********************MeterTrouble Result ********************** 
// *********************表计故障        ********************** 



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
public class MeterTroubleResult {


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

    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;

    public static final String AreaID = "AreaID";
    @JsonProperty(value=AreaID)
    public int areaID;

    public static final String HouseholdNum = "HouseholdNum";
    @JsonProperty(value=HouseholdNum)
    public String householdNum;

    public static final String MeterNum = "MeterNum";
    @JsonProperty(value=MeterNum)
    public String meterNum;

    public static final String TroubleAddress = "TroubleAddress";
    @JsonProperty(value=TroubleAddress)
    public String troubleAddress;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String TroubleReason = "TroubleReason";
    @JsonProperty(value=TroubleReason)
    public String troubleReason;

    public static final String TroubleReasonPic = "TroubleReasonPic";
    @JsonProperty(value=TroubleReasonPic)
    public String troubleReasonPic;

    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;

    public static final String HandleContentPic = "HandleContentPic";
    @JsonProperty(value=HandleContentPic)
    public String handleContentPic;

    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(MeterTroubleResult.ID, this.iD+"");
        rtn.add(MeterTroubleResult.UserID, this.userID+"");
        rtn.add(MeterTroubleResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(MeterTroubleResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.AreaName, this.areaName == null ? "" : this.areaName.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.AreaID, this.areaID+"");
        rtn.add(MeterTroubleResult.HouseholdNum, this.householdNum == null ? "" : this.householdNum.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.MeterNum, this.meterNum == null ? "" : this.meterNum.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.TroubleAddress, this.troubleAddress == null ? "" : this.troubleAddress.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.TroubleReason, this.troubleReason == null ? "" : this.troubleReason.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.HandleContent, this.handleContent == null ? "" : this.handleContent.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(MeterTroubleResult.IsHandled, this.isHandled+"");
        rtn.add(MeterTroubleResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}
