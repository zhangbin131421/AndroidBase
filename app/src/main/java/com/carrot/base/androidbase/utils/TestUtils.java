package com.carrot.base.androidbase.utils;

import com.carrot.base.androidbase.vo.TypeVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 8/12/16.
 */
public class TestUtils {

    public static List<TypeVo> getAllItems(){

        List<TypeVo> result = new ArrayList<>();

        TypeVo type4 = new TypeVo("营销管理", false);
        List<TypeVo> subTypes4 = new ArrayList<>();
        String[] ss4 = new String[]{"线损管理", "采集消缺", "业扩报装", "表计故障", "工单处理", "营业普查", "营销管理"};

        for (String item : ss4) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes4.add(itemVo);
        }
        type4.setSubTypes(subTypes4);

        result.add(type4);


        TypeVo type3 = new TypeVo("安全生产", false);
        List<TypeVo> subTypes3 = new ArrayList<>();
        String[] ss3 = new String[]{"总保试跳", "总保性能测试", "设备消缺", "交叉跨越测量", "负荷电压测量", "设备巡视", "接地电阻测量","专项安全检查"};

        for (String item : ss3) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes3.add(itemVo);
        }
        type3.setSubTypes(subTypes3);

        result.add(type3);



        TypeVo type2 = new TypeVo("工程管理", true);

        result.add(type2);
//        List<TypeVo> subTypes1 = new ArrayList<>();
//        String[] ss1 = new String[]{"线损管理", "采集消缺", "业扩报装", "表计故障", "工单处理", "营业普查", "营销管理"};
//
//        for (String item : ss1) {
//            TypeVo itemVo = new TypeVo(item);
//            subTypes1.add(itemVo);
//        }
//        type1.setSubTypes(subTypes1);



        TypeVo type1 = new TypeVo("综合管理", false);
        List<TypeVo> subTypes1 = new ArrayList<>();
        String[] ss1 = new String[]{"车辆使用管理", "备品备件领用", "工器具领用", "通知报表"};

        for (String item : ss1) {
            TypeVo itemVo = new TypeVo(item, true);
            subTypes1.add(itemVo);
        }
        type1.setSubTypes(subTypes1);

        result.add(type1);

        return result;
    }
}
