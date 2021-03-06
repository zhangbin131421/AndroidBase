


// *********************EarthResistanceTest Result ********************** 
// *********************接地电阻测量        ********************** 



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
public class EarthResistanceTestResult {


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

    public static final String EarthPlace = "EarthPlace";
    @JsonProperty(value=EarthPlace)
    public String earthPlace;

    public static final String EarthEquipmentName = "EarthEquipmentName";
    @JsonProperty(value=EarthEquipmentName)
    public String earthEquipmentName;

    public static final String ResistanceValue = "ResistanceValue";
    @JsonProperty(value=ResistanceValue)
    public String resistanceValue;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String Wether = "Wether";
    @JsonProperty(value=Wether)
    public String wether;

    public static final String TestResistanceValue = "TestResistanceValue";
    @JsonProperty(value=TestResistanceValue)
    public String testResistanceValue;

    public static final String TestResistanceValuePic = "TestResistanceValuePic";
    @JsonProperty(value=TestResistanceValuePic)
    public String testResistanceValuePic;

    public static final String TestDate = "TestDate";
    @JsonProperty(value=TestDate)
    public String testDate;

    public static final String Tester = "Tester";
    @JsonProperty(value=Tester)
    public String tester;

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

        rtn.add(EarthResistanceTestResult.ID, this.iD+"");
        rtn.add(EarthResistanceTestResult.UserID, this.userID+"");
        rtn.add(EarthResistanceTestResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(EarthResistanceTestResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.AreaName, this.areaName == null ? "" : this.areaName.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.AreaID, this.areaID+"");
        rtn.add(EarthResistanceTestResult.EarthPlace, this.earthPlace == null ? "" : this.earthPlace.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.EarthEquipmentName, this.earthEquipmentName == null ? "" : this.earthEquipmentName.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.ResistanceValue, this.resistanceValue == null ? "" : this.resistanceValue.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.Wether, this.wether == null ? "" : this.wether.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.TestResistanceValue, this.testResistanceValue == null ? "" : this.testResistanceValue.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.TestDate, this.testDate == null ? "" : this.testDate.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.Tester, this.tester == null ? "" : this.tester.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(EarthResistanceTestResult.IsHandled, this.isHandled+"");
        rtn.add(EarthResistanceTestResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}
