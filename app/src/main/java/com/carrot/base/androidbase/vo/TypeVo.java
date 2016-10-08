package com.carrot.base.androidbase.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
public class TypeVo implements Serializable{

    public String name = "";

    public boolean flag;//是否有下级，默认有

    public boolean addFlag = true;//是否允许app新增，默认true

    public int src;//icon的地址

    public List<TypeVo> subTypes;

    public TypeVo(String name, boolean flag, int src, boolean addFlag){
        this.name = name;
        this.flag = flag;
        this.src = src;
        this.addFlag = addFlag;
    }
}
