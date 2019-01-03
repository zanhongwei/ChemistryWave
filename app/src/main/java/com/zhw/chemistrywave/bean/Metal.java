package com.zhw.chemistrywave.bean;

import java.io.Serializable;

/**
 * Created by zhw on 2018/7/28.
 */

public class Metal implements Serializable{

//    metal_cu = bundle.getString("Cu");
//    //锌离子
//    metal_zn = bundle.getString("Zn");
//    //镍离子
//    metal_ni = bundle.getString("Ni");
//    //锑离子
//    metal_sb = bundle.getString("Sb");
//    //镉离子
//    metal_cd = bundle.getString("Cd");
//    //铅离子
//    metal_pb = bundle.getString("Pb");
//    //锡离子
//    metal_sn = bundle.getString("Sn");
//    //钴离子
//    metal_co = bundle.getString("Co");
//    //汞离子
//    metal_hg = bundle.getString("Hg");
//    //铋离子
//    metal_bi = bundle.getString("Bi");

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

    public Metal(String metal_cu, String metal_zn, String metal_ni, String metal_sb, String metal_cd, String metal_pb, String metal_sn, String metal_co, String metal_hg, String metal_bi) {
        this.metal_cu = metal_cu;
        this.metal_zn = metal_zn;
        this.metal_ni = metal_ni;
        this.metal_sb = metal_sb;
        this.metal_cd = metal_cd;
        this.metal_pb = metal_pb;
        this.metal_sn = metal_sn;
        this.metal_co = metal_co;
        this.metal_hg = metal_hg;
        this.metal_bi = metal_bi;
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
}
