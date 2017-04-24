package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Andrew on 2017/4/24.
 */

public class VersionUpgradeResult {

//    {
//        "ID": 1,
//            "VersionNum": "1.1",
//            "FilePath": "~/Download/etms_v1.1.apk",
//            "CreatedTime": "2017-04-24 11:48:45",
//            "Comments": null
//    }

    @JsonProperty(value = "ID")
    private String ID;
    @JsonProperty(value = "VersionNum")
    private String VersionNum;
    @JsonProperty(value = "FilePath")
    private String FilePath;
    @JsonProperty(value = "CreatedTime")
    private String CreatedTime;
    @JsonProperty(value = "Comments")
    private String Comments;

    public String getVersionNum() {
        return VersionNum;
    }

    public void setVersionNum(String versionNum) {
        VersionNum = versionNum;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getComments() {
        return Comments;
    }

    public void setComments(String comments) {
        Comments = comments;
    }
}
