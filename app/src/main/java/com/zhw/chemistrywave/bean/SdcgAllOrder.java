package com.zhw.chemistrywave.bean;

import android.os.Parcel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by axehome on 2017/12/13.
 */

public class SdcgAllOrder {


    /**
     * code : 0
     * data : {"totalCount":41,"list":[{"spon_id":1,"spon_serl":null,"sponsor_id":null,"spon_state":"1","spon_type":null,"create_time":null,"winning_time":null,"prtp_num":"0","sopn_others":null,"cargoBeans":[{"cargo_id":0,"cargo_name":null,"cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":null,"floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":1}]},{"spon_id":2,"spon_serl":null,"sponsor_id":null,"spon_state":"1","spon_type":null,"create_time":null,"winning_time":null,"prtp_num":"0","sopn_others":null,"cargoBeans":[{"cargo_id":2,"cargo_name":"asdasd","cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":null,"floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":2}]},{"spon_id":3,"spon_serl":"1513996930620383f","sponsor_id":"5","spon_state":"2","spon_type":"2","create_time":"2017-12-23 10:42:10","winning_time":null,"prtp_num":"1","sopn_others":null,"cargoBeans":[{"cargo_id":0,"cargo_name":null,"cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":"2017-12-23 10:42:10","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":3}]}]}
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
         * totalCount : 41
         * list : [{"spon_id":1,"spon_serl":null,"sponsor_id":null,"spon_state":"1","spon_type":null,"create_time":null,"winning_time":null,"prtp_num":"0","sopn_others":null,"cargoBeans":[{"cargo_id":0,"cargo_name":null,"cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":null,"floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":1}]},{"spon_id":2,"spon_serl":null,"sponsor_id":null,"spon_state":"1","spon_type":null,"create_time":null,"winning_time":null,"prtp_num":"0","sopn_others":null,"cargoBeans":[{"cargo_id":2,"cargo_name":"asdasd","cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":null,"floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":2}]},{"spon_id":3,"spon_serl":"1513996930620383f","sponsor_id":"5","spon_state":"2","spon_type":"2","create_time":"2017-12-23 10:42:10","winning_time":null,"prtp_num":"1","sopn_others":null,"cargoBeans":[{"cargo_id":0,"cargo_name":null,"cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":"2017-12-23 10:42:10","floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":3}]}]
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

        public static class ListBean  implements Serializable {
            @Override
            public String toString() {
                return "ListBean{" +
                        "spon_id=" + spon_id +
                        ", spon_serl=" + spon_serl +
                        ", sponsor_id=" + sponsor_id +
                        ", spon_state='" + spon_state + '\'' +
                        ", spon_type=" + spon_type +
                        ", create_time=" + create_time +
                        ", winning_time=" + winning_time +
                        ", prtp_num='" + prtp_num + '\'' +
                        ", sopn_others=" + sopn_others +
                        ", cargoBeans=" + cargoBeans +
                        '}';
            }

            /**
             * spon_id : 1
             * spon_serl : null
             * sponsor_id : null
             * spon_state : 1
             * spon_type : null
             * create_time : null
             * winning_time : null
             * prtp_num : 0
             * sopn_others : null
             * cargoBeans : [{"cargo_id":0,"cargo_name":null,"cargo_purity":null,"delivery_time":null,"cargo_num":null,"cargo_package":null,"ceiling_price":null,"transport_way":null,"payment_way":null,"create_time":null,"floor_price":null,"application_scheme":null,"product_picture":null,"production_state":null,"specifications":null,"spon_id":1}]
             */

            private int spon_id;
            private String spon_serl;
            private String sponsor_id;
            private String spon_state;
            private String spon_type;
            private String create_time;
            private Object winning_time;
            private String prtp_num;
            private Object sopn_others;
            private List<CargoBeansBean> cargoBeans;

            protected ListBean(Parcel in) {
                spon_id = in.readInt();
                spon_state = in.readString();
                prtp_num = in.readString();
            }


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

            public String getPrtp_num() {
                return prtp_num;
            }

            public void setPrtp_num(String prtp_num) {
                this.prtp_num = prtp_num;
            }

            public Object getSopn_others() {
                return sopn_others;
            }

            public void setSopn_others(Object sopn_others) {
                this.sopn_others = sopn_others;
            }

            public List<CargoBeansBean> getCargoBeans() {
                return cargoBeans;
            }

            public void setCargoBeans(List<CargoBeansBean> cargoBeans) {
                this.cargoBeans = cargoBeans;
            }



            public static class CargoBeansBean  implements Serializable {
                /**
                 * cargo_id : 0
                 * cargo_name : null
                 * cargo_purity : null
                 * delivery_time : null
                 * cargo_num : null
                 * cargo_package : null
                 * ceiling_price : null
                 * transport_way : null
                 * payment_way : null
                 * create_time : null
                 * floor_price : null
                 * application_scheme : null
                 * product_picture : null
                 * production_state : null
                 * specifications : null
                 * spon_id : 1
                 */

                private int cargo_id;
                private String cargo_name;
                private String cargo_purity;
                private String delivery_time;
                private String cargo_num;
                private String cargo_package;
                private String ceiling_price;
                private String transport_way;
                private String payment_way;
                private String create_time;
                private String floor_price;
                private String application_scheme;
                private String product_picture;
                private String production_state;
                private String specifications;
                private String cargo_state;
                private int spon_id;
                private int quality_id;
                private int metal_id;

                public String getCargo_state() {
                    return cargo_state;
                }

                public void setCargo_state(String cargo_state) {
                    this.cargo_state = cargo_state;
                }

                protected CargoBeansBean(Parcel in) {
                    cargo_id = in.readInt();
                    cargo_name = in.readString();
                    cargo_purity = in.readString();
                    delivery_time = in.readString();
                    cargo_num = in.readString();
                    cargo_package = in.readString();
                    ceiling_price = in.readString();
                    transport_way = in.readString();
                    payment_way = in.readString();
                    create_time = in.readString();
                    floor_price = in.readString();
                    application_scheme = in.readString();
                    product_picture = in.readString();
                    production_state = in.readString();
                    specifications = in.readString();
                    spon_id = in.readInt();
                }


                public int getQuality_id() {
                    return quality_id;
                }

                public void setQuality_id(int quality_id) {
                    this.quality_id = quality_id;
                }

                public int getMetal_id() {
                    return metal_id;
                }

                public void setMetal_id(int metal_id) {
                    this.metal_id = metal_id;
                }

                public int getCargo_id() {
                    return cargo_id;
                }

                public void setCargo_id(int cargo_id) {
                    this.cargo_id = cargo_id;
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




            }
        }
    }
}
