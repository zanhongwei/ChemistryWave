package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/1/30.
 */

public class ThreeDectionS {


    /**
     * msg : success
     * code : 0
     * data : [{"super_id":17,"super_url":"upload/null/20190103164051151.png","super_text":"1263213","super_time":"2019-01-03 16:41:03","order_id":"153275916749254a9"},{"super_id":18,"super_url":"upload/null/20190103164127919.png","super_text":"1331","super_time":"2019-01-03 16:41:29","order_id":"153275916749254a9"}]
     * count : 2
     */

    private String msg;
    private int code;
    private int count;
    private List<DataBean> data;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * super_id : 17
         * super_url : upload/null/20190103164051151.png
         * super_text : 1263213
         * super_time : 2019-01-03 16:41:03
         * order_id : 153275916749254a9
         */

        private int super_id;
        private String super_url;
        private String super_text;
        private String super_time;
        private String order_id;

        public int getSuper_id() {
            return super_id;
        }

        public void setSuper_id(int super_id) {
            this.super_id = super_id;
        }

        public String getSuper_url() {
            return super_url;
        }

        public void setSuper_url(String super_url) {
            this.super_url = super_url;
        }

        public String getSuper_text() {
            return super_text;
        }

        public void setSuper_text(String super_text) {
            this.super_text = super_text;
        }

        public String getSuper_time() {
            return super_time;
        }

        public void setSuper_time(String super_time) {
            this.super_time = super_time;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
