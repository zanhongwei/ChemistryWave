package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2017/12/9.
 */

public class SySMsg {

    /**
     * msg : success
     * code : 0
     * data : {"totalCount":3,"list":[{"msg_id":1,"msg_content":"驱蚊器","msg_time":"","msg_publisher":"去问问"},{"msg_id":2,"msg_content":"156","msg_time":"","msg_publisher":"1313"},{"msg_id":3,"msg_content":"545","msg_time":null,"msg_publisher":null}],"pageSize":10,"currPage":1,"totalPage":1}
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
         * totalCount : 3
         * list : [{"msg_id":1,"msg_content":"驱蚊器","msg_time":"","msg_publisher":"去问问"},{"msg_id":2,"msg_content":"156","msg_time":"","msg_publisher":"1313"},{"msg_id":3,"msg_content":"545","msg_time":null,"msg_publisher":null}]
         * pageSize : 10
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
             * msg_id : 1
             * msg_content : 驱蚊器
             * msg_time :
             * msg_publisher : 去问问
             */

            private int msg_id;
            private String msg_content;
            private String msg_time;
            private String msg_publisher;

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
            }

            public String getMsg_content() {
                return msg_content;
            }

            public void setMsg_content(String msg_content) {
                this.msg_content = msg_content;
            }

            public String getMsg_time() {
                return msg_time;
            }

            public void setMsg_time(String msg_time) {
                this.msg_time = msg_time;
            }

            public String getMsg_publisher() {
                return msg_publisher;
            }

            public void setMsg_publisher(String msg_publisher) {
                this.msg_publisher = msg_publisher;
            }
        }
    }
}
