package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueLeftData {
    private String name;
    private int id;
    private boolean check;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
