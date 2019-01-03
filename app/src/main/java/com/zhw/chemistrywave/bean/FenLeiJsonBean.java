package com.zhw.chemistrywave.bean;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by axehome on 2018/1/19.
 */

public class FenLeiJsonBean implements IPickerViewData{

    /**
     * name : 农用化学品
     * erji : ["农药中间体","化肥","复合肥","中农药助剂"]
     */

    private String name;
    private List<String> erji;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getErji() {
        return erji;
    }

    public void setErji(List<String> erji) {
        this.erji = erji;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }
}
