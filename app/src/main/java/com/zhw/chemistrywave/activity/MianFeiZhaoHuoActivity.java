package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.MianFeiZhaoHuolLvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.NewBaoJia;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

;

public class MianFeiZhaoHuoActivity extends BaseActivity {

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
    @BindView(R.id.lv_mianfeizhaohuo)
    ListView lvMianfeizhaohuo;
    @BindView(R.id.tv_miangeizhaohuo)
    TextView tvMiangeizhaohuo;
    @BindView(R.id.tv_mianfeizhaohuo_shuoming)
    TextView tvMianfeizhaohuoShuoming;
    private List<NewBaoJia.DataBean> mList;
    private MianFeiZhaoHuolLvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian_fei_zhao_huo);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView() {

        tvTitlebarCenter.setText("System inquiry");
        mList = new ArrayList<>();
        mAdapter = new MianFeiZhaoHuolLvAdapter(this,mList);
        lvMianfeizhaohuo.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        mList.clear();
        HashMap<String, String> params = new HashMap<>();
        params.put("page","1");
        params.put("limit","1000");
        params.put("tar_status","1");
        OkHttpUtils.post()
                .url(NetConfig.UNHANDLE_XUNPAN)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MianFeiZhaoHuoActivity.java:91)<--系统询盘的滚动列表失败返回-->"+e.getMessage());
                        Toast.makeText(MianFeiZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MianFeiZhaoHuoActivity.java:97)<--系统寻盘的滚动列表成功返回-->"+response);
                        if (!TextUtils.isEmpty(response)||!response.equals("null")){
                            NewBaoJia newBaoJia = new Gson().fromJson(response, NewBaoJia.class);
                            mList.addAll(newBaoJia.getData());
                            mAdapter.notifyDataSetChanged();
                            Log.e("aaa",
                                    "(MianFeiZhaoHuoActivity.java:105)<---->"+"集合的大小"+mList.size());
                        }else {
                            Toast.makeText(MianFeiZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_miangeizhaohuo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //我要找货
            case R.id.tv_miangeizhaohuo:
                Intent intent = new Intent(MianFeiZhaoHuoActivity.this, FaBuZhaoHuoActivity.class);
//                saveJiLu();
                startActivity(intent);
                break;
        }
    }

    /**
     * 保存记录
     */
    private void saveJiLu() {
        Log.e("aaa", "---保存记录参数----->" + MyUtils.getUser().getUser_id());
        OkHttpUtils.post().url(NetConfig.baocunjilu)
                .addParams("sponsor_id", MyUtils.getUser().getUser_id())
                .addParams("spon_type", "3")//发起类型（实单竞价：1 、实单采购：2、免费找货：3、以货易货：4）
                .addParams("domestic", "1")//国内：1，国外：2
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---保存记录返回--error--->" + e.getMessage());
                        Toast.makeText(MianFeiZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---保存记录返回----->" + response);
                        if (response != null) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    int spon_id = jo1.getInt("spon_id");
                                    MyUtils.putSpuString("spon_id", String.valueOf(spon_id));
                                    Intent intent = new Intent(MianFeiZhaoHuoActivity.this, FaBuZhaoHuoActivity.class);
                                    intent.putExtra("spon_id", String.valueOf(spon_id));
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MianFeiZhaoHuoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(MianFeiZhaoHuoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
