package com.zhw.chemistrywave.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by axehome on 2018/1/15.
 */

public class Order implements Serializable{


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":1,"list":[{"order_id":"15277493037032b6c","buyer_id":53,"seller_id":0,"total_money":null,"addr_id":28,"supv_id":null,"place_time":"2018-05-31 14:48:23","pay_time":null,"done_time":null,"transport_corporation":null,"transport_number":null,"payment_opt":"offline线下","package_opt":"1","color_light":"0.6","color_power":"1","goods_id":29,"status":"2","goods_name":" propane-1,2-diol","goods_price":"900.00","goods_num":1,"contract":"合同","goods_unit":"g","consignee":"2","com_name":null,"product_picture":"upload/14/20180308154320603.png","user_name":"nul概率统计"}],"pageSize":10000,"currPage":1,"totalPage":1}
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

    public static class DataBean implements Serializable{
        /**
         * totalCount : 1
         * list : [{"order_id":"15277493037032b6c","buyer_id":53,"seller_id":0,"total_money":null,"addr_id":28,"supv_id":null,"place_time":"2018-05-31 14:48:23","pay_time":null,"done_time":null,"transport_corporation":null,"transport_number":null,"payment_opt":"offline线下","package_opt":"1","color_light":"0.6","color_power":"1","goods_id":29,"status":"2","goods_name":" propane-1,2-diol","goods_price":"900.00","goods_num":1,"contract":"合同","goods_unit":"g","consignee":"2","com_name":null,"product_picture":"upload/14/20180308154320603.png","user_name":"nul概率统计"}]
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

        public static class ListBean implements Serializable{
            /**
             * order_id : 15277493037032b6c
             * buyer_id : 53
             * seller_id : 0
             * total_money : null
             * addr_id : 28
             * supv_id : null
             * place_time : 2018-05-31 14:48:23
             * pay_time : null
             * done_time : null
             * transport_corporation : null
             * transport_number : null
             * payment_opt : offline线下
             * package_opt : 1
             * color_light : 0.6
             * color_power : 1
             * goods_id : 29
             * status : 2
             * goods_name :  propane-1,2-diol
             * goods_price : 900.00
             * goods_num : 1
             * contract : 合同
             * goods_unit : g
             * consignee : 2
             * com_name : null
             * product_picture : upload/14/20180308154320603.png
             * user_name : nul概率统计
             */

            private String order_id;
            private int buyer_id;
            private int seller_id;
            private String total_money;
            private int addr_id;
            private String supv_id;
            private String place_time;
            private String pay_time;
            private String done_time;
            private String transport_corporation;
            private String transport_number;
            private String payment_opt;
            private String package_opt;
            private String color_light;
            private String color_power;
            private int goods_id;
            private String status;
            private String goods_name;
            private double goods_price;
            private int goods_num;
            private String contract;
            private String goods_unit;
            private String consignee;
            private String com_name;
            private String product_picture;
            private String user_name;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getBuyer_id() {
                return buyer_id;
            }

            public void setBuyer_id(int buyer_id) {
                this.buyer_id = buyer_id;
            }

            public int getSeller_id() {
                return seller_id;
            }

            public void setSeller_id(int seller_id) {
                this.seller_id = seller_id;
            }

            public String getTotal_money() {
                return total_money;
            }

            public void setTotal_money(String total_money) {
                this.total_money = total_money;
            }

            public int getAddr_id() {
                return addr_id;
            }

            public void setAddr_id(int addr_id) {
                this.addr_id = addr_id;
            }

            public String getSupv_id() {
                return supv_id;
            }

            public void setSupv_id(String supv_id) {
                this.supv_id = supv_id;
            }

            public String getPlace_time() {
                return place_time;
            }

            public void setPlace_time(String place_time) {
                this.place_time = place_time;
            }

            public String getPay_time() {
                return pay_time;
            }

            public void setPay_time(String pay_time) {
                this.pay_time = pay_time;
            }

            public String getDone_time() {
                return done_time;
            }

            public void setDone_time(String done_time) {
                this.done_time = done_time;
            }

            public String getTransport_corporation() {
                return transport_corporation;
            }

            public void setTransport_corporation(String transport_corporation) {
                this.transport_corporation = transport_corporation;
            }

            public String getTransport_number() {
                return transport_number;
            }

            public void setTransport_number(String transport_number) {
                this.transport_number = transport_number;
            }

            public String getPayment_opt() {
                return payment_opt;
            }

            public void setPayment_opt(String payment_opt) {
                this.payment_opt = payment_opt;
            }

            public String getPackage_opt() {
                return package_opt;
            }

            public void setPackage_opt(String package_opt) {
                this.package_opt = package_opt;
            }

            public String getColor_light() {
                return color_light;
            }

            public void setColor_light(String color_light) {
                this.color_light = color_light;
            }

            public String getColor_power() {
                return color_power;
            }

            public void setColor_power(String color_power) {
                this.color_power = color_power;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public double getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(double goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getContract() {
                return contract;
            }

            public void setContract(String contract) {
                this.contract = contract;
            }

            public String getGoods_unit() {
                return goods_unit;
            }

            public void setGoods_unit(String goods_unit) {
                this.goods_unit = goods_unit;
            }

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getCom_name() {
                return com_name;
            }

            public void setCom_name(String com_name) {
                this.com_name = com_name;
            }

            public String getProduct_picture() {
                return product_picture;
            }

            public void setProduct_picture(String product_picture) {
                this.product_picture = product_picture;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            @Override
            public String toString() {
                return super.toString();
            }
        }
    }
}
