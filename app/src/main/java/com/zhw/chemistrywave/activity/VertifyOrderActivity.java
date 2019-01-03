package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhw.chemistrywave.MainActivity;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.VertifyOrdermLvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.MyAddress;
import com.zhw.chemistrywave.bean.Shop;
import com.zhw.chemistrywave.bean.ShopDetail;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhw.chemistrywave.view.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zhw.chemistrywave.R.id.rl_titlebar_back;

public class VertifyOrderActivity extends BaseActivity {

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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_tel_phone)
    TextView tvTelPhone;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.rl_person)
    RelativeLayout rlPerson;
    @BindView(R.id.mlv_vertufyorder)
    MyListView mlvVertufyorder;
    @BindView(R.id.iv_store_img)
    ImageView ivStoreImg;
    @BindView(R.id.tv_store_name)
    TextView tvStoreName;
    @BindView(R.id.ll_shopname)
    LinearLayout llShopname;
    @BindView(R.id.iv_product_img)
    ImageView ivProductImg;
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    @BindView(R.id.tv_product_params)
    TextView tvProductParams;
    @BindView(R.id.tv_product_unit_price)
    TextView tvProductUnitPrice;
    @BindView(R.id.tv_product_unit_num)
    TextView tvProductUnitNum;
    @BindView(R.id.ll_shopcontent)
    LinearLayout llShopcontent;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    @BindView(R.id.ll_agreement)
    LinearLayout llAgreement;
    //    @BindView(R.id.tv_e_bank)
//    RadioButton tvEBank;
    @BindView(R.id.tv_three_way)
    RadioButton tvThreeWay;
    @BindView(R.id.tv_cash)
    RadioButton tvCash;
    //    @BindView(R.id.tv_financial)
//    RadioButton tvFinancial;
    @BindView(R.id.ll_pay_way)
    LinearLayout llPayWay;
    @BindView(R.id.tv_platform_detection)
    CheckBox tvPlatformDetection;
    @BindView(R.id.tv_free_detection)
    CheckBox tvFreeDetection;
    @BindView(R.id.ll_platform_service)
    LinearLayout llPlatformService;
    @BindView(R.id.tv_product_num)
    TextView tvProductNum;
    @BindView(R.id.tv_product_price)
    TextView tvProductPrice;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.rg_payment)
    RadioGroup rgPyment;
    private int report_id;
    private int super_id;
    private String cargo_id;
    private int addr_id;
    private String ifMianFeijianLi;
    private String ifSanfangjiance;
    private int prtp_id;
    private int sellerId;
    private String cargo_name;
    private String order;
    private VertifyOrdermLvAdapter mAdapter;
    private List<Shop> mList;

    private String color_power;
    private String color_light;
    private String package_opt;
    private double goods_price;
    private int goods_num;
    private String shopId;
    private String goods_photo;
    private String type;//来源Activity类别

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertify_order);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Confirmation of order");
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "ShopCarActivity":
                llShopcontent.setVisibility(View.GONE);
                llShopname.setVisibility(View.GONE);
                mList = new ArrayList<>();
                order = getIntent().getStringExtra("order");
                try {
                    JSONArray jsonArray = new JSONArray(order);
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Shop shop = gson.fromJson(jsonArray.optJSONObject(i).toString(), Shop.class);
                        mList.add(shop);
                        Log.e("aaa",
                                "(VertifyOrderActivity.java:174)<--传过来的商品信息集合大下-->" + mList.size());
                        Log.e("aaa", "(VertifyOrderActivity.java:175)<---->" + order);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mAdapter = new VertifyOrdermLvAdapter(VertifyOrderActivity.this, mList);
                mlvVertufyorder.setAdapter(mAdapter);
                Log.e("aaa", "---order.toString()-->" + order.toString());
                break;
            case "CaiGouZheActivity":
                llShopcontent.setVisibility(View.VISIBLE);
                llShopname.setVisibility(View.VISIBLE);
                cargo_id = getIntent().getStringExtra("cargo_id");
                cargo_name = getIntent().getStringExtra("cargo_name");
                prtp_id = getIntent().getIntExtra("prtp_id", 0);
                sellerId = getIntent().getIntExtra("sellerId", 0);
                break;
            case "GooodsDetailActivity":
                Bundle bundle = getIntent().getExtras();
                //店铺id
                shopId = bundle.getString("shopId");
                cargo_name = bundle.getString("goods_name");//货物名称
                cargo_id = bundle.getString("goods_id");//货物ID
                color_power = bundle.getString("color_power");//货物所选参数
                color_light = bundle.getString("color_light");//货物所选参数
                package_opt = bundle.getString("package_opt");//货物所选参数
                goods_price = bundle.getDouble("goods_price");//货物价格
                goods_num = bundle.getInt("goods_num");//货物数量
                goods_photo = bundle.getString("goods_photo");//货物数量
                sellerId = Integer.parseInt(shopId);
                initView();
                break;

        }

        //获取店铺信息接口
//        getShopInfo();
        getDefaultAddress();

        initListener();
    }

    private void initView() {
        tvProductName.setText(cargo_name);
        Glide.with(this).load(NetConfig.baseurl + goods_photo).apply(MyApplication.options).into(ivProductImg);
        tvProductParams.setText("selected: “" + color_power + "”" + "“" + color_light + "”" + "“" + package_opt + "”");
        tvProductUnitPrice.setText("$" + goods_price);
        tvProductUnitNum.setText(goods_num + "");
        tvCash.setChecked(true);

        tvProductNum.setText("x" + goods_num);
        tvProductPrice.setText("$" + goods_price);
        double total = goods_price * goods_num;
        tvTotalMoney.setText("$" + total);

    }

    private void getDefaultAddress() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(NetConfig.addresslist)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:79)<---->" + e.getMessage());
                        ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:86)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(VertifyOrderActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        } else {
                            MyAddress myAddress = new Gson().fromJson(response, MyAddress.class);
                            List<MyAddress.DataBean.ListBean> list = myAddress.getData().getList();
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).isIs_default()) {
                                    tvName.setText(list.get(i).getConsignee());
                                    tvPhone.setText(list.get(i).getMobile());
                                    tvAddress.setText(list.get(i).getDetail());
                                    tvTelPhone.setText("");
                                    addr_id = list.get(i).getAddr_id();
                                }
                            }
//                            mList.addAll(myAddress.getData().getList());
//                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initListener() {
        //免费监理
        tvFreeDetection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getMianfeijianli();
                }
            }
        });
        //三方检测
        tvPlatformDetection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sanfangjiance();
                }
            }
        });
    }

    /**
     * 免费监理
     */
    private void getMianfeijianli() {
        OkHttpUtils.post().url(NetConfig.mianfeijianli)
                .addParams("logistics_number", "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---免费监理返回---error--->" + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----免费监理返回----->" + response);
                        //{"msg":"success","code":0,"super_id":0}
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    super_id = jsonObject.getInt("super_id");
                                } else {
                                    super_id = 0;
                                    Toast.makeText(VertifyOrderActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 三方检测
     */
    private void sanfangjiance() {
        Log.e("aaa", "---三方检测参数---->" + cargo_id);
        OkHttpUtils.post().url(NetConfig.sanfangjiance)
                .addParams("cargo_id", cargo_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---三方检测返回---error--->" + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----三方检测返回----->" + response);
                        //{"code":0,"report_id":105}
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {

                                    report_id = jsonObject.getInt("report_id");
                                } else {
                                    report_id = 0;
                                    Toast.makeText(VertifyOrderActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void getShopInfo() {
        OkHttpUtils
                .post()
                .url(NetConfig.SHOP_DETAIL)
                .addParams("user_id", shopId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(VertifyOrderActivity.java:211)<---->" + e.getMessage());
                        ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(VertifyOrderActivity.java:217)<---->" + response);

                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    ShopDetail shopDetail = gson.fromJson(response, ShopDetail.class);
                                    String com_name = shopDetail.getData().getShop_name();
                                    String com_logo = shopDetail.getData().getCom_logo();
                                    Glide.with(VertifyOrderActivity.this).load(NetConfig.baseurl + com_logo).apply(MyApplication.options).into(ivStoreImg);
                                    tvStoreName.setText(com_name);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_agreement, R.id.tv_cash, R.id.tv_financial, R.id.ll_pay_way,
            R.id.tv_pay, R.id.rl_person})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case rl_titlebar_back:
                onBackPressed();
                break;
            //订单合同
            case R.id.ll_agreement:
                startActivity(new Intent(VertifyOrderActivity.this, OrderCompactActivity.class));
                break;
            case R.id.tv_pay:
                switch (type) {
                    case "ShopCarActivity":
                        makeOrderShopCar();
                        break;
                    case "GooodsDetailActivity":
                        makeOrder();
                        break;
                }

                break;
            case R.id.rl_person://联系人地址
                Intent intent = new Intent(VertifyOrderActivity.this, NewAddressManagerActivity.class);
                startActivityForResult(intent, 111);
                break;
        }
    }

    private void makeOrderShopCar() {
        if (addr_id == 0) {
            Toast.makeText(this, "Please select the delivery address", Toast.LENGTH_SHORT).show();
            return;
        }



    }

    private void makeOrder() {
        if (addr_id == 0) {
            Toast.makeText(this, "Please select the delivery address", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("goods_name", cargo_name);//订单名称（货物名称）
        map.put("addr_id", String.valueOf(addr_id));//收货地址信息id
        map.put("goods_num", String.valueOf(goods_num));//商品数量
        map.put("goods_price", String.valueOf(goods_price));//商品单价
        map.put("contract", NetConfig.baseurl + "upload/0/InternationalHarLan.doc");//合同
        map.put("buyer_id", MyUtils.getUser().getUser_id());//买家用户id
        map.put("seller_id", String.valueOf(sellerId));//卖家用户id
        map.put("payment_opt", String.valueOf("offline"));//付款方式
        map.put("package_opt", String.valueOf(""));//包装方式
        map.put("color_light", String.valueOf(""));//色光
        map.put("color_power", String.valueOf(""));//色力
        map.put("goods_id", String.valueOf(cargo_id));//产品ID
        map.put("check_state", "0");//平台监测状态
        map.put("super_state", "0");//平台监测状态

        Log.e("aaa", "--生成订单参数---->" + map.toString());
        OkHttpUtils.post().url(NetConfig.make_order)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--生成订单返回---->" + e.getMessage() + e.getCause());
                        ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--生成订单返回---->" + response);
                        //{"msg":"success","code":0,"order_id":"2018-01-1516:27:36"}
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    startActivity(new Intent(VertifyOrderActivity.this, MainActivity.class).putExtra("index", 2));
                                    finish();
                                } else {
                                    ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtil.showToastShort(VertifyOrderActivity.this, "Network error");
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 111 & resultCode == 222) {
                addr_id = data.getIntExtra("addr_id", 0);
                String consignee = data.getStringExtra("consignee");
                String province = data.getStringExtra("province");
                String city = data.getStringExtra("city");
                String area = data.getStringExtra("area");
                String detail = data.getStringExtra("detail");
                String mobile = data.getStringExtra("mobile");
                String landline = data.getStringExtra("landline");
                tvName.setText(consignee);
                tvPhone.setText(mobile);
                tvAddress.setText(province + city + area + detail);
                tvTelPhone.setText(landline);
            }
        }
    }
}
