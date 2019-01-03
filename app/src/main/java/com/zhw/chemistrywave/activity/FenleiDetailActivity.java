package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.FenLeiDetailgvAdapter;
import com.zhw.chemistrywave.adapters.PopLvAdapter;
import com.zhw.chemistrywave.adapters.PopLvErJiAdapter;
import com.zhw.chemistrywave.adapters.PopLvPurityAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.FenLeiJsonBean;
import com.zhw.chemistrywave.bean.GoodsSortsBean;
import com.zhw.chemistrywave.bean.HotSaleProduct;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class FenleiDetailActivity extends BaseActivity {


    @BindView(R.id.rl_fenleidetail_back)
    RelativeLayout rlFenleidetailBack;
    @BindView(R.id.et_fenleidetail_search)
    EditText etFenleidetailSearch;
    @BindView(R.id.tv_fenleidetail_fenlei)
    TextView tvFenleidetailFenlei;
    @BindView(R.id.iv_fenleidetail_fenlei)
    ImageView ivFenleidetailFenlei;
    @BindView(R.id.ll_fenleidetail_fenlei)
    LinearLayout llFenleidetailFenlei;
    @BindView(R.id.tv_fenleidetail_chundu)
    TextView tvFenleidetailChundu;
    @BindView(R.id.iv_fenleidetail_chundu)
    ImageView ivFenleidetailChundu;
    @BindView(R.id.ll_fenleidetail_chundu)
    LinearLayout llFenleidetailChundu;
    @BindView(R.id.tv_fenleidetail_huoqi)
    TextView tvFenleidetailHuoqi;
    @BindView(R.id.iv_fenleidetail_huoqi)
    ImageView ivFenleidetailHuoqi;
    @BindView(R.id.ll_fenleidetail_huoqi)
    LinearLayout llFenleidetailHuoqi;
    @BindView(R.id.tv_fenleidetail_jiage)
    TextView tvFenleidetailJiage;
    @BindView(R.id.iv_fenleidetail_price)
    ImageView ivFenleidetailPrice;
    @BindView(R.id.ll_fenleidetail_price)
    LinearLayout llFenleidetailPrice;
    @BindView(R.id.ll_fenlei)
    LinearLayout llFenlei;
    @BindView(R.id.gv_fenleidetail)
    GridView gvFenleidetail;
    @BindView(R.id.rl_blackback)
    RelativeLayout rlBlackback;
    boolean isAsc = false;
    private List<HotSaleProduct.DataBean.ListBean> mList;
    private FenLeiDetailgvAdapter mAdapter;
    private PopupWindow popWnd;
    private int tag = 0;
    private String onetype;
    private String twotype;
    private ArrayList<FenLeiJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private String chundu = "20%";
    private String huoqi = "10days";
    private String name = "";
    private String priceRank = "asc";
    private GoodsSortsBean goodsSortsBean;
    private int currentPosition = 0;//记录一级分类的点击位置
    private LoadingDailog dialog;


    public static void main(String[] args) {
//        Log.e("aaa", "(FenleiDetailActivity.java:112)<---->" + "执行main方法");
        System.out.println("执行main方法");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_fenlei_detail);
        ButterKnife.bind(this);
        onetype = getIntent().getStringExtra("onetype");
        twotype = getIntent().getStringExtra("twotype");
        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("Loading...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();
        Log.e("aaa", "--onetype-twotype->" + onetype + "   twotype -=== " + twotype);
        initJsonDataFromService();
        initData();
        initListener();

    }

    //获取网上分类数据
    private void initJsonDataFromService() {

        OkHttpUtils.get().url(NetConfig.SORT_GOODS).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddGoodsBaseInfoActivity.java:222)<---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddGoodsBaseInfoActivity.java:228)<---->" + response);
                        if (TextUtils.isEmpty(response)) {

                        } else {
                            goodsSortsBean = new Gson().fromJson(response, GoodsSortsBean.class);
                        }
                    }
                });
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new FenLeiDetailgvAdapter(FenleiDetailActivity.this, mList);
        gvFenleidetail.setAdapter(mAdapter);
        getData();

    }

    private void initListener() {
        gvFenleidetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(FenleiDetailActivity.this, GoodsDetailActivity.class).putExtra("mer_id", mList.get(position).getGoods_id()));
            }
        });
        etFenleidetailSearch.setImeOptions(IME_ACTION_SEARCH);
        etFenleidetailSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == IME_ACTION_SEARCH) {
                    name = etFenleidetailSearch.getText().toString().trim();
                    if (mList != null) {
                        mList.clear();
                    }
                    getData();
                    return true;
                }
                return false;
            }
        });
    }

    private void getData() {
        mList.clear();

        dialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("goods_state", "up");
            jsonObject.put("goods_type", "harlan");
            jsonObject.put("one_type", onetype);
            jsonObject.put("two_type", twotype);
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.post().url(NetConfig.ziyingshangcheng_url)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "------商品数据返回--error->" + e.getMessage() + e.getCause());
                        dialog.dismiss();
                        Toast.makeText(FenleiDetailActivity.this, "数据获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "------商品数据返回--success->" + response);
                        dialog.dismiss();
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    HotSaleProduct ziYingGoods = gson.fromJson(response, HotSaleProduct.class);
                                    List<HotSaleProduct.DataBean.ListBean> list = ziYingGoods.getData().getList();
                                    mList.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(FenleiDetailActivity.this, "数据获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_fenleidetail_back, R.id.ll_fenleidetail_fenlei, R.id.ll_fenleidetail_chundu, R.id.ll_fenleidetail_huoqi, R.id.ll_fenleidetail_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_fenleidetail_back:
                onBackPressed();
                break;
            case R.id.ll_fenleidetail_fenlei:
                tag = 1;
                tvFenleidetailFenlei.setTextColor(getResources().getColor(R.color.a0a83db));
                ivFenleidetailFenlei.setImageResource(R.drawable.arrow_up_blue);
                tvFenleidetailChundu.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailChundu.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailHuoqi.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailHuoqi.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailJiage.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
//                        showPopUpWindow();
                    } else {
                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }
                break;
            case R.id.ll_fenleidetail_chundu:
                tag = 2;
                tvFenleidetailChundu.setTextColor(getResources().getColor(R.color.a0a83db));
                ivFenleidetailChundu.setImageResource(R.drawable.arrow_up_blue);
                tvFenleidetailFenlei.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailFenlei.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailHuoqi.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailHuoqi.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailJiage.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
                        showPopUpWindow();
                    } else {
                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }
                break;
            case R.id.ll_fenleidetail_huoqi:
                tag = 3;
                tvFenleidetailHuoqi.setTextColor(getResources().getColor(R.color.a0a83db));
                ivFenleidetailHuoqi.setImageResource(R.drawable.arrow_up_blue);
                tvFenleidetailChundu.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailChundu.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailFenlei.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailFenlei.setImageResource(R.drawable.sj_down_grey);
                tvFenleidetailJiage.setTextColor(getResources().getColor(R.color.t333));
                ivFenleidetailPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
                        showPopUpWindow();
                    } else {

                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }
                break;
            case R.id.ll_fenleidetail_price:
                tag = 4;
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
//                        showPopUpWindow();
                    }
                }
                if (isAsc) {
                    priceRank = "desc";
                } else {
                    priceRank = "asc";
                }
                getPurityData();
                break;
        }
    }

    private void showPopUpWindow() {

        View contentView = LayoutInflater.from(FenleiDetailActivity.this).inflate(R.layout.item_fenlei, null);
        popWnd = new PopupWindow(FenleiDetailActivity.this);
        popWnd.setContentView(contentView);
        popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View contentView1 = popWnd.getContentView();
        contentView1.findViewById(R.id.tv_fenlei_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWnd.dismiss();
                if (mList != null) {
                    mList.clear();
                }
                switch (tag) {
                    case 1:
                        getData();
                        break;
                    case 2:
                        getPurityData();
                        break;
                    case 3:
                        getPurityData();
                        break;
                    case 4:
                        getPurityData();
                        break;
                }
            }
        });

        /**
         * 重置操作暂时没用
         */
        contentView1.findViewById(R.id.tv_fenlei_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWnd.dismiss();
//                onetype = "";
//                twotype = "";
//                chundu = "";
//                huoqi = "";
//                if (mList != null) {
//                    mList.clear();
//                }
//                getData();
            }
        });
        ListView lvLeft = ((ListView) contentView1.findViewById(R.id.lv_fenlei1));
        final ListView lvRight = ((ListView) contentView1.findViewById(R.id.lv_fenlei2));
        switch (tag) {
            case 1:
                final PopLvAdapter adapter = new PopLvAdapter(FenleiDetailActivity.this, goodsSortsBean.getData());
                lvLeft.setAdapter(adapter);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onetype = goodsSortsBean.getData().get(position).getText();
                        currentPosition = position;
                        final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(FenleiDetailActivity.this, goodsSortsBean.getData().get(position).getList());
                        lvRight.setAdapter(adapter1);
                        lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                twotype = goodsSortsBean.getData().get(currentPosition).getList().get(position).getText();
                                adapter1.setCurrentPosition(position);
                                adapter1.notifyDataSetChanged();
                            }
                        });
                        adapter.setCurrentPosition(currentPosition);
                        adapter.notifyDataSetChanged();
                    }
                });

                final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(FenleiDetailActivity.this, goodsSortsBean.getData().get(0).getList());
                lvRight.setAdapter(adapter1);
                lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        onetype = goodsSortsBean.getData().get(0).getText();
                        twotype = goodsSortsBean.getData().get(0).getList().get(position).getText();
                        adapter1.setCurrentPosition(position);
                        adapter1.notifyDataSetChanged();
                    }
                });

                break;
            case 2:
                lvRight.setVisibility(View.GONE);
                final List<String> mList2 = new ArrayList<>();

                for (int i = 20; i < 100; i += 10) {
                    mList2.add(i + "%");
                }

                final PopLvPurityAdapter adapter2 = new PopLvPurityAdapter(FenleiDetailActivity.this, mList2);
                lvLeft.setAdapter(adapter2);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        adapter2.setCurrentPosition(position);
                        chundu = mList2.get(position);
                        adapter2.notifyDataSetChanged();
                    }
                });
                break;
            case 3:

                lvRight.setVisibility(View.GONE);
                final List<String> mList3 = new ArrayList<>();

                for (int i = 10; i < 70; i += 10) {
                    mList3.add(i + "days");
                }
                final PopLvPurityAdapter adapter3 = new PopLvPurityAdapter(FenleiDetailActivity.this, mList3);
                lvLeft.setAdapter(adapter3);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        huoqi = mList3.get(position);
                        adapter3.setCurrentPosition(position);
                        adapter3.notifyDataSetChanged();
                    }
                });

                break;
            case 4:
                break;
        }

        popWnd.setTouchable(true);
        popWnd.setFocusable(false);
        popWnd.setOutsideTouchable(false);
        popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
//        backgroundAlpha(0.6f);
        rlBlackback.setVisibility(View.VISIBLE);
        //添加pop窗口关闭事件
        popWnd.setOnDismissListener(new poponDismissListener());

        popWnd.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    popWnd.dismiss();
                    return true;
                }
                return false;
            }
        });

        popWnd.showAsDropDown(llFenlei, 0, 0);
//        popWnd.showAtLocation(VertifyOrderActivity.this.findViewById(R.id.tv_pay),
//                Gravity.BOTTOM, 0, 0);

    }

    /**
     * 获取纯度的筛选结果
     */
    private void getPurityData() {
        mList.clear();
        dialog.show();
        /**
         * page
         * limit
         * user_id
         * goods_state
         * one_type
         * goods_purity
         * goods_deliver
         * two_type
         * sort
         */


        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("goods_state", "up");
            jsonObject.put("one_type", onetype);
            jsonObject.put("goods_purity", chundu);
            jsonObject.put("goods_deliver", huoqi);
            jsonObject.put("two_type", twotype);
            jsonObject.put("sort", priceRank);//价格的排序
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.post()
                .url(NetConfig.HOT_SALE)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(FenleiDetailActivity.java:546)<---->" + e.getMessage());
                        dialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(FenleiDetailActivity.java:552)<---->" + response);
                        dialog.dismiss();
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(FenleiDetailActivity.this, "数据获取失败，请检查网络", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    HotSaleProduct ziYingGoods = gson.fromJson(response, HotSaleProduct.class);
                                    List<HotSaleProduct.DataBean.ListBean> list = ziYingGoods.getData().getList();
                                    mList.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public ArrayList<FenLeiJsonBean> parseData(String result) {//Gson 解析
        ArrayList<FenLeiJsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                FenLeiJsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), FenLeiJsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    /**
     * 添加弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
//            backgroundAlpha(1f);
            rlBlackback.setVisibility(View.GONE);
            tvFenleidetailFenlei.setTextColor(getResources().getColor(R.color.t333));
            ivFenleidetailFenlei.setImageResource(R.drawable.sj_down_grey);
            tvFenleidetailChundu.setTextColor(getResources().getColor(R.color.t333));
            ivFenleidetailChundu.setImageResource(R.drawable.sj_down_grey);
            tvFenleidetailHuoqi.setTextColor(getResources().getColor(R.color.t333));
            ivFenleidetailHuoqi.setImageResource(R.drawable.sj_down_grey);
            tvFenleidetailJiage.setTextColor(getResources().getColor(R.color.t333));
            ivFenleidetailPrice.setImageResource(R.drawable.sj_down_grey);
        }

    }
}
