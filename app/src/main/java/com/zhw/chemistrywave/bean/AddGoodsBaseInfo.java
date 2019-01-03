package com.zhw.chemistrywave.bean;

import java.io.Serializable;

/**
 * Created by zhw on 2018/7/28.
 */

public class AddGoodsBaseInfo implements Serializable{

    private String cargo_name;
    private String cargo_specification;
    private String one_type;
    private String two_type;
    private String cino;
    private String cas;
    private String molecular_weight;
    private String pas;
    private String precise_quality;
    private String cargo_purity;
    private String cargo_num;
    private String goods_unit;
    private String cargo_package;
    private String transport_way;
    private String current_price;
    private String cargo_huoqi;
    private String payment_way;
    private String application_scheme;
    private String product_picture;
    private String production_state;


    public AddGoodsBaseInfo(String cargo_name, String cargo_specification, String one_type, String two_type, String cino, String cas, String molecular_weight, String pas, String precise_quality, String cargo_purity, String cargo_num, String goods_unit, String cargo_package, String transport_way, String current_price, String cargo_huoqi, String payment_way, String application_scheme, String product_picture, String production_state) {
        this.cargo_name = cargo_name;
        this.cargo_specification = cargo_specification;
        this.one_type = one_type;
        this.two_type = two_type;
        this.cino = cino;
        this.cas = cas;
        this.molecular_weight = molecular_weight;
        this.pas = pas;
        this.precise_quality = precise_quality;
        this.cargo_purity = cargo_purity;
        this.cargo_num = cargo_num;
        this.goods_unit = goods_unit;
        this.cargo_package = cargo_package;
        this.transport_way = transport_way;
        this.current_price = current_price;
        this.cargo_huoqi = cargo_huoqi;
        this.payment_way = payment_way;
        this.application_scheme = application_scheme;
        this.product_picture = product_picture;
        this.production_state = production_state;
    }

    public String getCargo_name() {
        return cargo_name;
    }

    public void setCargo_name(String cargo_name) {
        this.cargo_name = cargo_name;
    }

    public String getCargo_specification() {
        return cargo_specification;
    }

    public void setCargo_specification(String cargo_specification) {
        this.cargo_specification = cargo_specification;
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

    public String getCargo_purity() {
        return cargo_purity;
    }

    public void setCargo_purity(String cargo_purity) {
        this.cargo_purity = cargo_purity;
    }

    public String getCargo_num() {
        return cargo_num;
    }

    public void setCargo_num(String cargo_num) {
        this.cargo_num = cargo_num;
    }

    public String getGoods_unit() {
        return goods_unit;
    }

    public void setGoods_unit(String goods_unit) {
        this.goods_unit = goods_unit;
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

    public String getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(String current_price) {
        this.current_price = current_price;
    }

    public String getCargo_huoqi() {
        return cargo_huoqi;
    }

    public void setCargo_huoqi(String cargo_huoqi) {
        this.cargo_huoqi = cargo_huoqi;
    }

    public String getPayment_way() {
        return payment_way;
    }

    public void setPayment_way(String payment_way) {
        this.payment_way = payment_way;
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
}
