package com.huami.merchant.bean;

import java.util.List;

import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskPaper.ExaminationInner;
import com.huami.merchant.bean.TaskPaperPendingBean.TaskPaperData.TaskAnswer;
import com.huami.merchant.bean.TaskPaperAlreadyPending.TaskPaperAlreadyPendingData.TaskPaperAlreadyPendingHistoryPostil;

/**
 * Created by Henry on 2018/1/16.
 */

public class PaperAlreadyAuditing {
    private int code;
    private String msg;
    private PaperAlreadyAuditingData data;

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

    public PaperAlreadyAuditingData getData() {
        return data;
    }

    public void setData(PaperAlreadyAuditingData data) {
        this.data = data;
    }

    public static class PaperAlreadyAuditingData {
        private String generalPostil;
        private PaperAlreadyAuditingTaskTitle taskTitle;
        private PaperAlreadyAuditingPaper taskPaper;
        private List<TaskAnswer> questionAnswer;
        private List<TaskPaperAlreadyPendingHistoryPostil> singlePostil;

        public String getGeneralPostil() {
            return generalPostil;
        }

        public void setGeneralPostil(String generalPostil) {
            this.generalPostil = generalPostil;
        }

        public PaperAlreadyAuditingTaskTitle getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(PaperAlreadyAuditingTaskTitle taskTitle) {
            this.taskTitle = taskTitle;
        }

        public PaperAlreadyAuditingPaper getTaskPaper() {
            return taskPaper;
        }

        public void setTaskPaper(PaperAlreadyAuditingPaper taskPaper) {
            this.taskPaper = taskPaper;
        }

        public List<TaskAnswer> getQuestionAnswer() {
            return questionAnswer;
        }

        public void setQuestionAnswer(List<TaskAnswer> questionAnswer) {
            this.questionAnswer = questionAnswer;
        }

        public List<TaskPaperAlreadyPendingHistoryPostil> getSinglePostil() {
            return singlePostil;
        }

        public void setSinglePostil(List<TaskPaperAlreadyPendingHistoryPostil> singlePostil) {
            this.singlePostil = singlePostil;
        }
        public static class PaperAlreadyAuditingTaskTitle {
            private String taskRange;
            private String brandName;
            private String signInTime;
            private String submitTime;
            private String taskStandard;
            private String shopName;
            private String taskName;
            private int auditStage;
            private String generalPostil;
            private String signOutTime;
            private String check_complete;
            private String auditTime;

            public String getTaskRange() {
                return taskRange;
            }

            public void setTaskRange(String taskRange) {
                this.taskRange = taskRange;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getSignInTime() {
                return signInTime;
            }

            public void setSignInTime(String signInTime) {
                this.signInTime = signInTime;
            }

            public String getSubmitTime() {
                return submitTime;
            }

            public void setSubmitTime(String submitTime) {
                this.submitTime = submitTime;
            }

            public String getTaskStandard() {
                return taskStandard;
            }

            public void setTaskStandard(String taskStandard) {
                this.taskStandard = taskStandard;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public int getAuditStage() {
                return auditStage;
            }

            public void setAuditStage(int auditStage) {
                this.auditStage = auditStage;
            }

            public String getGeneralPostil() {
                return generalPostil;
            }

            public void setGeneralPostil(String generalPostil) {
                this.generalPostil = generalPostil;
            }

            public String getSignOutTime() {
                return signOutTime;
            }

            public void setSignOutTime(String signOutTime) {
                this.signOutTime = signOutTime;
            }

            public String getCheck_complete() {
                return check_complete;
            }

            public void setCheck_complete(String check_complete) {
                this.check_complete = check_complete;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }
        }
        public static class PaperAlreadyAuditingPaper {
            private int paperId;
            private String paperName;
            private int merchantId;
            private int bodyLength;
            private List<ExaminationInner> paperData;

            public int getPaperId() {
                return paperId;
            }

            public void setPaperId(int paperId) {
                this.paperId = paperId;
            }

            public String getPaperName() {
                return paperName;
            }

            public void setPaperName(String paperName) {
                this.paperName = paperName;
            }

            public int getMerchantId() {
                return merchantId;
            }

            public void setMerchantId(int merchantId) {
                this.merchantId = merchantId;
            }

            public int getBodyLength() {
                return bodyLength;
            }

            public void setBodyLength(int bodyLength) {
                this.bodyLength = bodyLength;
            }

            public List<ExaminationInner> getPaperData() {
                return paperData;
            }

            public void setPaperData(List<ExaminationInner> paperData) {
                this.paperData = paperData;
            }
        }

        public static class PaperAlreadyAuditingQuestionAnswer {
            private int usercase_id;
            private int check_times;
            private int question_id;
            private String last_mod;
            private int taskpaper_id;
            private String answer;
            private String body_type;
            private int uca_check_usercase_id;
            private int id;
            private int state;
            private String create_date;
            private int body_id;

            public int getUsercase_id() {
                return usercase_id;
            }

            public void setUsercase_id(int usercase_id) {
                this.usercase_id = usercase_id;
            }

            public int getCheck_times() {
                return check_times;
            }

            public void setCheck_times(int check_times) {
                this.check_times = check_times;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public String getLast_mod() {
                return last_mod;
            }

            public void setLast_mod(String last_mod) {
                this.last_mod = last_mod;
            }

            public int getTaskpaper_id() {
                return taskpaper_id;
            }

            public void setTaskpaper_id(int taskpaper_id) {
                this.taskpaper_id = taskpaper_id;
            }

            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public String getBody_type() {
                return body_type;
            }

            public void setBody_type(String body_type) {
                this.body_type = body_type;
            }

            public int getUca_check_usercase_id() {
                return uca_check_usercase_id;
            }

            public void setUca_check_usercase_id(int uca_check_usercase_id) {
                this.uca_check_usercase_id = uca_check_usercase_id;
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

            public int getBody_id() {
                return body_id;
            }

            public void setBody_id(int body_id) {
                this.body_id = body_id;
            }
        }

        public static class PaperAlreadyAuditingSinglePostil {
            private String question_content;
            private int question_id;

            public String getQuestion_content() {
                return question_content;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }
        }
    }
}
