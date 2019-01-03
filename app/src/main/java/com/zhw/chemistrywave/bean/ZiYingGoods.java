package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/1/20.
 */

public class ZiYingGoods {


    /**
     * msg : success
     * code : 0
     * data : {"totalCount":2,"list":[{"mer_id":14,"mer_type":"1","user_id":null,"user_name":null,"user_photo":null,"user_type":null,"mer_state":"1","cargo_name":"1","cargo_Ename":"1","one_type":"2","two_type":"3","cino":"1","cas":"1","molecular_weight":"1","pas":"1","precise_quality":null,"cargo_specification":"1","cargo_purity":"1","cargo_huoqi":"1","cargo_num":"1","current_price":"1","market_price":"1","payment_way":"alipay","transport_way":"1","application_scheme":null,"product_picture":null,"production_state":null,"intensity":"1","coloured_light":"1","cargo_package":"2","detect_report":null,"detect_video":null,"uv_data":null,"colourimeter_data":null,"sample_images":null,"big_photos":null,"create_time":"2018-01-20 11:16:47","is_discount":null,"discount":null,"is_seckill":"2","kill_price":"3","kill_start":null,"kill_limit":null,"mer_cu":"11","mer_zn":"1","mer_ni":"1","mer_sb":"1","mer_cd":"1","mer_pb":"1","mer_sn":"1","mer_co":null,"mer_hg":null,"mer_bi":null,"appearance":null,"moisture":null,"insoluble_substance":null,"solubility":null,"chloride_content":null,"solid_content":null,"salinity":null,"conductivity":null,"michler_ketone":"1"},{"mer_id":13,"mer_type":"1","user_id":null,"user_name":null,"user_photo":null,"user_type":null,"mer_state":"1","cargo_name":"测试","cargo_Ename":"test","one_type":"1","two_type":"1","cino":"1","cas":"1","molecular_weight":"1","pas":"1","precise_quality":"1","cargo_specification":"1","cargo_purity":"1","cargo_huoqi":"1","cargo_num":"1","current_price":"1","market_price":"1","payment_way":"alipay","transport_way":"1","application_scheme":null,"product_picture":"upload/null/20180120104801131.jpg","production_state":null,"intensity":"1","coloured_light":"1","cargo_package":"1","detect_report":null,"detect_video":null,"uv_data":null,"colourimeter_data":null,"sample_images":null,"big_photos":null,"create_time":"2018-01-20 10:48:05","is_discount":null,"discount":null,"is_seckill":"2","kill_price":"3","kill_start":null,"kill_limit":null,"mer_cu":"1","mer_zn":"1","mer_ni":"1","mer_sb":"1","mer_cd":"1","mer_pb":"1","mer_sn":"1","mer_co":"1","mer_hg":"1","mer_bi":"1","appearance":"1","moisture":"1","insoluble_substance":"1","solubility":"1","chloride_content":"1","solid_content":"1","salinity":"1","conductivity":"1","michler_ketone":"1"}],"pageSize":10000,"currPage":1,"totalPage":1}
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
         * list : [{"mer_id":14,"mer_type":"1","user_id":null,"user_name":null,"user_photo":null,"user_type":null,"mer_state":"1","cargo_name":"1","cargo_Ename":"1","one_type":"2","two_type":"3","cino":"1","cas":"1","molecular_weight":"1","pas":"1","precise_quality":null,"cargo_specification":"1","cargo_purity":"1","cargo_huoqi":"1","cargo_num":"1","current_price":"1","market_price":"1","payment_way":"alipay","transport_way":"1","application_scheme":null,"product_picture":null,"production_state":null,"intensity":"1","coloured_light":"1","cargo_package":"2","detect_report":null,"detect_video":null,"uv_data":null,"colourimeter_data":null,"sample_images":null,"big_photos":null,"create_time":"2018-01-20 11:16:47","is_discount":null,"discount":null,"is_seckill":"2","kill_price":"3","kill_start":null,"kill_limit":null,"mer_cu":"11","mer_zn":"1","mer_ni":"1","mer_sb":"1","mer_cd":"1","mer_pb":"1","mer_sn":"1","mer_co":null,"mer_hg":null,"mer_bi":null,"appearance":null,"moisture":null,"insoluble_substance":null,"solubility":null,"chloride_content":null,"solid_content":null,"salinity":null,"conductivity":null,"michler_ketone":"1"},{"mer_id":13,"mer_type":"1","user_id":null,"user_name":null,"user_photo":null,"user_type":null,"mer_state":"1","cargo_name":"测试","cargo_Ename":"test","one_type":"1","two_type":"1","cino":"1","cas":"1","molecular_weight":"1","pas":"1","precise_quality":"1","cargo_specification":"1","cargo_purity":"1","cargo_huoqi":"1","cargo_num":"1","current_price":"1","market_price":"1","payment_way":"alipay","transport_way":"1","application_scheme":null,"product_picture":"upload/null/20180120104801131.jpg","production_state":null,"intensity":"1","coloured_light":"1","cargo_package":"1","detect_report":null,"detect_video":null,"uv_data":null,"colourimeter_data":null,"sample_images":null,"big_photos":null,"create_time":"2018-01-20 10:48:05","is_discount":null,"discount":null,"is_seckill":"2","kill_price":"3","kill_start":null,"kill_limit":null,"mer_cu":"1","mer_zn":"1","mer_ni":"1","mer_sb":"1","mer_cd":"1","mer_pb":"1","mer_sn":"1","mer_co":"1","mer_hg":"1","mer_bi":"1","appearance":"1","moisture":"1","insoluble_substance":"1","solubility":"1","chloride_content":"1","solid_content":"1","salinity":"1","conductivity":"1","michler_ketone":"1"}]
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

        public static class ListBean {
            /**
             * mer_id : 14
             * mer_type : 1
             * user_id : null
             * user_name : null
             * user_photo : null
             * user_type : null
             * mer_state : 1
             * cargo_name : 1
             * cargo_Ename : 1
             * one_type : 2
             * two_type : 3
             * cino : 1
             * cas : 1
             * molecular_weight : 1
             * pas : 1
             * precise_quality : null
             * cargo_specification : 1
             * cargo_purity : 1
             * cargo_huoqi : 1
             * cargo_num : 1
             * current_price : 1
             * market_price : 1
             * payment_way : alipay
             * transport_way : 1
             * application_scheme : null
             * product_picture : null
             * production_state : null
             * intensity : 1
             * coloured_light : 1
             * cargo_package : 2
             * detect_report : null
             * detect_video : null
             * uv_data : null
             * colourimeter_data : null
             * sample_images : null
             * big_photos : null
             * create_time : 2018-01-20 11:16:47
             * is_discount : null
             * discount : null
             * is_seckill : 2
             * kill_price : 3
             * kill_start : null
             * kill_limit : null
             * mer_cu : 11
             * mer_zn : 1
             * mer_ni : 1
             * mer_sb : 1
             * mer_cd : 1
             * mer_pb : 1
             * mer_sn : 1
             * mer_co : null
             * mer_hg : null
             * mer_bi : null
             * appearance : null
             * moisture : null
             * insoluble_substance : null
             * solubility : null
             * chloride_content : null
             * solid_content : null
             * salinity : null
             * conductivity : null
             * michler_ketone : 1
             */

            private int mer_id;
            private String mer_type;
            private String user_id;
            private String user_name;
            private String user_photo;
            private String user_type;
            private String mer_state;
            private String cargo_name;
            private String cargo_Ename;
            private String one_type;
            private String two_type;
            private String cino;
            private String cas;
            private String molecular_weight;
            private String pas;
            private String precise_quality;
            private String cargo_specification;
            private String cargo_purity;
            private String cargo_huoqi;
            private String cargo_num;
            private String current_price;
            private String market_price;
            private String payment_way;
            private String transport_way;
            private String application_scheme;
            private String product_picture;
            private String production_state;
            private String intensity;
            private String coloured_light;
            private String cargo_package;
            private String detect_report;
            private String detect_video;
            private String uv_data;
            private String colourimeter_data;
            private String sample_images;
            private String big_photos;
            private String create_time;
            private String is_discount;
            private String discount;
            private String is_seckill;
            private String kill_price;
            private String kill_start;
            private String kill_limit;
            private String mer_cu;
            private String mer_zn;
            private String mer_ni;
            private String mer_sb;
            private String mer_cd;
            private String mer_pb;
            private String mer_sn;
            private String mer_co;
            private String mer_hg;
            private String mer_bi;
            private String appearance;
            private String moisture;
            private String insoluble_substance;
            private String solubility;
            private String chloride_content;
            private String solid_content;
            private String salinity;
            private String conductivity;
            private String michler_ketone;

            public int getMer_id() {
                return mer_id;
            }

            public void setMer_id(int mer_id) {
                this.mer_id = mer_id;
            }

            public String getMer_type() {
                return mer_type;
            }

            public void setMer_type(String mer_type) {
                this.mer_type = mer_type;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
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

            public String getMer_state() {
                return mer_state;
            }

            public void setMer_state(String mer_state) {
                this.mer_state = mer_state;
            }

            public String getCargo_name() {
                return cargo_name;
            }

            public void setCargo_name(String cargo_name) {
                this.cargo_name = cargo_name;
            }

            public String getCargo_Ename() {
                return cargo_Ename;
            }

            public void setCargo_Ename(String cargo_Ename) {
                this.cargo_Ename = cargo_Ename;
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

            public String getCargo_specification() {
                return cargo_specification;
            }

            public void setCargo_specification(String cargo_specification) {
                this.cargo_specification = cargo_specification;
            }

            public String getCargo_purity() {
                return cargo_purity;
            }

            public void setCargo_purity(String cargo_purity) {
                this.cargo_purity = cargo_purity;
            }

            public String getCargo_huoqi() {
                return cargo_huoqi;
            }

            public void setCargo_huoqi(String cargo_huoqi) {
                this.cargo_huoqi = cargo_huoqi;
            }

            public String getCargo_num() {
                return cargo_num;
            }

            public void setCargo_num(String cargo_num) {
                this.cargo_num = cargo_num;
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

            public String getPayment_way() {
                return payment_way;
            }

            public void setPayment_way(String payment_way) {
                this.payment_way = payment_way;
            }

            public String getTransport_way() {
                return transport_way;
            }

            public void setTransport_way(String transport_way) {
                this.transport_way = transport_way;
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

            public String getCargo_package() {
                return cargo_package;
            }

            public void setCargo_package(String cargo_package) {
                this.cargo_package = cargo_package;
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

            public String getUv_data() {
                return uv_data;
            }

            public void setUv_data(String uv_data) {
                this.uv_data = uv_data;
            }

            public String getColourimeter_data() {
                return colourimeter_data;
            }

            public void setColourimeter_data(String colourimeter_data) {
                this.colourimeter_data = colourimeter_data;
            }

            public String getSample_images() {
                return sample_images;
            }

            public void setSample_images(String sample_images) {
                this.sample_images = sample_images;
            }

            public String getBig_photos() {
                return big_photos;
            }

            public void setBig_photos(String big_photos) {
                this.big_photos = big_photos;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getIs_discount() {
                return is_discount;
            }

            public void setIs_discount(String is_discount) {
                this.is_discount = is_discount;
            }

            public String getDiscount() {
                return discount;
            }

            public void setDiscount(String discount) {
                this.discount = discount;
            }

            public String getIs_seckill() {
                return is_seckill;
            }

            public void setIs_seckill(String is_seckill) {
                this.is_seckill = is_seckill;
            }

            public String getKill_price() {
                return kill_price;
            }

            public void setKill_price(String kill_price) {
                this.kill_price = kill_price;
            }

            public String getKill_start() {
                return kill_start;
            }

            public void setKill_start(String kill_start) {
                this.kill_start = kill_start;
            }

            public String getKill_limit() {
                return kill_limit;
            }

            public void setKill_limit(String kill_limit) {
                this.kill_limit = kill_limit;
            }

            public String getMer_cu() {
                return mer_cu;
            }

            public void setMer_cu(String mer_cu) {
                this.mer_cu = mer_cu;
            }

            public String getMer_zn() {
                return mer_zn;
            }

            public void setMer_zn(String mer_zn) {
                this.mer_zn = mer_zn;
            }

            public String getMer_ni() {
                return mer_ni;
            }

            public void setMer_ni(String mer_ni) {
                this.mer_ni = mer_ni;
            }

            public String getMer_sb() {
                return mer_sb;
            }

            public void setMer_sb(String mer_sb) {
                this.mer_sb = mer_sb;
            }

            public String getMer_cd() {
                return mer_cd;
            }

            public void setMer_cd(String mer_cd) {
                this.mer_cd = mer_cd;
            }

            public String getMer_pb() {
                return mer_pb;
            }

            public void setMer_pb(String mer_pb) {
                this.mer_pb = mer_pb;
            }

            public String getMer_sn() {
                return mer_sn;
            }

            public void setMer_sn(String mer_sn) {
                this.mer_sn = mer_sn;
            }

            public String getMer_co() {
                return mer_co;
            }

            public void setMer_co(String mer_co) {
                this.mer_co = mer_co;
            }

            public String getMer_hg() {
                return mer_hg;
            }

            public void setMer_hg(String mer_hg) {
                this.mer_hg = mer_hg;
            }

            public String getMer_bi() {
                return mer_bi;
            }

            public void setMer_bi(String mer_bi) {
                this.mer_bi = mer_bi;
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
        }
    }
}
