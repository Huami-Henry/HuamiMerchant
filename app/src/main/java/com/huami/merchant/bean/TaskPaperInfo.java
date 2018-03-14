package com.huami.merchant.bean;

/**
 * Created by Henry on 2018/1/12.
 */

public class TaskPaperInfo {
    private int taskpaper_id;
    private int train_id;
    private String task_paper_number;
    private String tra_paper_number;
    private String name;
    private int state;
    private boolean check;

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
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


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
