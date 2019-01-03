package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/25.
 */

public class AddressDetailBean {

    /**
     * msg : success
     * code : 0
     * data : {"totalCount":2,"list":[{"addr_id":5,"consignee":"你","province":"贵州省","city":"毕节市","area":"纳雍县","detail":"1","mobile":"1","landline":"1","is_default":"1","user_id":"9a3b91e7-7"},{"addr_id":6,"consignee":"你","province":"贵州省","city":"毕节市","area":"纳雍县","detail":"1","mobile":"1","landline":"1","is_default":"1","user_id":"9a3b91e7-7"}],"pageSize":100,"currPage":1,"totalPage":1}
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
         * totalCount : 2
         * list : [{"addr_id":5,"consignee":"你","province":"贵州省","city":"毕节市","area":"纳雍县","detail":"1","mobile":"1","landline":"1","is_default":"1","user_id":"9a3b91e7-7"},{"addr_id":6,"consignee":"你","province":"贵州省","city":"毕节市","area":"纳雍县","detail":"1","mobile":"1","landline":"1","is_default":"1","user_id":"9a3b91e7-7"}]
         * pageSize : 100
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
             * addr_id : 5
             * consignee : 你
             * province : 贵州省
             * city : 毕节市
             * area : 纳雍县
             * detail : 1
             * mobile : 1
             * landline : 1
             * is_default : 1
             * user_id : 9a3b91e7-7
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
            private String user_id;

            @Override
            public String toString() {
                return "ListBean{" +
                        "addr_id=" + addr_id +
                        ", consignee='" + consignee + '\'' +
                        ", province='" + province + '\'' +
                        ", city='" + city + '\'' +
                        ", area='" + area + '\'' +
                        ", detail='" + detail + '\'' +
                        ", mobile='" + mobile + '\'' +
                        ", landline='" + landline + '\'' +
                        ", is_default='" + is_default + '\'' +
                        ", user_id='" + user_id + '\'' +
                        '}';
            }

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

            public String getUser_id() {
                return user_id == null ? "" : user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }
        }
    }
}
