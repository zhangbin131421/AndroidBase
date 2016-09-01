package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 8/28/16.
 */
public class TaskBaseVo {
    //    [{"ID":1,
    @JsonProperty(value="ID")
    public int id;

    //
//            "UserID":1,
    @JsonProperty(value="UserID")
    public int userId;
    //            "AssignByUserID":2,
    @JsonProperty(value="AssignByUserID")
    public int assignByUserID;

    //            "CreatedTime":null,
    @JsonProperty(value="CreatedTime")
    public String createdTime;


    //            "TaskNum":"T111",
    @JsonProperty(value="TaskNum")
    public String taskNum;

    //            "AssignmentTime":"\/Date(1471795200000)\/",
    @JsonProperty(value="AssignmentTime")
    public String assignmentTime;

    //            "AreaName":"测试",
    @JsonProperty(value="AreaName")
    public String areaName;
    //            "ProtectLine":"测试",
    @JsonProperty(value="ProtectLine")
    public String protectLine;
    //            "Type":"1",
    @JsonProperty(value="Type")
    public String type;
    //            "SafetyMeasure":"测试",
    @JsonProperty(value="SafetyMeasure")
    public String safetyMeasure;
    //            "BeginHandleTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="BeginHandleTime")
    public String beginHandleTime;
    //            "EndTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="EndTime")
    public String endTime;
    //            "Wether":"晴朗",
    @JsonProperty(value="Wether")
    public String wether;
    //            "TestWay":"测试",
    @JsonProperty(value="TestWay")
    public String testWay;
    //            "ATesting":"a",
    @JsonProperty(value="ATesting")
    public String aTesting;
    //            "BTesting":"b",
    @JsonProperty(value="BTesting")
    public String bTesting;
    //            "CTesting":"c",
    @JsonProperty(value="CTesting")
    public String cTesting;
    //            "TestResult":"合格",
    @JsonProperty(value="TestResult")
    public String testResult;
    //            "HandleContent":"测试",
    @JsonProperty(value="HandleContent")
    public String handleContent;
    //            "Tester":"1",
    @JsonProperty(value="Tester")
    public String tester;
    //            "TestingTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="TestingTime")
    public String testingTime;
    //            "EndHandleTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="EndHandleTime")
    public String endHandleTime;
    //            "IsHandled":1,
    @JsonProperty(value="IsHandled")
    public int isHandled;
    //            "UnhandleReason":null,
    @JsonProperty(value="UnhandleReason")
    public String unhandleReason;
    //            "UpdatedTime":null,
    @JsonProperty(value="UpdatedTime")
    public String updatedTime;
    //            "IsDelete":null}]
    @JsonProperty(value="IsDelete")
    public String isDelete;

}
