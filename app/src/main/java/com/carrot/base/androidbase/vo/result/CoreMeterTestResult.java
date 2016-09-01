package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by victor on 8/28/16.
 */
@Parcel
public class CoreMeterTestResult extends TaskBaseVo {
//    [{"ID":1,"UserID":1,"TaskNum":"T111","AssignmentTime":"\/Date(1471795200000)\/","AssignByUserID":2,
//            "AreaName":"测试","ProtectLine":"测试","Type":"1","SafetyMeasure":"测试",
//            "BeginHandleTime":"\/Date(1471968000000)\/","EndTime":"\/Date(1471968000000)\/",
//            "Wether":"晴朗","TestWay":"测试","ATesting":"a","BTesting":"b","CTesting":"c","TestResult":"合格",
//            "HandleContent":"测试","Tester":"1","TestingTime":"\/Date(1471968000000)\/",
//            "EndHandleTime":"\/Date(1471968000000)\/","IsHandled":1,"UnhandleReason":null,"CreatedTime":null,
//            "UpdatedTime":null,"IsDelete":null}]



//            "AreaName":"测试",
    @JsonProperty(value="TaskNum")
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

    @JsonProperty(value="IsHandled")
    public int isHandled;

    @JsonProperty(value="UnhandleReason")
    public String unhandleReason;

    @JsonProperty(value="CreatedTime")
    public String createdTime;

    @JsonProperty(value="UpdatedTime")
    public String updatedTime;

    @JsonProperty(value="IsDelete")
    public String isDelete;

}
