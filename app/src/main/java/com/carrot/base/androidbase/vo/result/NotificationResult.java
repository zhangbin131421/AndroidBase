


// *********************Notification Result ********************** 
// *********************通知报表        ********************** 



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
public class NotificationResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int iD;

    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;

    public static final String UserName = "UserName";
    @JsonProperty(value=UserName)
    public String userName;

    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;

    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;


    public static final String NotificationTitle = "NotificationTitle";
    @JsonProperty(value=NotificationTitle)
    public String notificationTitle;

    public static final String NotificationContent = "NotificationContent";
    @JsonProperty(value=NotificationContent)
    public String notificationContent;

    public static final String NotificationPic = "NotificationPic";
    @JsonProperty(value=NotificationPic)
    public String notificationPic;

    public static final String NotificationPicPic = "NotificationPicPic";
    @JsonProperty(value=NotificationPicPic)
    public String notificationPicPic;



    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(NotificationResult.ID, this.iD+"");
        rtn.add(NotificationResult.UserID, this.userID+"");
        rtn.add(NotificationResult.UserName, this.userName == null ? "" : this.userName.getBytes("UTF-8"));
        rtn.add(NotificationResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(NotificationResult.CreatedTime, this.createdTime == null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(NotificationResult.NotificationTitle, this.notificationTitle == null ? "" : this.notificationTitle.getBytes("UTF-8"));
        rtn.add(NotificationResult.NotificationContent, this.notificationContent == null ? "" : this.notificationContent.getBytes("UTF-8"));
        rtn.add(NotificationResult.NotificationPic, this.notificationPic == null ? "" : this.notificationPic.getBytes("UTF-8"));

        return rtn;
    }
}
