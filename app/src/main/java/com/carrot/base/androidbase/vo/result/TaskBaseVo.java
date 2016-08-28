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

    //            "TaskNum":"T111",
    @JsonProperty(value="TaskNum")
    public String taskNum;
    //            "AssignmentTime":"\/Date(1471795200000)\/",
    @JsonProperty(value="AssignmentTime")
    public String assignmentTime;
    //            "AssignByUserID":2,
    @JsonProperty(value="AssignByUserID")
    public String assignByUserID;



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
    //            "CreatedTime":null,
    @JsonProperty(value="CreatedTime")
    public String createdTime;
    //            "UpdatedTime":null,
    @JsonProperty(value="UpdatedTime")
    public String updatedTime;
    //            "IsDelete":null}]
    @JsonProperty(value="IsDelete")
    public String isDelete;
//
//
//    @JsonIgnore
//    public int getId() {
//        return id;
//    }
//    @JsonIgnore
//    public void setId(int id) {
//        this.id = id;
//    }
//    @JsonIgnore
//    public int getUserId() {
//        return userId;
//    }
//    @JsonIgnore
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
//    @JsonIgnore
//    public String getTaskNum() {
//        return taskNum;
//    }
//    @JsonIgnore
//    public void setTaskNum(String taskNum) {
//        this.taskNum = taskNum;
//    }
//    @JsonIgnore
//    public String getAssignmentTime() {
//        return assignmentTime;
//    }
//    @JsonIgnore
//    public void setAssignmentTime(String assignmentTime) {
//        this.assignmentTime = assignmentTime;
//    }
//    @JsonIgnore
//    public String getAssignByUserID() {
//        return assignByUserID;
//    }
//    @JsonIgnore
//    public void setAssignByUserID(String assignByUserID) {
//        this.assignByUserID = assignByUserID;
//    }

}
