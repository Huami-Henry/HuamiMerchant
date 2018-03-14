package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueRightData {
    /**
     * count : 3
     * list : [{"price":10,"intro":"为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。","packageId":3,"name":"查询经纬度","discount":0},{"price":5,"intro":"大批量导入任务点位信息","packageId":2,"name":"任务点导入","discount":0}]
     */

    private int count;
    private List<ValueRightInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ValueRightInfo> getList() {
        return list;
    }

    public void setList(List<ValueRightInfo> list) {
        this.list = list;
    }
}
