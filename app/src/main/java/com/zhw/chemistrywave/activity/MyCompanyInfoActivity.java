package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.CompanySupplier;
import com.zhw.chemistrywave.bean.ShopDetail;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhw.chemistrywave.view.MyPopupWindow;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MyCompanyInfoActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.ll_company_sex)
    LinearLayout llCompanySex;
    @BindView(R.id.tv_company_sex)
    TextView tvCompanySex;
    @BindView(R.id.ll_company_info)
    LinearLayout llCompanyInfo;
    @BindView(R.id.ll_company_environment)
    LinearLayout llCompanyEnvironment;
    @BindView(R.id.tv_company_product)
    TextView tvCompanyProduct;
    private String user_state;
    private String user_name;
    private String user_sex;
    private String user_phone;
    private String user_area;
    private int CHANGE_NICKNAME_CODE = 1;
    private int CHANGE_PHONE_CODE = 2;
    private int CHANGE_ADDRESS = 3;
    private MyPopupWindow sexContentPoP;
    private String sex;
    private String shop_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_company_info);
        ButterKnife.bind(this);

        user_state = (String) SPUtils.get(this, "user_state", "1");
        initView();
        initData();

    }

    private void initData() {

        OkHttpUtils.post()
                .url(NetConfig.user_detail)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:63)<---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:70)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(MyCompanyInfoActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    if (user_state.equals("1")) {
                                        JSONObject result = jsonObject.getJSONObject("result");
                                        user_name = result.getString("user_name") != null ? result.getString("user_name") : "";
                                        tvCompanyName.setText(user_name);
                                        user_sex = result.getString("user_gender") != null ? result.getString("user_gender") : "男";
                                        tvCompanyPhone.setText(user_sex);
                                        user_phone = result.getString("user_phone") != null ? result.getString("user_phone") : "";
                                        tvCompanyAddress.setText(user_phone);
                                        user_area = result.getString("user_area") != null ? result.getString("user_area") : "";
                                        tvCompanySex.setText(user_area);
                                    } else {
                                        CompanySupplier companySupplier = new Gson().fromJson(response, CompanySupplier.class);
                                        tvCompanyName.setText(companySupplier.getResult().getUser_name());
                                        tvCompanyPhone.setText(companySupplier.getResult().getUser_phone());
                                        tvCompanyAddress.setText(companySupplier.getResult().getCompany_address());
                                        tvCompanySex.setText(companySupplier.getResult().getUser_gender());
                                    }
                                    shop_id = jsonObject.getString("shop_id");

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initView() {

        tvTitlebarCenter.setText("My Information");

        switch (user_state) {

            case "1":
                ((TextView) findViewById(R.id.tv_name)).setText("name");
                ((TextView) findViewById(R.id.tv_phone)).setText("gender");
                ((TextView) findViewById(R.id.tv_address)).setText("phone");
                ((TextView) findViewById(R.id.tv_sex)).setText("address");
                break;

            case "2":
                ((TextView) findViewById(R.id.tv_name)).setText("Com-name");
                ((TextView) findViewById(R.id.tv_phone)).setText("Com-phone");
                ((TextView) findViewById(R.id.tv_address)).setText("Com-address");
                ((TextView) findViewById(R.id.tv_sex)).setText("Com-person sex");
                break;
        }
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_company_name, R.id.ll_company_phone, R.id.ll_company_address, R.id.ll_company_sex, R.id.ll_company_info, R.id.ll_company_environment, R.id.ll_company_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.ll_company_name:
                //  2018/4/11 修改昵称
                startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                        .putExtra("nickName", user_name)
                        .putExtra("flag", "name"), CHANGE_NICKNAME_CODE);
                break;
            case R.id.ll_company_phone:
                if (user_state.equals("1"))
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                            .putExtra("nickName", user_name)
                            .putExtra("flag", "address"), CHANGE_ADDRESS);
                else
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangePhoneActivity.class)
                            .putExtra("phone", user_phone), CHANGE_PHONE_CODE);
                break;
            case R.id.ll_company_address:
                if (user_state.equals("1"))
                    showSexChoose();
                else
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                            .putExtra("nickName", user_name)
                            .putExtra("flag", "address"), CHANGE_ADDRESS);
                break;
            case R.id.ll_company_sex:
                if (user_state.equals("1"))
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangePhoneActivity.class)
                            .putExtra("phone", user_phone), CHANGE_PHONE_CODE);
                else
                    showSexChoose();
                break;
            case R.id.ll_company_info:
                getShopDetail("2");
                break;
            case R.id.ll_company_environment:
                getShopDetail("1");
                break;
            case R.id.ll_company_product:
                getShopDetail("3");

                break;
        }
    }

    /**
     * 显示性别选择view
     */
    private void showSexChoose() {
        View v = View.inflate(MyCompanyInfoActivity.this, R.layout.choose_sex, null);
        TextView tvMan = (TextView) v.findViewById(R.id.tv_man);
        TextView tvWoman = (TextView) v.findViewById(R.id.tv_woman);
        sexContentPoP = new MyPopupWindow(MyCompanyInfoActivity.this, v);
        sexContentPoP.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        sexContentPoP.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);

            }
        });

        tvMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sex = "Man";
                sexContentPoP.dismiss();
                changeSex();

            }
        });

        tvWoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sex = "Woman";
                sexContentPoP.dismiss();
                changeSex();

            }
        });
    }

    //change gender
    private void changeSex() {

        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("user_gender", sex);

        OkHttpUtils.post()
                .url(NetConfig.user_update)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:231)<---->" + e.getMessage());
                        Toast.makeText(MyCompanyInfoActivity.this, "Network error!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:238)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(MyCompanyInfoActivity.this, "Network error!", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {

                                    if (user_state.equals("1"))
                                        tvCompanyPhone.setText(sex);
                                    else
                                        tvCompanySex.setText(sex);

                                } else {
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(MyCompanyInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_NICKNAME_CODE) {
            //nickname
            if (null != data) {
                String nickname = data.getStringExtra("nickName");
                tvCompanyName.setText(nickname);
            }

        } else if (requestCode == CHANGE_ADDRESS) {
            //address
            if (null != data) {
                String address = data.getStringExtra("address");
                if (user_state.equals("1"))
                    tvCompanySex.setText(address);
                else tvCompanyAddress.setText(address);
            }
        } else {
            getShopDetail("0");
        }
    }

    private void getShopDetail(final String flag) {

        OkHttpUtils
                .post()
                .url(NetConfig.SHOP_DETAIL)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:211)<---->" + e.getMessage());
                        ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyCompanyInfoActivity.java:217)<---->" + response);

                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Network error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    ShopDetail shopDetail = new Gson().fromJson(response, ShopDetail.class);
                                    String shop_state = shopDetail.getData().getShop_state();
                                    shop_id = shopDetail.getData().getShop_id() + "";
                                    if (flag.equals("2")) {
                                        if (shop_state.equals("1")) {
                                            startActivity(new Intent(MyCompanyInfoActivity.this, ApplyResultActivity.class));
                                        } else if (shop_state.equals("2")) {
                                            if (user_state.equals("1"))
                                                startActivity(new Intent(MyCompanyInfoActivity.this, MyShopActivity.class));
                                            else
                                                startActivity(new Intent(MyCompanyInfoActivity.this, MyShopComActivity.class));
                                        } else {
                                            startActivity(new Intent(MyCompanyInfoActivity.this, OpenAshopResultActivity.class));
                                        }
                                    } else if (flag.equals("1")) {

                                        if (TextUtils.isEmpty(shop_id) || shop_id.equals("0")) {
                                            ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Please wait for store approval!");
                                        } else {
                                            startActivity(new Intent(MyCompanyInfoActivity.this, CompanyEnvironmentActivity.class).putExtra("shopId", shop_id));
                                        }

                                    } else if (flag.equals("3")) {
                                        if (TextUtils.isEmpty(shop_id) || shop_id.equals("0")) {
                                            ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Please wait for store approval!");
                                        } else {
                                            startActivity(new Intent(MyCompanyInfoActivity.this, CompanyProductActivity.class).putExtra("shopId", shop_id));
                                        }
                                    }

                                } else if (code == 500) {
                                    if (flag.equals("2"))
                                        startActivity(new Intent(MyCompanyInfoActivity.this, OpenAShopActivity.class));
                                    else if ("1".equals(flag))
                                        ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Please open a shop first!");
                                    else if ("3".equals(flag))
                                        ToastUtil.showToastShort(MyCompanyInfoActivity.this, "Please open a shop first!");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
