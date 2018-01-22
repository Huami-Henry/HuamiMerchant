package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/19.
 */

public class TaskCondition {
    /**
     * last_mod : 1515400768000
     * expression : 3
     * task_id : 447
     * id : 507
     * state : 1
     * param_text :
     * create_date : 1515400768000
     * type : age
     * condition_id : 4
     * param1 : 16
     * param2 : 18
     * param_num : 2
     */

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
