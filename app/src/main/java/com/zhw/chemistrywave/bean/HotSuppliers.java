package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class HotSuppliers {


    /**
     * msg : success
     * code : 0
     * data : [{"shop_id":3,"user_id":1,"shop_name":"hello world","com_name":null,"com_addr":null,"contact_name":null,"contact_mobile":null,"com_logo":"upload/14/20180308152430750.png","com_license":null,"per_card":null,"per_addr":null,"domestic":null,"create_time":null,"shop_state":null,"rank":"1","total_volumes":10,"orderBeans":null,"merchandiseBeans":[{"goods_id":7,"goods_type":null,"sales_volume":0,"create_time":null,"user_id":0,"user_name":null,"user_photo":null,"user_type":null,"goods_state":null,"goods_name":null,"goods_name_en":null,"one_type":null,"two_type":null,"cino":null,"cas":null,"molecular_weight":null,"pas":null,"precise_quality":null,"specification":null,"goods_purity":null,"goods_deliver":null,"goods_num":null,"goods_unit":null,"current_price":null,"market_price":null,"payment_opt":null,"transport_opt":null,"application_scheme":null,"product_picture":"upload/17/123.png","production_state":null,"color_power":null,"color_light":null,"package_opt":null,"detect_report":null,"detect_video":null,"detect_uv":null,"detect_colourimeter":null,"detect_sample_imgs":null,"detect_bulk_imgs":null,"checked":false,"seckill_deadline":null,"metal_cu":null,"metal_zn":null,"metal_ni":null,"metal_sb":null,"metal_cd":null,"metal_pb":null,"metal_sn":null,"metal_co":null,"metal_hg":null,"metal_bi":null,"quality_aspect":null,"quality_moisture":null,"quality_insoluble":null,"quality_solubility":null,"quality_clContent":null,"quality_solidContent":null,"quality_salinity":null,"quality_conductivity":null,"quality_michlerKetone":null,"shop_name":null,"shopBean":null,"ccrn":null}],"goodsMallBean":null,"cartBeans":null,"orderItemBeans":null},{"shop_id":4,"user_id":10,"shop_name":"hello world","com_name":null,"com_addr":null,"contact_name":null,"contact_mobile":null,"com_logo":"upload/14/26.png","com_license":null,"per_card":null,"per_addr":null,"domestic":null,"create_time":null,"shop_state":null,"rank":"1","total_volumes":100,"orderBeans":null,"merchandiseBeans":[{"goods_id":12,"goods_type":null,"sales_volume":0,"create_time":null,"user_id":0,"user_name":null,"user_photo":null,"user_type":null,"goods_state":null,"goods_name":null,"goods_name_en":null,"one_type":null,"two_type":null,"cino":null,"cas":null,"molecular_weight":null,"pas":null,"precise_quality":null,"specification":null,"goods_purity":null,"goods_deliver":null,"goods_num":null,"goods_unit":null,"current_price":null,"market_price":null,"payment_opt":null,"transport_opt":null,"application_scheme":null,"product_picture":"upload/9/20180308120358896.png","production_state":null,"color_power":null,"color_light":null,"package_opt":null,"detect_report":null,"detect_video":null,"detect_uv":null,"detect_colourimeter":null,"detect_sample_imgs":null,"detect_bulk_imgs":null,"checked":false,"seckill_deadline":null,"metal_cu":null,"metal_zn":null,"metal_ni":null,"metal_sb":null,"metal_cd":null,"metal_pb":null,"metal_sn":null,"metal_co":null,"metal_hg":null,"metal_bi":null,"quality_aspect":null,"quality_moisture":null,"quality_insoluble":null,"quality_solubility":null,"quality_clContent":null,"quality_solidContent":null,"quality_salinity":null,"quality_conductivity":null,"quality_michlerKetone":null,"shop_name":null,"shopBean":null,"ccrn":null}],"goodsMallBean":null,"cartBeans":null,"orderItemBeans":null},{"shop_id":5,"user_id":11,"shop_name":"hello world","com_name":null,"com_addr":null,"contact_name":null,"contact_mobile":null,"com_logo":"upload/14/27.png","com_license":null,"per_card":null,"per_addr":null,"domestic":null,"create_time":null,"shop_state":null,"rank":"1","total_volumes":100,"orderBeans":null,"merchandiseBeans":[{"goods_id":14,"goods_type":null,"sales_volume":0,"create_time":null,"user_id":0,"user_name":null,"user_photo":null,"user_type":null,"goods_state":null,"goods_name":null,"goods_name_en":null,"one_type":null,"two_type":null,"cino":null,"cas":null,"molecular_weight":null,"pas":null,"precise_quality":null,"specification":null,"goods_purity":null,"goods_deliver":null,"goods_num":null,"goods_unit":null,"current_price":null,"market_price":null,"payment_opt":null,"transport_opt":null,"application_scheme":null,"product_picture":"upload/11/20180308141833082.png","production_state":null,"color_power":null,"color_light":null,"package_opt":null,"detect_report":null,"detect_video":null,"detect_uv":null,"detect_colourimeter":null,"detect_sample_imgs":null,"detect_bulk_imgs":null,"checked":false,"seckill_deadline":null,"metal_cu":null,"metal_zn":null,"metal_ni":null,"metal_sb":null,"metal_cd":null,"metal_pb":null,"metal_sn":null,"metal_co":null,"metal_hg":null,"metal_bi":null,"quality_aspect":null,"quality_moisture":null,"quality_insoluble":null,"quality_solubility":null,"quality_clContent":null,"quality_solidContent":null,"quality_salinity":null,"quality_conductivity":null,"quality_michlerKetone":null,"shop_name":null,"shopBean":null,"ccrn":null}],"goodsMallBean":null,"cartBeans":null,"orderItemBeans":null}]
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
         * shop_id : 3
         * user_id : 1
         * shop_name : hello world
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
         * rank : 1
         * total_volumes : 10
         * orderBeans : null
         * merchandiseBeans : [{"goods_id":7,"goods_type":null,"sales_volume":0,"create_time":null,"user_id":0,"user_name":null,"user_photo":null,"user_type":null,"goods_state":null,"goods_name":null,"goods_name_en":null,"one_type":null,"two_type":null,"cino":null,"cas":null,"molecular_weight":null,"pas":null,"precise_quality":null,"specification":null,"goods_purity":null,"goods_deliver":null,"goods_num":null,"goods_unit":null,"current_price":null,"market_price":null,"payment_opt":null,"transport_opt":null,"application_scheme":null,"product_picture":"upload/17/123.png","production_state":null,"color_power":null,"color_light":null,"package_opt":null,"detect_report":null,"detect_video":null,"detect_uv":null,"detect_colourimeter":null,"detect_sample_imgs":null,"detect_bulk_imgs":null,"checked":false,"seckill_deadline":null,"metal_cu":null,"metal_zn":null,"metal_ni":null,"metal_sb":null,"metal_cd":null,"metal_pb":null,"metal_sn":null,"metal_co":null,"metal_hg":null,"metal_bi":null,"quality_aspect":null,"quality_moisture":null,"quality_insoluble":null,"quality_solubility":null,"quality_clContent":null,"quality_solidContent":null,"quality_salinity":null,"quality_conductivity":null,"quality_michlerKetone":null,"shop_name":null,"shopBean":null,"ccrn":null}]
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
        private String goodsMallBean;
        private String cartBeans;
        private String orderItemBeans;
        private List<MerchandiseBeansBean> merchandiseBeans;

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

        public List<MerchandiseBeansBean> getMerchandiseBeans() {
            return merchandiseBeans;
        }

        public void setMerchandiseBeans(List<MerchandiseBeansBean> merchandiseBeans) {
            this.merchandiseBeans = merchandiseBeans;
        }

        public static class MerchandiseBeansBean {
            /**
             * goods_id : 7
             * goods_type : null
             * sales_volume : 0
             * create_time : null
             * user_id : 0
             * user_name : null
             * user_photo : null
             * user_type : null
             * goods_state : null
             * goods_name : null
             * goods_name_en : null
             * one_type : null
             * two_type : null
             * cino : null
             * cas : null
             * molecular_weight : null
             * pas : null
             * precise_quality : null
             * specification : null
             * goods_purity : null
             * goods_deliver : null
             * goods_num : null
             * goods_unit : null
             * current_price : null
             * market_price : null
             * payment_opt : null
             * transport_opt : null
             * application_scheme : null
             * product_picture : upload/17/123.png
             * production_state : null
             * color_power : null
             * color_light : null
             * package_opt : null
             * detect_report : null
             * detect_video : null
             * detect_uv : null
             * detect_colourimeter : null
             * detect_sample_imgs : null
             * detect_bulk_imgs : null
             * checked : false
             * seckill_deadline : null
             * metal_cu : null
             * metal_zn : null
             * metal_ni : null
             * metal_sb : null
             * metal_cd : null
             * metal_pb : null
             * metal_sn : null
             * metal_co : null
             * metal_hg : null
             * metal_bi : null
             * quality_aspect : null
             * quality_moisture : null
             * quality_insoluble : null
             * quality_solubility : null
             * quality_clContent : null
             * quality_solidContent : null
             * quality_salinity : null
             * quality_conductivity : null
             * quality_michlerKetone : null
             * shop_name : null
             * shopBean : null
             * ccrn : null
             */

            private int goods_id;
            private String goods_type;
            private int sales_volume;
            private String create_time;
            private int user_id;
            private String user_name;
            private String user_photo;
            private String user_type;
            private String goods_state;
            private String goods_name;
            private String goods_name_en;
            private String one_type;
            private String two_type;
            private String cino;
            private String cas;
            private String molecular_weight;
            private String pas;
            private String precise_quality;
            private String specification;
            private String goods_purity;
            private String goods_deliver;
            private String goods_num;
            private String goods_unit;
            private String current_price;
            private String market_price;
            private String payment_opt;
            private String transport_opt;
            private String application_scheme;
            private String product_picture;
            private String production_state;
            private String color_power;
            private String color_light;
            private String package_opt;
            private String detect_report;
            private String detect_video;
            private String detect_uv;
            private String detect_colourimeter;
            private String detect_sample_imgs;
            private String detect_bulk_imgs;
            private boolean checked;
            private String seckill_deadline;
            private String metal_cu;
            private String metal_zn;
            private String metal_ni;
            private String metal_sb;
            private String metal_cd;
            private String metal_pb;
            private String metal_sn;
            private String metal_co;
            private String metal_hg;
            private String metal_bi;
            private String quality_aspect;
            private String quality_moisture;
            private String quality_insoluble;
            private String quality_solubility;
            private String quality_clContent;
            private String quality_solidContent;
            private String quality_salinity;
            private String quality_conductivity;
            private String quality_michlerKetone;
            private String shop_name;
            private String shopBean;
            private String ccrn;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public int getSales_volume() {
                return sales_volume;
            }

            public void setSales_volume(int sales_volume) {
                this.sales_volume = sales_volume;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getUser_photo() {
                return user_photo;
            }

            public void setUser_photo(String user_photo) {
                this.user_photo = user_photo;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getGoods_state() {
                return goods_state;
            }

            public void setGoods_state(String goods_state) {
                this.goods_state = goods_state;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_name_en() {
                return goods_name_en;
            }

            public void setGoods_name_en(String goods_name_en) {
                this.goods_name_en = goods_name_en;
            }

            public String getOne_type() {
                return one_type;
            }

            public void setOne_type(String one_type) {
                this.one_type = one_type;
            }

            public String getTwo_type() {
                return two_type;
            }

            public void setTwo_type(String two_type) {
                this.two_type = two_type;
            }

            public String getCino() {
                return cino;
            }

            public void setCino(String cino) {
                this.cino = cino;
            }

            public String getCas() {
                return cas;
            }

            public void setCas(String cas) {
                this.cas = cas;
            }

            public String getMolecular_weight() {
                return molecular_weight;
            }

            public void setMolecular_weight(String molecular_weight) {
                this.molecular_weight = molecular_weight;
            }

            public String getPas() {
                return pas;
            }

            public void setPas(String pas) {
                this.pas = pas;
            }

            public String getPrecise_quality() {
                return precise_quality;
            }

            public void setPrecise_quality(String precise_quality) {
                this.precise_quality = precise_quality;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public String getGoods_purity() {
                return goods_purity;
            }

            public void setGoods_purity(String goods_purity) {
                this.goods_purity = goods_purity;
            }

            public String getGoods_deliver() {
                return goods_deliver;
            }

            public void setGoods_deliver(String goods_deliver) {
                this.goods_deliver = goods_deliver;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_unit() {
                return goods_unit;
            }

            public void setGoods_unit(String goods_unit) {
                this.goods_unit = goods_unit;
            }

            public String getCurrent_price() {
                return current_price;
            }

            public void setCurrent_price(String current_price) {
                this.current_price = current_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getPayment_opt() {
                return payment_opt;
            }

            public void setPayment_opt(String payment_opt) {
                this.payment_opt = payment_opt;
            }

            public String getTransport_opt() {
                return transport_opt;
            }

            public void setTransport_opt(String transport_opt) {
                this.transport_opt = transport_opt;
            }

            public String getApplication_scheme() {
                return application_scheme;
            }

            public void setApplication_scheme(String application_scheme) {
                this.application_scheme = application_scheme;
            }

            public String getProduct_picture() {
                return product_picture;
            }

            public void setProduct_picture(String product_picture) {
                this.product_picture = product_picture;
            }

            public String getProduction_state() {
                return production_state;
            }

            public void setProduction_state(String production_state) {
                this.production_state = production_state;
            }

            public String getColor_power() {
                return color_power;
            }

            public void setColor_power(String color_power) {
                this.color_power = color_power;
            }

            public String getColor_light() {
                return color_light;
            }

            public void setColor_light(String color_light) {
                this.color_light = color_light;
            }

            public String getPackage_opt() {
                return package_opt;
            }

            public void setPackage_opt(String package_opt) {
                this.package_opt = package_opt;
            }

            public String getDetect_report() {
                return detect_report;
            }

            public void setDetect_report(String detect_report) {
                this.detect_report = detect_report;
            }

            public String getDetect_video() {
                return detect_video;
            }

            public void setDetect_video(String detect_video) {
                this.detect_video = detect_video;
            }

            public String getDetect_uv() {
                return detect_uv;
            }

            public void setDetect_uv(String detect_uv) {
                this.detect_uv = detect_uv;
            }

            public String getDetect_colourimeter() {
                return detect_colourimeter;
            }

            public void setDetect_colourimeter(String detect_colourimeter) {
                this.detect_colourimeter = detect_colourimeter;
            }

            public String getDetect_sample_imgs() {
                return detect_sample_imgs;
            }

            public void setDetect_sample_imgs(String detect_sample_imgs) {
                this.detect_sample_imgs = detect_sample_imgs;
            }

            public String getDetect_bulk_imgs() {
                return detect_bulk_imgs;
            }

            public void setDetect_bulk_imgs(String detect_bulk_imgs) {
                this.detect_bulk_imgs = detect_bulk_imgs;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
            }

            public String getSeckill_deadline() {
                return seckill_deadline;
            }

            public void setSeckill_deadline(String seckill_deadline) {
                this.seckill_deadline = seckill_deadline;
            }

            public String getMetal_cu() {
                return metal_cu;
            }

            public void setMetal_cu(String metal_cu) {
                this.metal_cu = metal_cu;
            }

            public String getMetal_zn() {
                return metal_zn;
            }

            public void setMetal_zn(String metal_zn) {
                this.metal_zn = metal_zn;
            }

            public String getMetal_ni() {
                return metal_ni;
            }

            public void setMetal_ni(String metal_ni) {
                this.metal_ni = metal_ni;
            }

            public String getMetal_sb() {
                return metal_sb;
            }

            public void setMetal_sb(String metal_sb) {
                this.metal_sb = metal_sb;
            }

            public String getMetal_cd() {
                return metal_cd;
            }

            public void setMetal_cd(String metal_cd) {
                this.metal_cd = metal_cd;
            }

            public String getMetal_pb() {
                return metal_pb;
            }

            public void setMetal_pb(String metal_pb) {
                this.metal_pb = metal_pb;
            }

            public String getMetal_sn() {
                return metal_sn;
            }

            public void setMetal_sn(String metal_sn) {
                this.metal_sn = metal_sn;
            }

            public String getMetal_co() {
                return metal_co;
            }

            public void setMetal_co(String metal_co) {
                this.metal_co = metal_co;
            }

            public String getMetal_hg() {
                return metal_hg;
            }

            public void setMetal_hg(String metal_hg) {
                this.metal_hg = metal_hg;
            }

            public String getMetal_bi() {
                return metal_bi;
            }

            public void setMetal_bi(String metal_bi) {
                this.metal_bi = metal_bi;
            }

            public String getQuality_aspect() {
                return quality_aspect;
            }

            public void setQuality_aspect(String quality_aspect) {
                this.quality_aspect = quality_aspect;
            }

            public String getQuality_moisture() {
                return quality_moisture;
            }

            public void setQuality_moisture(String quality_moisture) {
                this.quality_moisture = quality_moisture;
            }

            public String getQuality_insoluble() {
                return quality_insoluble;
            }

            public void setQuality_insoluble(String quality_insoluble) {
                this.quality_insoluble = quality_insoluble;
            }

            public String getQuality_solubility() {
                return quality_solubility;
            }

            public void setQuality_solubility(String quality_solubility) {
                this.quality_solubility = quality_solubility;
            }

            public String getQuality_clContent() {
                return quality_clContent;
            }

            public void setQuality_clContent(String quality_clContent) {
                this.quality_clContent = quality_clContent;
            }

            public String getQuality_solidContent() {
                return quality_solidContent;
            }

            public void setQuality_solidContent(String quality_solidContent) {
                this.quality_solidContent = quality_solidContent;
            }

            public String getQuality_salinity() {
                return quality_salinity;
            }

            public void setQuality_salinity(String quality_salinity) {
                this.quality_salinity = quality_salinity;
            }

            public String getQuality_conductivity() {
                return quality_conductivity;
            }

            public void setQuality_conductivity(String quality_conductivity) {
                this.quality_conductivity = quality_conductivity;
            }

            public String getQuality_michlerKetone() {
                return quality_michlerKetone;
            }

            public void setQuality_michlerKetone(String quality_michlerKetone) {
                this.quality_michlerKetone = quality_michlerKetone;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShopBean() {
                return shopBean;
            }

            public void setShopBean(String shopBean) {
                this.shopBean = shopBean;
            }

            public String getCcrn() {
                return ccrn;
            }

            public void setCcrn(String ccrn) {
                this.ccrn = ccrn;
            }
        }
    }
}
