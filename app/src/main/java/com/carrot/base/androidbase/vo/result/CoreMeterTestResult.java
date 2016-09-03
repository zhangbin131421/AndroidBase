package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by victor on 8/28/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoreMeterTestResult {

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

    public static final String ProtectLine = "ProtectLine";
    @JsonProperty(value=ProtectLine)
    public String protectLine;

    public static final String Type = "Type";
    @JsonProperty(value=Type)
    public String type;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;

    public static final String Wether = "Wether";
    @JsonProperty(value=Wether)
    public String wether;

    public static final String TestWay = "TestWay";
    @JsonProperty(value=TestWay)
    public String testWay;

    public static final String ATesting = "ATesting";
    @JsonProperty(value=ATesting)
    public String aTesting;

    public static final String BTesting = "BTesting";
    @JsonProperty(value=BTesting)
    public String bTesting;

    public static final String CTesting = "CTesting";
    @JsonProperty(value=CTesting)
    public String cTesting;

    public static final String TestResult = "TestResult";
    @JsonProperty(value=TestResult)
    public String testResult;

    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;

    public static final String Tester = "Tester";
    @JsonProperty(value=Tester)
    public String tester;

    public static final String TestingTime = "TestingTime";
    @JsonProperty(value=TestingTime)
    public String testingTime;

    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;

    public static final String UpdatedTime = "UpdatedTime";
    @JsonProperty(value=UpdatedTime)
    public String updatedTime;

    public static final String IsDelete = "IsDelete";
    @JsonProperty(value=IsDelete)
    public int isDelete;

    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap(){
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(CoreMeterTestResult.AreaName, this.areaName);
        rtn.add(CoreMeterTestResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(CoreMeterTestResult.AssignmentTime, this.assignmentTime);
        rtn.add(CoreMeterTestResult.ATesting, this.aTesting);
        rtn.add(CoreMeterTestResult.BeginHandleTime, this.beginHandleTime);
        rtn.add(CoreMeterTestResult.BTesting, this.bTesting);
        rtn.add(CoreMeterTestResult.CreatedTime, this.createdTime);
        rtn.add(CoreMeterTestResult.CTesting, this.cTesting);
        rtn.add(CoreMeterTestResult.EndHandleTime, this.endHandleTime);
        rtn.add(CoreMeterTestResult.EndTime, this.endTime);
        rtn.add(CoreMeterTestResult.HandleContent, this.handleContent);
        rtn.add(CoreMeterTestResult.ID, this.id+"");
        rtn.add(CoreMeterTestResult.IsDelete, this.isDelete+"");
        rtn.add(CoreMeterTestResult.IsHandled, this.isHandled+"");
        rtn.add(CoreMeterTestResult.ProtectLine, this.protectLine);
        rtn.add(CoreMeterTestResult.SafetyMeasure, this.safetyMeasure);
        rtn.add(CoreMeterTestResult.TaskNum, this.taskNum);
        rtn.add(CoreMeterTestResult.Tester, this.tester);
        rtn.add(CoreMeterTestResult.TestingTime, this.testingTime);
        rtn.add(CoreMeterTestResult.TestResult, this.testResult);
        rtn.add(CoreMeterTestResult.TestWay, this.testWay);
        rtn.add(CoreMeterTestResult.Type, this.type);
        rtn.add(CoreMeterTestResult.UnhandleReason, this.unhandleReason);
        rtn.add(CoreMeterTestResult.UpdatedTime, this.updatedTime);
        rtn.add(CoreMeterTestResult.UserID, this.userId+"");
        rtn.add(CoreMeterTestResult.Wether, this.wether);


        return rtn;
    }

}
