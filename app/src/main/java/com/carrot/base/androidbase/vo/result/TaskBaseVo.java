package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by victor on 8/28/16.
 */
@Parcel
public class TaskBaseVo {


    @JsonProperty(value="ID")
    public int id;

    @JsonProperty(value="TaskNum")
    public String taskNum;

    @JsonProperty(value="UserID")
    public int userId;

    @JsonProperty(value="AssignByUserID")
    public int assignByUserID;

    @JsonProperty(value="AssignByUserName")
    public String AssignByUserName;

    @JsonProperty(value="AssignmentTime")
    public String assignmentTime;

    @JsonProperty(value="BeginHandleTime")
    public String beginHandleTime;

    @JsonProperty(value="EndHandleTime")
    public String endHandleTime;

    @JsonProperty(value="IsHandled")
    public int isHandled;

    @JsonProperty(value="UnhandleReason")
    public String unhandleReason;


    @JsonProperty(value="UserName")
    public String userName;

}
