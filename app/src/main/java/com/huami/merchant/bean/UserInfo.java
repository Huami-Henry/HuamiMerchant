package com.huami.merchant.bean;

/**
 * Created by henry on 2018/1/23.
 */

public class UserInfo {

    /**
     * code : 0
     * msg : 成功
     * data : {"userInfo":{"balance":37231,"name":"汉堡王(中国)投资有限公司","icon":"http://media.darenlaiye.com/darenlaiye/upload/merchant/1/logo.png","merchar_info":"<p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">我们是汉堡王<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王源自美国，始于1954年，风靡全球100多个国家，截止至2015年底，拥有超过15000家餐厅。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王使用真正的火烤工艺，使得每一块肉饼鲜嫩多汁，口感不腻，并散发独特的烤肉香味。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">真正火烤，料多味足，现点现做，真材实料。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王，美味就是王道！<\/span><\/p><p>    <br/><\/p>","merchar_desc":"我是一个大汉堡,你吃不了我嘿嘿嘿嘿","certification_status_type":"已认证","certification_status":1}}
     */

    private int code;
    private String msg;
    private UserData data;

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

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public static class UserData {
        /**
         * userInfo : {"balance":37231,"name":"汉堡王(中国)投资有限公司","icon":"http://media.darenlaiye.com/darenlaiye/upload/merchant/1/logo.png","merchar_info":"<p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">我们是汉堡王<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王源自美国，始于1954年，风靡全球100多个国家，截止至2015年底，拥有超过15000家餐厅。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王使用真正的火烤工艺，使得每一块肉饼鲜嫩多汁，口感不腻，并散发独特的烤肉香味。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">真正火烤，料多味足，现点现做，真材实料。<\/span><\/p><p>    <span style=\"font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;\">汉堡王，美味就是王道！<\/span><\/p><p>    <br/><\/p>","merchar_desc":"我是一个大汉堡,你吃不了我嘿嘿嘿嘿","certification_status_type":"已认证","certification_status":1}
         */

        private UserDetail userInfo;

        public UserDetail getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserDetail userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserDetail {
            /**
             * balance : 37231
             * name : 汉堡王(中国)投资有限公司
             * icon : http://media.darenlaiye.com/darenlaiye/upload/merchant/1/logo.png
             * merchar_info : <p>    <span style="font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;">我们是汉堡王</span></p><p>    <span style="font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;">汉堡王源自美国，始于1954年，风靡全球100多个国家，截止至2015年底，拥有超过15000家餐厅。</span></p><p>    <span style="font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;">汉堡王使用真正的火烤工艺，使得每一块肉饼鲜嫩多汁，口感不腻，并散发独特的烤肉香味。</span></p><p>    <span style="font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;">真正火烤，料多味足，现点现做，真材实料。</span></p><p>    <span style="font-size:16px;font-family:&#39;微软雅黑 Light&#39;,&#39;sans-serif&#39;">汉堡王，美味就是王道！</span></p><p>    <br/></p>
             * merchar_desc : 我是一个大汉堡,你吃不了我嘿嘿嘿嘿
             * certification_status_type : 已认证
             * certification_status : 1
             */

            private int balance;
            private String name;
            private String icon;
            private String merchar_info;
            private String merchar_desc;
            private String certification_status_type;
            private int certification_status;

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getMerchar_info() {
                return merchar_info;
            }

            public void setMerchar_info(String merchar_info) {
                this.merchar_info = merchar_info;
            }

            public String getMerchar_desc() {
                return merchar_desc;
            }

            public void setMerchar_desc(String merchar_desc) {
                this.merchar_desc = merchar_desc;
            }

            public String getCertification_status_type() {
                return certification_status_type;
            }

            public void setCertification_status_type(String certification_status_type) {
                this.certification_status_type = certification_status_type;
            }

            public int getCertification_status() {
                return certification_status;
            }

            public void setCertification_status(int certification_status) {
                this.certification_status = certification_status;
            }
        }
    }
}
