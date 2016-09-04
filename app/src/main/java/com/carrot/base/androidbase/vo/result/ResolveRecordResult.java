package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Created by victor on 9/1/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResolveRecordResult {


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




    public static final String DefectPlace = "DefectPlace";
    @JsonProperty(value=DefectPlace)
    public String defectPlace;

    public static final String DefectContent = "DefectContent";
    @JsonProperty(value=DefectContent)
    public String defectContent;

    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;

    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;

    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;

    public static final String WorkType = "WorkType";
    @JsonProperty(value=WorkType)
    public String workType;

    public static final String WorkInvoiceNum = "WorkInvoiceNum";
    @JsonProperty(value=WorkInvoiceNum)
    public String workInvoiceNum;

    public static final String StopScope = "StopScope";
    @JsonProperty(value=StopScope)
    public String stopScope;

    public static final String Applier = "Applier";
    @JsonProperty(value=Applier)
    public String applier;

    public static final String WorkLicensor = "WorkLicensor";
    @JsonProperty(value=WorkLicensor)
    public String workLicensor;

    public static final String WorkPrincipal = "WorkPrincipal";
    @JsonProperty(value=WorkPrincipal)
    public String workPrincipal;

    public static final String StopTime = "StopTime";
    @JsonProperty(value=StopTime)
    public String stopTime;

    public static final String EndStopTime = "EndStopTime";
    @JsonProperty(value=EndStopTime)
    public String endStopTime;

    public static final String StopPeople = "StopPeople";
    @JsonProperty(value=StopPeople)
    public String stopPeople;

    public static final String Worker = "Worker";
    @JsonProperty(value=Worker)
    public String worker;

    public static final String OperationInvoiceNum = "OperationInvoiceNum";
    @JsonProperty(value=OperationInvoiceNum)
    public String operationInvoiceNum;

    public static final String WorkInstruction  = "WorkInstruction ";
    @JsonProperty(value=WorkInstruction )
    public String workInstruction;

    public static final String ResolveContent = "ResolveContent";
    @JsonProperty(value=ResolveContent)
    public String resolveContent;

    public static final String WorkDate = "WorkDate";
    @JsonProperty(value=WorkDate)
    public String workDate;

    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;

    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;


    public static final String ResolveContentPic = "ResolveContentPic";
    @JsonProperty(value=ResolveContentPic)
    public String resolveContentPic;

    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap(){
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(ResolveRecordResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(ResolveRecordResult.AssignmentTime, this.assignmentTime);
        rtn.add(ResolveRecordResult.CreatedTime, this.createdTime);
        rtn.add(ResolveRecordResult.ID, this.id+"");
        rtn.add(ResolveRecordResult.TaskNum, this.taskNum);
        rtn.add(ResolveRecordResult.UserID, this.userId+"");


        rtn.add(ResolveRecordResult.Applier, this.applier);
        rtn.add(ResolveRecordResult.BeginHandleTime, this.beginHandleTime);
        rtn.add(ResolveRecordResult.DefectContent, this.defectContent);
        rtn.add(ResolveRecordResult.DefectPlace, this.defectPlace);
        rtn.add(ResolveRecordResult.EndHandleTime, this.endHandleTime);
        rtn.add(ResolveRecordResult.EndStopTime, this.endStopTime);
        rtn.add(ResolveRecordResult.EndTime, this.endTime);
        rtn.add(ResolveRecordResult.IsHandled, this.isHandled+"");
        rtn.add(ResolveRecordResult.OperationInvoiceNum, this.operationInvoiceNum);
        rtn.add(ResolveRecordResult.ResolveContent, this.resolveContent == null ? "" : this.resolveContent);
        rtn.add(ResolveRecordResult.ResolveContentPic, this.resolveContentPic == null ? "" : this.resolveContentPic);
        rtn.add(ResolveRecordResult.SafetyMeasure, this.safetyMeasure);
        rtn.add(ResolveRecordResult.StopPeople, this.stopPeople);
        rtn.add(ResolveRecordResult.StopScope, this.stopScope);
        rtn.add(ResolveRecordResult.StopTime, this.stopTime);
        rtn.add(ResolveRecordResult.WorkDate, this.workDate);
        rtn.add(ResolveRecordResult.Worker, this.worker);
        rtn.add(ResolveRecordResult.WorkInstruction, this.workInstruction);
        rtn.add(ResolveRecordResult.WorkInvoiceNum, this.workInvoiceNum);
        rtn.add(ResolveRecordResult.WorkLicensor, this.workLicensor);
        rtn.add(ResolveRecordResult.WorkPrincipal, this.workPrincipal);
        rtn.add(ResolveRecordResult.WorkType, this.workType);
        rtn.add(ResolveRecordResult.UnhandleReason, this.unhandleReason);




        return rtn;
    }
}
