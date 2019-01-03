package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by zhw on 2018/6/8.
 */

public class TradeNews {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":2,"list":[{"news_id":2,"news_type":"2","news_title":"Chemsigma International Co.,Ltd","news_content":"<p style=\"text-align: center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国好声音，呵呵呵<br/><\/p><p style=\"text-align: center;\"><img src=\"upload/ueditor/20180323110410855.jpg\" title=\"0855.jpg\" alt=\"0855.jpg\"/><\/p>","create_time":"2018-01-30 13:59:20","news_status":"0","read_times":51,"img_src":"upload/1/20180228115357425.jpg"},{"news_id":1,"news_type":"2","news_title":"Chemsigma International Co.,Ltd.\t","news_content":"预测\u2014\u2014下周丙烯酸丁酯僵持整理的概率较大。下游终端需求支撑力度一般，低端价格上调后，市场出货一般，华南区域有可能不涨操作，但幅度有限。原料方面高位盘整，支撑继续增强力度有限。因此，预计，下周丙烯酸及丁酯商谈重心延续僵持整理，上下两难的概率较大。","create_time":"2018-01-30 13:53:41","news_status":"0","read_times":8,"img_src":"upload/1/20180228115357425.jpg"}],"pageSize":1000,"currPage":1,"totalPage":1}
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
         * list : [{"news_id":2,"news_type":"2","news_title":"Chemsigma International Co.,Ltd","news_content":"<p style=\"text-align: center;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国好声音，呵呵呵<br/><\/p><p style=\"text-align: center;\"><img src=\"upload/ueditor/20180323110410855.jpg\" title=\"0855.jpg\" alt=\"0855.jpg\"/><\/p>","create_time":"2018-01-30 13:59:20","news_status":"0","read_times":51,"img_src":"upload/1/20180228115357425.jpg"},{"news_id":1,"news_type":"2","news_title":"Chemsigma International Co.,Ltd.\t","news_content":"预测\u2014\u2014下周丙烯酸丁酯僵持整理的概率较大。下游终端需求支撑力度一般，低端价格上调后，市场出货一般，华南区域有可能不涨操作，但幅度有限。原料方面高位盘整，支撑继续增强力度有限。因此，预计，下周丙烯酸及丁酯商谈重心延续僵持整理，上下两难的概率较大。","create_time":"2018-01-30 13:53:41","news_status":"0","read_times":8,"img_src":"upload/1/20180228115357425.jpg"}]
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
             * news_id : 2
             * news_type : 2
             * news_title : Chemsigma International Co.,Ltd
             * news_content : <p style="text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;中国好声音，呵呵呵<br/></p><p style="text-align: center;"><img src="upload/ueditor/20180323110410855.jpg" title="0855.jpg" alt="0855.jpg"/></p>
             * create_time : 2018-01-30 13:59:20
             * news_status : 0
             * read_times : 51
             * img_src : upload/1/20180228115357425.jpg
             */

            private int news_id;
            private String news_type;
            private String news_title;
            private String news_content;
            private String create_time;
            private String news_status;
            private int read_times;
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

            public int getRead_times() {
                return read_times;
            }

            public void setRead_times(int read_times) {
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
