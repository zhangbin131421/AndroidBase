package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by victor on 8/21/16.
 */
public class LoginResult {
//    {"ID":1,"IsSuccess":true}
    @JsonProperty(value="UserID")
    private int id;

    @JsonProperty(value="IsSuccess")
    private boolean isSuccess;

    @JsonIgnore
    public boolean isSuccess() {
        return isSuccess;
    }

    @JsonIgnore
    public void setSuccess(boolean success) {
        isSuccess = success;
    }
    @JsonIgnore
    public int getId() {
        return id;
    }
    @JsonIgnore
    public void setId(int id) {
        this.id = id;
    }


}
