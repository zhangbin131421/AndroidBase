


// *********************StopStartElectric Result ********************** 
// *********************停复电        ********************** 



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
public class StopStartElectricResult {


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

    public static final String StopStartElectricAddress = "StopStartElectricAddress";
    @JsonProperty(value=StopStartElectricAddress)
    public String stopStartElectricAddress;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;

    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;

    public static final String HandleType = "HandleType";
    @JsonProperty(value=HandleType)
    public String handleType;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;


    public static final String HandleContentPic = "HandleContentPic";
    @JsonProperty(value=HandleContentPic)
    public String handleContentPic;

    public static final String Worker = "Worker";
    @JsonProperty(value=Worker)
    public String worker;




    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(StopStartElectricResult.ID, this.iD+"");
        rtn.add(StopStartElectricResult.UserID, this.userID+"");
        rtn.add(StopStartElectricResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(StopStartElectricResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.AreaName, this.areaName == null ? "" : this.areaName.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.AreaID, this.areaID+"");
        rtn.add(StopStartElectricResult.StopStartElectricAddress, this.stopStartElectricAddress == null ? "" : this.stopStartElectricAddress.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.HandleContent, this.handleContent == null ? "" : this.handleContent.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.IsHandled, this.isHandled+"");
        rtn.add(StopStartElectricResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));

        rtn.add(StopStartElectricResult.Worker, this.worker == null ? "" : this.worker.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.EndTime, this.endTime == null ? "" : this.endTime.getBytes("UTF-8"));
        rtn.add(StopStartElectricResult.HandleType, this.handleType == null ? "" : this.handleType.getBytes("UTF-8"));
        return rtn;
    }
}
