


// *********************VoltageMeasurement Result ********************** 
// *********************负荷电压测量        ********************** 



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
public class VoltageMeasurementResult {


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

    public static final String ConfigA = "ConfigA";
    @JsonProperty(value=ConfigA)
    public String configA;

    public static final String ConfigB = "ConfigB";
    @JsonProperty(value=ConfigB)
    public String configB;

    public static final String ConfigC = "ConfigC";
    @JsonProperty(value=ConfigC)
    public String configC;

    public static final String RatedCurrent = "RatedCurrent";
    @JsonProperty(value=RatedCurrent)
    public String ratedCurrent;

    public static final String PowerHouseholder = "PowerHouseholder";
    @JsonProperty(value=PowerHouseholder)
    public String powerHouseholder;

    public static final String PowerCapacity = "PowerCapacity";
    @JsonProperty(value=PowerCapacity)
    public String powerCapacity;

    public static final String Householder = "Householder";
    @JsonProperty(value=Householder)
    public String householder;

    public static final String HouseholderCapacity = "HouseholderCapacity";
    @JsonProperty(value=HouseholderCapacity)
    public String householderCapacity;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String Period = "Period";
    @JsonProperty(value=Period)
    public String period;

    public static final String CurrentA = "CurrentA";
    @JsonProperty(value=CurrentA)
    public String currentA;

    public static final String CurrentB = "CurrentB";
    @JsonProperty(value=CurrentB)
    public String currentB;

    public static final String CurrentC = "CurrentC";
    @JsonProperty(value=CurrentC)
    public String currentC;

    public static final String ZeoLineCurrent = "ZeoLineCurrent";
    @JsonProperty(value=ZeoLineCurrent)
    public String zeoLineCurrent;

    public static final String LoadRate = "LoadRate";
    @JsonProperty(value=LoadRate)
    public String loadRate;

    public static final String ImbalanceRate = "ImbalanceRate";
    @JsonProperty(value=ImbalanceRate)
    public String imbalanceRate;

    public static final String HeaderVoltage = "HeaderVoltage";
    @JsonProperty(value=HeaderVoltage)
    public String headerVoltage;

    public static final String FooterVoltage = "FooterVoltage";
    @JsonProperty(value=FooterVoltage)
    public String footerVoltage;

    public static final String IsOutOfLimit = "IsOutOfLimit";
    @JsonProperty(value=IsOutOfLimit)
    public String isOutOfLimit;

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

        rtn.add(VoltageMeasurementResult.ID, this.iD+"");
        rtn.add(VoltageMeasurementResult.UserID, this.userID+"");
        rtn.add(VoltageMeasurementResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(VoltageMeasurementResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.TaskNum, this.taskNum == null ? "" : this.taskNum.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.AreaName, this.areaName == null ? "" : this.areaName.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigA, this.configA == null ? "" : this.configA.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigB, this.configB == null ? "" : this.configB.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigC, this.configC == null ? "" : this.configC.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.RatedCurrent, this.ratedCurrent == null ? "" : this.ratedCurrent.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.PowerHouseholder, this.powerHouseholder == null ? "" : this.powerHouseholder.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.PowerCapacity, this.powerCapacity == null ? "" : this.powerCapacity.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Householder, this.householder == null ? "" : this.householder.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.HouseholderCapacity, this.householderCapacity == null ? "" : this.householderCapacity.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.SafetyMeasure, this.safetyMeasure == null ? "" : this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.EndTime, this.endTime == null ? "" : this.endTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Period, this.period == null ? "" : this.period.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentA, this.currentA == null ? "" : this.currentA.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentB, this.currentB == null ? "" : this.currentB.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentC, this.currentC == null ? "" : this.currentC.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ZeoLineCurrent, this.zeoLineCurrent == null ? "" : this.zeoLineCurrent.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.LoadRate, this.loadRate == null ? "" : this.loadRate.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ImbalanceRate, this.imbalanceRate == null ? "" : this.imbalanceRate.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.HeaderVoltage, this.headerVoltage == null ? "" : this.headerVoltage.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.FooterVoltage, this.footerVoltage == null ? "" : this.footerVoltage.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.IsOutOfLimit, this.isOutOfLimit == null ? "" : this.isOutOfLimit.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ModificationOpinion, this.modificationOpinion == null ? "" : this.modificationOpinion.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.TestTime, this.testTime == null ? "" : this.testTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Tester, this.tester == null ? "" : this.tester.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.IsHandled, this.isHandled+"");
        rtn.add(VoltageMeasurementResult.UnhandleReason, this.unhandleReason == null ? "" : this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}
