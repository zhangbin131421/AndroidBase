package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;

/**
 * Created by victor on 10/8/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateResult {

    public static final int CODE_OK = 1;

    public static final String Message_OK = "OK";

    public static final String Message = "Message";
    @JsonProperty(value=Message)
    public String message;

    public static final String Code = "Code";
    @JsonProperty(value=Code)
    public int code;

}
