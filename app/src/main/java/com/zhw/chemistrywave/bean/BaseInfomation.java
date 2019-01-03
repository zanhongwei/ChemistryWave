package com.zhw.chemistrywave.bean;

import java.io.Serializable;

/**
 * Created by zhw on 2018/7/27.
 */

public class BaseInfomation implements Serializable{

    private String cargo_name;
    private String cargo_purity;
    private String delivery_time;
    private String cargo_num;
    private String cargo_package;
    private String transport_way;
    private String ceiling_price;
    private String payment_way;
    private String specifications;

    public BaseInfomation(String cargo_name, String cargo_purity, String delivery_time, String cargo_num, String cargo_package, String transport_way, String ceiling_price, String payment_way, String specifications) {
        this.cargo_name = cargo_name;
        this.cargo_purity = cargo_purity;
        this.delivery_time = delivery_time;
        this.cargo_num = cargo_num;
        this.cargo_package = cargo_package;
        this.transport_way = transport_way;
        this.ceiling_price = ceiling_price;
        this.payment_way = payment_way;
        this.specifications = specifications;
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

    public String getTransport_way() {
        return transport_way;
    }

    public void setTransport_way(String transport_way) {
        this.transport_way = transport_way;
    }

    public String getCeiling_price() {
        return ceiling_price;
    }

    public void setCeiling_price(String ceiling_price) {
        this.ceiling_price = ceiling_price;
    }

    public String getPayment_way() {
        return payment_way;
    }

    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
}
