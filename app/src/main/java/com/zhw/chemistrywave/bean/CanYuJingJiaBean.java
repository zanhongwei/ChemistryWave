package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 */

public class CanYuJingJiaBean {

    /**
     * code : 0
     * data : {"totalCount":1,"list":[{"prtp_id":0,"spon_serl":"1514444456432232e        ","buyer_id":0,"seller_id":0,"create_time":null,"cargo_name":null,"given_price":null,"require_sum":null,"unit_measure":null,"require_packing":null,"payment_way":null,"prtp_state":null,"one_key":null,"prtp_type":null,"cargo_id":0,"user_id":0,"user_name":null,"user_type":null,"user_photo":null,"spon_id":192,"cargo_state":null,"cargo_num":null,"cargo_purity":null,"prtp_num":null,"floor_price":null,"specifications":null,"delivery_time":null,"cargo_package":null,"transport_way":null,"participationBeans":[{"prtp_id":0,"spon_serl":null,"buyer_id":0,"seller_id":0,"create_time":null,"cargo_name":"水泥","given_price":null,"require_sum":null,"unit_measure":null,"require_packing":null,"payment_way":null,"prtp_state":null,"one_key":null,"prtp_type":null,"cargo_id":271,"user_id":0,"user_name":null,"user_type":null,"user_photo":null,"spon_id":0,"cargo_state":"3","cargo_num":"100","cargo_purity":"99%","prtp_num":"17","floor_price":null,"specifications":null,"delivery_time":null,"cargo_package":null,"transport_way":null,"participationBeans":null}]}],"pageSize":1000,"currPage":1,"totalPage":1}
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
         * totalCount : 1
         * list : [{"prtp_id":0,"spon_serl":"1514444456432232e        ","buyer_id":0,"seller_id":0,"create_time":null,"cargo_name":null,"given_price":null,"require_sum":null,"unit_measure":null,"require_packing":null,"payment_way":null,"prtp_state":null,"one_key":null,"prtp_type":null,"cargo_id":0,"user_id":0,"user_name":null,"user_type":null,"user_photo":null,"spon_id":192,"cargo_state":null,"cargo_num":null,"cargo_purity":null,"prtp_num":null,"floor_price":null,"specifications":null,"delivery_time":null,"cargo_package":null,"transport_way":null,"participationBeans":[{"prtp_id":0,"spon_serl":null,"buyer_id":0,"seller_id":0,"create_time":null,"cargo_name":"水泥","given_price":null,"require_sum":null,"unit_measure":null,"require_packing":null,"payment_way":null,"prtp_state":null,"one_key":null,"prtp_type":null,"cargo_id":271,"user_id":0,"user_name":null,"user_type":null,"user_photo":null,"spon_id":0,"cargo_state":"3","cargo_num":"100","cargo_purity":"99%","prtp_num":"17","floor_price":null,"specifications":null,"delivery_time":null,"cargo_package":null,"transport_way":null,"participationBeans":null}]}]
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
             * prtp_id : 0
             * spon_serl : 1514444456432232e
             * buyer_id : 0
             * seller_id : 0
             * create_time : null
             * cargo_name : null
             * given_price : null
             * require_sum : null
             * unit_measure : null
             * require_packing : null
             * payment_way : null
             * prtp_state : null
             * one_key : null
             * prtp_type : null
             * cargo_id : 0
             * user_id : 0
             * user_name : null
             * user_type : null
             * user_photo : null
             * spon_id : 192
             * cargo_state : null
             * cargo_num : null
             * cargo_purity : null
             * prtp_num : null
             * floor_price : null
             * specifications : null
             * delivery_time : null
             * cargo_package : null
             * transport_way : null
             * participationBeans : [{"prtp_id":0,"spon_serl":null,"buyer_id":0,"seller_id":0,"create_time":null,"cargo_name":"水泥","given_price":null,"require_sum":null,"unit_measure":null,"require_packing":null,"payment_way":null,"prtp_state":null,"one_key":null,"prtp_type":null,"cargo_id":271,"user_id":0,"user_name":null,"user_type":null,"user_photo":null,"spon_id":0,"cargo_state":"3","cargo_num":"100","cargo_purity":"99%","prtp_num":"17","floor_price":null,"specifications":null,"delivery_time":null,"cargo_package":null,"transport_way":null,"participationBeans":null}]
             */

            private int prtp_id;
            private String spon_serl;
            private int buyer_id;
            private int seller_id;
            private Object create_time;
            private Object cargo_name;
            private Object given_price;
            private Object require_sum;
            private Object unit_measure;
            private Object require_packing;
            private Object payment_way;
            private Object prtp_state;
            private Object one_key;
            private Object prtp_type;
            private int cargo_id;
            private int user_id;
            private Object user_name;
            private Object user_type;
            private Object user_photo;
            private int spon_id;
            private Object cargo_state;
            private Object cargo_num;
            private Object cargo_purity;
            private Object prtp_num;
            private Object floor_price;
            private Object specifications;
            private Object delivery_time;
            private Object cargo_package;
            private Object transport_way;
            private List<ParticipationBeansBean> participationBeans;

            public int getPrtp_id() {
                return prtp_id;
            }

            public void setPrtp_id(int prtp_id) {
                this.prtp_id = prtp_id;
            }

            public String getSpon_serl() {
                return spon_serl;
            }

            public void setSpon_serl(String spon_serl) {
                this.spon_serl = spon_serl;
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

            public Object getCreate_time() {
                return create_time;
            }

            public void setCreate_time(Object create_time) {
                this.create_time = create_time;
            }

            public Object getCargo_name() {
                return cargo_name;
            }

            public void setCargo_name(Object cargo_name) {
                this.cargo_name = cargo_name;
            }

            public Object getGiven_price() {
                return given_price;
            }

            public void setGiven_price(Object given_price) {
                this.given_price = given_price;
            }

            public Object getRequire_sum() {
                return require_sum;
            }

            public void setRequire_sum(Object require_sum) {
                this.require_sum = require_sum;
            }

            public Object getUnit_measure() {
                return unit_measure;
            }

            public void setUnit_measure(Object unit_measure) {
                this.unit_measure = unit_measure;
            }

            public Object getRequire_packing() {
                return require_packing;
            }

            public void setRequire_packing(Object require_packing) {
                this.require_packing = require_packing;
            }

            public Object getPayment_way() {
                return payment_way;
            }

            public void setPayment_way(Object payment_way) {
                this.payment_way = payment_way;
            }

            public Object getPrtp_state() {
                return prtp_state;
            }

            public void setPrtp_state(Object prtp_state) {
                this.prtp_state = prtp_state;
            }

            public Object getOne_key() {
                return one_key;
            }

            public void setOne_key(Object one_key) {
                this.one_key = one_key;
            }

            public Object getPrtp_type() {
                return prtp_type;
            }

            public void setPrtp_type(Object prtp_type) {
                this.prtp_type = prtp_type;
            }

            public int getCargo_id() {
                return cargo_id;
            }

            public void setCargo_id(int cargo_id) {
                this.cargo_id = cargo_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public Object getUser_name() {
                return user_name;
            }

            public void setUser_name(Object user_name) {
                this.user_name = user_name;
            }

            public Object getUser_type() {
                return user_type;
            }

            public void setUser_type(Object user_type) {
                this.user_type = user_type;
            }

            public Object getUser_photo() {
                return user_photo;
            }

            public void setUser_photo(Object user_photo) {
                this.user_photo = user_photo;
            }

            public int getSpon_id() {
                return spon_id;
            }

            public void setSpon_id(int spon_id) {
                this.spon_id = spon_id;
            }

            public Object getCargo_state() {
                return cargo_state;
            }

            public void setCargo_state(Object cargo_state) {
                this.cargo_state = cargo_state;
            }

            public Object getCargo_num() {
                return cargo_num;
            }

            public void setCargo_num(Object cargo_num) {
                this.cargo_num = cargo_num;
            }

            public Object getCargo_purity() {
                return cargo_purity;
            }

            public void setCargo_purity(Object cargo_purity) {
                this.cargo_purity = cargo_purity;
            }

            public Object getPrtp_num() {
                return prtp_num;
            }

            public void setPrtp_num(Object prtp_num) {
                this.prtp_num = prtp_num;
            }

            public Object getFloor_price() {
                return floor_price;
            }

            public void setFloor_price(Object floor_price) {
                this.floor_price = floor_price;
            }

            public Object getSpecifications() {
                return specifications;
            }

            public void setSpecifications(Object specifications) {
                this.specifications = specifications;
            }

            public Object getDelivery_time() {
                return delivery_time;
            }

            public void setDelivery_time(Object delivery_time) {
                this.delivery_time = delivery_time;
            }

            public Object getCargo_package() {
                return cargo_package;
            }

            public void setCargo_package(Object cargo_package) {
                this.cargo_package = cargo_package;
            }

            public Object getTransport_way() {
                return transport_way;
            }

            public void setTransport_way(Object transport_way) {
                this.transport_way = transport_way;
            }

            public List<ParticipationBeansBean> getParticipationBeans() {
                return participationBeans;
            }

            public void setParticipationBeans(List<ParticipationBeansBean> participationBeans) {
                this.participationBeans = participationBeans;
            }

            public static class ParticipationBeansBean {
                @Override
                public String toString() {
                    return "ParticipationBeansBean{" +
                            "prtp_id=" + prtp_id +
                            ", spon_serl=" + spon_serl +
                            ", buyer_id=" + buyer_id +
                            ", seller_id=" + seller_id +
                            ", create_time=" + create_time +
                            ", cargo_name='" + cargo_name + '\'' +
                            ", given_price=" + given_price +
                            ", require_sum=" + require_sum +
                            ", unit_measure=" + unit_measure +
                            ", require_packing=" + require_packing +
                            ", payment_way=" + payment_way +
                            ", prtp_state=" + prtp_state +
                            ", one_key=" + one_key +
                            ", prtp_type=" + prtp_type +
                            ", cargo_id=" + cargo_id +
                            ", user_id=" + user_id +
                            ", user_name=" + user_name +
                            ", user_type=" + user_type +
                            ", user_photo=" + user_photo +
                            ", spon_id=" + spon_id +
                            ", cargo_state='" + cargo_state + '\'' +
                            ", cargo_num='" + cargo_num + '\'' +
                            ", cargo_purity='" + cargo_purity + '\'' +
                            ", prtp_num='" + prtp_num + '\'' +
                            ", floor_price=" + floor_price +
                            ", specifications=" + specifications +
                            ", delivery_time=" + delivery_time +
                            ", cargo_package=" + cargo_package +
                            ", transport_way=" + transport_way +
                            ", participationBeans=" + participationBeans +
                            '}';
                }

                /**
                 * prtp_id : 0
                 * spon_serl : null
                 * buyer_id : 0
                 * seller_id : 0
                 * create_time : null
                 * cargo_name : 水泥
                 * given_price : null
                 * require_sum : null
                 * unit_measure : null
                 * require_packing : null
                 * payment_way : null
                 * prtp_state : null
                 * one_key : null
                 * prtp_type : null
                 * cargo_id : 271
                 * user_id : 0
                 * user_name : null
                 * user_type : null
                 * user_photo : null
                 * spon_id : 0
                 * cargo_state : 3
                 * cargo_num : 100
                 * cargo_purity : 99%
                 * prtp_num : 17
                 * floor_price : null
                 * specifications : null
                 * delivery_time : null
                 * cargo_package : null
                 * transport_way : null
                 * participationBeans : null
                 */

                private int prtp_id;
                private Object spon_serl;
                private int buyer_id;
                private int seller_id;
                private Object create_time;
                private String cargo_name;
                private Object given_price;
                private Object require_sum;
                private Object unit_measure;
                private Object require_packing;
                private Object payment_way;
                private Object prtp_state;
                private Object one_key;
                private Object prtp_type;
                private int cargo_id;
                private int user_id;
                private Object user_name;
                private Object user_type;
                private Object user_photo;
                private int spon_id;
                private String cargo_state;
                private String cargo_num;
                private String cargo_purity;
                private String prtp_num;
                private Object floor_price;
                private Object specifications;
                private Object delivery_time;
                private Object cargo_package;
                private Object transport_way;
                private Object participationBeans;

                public int getPrtp_id() {
                    return prtp_id;
                }

                public void setPrtp_id(int prtp_id) {
                    this.prtp_id = prtp_id;
                }

                public Object getSpon_serl() {
                    return spon_serl;
                }

                public void setSpon_serl(Object spon_serl) {
                    this.spon_serl = spon_serl;
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

                public Object getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(Object create_time) {
                    this.create_time = create_time;
                }

                public String getCargo_name() {
                    return cargo_name;
                }

                public void setCargo_name(String cargo_name) {
                    this.cargo_name = cargo_name;
                }

                public Object getGiven_price() {
                    return given_price;
                }

                public void setGiven_price(Object given_price) {
                    this.given_price = given_price;
                }

                public Object getRequire_sum() {
                    return require_sum;
                }

                public void setRequire_sum(Object require_sum) {
                    this.require_sum = require_sum;
                }

                public Object getUnit_measure() {
                    return unit_measure;
                }

                public void setUnit_measure(Object unit_measure) {
                    this.unit_measure = unit_measure;
                }

                public Object getRequire_packing() {
                    return require_packing;
                }

                public void setRequire_packing(Object require_packing) {
                    this.require_packing = require_packing;
                }

                public Object getPayment_way() {
                    return payment_way;
                }

                public void setPayment_way(Object payment_way) {
                    this.payment_way = payment_way;
                }

                public Object getPrtp_state() {
                    return prtp_state;
                }

                public void setPrtp_state(Object prtp_state) {
                    this.prtp_state = prtp_state;
                }

                public Object getOne_key() {
                    return one_key;
                }

                public void setOne_key(Object one_key) {
                    this.one_key = one_key;
                }

                public Object getPrtp_type() {
                    return prtp_type;
                }

                public void setPrtp_type(Object prtp_type) {
                    this.prtp_type = prtp_type;
                }

                public int getCargo_id() {
                    return cargo_id;
                }

                public void setCargo_id(int cargo_id) {
                    this.cargo_id = cargo_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public Object getUser_name() {
                    return user_name;
                }

                public void setUser_name(Object user_name) {
                    this.user_name = user_name;
                }

                public Object getUser_type() {
                    return user_type;
                }

                public void setUser_type(Object user_type) {
                    this.user_type = user_type;
                }

                public Object getUser_photo() {
                    return user_photo;
                }

                public void setUser_photo(Object user_photo) {
                    this.user_photo = user_photo;
                }

                public int getSpon_id() {
                    return spon_id;
                }

                public void setSpon_id(int spon_id) {
                    this.spon_id = spon_id;
                }

                public String getCargo_state() {
                    return cargo_state;
                }

                public void setCargo_state(String cargo_state) {
                    this.cargo_state = cargo_state;
                }

                public String getCargo_num() {
                    return cargo_num;
                }

                public void setCargo_num(String cargo_num) {
                    this.cargo_num = cargo_num;
                }

                public String getCargo_purity() {
                    return cargo_purity;
                }

                public void setCargo_purity(String cargo_purity) {
                    this.cargo_purity = cargo_purity;
                }

                public String getPrtp_num() {
                    return prtp_num;
                }

                public void setPrtp_num(String prtp_num) {
                    this.prtp_num = prtp_num;
                }

                public Object getFloor_price() {
                    return floor_price;
                }

                public void setFloor_price(Object floor_price) {
                    this.floor_price = floor_price;
                }

                public Object getSpecifications() {
                    return specifications;
                }

                public void setSpecifications(Object specifications) {
                    this.specifications = specifications;
                }

                public Object getDelivery_time() {
                    return delivery_time;
                }

                public void setDelivery_time(Object delivery_time) {
                    this.delivery_time = delivery_time;
                }

                public Object getCargo_package() {
                    return cargo_package;
                }

                public void setCargo_package(Object cargo_package) {
                    this.cargo_package = cargo_package;
                }

                public Object getTransport_way() {
                    return transport_way;
                }

                public void setTransport_way(Object transport_way) {
                    this.transport_way = transport_way;
                }

                public Object getParticipationBeans() {
                    return participationBeans;
                }

                public void setParticipationBeans(Object participationBeans) {
                    this.participationBeans = participationBeans;
                }
            }
        }
    }
}
