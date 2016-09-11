package com.carrot.base.androidbase.utils;

import android.util.Log;

import org.springframework.util.ObjectUtils;

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


    public final static String T_I = "1";
    public final static String T_S = "2";
    public final static String T_P = "3";

    public final static String T_INT = "int";
    public final static String T_STRING = "String";
    public final static String T_TIME = "Date";



    public List<Entity> allEntity = new ArrayList<>();


    public String[][] COLUMNS_CrossTest = new String[][]{
            //交叉跨越测量
            {"AssignmentTime", "任务派发", T_I, T_STRING, "1"},
            {"TaskNum", "任务编号", T_I, T_STRING, "0"},
            {"AreaName", "台区名称", T_S, T_STRING, "0"},
            {"CrossPoint", "交跨点", T_I, T_STRING, "0"},
            {"CrossName", "跨越物名称", T_I, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_S, T_STRING, "0"},
            {"BeginHandleTime", "测量情况", T_I, T_STRING, "1"},
            {"EarthDistance", "对地距离（米）", T_P, T_STRING, "0"},
            {"CrossDistance", "交跨距离（米）", T_P, T_STRING, "0"},
            {"IsQualified", "是否符合要求", T_S, T_STRING, "0"},
            {"ModificationOpinion", "整改意见", T_I, T_STRING, "0"},
            {"TestTime", "测量时间", T_I, T_STRING, "0"},
            {"Tester", "测量人员", T_I, T_STRING, "0"},
            {"EndHandleTime", "任务结束", T_I, T_STRING, "1"},
            {"IsHandled", "已处理", T_S, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_I, T_STRING, "0"}

    };

    public String[][] COLUMNS_VoltageMeasurement = new String[][]{
            {"AssignmentTime", "任务派发", T_I, T_STRING, "1"},
            {"TaskNum", "任务编号", T_I, T_STRING, "0"},
            {"AreaName", "台区名称", T_S, T_STRING, "0"},
            {"ConfigA", "配变容量（KVA）4", T_I, T_STRING, "0"},
            {"ConfigB", "配变型号", T_S, T_STRING, "0"},
            {"ConfigC", "配变类别", T_S, T_STRING, "0"},
            {"RatedCurrent", "额定电流（A）", T_I, T_STRING, "0"},
            {"PowerHouseholder", "动力户数", T_I, T_STRING, "0"},
            {"PowerCapacity", "动力容量", T_I, T_STRING, "0"},
            {"Householder", "居民户数", T_I, T_STRING, "0"},
            {"HouseholderCapacity", "居民容量", T_I, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_S, T_STRING, "0"},
            {"EndTime", "结束时间", T_I, T_STRING, "0"},
            {"BeginHandleTime", "测量情况", T_I, T_STRING, "1"},
            {"Period", "时段", T_S, T_STRING, "0"},
            {"CurrentA", "A相电流（A）1", T_P, T_STRING, "0"},
            {"CurrentB", "B相电流（A）2", T_P, T_STRING, "0"},
            {"CurrentC", "C相电流（A）3", T_P, T_STRING, "0"},
            {"ZeoLineCurrent", "零线电流（A）", T_P, T_STRING, "0"},
            {"LoadRate", "负载率（%）", T_I, T_STRING, "0"},
            {"ImbalanceRate", "三相不平衡率（%）", T_I, T_STRING, "0"},
            {"HeaderVoltage", "线路首端电压（V）", T_P, T_STRING, "0"},
            {"FooterVoltage", "线路末端电压（V）", T_P, T_STRING, "0"},
            {"IsOutOfLimit", "是否越限", T_S, T_STRING, "0"},
            {"ModificationOpinion", "整改建议", T_I, T_STRING, "0"},
            {"TestTime", "测量日期", T_I, T_STRING, "0"},
            {"Tester", "测量人员", T_I, T_STRING, "0"},
            {"EndHandleTime", "任务结束", T_I, T_STRING, "1"},
            {"IsHandled", "已处理", T_S, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_I, T_STRING, "0"}
    };

    public String[][] COLUMNS_EarthResistanceTest = new String[][]{

            {"AssignmentTime", "任务派发", T_I, T_STRING, "1"},
            {"TaskNum", "任务编号", T_I, T_STRING, "0"},
            {"AreaName", "台区名称", T_S, T_STRING, "0"},
            {"EarthPlace", "接地装置位置", T_I, T_STRING, "0"},
            {"EarthEquipmentName", "接地设备名称", T_I, T_STRING, "0"},
            {"ResistanceValue", "投运时电阻值Ω", T_I, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_S, T_STRING, "0"},
            {"BeginHandleTime", "测量情况", T_I, T_STRING, "1"},
            {"Wether", "天气", T_S, T_STRING, "0"},
            {"TestResistanceValue", "测量电阻值Ω", T_P, T_STRING, "0"},
            {"TestDate", "测量日期", T_I, T_STRING, "0"},
            {"Tester", "测量人员", T_I, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_I, T_STRING, "1"},
            {"IsHandled", "已处理", T_S, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_I, T_STRING, "0"}
    };
    public String[][] COLUMNS_SpecialSecurityCheck = new String[][]{
            {"AssignmentTime", "任务派发", T_I, T_STRING, "1"},
            {"TaskNum", "任务编号", T_I, T_STRING, "0"},
            {"BeginTime", "开始时间", T_I, T_STRING, "0"},
            {"EndTime", "结束时间", T_I, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_S, T_STRING, "0"},
            {"BeginHandleTime", "检查情况", T_I, T_STRING, "1"},
            {"ExistIssue", "存在问题", T_I, T_STRING, "0"},
            {"UserID", "检查人员", T_I, T_STRING, "0"},
            {"CheckDate", "检查日期", T_I, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_I, T_STRING, "1"},
            {"IsHandled", "已处理", T_S, T_STRING, "0"},
            {"UnhandleReason", "未处理", T_I, T_STRING, "0"}};


    public String[][] COLUMNS_DistributionNetworkEngineering = new String[][]{
            {"AssignmentTime", "工单派发", T_I, T_STRING, "1"},
            {"TaskNum", "工单编号", T_I, T_STRING, "0"},
            {"EngineeringName", "工程名称", T_I, T_STRING, "0"},
            {"EngineeringNum", "工程编号", T_I, T_STRING, "0"},
            {"AreaName", "台区名称", T_S, T_STRING, "0"},
            {"ExecutionCompany", "施工单位", T_S, T_STRING, "0"},
            {"WorkContent", "工作内容", T_I, T_STRING, "0"},
            {"WorkPlace", "工作地点", T_I, T_STRING, "0"},
            {"StopScope", "停电范围", T_I, T_STRING, "0"},
            {"StopTime", "停电时间", T_I, T_STRING, "0"},
            {"WorkLicensor", "工作许可人", T_I, T_STRING, "0"},
            {"BeginHandleTime", "现场处理", T_I, T_STRING, "1"},
            {"WorkInvoiceNum", "工作票号", T_I, T_STRING, "0"},
            {"ExecutionResponsible", "施工队责任人", T_I, T_STRING, "0"},
            {"WorkResponsible", "工作负责人", T_I, T_STRING, "0"},
            {"WorkContent2", "工作内容", T_I, T_STRING, "0"},
            {"SafetyMeasure", "安全措施", T_I, T_STRING, "0"},
            {"ActualStopTime", "实际停电时间", T_I, T_STRING, "0"},
            {"EndStopTime", "送电时间", T_I, T_STRING, "0"},
            {"Inspector", "督察人员", T_I, T_STRING, "0"},
            {"Inspect", "督察情况", T_P, T_STRING, "0"},
            {"Complete", "完成情况", T_I, T_STRING, "0"},
            {"EndHandleTime", "处理结果", T_I, T_STRING, "1"},
            {"IsHandled", "已完成", T_S, T_STRING, "0"},
            {"UnhandleReason", "未完成", T_I, T_STRING, "0"}};


    public String[][] COLUMNS_CarManagement = new String[][]{
            {"ApplyTime", "申请", T_I, T_STRING, "1"},
            {"ApplyNum", "申请编号", T_I, T_STRING, "0"},
            {"CarID", "车号", T_I, T_STRING, "0"},
            {"ArrivalPlace", "到达地点", T_I, T_STRING, "0"},
            {"DriveOutTime", "出车时间", T_I, T_STRING, "0"},
            {"BackTime", "回来时间", T_I, T_STRING, "0"},
            {"StartDistanceCode", "起始路码", T_I, T_STRING, "0"},
            {"EndDistanceCode", "终止路码", T_I, T_STRING, "0"},
            {"Cost", "费用", T_I, T_STRING, "0"}};

    public void generate(){
        allEntity.add(new Entity("CrossTest", TypeUtils.TYPE_2_5, COLUMNS_CrossTest));
//        allEntity.add(new Entity("VoltageMeasurement", TypeUtils.TYPE_2_5, COLUMNS_VoltageMeasurement));
//        allEntity.add(new Entity("EarthResistanceTest", TypeUtils.TYPE_2_6, COLUMNS_EarthResistanceTest));
//        allEntity.add(new Entity("SpecialSecurityCheck", TypeUtils.TYPE_2_7, COLUMNS_SpecialSecurityCheck));
//
//        allEntity.add(new Entity("DistributionNetworkEngineering", TypeUtils.TYPE_3_1, COLUMNS_DistributionNetworkEngineering));
//
//        allEntity.add(new Entity("SpecialSecurityCheck", TypeUtils.TYPE_4_1, COLUMNS_CarManagement));



        for (Entity entity : allEntity) {
//            gLayout(entity);
//            gResult(entity);
            gActivity(entity);
        }
    }


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
        output.append("\n" +
                "\n" +
                "\n" +
                "<!-- *********************"+entity.nameEnglish+" Activity ********************** -->"+
                "\n" +
                "\n" +
                "\n" +
                "\n");

        output.append("package com.carrot.base.androidbase.activity.handle;\n" +
                "\n" +
                "import android.widget.Spinner;\n" +
                "\n" +
                "import com.andreabaccega.widget.FormEditText;\n" +
                "import com.carrot.base.androidbase.R;\n" +
                "import com.carrot.base.androidbase.client."+entity.nameEnglish+"Client;\n" +
                "import com.carrot.base.androidbase.utils.DateUtils;\n" +
                "import com.carrot.base.androidbase.utils.FileUtils;\n" +
                "import com.carrot.base.androidbase.utils.TypeUtils;\n" +
                "import com.carrot.base.androidbase.vo.result."+entity.nameEnglish+"Result;\n" +
                "\n" +
                "import org.androidannotations.annotations.AfterViews;\n" +
                "import org.androidannotations.annotations.Background;\n" +
                "import org.androidannotations.annotations.Click;\n" +
                "import org.androidannotations.annotations.EActivity;\n" +
                "import org.androidannotations.annotations.OptionsMenu;\n" +
                "import org.androidannotations.annotations.UiThread;\n" +
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
                "@EActivity(R.layout.activity_)\n" +
                "@OptionsMenu(R.menu.task_item)\n" +
                "public class "+entity.nameEnglish+"Activity extends BaseHandlerActivity{\n" +
                "\n" +
                "\n" +
                "\n");

        for (String[] item : entity.columns) {
            if(item[2].equals(T_P)){
                output.append(
                "    List<PhotoInfo> "+parseToLowFirst(item[0])+"List = new ArrayList<>();\n" +
                "\n");
            }
        }

        output.append(
                "\n" +
                "    "+entity.nameEnglish+"Result "+parseToLowFirst(entity.nameEnglish)+"Result;\n" +
                "\n" +
                "    @RestService\n" +
                "    "+entity.nameEnglish+"Client "+parseToLowFirst(entity.nameEnglish)+"Client;\n" +
                "\n" +
                "\n");

        for (String[] item : entity.columns) {
            if(item[2].equals(T_I)){
                output.append(
                "    @ViewById(R.id.et"+item[0]+")\n" +
                "    FormEditText et"+item[0]+";\n" +
                "\n");
            }else if(item[2].equals(T_S)){
                output.append(
                "    @ViewById(R.id.et"+item[0]+")\n" +
                    "    Spinner et"+item[0]+";\n" +
                    "\n");
            }else if(item[2].equals(T_P)){//et"+item[0]+"_content
                output.append(
                    "    @ViewById(R.id.et"+item[0]+"_content)\n" +
                    "    org.apmem.tools.layouts.FlowLayout et"+item[0]+"Content;\n" +
                    "\n");
            }
        }

        output.append(
                "\n" +
                "    @AfterViews\n" +
                "    void bindAdapter(){\n" +
                "\n" +
                "        super.afterInitView(\""+entity.nameChinese+"\", getApplicationContext(), getResources());\n" +
                "\n" +
                "        allFields = new FormEditText[] {");

        String validateStr = "";
                for(String[] item : entity.columns){
                    if(item[2].equals(T_I)){
                        validateStr += "et"+item[0] + ",";
                    }
                }
        validateStr = validateStr.substring(0, validateStr.length()-2);

        output.append(validateStr +
                        "};\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    void initDropDownList(){\n" +
                "        //下拉选择框\n");

        for(String[] item : entity.columns){
            if(item[2].equals(T_S)){
                output.append(
                "        setDropDownListAdapter(et"+item[0]+", TypeUtils.TYPE_TEST);\n" +
                "\n");
            }
        }

        output.append(
                "    }\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    @Background\n" +
                "    void getObject(){\n" +
                "        showLoading();\n" +
                "\n" +
                "        if(taskBaseVo == null){\n" +
                "\n" +
                "        }else{\n" +
                "            "+parseToLowFirst(entity.nameEnglish)+"Result = "+parseToLowFirst(entity.nameEnglish)+"Client.getById(taskBaseVo.id);\n" +
                "        }\n" +
                "\n" +
                "        refreshView();\n" +
                "        dissmisLoading();\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    @UiThread\n" +
                "    void refreshView(){\n" +
                "        if("+parseToLowFirst(entity.nameEnglish)+"Result == null){\n" +
                "\n" +
                "            "+parseToLowFirst(entity.nameEnglish)+"Result = new "+entity.nameEnglish+"Result();\n" +
                "\n" +
                "            etAssignmentTime.setText(DateUtils.getCurrentYYYY_MM_DD());\n" +
                "\n" +
                "\n" +
                "        }else{\n" +
                "\n");

        for(String[] item : entity.columns){
            if(item[2].equals(T_I)){
                output.append(
                "            et"+item[0]+".setText("+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(item[0])+");\n");
            }else if(item[2].equals(T_S)){
                output.append(
                "            et"+item[0]+".setSelection(TypeUtils.getSelectedIndex(TypeUtils.TYPE_TEST, "+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(item[0])+"));\n");
            }
        }
        output.append(
                "\n" +
                "            getImage();\n" +
                "\n" +
                "            this.saveStatus = 1;\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * update,打开页面后，获取当前数据，并获取网络图片\n" +
                "     */\n" +
                "    @Background\n" +
                "    void getImage(){\n" +
                "\n");

        for(String[] item : entity.columns){
            if(item[2].equals(T_P)){
                output.append(
                "        super.getImageFromURL("+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(item[0])+", et"+item[0]+"Content);\n" +
                "\n");
            }
        }

        output.append(
                "    }\n" +
                "\n" +
                "\n");

        for(String[] item : entity.columns){
            if(item[2].equals(T_P)){
                output.append(
                "    @Click(R.id.btn_add_image"+item[0]+")\n" +
                "    void addImage"+item[0]+"(){\n" +
                "\n" +
                "        super.showChooseImage("+parseToLowFirst(item[0])+"List, et"+item[0]+"Content);\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n");
            }
        }

        output.append(
                "\n" +
                "    /**\n" +
                "     * 新增\n" +
                "     */\n" +
                "    @Override\n" +
                "    void add(){\n" +
                "\n" +
                "        "+parseToLowFirst(entity.nameEnglish)+"Result.assignByUserID = userPrefs.id().get();\n" +
                "        "+parseToLowFirst(entity.nameEnglish)+"Result.userId = userPrefs.id().get();\n" +
                "\n" +
                "        "+parseToLowFirst(entity.nameEnglish)+"Client.add("+parseToLowFirst(entity.nameEnglish)+"Result);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 更新\n" +
                "     */\n" +
                "    @Override\n" +
                "    void update(){\n" +
                "\n" +
                "        MultiValueMap<String, Object> data = null;\n" +
                "        try {\n" +
                "            data = "+parseToLowFirst(entity.nameEnglish)+"Result.parseToMultiValueMap();\n" +
                "        } catch (UnsupportedEncodingException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "\n");
        for(String[] item : entity.columns){
            if(item[2].equals(T_P)){
                output.append(
                "        FileUtils.addImageToData(data, "+entity.nameEnglish+"Result."+item[0]+", "+parseToLowFirst(item[0])+"List, this);\n");
            }
        }
        output.append(
                "        "+parseToLowFirst(entity.nameEnglish)+"Client.update(data);\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "\n" +
                "    @Override\n" +
                "    boolean validate(){\n" +
                "\n" +
                "        if(super.validate()) {\n" +
                "\n");

        for(String[] item : entity.columns){

            if(item[2].equals(T_I)){
                output.append(
                "            this."+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(item[0])+" = et"+item[0]+".getText().toString();\n" +
                "\n");
            }else if(item[2].equals(T_S)){
                output.append(
                "            this."+parseToLowFirst(entity.nameEnglish)+"Result."+parseToLowFirst(item[0])+" = et"+item[0]+".getSelectedItem().toString();\n" +
                "\n");

            }
        }
        output.append(
                "            return true;\n" +
                "        }{\n" +
                "            return false;\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "}\n");

        print(output.toString());
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

            if(item[2].equals(T_I)){
                output += "            <LinearLayout style=\"@style/"+columnStyle+"\">\n" +
                        "                <TextView style=\"@style/tableColName\"\n" +
                        "                    android:text=\""+item[1]+"\"/>\n" +
                        "                <com.andreabaccega.widget.FormEditText\n" +
                        "                    style=\"@style/tableColInput\"\n" +
                        "                    whatever:emptyErrorString=\"@string/notEmpty\"\n" +
                        "                    android:id=\"@+id/et"+item[0]+"\"/>\n" +
                        "            </LinearLayout>\n" +
                        "\n";
            }else
            if(item[2].equals(T_S)){
                output +=
                        "            <LinearLayout style=\"@style/tableTitle2\">\n" +
                        "                <TextView style=\"@style/tableColName\"\n" +
                        "                    android:text=\""+item[1]+"\"/>\n" +
                        "                <Spinner\n" +
                        "                    android:id=\"@+id/et"+item[0]+"\"\n" +
                        "                    android:layout_width=\"match_parent\"\n" +
                        "                    android:layout_height=\"wrap_content\" />\n" +
                        "            </LinearLayout>\n";
            }else if(item[2].equals(T_P)){
                output +=
                        "\n" +
                        "            <LinearLayout style=\"@style/tableTitle2\">\n" +
                        "                <TextView style=\"@style/tableColName\"\n" +
                        "                    android:text=\""+item[1]+"\"/>\n" +
                        "                <org.apmem.tools.layouts.FlowLayout\n" +
                        "                    android:id=\"@+id/et"+item[0]+"_content\"\n" +
                        "                    style=\"@style/tableColImageLayout\">\n" +
                        "                    <ImageView\n" +
                        "                        android:layout_margin=\"5dp\"\n" +
                        "                        android:id=\"@+id/btn_add_image"+item[0]+"\"\n" +
                        "                        style=\"@style/tableColImagAdd\"/>\n" +
                        "                </org.apmem.tools.layouts.FlowLayout>\n" +
                        "            </LinearLayout>\n" +
                        "\n";
            }
        }

        output +=
                "\n" +
                "        </LinearLayout>\n" +
                "    </ScrollView>\n" +
                "</LinearLayout>";


//        Log.i("sslog", output);
        print(output);
    }

    public void gResult(Entity entity){
        String output =
                "\n" +
                "\n" +
                "\n" +
                "<!-- *********************"+entity.nameEnglish+" Result ********************** -->"+
                "\n" +
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
                "\n" +
                "    public static final String UserID = \"UserID\";\n" +
                "    @JsonProperty(value=UserID)\n" +
                "    public int userId;\n" +
                "    public static final String AssignByUserID = \"AssignByUserID\";\n" +
                "    @JsonProperty(value=AssignByUserID)\n" +
                "    public int assignByUserID;";

        for (String[] item : entity.columns) {
            output +=
                    "    public static final String "+item[0]+" = \""+item[0]+"\";\n" +
                            "    @JsonProperty(value="+item[0]+")\n" +
                            "    public "+item[3]+" "+parseToLowFirst(item[0])+";\n";

        }

        output +=
                "\n" +
                "\n";

        output +=
                "    @JsonIgnore\n" +
                "    public MultiValueMap<String, Object> parseToMultiValueMap() throws UnsupportedEncodingException {\n" +
                "        MultiValueMap<String, Object> rtn = new LinkedMultiValueMap<>();\n" +
                "\n";

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


        print(output);
    }

    /**
     * 返回首字母小写的单词
     * @param item
     * @return
     */
    public String parseToLowFirst(String item){
        return item.substring(0,1).toLowerCase()+item.substring(1);
    }

    void print(String veryLongString){
        int maxLogSize = 1000;
        for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i+1) * maxLogSize;
            end = end > veryLongString.length() ? veryLongString.length() : end;
            Log.i("sslog", veryLongString.substring(start, end));
        }
    }
}
