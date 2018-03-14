package com.huami.merchant.bean;

import java.util.List;

/**
 * Created by henry on 2018/2/7.
 */

public class AlreadyService {

    /**
     * code : 0
     * msg : 成功
     * data : [{"amount":10,"merchantId":1,"price":10,"num":1,"intro":"为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。","packageId":3,"name":"查询经纬度","id":1,"create_date":{"date":6,"hours":18,"seconds":42,"month":1,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":27,"time":1517912862000,"day":2}},{"amount":30,"merchantId":1,"price":10,"num":3,"intro":"为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。","packageId":3,"name":"查询经纬度","id":2,"create_date":{"date":6,"hours":18,"seconds":29,"month":1,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":58,"time":1517914709000,"day":2}},{"amount":400,"merchantId":1,"price":200,"num":2,"intro":"协助您完成复杂精细的企业认证过程","packageId":1,"name":"协助认证","id":7,"create_date":{"date":7,"hours":9,"seconds":27,"month":1,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":35,"time":1517967327000,"day":3}}]
     */

    private int code;
    private String msg;
    private List<AlreadyServiceData> data;

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

    public List<AlreadyServiceData> getData() {
        return data;
    }

    public void setData(List<AlreadyServiceData> data) {
        this.data = data;
    }

    public static class AlreadyServiceData {
        /**
         * amount : 10
         * merchantId : 1
         * price : 10
         * num : 1
         * intro : 为任务点位位查询经纬度信息，保证任务点位位能够顺利使用。
         * packageId : 3
         * name : 查询经纬度
         * id : 1
         * create_date : {"date":6,"hours":18,"seconds":42,"month":1,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":27,"time":1517912862000,"day":2}
         */

        private int amount;
        private int merchantId;
        private int price;
        private int num;
        private String intro;
        private int packageId;
        private String name;
        private int id;
        private AlreadyServiceCreate create_date;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getPackageId() {
            return packageId;
        }

        public void setPackageId(int packageId) {
            this.packageId = packageId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public AlreadyServiceCreate getCreate_date() {
            return create_date;
        }

        public void setCreate_date(AlreadyServiceCreate create_date) {
            this.create_date = create_date;
        }

        public static class AlreadyServiceCreate {
            /**
             * date : 6
             * hours : 18
             * seconds : 42
             * month : 1
             * nanos : 0
             * timezoneOffset : -480
             * year : 118
             * minutes : 27
             * time : 1517912862000
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
}
