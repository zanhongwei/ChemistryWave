package com.zhw.chemistrywave.bean;

/**
 * Created by zhw on 2018/5/22.
 */

public class ShopDetail {


    /**
     * msg : success
     * code : 0
     * data : {"shop_id":3,"com_name":"Carbosynth Limited","com_landline":"58536238","com_fax":"58536238","com_qq":"1586324865","com_addr":"was founded in the reign of Xianfeng of Qing Dynasty","com_logo":"upload/14/20180308152430750.png","com_license":"upload/4/21.png","com_env":"upload/11/ab.gif","com_proenv":"upload/4/91.png","user_id":1,"create_time":"2018-03-08 11:21:06","domestic":"yes","shop_state":"2","rank":"1","total_volumes":100,"orderBeans":null,"merchandiseBeans":null,"goodsMallBean":null,"cartBeans":null,"orderItemBeans":null}
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
         * shop_id : 3
         * com_name : Carbosynth Limited
         * com_landline : 58536238
         * com_fax : 58536238
         * com_qq : 1586324865
         * com_addr : was founded in the reign of Xianfeng of Qing Dynasty
         * com_logo : upload/14/20180308152430750.png
         * com_license : upload/4/21.png
         * com_env : upload/11/ab.gif
         * com_proenv : upload/4/91.png
         * user_id : 1
         * create_time : 2018-03-08 11:21:06
         * domestic : yes
         * shop_state : 2
         * rank : 1
         * total_volumes : 100
         * orderBeans : null
         * merchandiseBeans : null
         * goodsMallBean : null
         * cartBeans : null
         * orderItemBeans : null
         */

        private int shop_id;
        private String com_name;
        private String com_landline;
        private String com_fax;
        private String com_qq;
        private String com_addr;
        private String com_logo;
        private String com_license;
        private String com_env;
        private String com_proenv;
        private int user_id;
        private String create_time;
        private String domestic;
        private String shop_state;
        private String rank;
        private int total_volumes;
        private String orderBeans;
        private String merchandiseBeans;
        private String goodsMallBean;
        private String cartBeans;
        private String shop_name;
        private String orderItemBeans;

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getCom_name() {
            return com_name;
        }

        public void setCom_name(String com_name) {
            this.com_name = com_name;
        }

        public String getCom_landline() {
            return com_landline;
        }

        public void setCom_landline(String com_landline) {
            this.com_landline = com_landline;
        }

        public String getCom_fax() {
            return com_fax;
        }

        public void setCom_fax(String com_fax) {
            this.com_fax = com_fax;
        }

        public String getCom_qq() {
            return com_qq;
        }

        public void setCom_qq(String com_qq) {
            this.com_qq = com_qq;
        }

        public String getCom_addr() {
            return com_addr;
        }

        public void setCom_addr(String com_addr) {
            this.com_addr = com_addr;
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

        public String getCom_env() {
            return com_env;
        }

        public void setCom_env(String com_env) {
            this.com_env = com_env;
        }

        public String getCom_proenv() {
            return com_proenv;
        }

        public void setCom_proenv(String com_proenv) {
            this.com_proenv = com_proenv;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getDomestic() {
            return domestic;
        }

        public void setDomestic(String domestic) {
            this.domestic = domestic;
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

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getOrderItemBeans() {
            return orderItemBeans;
        }

        public void setOrderItemBeans(String orderItemBeans) {
            this.orderItemBeans = orderItemBeans;
        }
    }
}
