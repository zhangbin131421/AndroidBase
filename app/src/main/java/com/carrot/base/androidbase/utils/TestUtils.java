package com.carrot.base.androidbase.utils;

import android.content.Context;

import com.carrot.base.androidbase.R;
import com.carrot.base.androidbase.vo.TaskVo;
import com.carrot.base.androidbase.vo.TypeVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 8/12/16.
 */
public class TestUtils {


    public static List<TaskVo> getAllTasks(String status){

        List<TaskVo> result = new ArrayList<>();

        TaskVo taskVo1 = new TaskVo("任务1", status, "2016-08-12");
        TaskVo taskVo2 = new TaskVo("任务2", status, "2016-08-12");
        TaskVo taskVo3 = new TaskVo("任务3", status, "2016-08-11");
        TaskVo taskVo4 = new TaskVo("任务4", status, "2016-08-11");
        TaskVo taskVo5 = new TaskVo("任务5", status, "2016-08-10");
        TaskVo taskVo6 = new TaskVo("任务6", status, "2016-08-10");
        TaskVo taskVo7 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo8 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo9 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo10 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo11 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo12 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo13 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo14 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo15 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo16 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo17 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo18 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo19 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo20 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo21 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo22 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo23 = new TaskVo("任务7", status, "2016-08-10");
        TaskVo taskVo24 = new TaskVo("任务7", status, "2016-08-10");

        result.add(taskVo1);
        result.add(taskVo2);
        result.add(taskVo3);
        result.add(taskVo4);
        result.add(taskVo5);
        result.add(taskVo6);
        result.add(taskVo7);
        result.add(taskVo8);
        result.add(taskVo9);
        result.add(taskVo10);
        result.add(taskVo11);
        result.add(taskVo12);
        result.add(taskVo13);
        result.add(taskVo14);
        result.add(taskVo15);
        result.add(taskVo16);
        result.add(taskVo17);
        result.add(taskVo18);
        result.add(taskVo19);
        result.add(taskVo20);
        result.add(taskVo21);
        result.add(taskVo22);
        result.add(taskVo23);
        result.add(taskVo24);


        return result;
    }
}
