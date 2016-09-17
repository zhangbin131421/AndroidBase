package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 8/21/16.
 */
public class UserResult {

    @JsonProperty(value="Dept")
    public String dept;

    @JsonProperty(value="IsManager")
    public int isManager;

    @JsonProperty(value="ClassSort")
    public String classSort;

    @JsonProperty(value="ID")
    public int id;

    @JsonProperty(value="Name")
    public String name;


    @JsonProperty(value="Phone")
    public String phone;

    @JsonProperty(value="IsValid")
    public int isValid;

    @JsonProperty(value="Message")
    public String message;


    @JsonProperty(value="Role")
    public String role;

    @JsonProperty(value="CreateTime")
    public String createTime;

    @JsonProperty(value="UpdateTime")
    public String updateTime;


    @JsonProperty(value = "DeviceID")
    public String deviceID;


    @JsonProperty(value="Code")
    public int code;

}
