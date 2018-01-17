package com.huami.merchant.bean;

/**
 * Created by Henry on 2018/1/12.
 */

public class TaskPaperInfo {
    private int taskpaper_id;
    private int trainpaper_id;
    private long last_mod;
    private String task_paper_number;
    private String tra_paper_number;
    private String name;
    private int state;
    private long create_date;
    private boolean check;

    public int getTrainpaper_id() {
        return trainpaper_id;
    }

    public void setTrainpaper_id(int trainpaper_id) {
        this.trainpaper_id = trainpaper_id;
    }

    public String getTra_paper_number() {
        return tra_paper_number;
    }

    public void setTra_paper_number(String tra_paper_number) {
        this.tra_paper_number = tra_paper_number;
    }

    public int getTaskpaper_id() {
        return taskpaper_id;
    }

    public void setTaskpaper_id(int taskpaper_id) {
        this.taskpaper_id = taskpaper_id;
    }

    public long getLast_mod() {
        return last_mod;
    }

    public void setLast_mod(long last_mod) {
        this.last_mod = last_mod;
    }

    public String getTask_paper_number() {
        return task_paper_number;
    }

    public void setTask_paper_number(String task_paper_number) {
        this.task_paper_number = task_paper_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
