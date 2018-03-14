package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/1/29.
 */

public class SingleValueBean {
    private int code;
    private String msg;
    private List<ValueRightInfo> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValueRightInfo> getData() {
        return data;
    }

    public void setData(List<ValueRightInfo> data) {
        this.data = data;
    }
}
