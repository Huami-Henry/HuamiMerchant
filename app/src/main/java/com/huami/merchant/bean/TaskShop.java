package com.huami.merchant.bean;

/**
 * Created by Henry on 2018/1/15.
 */
public class TaskShop {
    private int shop_id;
    private int price;
    private int mer_price;
    private int total_num;

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMer_price() {
        return mer_price;
    }

    public void setMer_price(int mer_price) {
        this.mer_price = mer_price;
    }

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }
}
