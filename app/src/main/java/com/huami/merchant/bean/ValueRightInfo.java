package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/23.
 */

public class ValueRightInfo {
    /**
     * price : 10
     * intro : 为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。
     * packageId : 3
     * name : 查询经纬度
     * discount : 0
     */

    private int price;
    private String intro;
    private int packageId;
    private String name;
    private int discount;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
