package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2017/12/14.
 */

public class PingShiGouMai {

    /**
     * msg : success
     * code : 0
     * data : [{"product_id":"PARCEL15129803150008c4f","product_name":"13","create_time":"2017-12-11 16:18:35","product_type":"1"},{"product_id":"PARCEL15129804188611aa5","product_name":"16","create_time":"2017-12-11 16:20:18","product_type":"1"},{"product_id":"PARCEL1512980346881639b","product_name":"15","create_time":"2017-12-11 16:19:06","product_type":"1"},{"product_id":"6","product_name":"6","create_time":"2017-12-06 14:48:04","product_type":"1"},{"product_id":"4","product_name":"4","create_time":"2017-12-06 14:48:04","product_type":"4"},{"product_id":"5","product_name":"5","create_time":"2017-12-06 14:48:04","product_type":"5"},{"product_id":"PARCEL151298033306127ef","product_name":"14","create_time":"2017-12-11 16:18:53","product_type":"1"},{"product_id":"PARCEL151298028380142ac","product_name":"11","create_time":"2017-12-11 16:18:03","product_type":"1"},{"product_id":"7","product_name":"7","create_time":null,"product_type":null},{"product_id":"PARCEL1513218772930ce14","product_name":"1","create_time":"2017-12-14 10:32:52","product_type":"1"},{"product_id":"3","product_name":"3","create_time":"2017-12-06 14:48:04","product_type":"3"},{"product_id":"PARCEL15132183679512d81","product_name":"1","create_time":"2017-12-14 10:26:07","product_type":"1"}]
     */

    private String msg;
    private int code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * product_id : PARCEL15129803150008c4f
         * product_name : 13
         * create_time : 2017-12-11 16:18:35
         * product_type : 1
         */

        private String shopIntent_id;
        private String product_name;
        private String create_time;
        private String product_type;
        private String domestic;

        public String getShopIntent_id() {
            return shopIntent_id;
        }

        public void setShopIntent_id(String shopIntent_id) {
            this.shopIntent_id = shopIntent_id;
        }

        public String getDomestic() {
            return domestic;
        }

        public void setDomestic(String domestic) {
            this.domestic = domestic;
        }



        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getProduct_type() {
            return product_type;
        }

        public void setProduct_type(String product_type) {
            this.product_type = product_type;
        }
    }
}
