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

    public static List<TypeVo> getAllItems(Context context){

        List<TypeVo> result = new ArrayList<>();



        TypeVo type4 = new TypeVo(context.getString(R.string.type_1), false);
        List<TypeVo> subTypes4 = new ArrayList<>();
        String[] ss4 = new String[]{context.getString(R.string.type_1_1),
                context.getString(R.string.type_1_2),
                context.getString(R.string.type_1_3),
                context.getString(R.string.type_1_4),
                context.getString(R.string.type_1_5),
                context.getString(R.string.type_1_6),
                context.getString(R.string.type_1_7)};

        for (String item : ss4) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes4.add(itemVo);
        }
        type4.setSubTypes(subTypes4);

        result.add(type4);


        TypeVo type3 = new TypeVo(context.getString(R.string.type_2), false);
        List<TypeVo> subTypes3 = new ArrayList<>();
        String[] ss3 = new String[]{context.getString(R.string.type_2_1),
                context.getString(R.string.type_2_2),
                context.getString(R.string.type_2_3),
                context.getString(R.string.type_2_4),
                context.getString(R.string.type_2_5),
                context.getString(R.string.type_2_6),
                context.getString(R.string.type_2_7),
                context.getString(R.string.type_2_8),
        };

        for (String item : ss3) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes3.add(itemVo);
        }
        type3.setSubTypes(subTypes3);

        result.add(type3);



        TypeVo type2 = new TypeVo(context.getString(R.string.type_3), true);

        result.add(type2);
        List<TypeVo> subTypes2 = new ArrayList<>();
        String[] ss2 = new String[]{context.getString(R.string.type_3_1)};

        for (String item : ss2) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes2.add(itemVo);
        }
        type2.setSubTypes(subTypes2);



        TypeVo type1 = new TypeVo(context.getString(R.string.type_4), false);
        List<TypeVo> subTypes1 = new ArrayList<>();
        String[] ss1 = new String[]{context.getString(R.string.type_4_1)};

        for (String item : ss1) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes1.add(itemVo);
        }
        type1.setSubTypes(subTypes1);

        result.add(type1);

        return result;
    }



    public static void openItem(String typeName, Context context, TaskBaseVo taskBaseVo){
        switch (typeName){
            case "线损管理":
                LineBrokenManagementActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "采集消缺":
                CollectResolveTroubleActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "业扩报装":
                ExtendBussinessSetupActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "表计故障":
                MeterTroubleActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "工单处理":
                OrderHandleActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "营业普查":
                BusinessAuditeActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "停复电":
                StopStartElectricActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                //-------------------------
            case "总表试跳":
                CoreMeterTestActivity_.intent(context)
                        .taskBaseVo(taskBaseVo)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "总保性能检测":
                TotalPerformanceTestActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "设备巡视":
                EquipmentCheckActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "消缺记录":
                ResolveRecordActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "交叉跨越测量":
                CrossTestActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "负荷电压测量":
                VoltageMeasurementActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "接地电阻测量":
                EarthResistanceTestActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;
            case "专项安全检查":
                SpecialSecurityCheckActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;

            //----------

            case "农配网工程":
                DistributionNetworkEngineeringActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;

            //----------

            case "车辆管理":
                CarManagementActivity_.intent(context)
                        .flags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .start();
                break;

            default:
                Toast.makeText(context, "开发中....", Toast.LENGTH_SHORT).show();
        }

    }
}
