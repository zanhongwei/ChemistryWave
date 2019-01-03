package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/3.
 */

public class SDJJBean {

    /**
     * code : 0
     * data : {"totalCount":36,"list":[{"spon_id":290,"spon_serl":"15149474017484e87","sponsor_id":"1","spon_state":"2","spon_type":"1","create_time":"2018-01-03 10:43:21","winning_time":null,"sopn_others":null,"domestic":null,"mall_status":null,"is_third":null,"cargoBeans":[{"cargo_id":320,"cargo_state":null,"prtp_num":null,"cargo_name":"醋酸","cargo_purity":"1","delivery_time":"1","cargo_num":"1","cargo_package":"1","ceiling_price":"11","transport_way":"1","payment_way":"1","create_time":"2018-01-03 10:43:48","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":"1","spon_id":290,"pro_pic":null,"market_price":null,"current_price":null,"cino":null,"cas":null,"cn_name":null,"en_name":null,"molecular":null,"psa":null,"exact_mass":null,"first_level":null,"second_level":null,"is_discount":null,"discount":null,"is_seckill":null,"kill_price":null,"kill_start":null,"kill_limit":null,"metalBean":null,"qualityBean":null,"reportBean":null,"is_offering":null}]}]}
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
         * totalCount : 36
         * list : [{"spon_id":290,"spon_serl":"15149474017484e87","sponsor_id":"1","spon_state":"2","spon_type":"1","create_time":"2018-01-03 10:43:21","winning_time":null,"sopn_others":null,"domestic":null,"mall_status":null,"is_third":null,"cargoBeans":[{"cargo_id":320,"cargo_state":null,"prtp_num":null,"cargo_name":"醋酸","cargo_purity":"1","delivery_time":"1","cargo_num":"1","cargo_package":"1","ceiling_price":"11","transport_way":"1","payment_way":"1","create_time":"2018-01-03 10:43:48","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":"1","spon_id":290,"pro_pic":null,"market_price":null,"current_price":null,"cino":null,"cas":null,"cn_name":null,"en_name":null,"molecular":null,"psa":null,"exact_mass":null,"first_level":null,"second_level":null,"is_discount":null,"discount":null,"is_seckill":null,"kill_price":null,"kill_start":null,"kill_limit":null,"metalBean":null,"qualityBean":null,"reportBean":null,"is_offering":null}]}]
         */

        private int totalCount;
        private List<ListBean> list;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * spon_id : 290
             * spon_serl : 15149474017484e87
             * sponsor_id : 1
             * spon_state : 2
             * spon_type : 1
             * create_time : 2018-01-03 10:43:21
             * winning_time : null
             * sopn_others : null
             * domestic : null
             * mall_status : null
             * is_third : null
             * cargoBeans : [{"cargo_id":320,"cargo_state":null,"prtp_num":null,"cargo_name":"醋酸","cargo_purity":"1","delivery_time":"1","cargo_num":"1","cargo_package":"1","ceiling_price":"11","transport_way":"1","payment_way":"1","create_time":"2018-01-03 10:43:48","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":"1","spon_id":290,"pro_pic":null,"market_price":null,"current_price":null,"cino":null,"cas":null,"cn_name":null,"en_name":null,"molecular":null,"psa":null,"exact_mass":null,"first_level":null,"second_level":null,"is_discount":null,"discount":null,"is_seckill":null,"kill_price":null,"kill_start":null,"kill_limit":null,"metalBean":null,"qualityBean":null,"reportBean":null,"is_offering":null}]
             */

            private int spon_id;
            private String spon_serl;
            private String sponsor_id;
            private String spon_state;
            private String spon_type;
            private String create_time;
            private Object winning_time;
            private Object sopn_others;
            private Object domestic;
            private Object mall_status;
            private Object is_third;
            private List<CargoBeansBean> cargoBeans;

            public int getSpon_id() {
                return spon_id;
            }

            public void setSpon_id(int spon_id) {
                this.spon_id = spon_id;
            }

            public String getSpon_serl() {
                return spon_serl;
            }

            public void setSpon_serl(String spon_serl) {
                this.spon_serl = spon_serl;
            }

            public String getSponsor_id() {
                return sponsor_id;
            }

            public void setSponsor_id(String sponsor_id) {
                this.sponsor_id = sponsor_id;
            }

            public String getSpon_state() {
                return spon_state;
            }

            public void setSpon_state(String spon_state) {
                this.spon_state = spon_state;
            }

            public String getSpon_type() {
                return spon_type;
            }

            public void setSpon_type(String spon_type) {
                this.spon_type = spon_type;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public Object getWinning_time() {
                return winning_time;
            }

            public void setWinning_time(Object winning_time) {
                this.winning_time = winning_time;
            }

            public Object getSopn_others() {
                return sopn_others;
            }

            public void setSopn_others(Object sopn_others) {
                this.sopn_others = sopn_others;
            }

            public Object getDomestic() {
                return domestic;
            }

            public void setDomestic(Object domestic) {
                this.domestic = domestic;
            }

            public Object getMall_status() {
                return mall_status;
            }

            public void setMall_status(Object mall_status) {
                this.mall_status = mall_status;
            }

            public Object getIs_third() {
                return is_third;
            }

            public void setIs_third(Object is_third) {
                this.is_third = is_third;
            }

            public List<CargoBeansBean> getCargoBeans() {
                return cargoBeans;
            }

            public void setCargoBeans(List<CargoBeansBean> cargoBeans) {
                this.cargoBeans = cargoBeans;
            }

            public static class CargoBeansBean {
                /**
                 * cargo_id : 320
                 * cargo_state : null
                 * prtp_num : null
                 * cargo_name : 醋酸
                 * cargo_purity : 1
                 * delivery_time : 1
                 * cargo_num : 1
                 * cargo_package : 1
                 * ceiling_price : 11
                 * transport_way : 1
                 * payment_way : 1
                 * create_time : 2018-01-03 10:43:48
                 * floor_price : null
                 * application_scheme : null
                 * product_picture : null
                 * production_state : null
                 * specifications : 1
                 * spon_id : 290
                 * pro_pic : null
                 * market_price : null
                 * current_price : null
                 * cino : null
                 * cas : null
                 * cn_name : null
                 * en_name : null
                 * molecular : null
                 * psa : null
                 * exact_mass : null
                 * first_level : null
                 * second_level : null
                 * is_discount : null
                 * discount : null
                 * is_seckill : null
                 * kill_price : null
                 * kill_start : null
                 * kill_limit : null
                 * metalBean : null
                 * qualityBean : null
                 * reportBean : null
                 * is_offering : null
                 */

                private int cargo_id;
                private Object cargo_state;
                private Object prtp_num;
                private String cargo_name;
                private String cargo_purity;
                private String delivery_time;
                private String cargo_num;
                private String cargo_package;
                private String ceiling_price;
                private String transport_way;
                private String payment_way;
                private String create_time;
                private Object floor_price;
                private Object application_scheme;
                private Object product_picture;
                private Object production_state;
                private String specifications;
                private int spon_id;
                private Object pro_pic;
                private Object market_price;
                private Object current_price;
                private Object cino;
                private Object cas;
                private Object cn_name;
                private Object en_name;
                private Object molecular;
                private Object psa;
                private Object exact_mass;
                private Object first_level;
                private Object second_level;
                private Object is_discount;
                private Object discount;
                private Object is_seckill;
                private Object kill_price;
                private Object kill_start;
                private Object kill_limit;
                private Object metalBean;
                private Object qualityBean;
                private Object reportBean;
                private Object is_offering;

                public int getCargo_id() {
                    return cargo_id;
                }

                public void setCargo_id(int cargo_id) {
                    this.cargo_id = cargo_id;
                }

                public Object getCargo_state() {
                    return cargo_state;
                }

                public void setCargo_state(Object cargo_state) {
                    this.cargo_state = cargo_state;
                }

                public Object getPrtp_num() {
                    return prtp_num;
                }

                public void setPrtp_num(Object prtp_num) {
                    this.prtp_num = prtp_num;
                }

                public String getCargo_name() {
                    return cargo_name;
                }

                public void setCargo_name(String cargo_name) {
                    this.cargo_name = cargo_name;
                }

                public String getCargo_purity() {
                    return cargo_purity;
                }

                public void setCargo_purity(String cargo_purity) {
                    this.cargo_purity = cargo_purity;
                }

                public String getDelivery_time() {
                    return delivery_time;
                }

                public void setDelivery_time(String delivery_time) {
                    this.delivery_time = delivery_time;
                }

                public String getCargo_num() {
                    return cargo_num;
                }

                public void setCargo_num(String cargo_num) {
                    this.cargo_num = cargo_num;
                }

                public String getCargo_package() {
                    return cargo_package;
                }

                public void setCargo_package(String cargo_package) {
                    this.cargo_package = cargo_package;
                }

                public String getCeiling_price() {
                    return ceiling_price;
                }

                public void setCeiling_price(String ceiling_price) {
                    this.ceiling_price = ceiling_price;
                }

                public String getTransport_way() {
                    return transport_way;
                }

                public void setTransport_way(String transport_way) {
                    this.transport_way = transport_way;
                }

                public String getPayment_way() {
                    return payment_way;
                }

                public void setPayment_way(String payment_way) {
                    this.payment_way = payment_way;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public Object getFloor_price() {
                    return floor_price;
                }

                public void setFloor_price(Object floor_price) {
                    this.floor_price = floor_price;
                }

                public Object getApplication_scheme() {
                    return application_scheme;
                }

                public void setApplication_scheme(Object application_scheme) {
                    this.application_scheme = application_scheme;
                }

                public Object getProduct_picture() {
                    return product_picture;
                }

                public void setProduct_picture(Object product_picture) {
                    this.product_picture = product_picture;
                }

                public Object getProduction_state() {
                    return production_state;
                }

                public void setProduction_state(Object production_state) {
                    this.production_state = production_state;
                }

                public String getSpecifications() {
                    return specifications;
                }

                public void setSpecifications(String specifications) {
                    this.specifications = specifications;
                }

                public int getSpon_id() {
                    return spon_id;
                }

                public void setSpon_id(int spon_id) {
                    this.spon_id = spon_id;
                }

                public Object getPro_pic() {
                    return pro_pic;
                }

                public void setPro_pic(Object pro_pic) {
                    this.pro_pic = pro_pic;
                }

                public Object getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(Object market_price) {
                    this.market_price = market_price;
                }

                public Object getCurrent_price() {
                    return current_price;
                }

                public void setCurrent_price(Object current_price) {
                    this.current_price = current_price;
                }

                public Object getCino() {
                    return cino;
                }

                public void setCino(Object cino) {
                    this.cino = cino;
                }

                public Object getCas() {
                    return cas;
                }

                public void setCas(Object cas) {
                    this.cas = cas;
                }

                public Object getCn_name() {
                    return cn_name;
                }

                public void setCn_name(Object cn_name) {
                    this.cn_name = cn_name;
                }

                public Object getEn_name() {
                    return en_name;
                }

                public void setEn_name(Object en_name) {
                    this.en_name = en_name;
                }

                public Object getMolecular() {
                    return molecular;
                }

                public void setMolecular(Object molecular) {
                    this.molecular = molecular;
                }

                public Object getPsa() {
                    return psa;
                }

                public void setPsa(Object psa) {
                    this.psa = psa;
                }

                public Object getExact_mass() {
                    return exact_mass;
                }

                public void setExact_mass(Object exact_mass) {
                    this.exact_mass = exact_mass;
                }

                public Object getFirst_level() {
                    return first_level;
                }

                public void setFirst_level(Object first_level) {
                    this.first_level = first_level;
                }

                public Object getSecond_level() {
                    return second_level;
                }

                public void setSecond_level(Object second_level) {
                    this.second_level = second_level;
                }

                public Object getIs_discount() {
                    return is_discount;
                }

                public void setIs_discount(Object is_discount) {
                    this.is_discount = is_discount;
                }

                public Object getDiscount() {
                    return discount;
                }

                public void setDiscount(Object discount) {
                    this.discount = discount;
                }

                public Object getIs_seckill() {
                    return is_seckill;
                }

                public void setIs_seckill(Object is_seckill) {
                    this.is_seckill = is_seckill;
                }

                public Object getKill_price() {
                    return kill_price;
                }

                public void setKill_price(Object kill_price) {
                    this.kill_price = kill_price;
                }

                public Object getKill_start() {
                    return kill_start;
                }

                public void setKill_start(Object kill_start) {
                    this.kill_start = kill_start;
                }

                public Object getKill_limit() {
                    return kill_limit;
                }

                public void setKill_limit(Object kill_limit) {
                    this.kill_limit = kill_limit;
                }

                public Object getMetalBean() {
                    return metalBean;
                }

                public void setMetalBean(Object metalBean) {
                    this.metalBean = metalBean;
                }

                public Object getQualityBean() {
                    return qualityBean;
                }

                public void setQualityBean(Object qualityBean) {
                    this.qualityBean = qualityBean;
                }

                public Object getReportBean() {
                    return reportBean;
                }

                public void setReportBean(Object reportBean) {
                    this.reportBean = reportBean;
                }

                public Object getIs_offering() {
                    return is_offering;
                }

                public void setIs_offering(Object is_offering) {
                    this.is_offering = is_offering;
                }
            }
        }
    }
}
