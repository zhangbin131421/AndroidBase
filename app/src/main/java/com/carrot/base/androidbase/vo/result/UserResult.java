package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 8/21/16.
 */
public class UserResult {
//   {"ID":2,"Name":"Leo si","Phone":"15001768927","Role":"2","IsValid":1,"CreateTime":"\/Date(1471753316033)\/","UpdateTime":"\/Date(1471753316033)\/","Message":"OK","Code":1}

    @JsonProperty(value="ID")
    private int id;

    @JsonProperty(value="Name")
    private String name;


    @JsonProperty(value="Phone")
    private String phone;

    @JsonProperty(value="IsValid")
    private int isValid;

    @JsonProperty(value="Message")
    private String message;


    @JsonProperty(value="Role")
    private String role;

    @JsonProperty(value="CreateTime")
    private String createTime;

    @JsonProperty(value="UpdateTime")
    private String updateTime;


    @JsonProperty(value = "DeviceID")
    public String deviceID;


    @JsonProperty(value="Code")
    private int code;

    @JsonIgnore
    public int getCode() {
        return code;
    }

    @JsonIgnore
    public void setCode(int code) {
        this.code = code;
    }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonIgnore
    public void setName(String name) {
        this.name = name;
    }


    @JsonIgnore
    public String getPhone() {
        return phone;
    }

    @JsonIgnore
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public int getIsValid() {
        return isValid;
    }

    @JsonIgnore
    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    @JsonIgnore
    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnore
    public String getRole() {
        return role;
    }
    @JsonIgnore
    public void setRole(String role) {
        this.role = role;
    }
    @JsonIgnore
    public String getCreateTime() {
        return createTime;
    }
    @JsonIgnore
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    @JsonIgnore
    public String getUpdateTime() {
        return updateTime;
    }
    @JsonIgnore
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
