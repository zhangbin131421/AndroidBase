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
public class EquipmentCheckResult {


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


//            "CheckType":"11",
    public static final String CheckType = "CheckType";
    @JsonProperty(value=CheckType)
    public String checkType;
//            "":"22",
    public static final String CheckScope = "CheckScope";
    @JsonProperty(value=CheckScope)
    public String checkScope;
//            "":"33",
    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;
//            "":"2016-09-03 00:00:00",
    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;
//            "":"2016-09-03 00:00:00",
    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;
//            "":"44",
    public static final String ExistDefect = "ExistDefect";
    @JsonProperty(value=ExistDefect)
    public String existDefect;
//            "":"55",
    public static final String DefectPlace = "DefectPlace";
    @JsonProperty(value=DefectPlace)
    public String defectPlace;
//            "":"66",
    public static final String DefectContent = "DefectContent";
    @JsonProperty(value=DefectContent)
    public String defectContent;
//            "":"1",
    public static final String DefectLevel = "DefectLevel";
    @JsonProperty(value=DefectLevel)
    public String defectLevel;
//            "":"wewew",
    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;
//            "":"1",
    public static final String CheckPeople = "CheckPeople";
    @JsonProperty(value=CheckPeople)
    public String checkPeople;
//            "":"2016-09-03 00:00:00",
    public static final String CheckTime = "CheckTime";
    @JsonProperty(value=CheckTime)
    public String checkTime;
//            "EndHandleTime":"2016-09-03 00:00:00",
    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;
//            "":1,
    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public String isHandled;
//            "":null,
    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;
//            "":"2016-09-03 00:00:00",
    public static final String UpdatedTime = "UpdatedTime";
    @JsonProperty(value=UpdatedTime)
    public String updatedTime;
//            "":0,
    public static final String IsDelete = "IsDelete";
    @JsonProperty(value=IsDelete)
    public String isDelete;
//            "":null}
    public static final String DefectContentPic = "DefectContentPic";
    @JsonProperty(value=DefectContentPic)
    public String defectContentPic = "";


    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap(){
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(EquipmentCheckResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(EquipmentCheckResult.AssignmentTime, this.assignmentTime);
        rtn.add(EquipmentCheckResult.BeginHandleTime, this.beginHandleTime);
        rtn.add(EquipmentCheckResult.CheckPeople, this.checkPeople);
        rtn.add(EquipmentCheckResult.CheckScope, this.checkScope);
        rtn.add(EquipmentCheckResult.CheckTime, this.checkTime);
        rtn.add(EquipmentCheckResult.CheckType, this.checkType);
        rtn.add(EquipmentCheckResult.CreatedTime, this.createdTime);
        rtn.add(EquipmentCheckResult.DefectContent, this.defectContent);
//        rtn.add(EquipmentCheckResult.DefectContentPic, this.defectContentPic == null ? "" : this.defectContentPic);
        rtn.add(EquipmentCheckResult.DefectLevel, this.defectLevel);
        rtn.add(EquipmentCheckResult.DefectPlace, this.defectPlace);
        rtn.add(EquipmentCheckResult.EndHandleTime, this.endHandleTime);
        rtn.add(EquipmentCheckResult.EndTime, this.endTime);
        rtn.add(EquipmentCheckResult.ExistDefect, this.existDefect);
        rtn.add(EquipmentCheckResult.HandleContent, this.handleContent);
        rtn.add(EquipmentCheckResult.ID, this.id+"");
        rtn.add(EquipmentCheckResult.IsDelete, this.isDelete+"");
        rtn.add(EquipmentCheckResult.IsHandled, this.isHandled+"");
        rtn.add(EquipmentCheckResult.TaskNum, this.taskNum);
        rtn.add(EquipmentCheckResult.SafetyMeasure, this.safetyMeasure);
        rtn.add(EquipmentCheckResult.UnhandleReason, this.unhandleReason);
        rtn.add(EquipmentCheckResult.UserID, this.userId+"");
        rtn.add(EquipmentCheckResult.UpdatedTime, this.updatedTime);


        return rtn;
    }
}
