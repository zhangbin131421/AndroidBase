
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
public class TotalPerformanceTestResult {


    public static final String ID = "ID";
    @JsonProperty(value=ID)
    public int id;
    public static final String UserID = "UserID";
    @JsonProperty(value=UserID)
    public int userID;
    public static final String AssignByUserID = "AssignByUserID";
    @JsonProperty(value=AssignByUserID)
    public int assignByUserID;
    public static final String CreatedTime = "CreatedTime";
    @JsonProperty(value=CreatedTime)
    public String createdTime;
    public static final String AssignmentTime = "AssignmentTime";
    @JsonProperty(value=AssignmentTime)
    public String assignmentTime;
    public static final String TaskNum = "TaskNum";
    @JsonProperty(value=TaskNum)
    public String taskNum;
    public static final String AreaName = "AreaName";
    @JsonProperty(value=AreaName)
    public String areaName;
    public static final String AreaID = "AreaID";
    @JsonProperty(value=AreaID)
    public int areaID;
    public static final String ProtectLine = "ProtectLine";
    @JsonProperty(value=ProtectLine)
    public String protectLine;
    public static final String Type = "Type";
    @JsonProperty(value=Type)
    public String type;
    public static final String SafetyMeasure = "SafetyMeasure";
    @JsonProperty(value=SafetyMeasure)
    public String safetyMeasure;
    public static final String EndTime = "EndTime";
    @JsonProperty(value=EndTime)
    public String endTime;
    public static final String BeginHandleTime = "BeginHandleTime";
    @JsonProperty(value=BeginHandleTime)
    public String beginHandleTime;
    public static final String ElectricityA = "ElectricityA";
    @JsonProperty(value=ElectricityA)
    public String electricityA;
    public static final String ElectricityB = "ElectricityB";
    @JsonProperty(value=ElectricityB)
    public String electricityB;


    public static final String ElectricityBPic = "ElectricityBPic";
    @JsonProperty(value=ElectricityBPic)
    public String electricityBPic;

    public static final String ElectricityC = "ElectricityC";
    @JsonProperty(value=ElectricityC)
    public String electricityC;

    public static final String ElectricityD = "ElectricityD";
    @JsonProperty(value=ElectricityD)
    public String electricityD;


    public static final String ElectricityDPic = "ElectricityDPic";
    @JsonProperty(value=ElectricityDPic)
    public String electricityDPic;

    public static final String OperateTime = "OperateTime";
    @JsonProperty(value=OperateTime)
    public String operateTime;
    public static final String TestTime = "TestTime";
    @JsonProperty(value=TestTime)
    public String testTime;
    public static final String TestResult= "TestResult";
    @JsonProperty(value=TestResult)
    public String testResult;
    public static final String HandleContent = "HandleContent";
    @JsonProperty(value=HandleContent)
    public String handleContent;
    public static final String Tester = "Tester";
    @JsonProperty(value=Tester)
    public String tester;
    public static final String EndHandleTime = "EndHandleTime";
    @JsonProperty(value=EndHandleTime)
    public String endHandleTime;
    public static final String IsHandled = "IsHandled";
    @JsonProperty(value=IsHandled)
    public int isHandled;
    public static final String UnhandleReason = "UnhandleReason";
    @JsonProperty(value=UnhandleReason)
    public String unhandleReason;


    @JsonIgnore
    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {
        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();

        rtn.add(TotalPerformanceTestResult.ID, this.id+"");
        rtn.add(TotalPerformanceTestResult.UserID, this.userID+"");
        rtn.add(TotalPerformanceTestResult.AssignByUserID, this.assignByUserID+"");
        rtn.add(TotalPerformanceTestResult.CreatedTime, this.createdTime==null ? "" : this.createdTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.AssignmentTime, this.assignmentTime == null ? "" : this.assignmentTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.TaskNum, this.taskNum.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.AreaName, this.areaName.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.AreaID, this.areaID+"");
        rtn.add(TotalPerformanceTestResult.ProtectLine, this.protectLine.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.Type, this.type.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.SafetyMeasure, this.safetyMeasure.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.EndTime, this.endTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.BeginHandleTime, this.beginHandleTime == null ? "" : this.beginHandleTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.ElectricityA, this.electricityA.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.ElectricityB, this.electricityB.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.ElectricityC, this.electricityC.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.ElectricityD, this.electricityD.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.OperateTime, this.operateTime == null ? "" : this.operateTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.TestTime, this.testTime == null ? "" : this.testTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.TestResult, this.testResult.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.HandleContent, this.handleContent.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.Tester, this.tester.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.EndHandleTime, this.endHandleTime == null ? "" : this.endHandleTime.getBytes("UTF-8"));
        rtn.add(TotalPerformanceTestResult.IsHandled, this.isHandled+"");
        rtn.add(TotalPerformanceTestResult.UnhandleReason, this.unhandleReason.getBytes("UTF-8"));

        return rtn;
    }
}