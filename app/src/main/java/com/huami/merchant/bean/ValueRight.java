package com.huami.merchant.bean;
/**
 * Created by henry on 2018/1/23.
 */
public class ValueRight {
    /**
     * code : 0
     * msg : 成功
     * data : {"count":3,"list":[{"price":10,"intro":"为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。","packageId":3,"name":"查询经纬度","discount":0},{"price":5,"intro":"大批量导入任务点位信息","packageId":2,"name":"任务点导入","discount":0}]}
     */

    private int code;
    private String msg;
    private ValueRightData data;

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

    public ValueRightData getData() {
        return data;
    }

    public void setData(ValueRightData data) {
        this.data = data;
    }

}
