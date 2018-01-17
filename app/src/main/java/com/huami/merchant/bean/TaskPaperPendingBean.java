package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by Henry on 2018/1/16.
 */

public class TaskPaperPendingBean {
    private int code;
    private String msg;
    private TaskPaperData data;

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

    public TaskPaperData getData() {
        return data;
    }

    public void setData(TaskPaperData data) {
        this.data = data;
    }

    public static class TaskPaperData {
        private TaskPaper taskPaper;
        private TaskTitle taskTitle;
        private List<TaskAnswer> taskAnswer;
        private List<CheckCaseIdListInfo> checkCaseIdList;

        public TaskPaper getTaskPaper() {
            return taskPaper;
        }

        public void setTaskPaper(TaskPaper taskPaper) {
            this.taskPaper = taskPaper;
        }

        public TaskTitle getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(TaskTitle taskTitle) {
            this.taskTitle = taskTitle;
        }

        public List<TaskAnswer> getTaskAnswer() {
            return taskAnswer;
        }

        public void setTaskAnswer(List<TaskAnswer> taskAnswer) {
            this.taskAnswer = taskAnswer;
        }

        public List<CheckCaseIdListInfo> getCheckCaseIdList() {
            return checkCaseIdList;
        }

        public void setCheckCaseIdList(List<CheckCaseIdListInfo> checkCaseIdList) {
            this.checkCaseIdList = checkCaseIdList;
        }

        public static class TaskPaper {
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

            public static class ExaminationInner {
                private int qId;
                private String title;
                private String type;
                private String sort;
                private String hint;
                private String postil;

                public int getqId() {
                    return qId;
                }

                public void setqId(int qId) {
                    this.qId = qId;
                }

                public String getPostil() {
                    return postil;
                }

                public void setPostil(String postil) {
                    this.postil = postil;
                }

                private List<Body> body;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getHint() {
                    return hint;
                }

                public void setHint(String hint) {
                    this.hint = hint;
                }

                public List<Body> getBody() {
                    return body;
                }

                public void setBody(List<Body> body) {
                    this.body = body;
                }

                public static class Body {
                    private int id;
                    private int sort;
                    private String type;
                    private int min;
                    private int max;
                    private int bodyIndex;
                    private String descs;
                    private List<Options> options;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public int getMin() {
                        return min;
                    }

                    public void setMin(int min) {
                        this.min = min;
                    }

                    public int getMax() {
                        return max;
                    }

                    public void setMax(int max) {
                        this.max = max;
                    }

                    public int getBodyIndex() {
                        return bodyIndex;
                    }

                    public void setBodyIndex(int bodyIndex) {
                        this.bodyIndex = bodyIndex;
                    }

                    public String getDescs() {
                        return descs;
                    }

                    public void setDescs(String descs) {
                        this.descs = descs;
                    }

                    public List<Options> getOptions() {
                        return options;
                    }

                    public void setOptions(List<Options> options) {
                        this.options = options;
                    }

                    public static class Options {
                        private String id;
                        private String content;
                        private String type;
                        private String sort;
                        private String skip;
                        private String isAnswer;

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getContent() {
                            return content;
                        }

                        public void setContent(String content) {
                            this.content = content;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getSort() {
                            return sort;
                        }

                        public void setSort(String sort) {
                            this.sort = sort;
                        }

                        public String getSkip() {
                            return skip;
                        }

                        public void setSkip(String skip) {
                            this.skip = skip;
                        }

                        public String getIsAnswer() {
                            return isAnswer;
                        }

                        public void setIsAnswer(String isAnswer) {
                            this.isAnswer = isAnswer;
                        }
                    }
                }
            }
        }

        public static class TaskTitle {
            private String brandName;
            private String generalPostil;
            private String shopName;
            private String taskRange;
            private String signInTime;
            private String submitTime;
            private String taskStandard;
            private String auditTime;
            private String taskName;
            private int auditStage;
            private String signOutTime;
            private String auditDeadline;

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getGeneralPostil() {
                return generalPostil;
            }

            public void setGeneralPostil(String generalPostil) {
                this.generalPostil = generalPostil;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getTaskRange() {
                return taskRange;
            }

            public void setTaskRange(String taskRange) {
                this.taskRange = taskRange;
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

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
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

            public String getSignOutTime() {
                return signOutTime;
            }

            public void setSignOutTime(String signOutTime) {
                this.signOutTime = signOutTime;
            }

            public String getAuditDeadline() {
                return auditDeadline;
            }

            public void setAuditDeadline(String auditDeadline) {
                this.auditDeadline = auditDeadline;
            }
        }

        public static class TaskAnswer {
            private String answer;
            private int state;
            private int question_id;
            private int body_id;
            public String getAnswer() {
                return answer;
            }

            public void setAnswer(String answer) {
                this.answer = answer;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public int getBody_id() {
                return body_id;
            }

            public void setBody_id(int body_id) {
                this.body_id = body_id;
            }
        }

        public static class CheckCaseIdListInfo {
            private int checkCaseId;
            private int checkTimes;
            private int state;
            private boolean check;

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public int getCheckCaseId() {
                return checkCaseId;
            }

            public void setCheckCaseId(int checkCaseId) {
                this.checkCaseId = checkCaseId;
            }

            public int getCheckTimes() {
                return checkTimes;
            }

            public void setCheckTimes(int checkTimes) {
                this.checkTimes = checkTimes;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }
        }
    }
}
