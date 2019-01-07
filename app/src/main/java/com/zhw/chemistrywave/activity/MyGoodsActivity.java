package com.zhw.chemistrywave.activity;

import android.annotation.SuppressLint;
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
import com.zhw.chemistrywave.adapters.MyGoodsLvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.GoodsInfoBean;
import com.zhw.chemistrywave.event.ShangjiaEvent;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class MyGoodsActivity extends BaseActivity {


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
    @BindView(R.id.tv_myfaqibidding_zhengzaijingxing)
    TextView tvMyfaqibiddingZhengzaijingxing;
    @BindView(R.id.tv_myfaqibidding_zhengzaijingxingline)
    TextView tvMyfaqibiddingZhengzaijingxingline;
    @BindView(R.id.rl_myfaqibidding_zhengzaijingxing)
    RelativeLayout rlMyfaqibiddingZhengzaijingxing;
    @BindView(R.id.tv_myfaqibidding_yiwancheng)
    TextView tvMyfaqibiddingYiwancheng;
    @BindView(R.id.tv_myfaqibidding_yiwanchengline)
    TextView tvMyfaqibiddingYiwanchengline;
    @BindView(R.id.rl_myfaqibidding_yiwancheng)
    RelativeLayout rlMyfaqibiddingYiwancheng;
    @BindView(R.id.lv_mygoods)
    ListView lvMygoods;
    private List<GoodsInfoBean.DataBean.ListBean> mList;
    private MyGoodsLvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvTitlebarCenter.setText("My Goods");
        ivTitlebarLine.setVisibility(View.VISIBLE);
        rlTitlebarSearch.setVisibility(View.GONE);
        tvMyfaqibiddingZhengzaijingxing.setText("Shelf goods");
        tvMyfaqibiddingYiwancheng.setText("Removed goods");
        mList = new ArrayList<>();
        mAdapter = new MyGoodsLvAdapter(MyGoodsActivity.this, mList);
        getData("up");
        lvMygoods.setAdapter(mAdapter);
    }

    private void getData(final String state) {
        mList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("page", "1");
            jsonObject.put("limit", "10000");
            jsonObject.put("goods_state", state);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(NetConfig.mygoods_url)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---我的商品返回---error-->" + e.getMessage() + e.getCause());
                        Toast.makeText(MyGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---我的商品返回----->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    GoodsInfoBean ziYingGoods = gson.fromJson(response, GoodsInfoBean.class);
                                    List<GoodsInfoBean.DataBean.ListBean> list = ziYingGoods.getData().getList();
                                    mList.addAll(list);
                                    mAdapter.setTag(state);
                                } else {
                                    Toast.makeText(MyGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MyGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }

                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @SuppressLint("NewApi")
    @OnClick({R.id.rl_titlebar_back, R.id.rl_titlebar_search, R.id.rl_myfaqibidding_zhengzaijingxing, R.id.rl_myfaqibidding_yiwancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                onBackPressed();
                break;
            //搜索
            case R.id.rl_titlebar_search:
                break;
            //已上架
            case R.id.rl_myfaqibidding_zhengzaijingxing:
//                tvMygoodsYishangjia.setTextColor(getResources().getColor(R.color.white));
//                tvMygoodsYishangjia.setBackground(getResources().getDrawable(R.drawable.bj_yishangjia));
//                tvMygoodsYixiajia.setTextColor(getResources().getColor(R.color.a32));
//                tvMygoodsYixiajia.setBackground(getResources().getDrawable(R.drawable.bj_white));
                tvMyfaqibiddingZhengzaijingxing.setTextColor(getResources().getColor(R.color.b82db));
                tvMyfaqibiddingZhengzaijingxingline.setBackgroundColor(getResources().getColor(R.color.b82db));
                tvMyfaqibiddingYiwancheng.setTextColor(getResources().getColor(R.color.be));
                tvMyfaqibiddingYiwanchengline.setBackgroundColor(getResources().getColor(R.color.be));
                getData("up");
//                lvMygoods.setAdapter(mAdapter);
                break;
            //已下架
            case R.id.rl_myfaqibidding_yiwancheng:
//                tvMygoodsYixiajia.setTextColor(getResources().getColor(R.color.white));
//                tvMygoodsYixiajia.setBackground(getResources().getDrawable(R.drawable.bj_yishangjia));
//                tvMygoodsYishangjia.setTextColor(getResources().getColor(R.color.a32));
//                tvMygoodsYishangjia.setBackground(getResources().getDrawable(R.drawable.bj_white));
                tvMyfaqibiddingZhengzaijingxing.setTextColor(getResources().getColor(R.color.be));
                tvMyfaqibiddingZhengzaijingxingline.setBackgroundColor(getResources().getColor(R.color.be));
                tvMyfaqibiddingYiwancheng.setTextColor(getResources().getColor(R.color.b82db));
                tvMyfaqibiddingYiwanchengline.setBackgroundColor(getResources().getColor(R.color.b82db));
                getData("down");
//                lvMygoods.setAdapter(mAdapter);

                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void shangJia(ShangjiaEvent shangjiaEvent) {
        Log.e("aaa", "--收到了消息-->" + shangjiaEvent.getMsg());
        if (mList != null) {
            mList.clear();
        }
        getData("down");
    }
}
