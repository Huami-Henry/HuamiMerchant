package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by Henry on 2018/1/8.
 */

public class TaskPreviewBean {

    /**
     * count : 1
     * code : 0
     * msg : 成功！
     * data : [{"task_name":"海底捞国庆节调研 10.8","last_mod":1514892170000,"usercase_id":68308,"check_result":"待审核","check_times":1,"uca_check_usercase_id":41597,"check_end_date":1515151370000,"region_name":"上海市","state":1,"merchant_id":8,"shop_name":"上海十二店"}]
     */

    private int count;
    private int code;
    private String msg;
    private List<TaskPreviewData> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public List<TaskPreviewData> getData() {
        return data;
    }

    public void setData(List<TaskPreviewData> data) {
        this.data = data;
    }

    public static class TaskPreviewData {
        /**
         * task_name : 海底捞国庆节调研 10.8
         * last_mod : 1514892170000
         * usercase_id : 68308
         * check_result : 待审核
         * check_times : 1
         * uca_check_usercase_id : 41597
         * check_end_date : 1515151370000
         * region_name : 上海市
         * state : 1
         * merchant_id : 8
         * shop_name : 上海十二店
         */

        private String task_name;
        private long last_mod;
        private int usercase_id;
        private String check_result;
        private int check_times;
        private int shop_id;
        private int uca_check_usercase_id;
        private long check_end_date;
        private String region_name;
        private String brand_name;
        private int state;
        private int merchant_id;
        private String shop_name;
        private String shop_address;
        private int price;
        private int taskpaper_id;

        public int getTaskpaper_id() {
            return taskpaper_id;
        }

        public void setTaskpaper_id(int taskpaper_id) {
            this.taskpaper_id = taskpaper_id;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getShop_address() {
            return shop_address;
        }

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public long getLast_mod() {
            return last_mod;
        }

        public void setLast_mod(long last_mod) {
            this.last_mod = last_mod;
        }

        public int getUsercase_id() {
            return usercase_id;
        }

        public void setUsercase_id(int usercase_id) {
            this.usercase_id = usercase_id;
        }

        public String getCheck_result() {
            return check_result;
        }

        public void setCheck_result(String check_result) {
            this.check_result = check_result;
        }

        public int getCheck_times() {
            return check_times;
        }

        public void setCheck_times(int check_times) {
            this.check_times = check_times;
        }

        public int getUca_check_usercase_id() {
            return uca_check_usercase_id;
        }

        public void setUca_check_usercase_id(int uca_check_usercase_id) {
            this.uca_check_usercase_id = uca_check_usercase_id;
        }

        public long getCheck_end_date() {
            return check_end_date;
        }

        public void setCheck_end_date(long check_end_date) {
            this.check_end_date = check_end_date;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }
    }
}
