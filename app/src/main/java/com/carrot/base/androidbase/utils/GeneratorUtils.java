package com.carrot.base.androidbase.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 9/9/16.
 */
public class GeneratorUtils {


    private static GeneratorUtils instance;

    private GeneratorUtils(){

    }

    public static GeneratorUtils getInstance(){
        if(instance == null){
            instance = new GeneratorUtils();
        }

        return instance;
    }


    public final static String T_INPUT = "1";//edit text field
    public final static String T_SPINNER = "2";//spinner
    public final static String T_IMAGE = "3";//image

    public final static String T_INT = "int";
    public final static String T_STRING = "String";
    public final static String T_TIME = "Date";

    public final static String TITLE_TYPE_1 = "1";//表格的样式，有背景色
    public final static String TITLE_TYPE_0 = "0";//表格的样式，无背景色


    public List<Entity> allEntity = new ArrayList<>();

    public String[][] ENTITY_BASE = new String[][]{
            {"Id", T_INT},
            {"UserId", T_INT},
            {"AssignByUserID", T_INT},
            {"CreatedTime", T_STRING}
    };



    public void generate(){
//        allEntity.add(new Entity("TotalPerformanceTest", TypeUtils.TYPE_2_2, COLUMNS_TotalPerformanceTest));
        allEntity.add(new Entity("CrossTest", TypeUtils.TYPE_2_5, COLUMNS_CrossTest));
//        allEntity.add(new Entity("VoltageMeasurement", TypeUtils.TYPE_2_6, COLUMNS_VoltageMeasurement));
//        allEntity.add(new Entity("EarthResistanceTest", TypeUtils.TYPE_2_7, COLUMNS_EarthResistanceTest));
//        allEntity.add(new Entity("SpecialSecurityCheck", TypeUtils.TYPE_2_8, COLUMNS_SpecialSecurityCheck));
//
//        allEntity.add(new Entity("DistributionNetworkEngineering", TypeUtils.TYPE_3_1, COLUMNS_DistributionNetworkEngineering));
//
//        allEntity.add(new Entity("SpecialSecurityCheck", TypeUtils.TYPE_4_1, COLUMNS_CarManagement));



        for (Entity entity : allEntity) {
            gLayout(entity);
            gResult(entity);
            gActivity(entity);
        }
    }

    public String[][] COLUMNS_TotalPerformanceTest = new String[][]{
            //交叉跨越测量
            {"AssignmentTime", "任务派发", T_INPUT, T_STRING, TITLE_TYPE_1},
            {"TaskNum", "任务编号", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"AreaName", "台区名称", T_SPINNER, T_STRING, TITLE_TYPE_0},
            {"ProtectLine", "保护线路", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"Type", "型号", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"SafetyMeasure", "安全措施", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"EndTime", "结束时间", T_INPUT, T_STRING, TITLE_TYPE_0},

            {"BeginHandleTime", "检测情况", T_INPUT, T_STRING, TITLE_TYPE_1},
            {"ElectricityA", "分断动作电流（额定值）", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"ElectricityB", "分断动作电流（实测值）", T_IMAGE, T_STRING, TITLE_TYPE_0},
            {"ElectricityC", "分断动作时间（额定值）", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"ElectricityD", "分断动作时间（实测值）", T_IMAGE, T_STRING, TITLE_TYPE_0},
            {"OperateTime", "投运时间", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"TestTime", "测量时间", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"TestResult", "检测结果", T_SPINNER, T_STRING, TITLE_TYPE_0},
            {"HandleContent", "采取措施", T_INPUT, T_STRING, TITLE_TYPE_0},
            {"Tester", "测试人", T_INPUT, T_STRING, TITLE_TYPE_0},

            {"EndHandleTime", "任务结束", T_INPUT, T_STRING, TITLE_TYPE_1},
            {"IsHandled", "已处理", T_SPINNER, T_INT, TITLE_TYPE_0},
            {"UnhandleReason", "未处理", T_INPUT, T_STRING, TITLE_TYPE_0}
    };


    public String[][] COLUMNS_CrossTest = new String[][]{
            //交叉跨越测量
            {"AssignmentTime", "任务派发", T_INPUT, T_STRING, "1"},
            {"TaskNum", "任务编号", T_INPUT, T_STRING, "0"},
            {"AreaName", "台区名称", T_SPINNER, T_STRING, "0"},
            {"CrossPoint", "交跨点", T_INPUT, T_STRING, "0"},
            {"CrossName", "跨越物名称", T_INPUT, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_INPUT, T_STRING, "0"},

            {"BeginHandleTime", "测量情况", T_INPUT, T_STRING, "1"},
            {"EarthDistance", "对地距离（米）", T_IMAGE, T_STRING, "0"},
            {"CrossDistance", "交跨距离（米）", T_IMAGE, T_STRING, "0"},
            {"IsQualified", "是否符合要求", T_SPINNER, T_STRING, "0"},
            {"ModificationOpinion", "整改意见", T_INPUT, T_STRING, "0"},
            {"TestTime", "测量时间", T_INPUT, T_STRING, "0"},
            {"Tester", "测量人员", T_INPUT, T_STRING, "0"},

            {"EndHandleTime", "任务结束", T_INPUT, T_STRING, "1"},
            {"IsHandled", "已处理", T_SPINNER, T_INT, "0"},
            {"UnhandleReason", "未处理", T_INPUT, T_STRING, "0"}

    };

    public String[][] COLUMNS_VoltageMeasurement = new String[][]{
            {"AssignmentTime", "任务派发", T_INPUT, T_STRING, "1"},
            {"TaskNum", "任务编号", T_INPUT, T_STRING, "0"},
            {"AreaName", "台区名称", T_SPINNER, T_STRING, "0"},
            {"ConfigA", "配变容量（KVA）4", T_INPUT, T_STRING, "0"},
            {"ConfigB", "配变型号", T_SPINNER, T_STRING, "0"},
            {"ConfigC", "配变类别", T_SPINNER, T_STRING, "0"},
            {"RatedCurrent", "额定电流（A）", T_INPUT, T_STRING, "0"},
            {"PowerHouseholder", "动力户数", T_INPUT, T_STRING, "0"},
            {"PowerCapacity", "动力容量", T_INPUT, T_STRING, "0"},
            {"Householder", "居民户数", T_INPUT, T_STRING, "0"},
            {"HouseholderCapacity", "居民容量", T_INPUT, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_SPINNER, T_STRING, "0"},
            {"EndTime", "结束时间", T_INPUT, T_STRING, "0"},
            {"BeginHandleTime", "测量情况", T_INPUT, T_STRING, "1"},
            {"Period", "时段", T_SPINNER, T_STRING, "0"},
            {"CurrentA", "A相电流（A）1", T_IMAGE, T_STRING, "0"},
            {"CurrentB", "B相电流（A）2", T_IMAGE, T_STRING, "0"},
            {"CurrentC", "C相电流（A）3", T_IMAGE, T_STRING, "0"},
            {"ZeoLineCurrent", "零线电流（A）", T_IMAGE, T_STRING, "0"},
            {"LoadRate", "负载率（%）", T_INPUT, T_STRING, "0"},
            {"ImbalanceRate", "三相不平衡率（%）", T_INPUT, T_STRING, "0"},
            {"HeaderVoltage", "线路首端电压（V）", T_IMAGE, T_STRING, "0"},
            {"FooterVoltage", "线路末端电压（V）", T_IMAGE, T_STRING, "0"},
            {"IsOutOfLimit", "是否越限", T_SPINNER, T_STRING, "0"},
            {"ModificationOpinion", "整改建议", T_INPUT, T_STRING, "0"},
            {"TestTime", "测量日期", T_INPUT, T_STRING, "0"},
            {"Tester", "测量人员", T_INPUT, T_STRING, "0"},
            {"EndHandleTime", "任务结束", T_INPUT, T_STRING, "1"},
            {"IsHandled", "已处理", T_SPINNER, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_INPUT, T_STRING, "0"}
    };

    public String[][] COLUMNS_EarthResistanceTest = new String[][]{

            {"AssignmentTime", "任务派发", T_INPUT, T_STRING, "1"},
            {"TaskNum", "任务编号", T_INPUT, T_STRING, "0"},
            {"AreaName", "台区名称", T_SPINNER, T_STRING, "0"},
            {"EarthPlace", "接地装置位置", T_INPUT, T_STRING, "0"},
            {"EarthEquipmentName", "接地设备名称", T_INPUT, T_STRING, "0"},
            {"ResistanceValue", "投运时电阻值Ω", T_INPUT, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_SPINNER, T_STRING, "0"},
            {"BeginHandleTime", "测量情况", T_INPUT, T_STRING, "1"},
            {"Wether", "天气", T_SPINNER, T_STRING, "0"},
            {"TestResistanceValue", "测量电阻值Ω", T_IMAGE, T_STRING, "0"},
            {"TestDate", "测量日期", T_INPUT, T_STRING, "0"},
            {"Tester", "测量人员", T_INPUT, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_INPUT, T_STRING, "1"},
            {"IsHandled", "已处理", T_SPINNER, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_INPUT, T_STRING, "0"}
    };
    public String[][] COLUMNS_SpecialSecurityCheck = new String[][]{
            {"AssignmentTime", "任务派发", T_INPUT, T_STRING, "1"},
            {"TaskNum", "任务编号", T_INPUT, T_STRING, "0"},
            {"BeginTime", "开始时间", T_INPUT, T_STRING, "0"},
            {"EndTime", "结束时间", T_INPUT, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_SPINNER, T_STRING, "0"},
            {"BeginHandleTime", "检查情况", T_INPUT, T_STRING, "1"},
            {"ExistIssue", "存在问题", T_INPUT, T_STRING, "0"},
            {"UserID", "检查人员", T_INPUT, T_STRING, "0"},
            {"CheckDate", "检查日期", T_INPUT, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_INPUT, T_STRING, "1"},
            {"IsHandled", "已处理", T_SPINNER, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_INPUT, T_STRING, "0"}};


    public String[][] COLUMNS_DistributionNetworkEngineering = new String[][]{
            {"AssignmentTime", "工单派发", T_INPUT, T_STRING, "1"},
            {"TaskNum", "工单编号", T_INPUT, T_STRING, "0"},
            {"EngineeringName", "工程名称", T_INPUT, T_STRING, "0"},
            {"EngineeringNum", "工程编号", T_INPUT, T_STRING, "0"},
            {"AreaName", "台区名称", T_SPINNER, T_STRING, "0"},
            {"ExecutionCompany", "施工单位", T_SPINNER, T_STRING, "0"},
            {"WorkContent", "工作内容", T_INPUT, T_STRING, "0"},
            {"WorkPlace", "工作地点", T_INPUT, T_STRING, "0"},
            {"StopScope", "停电范围", T_INPUT, T_STRING, "0"},
            {"StopTime", "停电时间", T_INPUT, T_STRING, "0"},
            {"WorkLicensor", "工作许可人", T_INPUT, T_STRING, "0"},
            {"BeginHandleTime", "现场处理", T_INPUT, T_STRING, "1"},
            {"WorkInvoiceNum", "工作票号", T_INPUT, T_STRING, "0"},
            {"ExecutionResponsible", "施工队责任人", T_INPUT, T_STRING, "0"},
            {"WorkResponsible", "工作负责人", T_INPUT, T_STRING, "0"},
            {"WorkContent2", "工作内容", T_INPUT, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_INPUT, T_STRING, "0"},
            {"ActualStopTime", "实际停电时间", T_INPUT, T_STRING, "0"},
            {"EndStopTime", "送电时间", T_INPUT, T_STRING, "0"},
            {"Inspector", "督察人员", T_INPUT, T_STRING, "0"},
            {"Inspect", "督察情况", T_IMAGE, T_STRING, "0"},
            {"Complete", "完成情况", T_INPUT, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_INPUT, T_STRING, "1"},
            {"IsHandled", "已完成", T_SPINNER, T_STRING, "0"},
            {"UnhandleReason", "未完成", T_INPUT, T_STRING, "0"}};


    public String[][] COLUMNS_CarManagement = new String[][]{
            {"ApplyTime", "申请", T_INPUT, T_STRING, "1"},
            {"ApplyNum", "申请编号", T_INPUT, T_STRING, "0"},
            {"CarID", "车号", T_INPUT, T_STRING, "0"},
            {"ArrivalPlace", "到达地点", T_INPUT, T_STRING, "0"},
            {"DriveOutTime", "出车时间", T_INPUT, T_STRING, "0"},
            {"BackTime", "回来时间", T_INPUT, T_STRING, "0"},
            {"StartDistanceCode", "起始路码", T_INPUT, T_STRING, "0"},
            {"EndDistanceCode", "终止路码", T_INPUT, T_STRING, "0"},
            {"Cost", "费用", T_INPUT, T_STRING, "0"}};



    class Entity{
        public String nameEnglish;
        public String nameChinese;
        public String[][] columns;

        public Entity(String nameEnglish, String nameChinese, String[][] columns){
            this.nameChinese = nameChinese;
            this.nameEnglish = nameEnglish;
            this.columns = columns;
        }
    }






    private void gActivity(Entity entity){
        StringBuffer output = new StringBuffer();
        output.append("package com.carrot.base.androidbase.activity.handle;\n" +
                "\n" +
                "import android.widget.ImageView;\n" +
                "import android.widget.LinearLayout;\n" +
                "import android.widget.Spinner;\n" +
                "\n" +
                "import com.andreabaccega.widget.FormEditText;\n" +
                "import com.carrot.base.androidbase.R;\n" +
                "import com.carrot.base.androidbase.client."+entity.nameEnglish+"Client;\n" +
                "import com.carrot.base.androidbase.preferences.DataInstance;\n" +
                "import com.carrot.base.androidbase.utils.DateUtils;\n" +
                "import com.carrot.base.androidbase.utils.FileUtils;\n" +
                "import com.carrot.base.androidbase.utils.TypeUtils;\n" +
                "import com.carrot.base.androidbase.vo.result.AreaInformationResult;\n" +
                "import com.carrot.base.androidbase.vo.result."+entity.nameEnglish+"Result;\n" +
                "import com.carrot.base.androidbase.vo.result.UpdateResult;\n" +
                "\n" +
                "import org.androidannotations.annotations.AfterViews;\n" +
                "import org.androidannotations.annotations.Background;\n" +
                "import org.androidannotations.annotations.EActivity;\n" +
                "import org.androidannotations.annotations.OptionsMenu;\n" +
                "import org.androidannotations.annotations.ViewById;\n" +
                "import org.androidannotations.rest.spring.annotations.RestService;\n" +
                "import org.springframework.util.MultiValueMap;\n" +
                "\n" +
                "import java.io.UnsupportedEncodingException;\n" +
                "import java.util.ArrayList;\n" +
                "import java.util.List;\n" +
                "\n" +
                "import cn.finalteam.galleryfinal.model.PhotoInfo;\n" +
                "\n" +
                "/**\n" +
                " * Created by victor on 8/22/16.\n" +
                " */\n" +
                "@EActivity(R.layout.activity_"+parseToUnder(entity.nameEnglish)+")\n" +
                "@OptionsMenu(R.menu.task_item)\n" +
                "public class "+entity.nameEnglish+"Activity extends BaseHandlerActivity{\n" +
                "\n" +
                "\n" +
                "\n");
        for (String[] items: entity.columns) {
            if(items[2].equals(T_IMAGE)){
                output.append("    List<PhotoInfo> "+ getActivityPicListName(items[0]) + " = new ArrayList<>();\n");
            }
        }

        output.append(
                "\n\n" +
                        "    "+entity.nameEnglish+"Result "+parseToLowFirst(entity.nameEnglish)+"Result;\n" +
                        "\n" +
                        "    @RestService\n" +
                        "    "+entity.nameEnglish+"Client "+parseToLowFirst(entity.nameEnglish)+"Client;\n" +
                        "\n" +
                        "\n" +
                        "\n");
        for(String[] items : entity.columns){
            if(items[2].equals(T_IMAGE)){
                output.append(
                        "\n" +
                                "    @ViewById(R.id."+getLayoutImageLLName(items[0])+")\n" +
                                "    org.apmem.tools.layouts.FlowLayout "+getActivityImageLLName(items[0])+";\n" +
                                "\n" +
                                "\n" +
                                "    @ViewById(R.id."+getLayoutAddImageButtonName(items[0])+")\n" +
                                "    ImageView "+getActivityAddImageButtonName(items[0])+";\n" +
                                "\n" +
                                "    @ViewById(R.id."+getLayoutName(items[0])+")\n" +
                                "    FormEditText "+getActivityName(items[0])+";\n" +
                                "\n");
            }else if(items[2].equals(T_INPUT)){
                output.append(
                        "    @ViewById(R.id."+getLayoutName(items[0])+")\n" +
                                "    FormEditText "+getActivityName(items[0])+";\n");
            }else if(items[2].equals(T_SPINNER)){
                output.append(
                        "    @ViewById(R.id."+getSpinnerLayoutName(items[0])+")\n" +
                                "    Spinner "+getSpinnerActivityName(items[0])+";\n");
            }

        }

        output.append(
                "\n" +
                        "\n" +
                        "\n" +
                        "    @AfterViews\n" +
                        "    void bindAdapter(){\n" +
                        "        super.afterInitView(TypeUtils.TYPE_, getApplicationContext(), getResources());\n" +
                        "\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    public void setValidateList(){\n" +
                        "        allValidateFields = new FormEditText[] {};\n" +
                        "\n" +
                        "        addDisableList = new FormEditText[] {};\n" +
                        "\n" +
                        "        updateDisableList = new FormEditText[] {};\n" +
                        "\n" +
                        "        finishDisableList = new FormEditText[] {};\n" +
                        "\n" +
                        "        updateDisabledSpinnerList = new Spinner[] {};\n" +
                        "        finishDisabledSpinnerList = new Spinner[] {};\n" +"\n" +
                        "        openDateEditTextList = new OpenDateVo[] {};\n" +
                        "\n" +
                        "        showBySpinnerList = new ShowBySpinnerVo[]{};\n" +
                        "\n" +
                        "\n" +
                        "\n");

        for(String[] items : entity.columns){
            output.append("        imageAddButtonList = new ImageView[] {");
            if(items[2].equals(T_IMAGE)){
                output.append(getActivityAddImageButtonName(items[0])+",");
            }
            output.append("};\n");
        }

        output.append("        openChooseImageList = new BaseHandlerActivity.ImageChooseVo[] {");
        for(String[] items : entity.columns){
            if(items[2].equals(T_IMAGE)){
                output.append("\n                new ImageChooseVo("+getActivityAddImageButtonName(items[0])+", "+getActivityPicListName(items[0])+", "+getActivityImageLLName(items[0])+"),\n");
            }
        }
        output.append("};\n");


        output.append(
                "\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public void setErrorHandler(){\n" +
                        "        "+parseToLowFirst(entity.nameEnglish)+"Client.setRestErrorHandler(ssErrorHandler);\n" +
                        "    }\n" +
                        "\n");

        output.append("    void initDropDownList(){\n" +
                "        //下拉选择框\n");
        for(String[] items : entity.columns){
            if(items[2].equals(T_SPINNER)){output.append(
                    "        setDropDownListAdapter("+getSpinnerActivityName(items[0])+", TypeUtils.);\n");
            }

        }
        output.append(
                "    }\n" +
                        "\n" +
                        "\n");

        output.append(
                "    void getEntityFromServer(){\n" +
                        "        "+parseToLowFirst(entity.nameEnglish)+"Result = "+parseToLowFirst(entity.nameEnglish)+"Client.getById(taskBaseVo.id);\n" +
                        "    }\n" +
                        "\n" +
                        "    void refreshViewAfterGetEntity(){\n" +
                        "        if("+parseToLowFirst(entity.nameEnglish)+"Result == null){\n" +
                        "\n" +
                        "            "+parseToLowFirst(entity.nameEnglish)+"Result = new "+entity.nameEnglish+"Result();\n" +
                        "\n" +
                        "\n" +
                        "        }else{\n" +
                        "\n");
        for(String[] items : entity.columns){
            if(items[2].equals(T_INPUT) || items[2].equals(T_IMAGE)){
                output.append("            "+getActivityName(items[0])+".setText("+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(items[0])+");\n");
            }else if(items[2].equals(T_SPINNER)){
                output.append("            "+getSpinnerActivityName(items[0])+".setSelection(TypeUtils.getSelectedIndex(TypeUtils., "+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(items[0])+"));\n");
            }
        }
        output.append(
                "\n" +
                        "            getImage();\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "    /**\n" +
                        "     * update,打开页面后，获取当前数据，并获取网络图片\n" +
                        "     */\n" +
                        "    @Background\n" +
                        "    void getImage(){\n" +
                        "\n");
        for (String[] items : entity.columns){
            if(items[2].equals(T_IMAGE)){
                output.append("        super.getImageFromURL("+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(items[0])+", "+this.getActivityImageLLName(items[0])+");\n");
            }
        }

        output.append(
                "\n" +
                        "    }\n" +
                        "\n" +
                        "    UpdateResult save(){\n" +
                        "\n" +
                        "        if("+parseToLowFirst(entity.nameEnglish)+"Result.id == 0){\n" +
                        "            "+parseToLowFirst(entity.nameEnglish)+"Result.assignByUserID = userPrefs.id().get();\n" +
                        "            "+parseToLowFirst(entity.nameEnglish)+"Result.userId = userPrefs.id().get();\n" +
                        "        }\n" +
                        "\n" +
                        "        MultiValueMap<String, Object> data = null;\n" +
                        "        try {\n" +
                        "            data = "+parseToLowFirst(entity.nameEnglish)+"Result.parseToMultiValueMap();\n" +
                        "        } catch (UnsupportedEncodingException e) {\n" +
                        "            e.printStackTrace();\n" +
                        "        }\n" +
                        "\n");
        for(String[] items : entity.columns){
            if(items[2].equals(T_IMAGE)){
                output.append(
                        "        FileUtils.addImageToData(data, "+entity.nameEnglish+"Result."+items[0]+", "+this.getActivityPicListName(items[0])+", this);\n" +
                                "\n"
                );
            }
        }

        output.append(
                "        return "+parseToLowFirst(entity.nameEnglish)+"Client.update(data);\n" +
                        "    }\n" +
                        "\n" +
                        "\n" +
                        "    boolean validate(){\n" +
                        "\n" +
                        "        if(super.validate()) {\n" +
                        "\n");

        for (String[] items : entity.columns){
            if(items[2].equals(T_INPUT) || items[2].equals(T_IMAGE)){
                output.append("            this."+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(items[0])+" = "+getActivityName(items[0])+".getText().toString();\n");
            }else if(items[2].equals(T_SPINNER)){
                output.append(
                        "            this."+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(items[0])+" = "+getSpinnerActivityName(items[0])+".getSelectedItem().toString();\n" +
                                "\n");
            }

        }

        output.append(
                "\n" +
                        "\n" +
                        "            return true;\n" +
                        "        }{\n" +
                        "            return false;\n" +
                        "        }\n" +
                        "    }\n" +
                        "\n" +
                        "}\n");

        print(output.toString());
        try {
            FileOutputStream fos = new FileOutputStream(new File("/Users/victor/Desktop/workspace_android/AndroidBase/app/src/main/java/com/carrot/base/androidbase/activity/handle/"+entity.nameEnglish+"Activity.java"));
            fos.write(output.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void gLayout(Entity entity){
        String output =
                "\n" +
                        "\n" +
                        "\n" +
                        "<!-- *********************"+entity.nameEnglish+" Layout ********************** -->"+
                        "\n" +
                        "\n" +
                        "\n" +
                        "\n";

        output = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    xmlns:whatever=\"http://schemas.android.com/apk/res-auto\"\n" +
                "    android:orientation=\"vertical\"\n" +
                "    android:layout_width=\"match_parent\"\n" +
                "    android:layout_height=\"match_parent\">\n" +
                "    <!-- "+entity.nameChinese+" -->\n" +
                "    <android.support.design.widget.AppBarLayout\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"wrap_content\"\n" +
                "        android:theme=\"@style/ThemeOverlay.AppCompat.Dark.ActionBar\">\n" +
                "        <android.support.v7.widget.Toolbar\n" +
                "            android:id=\"@+id/tb_tool_bar\"\n" +
                "            android:layout_width=\"match_parent\"\n" +
                "            android:layout_height=\"wrap_content\">\n" +
                "        </android.support.v7.widget.Toolbar>\n" +
                "    </android.support.design.widget.AppBarLayout>\n" +
                "\n" +
                "\n" +
                "    <ScrollView xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "        android:layout_width=\"match_parent\"\n" +
                "        android:layout_height=\"match_parent\"\n" +
                "        android:fillViewport=\"true\">\n" +
                "        <LinearLayout\n" +
                "            android:orientation=\"vertical\"\n" +
                "            android:layout_width=\"match_parent\"\n" +
                "            android:layout_height=\"wrap_content\">\n" +
                "\n";
        for (String[] item : entity.columns) {

            String columnStyle = item[4].equals("0") ? "tableTitle2" : "tableTitle";

            if(item[2].equals(T_INPUT)){
                output += "            <LinearLayout style=\"@style/"+columnStyle+"\">\n" +
                        "                <TextView style=\"@style/tableColName\"\n" +
                        "                    android:text=\""+item[1]+"\"/>\n" +
                        "                <TextView style=\"@style/formDivider\"/>\n"+
                        "                <com.andreabaccega.widget.FormEditText\n" +
                        "                    style=\"@style/tableColInput\"\n" +
                        "                    whatever:emptyErrorString=\"@string/notEmpty\"\n" +
                        "                    android:id=\"@+id/"+this.getLayoutName(item[0])+"\"/>\n" +
                        "            </LinearLayout>\n" +
                        "\n";
            }else
            if(item[2].equals(T_SPINNER)){
                output +=
                        "            <LinearLayout style=\"@style/tableTitle2\">\n" +
                                "                <TextView style=\"@style/tableColName\"\n" +
                                "                    android:text=\""+item[1]+"\"/>\n" +
                                "                <TextView style=\"@style/formDivider\"/>\n"+
                                "                <Spinner\n" +
                                "                    android:id=\"@+id/"+this.getSpinnerLayoutName(item[0])+"\"\n" +
                                "                    style=\"@style/spinner_form_right\"" +
                                "            </LinearLayout>\n";
            }else if(item[2].equals(T_IMAGE)){
                output += "<LinearLayout style=\"@style/tableTitle2\">\n" +
                        "                    <TextView style=\"@style/tableColName\"\n" +
                        "                        android:text=\""+item[1]+"\"/>\n" +
                        "                    <TextView style=\"@style/formDivider\"/>\n" +
                        "                    <LinearLayout\n" +
                        "                        android:layout_width=\"match_parent\"\n" +
                        "                        android:layout_height=\"wrap_content\"\n" +
                        "                        android:orientation=\"vertical\">\n" +
                        "                        <com.andreabaccega.widget.FormEditText\n" +
                        "                            whatever:emptyErrorString=\"@string/notEmpty\" style=\"@style/tableColInputPic\"\n" +
                        "                            android:id=\"@+id/"+this.getLayoutName(item[0])+"\"/>\n" +
                        "                        <org.apmem.tools.layouts.FlowLayout\n" +
                        "                            android:id=\"@+id/"+this.getLayoutImageLLName(item[0])+"\"\n" +
                        "                            style=\"@style/tableColImageLayout\"\n" +
                        "                            android:gravity=\"center_vertical\">\n" +
                        "                            <ImageView\n" +
                        "                                android:id=\"@+id/"+this.getLayoutAddImageButtonName(item[0])+"\"\n" +
                        "                                style=\"@style/tableColImagAdd\"/>\n" +
                        "                        </org.apmem.tools.layouts.FlowLayout>\n" +
                        "                    </LinearLayout>\n" +
                        "\n" +
                        "                </LinearLayout>";
            }
        }

        output +=
                "\n" +
                        "        </LinearLayout>\n" +
                        "    </ScrollView>\n" +
                        "</LinearLayout>";


//        Log.i("sslog", output);
        print(output);
        try {
            String filename = "/Users/victor/Desktop/workspace_android/AndroidBase/app/src/main/res/layout/activity_"+this.parseToUnder(entity.nameEnglish)+".xml";
            FileOutputStream fos = new FileOutputStream(new File(filename));
            System.out.println(filename);
            fos.write(output.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gResult(Entity entity){
        String output =
                "\n" +
                        "\n" +
                        "\n" +
                        "// *********************"+entity.nameEnglish+" Result ********************** \n"+
                        "// *********************"+entity.nameChinese+"        ********************** \n" +
                        "\n" +
                        "\n" +
                        "\n";;

        output += "package com.carrot.base.androidbase.vo.result;\n" +
                "\n" +
                "\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnore;\n" +
                "import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n" +
                "import com.fasterxml.jackson.annotation.JsonProperty;\n" +
                "import com.fasterxml.jackson.databind.annotation.JsonSerialize;\n" +
                "\n" +
                "import org.parceler.Parcel;\n" +
                "import org.springframework.util.LinkedMultiValueMap;\n" +
                "import org.springframework.util.MultiValueMap;\n" +
                "\n" +
                "import java.io.UnsupportedEncodingException;\n" +
                "\n" +
                "/**\n" +
                " * Created by victor on 9/1/16.\n" +
                " */\n" +
                "@Parcel\n" +
                "@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)\n" +
                "@JsonIgnoreProperties(ignoreUnknown = true)\n" +
                "public class "+entity.nameEnglish+"Result {\n" +
                "\n" +
                "\n";

        for (String[] item : ENTITY_BASE) {
            output +=
                    "    public static final String "+item[0]+" = \""+item[0]+"\";\n" +
                            "    @JsonProperty(value="+item[0]+")\n" +
                            "    public "+item[1]+" "+parseToLowFirst(item[0])+";\n\n";

        }

        for (String[] item : entity.columns) {
            output +=
                    "    public static final String "+item[0]+" = \""+item[0]+"\";\n" +
                            "    @JsonProperty(value="+item[0]+")\n" +
                            "    public "+item[3]+" "+parseToLowFirst(item[0])+";\n\n";

        }

        output +=
                "\n" +
                        "\n";

        output +=
                "    @JsonIgnore\n" +
                        "    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {\n" +
                        "        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();\n" +
                        "\n";

        for(String[] item : ENTITY_BASE){
            if(item[1].equals(T_INT)){
                output +=
                        "        rtn.add("+entity.nameEnglish+"Result."+item[0]+", this."+parseToLowFirst(item[0])+"+\"\");\n";
            }else if(item[1].equals(T_STRING)){
                output +=
                        "        rtn.add("+entity.nameEnglish+"Result."+item[0]+", this."+parseToLowFirst(item[0])+".getBytes(\"UTF-8\"));\n";

            }else if(item[1].equals(T_TIME)){

            }
        }
        for (String[] item : entity.columns){
            if(item[3].equals(T_INT)){
                output +=
                        "        rtn.add("+entity.nameEnglish+"Result."+item[0]+", this."+parseToLowFirst(item[0])+"+\"\");\n";
            }else if(item[3].equals(T_STRING)){
                output +=
                        "        rtn.add("+entity.nameEnglish+"Result."+item[0]+", this."+parseToLowFirst(item[0])+".getBytes(\"UTF-8\"));\n";

            }else if(item[3].equals(T_TIME)){

            }

        }

        output +=
                "\n" +
                        "        return rtn;\n" +
                        "    }\n" +
                        "}\n";


        print(output.toString());
        try {
            String filename = "/Users/victor/Desktop/workspace_android/AndroidBase/app/src/main/java/com/carrot/base/androidbase/vo/result/"+entity.nameEnglish+"Result.java";
            FileOutputStream fos = new FileOutputStream(new File(filename));
            System.out.println(filename);
            fos.write(output.toString().getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回首字母小写的单词
     * @param item
     * @return
     */
    public String parseToLowFirst(String item){
        return item.substring(0,1).toLowerCase()+item.substring(1);
    }

    public String getLayoutName(String item){
        return "et_" + parseToUnder(item);
    }

    public String getActivityName(String item){
        return "et" + item;
    }

    public String getLayoutImageLLName(String item){
        return "ll_" + parseToUnder(item);
    }

    public String getActivityImageLLName(String item){
        return "ll" + item;
    }

    public String getLayoutAddImageButtonName(String item){
        return "btn_add_image_" + parseToUnder(item);
    }
    public String getActivityAddImageButtonName(String item){
        return "btnAddImage" + item;
    }

    public String getSpinnerLayoutName(String item){
        return "spn_" + parseToUnder(item);
    }

    public String getSpinnerActivityName(String item){
        return "spn" + item;
    }

    public String getActivityPicListName(String item){
        return parseToLowFirst(item) + "PicList";
    }

    public String parseToUnder(String item){
        StringBuffer sb = new StringBuffer("");
        int i = 0;
        for(i = 0; i < item.length() ;i++){
            char chr = item.charAt(i);

            if(i == 0){
                sb.append(String.valueOf(chr).toLowerCase());
            }else{
                if(chr >= 'A' && chr <= 'Z'){
                    break;
                }
                sb.append(String.valueOf(chr).toLowerCase());
            }
        }
        if(i < item.length()){
            return sb.append("_").append(parseToUnder(item.substring(i))).toString();
        }else{
            return sb.toString();
        }
    }

    void print(String veryLongString){
        int maxLogSize = 1000;
        for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            System.out.println(veryLongString.substring(start, end));
        }
    }
}