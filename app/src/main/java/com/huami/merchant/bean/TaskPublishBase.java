package com.huami.merchant.bean;
import java.util.List;
/**
 * Created by henry on 2018/1/19.
 */

public class TaskPublishBase {
    private String oldTaskId;
    private String uuid;
    private int merUserId;
    private TaskInfo taskinfo;
    private int taskPaperId;
    private List<TaskShop> taskShop;
    private int traInfoId;
    private int automaticAddType;
    private List<TaskCondition> taskCondition;
    private List<String> taskAttention;

    public String getOldTaskId() {
        return oldTaskId;
    }

    public void setOldTaskId(String oldTaskId) {
        this.oldTaskId = oldTaskId;
    }

    public int getMerUserId() {
        return merUserId;
    }

    public void setMerUserId(int merUserId) {
        this.merUserId = merUserId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public TaskInfo getTaskinfo() {
        return taskinfo;
    }

    public void setTaskinfo(TaskInfo taskinfo) {
        this.taskinfo = taskinfo;
    }

    public int getTaskPaperId() {
        return taskPaperId;
    }

    public void setTaskPaperId(int taskPaperId) {
        this.taskPaperId = taskPaperId;
    }

    public List<TaskShop> getTaskShop() {
        return taskShop;
    }

    public void setTaskShop(List<TaskShop> taskShop) {
        this.taskShop = taskShop;
    }

    public int getTraInfoId() {
        return traInfoId;
    }

    public void setTraInfoId(int traInfoId) {
        this.traInfoId = traInfoId;
    }

    public int getAutomaticAddType() {
        return automaticAddType;
    }

    public void setAutomaticAddType(int automaticAddType) {
        this.automaticAddType = automaticAddType;
    }

    public List<TaskCondition> getTaskCondition() {
        return taskCondition;
    }

    public void setTaskCondition(List<TaskCondition> taskCondition) {
        this.taskCondition = taskCondition;
    }

    public List<String> getTaskAttention() {
        return taskAttention;
    }

    public void setTaskAttention(List<String> taskAttention) {
        this.taskAttention = taskAttention;
    }
}
