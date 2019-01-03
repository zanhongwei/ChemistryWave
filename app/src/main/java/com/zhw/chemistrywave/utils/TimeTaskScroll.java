package com.zhw.chemistrywave.utils;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.zhw.chemistrywave.adapters.PriceExpressAdapter;
import com.zhw.chemistrywave.bean.PriceExpressBean;

import java.util.List;
import java.util.TimerTask;

public class TimeTaskScroll extends TimerTask {

    private ListView listView;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            listView.smoothScrollBy(2, 0);
        }
    };

    public TimeTaskScroll(Context context, ListView listView, List<PriceExpressBean.DataBean.ListBean> list) {
        this.listView = listView;
        listView.setAdapter(new PriceExpressAdapter(list, context));
    }

    @Override
    public void run() {
        Message msg = handler.obtainMessage();
        handler.sendMessage(msg);
    }

}
