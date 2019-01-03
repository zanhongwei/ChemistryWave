package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class PriceExpressBean {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":10,"list":[{"price_id":1,"goods_name":"Sodium hydrogencarbonate","goods_price":"325","price_unit":"g","currency_kinds":"1"},{"price_id":2,"goods_name":"Hydroxyethyl Cellulose","goods_price":"84","price_unit":"g","currency_kinds":"2"},{"price_id":3,"goods_name":"Propane-1,2-diol","goods_price":"526","price_unit":"g","currency_kinds":"3"},{"price_id":4,"goods_name":"Dichloromethane","goods_price":"78","price_unit":"g","currency_kinds":"4"},{"price_id":5,"goods_name":"Refined Naphthalene","goods_price":"863","price_unit":"g","currency_kinds":"5"},{"price_id":6,"goods_name":"Nandrolone Decanoate","goods_price":"25","price_unit":"g","currency_kinds":"6"},{"price_id":7,"goods_name":"\r\nCompany Profile","goods_price":"156","price_unit":"g","currency_kinds":"7"},{"price_id":8,"goods_name":"enrofloxacin\r\nacyclovir","goods_price":"456","price_unit":"g","currency_kinds":"8"},{"price_id":9,"goods_name":"\r\nacyclovir","goods_price":"856","price_unit":"g","currency_kinds":"9"},{"price_id":10,"goods_name":"amoxicillin trihydrate","goods_price":"226","price_unit":"g","currency_kinds":"10"}],"pageSize":100,"currPage":1,"totalPage":1}
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
         * totalCount : 10
         * list : [{"price_id":1,"goods_name":"Sodium hydrogencarbonate","goods_price":"325","price_unit":"g","currency_kinds":"1"},{"price_id":2,"goods_name":"Hydroxyethyl Cellulose","goods_price":"84","price_unit":"g","currency_kinds":"2"},{"price_id":3,"goods_name":"Propane-1,2-diol","goods_price":"526","price_unit":"g","currency_kinds":"3"},{"price_id":4,"goods_name":"Dichloromethane","goods_price":"78","price_unit":"g","currency_kinds":"4"},{"price_id":5,"goods_name":"Refined Naphthalene","goods_price":"863","price_unit":"g","currency_kinds":"5"},{"price_id":6,"goods_name":"Nandrolone Decanoate","goods_price":"25","price_unit":"g","currency_kinds":"6"},{"price_id":7,"goods_name":"\r\nCompany Profile","goods_price":"156","price_unit":"g","currency_kinds":"7"},{"price_id":8,"goods_name":"enrofloxacin\r\nacyclovir","goods_price":"456","price_unit":"g","currency_kinds":"8"},{"price_id":9,"goods_name":"\r\nacyclovir","goods_price":"856","price_unit":"g","currency_kinds":"9"},{"price_id":10,"goods_name":"amoxicillin trihydrate","goods_price":"226","price_unit":"g","currency_kinds":"10"}]
         * pageSize : 100
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
             * price_id : 1
             * goods_name : Sodium hydrogencarbonate
             * goods_price : 325
             * price_unit : g
             * currency_kinds : 1
             */

            private int price_id;
            private String goods_name;
            private String goods_price;
            private String price_unit;
            private String currency_kinds;

            public int getPrice_id() {
                return price_id;
            }

            public void setPrice_id(int price_id) {
                this.price_id = price_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public String getPrice_unit() {
                return price_unit;
            }

            public void setPrice_unit(String price_unit) {
                this.price_unit = price_unit;
            }

            public String getCurrency_kinds() {
                return currency_kinds;
            }

            public void setCurrency_kinds(String currency_kinds) {
                this.currency_kinds = currency_kinds;
            }
        }
    }
}
