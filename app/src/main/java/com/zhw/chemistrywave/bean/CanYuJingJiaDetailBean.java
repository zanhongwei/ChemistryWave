package com.zhw.chemistrywave.bean;

/**
 * Created by Administrator on 2018/1/12.
 */

public class CanYuJingJiaDetailBean {

    /**
     * code : 0
     * data : {"cargo_id":807,"cargo_others":null,"cargo_state":null,"prtp_num":null,"cargo_name":"2","cargo_purity":"2","delivery_time":"2","cargo_num":"2","cargo_package":"2","ceiling_price":null,"transport_way":"2","payment_way":"offline","create_time":null,"floor_price":"2","application_scheme":"upload/1/20180112095414805.jpg","product_picture":"upload/1/20180112095418311.jpg","production_state":"upload/1/20180112095422171.jpg","specifications":null,"spon_id":0,"pro_pic":null,"market_price":null,"current_price":null,"cino":null,"cas":null,"cn_name":null,"en_name":null,"molecular":null,"psa":null,"exact_mass":null,"first_level":null,"second_level":null,"is_discount":null,"discount":null,"is_seckill":null,"kill_price":null,"kill_start":null,"kill_limit":null,"metalBean":{"metal_id":0,"others":null,"cargo_id":807,"cu":null,"zn":null,"ni":null,"sb":null,"cd":null,"pb":null,"sn":null,"co":null,"hg":null,"bi":null},"qualityBean":{"quality_id":0,"intensity":null,"coloured_light":null,"appearance":null,"moisture":null,"insoluble_substance":null,"solubility":null,"chloride_content":null,"solid_content":null,"salinity":null,"conductivity":null,"michler_ketone":null,"cargo_id":807},"reportBean":{"report_id":0,"uv_data":null,"colourimeter_data":null,"sample_picture":null,"detect_report":null,"detect_video":null,"bulk_picture":null,"production_status":null,"cargo_id":807},"user_id":1,"user_name":"张","user_type":"1","user_photo":"upload/1/20180106171739651.jpg","is_offering":null}
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
         * cargo_id : 807
         * cargo_others : null
         * cargo_state : null
         * prtp_num : null
         * cargo_name : 2
         * cargo_purity : 2
         * delivery_time : 2
         * cargo_num : 2
         * cargo_package : 2
         * ceiling_price : null
         * transport_way : 2
         * payment_way : offline
         * create_time : null
         * floor_price : 2
         * application_scheme : upload/1/20180112095414805.jpg
         * product_picture : upload/1/20180112095418311.jpg
         * production_state : upload/1/20180112095422171.jpg
         * specifications : null
         * spon_id : 0
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
         * metalBean : {"metal_id":0,"others":null,"cargo_id":807,"cu":null,"zn":null,"ni":null,"sb":null,"cd":null,"pb":null,"sn":null,"co":null,"hg":null,"bi":null}
         * qualityBean : {"quality_id":0,"intensity":null,"coloured_light":null,"appearance":null,"moisture":null,"insoluble_substance":null,"solubility":null,"chloride_content":null,"solid_content":null,"salinity":null,"conductivity":null,"michler_ketone":null,"cargo_id":807}
         * reportBean : {"report_id":0,"uv_data":null,"colourimeter_data":null,"sample_picture":null,"detect_report":null,"detect_video":null,"bulk_picture":null,"production_status":null,"cargo_id":807}
         * user_id : 1
         * user_name : 张
         * user_type : 1
         * user_photo : upload/1/20180106171739651.jpg
         * is_offering : null
         */

        private int cargo_id;
        private Object cargo_others;
        private Object cargo_state;
        private Object prtp_num;
        private String cargo_name;
        private String cargo_purity;
        private String delivery_time;
        private String cargo_num;
        private String cargo_package;
        private Object ceiling_price;
        private String transport_way;
        private String payment_way;
        private Object create_time;
        private String floor_price;
        private String application_scheme;
        private String product_picture;
        private String production_state;
        private Object specifications;
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
        private MetalBeanBean metalBean;
        private QualityBeanBean qualityBean;
        private ReportBeanBean reportBean;
        private int user_id;
        private String user_name;
        private String user_type;
        private String user_photo;
        private Object is_offering;

        public int getCargo_id() {
            return cargo_id;
        }

        public void setCargo_id(int cargo_id) {
            this.cargo_id = cargo_id;
        }

        public Object getCargo_others() {
            return cargo_others;
        }

        public void setCargo_others(Object cargo_others) {
            this.cargo_others = cargo_others;
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

        public Object getCeiling_price() {
            return ceiling_price;
        }

        public void setCeiling_price(Object ceiling_price) {
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

        public Object getCreate_time() {
            return create_time;
        }

        public void setCreate_time(Object create_time) {
            this.create_time = create_time;
        }

        public String getFloor_price() {
            return floor_price;
        }

        public void setFloor_price(String floor_price) {
            this.floor_price = floor_price;
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

        public Object getSpecifications() {
            return specifications;
        }

        public void setSpecifications(Object specifications) {
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

        public MetalBeanBean getMetalBean() {
            return metalBean;
        }

        public void setMetalBean(MetalBeanBean metalBean) {
            this.metalBean = metalBean;
        }

        public QualityBeanBean getQualityBean() {
            return qualityBean;
        }

        public void setQualityBean(QualityBeanBean qualityBean) {
            this.qualityBean = qualityBean;
        }

        public ReportBeanBean getReportBean() {
            return reportBean;
        }

        public void setReportBean(ReportBeanBean reportBean) {
            this.reportBean = reportBean;
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

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getUser_photo() {
            return user_photo;
        }

        public void setUser_photo(String user_photo) {
            this.user_photo = user_photo;
        }

        public Object getIs_offering() {
            return is_offering;
        }

        public void setIs_offering(Object is_offering) {
            this.is_offering = is_offering;
        }

        public static class MetalBeanBean {
            /**
             * metal_id : 0
             * others : null
             * cargo_id : 807
             * cu : null
             * zn : null
             * ni : null
             * sb : null
             * cd : null
             * pb : null
             * sn : null
             * co : null
             * hg : null
             * bi : null
             */

            private int metal_id;
            private Object others;
            private int cargo_id;
            private Object cu;
            private Object zn;
            private Object ni;
            private Object sb;
            private Object cd;
            private Object pb;
            private Object sn;
            private Object co;
            private Object hg;
            private Object bi;

            public int getMetal_id() {
                return metal_id;
            }

            public void setMetal_id(int metal_id) {
                this.metal_id = metal_id;
            }

            public Object getOthers() {
                return others;
            }

            public void setOthers(Object others) {
                this.others = others;
            }

            public int getCargo_id() {
                return cargo_id;
            }

            public void setCargo_id(int cargo_id) {
                this.cargo_id = cargo_id;
            }

            public Object getCu() {
                return cu;
            }

            public void setCu(Object cu) {
                this.cu = cu;
            }

            public Object getZn() {
                return zn;
            }

            public void setZn(Object zn) {
                this.zn = zn;
            }

            public Object getNi() {
                return ni;
            }

            public void setNi(Object ni) {
                this.ni = ni;
            }

            public Object getSb() {
                return sb;
            }

            public void setSb(Object sb) {
                this.sb = sb;
            }

            public Object getCd() {
                return cd;
            }

            public void setCd(Object cd) {
                this.cd = cd;
            }

            public Object getPb() {
                return pb;
            }

            public void setPb(Object pb) {
                this.pb = pb;
            }

            public Object getSn() {
                return sn;
            }

            public void setSn(Object sn) {
                this.sn = sn;
            }

            public Object getCo() {
                return co;
            }

            public void setCo(Object co) {
                this.co = co;
            }

            public Object getHg() {
                return hg;
            }

            public void setHg(Object hg) {
                this.hg = hg;
            }

            public Object getBi() {
                return bi;
            }

            public void setBi(Object bi) {
                this.bi = bi;
            }
        }

        public static class QualityBeanBean {
            /**
             * quality_id : 0
             * intensity : null
             * coloured_light : null
             * appearance : null
             * moisture : null
             * insoluble_substance : null
             * solubility : null
             * chloride_content : null
             * solid_content : null
             * salinity : null
             * conductivity : null
             * michler_ketone : null
             * cargo_id : 807
             */

            private int quality_id;
            private Object intensity;
            private Object coloured_light;
            private Object appearance;
            private Object moisture;
            private Object insoluble_substance;
            private Object solubility;
            private Object chloride_content;
            private Object solid_content;
            private Object salinity;
            private Object conductivity;
            private Object michler_ketone;
            private int cargo_id;

            public int getQuality_id() {
                return quality_id;
            }

            public void setQuality_id(int quality_id) {
                this.quality_id = quality_id;
            }

            public Object getIntensity() {
                return intensity;
            }

            public void setIntensity(Object intensity) {
                this.intensity = intensity;
            }

            public Object getColoured_light() {
                return coloured_light;
            }

            public void setColoured_light(Object coloured_light) {
                this.coloured_light = coloured_light;
            }

            public Object getAppearance() {
                return appearance;
            }

            public void setAppearance(Object appearance) {
                this.appearance = appearance;
            }

            public Object getMoisture() {
                return moisture;
            }

            public void setMoisture(Object moisture) {
                this.moisture = moisture;
            }

            public Object getInsoluble_substance() {
                return insoluble_substance;
            }

            public void setInsoluble_substance(Object insoluble_substance) {
                this.insoluble_substance = insoluble_substance;
            }

            public Object getSolubility() {
                return solubility;
            }

            public void setSolubility(Object solubility) {
                this.solubility = solubility;
            }

            public Object getChloride_content() {
                return chloride_content;
            }

            public void setChloride_content(Object chloride_content) {
                this.chloride_content = chloride_content;
            }

            public Object getSolid_content() {
                return solid_content;
            }

            public void setSolid_content(Object solid_content) {
                this.solid_content = solid_content;
            }

            public Object getSalinity() {
                return salinity;
            }

            public void setSalinity(Object salinity) {
                this.salinity = salinity;
            }

            public Object getConductivity() {
                return conductivity;
            }

            public void setConductivity(Object conductivity) {
                this.conductivity = conductivity;
            }

            public Object getMichler_ketone() {
                return michler_ketone;
            }

            public void setMichler_ketone(Object michler_ketone) {
                this.michler_ketone = michler_ketone;
            }

            public int getCargo_id() {
                return cargo_id;
            }

            public void setCargo_id(int cargo_id) {
                this.cargo_id = cargo_id;
            }
        }

        public static class ReportBeanBean {
            /**
             * report_id : 0
             * uv_data : null
             * colourimeter_data : null
             * sample_picture : null
             * detect_report : null
             * detect_video : null
             * bulk_picture : null
             * production_status : null
             * cargo_id : 807
             */

            private int report_id;
            private Object uv_data;
            private Object colourimeter_data;
            private Object sample_picture;
            private Object detect_report;
            private Object detect_video;
            private Object bulk_picture;
            private Object production_status;
            private int cargo_id;

            public int getReport_id() {
                return report_id;
            }

            public void setReport_id(int report_id) {
                this.report_id = report_id;
            }

            public Object getUv_data() {
                return uv_data;
            }

            public void setUv_data(Object uv_data) {
                this.uv_data = uv_data;
            }

            public Object getColourimeter_data() {
                return colourimeter_data;
            }

            public void setColourimeter_data(Object colourimeter_data) {
                this.colourimeter_data = colourimeter_data;
            }

            public Object getSample_picture() {
                return sample_picture;
            }

            public void setSample_picture(Object sample_picture) {
                this.sample_picture = sample_picture;
            }

            public Object getDetect_report() {
                return detect_report;
            }

            public void setDetect_report(Object detect_report) {
                this.detect_report = detect_report;
            }

            public Object getDetect_video() {
                return detect_video;
            }

            public void setDetect_video(Object detect_video) {
                this.detect_video = detect_video;
            }

            public Object getBulk_picture() {
                return bulk_picture;
            }

            public void setBulk_picture(Object bulk_picture) {
                this.bulk_picture = bulk_picture;
            }

            public Object getProduction_status() {
                return production_status;
            }

            public void setProduction_status(Object production_status) {
                this.production_status = production_status;
            }

            public int getCargo_id() {
                return cargo_id;
            }

            public void setCargo_id(int cargo_id) {
                this.cargo_id = cargo_id;
            }
        }
    }
}
