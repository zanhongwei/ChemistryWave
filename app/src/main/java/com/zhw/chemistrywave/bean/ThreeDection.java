package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/1/30.
 */

public class ThreeDection {

    /**
     * code : 0
     * data : {"totalCount":1,"list":[{"detect_id":1,"de_name":"1","submit_time":"2017-12-25 11:29:07","detect_time":null,"finish_time":null,"de_state":null,"result_path":null,"detect_item":null,"consignor":null,"mobile":null,"corporation":null,"user_id":1,"total_fee":null}],"pageSize":10000,"currPage":1,"totalPage":1}
     */

    private int code;
    private DataBean data;

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
         * list : [{"detect_id":1,"de_name":"1","submit_time":"2017-12-25 11:29:07","detect_time":null,"finish_time":null,"de_state":null,"result_path":null,"detect_item":null,"consignor":null,"mobile":null,"corporation":null,"user_id":1,"total_fee":null}]
         * pageSize : 10000
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
            @Override
            public String toString() {
                return "ListBean{" +
                        "detect_id=" + detect_id +
                        ", de_name='" + de_name + '\'' +
                        ", submit_time='" + submit_time + '\'' +
                        ", detect_time=" + detect_time +
                        ", finish_time=" + finish_time +
                        ", de_state=" + de_state +
                        ", result_path=" + result_path +
                        ", detect_item=" + detect_item +
                        ", consignor=" + consignor +
                        ", mobile=" + mobile +
                        ", corporation=" + corporation +
                        ", user_id=" + user_id +
                        ", total_fee=" + total_fee +
                        '}';
            }

            /**
             * detect_id : 1
             * de_name : 1
             * submit_time : 2017-12-25 11:29:07
             * detect_time : null
             * finish_time : null
             * de_state : null
             * result_path : null
             * detect_item : null
             * consignor : null
             * mobile : null
             * corporation : null
             * user_id : 1
             * total_fee : null
             */

            private int detect_id;
            private String de_name;
            private String submit_time;
            private Object detect_time;
            private Object finish_time;
            private Object de_state;
            private Object result_path;
            private Object detect_item;
            private Object consignor;
            private Object mobile;
            private Object corporation;
            private int user_id;
            private Object total_fee;

            public int getDetect_id() {
                return detect_id;
            }

            public void setDetect_id(int detect_id) {
                this.detect_id = detect_id;
            }

            public String getDe_name() {
                return de_name;
            }

            public void setDe_name(String de_name) {
                this.de_name = de_name;
            }

            public String getSubmit_time() {
                return submit_time;
            }

            public void setSubmit_time(String submit_time) {
                this.submit_time = submit_time;
            }

            public Object getDetect_time() {
                return detect_time;
            }

            public void setDetect_time(Object detect_time) {
                this.detect_time = detect_time;
            }

            public Object getFinish_time() {
                return finish_time;
            }

            public void setFinish_time(Object finish_time) {
                this.finish_time = finish_time;
            }

            public Object getDe_state() {
                return de_state;
            }

            public void setDe_state(Object de_state) {
                this.de_state = de_state;
            }

            public Object getResult_path() {
                return result_path;
            }

            public void setResult_path(Object result_path) {
                this.result_path = result_path;
            }

            public Object getDetect_item() {
                return detect_item;
            }

            public void setDetect_item(Object detect_item) {
                this.detect_item = detect_item;
            }

            public Object getConsignor() {
                return consignor;
            }

            public void setConsignor(Object consignor) {
                this.consignor = consignor;
            }

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public Object getCorporation() {
                return corporation;
            }

            public void setCorporation(Object corporation) {
                this.corporation = corporation;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public Object getTotal_fee() {
                return total_fee;
            }

            public void setTotal_fee(Object total_fee) {
                this.total_fee = total_fee;
            }
        }
    }
}
