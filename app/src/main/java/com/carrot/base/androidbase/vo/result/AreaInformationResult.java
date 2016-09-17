package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;

/**
 * Created by victor on 9/17/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AreaInformationResult {

    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int id;

    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;

    public static final String LineName = "LineName";
    @JsonProperty(value=LineName)
    public String lineName;

    public static final String CapacityKVA = "CapacityKVA";
    @JsonProperty(value=CapacityKVA)
    public String capacityKVA;

    public static final String UserNum = "UserNum";
    @JsonProperty(value=UserNum)
    public String userNum;

    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;

    @JsonIgnore
    public String toString(){
        return areaName;
    }

}
