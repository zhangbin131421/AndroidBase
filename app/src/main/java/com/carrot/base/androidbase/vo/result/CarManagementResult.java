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
 * Created by victor on 8/28/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarManagementResult {

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



    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;


    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;

//----------


    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(CoreMeterTestResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(CoreMeterTestResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime);
        rtn.add(CoreMeterTestResult.CreatedTime, this.createdTime == null ? "" : this.createdTime);
        rtn.add(CoreMeterTestResult.EndTime, this.endTime);
        rtn.add(CoreMeterTestResult.ID, this.id+"");
        rtn.add(CoreMeterTestResult.IsHandled, this.isHandled+"");
        rtn.add(CoreMeterTestResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(CoreMeterTestResult.UserID, this.userId+"");


        return rtn;
    }

}
