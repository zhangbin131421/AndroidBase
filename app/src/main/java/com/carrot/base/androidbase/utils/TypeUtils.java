package com.carrot.base.androidbase.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.activity.handle.BusinessAuditeActivity_;
import com.carrot.base.androidbase.activity.handle.CarManagementActivity_;
import com.carrot.base.androidbase.activity.handle.CollectResolveTroubleActivity_;
import com.carrot.base.androidbase.activity.handle.CoreMeterTestActivity_;
import com.carrot.base.androidbase.activity.handle.CrossTestActivity_;
import com.carrot.base.androidbase.activity.handle.DistributionNetworkEngineeringActivity_;
import com.carrot.base.androidbase.activity.handle.EarthResistanceTestActivity_;
import com.carrot.base.androidbase.activity.handle.EquipmentCheckActivity_;
import com.carrot.base.androidbase.activity.handle.ExtendBussinessSetupActivity_;
import com.carrot.base.androidbase.activity.handle.LineBrokenManagementActivity_;
import com.carrot.base.androidbase.activity.handle.MeterTroubleActivity_;
import com.carrot.base.androidbase.activity.handle.OrderHandleActivity_;
import com.carrot.base.androidbase.activity.handle.ResolveRecordActivity_;
import com.carrot.base.androidbase.activity.handle.SpecialSecurityCheckActivity_;
import com.carrot.base.androidbase.activity.handle.StopStartElectricActivity_;
import com.carrot.base.androidbase.activity.handle.TotalPerformanceTestActivity_;
import com.carrot.base.androidbase.activity.handle.VoltageMeasurementActivity_;
import com.carrot.base.androidbase.vo.TypeVo;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 8/27/16.
 */
public class TypeUtils {

    public final static String TYPE_1 = "营销";
    public final static String TYPE_1_1 = "线损管理";
    public final static String TYPE_1_2 = "采集消缺";
    public final static String TYPE_1_3 = "业扩报装";
    public final static String TYPE_1_4 = "表计故障";
    public final static String TYPE_1_5 = "工单处理";
    public final static String TYPE_1_6 = "营业普查";
    public final static String TYPE_1_7 = "停复电";

    public final static String TYPE_2 = "生产";
    public final static String TYPE_2_1 = "总表试跳";
    public final static String TYPE_2_2 = "总保性能检测";
    public final static String TYPE_2_3 = "设备巡视";
    public final static String TYPE_2_4 = "消缺记录";
    public final static String TYPE_2_5 = "交叉跨越测量";
    public final static String TYPE_2_6 = "负荷电压测量";
    public final static String TYPE_2_7 = "接地电阻测量";
    public final static String TYPE_2_8 = "专项安全检查";

    public final static String TYPE_3 = "工程";
    public final static String TYPE_3_1 = "农配网工程";

    public final static String TYPE_4 = "综合";
    public final static String TYPE_4_1 = "车辆管理";
    /**
     * 测试
     */
    public final static String[] TYPE_TEST = new String[]{"测试1", "测试2"};

    /**
     * 处理情况
     */
    public final static String[] TYPE_HANDLER = new String[]{"已处理", "未处理"};
    /**
     * 巡视种类
     */
    public final static String[] CHECK_TYPE = new String[]{"定期","特殊","夜间","故障","监察","其他"};

    /**
     * 缺陷等级
     */
    public final static String[] DEFECT_LEVEL = new String[]{"一般","重大","紧急"};


    /**
     * 存在问题
     */
    public final static String[] EXIST_DEFECT = new String[]{"无","有"};
    /**
     * 存在问题
     */
    public final static String[] TASK_REPORT = new String[]{"否","是"};

    /**
     * 工作性质
     */
    public final static String[] WORK_TYPE = new String[]{"消缺","正常检修","抢修","故障","其它"};

    /**
     * 天气
     */
    public final static String[] WEATHERS = new String[]{"睛","阴","多云"};
    /**
     * 试跳方法
     */
    public final static String[] TEST_WAY = new String[]{"测试仪","手工"};
    /**
     * 检测结果-总表试跳
     */
    public final static String[] TEST_RESULT = new String[]{"合格","不合格"};
    /**
     * 台区名称
     */
    public final static String[] AREA = new String[]{"台区1","台区2","台区3","台区4"};
    /**
     * 安全措施
     */
    public final static String[] SAFETY_MEASURE = new String[]{"措施1","措施2","措施3","措施4"};
    /**
     * 不合格原因 -- 线损管理
     */
    public final static String[] UNQUALIFIED_REASON = new String[]{"窃电","采集","表计故障"};
    /**
     * 保护线路
     */
    public final static String[] PRODUCTION_LINE = new String[]{"保护1","保护2","保护3"};
    /**
     * 型号 -- 总保性能检测
     */
    public final static String[] TYPE_TPT = new String[]{"型号1","型号2","型号3"};


    /**
     * 获取选中的index
     * @param items
     * @param item
     * @return
     */
    public static int getSelectedIndex(String[] items, String item){
        int rtn = 0;
        for(int i = 0; i < items.length; i ++){
            if(items[i].equals(item)){
                rtn = i;
                break;
            }
        }
        return rtn;
    }


    public static List<TypeVo> getAllItems(Context context){

        List<TypeVo> result = new ArrayList<>();



        TypeVo type4 = new TypeVo(TYPE_1, false, R.mipmap.manager4);
        List<TypeVo> subTypes4 = new ArrayList<>();
        String[] ss4 = new String[]{TYPE_1_1,TYPE_1_2,TYPE_1_3,TYPE_1_4,TYPE_1_5,TYPE_1_6,TYPE_1_7};

        for (String item : ss4) {
            TypeVo itemVo = new TypeVo(item, true, R.mipmap.manager1);
            subTypes4.add(itemVo);
        }
        type4.setSubTypes(subTypes4);

        result.add(type4);


        TypeVo type3 = new TypeVo(TYPE_2, false, R.mipmap.manager1);
        List<TypeVo> subTypes3 = new ArrayList<>();
        String[] ss3 = new String[]{TYPE_2_1,
                TYPE_2_2,
                TYPE_2_3,
                TYPE_2_4,
                TYPE_2_5,
                TYPE_2_6,
                TYPE_2_7,
                TYPE_2_8,
        };

        for (String item : ss3) {
            TypeVo itemVo = new TypeVo(item, true, R.mipmap.manager1);
            subTypes3.add(itemVo);
        }
        type3.setSubTypes(subTypes3);

        result.add(type3);



        TypeVo type2 = new TypeVo(TYPE_3, true, R.mipmap.manager3);

        result.add(type2);
        List<TypeVo> subTypes2 = new ArrayList<>();
        String[] ss2 = new String[]{TYPE_3_1};

        for (String item : ss2) {
            TypeVo itemVo = new TypeVo(item, true, R.mipmap.manager1);
            subTypes2.add(itemVo);
        }
        type2.setSubTypes(subTypes2);



        TypeVo type1 = new TypeVo(TYPE_4, false, R.mipmap.manager2);
        List<TypeVo> subTypes1 = new ArrayList<>();
        String[] ss1 = new String[]{TYPE_4_1};

        for (String item : ss1) {
            TypeVo itemVo = new TypeVo(item, true, R.mipmap.manager1);
            subTypes1.add(itemVo);
        }
        type1.setSubTypes(subTypes1);

        result.add(type1);

        return result;
    }


    /**
     *
     * @param typeName
     * @param context
     * @param taskBaseVo
     * @param requestCode
     * @param status 未完成1、已完成2,新增0
     */
    public static void openItem(String typeName, Context context, TaskBaseVo taskBaseVo, int requestCode, int status){

        switch (typeName){
            case TYPE_1_1:
                LineBrokenManagementActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_2:
                CollectResolveTroubleActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_3:
                ExtendBussinessSetupActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_4:
                MeterTroubleActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_5:
                OrderHandleActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_6:
                BusinessAuditeActivity_.intent(context)
                        .startForResult(requestCode);
                break;
            case TYPE_1_7:
                StopStartElectricActivity_.intent(context)
                        .startForResult(requestCode);
                //-------------------------
            case TYPE_2_1:
                CoreMeterTestActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_2:
                TotalPerformanceTestActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_3:
                EquipmentCheckActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_4:
                ResolveRecordActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_5:
                CrossTestActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_6:
                VoltageMeasurementActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_7:
                EarthResistanceTestActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;
            case TYPE_2_8:
                SpecialSecurityCheckActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;

            //----------

            case TYPE_3_1:
                DistributionNetworkEngineeringActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .isFinished(status)
                        .startForResult(requestCode);
                break;

            //----------

            case TYPE_4_1:
                CarManagementActivity_.intent(context)
                        .startForResult(requestCode);
                break;

            default:
                Toast.makeText(context, "开发中....", Toast.LENGTH_SHORT).show();
        }

    }
}
