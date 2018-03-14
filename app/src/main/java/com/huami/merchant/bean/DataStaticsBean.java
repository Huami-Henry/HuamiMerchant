package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/1/25.
 */

public class DataStaticsBean {

    /**
     * code : 0
     * msg : 成功
     * data : {"taskquestionInfo":[{"num":2,"oContent":"是","oId":1,"bId":1,"qId":1,"oSort":1},{"num":1,"oContent":"否","oId":2,"bId":1,"qId":1,"oSort":2},{"num":3,"oContent":"第1次就摇到了","oId":3,"bId":3,"qId":3,"oSort":1},{"num":0,"oContent":"第2次或摇到所有商家后点击出现","oId":4,"bId":3,"qId":3,"oSort":2},{"num":0,"oContent":"未摇到（已打开蓝牙并根据要求在微信周边中摇动）","oId":5,"bId":3,"qId":3,"oSort":3},{"num":3,"oContent":"很快","oId":6,"bId":5,"qId":4,"oSort":1},{"num":0,"oContent":"适中","oId":7,"bId":5,"qId":4,"oSort":2},{"num":0,"oContent":"很慢","oId":8,"bId":5,"qId":4,"oSort":3},{"num":0,"oContent":"没注意到这个问题","oId":9,"bId":5,"qId":4,"oSort":4},{"num":1,"oContent":"可以很快地解释并告知","oId":10,"bId":6,"qId":5,"oSort":1},{"num":1,"oContent":"迟疑但能告知","oId":11,"bId":6,"qId":5,"oSort":2},{"num":1,"oContent":"不清楚活动但立刻咨询其他同事","oId":12,"bId":6,"qId":5,"oSort":3},{"num":0,"oContent":"表示不清楚，也不做进一步说明","oId":13,"bId":6,"qId":5,"oSort":4},{"num":2,"oContent":"有，优惠力度很大","oId":14,"bId":7,"qId":6,"oSort":1},{"num":0,"oContent":"一般，可能会考虑用","oId":15,"bId":7,"qId":6,"oSort":2},{"num":1,"oContent":"没有，觉得不给力","oId":16,"bId":7,"qId":6,"oSort":3},{"num":0,"oContent":"优惠力度不错，但是我不喜欢这次的产品","oId":17,"bId":7,"qId":6,"oSort":4},{"num":2,"oContent":"会","oId":18,"bId":8,"qId":7,"oSort":1},{"num":0,"oContent":"不会","oId":19,"bId":8,"qId":7,"oSort":2},{"num":1,"oContent":"如果有招贴告知，会摇一摇","oId":20,"bId":8,"qId":7,"oSort":3}],"taskquestion":[{"qTitle":"在限定范围内，是否摇到了汉堡王的活动链接？","type":"radio","qId":1},{"qTitle":"如果摇到，请问是第几次摇到？（如果选其他请填写具体次数）","type":"radio","qId":3},{"qTitle":"点击活动链接，跳转至优惠券页面时，我感觉等待时间（单选）","type":"radio","qId":4},{"qTitle":"在咨询店员关于优惠券的使用情况时，店员的反应是（单选）","type":"radio","qId":5},{"qTitle":"摇出来的优惠券，在您看来有吸引力吗？（单选）","type":"radio","qId":6},{"qTitle":"以后经过其他汉堡王门店时，您会主动摇一摇吗？（单选）","type":"radio","qId":7}],"taskInfo":{"taskName":"小辉哥火锅","accept_begin_date":{"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4},"task_end_date":{"date":1,"hours":23,"seconds":59,"month":11,"nanos":0,"timezoneOffset":-480,"year":117,"minutes":59,"time":1512143999000,"day":5},"task_begin_date":{"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4},"accept_end_date":{"date":31,"hours":23,"seconds":59,"month":9,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":59,"time":1477929599000,"day":1}},"situation":{"totalNum":1100,"submitNum":33,"receiveNum":33,"completedNum":32}}
     */

    private int code;
    private String msg;
    private DataStaticsData data;

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

    public DataStaticsData getData() {
        return data;
    }

    public void setData(DataStaticsData data) {
        this.data = data;
    }

    public static class DataStaticsData {
        /**
         * taskquestionInfo : [{"num":2,"oContent":"是","oId":1,"bId":1,"qId":1,"oSort":1},{"num":1,"oContent":"否","oId":2,"bId":1,"qId":1,"oSort":2},{"num":3,"oContent":"第1次就摇到了","oId":3,"bId":3,"qId":3,"oSort":1},{"num":0,"oContent":"第2次或摇到所有商家后点击出现","oId":4,"bId":3,"qId":3,"oSort":2},{"num":0,"oContent":"未摇到（已打开蓝牙并根据要求在微信周边中摇动）","oId":5,"bId":3,"qId":3,"oSort":3},{"num":3,"oContent":"很快","oId":6,"bId":5,"qId":4,"oSort":1},{"num":0,"oContent":"适中","oId":7,"bId":5,"qId":4,"oSort":2},{"num":0,"oContent":"很慢","oId":8,"bId":5,"qId":4,"oSort":3},{"num":0,"oContent":"没注意到这个问题","oId":9,"bId":5,"qId":4,"oSort":4},{"num":1,"oContent":"可以很快地解释并告知","oId":10,"bId":6,"qId":5,"oSort":1},{"num":1,"oContent":"迟疑但能告知","oId":11,"bId":6,"qId":5,"oSort":2},{"num":1,"oContent":"不清楚活动但立刻咨询其他同事","oId":12,"bId":6,"qId":5,"oSort":3},{"num":0,"oContent":"表示不清楚，也不做进一步说明","oId":13,"bId":6,"qId":5,"oSort":4},{"num":2,"oContent":"有，优惠力度很大","oId":14,"bId":7,"qId":6,"oSort":1},{"num":0,"oContent":"一般，可能会考虑用","oId":15,"bId":7,"qId":6,"oSort":2},{"num":1,"oContent":"没有，觉得不给力","oId":16,"bId":7,"qId":6,"oSort":3},{"num":0,"oContent":"优惠力度不错，但是我不喜欢这次的产品","oId":17,"bId":7,"qId":6,"oSort":4},{"num":2,"oContent":"会","oId":18,"bId":8,"qId":7,"oSort":1},{"num":0,"oContent":"不会","oId":19,"bId":8,"qId":7,"oSort":2},{"num":1,"oContent":"如果有招贴告知，会摇一摇","oId":20,"bId":8,"qId":7,"oSort":3}]
         * taskquestion : [{"qTitle":"在限定范围内，是否摇到了汉堡王的活动链接？","type":"radio","qId":1},{"qTitle":"如果摇到，请问是第几次摇到？（如果选其他请填写具体次数）","type":"radio","qId":3},{"qTitle":"点击活动链接，跳转至优惠券页面时，我感觉等待时间（单选）","type":"radio","qId":4},{"qTitle":"在咨询店员关于优惠券的使用情况时，店员的反应是（单选）","type":"radio","qId":5},{"qTitle":"摇出来的优惠券，在您看来有吸引力吗？（单选）","type":"radio","qId":6},{"qTitle":"以后经过其他汉堡王门店时，您会主动摇一摇吗？（单选）","type":"radio","qId":7}]
         * taskInfo : {"taskName":"小辉哥火锅","accept_begin_date":{"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4},"task_end_date":{"date":1,"hours":23,"seconds":59,"month":11,"nanos":0,"timezoneOffset":-480,"year":117,"minutes":59,"time":1512143999000,"day":5},"task_begin_date":{"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4},"accept_end_date":{"date":31,"hours":23,"seconds":59,"month":9,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":59,"time":1477929599000,"day":1}}
         * situation : {"totalNum":1100,"submitNum":33,"receiveNum":33,"completedNum":32}
         */

        private DataStaticsTaskInfo taskInfo;
        private DataStaticsSituation situation;
        private List<DataStaticsTaskquestionInfo> taskquestionInfo;
        private List<DataStaticsTaskquestion> taskquestion;

        public DataStaticsTaskInfo getTaskInfo() {
            return taskInfo;
        }

        public void setTaskInfo(DataStaticsTaskInfo taskInfo) {
            this.taskInfo = taskInfo;
        }

        public DataStaticsSituation getSituation() {
            return situation;
        }

        public void setSituation(DataStaticsSituation situation) {
            this.situation = situation;
        }

        public List<DataStaticsTaskquestionInfo> getTaskquestionInfo() {
            return taskquestionInfo;
        }

        public void setTaskquestionInfo(List<DataStaticsTaskquestionInfo> taskquestionInfo) {
            this.taskquestionInfo = taskquestionInfo;
        }

        public List<DataStaticsTaskquestion> getTaskquestion() {
            return taskquestion;
        }

        public void setTaskquestion(List<DataStaticsTaskquestion> taskquestion) {
            this.taskquestion = taskquestion;
        }

        public static class DataStaticsTaskInfo {
            /**
             * taskName : 小辉哥火锅
             * accept_begin_date : {"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4}
             * task_end_date : {"date":1,"hours":23,"seconds":59,"month":11,"nanos":0,"timezoneOffset":-480,"year":117,"minutes":59,"time":1512143999000,"day":5}
             * task_begin_date : {"date":1,"hours":0,"seconds":0,"month":8,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":0,"time":1472659200000,"day":4}
             * accept_end_date : {"date":31,"hours":23,"seconds":59,"month":9,"nanos":0,"timezoneOffset":-480,"year":116,"minutes":59,"time":1477929599000,"day":1}
             */

            private String taskName;
            private AcceptBeginDateBean accept_begin_date;
            private TaskEndDateBean task_end_date;
            private TaskBeginDateBean task_begin_date;
            private AcceptEndDateBean accept_end_date;

            public String getTaskName() {
                return taskName;
            }

            public void setTaskName(String taskName) {
                this.taskName = taskName;
            }

            public AcceptBeginDateBean getAccept_begin_date() {
                return accept_begin_date;
            }

            public void setAccept_begin_date(AcceptBeginDateBean accept_begin_date) {
                this.accept_begin_date = accept_begin_date;
            }

            public TaskEndDateBean getTask_end_date() {
                return task_end_date;
            }

            public void setTask_end_date(TaskEndDateBean task_end_date) {
                this.task_end_date = task_end_date;
            }

            public TaskBeginDateBean getTask_begin_date() {
                return task_begin_date;
            }

            public void setTask_begin_date(TaskBeginDateBean task_begin_date) {
                this.task_begin_date = task_begin_date;
            }

            public AcceptEndDateBean getAccept_end_date() {
                return accept_end_date;
            }

            public void setAccept_end_date(AcceptEndDateBean accept_end_date) {
                this.accept_end_date = accept_end_date;
            }

            public static class AcceptBeginDateBean {
                /**
                 * date : 1
                 * hours : 0
                 * seconds : 0
                 * month : 8
                 * nanos : 0
                 * timezoneOffset : -480
                 * year : 116
                 * minutes : 0
                 * time : 1472659200000
                 * day : 4
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

            public static class TaskEndDateBean {
                /**
                 * date : 1
                 * hours : 23
                 * seconds : 59
                 * month : 11
                 * nanos : 0
                 * timezoneOffset : -480
                 * year : 117
                 * minutes : 59
                 * time : 1512143999000
                 * day : 5
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

            public static class TaskBeginDateBean {
                /**
                 * date : 1
                 * hours : 0
                 * seconds : 0
                 * month : 8
                 * nanos : 0
                 * timezoneOffset : -480
                 * year : 116
                 * minutes : 0
                 * time : 1472659200000
                 * day : 4
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

            public static class AcceptEndDateBean {
                /**
                 * date : 31
                 * hours : 23
                 * seconds : 59
                 * month : 9
                 * nanos : 0
                 * timezoneOffset : -480
                 * year : 116
                 * minutes : 59
                 * time : 1477929599000
                 * day : 1
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

        public static class DataStaticsSituation {
            /**
             * totalNum : 1100
             * submitNum : 33
             * receiveNum : 33
             * completedNum : 32
             */

            private int totalNum;
            private int submitNum;
            private int receiveNum;
            private int completedNum;

            public int getTotalNum() {
                return totalNum;
            }

            public void setTotalNum(int totalNum) {
                this.totalNum = totalNum;
            }

            public int getSubmitNum() {
                return submitNum;
            }

            public void setSubmitNum(int submitNum) {
                this.submitNum = submitNum;
            }

            public int getReceiveNum() {
                return receiveNum;
            }

            public void setReceiveNum(int receiveNum) {
                this.receiveNum = receiveNum;
            }

            public int getCompletedNum() {
                return completedNum;
            }

            public void setCompletedNum(int completedNum) {
                this.completedNum = completedNum;
            }
        }

        public static class DataStaticsTaskquestionInfo {
            /**
             * num : 2
             * oContent : 是
             * oId : 1
             * bId : 1
             * qId : 1
             * oSort : 1
             */

            private int num;
            private String oContent;
            private int oId;
            private int bId;
            private int qId;
            private int oSort;

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getOContent() {
                return oContent;
            }

            public void setOContent(String oContent) {
                this.oContent = oContent;
            }

            public int getOId() {
                return oId;
            }

            public void setOId(int oId) {
                this.oId = oId;
            }

            public int getBId() {
                return bId;
            }

            public void setBId(int bId) {
                this.bId = bId;
            }

            public int getQId() {
                return qId;
            }

            public void setQId(int qId) {
                this.qId = qId;
            }

            public int getOSort() {
                return oSort;
            }

            public void setOSort(int oSort) {
                this.oSort = oSort;
            }
        }

        public static class DataStaticsTaskquestion {
            /**
             * qTitle : 在限定范围内，是否摇到了汉堡王的活动链接？
             * type : radio
             * qId : 1
             */

            private String qTitle;
            private String type;
            private int qId;

            public String getQTitle() {
                return qTitle;
            }

            public void setQTitle(String qTitle) {
                this.qTitle = qTitle;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getQId() {
                return qId;
            }

            public void setQId(int qId) {
                this.qId = qId;
            }
        }
    }
}
