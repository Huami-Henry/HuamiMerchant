package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by Henry on 2018/1/16.
 */

public class TaskPaperAlreadyPending {
    private int code;
    private String msg;
    private TaskPaperAlreadyPendingData data;

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

    public TaskPaperAlreadyPendingData getData() {
        return data;
    }

    public void setData(TaskPaperAlreadyPendingData data) {
        this.data = data;
    }

    public static class TaskPaperAlreadyPendingData {

        private TaskPaperAlreadyPendingTaskTitle taskTitle;
        private List<TaskPaperAlreadyPendingHistoryPostil> historyPostil;
        private List<TaskPaperAlreadyPendingTaskAnswer> taskAnswer;

        public TaskPaperAlreadyPendingTaskTitle getTaskTitle() {
            return taskTitle;
        }

        public void setTaskTitle(TaskPaperAlreadyPendingTaskTitle taskTitle) {
            this.taskTitle = taskTitle;
        }

        public List<TaskPaperAlreadyPendingHistoryPostil> getHistoryPostil() {
            return historyPostil;
        }

        public void setHistoryPostil(List<TaskPaperAlreadyPendingHistoryPostil> historyPostil) {
            this.historyPostil = historyPostil;
        }

        public List<TaskPaperAlreadyPendingTaskAnswer> getTaskAnswer() {
            return taskAnswer;
        }

        public void setTaskAnswer(List<TaskPaperAlreadyPendingTaskAnswer> taskAnswer) {
            this.taskAnswer = taskAnswer;
        }

        public static class TaskPaperAlreadyPendingTaskTitle {
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

        public static class TaskPaperAlreadyPendingHistoryPostil {
            /**
             * last_mod : {"date":2,"hours":19,"seconds":9,"month":0,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":21,"time":1514892069000,"day":2}
             * question_content : btg
             * id : 41596
             * question_id : 7676
             * content :
             */

            private TaskPaperAlreadyPendingLastModBean last_mod;
            private String question_content;
            private int id;
            private int question_id;
            private String content;

            public TaskPaperAlreadyPendingLastModBean getLast_mod() {
                return last_mod;
            }

            public void setLast_mod(TaskPaperAlreadyPendingLastModBean last_mod) {
                this.last_mod = last_mod;
            }

            public String getQuestion_content() {
                return question_content;
            }

            public void setQuestion_content(String question_content) {
                this.question_content = question_content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(int question_id) {
                this.question_id = question_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public static class TaskPaperAlreadyPendingLastModBean {
                /**
                 * date : 2
                 * hours : 19
                 * seconds : 9
                 * month : 0
                 * nanos : 0
                 * timezoneOffset : -480
                 * year : 118
                 * minutes : 21
                 * time : 1514892069000
                 * day : 2
                 */

                private int date;
                private int hours;
                private int seconds;
                private int month;
                private int nanos;
                private int timezoneOffset;
                private int year;
                private int minutes;
                private long time;
                private int day;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }
            }
        }

        public static class TaskPaperAlreadyPendingTaskAnswer {
            /**
             * answer : 1,4
             * body_type : checkbox
             * body_id : 12164
             */

            private String answer;
            private String body_type;
            private int body_id;

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

            public int getBody_id() {
                return body_id;
            }

            public void setBody_id(int body_id) {
                this.body_id = body_id;
            }
        }
    }
}
