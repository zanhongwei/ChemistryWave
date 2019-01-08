package com.zhw.chemistrywave.bean;

/**
 * Created by Axehome_Mr.z on 2019/1/7 11:37
 * $Describe
 */
public class AddressBean {

    /**
     * msg : success
     * code : 0
     * data : {"addr_id":80,"consignee":"zhw","province":null,"city":null,"area":null,"detail":"123456","mobile":"15122947309","landline":null,"is_default":true,"user_id":266}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr_id : 80
         * consignee : zhw
         * province : null
         * city : null
         * area : null
         * detail : 123456
         * mobile : 15122947309
         * landline : null
         * is_default : true
         * user_id : 266
         */

        private int addr_id;
        private String consignee;
        private String province;
        private String city;
        private String area;
        private String detail;
        private String mobile;
        private String landline;
        private boolean is_default;
        private int user_id;

        public int getAddr_id() {
            return addr_id;
        }

        public void setAddr_id(int addr_id) {
            this.addr_id = addr_id;
        }

        public String getConsignee() {
            return consignee == null ? "" : consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getProvince() {
            return province == null ? "" : province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city == null ? "" : city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area == null ? "" : area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDetail() {
            return detail == null ? "" : detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getLandline() {
            return landline == null ? "" : landline;
        }

        public void setLandline(String landline) {
            this.landline = landline;
        }

        public boolean isIs_default() {
            return is_default;
        }

        public void setIs_default(boolean is_default) {
            this.is_default = is_default;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
