package com.zhw.chemistrywave.bean;

/**
 * Created by axehome on 2018/1/30.
 */

public class ThreeDection {


    /**
     * code : 0
     * data : {"check_id":14,"uv":null,"color":null,"sample":null,"report":null,"video":null,"bulk":null,"check_url":"upload/null/20190103163611017.png","order_id":"153275916749254a9"}
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
         * check_id : 14
         * uv : null
         * color : null
         * sample : null
         * report : null
         * video : null
         * bulk : null
         * check_url : upload/null/20190103163611017.png
         * order_id : 153275916749254a9
         */

        private int check_id;
        private Object uv;
        private Object color;
        private Object sample;
        private Object report;
        private Object video;
        private Object bulk;
        private String check_url;
        private String order_id;

        public int getCheck_id() {
            return check_id;
        }

        public void setCheck_id(int check_id) {
            this.check_id = check_id;
        }

        public Object getUv() {
            return uv;
        }

        public void setUv(Object uv) {
            this.uv = uv;
        }

        public Object getColor() {
            return color;
        }

        public void setColor(Object color) {
            this.color = color;
        }

        public Object getSample() {
            return sample;
        }

        public void setSample(Object sample) {
            this.sample = sample;
        }

        public Object getReport() {
            return report;
        }

        public void setReport(Object report) {
            this.report = report;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public Object getBulk() {
            return bulk;
        }

        public void setBulk(Object bulk) {
            this.bulk = bulk;
        }

        public String getCheck_url() {
            return check_url;
        }

        public void setCheck_url(String check_url) {
            this.check_url = check_url;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }
    }
}
