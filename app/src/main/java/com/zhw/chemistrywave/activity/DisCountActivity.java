package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Shangpxiangqing;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class DisCountActivity extends BaseActivity {


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
    @BindView(R.id.cb_discount_youhuizhekou)
    CheckBox cbDiscountYouhuizhekou;
    @BindView(R.id.ll_discount_youhuizhekoucb)
    LinearLayout llDiscountYouhuizhekoucb;
    @BindView(R.id.et_discount_youhuizhekou)
    EditText etDiscountYouhuizhekou;
    @BindView(R.id.ll_discount_youhuizhekou)
    LinearLayout llDiscountYouhuizhekou;
    @BindView(R.id.cb_discount_xianshimiaosha)
    CheckBox cbDiscountXianshimiaosha;
    @BindView(R.id.ll_discount_xianshimiaoshacb)
    LinearLayout llDiscountXianshimiaoshacb;
    @BindView(R.id.et_discount_xianshimiaosha)
    EditText etDiscountXianshimiaosha;
    @BindView(R.id.et_discount_miaoshadatestart)
    EditText etDiscountMiaoshadatestart;
    @BindView(R.id.et_discount_miaoshadateend)
    EditText etDiscountMiaoshadateend;
    @BindView(R.id.ll_discount_xianshimiaosha)
    LinearLayout llDiscountXianshimiaosha;
    @BindView(R.id.tv_discount_save)
    TextView tvDiscountSave;
    private boolean flag = true;
    private String mer_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_count);
        ButterKnife.bind(this);
        mer_id = getIntent().getStringExtra("mer_id");
        String type = getIntent().getStringExtra("type");
        switch (type) {
            case "EditGoodsActivity":
                getData();
                break;
            case "TianJiaShangPingActivity":
                break;
        }
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("折扣优惠");
        llDiscountYouhuizhekoucb.performClick();

    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_discount_youhuizhekoucb, R.id.ll_discount_xianshimiaoshacb, R.id.tv_discount_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.ll_discount_youhuizhekoucb:
                if (flag) {
                    cbDiscountYouhuizhekou.setChecked(true);
                    flag = !flag;
                } else {
                    cbDiscountYouhuizhekou.setChecked(false);
                    flag = !flag;
                }

//                cbDiscountXianshimiaosha.setChecked(false);
//                llDiscountYouhuizhekou.setVisibility(View.VISIBLE);
//                llDiscountXianshimiaosha.setVisibility(View.GONE);
                break;
            case R.id.ll_discount_xianshimiaoshacb:
//                cbDiscountYouhuizhekou.setChecked(false);
//                cbDiscountXianshimiaosha.setChecked(true);
//                llDiscountYouhuizhekou.setVisibility(View.GONE);
//                llDiscountXianshimiaosha.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_discount_save:
                if (cbDiscountYouhuizhekou.isChecked()) {
                    saveZheKou();

                } else {
                    finish();
                }
                break;
        }
    }

    private void saveZheKou() {
        String price = etDiscountYouhuizhekou.getText().toString().trim();
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, "请输入优惠价格", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("kill_price", price);
        map.put("mer_id", mer_id);
        OkHttpUtils.post().url(NetConfig.modifygoods_url)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---优惠价格返回---error--->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----优惠价格返回------>" + response);//{"code":0}
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    Toast.makeText(DisCountActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getData() {
        Log.e("aaa", "--商品详情参数--mer_id-->" + mer_id);
        OkHttpUtils.post().url(NetConfig.goodsdetail_url)
                .addParams("mer_id", String.valueOf(mer_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--商品详情返回--error->" + e.getMessage());
                        Toast.makeText(DisCountActivity.this, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--商品详情返回--->" + response);
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Shangpxiangqing shangpxiangqing = gson.fromJson(response, Shangpxiangqing.class);
                                    String killprice = shangpxiangqing.getData().getCurrent_price();
                                    etDiscountYouhuizhekou.setText(killprice != null ? killprice : "");
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(DisCountActivity.this, "获取数据失败，请检查网络", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
