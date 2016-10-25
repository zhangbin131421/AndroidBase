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
public class CarResult {

    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int id;

    public static final String Status = "Status";
    @JsonProperty(value=Status)
    public String status;

    public static final String Usetime = "Usetime";
    @JsonProperty(value=Usetime)
    public String usetime;

    public static final String Buytime = "Buytime";
    @JsonProperty(value=Buytime)
    public String buytime;

    public static final String Floodlight = "Floodlight";
    @JsonProperty(value=Floodlight)
    public String floodlight;

    public static final String GPS = "GPS";
    @JsonProperty(value=GPS)
    public String gps;

    public static final String AssetNo = "AssetNo";
    @JsonProperty(value=AssetNo)
    public String assetNo;

    public static final String EngineNo = "EngineNo";
    @JsonProperty(value=EngineNo)
    public String engineNo;

    public static final String Brand = "Brand";
    @JsonProperty(value=Brand)
    public String brand;

    public static final String Type = "Type";
    @JsonProperty(value=Type)
    public String type;

    public static final String License = "License";
    @JsonProperty(value=License)
    public String license;

    public static final String OrganizationUse = "OrganizationUse";
    @JsonProperty(value=OrganizationUse)
    public String organizationUse;

    public static final String Organization1 = "Organization1";
    @JsonProperty(value=Organization1)
    public String organization1;

    public static final String Organization2 = "Organization2";
    @JsonProperty(value=Organization2)
    public String organization2;

    public static final String OrganizationID = "OrganizationID";
    @JsonProperty(value=OrganizationID)
    public String organizationID;


    @JsonIgnore
    public String toString(){
        return license;
    }

}
