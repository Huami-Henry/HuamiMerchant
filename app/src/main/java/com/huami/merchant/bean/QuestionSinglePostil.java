package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/17.
 */

public class QuestionSinglePostil {
    private int checkCaseId;
    private int userCaseId;
    private int taskPaperId;
    private int questionId;
    private String questionContent;

    public int getCheckCaseId() {
        return checkCaseId;
    }

    public void setCheckCaseId(int checkCaseId) {
        this.checkCaseId = checkCaseId;
    }

    public int getUserCaseId() {
        return userCaseId;
    }

    public void setUserCaseId(int userCaseId) {
        this.userCaseId = userCaseId;
    }

    public int getTaskPaperId() {
        return taskPaperId;
    }

    public void setTaskPaperId(int taskPaperId) {
        this.taskPaperId = taskPaperId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
