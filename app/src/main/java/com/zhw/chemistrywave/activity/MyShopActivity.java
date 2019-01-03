package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.ShopInformation;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MyShopActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_id_number)
    TextView tvIdNumber;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    private void initData() {

            //获取店铺信息
            OkHttpUtils
                    .post()
                    .url(NetConfig.SHOP_DETAIL)
                    .addParams("user_id", MyUtils.getUser().getUser_id())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("aaa",
                                    "(MyShopActivity.java:59)<--获取店铺信息失败返回-->"+e.getMessage());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("aaa",
                                    "(MyShopActivity.java:67)<--获取店铺信息成功返回-->"+response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    ShopInformation shopDetail = new Gson().fromJson(response, ShopInformation.class);
                                    String shop_name = shopDetail.getData().getShop_name();//店铺名称
                                    String contact_name = shopDetail.getData().getContact_name();//联系人
                                    String contact_mobile = shopDetail.getData().getContact_mobile();//联系电话
                                    String per_card = shopDetail.getData().getPer_card();//身份证号
                                    String per_addr = shopDetail.getData().getPer_addr();//地址
                                    String com_logo = shopDetail.getData().getCom_logo();//logo

                                    tvNickname.setText(contact_name);
                                    tvPhone.setText(contact_mobile);
                                    tvIdNumber.setText(per_card);
                                    tvAddress.setText(per_addr);
                                    tvShopName.setText(shop_name);
                                    Glide.with(MyShopActivity.this).load(NetConfig.baseurl+com_logo).apply(MyApplication.options).into(ivShopLogo);

                                }else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
    }

    private void initView() {

        tvTitlebarCenter.setText("Shop Information");
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }
}
