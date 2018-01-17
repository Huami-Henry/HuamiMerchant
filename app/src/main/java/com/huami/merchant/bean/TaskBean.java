package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by Henry on 2018/1/5.
 */

public class TaskBean {
    private int code;
    private String msg;
    private TaskData data;

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

    public TaskData getData() {
        return data;
    }

    public void setData(TaskData data) {
        this.data = data;
    }

    public static class TaskData {
        private int count;
        private List<TaskInfo> task;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<TaskInfo> getTask() {
            return task;
        }

        public void setTask(List<TaskInfo> task) {
            this.task = task;
        }

        public static class TaskInfo {
            /**
             * task_name : 靳永辉撞树
             * require_shop_time : 0
             * accept_begin_date : 1514951745000
             * task_id : 443
             * task_desc : 123123
             * merchant_id : 1
             * task_begin_date : 1514951749000
             * last_mod : 1514951899000
             * check_result : 待审核
             * task_end_date : 1517025356000
             * state : 2
             * create_date : 1514951899000
             * over_time : 24
             * accept_end_date : 1516938946000
             * sign_dis : 1000
             * is_automatically_add : 0
             * is_issuebysystem : 0
             * task_range : 上海
             * task_total_count : 0
             * check_state : 1
             * accept_type : 2
             * task_info :
             * task_icon :
             * show_date : 1514951742000
             * mer_user_id : 1
             * task_type : 1
             * task_price : 0
             */
            private String task_name;
            private int require_shop_time;
            private long accept_begin_date;
            private int task_id;
            private String task_desc;
            private int merchant_id;
            private long task_begin_date;
            private long last_mod;
            private String check_result;
            private long task_end_date;
            private int state;
            private long create_date;
            private int over_time;
            private long accept_end_date;
            private int sign_dis;
            private int is_automatically_add;
            private int is_issuebysystem;
            private String task_range;
            private int task_total_count;
            private int check_state;
            private int accept_type;
            private String task_info;
            private String task_icon;
            private long show_date;
            private int mer_user_id;
            private int task_type;
            private int task_price;

            public String getTask_name() {
                return task_name;
            }

            public void setTask_name(String task_name) {
                this.task_name = task_name;
            }

            public int getRequire_shop_time() {
                return require_shop_time;
            }

            public void setRequire_shop_time(int require_shop_time) {
                this.require_shop_time = require_shop_time;
            }

            public long getAccept_begin_date() {
                return accept_begin_date;
            }

            public void setAccept_begin_date(long accept_begin_date) {
                this.accept_begin_date = accept_begin_date;
            }

            public int getTask_id() {
                return task_id;
            }

            public void setTask_id(int task_id) {
                this.task_id = task_id;
            }

            public String getTask_desc() {
                return task_desc;
            }

            public void setTask_desc(String task_desc) {
                this.task_desc = task_desc;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public long getTask_begin_date() {
                return task_begin_date;
            }

            public void setTask_begin_date(long task_begin_date) {
                this.task_begin_date = task_begin_date;
            }

            public long getLast_mod() {
                return last_mod;
            }

            public void setLast_mod(long last_mod) {
                this.last_mod = last_mod;
            }

            public String getCheck_result() {
                return check_result;
            }

            public void setCheck_result(String check_result) {
                this.check_result = check_result;
            }

            public long getTask_end_date() {
                return task_end_date;
            }

            public void setTask_end_date(long task_end_date) {
                this.task_end_date = task_end_date;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public long getCreate_date() {
                return create_date;
            }

            public void setCreate_date(long create_date) {
                this.create_date = create_date;
            }

            public int getOver_time() {
                return over_time;
            }

            public void setOver_time(int over_time) {
                this.over_time = over_time;
            }

            public long getAccept_end_date() {
                return accept_end_date;
            }

            public void setAccept_end_date(long accept_end_date) {
                this.accept_end_date = accept_end_date;
            }

            public int getSign_dis() {
                return sign_dis;
            }

            public void setSign_dis(int sign_dis) {
                this.sign_dis = sign_dis;
            }

            public int getIs_automatically_add() {
                return is_automatically_add;
            }

            public void setIs_automatically_add(int is_automatically_add) {
                this.is_automatically_add = is_automatically_add;
            }

            public int getIs_issuebysystem() {
                return is_issuebysystem;
            }

            public void setIs_issuebysystem(int is_issuebysystem) {
                this.is_issuebysystem = is_issuebysystem;
            }

            public String getTask_range() {
                return task_range;
            }

            public void setTask_range(String task_range) {
                this.task_range = task_range;
            }

            public int getTask_total_count() {
                return task_total_count;
            }

            public void setTask_total_count(int task_total_count) {
                this.task_total_count = task_total_count;
            }

            public int getCheck_state() {
                return check_state;
            }

            public void setCheck_state(int check_state) {
                this.check_state = check_state;
            }

            public int getAccept_type() {
                return accept_type;
            }

            public void setAccept_type(int accept_type) {
                this.accept_type = accept_type;
            }

            public String getTask_info() {
                return task_info;
            }

            public void setTask_info(String task_info) {
                this.task_info = task_info;
            }

            public String getTask_icon() {
                return task_icon;
            }

            public void setTask_icon(String task_icon) {
                this.task_icon = task_icon;
            }

            public long getShow_date() {
                return show_date;
            }

            public void setShow_date(long show_date) {
                this.show_date = show_date;
            }

            public int getMer_user_id() {
                return mer_user_id;
            }

            public void setMer_user_id(int mer_user_id) {
                this.mer_user_id = mer_user_id;
            }

            public int getTask_type() {
                return task_type;
            }

            public void setTask_type(int task_type) {
                this.task_type = task_type;
            }

            public int getTask_price() {
                return task_price;
            }

            public void setTask_price(int task_price) {
                this.task_price = task_price;
            }
        }
    }
}
