package com.zhw.chemistrywave.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.FreeSearchGoodsActivity;
import com.zhw.chemistrywave.activity.HasHandleActivity;
import com.zhw.chemistrywave.activity.MessageActivity;
import com.zhw.chemistrywave.activity.MyCompanyInfoActivity;
import com.zhw.chemistrywave.activity.MyGoodsActivity;
import com.zhw.chemistrywave.activity.MyInformationActivity;
import com.zhw.chemistrywave.activity.MyServiceActivity;
import com.zhw.chemistrywave.activity.OpenAshopResultActivity;
import com.zhw.chemistrywave.activity.SettingActivity;
import com.zhw.chemistrywave.activity.ShopCarActivity;
import com.zhw.chemistrywave.activity.SupplierSearchActivity;
import com.zhw.chemistrywave.activity.TianJiaShangPingActivity;
import com.zhw.chemistrywave.activity.UnHandleActivity;
import com.zhw.chemistrywave.adapters.ShopCarAdapter;
import com.zhw.chemistrywave.bean.ShopCar;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhw.chemistrywave.view.CircleImageView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.zhw.chemistrywave.utils.MyUtils.getUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCenterFragment extends Fragment {


    @BindView(R.id.civ_mycenterf_headimage)
    CircleImageView civMycenterfHeadimage;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.ll_mycenterf_wodeshangping)
    LinearLayout llWodeshangpin;
    @BindView(R.id.ll_mycenterf_tianjiashangping)
    LinearLayout llTianjiashangpin;
    @BindView(R.id.ll_mycenterf_xitongxunpan)
    LinearLayout llXitongxunpan;
    @BindView(R.id.ll_mycenterf_daichulixunpan)
    LinearLayout llDaichulixunpan;
    @BindView(R.id.ll_mycenterf_yichulixunpan)
    LinearLayout llYichulixunpan;
    @BindView(R.id.ll_mycenterf_gongyingshangxunpan)
    LinearLayout llGongyingshangxunpan;
    @BindView(R.id.view_shopcar)
    View vShopCar;
    @BindView(R.id.ll_shopcar)
    LinearLayout llShopCar;
    @BindView(R.id.ll_mycenterf_hualangzhiyan)
    LinearLayout llPriceExpress;//价格速递
    @BindView(R.id.ll_mycenterf_zhaodaili)
    LinearLayout llPlatformGua;//平台交易
    @BindView(R.id.tv_shopcar_num)
    TextView tvShopCarNum;
    @BindView(R.id.tv_message_num)
    TextView tvMessageNum;

    Unbinder unbinder;
    private String user_id;
    private String user_name;
    private String user_photo;
    private String user_state;

    private String shopState = "";//店铺是否透过审核

    public MyCenterFragment() {
        // Required empty public constructor
    }

    @OnClick({R.id.ll_settings, R.id.ll_message, R.id.ll_shopcar, R.id.ll_mycenterf_wodeshangping,
            R.id.ll_mycenterf_tianjiashangping, R.id.ll_mycenterf_wodeziliao, R.id.ll_mycenterf_daichulixunpan,
            R.id.ll_mycenterf_yichulixunpan, R.id.ll_mycenterf_xitongxunpan, R.id.ll_mycenterf_gongyingshangxunpan,
            R.id.ll_mycenterf_pingtaijiance, R.id.ll_mycenterf_hualangzhiyan, R.id.ll_mycenterf_zhaodaili,
            R.id.ll_mycenterf_zhengxinchaxun, R.id.ll_mycenterf_tousujiufen, R.id.civ_mycenterf_headimage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.civ_mycenterf_headimage:
                if (user_state.equals("0"))
                    startActivity(new Intent(getActivity(), MyInformationActivity.class));
                else
                    startActivity(new Intent(getActivity(), MyCompanyInfoActivity.class));
                break;
            case R.id.ll_settings:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
            case R.id.ll_message:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            //我的商品
            case R.id.ll_mycenterf_wodeshangping:
                startActivity(new Intent(getActivity(), MyGoodsActivity.class));
                break;
            //我添加的商品
            case R.id.ll_mycenterf_tianjiashangping:
                Toast.makeText(getActivity(), "Please go to the website to add goods", Toast.LENGTH_SHORT).show();
//                return;
//                saveSp();

//                if (TextUtils.isEmpty(shopState)){
//                    ToastUtil.showToastShort(getActivity(),"Please open a shop first!");
//                }else {
//                    if (shopState.equals("2")){
//                        Intent intent1 = new Intent(getActivity(), TianJiaShangPingActivity.class);
////                intent1.putExtra("mer_id",data);
//                        startActivity(intent1);
//                    }else if (shopState.equals("1")){
//                        ToastUtil.showToastShort(getActivity(),"Please wait for store approval!");
//                    }else {
//                        startActivity(new Intent(getActivity(),OpenAshopResultActivity.class));
//                    }
//                }
                break;
            case R.id.ll_shopcar:
                startActivity(new Intent(getActivity(), ShopCarActivity.class));
                break;
            //我的资料
            case R.id.ll_mycenterf_wodeziliao:
                if (user_state.equals("0"))
                    startActivity(new Intent(getActivity(), MyInformationActivity.class));
                else
                    startActivity(new Intent(getActivity(), MyCompanyInfoActivity.class));

                break;
            //待处理询盘
            case R.id.ll_mycenterf_daichulixunpan:
                startActivity(new Intent(getActivity(), UnHandleActivity.class));
                break;
            //已处理询盘
            case R.id.ll_mycenterf_yichulixunpan:
                startActivity(new Intent(getActivity(), HasHandleActivity.class));
                break;
            //系统询盘
            case R.id.ll_mycenterf_xitongxunpan:
                startActivity(new Intent(getActivity(), FreeSearchGoodsActivity.class));
                break;
            //供应商询盘
            case R.id.ll_mycenterf_gongyingshangxunpan:
                startActivity(new Intent(getActivity(), SupplierSearchActivity.class));
                break;
            //平台监测
            case R.id.ll_mycenterf_pingtaijiance:
                startActivity(new Intent(getActivity(), MyServiceActivity.class).putExtra("name", "Harlan eye"));
                break;
            //化浪之眼
            case R.id.ll_mycenterf_hualangzhiyan:
                startActivity(new Intent(getActivity(), MyServiceActivity.class).putExtra("name", "Price express"));
                break;
            //招代理
            case R.id.ll_mycenterf_zhaodaili:
                startActivity(new Intent(getActivity(), MyServiceActivity.class).putExtra("name", "Platform guarantee"));
                break;
            //征信查询
            case R.id.ll_mycenterf_zhengxinchaxun:
                startActivity(new Intent(getActivity(), MyServiceActivity.class).putExtra("name", "Supply chain finance"));
                break;
            //投诉纠纷
            case R.id.ll_mycenterf_tousujiufen:
//                startActivity(new Intent(getActivity(), MyServiceActivity.class).putExtra("name", "Complaint and Dispute"));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!user_state.equals("0")) {
            getShopDetail();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_center, container, false);
        unbinder = ButterKnife.bind(this, view);

        user_state = (String) SPUtils.get(getActivity(), "user_state", "0");

        initView();

        getUserInfo();//获取用户信息

        getShopCarInfo();//获取购物车数量

        getMessageNum();//获取未读消息数量


        return view;
    }


    private void initView() {

        if (user_state.equals("0")) {
            llWodeshangpin.setVisibility(View.GONE);
            llTianjiashangpin.setVisibility(View.GONE);
            llDaichulixunpan.setVisibility(View.GONE);
            llYichulixunpan.setVisibility(View.GONE);
        } else {
            vShopCar.setVisibility(View.GONE);
            llShopCar.setVisibility(View.GONE);
            llGongyingshangxunpan.setVisibility(View.GONE);
            getShopDetail();
        }

        llPriceExpress.setVisibility(View.GONE);
        llPlatformGua.setVisibility(View.GONE);
    }

    private void getUserInfo() {
        OkHttpUtils.post().url(NetConfig.user_detail)
                .addParams("user_id", getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyCenterFragment.java:255)<---->" + e.getMessage());
                        Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "-获取用户信息-->" + response);
                        /**
                         * {
                         "result": {
                         "user_id": 1,
                         "user_name": "张",
                         "user_phone": "1",
                         "user_password": "1",
                         "user_code": null,
                         "user_type": "1",
                         "company_name": null,
                         "company_address": null,
                         "company_landline": null,
                         "trade_tendency": null,
                         "u_QQ": null,
                         "u_wechat": null,
                         "u_weibo": null,
                         "register_time": null,
                         "user_state": null,
                         "user_photo": null,
                         "user_gender": "1",
                         "user_area": "北京市 北京市 东城区",
                         "domestic": null
                         },
                         "code": 0
                         }
                         */
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    JSONObject result = jsonObject.getJSONObject("result");
                                    user_id = result.getString("user_id");
                                    user_name = result.getString("user_name") != null ? result.getString("user_name") : "";
                                    user_photo = result.getString("user_photo") != null ? result.getString("user_photo") : "";
                                    textView.setText(user_name != null ? user_name : "User name");
//                                    String user_gender = result.getString("user_gender");
//                                    switch (user_gender) {
//                                        // //用户性别1：男，0：保密，-1：女
//                                        case "1":
//                                            tvMyinfoGender.setText("男");
//                                            break;
//                                        case "0":
//                                            tvMyinfoGender.setText("保密");
//                                            break;
//                                        case "-1":
//                                            tvMyinfoGender.setText("女");
//                                            break;
//                                        default:
//                                            tvMyinfoGender.setText("请点击设置");
//                                            break;
//                                    }
//                                    String user_area = result.getString("user_area");
//                                    tvMyinfoAddress.setText(user_area != null ? user_area : "请点击设置");
                                    Log.e("aaa", "-result.getString(\"user_photo\")-->" + NetConfig.baseurl + result.getString("user_photo"));
                                    if (user_state.equals("0")) {
                                        if (TextUtils.isEmpty(result.getString("user_photo"))
                                                || "null".equals(result.getString("user_photo"))
                                                || null == result.getString("user_photo")) {

//                                        civMycenterfHeadimage.setImageResource(R.drawable.default_userinfo);
                                            Glide.with(getActivity()).load("http://www.harlanchina.com/photo.png").into(civMycenterfHeadimage);
                                        } else {
                                            ImageLoader.getInstance().displayImage(NetConfig.baseurl + result.getString("user_photo"), civMycenterfHeadimage);
                                        }
                                    } else {
                                        if (TextUtils.isEmpty(result.getString("user_photo"))
                                                || "null".equals(result.getString("user_photo"))
                                                || null == result.getString("user_photo")) {

//                                        civMycenterfHeadimage.setImageResource(R.drawable.default_userinfo);
                                            Glide.with(getActivity()).load("http://www.harlanchina.com/photo.png").into(civMycenterfHeadimage);
                                        } else {
                                            ImageLoader.getInstance().displayImage(NetConfig.baseurl + result.getString("user_photo"), civMycenterfHeadimage);
                                        }
                                    }

//                                    String user_type = result.getString("user_type");
                                    //类型0 个人采购 1个人供货 2公司供货

                                } else {
                                    Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getShopCarInfo() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("page", "1");
            jsonObject.put("limit", "100000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(NetConfig.shopcar_url)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--查询我的购物车返回-error-->" + e.toString());
                        ToastUtil.showToastShort(getActivity(), "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--查询我的购物车返回--->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    ShopCar shopCar = gson.fromJson(response, ShopCar.class);
                                    List<ShopCar.DataBean.ListBean> list = shopCar.getData().getList();
                                    if (list.size() > 0) {
                                        tvShopCarNum.setVisibility(View.VISIBLE);
                                        tvShopCarNum.setText(String.valueOf(list.size()));
                                    } else {
                                        tvShopCarNum.setVisibility(View.GONE);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void getMessageNum() {

        JSONObject object = new JSONObject();
        try {
            object.put("user_id", MyUtils.getUser().getUser_id());
            object.put("status", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils
                .post()
                .url(NetConfig.queryUnreadNumber)
                .addParams("jsonStr", object.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "(MyCenterFragment.java:369)<--获取未读消息数量-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "(MyCenterFragment.java:374)<--获取未读消息的数量-->" + response);
                        if (TextUtils.isEmpty(response)) {

                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    int data = jsonObject.getInt("data");
                                    if (data > 0) {
                                        tvMessageNum.setVisibility(View.VISIBLE);
                                        tvMessageNum.setText(String.valueOf(data));
                                    } else {
                                        tvMessageNum.setVisibility(View.GONE);
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
//        getUserInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void getShopDetail() {
        OkHttpUtils
                .post()
                .url(NetConfig.SHOP_DETAIL)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyCenterFragment.java:229)<--获取开店信息失败返回-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyCenterFragment.java:235)<--获取开店信息成功返回-->" + response);

                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(getActivity(), "Network error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    JSONObject jsonObject1 = new JSONObject(response);
                                    JSONObject data = jsonObject1.getJSONObject("data");
                                    shopState = data.getString("shop_state");
                                } else if (code == 500) {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
