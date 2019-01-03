package com.zhw.chemistrywave.bean;

import java.util.List;

/**
 * Created by axehome on 2018/2/1.
 */

public class Shop {
    private String shopName;
    private List<ShopCar.DataBean.ListBean.CartBeansBean> list;

    public Shop(String shopName, List<ShopCar.DataBean.ListBean.CartBeansBean> list) {
        this.shopName = shopName;
        this.list = list;
    }

    public Shop() {
    }

    public List<ShopCar.DataBean.ListBean.CartBeansBean> getList() {
        return list;
    }

    public void setList(List<ShopCar.DataBean.ListBean.CartBeansBean> list) {
        this.list = list;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
