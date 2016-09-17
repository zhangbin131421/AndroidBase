package com.carrot.base.androidbase.preferences;

import com.carrot.base.androidbase.vo.result.AreaInformationResult;

/**
 * Created by victor on 9/17/16.
 */
public class DataInstance {

    private static DataInstance instance;

    public AreaInformationResult[] areaInformationResults;

    private DataInstance(){};

    public static DataInstance getInstance(){
        if(instance == null){
            instance = new DataInstance();
        }

        return instance;
    }

}
