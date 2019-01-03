package com.zhw.chemistrywave.bean;

import java.io.Serializable;

/**
 * Created by zhw on 2018/7/28.
 */

public class QualityDetail implements Serializable{

//    bundle.putString("intensity", qingdu);//强度
//        bundle.putString("coloured_light", seguan);//色光
//        bundle.putString("appearance", waiguan);//外观
//        bundle.putString("moisture", shuifen);//水分
//        bundle.putString("insoluble_substance", burongwu);//不溶物
//        bundle.putString("solubility", rongjiedu);//溶解度
//        bundle.putString("chloride_content", lvlizihanliang);//氯离子含量
//        bundle.putString("solid_content", guhanliang);//固含量
//        bundle.putString("salinity", yanfen);//盐分
//        bundle.putString("conductivity", diandaolv);//电导率
//        bundle.putString("michler_ketone", mishitong);//米氏酮

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

    public QualityDetail(String intensity, String coloured_light, String appearance, String moisture, String insoluble_substance, String solubility, String chloride_content, String solid_content, String salinity, String conductivity, String michler_ketone) {
        this.intensity = intensity;
        this.coloured_light = coloured_light;
        this.appearance = appearance;
        this.moisture = moisture;
        this.insoluble_substance = insoluble_substance;
        this.solubility = solubility;
        this.chloride_content = chloride_content;
        this.solid_content = solid_content;
        this.salinity = salinity;
        this.conductivity = conductivity;
        this.michler_ketone = michler_ketone;
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
}
