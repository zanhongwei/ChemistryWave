package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.FreeSearchGoodsAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.XTXPOrder;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class FreeSearchGoodsActivity extends BaseActivity {


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
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.view_all)
    View viewAll;
    @BindView(R.id.rl_all)
    RelativeLayout rlAll;
    @BindView(R.id.tv_to_accept)
    TextView tvToAccept;
    @BindView(R.id.view_to_accept)
    View viewToAccept;
    @BindView(R.id.rl_to_accept)
    RelativeLayout rlToAccept;
    @BindView(R.id.tv_finding)
    TextView tvFinding;
    @BindView(R.id.view_finding)
    View viewFinding;
    @BindView(R.id.rl_finding)
    RelativeLayout rlFinding;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.view_result)
    View viewResult;
    @BindView(R.id.rl_result)
    RelativeLayout rlResult;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.view_cancel)
    View viewCancel;
    @BindView(R.id.rl_cancel)
    RelativeLayout rlCancel;
    @BindView(R.id.lv_free_search_goods)
    ListView lvFreeSearchGoods;
    private List<XTXPOrder.DataBean.ListBean> mList;
    private List<XTXPOrder.DataBean.ListBean> mDaichuliList;
    private List<XTXPOrder.DataBean.ListBean> mYichuliList;
    private FreeSearchGoodsAdapter mAdapter;
    private String tag = "0";
    private LoadingDailog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_search_goods);
        ButterKnife.bind(this);
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();

        initView();
        initListener();


    }

    /**
     * 初始化事件监听
     */
    private void initListener() {
        lvFreeSearchGoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FreeSearchGoodsActivity.this, FreeSearchGoodsDetailActivity.class);
                switch (tag){
                    case "0":
                        intent.putExtra("list",mList.get(position));
                        break;
                    case "1":
                        intent.putExtra("list",mDaichuliList.get(position));
                        break;
                    case "2":
                        intent.putExtra("list",mYichuliList.get(position));
                        break;
                }
                startActivity(intent);
            }
        });
    }

    private void initView() {

        tvTitlebarCenter.setText("System inquiry");
        mList = new ArrayList<>();
        mYichuliList = new ArrayList<>();
        mDaichuliList = new ArrayList<>();
        getData(tag);
        mAdapter = new FreeSearchGoodsAdapter(this, mList);
        lvFreeSearchGoods.setAdapter(mAdapter);

    }

    /**
     * 获取系统询盘的数据
     */
    private void getData(final String tag) {

        dialog.show();

        mList.clear();
        mDaichuliList.clear();
        mYichuliList.clear();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("goods_type", "enquiry");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.post().url(NetConfig.mygoods_url)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "----系统询盘的失败返回--error--->" + e.getMessage() + e.getCause());
                        Toast.makeText(FreeSearchGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----系统询盘的成功返回----->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            dialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    XTXPOrder sdcgAllOrder = gson.fromJson(response, XTXPOrder.class);
                                    List<XTXPOrder.DataBean.ListBean> list = sdcgAllOrder.getData().getList();
                                    mList.addAll(list);
                                    for (int i = 0; i < mList.size(); i++) {

                                        String goods_state = mList.get(i).getGoods_state();
                                        if (goods_state.equals("wait_audit")) {
                                            mDaichuliList.add(mList.get(i));
                                        }

                                        if (goods_state.equals("up")) {
                                            mYichuliList.add(mList.get(i));
                                        }
                                    }

                                    switch (tag) {
                                        case "0":
                                            mAdapter.setList(mList);
                                            mAdapter.notifyDataSetChanged();
                                            break;
                                        case "1":
                                            mAdapter.setList(mDaichuliList);
                                            mAdapter.notifyDataSetChanged();
                                            break;
                                        case "2":
                                            mAdapter.setList(mYichuliList);
                                            mAdapter.notifyDataSetChanged();
                                            break;
                                    }

                                } else {
                                    Toast.makeText(FreeSearchGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(FreeSearchGoodsActivity.this, "network error", Toast.LENGTH_SHORT).show();                        }

                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.rl_all, R.id.rl_to_accept, R.id.rl_finding, R.id.rl_result, R.id.rl_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.rl_all:
                tvAll.setTextColor(getResources().getColor(R.color.blue));
                viewAll.setBackgroundColor(getResources().getColor(R.color.blue));
                tvToAccept.setTextColor(getResources().getColor(R.color.a33));
                viewToAccept.setBackgroundColor(getResources().getColor(R.color.white));
                tvFinding.setTextColor(getResources().getColor(R.color.a33));
                viewFinding.setBackgroundColor(getResources().getColor(R.color.white));
                tvResult.setTextColor(getResources().getColor(R.color.a33));
                viewResult.setBackgroundColor(getResources().getColor(R.color.white));
                tvCancel.setTextColor(getResources().getColor(R.color.a33));
                viewCancel.setBackgroundColor(getResources().getColor(R.color.white));
                tag = "0";
                getData("0");
                break;
            case R.id.rl_to_accept:
                tvToAccept.setTextColor(getResources().getColor(R.color.blue));
                viewToAccept.setBackgroundColor(getResources().getColor(R.color.blue));
                tvAll.setTextColor(getResources().getColor(R.color.a33));
                viewAll.setBackgroundColor(getResources().getColor(R.color.white));
                tvFinding.setTextColor(getResources().getColor(R.color.a33));
                viewFinding.setBackgroundColor(getResources().getColor(R.color.white));
                tvResult.setTextColor(getResources().getColor(R.color.a33));
                viewResult.setBackgroundColor(getResources().getColor(R.color.white));
                tvCancel.setTextColor(getResources().getColor(R.color.a33));
                viewCancel.setBackgroundColor(getResources().getColor(R.color.white));
                tag = "1";
                getData("1");
                break;
            case R.id.rl_result:
                tvResult.setTextColor(getResources().getColor(R.color.blue));
                viewResult.setBackgroundColor(getResources().getColor(R.color.blue));
                tvToAccept.setTextColor(getResources().getColor(R.color.a33));
                viewToAccept.setBackgroundColor(getResources().getColor(R.color.white));
                tvAll.setTextColor(getResources().getColor(R.color.a33));
                viewAll.setBackgroundColor(getResources().getColor(R.color.white));
                tvCancel.setTextColor(getResources().getColor(R.color.a33));
                viewCancel.setBackgroundColor(getResources().getColor(R.color.white));
                tag = "2";
                getData("2");
                break;
        }
    }


}
