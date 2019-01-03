package com.zhw.chemistrywave.bean;

/**
 * Created by Administrator on 2017/11/27.
 */

public class LoveGoods {

    public boolean isCheck;
    private int logo;
    private String name;
    private String url;
    private String size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LoveGoods() {
    }

    public LoveGoods(int logo, String name, String ul, String size) {
        this.logo = logo;
        this.name = name;
        this.url=ul;
        this.size=size;
    }

    public LoveGoods(boolean isCheck, int logo, String name) {
        this.isCheck = isCheck;
        this.logo = logo;
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
