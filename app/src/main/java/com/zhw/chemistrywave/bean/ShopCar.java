package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/1/31.
 */

public class ShopCar {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":2,"list":[{"shop_id":3,"user_id":0,"shop_name":null,"com_name":null,"com_addr":null,"contact_name":null,"contact_mobile":null,"com_logo":"upload/14/20180308152430750.png","com_license":null,"per_card":null,"per_addr":null,"domestic":null,"create_time":null,"shop_state":null,"rank":null,"total_volumes":0,"orderBeans":null,"merchandiseBeans":null,"goodsMallBean":null,"cartBeans":[{"cart_id":49,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:23","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false},{"cart_id":48,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:20","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false}],"orderItemBeans":null}],"pageSize":100000,"currPage":1,"totalPage":1}
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
         * list : [{"shop_id":3,"user_id":0,"shop_name":null,"com_name":null,"com_addr":null,"contact_name":null,"contact_mobile":null,"com_logo":"upload/14/20180308152430750.png","com_license":null,"per_card":null,"per_addr":null,"domestic":null,"create_time":null,"shop_state":null,"rank":null,"total_volumes":0,"orderBeans":null,"merchandiseBeans":null,"goodsMallBean":null,"cartBeans":[{"cart_id":49,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:23","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false},{"cart_id":48,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:20","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false}],"orderItemBeans":null}]
         * pageSize : 100000
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
             * shop_id : 3
             * user_id : 0
             * shop_name : null
             * com_name : null
             * com_addr : null
             * contact_name : null
             * contact_mobile : null
             * com_logo : upload/14/20180308152430750.png
             * com_license : null
             * per_card : null
             * per_addr : null
             * domestic : null
             * create_time : null
             * shop_state : null
             * rank : null
             * total_volumes : 0
             * orderBeans : null
             * merchandiseBeans : null
             * goodsMallBean : null
             * cartBeans : [{"cart_id":49,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:23","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false},{"cart_id":48,"user_id":53,"goods_id":29,"goods_num":1,"goods_name":" propane-1,2-diol","goods_price":"900.00","color_light":"0.6","color_power":"1","deliver_addr":null,"create_time":"2018-05-31 14:21:20","product_picture":"upload/14/20180308154320603.png","package_opt":"1","checked":false}]
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
            private String orderItemBeans;
            private List<CartBeansBean> cartBeans;
            private boolean choosed;

            public boolean isChoosed() {
                return choosed;
            }

            public void setChoosed(boolean choosed) {
                this.choosed = choosed;
            }

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

            public String getOrderItemBeans() {
                return orderItemBeans;
            }

            public void setOrderItemBeans(String orderItemBeans) {
                this.orderItemBeans = orderItemBeans;
            }

            public List<CartBeansBean> getCartBeans() {
                return cartBeans;
            }

            public void setCartBeans(List<CartBeansBean> cartBeans) {
                this.cartBeans = cartBeans;
            }

            public static class CartBeansBean {
                /**
                 * cart_id : 49
                 * user_id : 53
                 * goods_id : 29
                 * goods_num : 1
                 * goods_name :  propane-1,2-diol
                 * goods_price : 900.00
                 * color_light : 0.6
                 * color_power : 1
                 * deliver_addr : null
                 * create_time : 2018-05-31 14:21:23
                 * product_picture : upload/14/20180308154320603.png
                 * package_opt : 1
                 * checked : false
                 */

                private int cart_id;
                private int user_id;
                private int goods_id;
                private int goods_num;
                private String goods_name;
                private String goods_price;
                private String color_light;
                private String color_power;
                private String deliver_addr;
                private String create_time;
                private String product_picture;
                private String package_opt;
                private boolean checked;

                public int getCart_id() {
                    return cart_id;
                }

                public void setCart_id(int cart_id) {
                    this.cart_id = cart_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(int goods_num) {
                    this.goods_num = goods_num;
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

                public String getDeliver_addr() {
                    return deliver_addr;
                }

                public void setDeliver_addr(String deliver_addr) {
                    this.deliver_addr = deliver_addr;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getProduct_picture() {
                    return product_picture;
                }

                public void setProduct_picture(String product_picture) {
                    this.product_picture = product_picture;
                }

                public String getPackage_opt() {
                    return package_opt;
                }

                public void setPackage_opt(String package_opt) {
                    this.package_opt = package_opt;
                }

                public boolean isChecked() {
                    return checked;
                }

                public void setChecked(boolean checked) {
                    this.checked = checked;
                }
            }
        }
    }
}
