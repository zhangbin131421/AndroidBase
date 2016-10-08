
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

    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;

    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int id;
    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userId;
    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;    public static final
    String AssignmentTime = "AssignmentTime";
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
    public int powerHouseholder;
    public static final String PowerCapacity = "PowerCapacity";
    @JsonProperty(value=PowerCapacity)
    public String powerCapacity;
    public static final String Householder = "Householder";
    @JsonProperty(value=Householder)
    public int householder;
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
    public static final String CurrentAPic = "CurrentAPic";
    @JsonProperty(value=CurrentAPic)
    public String currentAPic;
    public static final String CurrentBPic = "CurrentBPic";
    @JsonProperty(value=CurrentBPic)
    public String currentBPic;
    public static final String CurrentCPic = "CurrentCPic";
    @JsonProperty(value=CurrentCPic)
    public String currentCPic;
    public static final String ZeoLineCurrentPic = "ZeoLineCurrentPic";
    @JsonProperty(value=ZeoLineCurrentPic)
    public String zeoLineCurrentPic;
    public static final String LoadRate = "LoadRate";
    @JsonProperty(value=LoadRate)
    public String loadRate;
    public static final String ImbalanceRate = "ImbalanceRate";
    @JsonProperty(value=ImbalanceRate)
    public String imbalanceRate;
    public static final String HeaderVoltagePic = "HeaderVoltagePic";
    @JsonProperty(value=HeaderVoltagePic)
    public String headerVoltagePic;
    public static final String FooterVoltagePic = "FooterVoltagePic";
    @JsonProperty(value=FooterVoltagePic)
    public String footerVoltagePic;
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
        rtn.add(VoltageMeasurementResult.UserID, this.userId+"");
        rtn.add(VoltageMeasurementResult.CreatedTime, this.createdTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.AssignmentTime, this.assignmentTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ID, this.id +"");
        rtn.add(VoltageMeasurementResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.AreaName, this.areaName.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigA, this.configA.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigB, this.configB.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ConfigC, this.configC.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.RatedCurrent, this.ratedCurrent.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.PowerHouseholder, this.powerHouseholder + "");
        rtn.add(VoltageMeasurementResult.PowerCapacity, this.powerCapacity.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Householder, this.householder+"");
        rtn.add(VoltageMeasurementResult.HouseholderCapacity, this.householderCapacity.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.SafetyMeasure, this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.EndTime, this.endTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.BeginHandleTime, this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Period, this.period.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentAPic, this.currentAPic == null ? "" : this.currentAPic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentBPic, this.currentBPic == null ? "" : this.currentBPic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.CurrentCPic, this.currentCPic == null ? "" : this.currentCPic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ZeoLineCurrentPic, this.zeoLineCurrentPic == null ? "" : this.zeoLineCurrentPic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.LoadRate, this.loadRate.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ImbalanceRate, this.imbalanceRate.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.HeaderVoltagePic, this.headerVoltagePic == null ? "" : this.headerVoltagePic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.FooterVoltagePic, this.footerVoltagePic == null ? "" : this.footerVoltagePic.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.IsOutOfLimit, this.isOutOfLimit.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.ModificationOpinion, this.modificationOpinion.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.TestTime, this.testTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.Tester, this.tester.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.EndHandleTime, this.endHandleTime.getBytes("UTF-8"));
        rtn.add(VoltageMeasurementResult.IsHandled, this.isHandled);
        rtn.add(VoltageMeasurementResult.UnhandleReason, this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}