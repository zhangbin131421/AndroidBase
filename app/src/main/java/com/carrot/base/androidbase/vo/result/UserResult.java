package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 8/21/16.
 */
public class UserResult {
//    {"ID":2,"Name":"Leo si","Level":"1","Phone":"15001768927","IsValid":1,"Message":"OK","Code":1}

    @JsonProperty(value="ID")
    private int id;

    @JsonProperty(value="Name")
    private String name;

    @JsonProperty(value="Level")
    private String level;

    @JsonProperty(value="Phone")
    private String phone;

    @JsonProperty(value="IsValid")
    private int isValid;

    @JsonProperty(value="Message")
    private String message;


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
    public String getLevel() {
        return level;
    }

    @JsonIgnore
    public void setLevel(String level) {
        this.level = level;
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

}
