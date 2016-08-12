package com.carrot.base.androidbase.vo;

import java.util.List;

/**
 * Created by victor on 8/12/16.
 */
public class TypeVo {


    private String name = "";

    private boolean flag;

    List<TypeVo> subTypes;

    public TypeVo(String name, boolean flag){
        this.name = name;
        this.flag = flag;
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
}
