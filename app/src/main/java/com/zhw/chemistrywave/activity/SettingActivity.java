package com.zhw.chemistrywave.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.CleanMessageUtil;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.view.EaseSwitchButton;
import com.zhw.chemistrywave.view.SelfDialog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends BaseActivity {

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
    @BindView(R.id.civ_setting_headimage)
    CircleImageView civSettingHeadimage;
    @BindView(R.id.tv_setting_nickname)
    TextView tvSettingNickname;
    @BindView(R.id.tv_setting_username)
    TextView tvSettingUsername;
    @BindView(R.id.tv_setting_cache)
    TextView tvSettingCache;
    @BindView(R.id.ll_setting_clearcache)
    LinearLayout llSettingClearcache;
    @BindView(R.id.tv_setting_banbenshengji)
    TextView tvSettingBanbenshengji;
    @BindView(R.id.ll_setting_banbenshengji)
    LinearLayout llSettingBanbenshengji;
    @BindView(R.id.esb_setting_usermsg)
    EaseSwitchButton esbSettingUsermsg;
    @BindView(R.id.ll_setting_newmessage)
    LinearLayout llSettingNewmessage;
    @BindView(R.id.esb_setting_msgdetail)
    EaseSwitchButton esbSettingMsgdetail;
    @BindView(R.id.ll_setting_diqvshezhi)
    LinearLayout llSettingDiqvshezhi;
    @BindView(R.id.esb_setting_voice)
    EaseSwitchButton esbSettingVoice;
    @BindView(R.id.ll_setting_privacy)
    LinearLayout llSettingPrivacy;
    @BindView(R.id.esb_setting_shake)
    EaseSwitchButton esbSettingShake;
    @BindView(R.id.ll_setting_tongyong)
    LinearLayout llSettingTongyong;
    @BindView(R.id.ll_setting_aboutus)
    LinearLayout llSettingAboutus;
    @BindView(R.id.ll_setting_exit)
    LinearLayout llSettingExit;
    private boolean flag = false;
    private boolean flag1 = false;
    private boolean flag2 = false;
    private boolean flag3 = false;
    private boolean flag4 = false;
    private boolean flag5 = false;
    private SelfDialog selfDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //实现状态栏图标和文字颜色为暗色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        if (null == MyUtils.getUser().getUser_photo() || MyUtils.getUser().getUser_photo().equals("null")) {
            Glide.with(this).load("http://www.harlanchina.com/photo.png").apply(MyApplication.options).into(civSettingHeadimage);
        } else {
            Glide.with(this).load(NetConfig.baseurl + MyUtils.getUser().getUser_photo()).apply(MyApplication.options).into(civSettingHeadimage);
        }
        tvSettingNickname.setText(MyUtils.getUser().getUser_name());
        tvSettingUsername.setText(MyUtils.getUser().getUser_id());
    }

    @OnClick({R.id.rl_titlebar_back, R.id.civ_setting_headimage, R.id.esb_setting_usermsg, R.id.esb_setting_msgdetail, R.id.esb_setting_voice, R.id.esb_setting_shake, R.id.ll_setting_aboutus, R.id.ll_setting_exit, R.id.ll_setting_clearcache, R.id.ll_setting_banbenshengji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;

            //头像
            case R.id.civ_setting_headimage:
                break;
            //清除缓存
            case R.id.ll_setting_clearcache:
                selfDialog = new SelfDialog(SettingActivity.this);
                selfDialog.setTitle("Do you clear the cache");
                selfDialog.setYesOnclickListener("Confirm", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        selfDialog.dismiss();
                        CleanMessageUtil.clearAllCache(SettingActivity.this);
                        try {
                            MyUtils.cleanVideoCacheDir(SettingActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tvSettingCache.setText("0.0KB");
                    }
                });
                selfDialog.setNoOnclickListener("Cancel", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {

                        selfDialog.dismiss();
                    }
                });
                selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        backgroundAlpha(1f);
                    }
                });
                backgroundAlpha(0.6f);
                selfDialog.show();
                break;
            //版本升级
            case R.id.ll_setting_banbenshengji:

                break;
            //接受用户消息
            case R.id.esb_setting_usermsg:
                if (flag2) {
                    esbSettingUsermsg.openSwitch();
                    flag2 = !flag2;
                } else {
                    esbSettingUsermsg.closeSwitch();
                    flag2 = !flag2;
                }
                break;
            //通知显示消息详情
            case R.id.esb_setting_msgdetail:
                if (flag3) {
                    esbSettingMsgdetail.openSwitch();
                    flag3 = !flag3;
                } else {
                    esbSettingMsgdetail.closeSwitch();
                    flag3 = !flag3;
                }
                break;
            //声音
            case R.id.esb_setting_voice:
                if (flag4) {
                    esbSettingVoice.openSwitch();
                    flag4 = !flag4;
                } else {
                    esbSettingVoice.closeSwitch();
                    flag4 = !flag4;
                }
                break;
            //震动
            case R.id.esb_setting_shake:
                if (flag5) {
                    esbSettingShake.openSwitch();
                    flag5 = !flag5;
                } else {
                    esbSettingShake.closeSwitch();
                    flag5 = !flag5;
                }
                break;
            //关于我们
            case R.id.ll_setting_aboutus:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            //退出登录
            case R.id.ll_setting_exit:
                loginOut();
                break;
        }
    }

    private void loginOut() {
        selfDialog = new SelfDialog(SettingActivity.this);
        selfDialog.setTitle("Do you Log out");
        selfDialog.setYesOnclickListener("Confirm", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                selfDialog.dismiss();
                SPUtils.clear(SettingActivity.this);
                getSharedPreferences("Log", MODE_PRIVATE).edit().clear().commit();
                startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                finish();
            }
        });
        selfDialog.setNoOnclickListener("Cancel", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {

                selfDialog.dismiss();
            }
        });
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                backgroundAlpha(1f);
            }
        });
        backgroundAlpha(0.6f);
        selfDialog.show();
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }


}
