package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/1/17.
 */

public class AuditResult {
    private String uuid;
    private String merUserId;
    private String checkCaseId;
    private String content;
    private double score;
    private String resultId;
    private List<QuestionSinglePostil> postil;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMerUserId() {
        return merUserId;
    }

    public void setMerUserId(String merUserId) {
        this.merUserId = merUserId;
    }

    public String getCheckCaseId() {
        return checkCaseId;
    }

    public void setCheckCaseId(String checkCaseId) {
        this.checkCaseId = checkCaseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public List<QuestionSinglePostil>  getPostil() {
        return postil;
    }

    public void setPostil(List<QuestionSinglePostil>  postil) {
        this.postil = postil;
    }
}
