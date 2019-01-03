package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/1/31.
 */

public class Kuaixun {

    /**
     * msg : success
     * code : 0
     * data : {"totalCount":2,"list":[{"news_id":4,"news_type":"2","news_title":"好渴","news_content":"我渴了，想喝水","create_time":"2018-01-30 14:06:29","news_status":"0"},{"news_id":3,"news_type":"2","news_title":"好累","news_content":"我累了，想休息","create_time":"2018-01-30 14:06:27","news_status":"0"}],"pageSize":1000000,"currPage":1,"totalPage":1}
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
         * list : [{"news_id":4,"news_type":"2","news_title":"好渴","news_content":"我渴了，想喝水","create_time":"2018-01-30 14:06:29","news_status":"0"},{"news_id":3,"news_type":"2","news_title":"好累","news_content":"我累了，想休息","create_time":"2018-01-30 14:06:27","news_status":"0"}]
         * pageSize : 1000000
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
             * news_id : 4
             * news_type : 2
             * news_title : 好渴
             * news_content : 我渴了，想喝水
             * create_time : 2018-01-30 14:06:29
             * news_status : 0
             * img_src
             */

            private int news_id;
            private String news_type;
            private String news_title;
            private String news_content;
            private String create_time;
            private String news_status;
            private String read_times;
            private String img_src;

            public int getNews_id() {
                return news_id;
            }

            public void setNews_id(int news_id) {
                this.news_id = news_id;
            }

            public String getNews_type() {
                return news_type;
            }

            public void setNews_type(String news_type) {
                this.news_type = news_type;
            }

            public String getNews_title() {
                return news_title;
            }

            public void setNews_title(String news_title) {
                this.news_title = news_title;
            }

            public String getNews_content() {
                return news_content;
            }

            public void setNews_content(String news_content) {
                this.news_content = news_content;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getNews_status() {
                return news_status;
            }

            public void setNews_status(String news_status) {
                this.news_status = news_status;
            }

            public String getRead_times() {
                return read_times;
            }

            public void setRead_times(String read_times) {
                this.read_times = read_times;
            }

            public String getImg_src() {
                return img_src;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }
        }


    }
}
