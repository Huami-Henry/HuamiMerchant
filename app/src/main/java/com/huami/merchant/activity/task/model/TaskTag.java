package com.huami.merchant.activity.task.model;

import java.util.List;

/**
 * Created by henry on 2018/1/18.
 */

public class TaskTag {

    private int code;
    private String msg;
    private List<TaskTagInfo> data;

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

    public List<TaskTagInfo> getData() {
        return data;
    }

    public void setData(List<TaskTagInfo> data) {
        this.data = data;
    }

    public static class TaskTagInfo {
        /**
         * last_mod : {"date":26,"hours":13,"seconds":35,"month":11,"nanos":0,"timezoneOffset":-480,"year":117,"minutes":12,"time":1514265155000,"day":2}
         * descs : 评论没有按题意写
         * id : 5
         * state : 1
         * sort : 1
         * type : 2
         * create_date : {"date":26,"hours":13,"seconds":35,"month":11,"nanos":0,"timezoneOffset":-480,"year":117,"minutes":12,"time":1514265155000,"day":2}
         */

        private String descs;
        private int id;
        private int state;
        private int sort;
        private int type;

        public String getDescs() {
            return descs;
        }

        public void setDescs(String descs) {
            this.descs = descs;
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

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
