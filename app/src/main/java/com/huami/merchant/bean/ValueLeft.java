package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueLeft {

    private int code;
    private String msg;
    private List<ValueLeftData> data;

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

    public List<ValueLeftData> getData() {
        return data;
    }

    public void setData(List<ValueLeftData> data) {
        this.data = data;
    }
}
