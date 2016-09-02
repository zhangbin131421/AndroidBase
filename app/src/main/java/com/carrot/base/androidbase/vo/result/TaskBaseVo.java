package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.parceler.Parcel;

/**
 * Created by victor on 8/28/16.
 */
@Parcel
public class TaskBaseVo {

    //    [{"ID":1,
    @JsonProperty(value="ID")
    public int id;
    //            "TaskNum":"T111",
    @JsonProperty(value="TaskNum")
    public String taskNum;
    //            "UserID":1,
    @JsonProperty(value="UserID")
    public int userId;
    //            "AssignByUserID":2,
    @JsonProperty(value="AssignByUserID")
    public int assignByUserID;
    //            "AssignByUserName":2,
    @JsonProperty(value="AssignByUserName")
    public String AssignByUserName;
    //            "AssignmentTime":"\/Date(1471795200000)\/",
    @JsonProperty(value="AssignmentTime")
    public String assignmentTime;
    //            "BeginHandleTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="BeginHandleTime")
    public String beginHandleTime;
    //            "EndHandleTime":"\/Date(1471968000000)\/",
    @JsonProperty(value="EndHandleTime")
    public String endHandleTime;
    //            "IsHandled":1,
    @JsonProperty(value="IsHandled")
    public int isHandled;
    //            "UnhandleReason":null,
    @JsonProperty(value="UnhandleReason")
    public String unhandleReason;

}
