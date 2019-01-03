package com.zhw.chemistrywave.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public class HomeTabAdapter extends FragmentPagerAdapter {
    private List<Fragment> listFragment;                         //fragment列表
                               //tab名的列表

    public HomeTabAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;

    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        if (listFragment.size()>0) {
            return listFragment.size();
        }
        return 0;
    }



}
