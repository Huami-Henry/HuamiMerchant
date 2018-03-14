package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/31.
 */

public class ValueDetail {

    /**
     * code : 0
     * msg : 成功
     * data : {"display_pic2":"","end_date":"2018-02-28 09:38:50.0","display_pic1":"","operator_id":1,"begin_date":"2018-01-09 09:38:45.0","discount":0,"remark":"","package_type":1,"pricing_type":1,"last_mod":"2018-01-09 09:39:03.0","descs":"","use_type":1,"price":200,"intro":"协助您完成复杂精细的企业认证过程","name":"协助认证","pricing_asso_id":1,"id":1,"state":1,"create_date":"2018-01-09 09:39:03.0","use_num":0,"use_unit":""}
     */

    private int code;
    private String msg;
    private ValueDetailData data;

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

    public ValueDetailData getData() {
        return data;
    }

    public void setData(ValueDetailData data) {
        this.data = data;
    }

    public static class ValueDetailData {
        /**
         * display_pic2 :
         * end_date : 2018-02-28 09:38:50.0
         * display_pic1 :
         * operator_id : 1
         * begin_date : 2018-01-09 09:38:45.0
         * discount : 0
         * remark :
         * package_type : 1
         * pricing_type : 1
         * last_mod : 2018-01-09 09:39:03.0
         * descs :
         * use_type : 1
         * price : 200
         * intro : 协助您完成复杂精细的企业认证过程
         * name : 协助认证
         * pricing_asso_id : 1
         * id : 1
         * state : 1
         * create_date : 2018-01-09 09:39:03.0
         * use_num : 0
         * use_unit :
         */

        private String display_pic2;
        private String end_date;
        private String display_pic1;
        private int operator_id;
        private String begin_date;
        private int discount;
        private String remark;
        private int package_type;
        private int pricing_type;
        private String last_mod;
        private String descs;
        private int use_type;
        private int price;
        private String intro;
        private String name;
        private int pricing_asso_id;
        private int id;
        private int state;
        private String create_date;
        private int use_num;
        private String use_unit;

        public String getDisplay_pic2() {
            return display_pic2;
        }

        public void setDisplay_pic2(String display_pic2) {
            this.display_pic2 = display_pic2;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getDisplay_pic1() {
            return display_pic1;
        }

        public void setDisplay_pic1(String display_pic1) {
            this.display_pic1 = display_pic1;
        }

        public int getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(int operator_id) {
            this.operator_id = operator_id;
        }

        public String getBegin_date() {
            return begin_date;
        }

        public void setBegin_date(String begin_date) {
            this.begin_date = begin_date;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getPackage_type() {
            return package_type;
        }

        public void setPackage_type(int package_type) {
            this.package_type = package_type;
        }

        public int getPricing_type() {
            return pricing_type;
        }

        public void setPricing_type(int pricing_type) {
            this.pricing_type = pricing_type;
        }

        public String getLast_mod() {
            return last_mod;
        }

        public void setLast_mod(String last_mod) {
            this.last_mod = last_mod;
        }

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
        }

        public int getUse_type() {
            return use_type;
        }

        public void setUse_type(int use_type) {
            this.use_type = use_type;
        }

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPricing_asso_id() {
            return pricing_asso_id;
        }

        public void setPricing_asso_id(int pricing_asso_id) {
            this.pricing_asso_id = pricing_asso_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCreate_date() {
            return create_date;
        }

        public void setCreate_date(String create_date) {
            this.create_date = create_date;
        }

        public int getUse_num() {
            return use_num;
        }

        public void setUse_num(int use_num) {
            this.use_num = use_num;
        }

        public String getUse_unit() {
            return use_unit;
        }

        public void setUse_unit(String use_unit) {
            this.use_unit = use_unit;
        }
    }
}
