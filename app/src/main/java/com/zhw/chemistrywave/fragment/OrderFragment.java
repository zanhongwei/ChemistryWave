package com.zhw.chemistrywave.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {


    @BindView(R.id.rl_titlebarmyorder_back)
    RelativeLayout rlTitlebarmyorderBack;
    @BindView(R.id.iv_titlebarmyorder_line)
    ImageView ivTitlebarmyorderLine;
    @BindView(R.id.tv_titlebarmyorder_center)
    TextView tvTitlebarmyorderCenter;
    @BindView(R.id.tv_titlebarmyorder_right)
    TextView tvTitlebarmyorderRight;
    @BindView(R.id.tv_orderf_caigoudingdan)
    TextView tvOrderfCaigoudingdan;
    @BindView(R.id.tv_orderf_gonghuodingdan)
    TextView tvOrderfGonghuodingdan;
    @BindView(R.id.fl_orderf_container)
    FrameLayout flOrderfContainer;
    Unbinder unbinder;
    private String type = "0";
    private CaiGouOrderFragment mCaiGouOrderFragment;
    private GonghuoOrderFragment mGonghuoOrderFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private String user_state;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        user_state = (String) SPUtils.get(getActivity(), "user_state", "0");
        initView();
        return view;
    }


    /**
     * 初始化数据源
     */
    private void initView() {
        rlTitlebarmyorderBack.setVisibility(View.GONE);
        ivTitlebarmyorderLine.setVisibility(View.GONE);
        initFragment();
    }

    private void initFragment() {
        mCaiGouOrderFragment = new CaiGouOrderFragment();
        mGonghuoOrderFragment = new GonghuoOrderFragment();
        //添加到fragment数组
        mFragments = new Fragment[]{mCaiGouOrderFragment, mGonghuoOrderFragment};
        //开启事务
        FragmentTransaction ft;

        ft = this.getChildFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.fl_orderf_container, mCaiGouOrderFragment).commit();
        //设置默认为第0个
        if (user_state.equals("0")) {//采购商身份
            setIndexSelected(0);
        } else {//供货商身份
            setIndexSelected(1);
        }

    }

    public void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏当前fragment
        ft.hide(mFragments[mIndex]);
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_orderf_container, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.tv_orderf_caigoudingdan, R.id.tv_orderf_gonghuodingdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_orderf_caigoudingdan:
                tvOrderfCaigoudingdan.setTextColor(getResources().getColor(R.color.white));
                tvOrderfCaigoudingdan.setBackground(getResources().getDrawable(R.drawable.bj_caigoudingdan));
                tvOrderfGonghuodingdan.setTextColor(getResources().getColor(R.color.a58));
                tvOrderfGonghuodingdan.setBackground(getResources().getDrawable(R.drawable.bj_white));
                setIndexSelected(0);
                break;
            case R.id.tv_orderf_gonghuodingdan:
                tvOrderfGonghuodingdan.setTextColor(getResources().getColor(R.color.white));
                tvOrderfGonghuodingdan.setBackground(getResources().getDrawable(R.drawable.bj_caigoudingdan));
                tvOrderfCaigoudingdan.setTextColor(getResources().getColor(R.color.a58));
                tvOrderfCaigoudingdan.setBackground(getResources().getDrawable(R.drawable.bj_white));
                setIndexSelected(1);
                break;
        }
    }
}
