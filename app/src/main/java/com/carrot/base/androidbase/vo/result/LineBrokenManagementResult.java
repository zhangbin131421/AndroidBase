


// *********************LineBrokenManagement Result ********************** 
// *********************线损管理        ********************** 



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

    public static final String ElectricityA = "ElectricityA";
    @JsonProperty(value=ElectricityA)
    public String electricityA;

    public static final String ElectricityB = "ElectricityB";
    @JsonProperty(value=ElectricityB)
    public String electricityB;

    public static final String ElectricityC = "ElectricityC";
    @JsonProperty(value=ElectricityC)
    public String electricityC;

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

    public static final String Worker = "Worker";
    @JsonProperty(value=Worker)
    public String worker;




    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(LineBrokenManagementResult.ID, this.iD+"");
        rtn.add(LineBrokenManagementResult.UserID, this.userID+"");
        rtn.add(LineBrokenManagementResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(LineBrokenManagementResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.AreaName, this.areaName == null ? "" : this.areaName.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.AreaID, this.areaID+"");
        rtn.add(LineBrokenManagementResult.ElectricityA, this.electricityA == null ? "" : this.electricityA.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.ElectricityB, this.electricityB == null ? "" : this.electricityB.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.ElectricityC, this.electricityC == null ? "" : this.electricityC.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.BrokenRate, this.brokenRate == null ? "" : this.brokenRate.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.UnqualifiedReason, this.unqualifiedReason == null ? "" : this.unqualifiedReason.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.HandleContent, this.handleContent == null ? "" : this.handleContent.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.EndHandleTiem, this.endHandleTiem == null ? "" : this.endHandleTiem.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.IsHandled, this.isHandled+"");
        rtn.add(LineBrokenManagementResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));
        rtn.add(LineBrokenManagementResult.Worker, this.worker == null ? "" : this.worker.getBytes("UTF-8"));

        return rtn;
    }
}
