


// *********************CarManagement Result ********************** 
// *********************车辆管理        ********************** 



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
 * Created by victor on 9/1/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarManagementResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int iD;

    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;

    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;

    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;

    public static final String ApplyTime = "ApplyTime";
    @JsonProperty(value=ApplyTime)
    public String applyTime;

    public static final String ApplyNum = "ApplyNum";
    @JsonProperty(value=ApplyNum)
    public String applyNum;

    public static final String CarID = "CarID";
    @JsonProperty(value=CarID)
    public String carID;

    public static final String ArrivalPlace = "ArrivalPlace";
    @JsonProperty(value=ArrivalPlace)
    public String arrivalPlace;

    public static final String DriveOutTime = "DriveOutTime";
    @JsonProperty(value=DriveOutTime)
    public String driveOutTime;

    public static final String BackTime = "BackTime";
    @JsonProperty(value=BackTime)
    public String backTime;

    public static final String StartDistanceCode = "StartDistanceCode";
    @JsonProperty(value=StartDistanceCode)
    public String startDistanceCode;

    public static final String EndDistanceCode = "EndDistanceCode";
    @JsonProperty(value=EndDistanceCode)
    public String endDistanceCode;

    public static final String Cost = "Cost";
    @JsonProperty(value=Cost)
    public String cost;

    public static final String ApplyStatus = "ApplyStatus";
    @JsonProperty(value=ApplyStatus)
    public String applyStatus;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(CarManagementResult.ID, this.iD+"");
        rtn.add(CarManagementResult.UserID, this.userID+"");
        rtn.add(CarManagementResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(CarManagementResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(CarManagementResult.ApplyTime, this.applyTime == null ? "" : this.applyTime.getBytes("UTF-8"));
        rtn.add(CarManagementResult.ApplyNum, this.applyNum == null ? "" : this.applyNum.getBytes("UTF-8"));
        rtn.add(CarManagementResult.CarID, this.carID == null ? "" : this.carID.getBytes("UTF-8"));
        rtn.add(CarManagementResult.ArrivalPlace, this.arrivalPlace == null ? "" : this.arrivalPlace.getBytes("UTF-8"));
        rtn.add(CarManagementResult.DriveOutTime, this.driveOutTime == null ? "" : this.driveOutTime.getBytes("UTF-8"));
        rtn.add(CarManagementResult.BackTime, this.backTime == null ? "" : this.backTime.getBytes("UTF-8"));
        rtn.add(CarManagementResult.StartDistanceCode, this.startDistanceCode == null ? "" : this.startDistanceCode.getBytes("UTF-8"));
        rtn.add(CarManagementResult.EndDistanceCode, this.endDistanceCode == null ? "" : this.endDistanceCode.getBytes("UTF-8"));
        rtn.add(CarManagementResult.Cost, this.cost == null ? "" : this.cost.getBytes("UTF-8"));
        rtn.add(CarManagementResult.ApplyStatus, this.applyStatus == null ? "" : this.applyStatus.getBytes("UTF-8"));

        return rtn;
    }
}
