package com.zhw.chemistrywave;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.activity.LoginActivity;
import com.zhw.chemistrywave.activity.MianFeiZhaoHuoActivity;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.fragment.ClassifyFragment;
import com.zhw.chemistrywave.fragment.HomePageFragment;
import com.zhw.chemistrywave.fragment.MyCenterFragment;
import com.zhw.chemistrywave.fragment.OrderFragment;
import com.zhw.chemistrywave.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main_container)
    FrameLayout flMainContainer;
    @BindView(R.id.ib_homepage)
    ImageView ibHomepage;
    @BindView(R.id.tv_homepage)
    TextView tvHomepage;
    @BindView(R.id.ib_classiffy)
    ImageView ibClassiffy;
    @BindView(R.id.tv_classiffy)
    TextView tvClassiffy;
    @BindView(R.id.ib_order)
    ImageView ibOrder;
    @BindView(R.id.tv_order)
    TextView tvOrder;
    @BindView(R.id.ib_mine)
    ImageView ibMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;

    private HomePageFragment mHomePageFragment;
    private ClassifyFragment mClassifyFragment;
    private OrderFragment mOrderFragment;
    private MyCenterFragment mMyCenterFragment;
    private Fragment[] mFragments;
    private int mIndex = 0;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        int currentIndex = getIntent().getIntExtra("index", 0);
        Log.e("aaa",
                "(MainActivity.java:56)<--mIndex-->" + mIndex);

        initData(currentIndex);
    }

    /**
     * 初始化数据源
     */
    private void initData(int currentIndex) {
        initFragment(currentIndex);
    }

    private void initFragment(int currentIndex) {

        mHomePageFragment = new HomePageFragment();
        mClassifyFragment = new ClassifyFragment();
        mOrderFragment = new OrderFragment();
        mMyCenterFragment = new MyCenterFragment();

        //添加到fragment数组
        mFragments = new Fragment[]{mHomePageFragment, mClassifyFragment, mOrderFragment, mMyCenterFragment};
        //开启事务
        FragmentTransaction ft;
        ft = getSupportFragmentManager().beginTransaction();
        //添加首页
        ft.add(R.id.fl_main_container, mHomePageFragment).commit();
        //设置默认为第0个
        setIndexSelected(currentIndex);
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
            ft.add(R.id.fl_main_container, mFragments[index]).show(mFragments[index]);
        } else {
            ft.show(mFragments[index]);
        }
        ft.commit();
        //再次赋值
        mIndex = index;
    }

//    @Override
//    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//        switch (checkedId) {
//            case R.id.rb_main_homepage:
//                rbMainHomepage.setTextColor(getResources().getColor(R.color.a86de));
//                rbMainFenlei.setTextColor(getResources().getColor(R.color.a232326));
//                rbMainOrder.setTextColor(getResources().getColor(R.color.a232326));
//                rbMainMycenter.setTextColor(getResources().getColor(R.color.a232326));
//                setIndexSelected(0);
//                break;
//            case R.id.rb_main_fenlei:
//                rbMainHomepage.setTextColor(getResources().getColor(R.color.a232326));
//                rbMainFenlei.setTextColor(getResources().getColor(R.color.a86de));
//                rbMainOrder.setTextColor(getResources().getColor(R.color.a232326));
//                rbMainMycenter.setTextColor(getResources().getColor(R.color.a232326));
//                setIndexSelected(1);
//                break;
//            case R.id.rb_main_mianfeizhaohuo:
//                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                } else {
//                    rbMainHomepage.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainFenlei.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainOrder.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainMycenter.setTextColor(getResources().getColor(R.color.a232326));
//                    startActivity(new Intent(MainActivity.this, MianFeiZhaoHuoActivity.class));
//                }
//                break;
//            case R.id.rb_main_order:
//                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                } else {
//                    rbMainHomepage.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainFenlei.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainOrder.setTextColor(getResources().getColor(R.color.a86de));
//                    rbMainMycenter.setTextColor(getResources().getColor(R.color.a232326));
//                    setIndexSelected(2);
//                }
//                break;
//            case R.id.rb_main_mycenter:
//                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
//                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                } else {
//                    rbMainHomepage.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainFenlei.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainOrder.setTextColor(getResources().getColor(R.color.a232326));
//                    rbMainMycenter.setTextColor(getResources().getColor(R.color.a86de));
//                    setIndexSelected(3);
//                }
//                break;
//        }
//
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                //SPUtils.clear(this);
                MyApplication.getInstance().exit();
//                finish();
//                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.ll_homepage, R.id.ll_classiffy, R.id.ll_inquiry, R.id.ll_order, R.id.ll_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_homepage:
                setTextColor(R.id.ll_homepage);
                setImageResourse(R.id.ll_homepage);
                setIndexSelected(0);
                break;
            case R.id.ll_classiffy:
                setTextColor(R.id.ll_classiffy);
                setImageResourse(R.id.ll_classiffy);
                setIndexSelected(1);
                break;
            case R.id.ll_inquiry:
                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    setTextColor(R.id.ll_inquiry);
                    setImageResourse(R.id.ll_inquiry);
                    startActivity(new Intent(MainActivity.this, MianFeiZhaoHuoActivity.class));
                }
                break;
            case R.id.ll_order:
                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    setTextColor(R.id.ll_order);
                    setImageResourse(R.id.ll_order);
                    setIndexSelected(2);
                }

                break;
            case R.id.ll_mine:
                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    setTextColor(R.id.ll_mine);
                    setImageResourse(R.id.ll_mine);
                    setIndexSelected(3);
                }
                break;
        }
    }

    private void setTextColor(@IdRes int viewId) {
        tvHomepage.setTextColor(getResources().getColor(R.color.a232326));
        tvClassiffy.setTextColor(getResources().getColor(R.color.a232326));
        tvOrder.setTextColor(getResources().getColor(R.color.a232326));
        tvMine.setTextColor(getResources().getColor(R.color.a232326));
        switch (viewId) {
            case R.id.ll_homepage:
                tvHomepage.setTextColor(getResources().getColor(R.color.a86de));
                break;
            case R.id.ll_classiffy:
                tvClassiffy.setTextColor(getResources().getColor(R.color.a86de));
                break;
            case R.id.ll_order:
                tvOrder.setTextColor(getResources().getColor(R.color.a86de));
                break;
            case R.id.ll_mine:
                tvMine.setTextColor(getResources().getColor(R.color.a86de));
                break;
        }

    }

    private void setImageResourse(@IdRes int viewId) {
        ibHomepage.setImageResource(R.drawable.home_icon_unselected);
        ibClassiffy.setImageResource(R.drawable.fenlei_unselected);
        ibOrder.setImageResource(R.drawable.order_unselected);
        ibMine.setImageResource(R.drawable.wode_unselected);

        switch (viewId) {
            case R.id.ll_homepage:
                ibHomepage.setImageResource(R.drawable.home_icon_selected);
                break;
            case R.id.ll_classiffy:
                ibClassiffy.setImageResource(R.drawable.fenlei_selected);
                break;
            case R.id.ll_order:
                ibOrder.setImageResource(R.drawable.order_selected);
                break;
            case R.id.ll_mine:
                ibMine.setImageResource(R.drawable.wode_selected);
                break;
        }
    }
}
