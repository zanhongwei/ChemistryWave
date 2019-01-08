package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by Axehome_Mr.Z on 2019/01/07
 */
public class TianxiewuliuxinxiActivity extends BaseActivity {

    @BindView(R.id.et_company)
    EditText etCompany;
    @BindView(R.id.et_number)
    EditText etNumber;
    private String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tianxiewuliuxinxi);
        ButterKnife.bind(this);
        order_id = getIntent().getStringExtra("order_id");

    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                deliverGoods();
                break;
        }
    }

    private void deliverGoods() {

        String company = etCompany.getText().toString().trim();
        String number = etNumber.getText().toString().trim();

        if (company.isEmpty() || number.isEmpty()) {
            Toast.makeText(this, "Please enter full information", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("order_id", order_id);
        params.put("transport_corporation", company);
        params.put("transport_number", number);

        OkHttpUtils.post()
                .url(NetConfig.delivery_goods)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "(TianxiewuliuxinxiActivity.java:75)<---->" + e.getMessage());
                        Toast.makeText(TianxiewuliuxinxiActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "(TianxiewuliuxinxiActivity.java:81)<---->" + response);
                        if (!TextUtils.isEmpty(response)){
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    finish();
                                }else {
//                                    jsonObject.getString("");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
}
