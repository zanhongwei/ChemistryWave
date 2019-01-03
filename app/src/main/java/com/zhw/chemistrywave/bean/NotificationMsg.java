package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2017/12/9.
 */

public class NotificationMsg {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":4,"list":[{"msg_id":677,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:57:29"},{"msg_id":676,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:57:22"},{"msg_id":675,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:55:44"},{"msg_id":665,"user_id":1,"msg_type":null,"source_id":null,"content":null,"create_time":null}],"pageSize":10,"currPage":1,"totalPage":1}
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
         * totalCount : 4
         * list : [{"msg_id":677,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:57:29"},{"msg_id":676,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:57:22"},{"msg_id":675,"user_id":1,"msg_type":"2","source_id":"1513996930620383f","content":"您参与的水泥已经中标!","create_time":"2017-12-23 16:55:44"},{"msg_id":665,"user_id":1,"msg_type":null,"source_id":null,"content":null,"create_time":null}]
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
             * msg_id : 677
             * user_id : 1
             * msg_type : 2
             * source_id : 1513996930620383f
             * content : 您参与的水泥已经中标!
             * create_time : 2017-12-23 16:57:29
             */

            private int msg_id;
            private int user_id;
            private String msg_type;
            private String source_id;
            private String content;
            private String create_time;

            public int getMsg_id() {
                return msg_id;
            }

            public void setMsg_id(int msg_id) {
                this.msg_id = msg_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getMsg_type() {
                return msg_type;
            }

            public void setMsg_type(String msg_type) {
                this.msg_type = msg_type;
            }

            public String getSource_id() {
                return source_id;
            }

            public void setSource_id(String source_id) {
                this.source_id = source_id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
