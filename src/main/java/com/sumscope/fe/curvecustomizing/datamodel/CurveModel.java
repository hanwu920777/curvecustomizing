package com.sumscope.fe.curvecustomizing.datamodel;

import lombok.Data;


public enum CurveModel {

    EXISTING_CURVE("已存在的曲线",1),INTERESTRATE_CURVE("零息利率",2),FACTOR_CURVE("贴现因子",3);
    private String name;
    private int index;
    // 构造方法
    private CurveModel(String name, int index) {
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (CurveModel c : CurveModel.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
