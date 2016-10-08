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
public class LineBrokenManagementResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int id;
    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userId;
    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;
    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;
    public static final String TaskNum = "TaskNum";
    @JsonProperty(value=TaskNum)
    public String taskNum;
    public static final String AssignmentTime = "AssignmentTime";
    @JsonProperty(value=AssignmentTime)
    public String assignmentTime;


    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;




    public static final String BrokenRate = "BrokenRate";
    @JsonProperty(value=BrokenRate)
    public String brokenRate;




    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;




    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;




    public static final String UnqualifiedReason = "UnqualifiedReason";
    @JsonProperty(value=UnqualifiedReason)
    public String unqualifiedReason;




    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;

    public static final String HandleContentPic = "HandleContentPic";
    @JsonProperty(value=HandleContentPic)
    public String handleContentPic;




    public static final String EndHandleTiem = "EndHandleTiem";
    @JsonProperty(value=EndHandleTiem)
    public String endHandleTiem;




    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;




    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;



    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(LineBrokenManagementResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(LineBrokenManagementResult.AssignmentTime, this.assignmentTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.CreatedTime, this.createdTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.ID, this.id+"");
        rtn.add(LineBrokenManagementResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.UserID, this.userId+"");
        rtn.add(LineBrokenManagementResult.AreaName, this.areaName.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.BrokenRate, this.brokenRate.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.SafetyMeasure, this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.BeginHandleTime, this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.UnqualifiedReason, this.unqualifiedReason.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.HandleContent, this.handleContent.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.EndHandleTiem, this.endHandleTiem.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.IsHandled, this.isHandled);
        rtn.add(LineBrokenManagementResult.UnhandleReason, this.unhandleReason.getBytes("UTF-8"));


        return rtn;
    }
}
