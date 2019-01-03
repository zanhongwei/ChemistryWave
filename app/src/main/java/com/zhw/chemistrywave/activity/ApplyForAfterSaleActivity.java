package com.zhw.chemistrywave.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyForAfterSaleActivity extends BaseActivity {

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
    @BindView(R.id.iv_applyforaftersale_goods)
    ImageView ivApplyforaftersaleGoods;
    @BindView(R.id.tv_applyforaftersale_goodsname)
    TextView tvApplyforaftersaleGoodsname;
    @BindView(R.id.tv_applyforaftersale_goodscontent)
    TextView tvApplyforaftersaleGoodscontent;
    @BindView(R.id.tv_applyforaftersale_tuikuangjine)
    TextView tvApplyforaftersaleTuikuangjine;
    @BindView(R.id.ll_applyforaftersale_tuikuangyuanyin)
    LinearLayout llApplyforaftersaleTuikuangyuanyin;
    @BindView(R.id.et_applyforaftersale_wentimiaoshu)
    EditText etApplyforaftersaleWentimiaoshu;
    @BindView(R.id.iv_applyforaftersale_addimage)
    ImageView ivApplyforaftersaleAddimage;
    @BindView(R.id.tv_applyforaftersale_commit)
    TextView tvApplyforaftersaleCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_after_sale);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("申请售后");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_applyforaftersale_tuikuangyuanyin, R.id.iv_applyforaftersale_addimage, R.id.tv_applyforaftersale_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //退款原因
            case R.id.ll_applyforaftersale_tuikuangyuanyin:
                showPopUpWindow();
                break;
            case R.id.iv_applyforaftersale_addimage:
                break;
            case R.id.tv_applyforaftersale_commit:
                break;
        }
    }

    private void showPopUpWindow() {
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_qvxiaodingdan, null);
        final PopupWindow popWnd = new PopupWindow(this);
        popWnd.setContentView(contentView);
//    popWnd.setWidth(263);
//    popWnd.setHeight(320);
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View contentView1 = popWnd.getContentView();


        popWnd.setTouchable(true);
        popWnd.setFocusable(true);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        backgroundAlpha(0.6f);

        //添加pop窗口关闭事件
        popWnd.setOnDismissListener(new poponDismissListener());

        popWnd.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popWnd.dismiss();
                    return true;
                }
                return false;
            }
        });

        //popWnd.showAsDropDown(mTvLine, 200, 0);
        popWnd.showAtLocation(llApplyforaftersaleTuikuangyuanyin, Gravity.CENTER, 0, 0);

    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

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
