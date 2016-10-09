


// *********************CrossTest Result ********************** 
// *********************交叉跨越测量        ********************** 



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
public class CrossTestResult {


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

    public static final String AssignmentTime = "AssignmentTime";
    @JsonProperty(value=AssignmentTime)
    public String assignmentTime;

    public static final String TaskNum = "TaskNum";
    @JsonProperty(value=TaskNum)
    public String taskNum;

    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;

    public static final String CrossPoint = "CrossPoint";
    @JsonProperty(value=CrossPoint)
    public String crossPoint;

    public static final String CrossName = "CrossName";
    @JsonProperty(value=CrossName)
    public String crossName;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String EarthDistance = "EarthDistance";
    @JsonProperty(value=EarthDistance)
    public String earthDistance;

    public static final String CrossDistance = "CrossDistance";
    @JsonProperty(value=CrossDistance)
    public String crossDistance;

    public static final String IsQualified = "IsQualified";
    @JsonProperty(value=IsQualified)
    public String isQualified;

    public static final String ModificationOpinion = "ModificationOpinion";
    @JsonProperty(value=ModificationOpinion)
    public String modificationOpinion;

    public static final String TestTime = "TestTime";
    @JsonProperty(value=TestTime)
    public String testTime;

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

        rtn.add(CrossTestResult.ID, this.id+"");
        rtn.add(CrossTestResult.UserID, this.userId+"");
        rtn.add(CrossTestResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(CrossTestResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(CrossTestResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(CrossTestResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(CrossTestResult.AreaName, this.areaName.getBytes("UTF-8"));
        rtn.add(CrossTestResult.CrossPoint, this.crossPoint.getBytes("UTF-8"));
        rtn.add(CrossTestResult.CrossName, this.crossName.getBytes("UTF-8"));
        rtn.add(CrossTestResult.SafetyMeasure, this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(CrossTestResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(CrossTestResult.EarthDistance, this.earthDistance == null ? "" : this.earthDistance.getBytes("UTF-8"));
        rtn.add(CrossTestResult.CrossDistance, this.crossDistance == null ? "" : this.crossDistance.getBytes("UTF-8"));
        rtn.add(CrossTestResult.IsQualified, this.isQualified.getBytes("UTF-8"));
        rtn.add(CrossTestResult.ModificationOpinion, this.modificationOpinion.getBytes("UTF-8"));
        rtn.add(CrossTestResult.TestTime, this.testTime == null ? "" : this.testTime.getBytes("UTF-8"));
        rtn.add(CrossTestResult.Tester, this.tester.getBytes("UTF-8"));
        rtn.add(CrossTestResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(CrossTestResult.IsHandled, this.isHandled+"");
        rtn.add(CrossTestResult.UnhandleReason, this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}
