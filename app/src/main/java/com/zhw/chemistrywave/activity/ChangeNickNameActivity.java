package com.zhw.chemistrywave.activity;

import android.content.Intent;
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

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ChangeNickNameActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_nick_name);
        ButterKnife.bind(this);
        flag = getIntent().getStringExtra("flag");
        if (flag.equals("name")){
            tvTitlebarCenter.setText("Change Nickname");
            String nickName = getIntent().getStringExtra("nickName");
            etNickname.setText(nickName);
            tvText.setText("The current nickname");
        }else {
            tvTitlebarCenter.setText("Change Address");
            String nickName = getIntent().getStringExtra("address");
            etNickname.setText(nickName);
            tvText.setText("The current address");
        }


    }

    @OnClick({R.id.rl_titlebar_back, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.btn_commit:
                if (flag.equals("name"))
                commitNickName();
                else
                    commitaddress();
                break;
        }
    }

    private void commitaddress() {
        final String nickName = etNickname.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)){
            Toast.makeText(this,"please fill in the address",Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("user_area",nickName);

        OkHttpUtils.post()
                .url(NetConfig.user_update)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(ChangeNickNameActivity.java:69)<--修改昵称的失败返回-->"+e.getMessage());
                        Toast.makeText(ChangeNickNameActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(ChangeNickNameActivity.java:76)<--修改昵称的成功返回-->"+response);
                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(ChangeNickNameActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    Toast.makeText(ChangeNickNameActivity.this, "change successfully", Toast.LENGTH_SHORT).show();
                                    ChangeNickNameActivity.this.setResult(44,new Intent().putExtra("address",nickName));
                                    ChangeNickNameActivity.this.finish();
                                }else {
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ChangeNickNameActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    private void commitNickName() {

        final String nickName = etNickname.getText().toString().trim();
        if (TextUtils.isEmpty(nickName)){
            Toast.makeText(this,"please fill in nickname",Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("user_id", MyUtils.getUser().getUser_id());
        params.put("user_name",nickName);

        OkHttpUtils.post()
                .url(NetConfig.user_update)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(ChangeNickNameActivity.java:69)<--修改昵称的失败返回-->"+e.getMessage());
                        Toast.makeText(ChangeNickNameActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(ChangeNickNameActivity.java:76)<--修改昵称的成功返回-->"+response);
                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(ChangeNickNameActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    Toast.makeText(ChangeNickNameActivity.this, "change successfully", Toast.LENGTH_SHORT).show();
                                    ChangeNickNameActivity.this.setResult(44,new Intent().putExtra("nickName",nickName));
                                    ChangeNickNameActivity.this.finish();
                                }else {
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ChangeNickNameActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }
}
