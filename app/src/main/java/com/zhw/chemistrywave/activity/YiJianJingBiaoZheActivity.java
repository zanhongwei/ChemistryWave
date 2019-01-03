package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.YiJianJingJiaZhelvjAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.NewBaoJia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YiJianJingBiaoZheActivity extends BaseActivity {

    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rl_titlebar_search)
    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.lv_yijianjingbiaozhe)
    ListView lvYijianjingbiaozhe;
    private List<NewBaoJia> mList;
    private YiJianJingJiaZhelvjAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi_jian_jing_biao_zhe);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("参与一键竞标者");
        mList = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            NewBaoJia newBaoJia = new NewBaoJia("用户名");
//            mList.add(newBaoJia);
//        }
        mAdapter = new YiJianJingJiaZhelvjAdapter(YiJianJingBiaoZheActivity.this, mList);
        lvYijianjingbiaozhe.setAdapter(mAdapter);
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
