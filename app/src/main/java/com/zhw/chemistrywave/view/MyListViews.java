package com.zhw.chemistrywave.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/7/25.
        */

public class MyListViews extends ListView {
    public MyListViews(Context context) {
        super(context);
    }

    public MyListViews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
