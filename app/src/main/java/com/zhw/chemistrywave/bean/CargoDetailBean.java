package com.zhw.chemistrywave.bean;

/**
 * Created by Administrator on 2018/1/4.
 */

public class CargoDetailBean {

    /**
     * msg : success
     * code : 0
     * data : {"cargo_id":647,"cargo_others":null,"cargo_state":"1","prtp_num":"0","cargo_name":"打完水","cargo_purity":"我去二","delivery_time":"1","cargo_num":"1吨","cargo_package":"编织袋","ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":"2018-01-04 17:25:58","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":509,"pro_pic":null,"market_price":"101","current_price":null,"cino":null,"cas":null,"cn_name":null,"en_name":null,"molecular":null,"psa":null,"exact_mass":null,"first_level":null,"second_level":null,"is_discount":null,"discount":null,"is_seckill":null,"kill_price":null,"kill_start":null,"kill_limit":null,"metalBean":{"metal_id":235,"others":"水电他","cargo_id":647,"cu":"是代付个好","zn":"对方合同","ni":"爱上人员","sb":"闪电发货","cd":"啊收到过","pb":"然后给而是","sn":"输入他以为","co":"地方污染","hg":"虽然因为","bi":"暗示如果"},"qualityBean":{"quality_id":245,"intensity":"1","coloured_light":"2","appearance":"水电费","moisture":"水电费电饭锅","insoluble_substance":"电饭锅为","solubility":"点发货人daf","chloride_content":"安尔碘干啥的若干二e","solid_content":"对身体好","salinity":"如果我输入个啊输入ed","conductivity":"仿古色他","michler_ketone":"是的韩国","cargo_id":647},"reportBean":{"report_id":0,"uv_data":null,"colourimeter_data":null,"sample_picture":null,"detect_report":null,"detect_video":null,"bulk_picture":null,"production_status":null,"cargo_id":647},"is_offering":null}
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
         * cargo_id : 647
         * cargo_others : null
         * cargo_state : 1
         * prtp_num : 0
         * cargo_name : 打完水
         * cargo_purity : 我去二
         * delivery_time : 1
         * cargo_num : 1吨
         * cargo_package : 编织袋
         * ceiling_price : null
         * transport_way : null
         * payment_way : null
         * create_time : 2018-01-04 17:25:58
         * floor_price : null
         * application_scheme : null
         * product_picture : null
         * production_state : null
         * specifications : null
         * spon_id : 509
         * pro_pic : null
         * market_price : 101
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
         * metalBean : {"metal_id":235,"others":"水电他","cargo_id":647,"cu":"是代付个好","zn":"对方合同","ni":"爱上人员","sb":"闪电发货","cd":"啊收到过","pb":"然后给而是","sn":"输入他以为","co":"地方污染","hg":"虽然因为","bi":"暗示如果"}
         * qualityBean : {"quality_id":245,"intensity":"1","coloured_light":"2","appearance":"水电费","moisture":"水电费电饭锅","insoluble_substance":"电饭锅为","solubility":"点发货人daf","chloride_content":"安尔碘干啥的若干二e","solid_content":"对身体好","salinity":"如果我输入个啊输入ed","conductivity":"仿古色他","michler_ketone":"是的韩国","cargo_id":647}
         * reportBean : {"report_id":0,"uv_data":null,"colourimeter_data":null,"sample_picture":null,"detect_report":null,"detect_video":null,"bulk_picture":null,"production_status":null,"cargo_id":647}
         * is_offering : null
         */

        private int cargo_id;
        private Object cargo_others;
        private String cargo_state;
        private String prtp_num;
        private String cargo_name;
        private String cargo_purity;
        private String delivery_time;
        private String cargo_num;
        private String cargo_package;
        private Object ceiling_price;
        private Object transport_way;
        private Object payment_way;
        private String create_time;
        private Object floor_price;
        private Object application_scheme;
        private Object product_picture;
        private Object production_state;
        private Object specifications;
        private int spon_id;
        private Object pro_pic;
        private String market_price;
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

        public String getCargo_state() {
            return cargo_state;
        }

        public void setCargo_state(String cargo_state) {
            this.cargo_state = cargo_state;
        }

        public String getPrtp_num() {
            return prtp_num;
        }

        public void setPrtp_num(String prtp_num) {
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

        public Object getTransport_way() {
            return transport_way;
        }

        public void setTransport_way(Object transport_way) {
            this.transport_way = transport_way;
        }

        public Object getPayment_way() {
            return payment_way;
        }

        public void setPayment_way(Object payment_way) {
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

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
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

        public Object getIs_offering() {
            return is_offering;
        }

        public void setIs_offering(Object is_offering) {
            this.is_offering = is_offering;
        }

        public static class MetalBeanBean {
            /**
             * metal_id : 235
             * others : 水电他
             * cargo_id : 647
             * cu : 是代付个好
             * zn : 对方合同
             * ni : 爱上人员
             * sb : 闪电发货
             * cd : 啊收到过
             * pb : 然后给而是
             * sn : 输入他以为
             * co : 地方污染
             * hg : 虽然因为
             * bi : 暗示如果
             */

            private int metal_id;
            private String others;
            private int cargo_id;
            private String cu;
            private String zn;
            private String ni;
            private String sb;
            private String cd;
            private String pb;
            private String sn;
            private String co;
            private String hg;
            private String bi;

            public int getMetal_id() {
                return metal_id;
            }

            public void setMetal_id(int metal_id) {
                this.metal_id = metal_id;
            }

            public String getOthers() {
                return others;
            }

            public void setOthers(String others) {
                this.others = others;
            }

            public int getCargo_id() {
                return cargo_id;
            }

            public void setCargo_id(int cargo_id) {
                this.cargo_id = cargo_id;
            }

            public String getCu() {
                return cu;
            }

            public void setCu(String cu) {
                this.cu = cu;
            }

            public String getZn() {
                return zn;
            }

            public void setZn(String zn) {
                this.zn = zn;
            }

            public String getNi() {
                return ni;
            }

            public void setNi(String ni) {
                this.ni = ni;
            }

            public String getSb() {
                return sb;
            }

            public void setSb(String sb) {
                this.sb = sb;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getPb() {
                return pb;
            }

            public void setPb(String pb) {
                this.pb = pb;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getHg() {
                return hg;
            }

            public void setHg(String hg) {
                this.hg = hg;
            }

            public String getBi() {
                return bi;
            }

            public void setBi(String bi) {
                this.bi = bi;
            }
        }

        public static class QualityBeanBean {
            /**
             * quality_id : 245
             * intensity : 1
             * coloured_light : 2
             * appearance : 水电费
             * moisture : 水电费电饭锅
             * insoluble_substance : 电饭锅为
             * solubility : 点发货人daf
             * chloride_content : 安尔碘干啥的若干二e
             * solid_content : 对身体好
             * salinity : 如果我输入个啊输入ed
             * conductivity : 仿古色他
             * michler_ketone : 是的韩国
             * cargo_id : 647
             */

            private int quality_id;
            private String intensity;
            private String coloured_light;
            private String appearance;
            private String moisture;
            private String insoluble_substance;
            private String solubility;
            private String chloride_content;
            private String solid_content;
            private String salinity;
            private String conductivity;
            private String michler_ketone;
            private int cargo_id;

            public int getQuality_id() {
                return quality_id;
            }

            public void setQuality_id(int quality_id) {
                this.quality_id = quality_id;
            }

            public String getIntensity() {
                return intensity;
            }

            public void setIntensity(String intensity) {
                this.intensity = intensity;
            }

            public String getColoured_light() {
                return coloured_light;
            }

            public void setColoured_light(String coloured_light) {
                this.coloured_light = coloured_light;
            }

            public String getAppearance() {
                return appearance;
            }

            public void setAppearance(String appearance) {
                this.appearance = appearance;
            }

            public String getMoisture() {
                return moisture;
            }

            public void setMoisture(String moisture) {
                this.moisture = moisture;
            }

            public String getInsoluble_substance() {
                return insoluble_substance;
            }

            public void setInsoluble_substance(String insoluble_substance) {
                this.insoluble_substance = insoluble_substance;
            }

            public String getSolubility() {
                return solubility;
            }

            public void setSolubility(String solubility) {
                this.solubility = solubility;
            }

            public String getChloride_content() {
                return chloride_content;
            }

            public void setChloride_content(String chloride_content) {
                this.chloride_content = chloride_content;
            }

            public String getSolid_content() {
                return solid_content;
            }

            public void setSolid_content(String solid_content) {
                this.solid_content = solid_content;
            }

            public String getSalinity() {
                return salinity;
            }

            public void setSalinity(String salinity) {
                this.salinity = salinity;
            }

            public String getConductivity() {
                return conductivity;
            }

            public void setConductivity(String conductivity) {
                this.conductivity = conductivity;
            }

            public String getMichler_ketone() {
                return michler_ketone;
            }

            public void setMichler_ketone(String michler_ketone) {
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
             * cargo_id : 647
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
