package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ChangePwdsActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwds);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Change Password");
    }

    @OnClick({R.id.rl_titlebar_back, R.id.btn_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.btn_complete:
                changePwd();
                break;
        }
    }

    private void changePwd() {

        String newPwd = etNewPwd.getText().toString().trim();
        String newPwds = etPwd.getText().toString().trim();

        if (newPwd.equals(newPwds)){
            OkHttpUtils.post()
                    .url(NetConfig.changepwd)
                    .addParams("user_id", MyUtils.getUser().getUser_id())
                    .addParams("old_pass",MyUtils.getUser().getUser_password())
                    .addParams("new_pass",newPwd)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Log.e("aaa",
                                    "(ChangePwdsActivity.java:68)<---->"+e.getMessage());
                            Toast.makeText(ChangePwdsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Log.e("aaa",
                                    "(ChangePwdsActivity.java:75)<---->"+response);

                            if (TextUtils.isEmpty(response)){
                                Toast.makeText(ChangePwdsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                            }else {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    int code = jsonObject.getInt("code");
                                    if (code==0){
                                        finish();
                                    }
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ChangePwdsActivity.this, msg, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });
        }
    }
}
