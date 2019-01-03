package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by zhw on 2018/5/26.
 */

public class MyAddress {

    /**
     * msg : success
     * code : 0
     * data : {"totalCount":1,"list":[{"addr_id":57,"consignee":"222","province":null,"city":null,"area":null,"detail":"8888","mobile":"777","landline":null,"is_default":true,"user_id":122}],"pageSize":1000,"currPage":1,"totalPage":1}
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
         * totalCount : 1
         * list : [{"addr_id":57,"consignee":"222","province":null,"city":null,"area":null,"detail":"8888","mobile":"777","landline":null,"is_default":true,"user_id":122}]
         * pageSize : 1000
         * currPage : 1
         * totalPage : 1
         */

        private int totalCount;
        private int pageSize;
        private int currPage;
        private int totalPage;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrPage() {
            return currPage;
        }

        public void setCurrPage(int currPage) {
            this.currPage = currPage;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * addr_id : 57
             * consignee : 222
             * province : null
             * city : null
             * area : null
             * detail : 8888
             * mobile : 777
             * landline : null
             * is_default : true
             * user_id : 122
             */

            private int addr_id;
            private String consignee;
            private Object province;
            private Object city;
            private Object area;
            private String detail;
            private String mobile;
            private Object landline;
            private boolean is_default;
            private int user_id;

            public int getAddr_id() {
                return addr_id;
            }

            public void setAddr_id(int addr_id) {
                this.addr_id = addr_id;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public Object getProvince() {
                return province;
            }

            public void setProvince(Object province) {
                this.province = province;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getArea() {
                return area;
            }

            public void setArea(Object area) {
                this.area = area;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public Object getLandline() {
                return landline;
            }

            public void setLandline(Object landline) {
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
}
