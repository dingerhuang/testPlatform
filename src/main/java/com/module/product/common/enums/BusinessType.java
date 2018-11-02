package com.module.product.common.enums;

public enum BusinessType {
    xf("小凡分期",1),yzt("一账通",2);
    private String name;
    private int index;
    private BusinessType(String name,int index){
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (BusinessType c : BusinessType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
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
