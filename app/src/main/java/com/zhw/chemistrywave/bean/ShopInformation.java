package com.zhw.chemistrywave.bean;

/**
 * Created by zhw on 2018/7/26.
 */

public class ShopInformation {


    /**
     * msg : success
     * code : 0
     * data : {"shop_id":56,"user_id":125,"shop_name":"啊啊啊","com_name":null,"com_addr":null,"contact_name":"双十一","contact_mobile":"126","com_logo":"upload/125/20180725154334531.jpg","com_license":null,"per_card":"1265","per_addr":"佛了了","domestic":null,"create_time":"2018-07-25 15:43:35","shop_state":"1","rank":null,"total_volumes":0,"orderBeans":null,"merchandiseBeans":null,"goodsMallBean":null,"cartBeans":null,"orderItemBeans":null}
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
         * shop_id : 56
         * user_id : 125
         * shop_name : 啊啊啊
         * com_name : null
         * com_addr : null
         * contact_name : 双十一
         * contact_mobile : 126
         * com_logo : upload/125/20180725154334531.jpg
         * com_license : null
         * per_card : 1265
         * per_addr : 佛了了
         * domestic : null
         * create_time : 2018-07-25 15:43:35
         * shop_state : 1
         * rank : null
         * total_volumes : 0
         * orderBeans : null
         * merchandiseBeans : null
         * goodsMallBean : null
         * cartBeans : null
         * orderItemBeans : null
         */

        private int shop_id;
        private int user_id;
        private String shop_name;
        private String com_name;
        private String com_addr;
        private String contact_name;
        private String contact_mobile;
        private String com_logo;
        private String com_license;
        private String per_card;
        private String per_addr;
        private String domestic;
        private String create_time;
        private String shop_state;
        private String rank;
        private int total_volumes;
        private String orderBeans;
        private String merchandiseBeans;
        private String goodsMallBean;
        private String cartBeans;
        private String orderItemBeans;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public String getCom_addr() {
            return com_addr;
        }

        public void setCom_addr(String com_addr) {
            this.com_addr = com_addr;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getContact_mobile() {
            return contact_mobile;
        }

        public void setContact_mobile(String contact_mobile) {
            this.contact_mobile = contact_mobile;
        }

        public String getCom_logo() {
            return com_logo;
        }

        public void setCom_logo(String com_logo) {
            this.com_logo = com_logo;
        }

        public String getCom_license() {
            return com_license;
        }

        public void setCom_license(String com_license) {
            this.com_license = com_license;
        }

        public String getPer_card() {
            return per_card;
        }

        public void setPer_card(String per_card) {
            this.per_card = per_card;
        }

        public String getPer_addr() {
            return per_addr;
        }

        public void setPer_addr(String per_addr) {
            this.per_addr = per_addr;
        }

        public String getDomestic() {
            return domestic;
        }

        public void setDomestic(String domestic) {
            this.domestic = domestic;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getShop_state() {
            return shop_state;
        }

        public void setShop_state(String shop_state) {
            this.shop_state = shop_state;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public int getTotal_volumes() {
            return total_volumes;
        }

        public void setTotal_volumes(int total_volumes) {
            this.total_volumes = total_volumes;
        }

        public String getOrderBeans() {
            return orderBeans;
        }

        public void setOrderBeans(String orderBeans) {
            this.orderBeans = orderBeans;
        }

        public String getMerchandiseBeans() {
            return merchandiseBeans;
        }

        public void setMerchandiseBeans(String merchandiseBeans) {
            this.merchandiseBeans = merchandiseBeans;
        }

        public String getGoodsMallBean() {
            return goodsMallBean;
        }

        public void setGoodsMallBean(String goodsMallBean) {
            this.goodsMallBean = goodsMallBean;
        }

        public String getCartBeans() {
            return cartBeans;
        }

        public void setCartBeans(String cartBeans) {
            this.cartBeans = cartBeans;
        }

        public String getOrderItemBeans() {
            return orderItemBeans;
        }

        public void setOrderItemBeans(String orderItemBeans) {
            this.orderItemBeans = orderItemBeans;
        }
    }
}
