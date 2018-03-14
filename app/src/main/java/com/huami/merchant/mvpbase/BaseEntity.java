package com.huami.merchant.mvpbase;

import java.util.List;

/**
 * Created by henry on 2018/2/7.
 */

public class BaseEntity<E> {
    private int code;
    private String msg;
    private List<E> data;

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

    public List<E> getData() {
        return data;
    }

    public void setData(List<E>data) {
        this.data = data;
    }
}
