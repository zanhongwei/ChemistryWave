package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
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
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.ll_company_name)
    LinearLayout llCompanyName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.ll_company_address)
    LinearLayout llCompanyAddress;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_company_sex)
    TextView tvCompanySex;
    @BindView(R.id.ll_company_sex)
    LinearLayout llCompanySex;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.ll_company_phone)
    LinearLayout llCompanyPhone;
    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.ll_company_business)
    LinearLayout llCompanyBusiness;
    @BindView(R.id.tv_company_others)
    TextView tvCompanyOthers;
    @BindView(R.id.ll_company_others)
    LinearLayout llCompanyOthers;
    @BindView(R.id.ll_company)
    LinearLayout llCompany;
    @BindView(R.id.tv_person_name)
    TextView tvPersonName;
    @BindView(R.id.ll_person_name)
    LinearLayout llPersonName;
    @BindView(R.id.tv_person_phone)
    TextView tvPersonPhone;
    @BindView(R.id.ll_person_address)
    LinearLayout llPersonAddress;
    @BindView(R.id.ll_person_idcard)
    LinearLayout llPersonIdcard;
    @BindView(R.id.ll_person_other)
    LinearLayout llPersonOther;
    @BindView(R.id.ll_person)
    LinearLayout llPerson;
    @BindView(R.id.tv_company_info)
    TextView tvCompanyInfo;
    @BindView(R.id.ll_company_info)
    LinearLayout llCompanyInfo;
    @BindView(R.id.tv_company_environment)
    TextView tvCompanyEnvironment;
    @BindView(R.id.ll_company_environment)
    LinearLayout llCompanyEnvironment;
    @BindView(R.id.tv_company_product)
    TextView tvCompanyProduct;
    @BindView(R.id.ll_company_product)
    LinearLayout llCompanyProduct;
    private String user_state;
    private String user_name;
    private String user_phone;
    private String company_name;
    private String company_address;
    private String user_area;
    private String cardo_src;//身份证正面
    private String cardn_src;//身份证反面
    private String cardh_src;//身份证手持正面
    private String attachment;//附件

    private int CHANGE_NICKNAME_CODE = 1;
    private int CHANGE_PHONE_CODE = 2;
    private int CHANGE_ADDRESS = 3;
    private int CHANGE_CONTACT_NAME = 4;
    private MyPopupWindow sexContentPoP;
    private String sex;
    private String shop_id;
    private String license_src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_company_info);
        ButterKnife.bind(this);

        user_state = (String) SPUtils.get(this, "user_state", "1");

        initView();
        initData();

    }

    private void initView() {
        tvTitlebarCenter.setText("My Information");
        switch (user_state) {
            case "1":
                llCompany.setVisibility(View.GONE);
                llPerson.setVisibility(View.VISIBLE);
                break;
            case "2":
                llPerson.setVisibility(View.GONE);
                llCompany.setVisibility(View.VISIBLE);
                break;
        }
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
                                        user_name = result.getString("real_name") != null ? result.getString("real_name") : "";
                                        tvPersonName.setText(user_name);
                                        user_phone = result.getString("user_phone") != null ? result.getString("user_phone") : "";
                                        tvPersonPhone.setText(user_phone);
                                        cardo_src = result.getString("cardo_src") != null ? result.getString("cardo_src") : "";
                                        cardn_src = result.getString("cardn_src") != null ? result.getString("cardn_src") : "";
                                        cardh_src = result.getString("cardh_src") != null ? result.getString("cardh_src") : "";
                                        attachment = result.getString("attachment") != null ? result.getString("attachment") : "";
                                    } else {
                                        CompanySupplier companySupplier = new Gson().fromJson(response, CompanySupplier.class);
                                        company_name = companySupplier.getResult().getCompany_name();
                                        tvCompanyName.setText(company_name);
                                        user_area = companySupplier.getResult().getCompany_address();
                                        tvCompanyAddress.setText(user_area);
                                        user_name = companySupplier.getResult().getContact_name();
                                        tvCompanySex.setText(user_name);
                                        user_phone = companySupplier.getResult().getContact_phone();
                                        tvCompanyPhone.setText(user_phone);
                                        license_src = companySupplier.getResult().getLicense_src();
                                        attachment = companySupplier.getResult().getAttachment();
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

    @OnClick({R.id.rl_titlebar_back, R.id.ll_company_name, R.id.ll_company_phone, R.id.ll_company_address,
            R.id.ll_company_sex, R.id.ll_company_info, R.id.ll_company_environment, R.id.ll_company_product,
            R.id.ll_company_business, R.id.ll_company_others, R.id.ll_person_name, R.id.ll_person_address,
            R.id.ll_person_idcard, R.id.ll_person_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.ll_company_name:
                //  2018/4/11 修改公司名称
                startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                        .putExtra("nickName", company_name)
                        .putExtra("flag", "name"), CHANGE_NICKNAME_CODE);
                break;
            case R.id.ll_company_phone://修改联系电话
//                if (user_state.equals("1"))
//                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
//                            .putExtra("nickName", user_name)
//                            .putExtra("flag", "address"), CHANGE_ADDRESS);
//                else
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangePhoneActivity.class)
                            .putExtra("phone", user_phone), CHANGE_PHONE_CODE);
                break;
            case R.id.ll_company_address:
//                if (user_state.equals("1"))
//                    showSexChoose();
//                else
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                            .putExtra("nickName", user_area)
                            .putExtra("flag", "address"), CHANGE_ADDRESS);
                break;
            case R.id.ll_company_sex://修改联系人姓名

                Intent intent = new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class);
                intent.putExtra("nickName",user_name);
                intent.putExtra("flag","contact");
                startActivityForResult(intent,CHANGE_CONTACT_NAME);
//                if (user_state.equals("1"))

//                else
//                    showSexChoose();
                break;

            case R.id.ll_company_business://查看公司营业执照
                Intent intent1 = new Intent(this, PhotoInfomationActivity.class);
                intent1.putExtra("url",license_src);
                intent1.putExtra("flag","business");
                startActivity(intent1);
                break;
            case R.id.ll_company_others://其他信息
                Intent intent2 = new Intent(this, PhotoInfomationActivity.class);
                intent2.putExtra("url",attachment);
                intent2.putExtra("flag","attachment");
                startActivity(intent2);
                break;
            case R.id.ll_person_name:
                startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangeNickNameActivity.class)
                        .putExtra("nickName", user_name)
                        .putExtra("flag", "name"), CHANGE_NICKNAME_CODE);
                break;

            case R.id.ll_person_address://个人电话
                    startActivityForResult(new Intent(MyCompanyInfoActivity.this, ChangePhoneActivity.class)
                            .putExtra("phone", user_phone), CHANGE_PHONE_CODE);
                break;

            case R.id.ll_person_idcard:
                Intent intent3 = new Intent(this, PhotoInfomationActivity.class);
                intent3.putExtra("url",cardo_src);
                intent3.putExtra("url2",cardn_src);
                intent3.putExtra("url3",cardh_src);
                intent3.putExtra("flag","idCard");
                startActivity(intent3);
                break;
            case R.id.ll_person_other:
                Intent intent4 = new Intent(this, PhotoInfomationActivity.class);
                intent4.putExtra("url",attachment);
                intent4.putExtra("flag","attachment");
                startActivity(intent4);
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
}
