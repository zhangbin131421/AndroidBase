

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
public class DistributionNetworkEngineeringResult {


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
    public static final String EngineeringName = "EngineeringName";
    @JsonProperty(value=EngineeringName)
    public String engineeringName;
    public static final String EngineeringNum = "EngineeringNum";
    @JsonProperty(value=EngineeringNum)
    public String engineeringNum;
    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;
    public static final String ExecutionCompany = "ExecutionCompany";
    @JsonProperty(value=ExecutionCompany)
    public String executionCompany;
    public static final String WorkContent = "WorkContent";
    @JsonProperty(value=WorkContent)
    public String workContent;
    public static final String WorkPlace = "WorkPlace";
    @JsonProperty(value=WorkPlace)
    public String workPlace;
    public static final String StopScope = "StopScope";
    @JsonProperty(value=StopScope)
    public String stopScope;
    public static final String StopTime = "StopTime";
    @JsonProperty(value=StopTime)
    public String stopTime;
    public static final String WorkLicensor = "WorkLicensor";
    @JsonProperty(value=WorkLicensor)
    public String workLicensor;
    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;
    public static final String WorkInvoiceNum = "WorkInvoiceNum";
    @JsonProperty(value=WorkInvoiceNum)
    public String workInvoiceNum;
    public static final String ExecutionResponsible = "ExecutionResponsible";
    @JsonProperty(value=ExecutionResponsible)
    public String executionResponsible;
    public static final String WorkResponsible = "WorkResponsible";
    @JsonProperty(value=WorkResponsible)
    public String workResponsible;
    public static final String WorkContent2 = "WorkContent2";
    @JsonProperty(value=WorkContent2)
    public String workContent2;
    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;
    public static final String ActualStopTime = "ActualStopTime";
    @JsonProperty(value=ActualStopTime)
    public String actualStopTime;
    public static final String EndStopTime = "EndStopTime";
    @JsonProperty(value=EndStopTime)
    public String endStopTime;
    public static final String Inspector = "Inspector";
    @JsonProperty(value=Inspector)
    public String inspector;
    public static final String Inspect = "Inspect";
    @JsonProperty(value=Inspect)
    public String inspect;
    public static final String Complete = "Complete";
    @JsonProperty(value=Complete)
    public String complete;
    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;
    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public String isHandled;
    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;


    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(DistributionNetworkEngineeringResult.ID, this.iD+"");
        rtn.add(DistributionNetworkEngineeringResult.UserID, this.userID+"");
        rtn.add(DistributionNetworkEngineeringResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(DistributionNetworkEngineeringResult.CreatedTime, this.createdTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.AssignmentTime, this.assignmentTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.EngineeringName, this.engineeringName.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.EngineeringNum, this.engineeringNum.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.AreaName, this.areaName.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.ExecutionCompany, this.executionCompany.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkContent, this.workContent.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkPlace, this.workPlace.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.StopScope, this.stopScope.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.StopTime, this.stopTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkLicensor, this.workLicensor.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.BeginHandleTime, this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkInvoiceNum, this.workInvoiceNum.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.ExecutionResponsible, this.executionResponsible.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkResponsible, this.workResponsible.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.WorkContent2, this.workContent2.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.SafetyMeasure, this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.ActualStopTime, this.actualStopTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.EndStopTime, this.endStopTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.Inspector, this.inspector.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.Inspect, this.inspect.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.Complete, this.complete.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.EndHandleTime, this.endHandleTime.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.IsHandled, this.isHandled.getBytes("UTF-8"));
        rtn.add(DistributionNetworkEngineeringResult.UnhandleReason, this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}