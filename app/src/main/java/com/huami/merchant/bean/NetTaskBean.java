package com.huami.merchant.bean;

import java.util.List;

import com.huami.merchant.bean.TaskInfo.TaskCondition;

/**
 * Created by henry on 2018/1/19.
 */

public class NetTaskBean {

    private int code;
    private String msg;
    private NetTaskData data;

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

    public NetTaskData getData() {
        return data;
    }

    public void setData(NetTaskData data) {
        this.data = data;
    }

    public static class NetTaskData {

        private String task_name;
        private int require_shop_time;
        private int operator_id;
        private String train_name;
        private long accept_begin_date;
        private int task_id;
        private String task_desc;
        private int merchant_id;
        private long task_begin_date;
        private long last_mod;
        private int taskpaper_id;
        private String task_paper_name;
        private long task_end_date;
        private int state;
        private int task_price;
        private String task_icon;
        private long create_date;
        private int over_time;
        private long accept_end_date;
        private int sign_dis;
        private int is_automatically_add;
        private int is_issuebysystem;
        private String task_range;
        private int task_total_count;
        private int accept_type;
        private String task_info;
        private int shop_count;
        private long show_date;
        private int trainpaper_id;
        private int task_type;
        private List<TaskCondition> taskCondition;
        private List<String> taskAttention;

        public List<String> getTaskAttention() {
            return taskAttention;
        }

        public void setTaskAttention(List<String> taskAttention) {
            this.taskAttention = taskAttention;
        }

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

        public int getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(int operator_id) {
            this.operator_id = operator_id;
        }

        public String getTrain_name() {
            return train_name;
        }

        public void setTrain_name(String train_name) {
            this.train_name = train_name;
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

        public int getTaskpaper_id() {
            return taskpaper_id;
        }

        public void setTaskpaper_id(int taskpaper_id) {
            this.taskpaper_id = taskpaper_id;
        }

        public String getTask_paper_name() {
            return task_paper_name;
        }

        public void setTask_paper_name(String task_paper_name) {
            this.task_paper_name = task_paper_name;
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

        public int getTask_price() {
            return task_price;
        }

        public void setTask_price(int task_price) {
            this.task_price = task_price;
        }

        public String getTask_icon() {
            return task_icon;
        }

        public void setTask_icon(String task_icon) {
            this.task_icon = task_icon;
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

        public int getShop_count() {
            return shop_count;
        }

        public void setShop_count(int shop_count) {
            this.shop_count = shop_count;
        }

        public long getShow_date() {
            return show_date;
        }

        public void setShow_date(long show_date) {
            this.show_date = show_date;
        }

        public int getTrainpaper_id() {
            return trainpaper_id;
        }

        public void setTrainpaper_id(int trainpaper_id) {
            this.trainpaper_id = trainpaper_id;
        }

        public int getTask_type() {
            return task_type;
        }

        public void setTask_type(int task_type) {
            this.task_type = task_type;
        }

        public List<TaskCondition> getTaskCondition() {
            return taskCondition;
        }

        public void setTaskCondition(List<TaskCondition> taskCondition) {
            this.taskCondition = taskCondition;
        }

        public static class NetTaskCondition {
            private int expression;
            private int task_id;
            private int id;
            private int state;
            private String param_text;
            private String type;
            private int condition_id;
            private int param1;
            private int param2;
            private int param_num;
            public int getExpression() {
                return expression;
            }

            public void setExpression(int expression) {
                this.expression = expression;
            }

            public int getTask_id() {
                return task_id;
            }

            public void setTask_id(int task_id) {
                this.task_id = task_id;
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

            public String getParam_text() {
                return param_text;
            }

            public void setParam_text(String param_text) {
                this.param_text = param_text;
            }
            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getCondition_id() {
                return condition_id;
            }

            public void setCondition_id(int condition_id) {
                this.condition_id = condition_id;
            }

            public int getParam1() {
                return param1;
            }

            public void setParam1(int param1) {
                this.param1 = param1;
            }

            public int getParam2() {
                return param2;
            }

            public void setParam2(int param2) {
                this.param2 = param2;
            }

            public int getParam_num() {
                return param_num;
            }

            public void setParam_num(int param_num) {
                this.param_num = param_num;
            }
        }
    }
}
