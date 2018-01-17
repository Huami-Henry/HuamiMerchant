package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by Henry on 2018/1/15.
 */

public class TaskPointBean {
    private int code;
    private String msg;
    private List<TaskPointInfo> data;

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

    public List<TaskPointInfo> getData() {
        return data;
    }

    public void setData(List<TaskPointInfo> data) {
        this.data = data;
    }
}
