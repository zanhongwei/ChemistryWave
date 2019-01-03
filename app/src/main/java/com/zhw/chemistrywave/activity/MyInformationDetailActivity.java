package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
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
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

import static com.zhw.chemistrywave.utils.MyUtils.getUser;

public class MyInformationDetailActivity extends BaseActivity {

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
    @BindView(R.id.civ_user_photo)
    CircleImageView civUserPhoto;
    @BindView(R.id.tv_change_photo)
    TextView tvChangePhoto;
    @BindView(R.id.tv_account_num)
    TextView tvAccountNum;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.rb_myinfo_nan)
    RadioButton rbMyinfoNan;
    @BindView(R.id.rb_myinfo_nv)
    RadioButton rbMyinfoNv;
    @BindView(R.id.rb_myinfo_baomi)
    RadioButton rbMyinfoBaomi;
    @BindView(R.id.rg_myinfo_gender)
    RadioGroup rgMyinfoGender;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.iv_sex)
    ImageView ivSex;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.tv_save_infor)
    TextView tvSaveInfor;
    private String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information_detail);
        ButterKnife.bind(this);
        tvAccountNum.setText("" + getUser().getUser_phone());
        initView();
        getUserInfo();
    }

    private void getUserInfo() {
        OkHttpUtils.post().url(NetConfig.user_detail)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

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
                                    String user_name = result.getString("user_name");
                                    etUserName.setText(user_name != null ? user_name : "");
                                    String user_gender = result.getString("user_gender");
                                    switch (user_gender) {
                                        // //用户性别1：男，0：保密，-1：女
                                        case "1":
                                            rbMyinfoNan.setChecked(true);
                                            break;
                                        case "0":
                                            rbMyinfoBaomi.setChecked(true);
                                            break;
                                        case "-1":
                                            rbMyinfoNv.setChecked(true);
                                            break;
                                    }
                                    String user_area = result.getString("user_area");
                                    tvAddress.setText(user_area != null ? user_area : "");
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initView() {
        tvTitlebarCenter.setText("基本信息");
    }

    @OnClick({R.id.tv_address, R.id.rl_titlebar_back, R.id.civ_user_photo, R.id.tv_change_photo, R.id.iv_sex, R.id.iv_address, R.id.tv_save_infor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            case R.id.civ_user_photo:

                break;
            case R.id.tv_change_photo:
                break;
            case R.id.iv_sex:
                break;
            case R.id.iv_address:
                break;
            case R.id.tv_address:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideProvince(false);
                task.setHideCounty(false);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        Toast.makeText(MyInformationDetailActivity.this, "数据初始化失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        if (county == null) {
                            Toast.makeText(MyInformationDetailActivity.this, province.getAreaName() + city.getAreaName(), Toast.LENGTH_SHORT).show();
                        } else {
//                            Toast.makeText(MyInformationDetailActivity.this, "jjjj" + province.getAreaName() + city.getAreaName() + county.getAreaName(), Toast.LENGTH_SHORT).show();
                            tvAddress.setText(province.getAreaName() + city.getAreaName() + county.getAreaName() + "");
                        }
                    }
                });
//                task.execute("贵州", "毕节", "纳雍");
                task.execute();
                break;
            case R.id.tv_save_infor:
                saveUser();
                break;
        }
    }

    /**
     * 修改用户信息
     */
    private void saveUser() {
        String username = etUserName.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请填写昵称！", Toast.LENGTH_SHORT).show();
            return;
        }
        //用户性别1：男，0：保密，-1：女
        if (rbMyinfoNan.isChecked()) {
            gender = "1";
        } else if (rbMyinfoBaomi.isChecked()) {
            gender = "0";
        } else if (rbMyinfoNv.isChecked()) {
            gender = "-1";
        } else {
            Toast.makeText(this, "请选择性别", Toast.LENGTH_SHORT).show();
            return;
        }
        String address = tvAddress.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("user_id", getUser().getUser_id());
        map.put("user_name", username);
        map.put("user_gender", gender);
        map.put("user_area", address);
        OkHttpUtils.post().url(NetConfig.user_update)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "----修改用户信息返回-error--->" + e.getCause() + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----修改用户信息返回---->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject JO1 = new JSONObject(response);
                                int code = JO1.getInt("code");
                                if (code == 0) {
                                    Toast.makeText(MyInformationDetailActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
