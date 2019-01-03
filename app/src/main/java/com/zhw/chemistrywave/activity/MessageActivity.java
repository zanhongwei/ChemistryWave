package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.fragment.SysMsgFragment;
import com.zhw.chemistrywave.fragment.UserMsgFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {


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
    @BindView(R.id.tv_message_sys)
    TextView tvMessageSys;
    @BindView(R.id.tv_message_sysline)
    TextView tvMessageSysline;
    @BindView(R.id.rl_message_sys)
    RelativeLayout rlMessageSys;
    @BindView(R.id.tv_message_user)
    TextView tvMessageUser;
    @BindView(R.id.tv_message_userline)
    TextView tvMessageUserline;
    @BindView(R.id.rl_message_user)
    RelativeLayout rlMessageUser;
    @BindView(R.id.tv_message_notification)
    TextView tvMessageNotification;
    @BindView(R.id.tv_message_notificationline)
    TextView tvMessageNotificationline;
    @BindView(R.id.rl_message_notification)
    RelativeLayout rlMessageNotification;
    @BindView(R.id.fl_message_container)
    FrameLayout flMessageContainer;
    private SysMsgFragment mSysMsgFragment;
    private UserMsgFragment mUserMsgFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        initData();

    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("Message");
        initFragment();
    }

    @OnClick({R.id.rl_titlebar_back, R.id.rl_message_sys, R.id.rl_message_user, R.id.rl_message_notification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //系统消息
            case R.id.rl_message_sys:
                tvMessageSys.setTextColor(getResources().getColor(R.color.a0987de));
                tvMessageUser.setTextColor(getResources().getColor(R.color.a67));
                tvMessageNotification.setTextColor(getResources().getColor(R.color.a67));
                tvMessageSysline.setVisibility(View.VISIBLE);
                tvMessageUserline.setVisibility(View.GONE);
                tvMessageNotificationline.setVisibility(View.GONE);
                setIndexSelected(0);
                break;
            //用户消息
            case R.id.rl_message_user:
                tvMessageSys.setTextColor(getResources().getColor(R.color.a67));
                tvMessageUser.setTextColor(getResources().getColor(R.color.a0987de));
                tvMessageNotification.setTextColor(getResources().getColor(R.color.a67));
                tvMessageSysline.setVisibility(View.GONE);
                tvMessageUserline.setVisibility(View.VISIBLE);
                tvMessageNotificationline.setVisibility(View.GONE);
                setIndexSelected(1);
                break;
            //通知消息
            case R.id.rl_message_notification:
                tvMessageSys.setTextColor(getResources().getColor(R.color.a67));
                tvMessageUser.setTextColor(getResources().getColor(R.color.a67));
                tvMessageNotification.setTextColor(getResources().getColor(R.color.a0987de));
                tvMessageSysline.setVisibility(View.GONE);
                tvMessageUserline.setVisibility(View.GONE);
                tvMessageNotificationline.setVisibility(View.VISIBLE);
                setIndexSelected(1);
                break;
        }
    }

    private void initFragment() {
        mSysMsgFragment = new SysMsgFragment();
        mUserMsgFragment = new UserMsgFragment();

        //添加到fragment数组
        mFragments = new Fragment[]{mSysMsgFragment, mUserMsgFragment};
        //开启事务
        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.fl_message_container, mSysMsgFragment).commit();
        //设置默认为第0个
        setIndexSelected(0);
    }


    public void setIndexSelected(int index) {

        if (mIndex == index) {
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        //隐藏当前fragment
        ft.hide(mFragments[mIndex]);
        if (!mFragments[index].isAdded()) {
            ft.add(R.id.fl_message_container, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }


}
