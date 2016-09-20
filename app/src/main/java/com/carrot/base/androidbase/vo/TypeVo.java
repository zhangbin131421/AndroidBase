package com.carrot.base.androidbase.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
public class TypeVo implements Serializable{


    private String name = "";

    private boolean flag;

    private int src;

    List<TypeVo> subTypes;

    public TypeVo(String name, boolean flag, int src){
        this.name = name;
        this.flag = flag;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public List<TypeVo> getSubTypes() {
        return subTypes;
    }

    public void setSubTypes(List<TypeVo> subTypes) {
        this.subTypes = subTypes;
    }


    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }
}
