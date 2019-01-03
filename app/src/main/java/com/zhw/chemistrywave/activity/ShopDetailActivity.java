package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.FenLeiDetailgvAdapter;
import com.zhw.chemistrywave.adapters.PopLvAdapter;
import com.zhw.chemistrywave.adapters.PopLvErJiAdapter;
import com.zhw.chemistrywave.adapters.PopLvPurityAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.GoodsSortsBean;
import com.zhw.chemistrywave.bean.HotSaleProduct;
import com.zhw.chemistrywave.bean.ShopDetail;
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

public class ShopDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.et_shop_product_search)
    EditText etShopProductSearch;
    @BindView(R.id.iv_shop_logo)
    ImageView ivShopLogo;
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    @BindView(R.id.tv_shop_product_fenlei)
    TextView tvShopProductFenlei;
    @BindView(R.id.iv_shop_product_fenlei)
    ImageView ivShopProductFenlei;
    @BindView(R.id.tv_shop_product_chundu)
    TextView tvShopProductChundu;
    @BindView(R.id.iv_shop_product_chundu)
    ImageView ivShopProductChundu;
    @BindView(R.id.tv_shop_product_huoqi)
    TextView tvShopProductHuoqi;
    @BindView(R.id.iv_shop_product_huoqi)
    ImageView ivShopProductHuoqi;
    @BindView(R.id.tv_shop_product_jiage)
    TextView tvShopProductJiage;
    @BindView(R.id.iv_shop_product_price)
    ImageView ivShopProductPrice;
    @BindView(R.id.gv_shop_product)
    GridView gvShopProduct;
    @BindView(R.id.ll_shop_info)
    LinearLayout llShopInfo;
    @BindView(R.id.ll_production_info)
    LinearLayout llProductionInfo;
    @BindView(R.id.tv_shop_grade)
    TextView tvShopGrade;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_company_address)
    TextView tvCompanyAddress;
    @BindView(R.id.tv_company_phone)
    TextView tvCompanyPhone;
    @BindView(R.id.tv_company_phone_s)
    TextView tvCompanyPhoneS;
    @BindView(R.id.iv_business_license)
    ImageView ivBusinessLicense;
    @BindView(R.id.tv_business_license)
    TextView tvBusinessLicense;
    @BindView(R.id.iv_shop_environment)
    ImageView ivShopEnvironment;
    @BindView(R.id.tv_shop_environment)
    TextView tvShopEnvironment;
    @BindView(R.id.iv_production_environment)
    ImageView ivProductionEnvironment;
    @BindView(R.id.tv_production_environment)
    TextView tvProductionEnvironment;
    @BindView(R.id.ll_shop_detail)
    LinearLayout llShopDetail;
    @BindView(R.id.ll_company_environment)
    LinearLayout llCompanyEnvironment;
    @BindView(R.id.ll_business_license)
    LinearLayout llBusinessLicense;
    @BindView(R.id.ll_production_environment)
    LinearLayout llProductionEnvironment;
    @BindView(R.id.rl_blackback)
    RelativeLayout rlBlackback;
    @BindView(R.id.ll_shop_product)
    LinearLayout llFenlei;
    private FenLeiDetailgvAdapter mAdapter;
    private List<HotSaleProduct.DataBean.ListBean> mList = new ArrayList<>();
    private boolean isShowShopInfo = false;

    private String oneType,twoType,goods_purity,goods_deliver;
    private String sort = "asc";
    private GoodsSortsBean goodsSortsBean;
    private int tag = 0;
    private int currentTag = 0;

    private PopupWindow popWnd;
    private String user_id;
    private int currentPosition;
    private boolean isAsc;
    private String priceRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ButterKnife.bind(this);
        user_id = getIntent().getStringExtra("user_id");
        initView();
        initJsonDataFromService();//获取分类数据
        initListener();
        getData(user_id);
        getGoodsData(user_id,oneType,twoType,goods_purity,goods_deliver,sort);
    }

    private void initListener() {
        gvShopProduct.setOnItemClickListener(this);
    }

    private void getGoodsData(String user_id,String oneType,String twoType,String goods_deliver,String goods_purity,String sort) {
        mList.clear();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page","1");
            jsonObject.put("limit","1000");
            jsonObject.put("user_id", user_id);
            jsonObject.put("goods_state","up");
            jsonObject.put("one_type",oneType);
            jsonObject.put("goods_purity",goods_purity);
            jsonObject.put("goods_deliver",goods_deliver);
            jsonObject.put("two_type",twoType);
            jsonObject.put("sort",sort);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils
                .post()
                .url(NetConfig.mygoods_url)
                .addParams("jsonStr",jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(ShopDetailActivity.java:143)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(ShopDetailActivity.java:149)<---->"+response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                Gson gson = new Gson();
                                HotSaleProduct ziYingGoods = gson.fromJson(response, HotSaleProduct.class);
                                List<HotSaleProduct.DataBean.ListBean> list = ziYingGoods.getData().getList();
                                mList.addAll(list);
                                mAdapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(ShopDetailActivity.this, "失败", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void getData(String user_id) {
        OkHttpUtils.post()
                .url(NetConfig.SHOP_DETAIL)
                .addParams("user_id", user_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(ShopDetailActivity.java:76)<---->" + e.getMessage());
                        Toast.makeText(ShopDetailActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(ShopDetailActivity.java:83)<---->" + response);

                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(ShopDetailActivity.this, "Network error", Toast.LENGTH_SHORT).show();
                        } else {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    Gson gson = new Gson();
                                    ShopDetail shopDetail = gson.fromJson(response, ShopDetail.class);
                                    Glide.with(ShopDetailActivity.this).load(NetConfig.baseurl + shopDetail.getData().getCom_logo()).apply(MyApplication.options).into(ivShopLogo);
                                    tvShopName.setText(shopDetail.getData().getCom_name());
                                    tvCompanyName.setText(shopDetail.getData().getCom_name());
                                    tvCompanyAddress.setText(shopDetail.getData().getCom_addr());
                                    String rank = shopDetail.getData().getRank() == null ? "0" : shopDetail.getData().getRank();
                                    tvShopGrade.setText(rank+"grade");
                                    tvCompanyPhone.setText(shopDetail.getData().getCom_landline());//暂时用这个字段
                                    tvCompanyPhoneS.setText(shopDetail.getData().getCom_landline());//暂时用这个字段

                                    String com_license = shopDetail.getData().getCom_license()==null?"":shopDetail.getData().getCom_license();//
                                    if (TextUtils.isEmpty(com_license)){
                                        llBusinessLicense.setVisibility(View.GONE);
                                    }else {
                                        Glide.with(ShopDetailActivity.this).load(NetConfig.baseurl+com_license).apply(MyApplication.options).into(ivBusinessLicense);
                                        tvBusinessLicense.setText("Business license(1)");
                                    }

                                    String com_env = shopDetail.getData().getCom_env()==null?"":shopDetail.getData().getCom_env();

                                    if (TextUtils.isEmpty(com_env)){
                                        llCompanyEnvironment.setVisibility(View.GONE);
                                    }else {
                                        Glide.with(ShopDetailActivity.this).load(NetConfig.baseurl+com_env).apply(MyApplication.options).into(ivShopEnvironment);
                                        tvShopEnvironment.setText("Company environment(1)");
                                    }

                                    String com_proenv = shopDetail.getData().getCom_proenv()==null?"":shopDetail.getData().getCom_proenv();

                                    if (TextUtils.isEmpty(com_proenv)){
                                        llProductionEnvironment.setVisibility(View.GONE);
                                    }else {
                                        Glide.with(ShopDetailActivity.this).load(NetConfig.baseurl+com_proenv).apply(MyApplication.options).into(ivProductionEnvironment);
                                        tvProductionEnvironment.setText("Production environment(1)");
                                    }

                                }else {
                                    String msg = jsonObject.getString("msg");
                                    Toast.makeText(ShopDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initView() {
        mAdapter = new FenLeiDetailgvAdapter(this, mList);
        gvShopProduct.setAdapter(mAdapter);

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

    @OnClick({R.id.rl_shop_product_back, R.id.ll_shop_info, R.id.ll_shop_product_fenlei, R.id.ll_shop_product_chundu, R.id.ll_shop_product_huoqi, R.id.ll_shop_product_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_shop_product_back:
                if (isShowShopInfo){
                    isShowShopInfo = false;
                    llProductionInfo.setVisibility(View.VISIBLE);
                    llShopDetail.setVisibility(View.GONE);
                }else
                finish();
                break;
            case R.id.ll_shop_info:
                isShowShopInfo = true;
                llProductionInfo.setVisibility(View.GONE);
                llShopDetail.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_shop_product_fenlei:
                tag = 1;
                tvShopProductFenlei.setTextColor(getResources().getColor(R.color.a0a83db));
                ivShopProductFenlei.setImageResource(R.drawable.arrow_up_blue);
                tvShopProductChundu.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductChundu.setImageResource(R.drawable.sj_down_grey);
                tvShopProductHuoqi.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductHuoqi.setImageResource(R.drawable.sj_down_grey);
                tvShopProductJiage.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
                        if (tag!=currentTag) showPopUpWindow();
                    } else {
                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }
                currentTag = tag;
                break;
            case R.id.ll_shop_product_chundu:
                tag = 2;
                tvShopProductChundu.setTextColor(getResources().getColor(R.color.a0a83db));
                ivShopProductChundu.setImageResource(R.drawable.arrow_up_blue);
                tvShopProductFenlei.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductFenlei.setImageResource(R.drawable.sj_down_grey);
                tvShopProductHuoqi.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductHuoqi.setImageResource(R.drawable.sj_down_grey);
                tvShopProductJiage.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
                        if (tag!=currentTag) showPopUpWindow();
                    } else {
                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }
                currentTag = tag;
                break;
            case R.id.ll_shop_product_huoqi:
                tag = 3;
                tvShopProductHuoqi.setTextColor(getResources().getColor(R.color.a0a83db));
                ivShopProductHuoqi.setImageResource(R.drawable.arrow_up_blue);
                tvShopProductFenlei.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductFenlei.setImageResource(R.drawable.sj_down_grey);
                tvShopProductChundu.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductChundu.setImageResource(R.drawable.sj_down_grey);
                tvShopProductJiage.setTextColor(getResources().getColor(R.color.t333));
                ivShopProductPrice.setImageResource(R.drawable.sj_down_grey);
                if (popWnd != null) {
                    if (popWnd.isShowing()) {
                        popWnd.dismiss();
                        if (tag!=currentTag) showPopUpWindow();
                    } else {
                        showPopUpWindow();
                    }
                } else {
                    showPopUpWindow();
                }

                currentTag = tag;
                break;
            case R.id.ll_shop_product_price:
                tag = 4;
                if (isAsc){
                    sort = "desc";
                    isAsc = true;
                }else {
                    sort = "asc";
                    isAsc = false;
                }
                getGoodsData(user_id,oneType,twoType,goods_purity,goods_deliver,sort);
//                tvShopProductJiage.setTextColor(getResources().getColor(R.color.a0a83db));
//                ivShopProductPrice.setImageResource(R.drawable.arrow_up_blue);
//                tvShopProductFenlei.setTextColor(getResources().getColor(R.color.t333));
//                ivShopProductFenlei.setImageResource(R.drawable.sj_down_grey);
//                tvShopProductChundu.setTextColor(getResources().getColor(R.color.t333));
//                ivShopProductChundu.setImageResource(R.drawable.sj_down_grey);
//                tvShopProductHuoqi.setTextColor(getResources().getColor(R.color.t333));
//                ivShopProductHuoqi.setImageResource(R.drawable.sj_down_grey);
//                if (popWnd != null) {
//
//                    if (popWnd.isShowing()) {
//                        popWnd.dismiss();
//                        if (currentTag!=tag)
//                        showPopUpWindow();
//                    } else {
//                        showPopUpWindow();
//                    }
//                } else {
//                    showPopUpWindow();
//                }
//                currentTag = tag;
                break;
        }
    }


    private void showPopUpWindow() {

        View contentView = LayoutInflater.from(ShopDetailActivity.this).inflate(R.layout.item_fenlei, null);
        popWnd = new PopupWindow(ShopDetailActivity.this);
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
                        getGoodsData(user_id,oneType,twoType,goods_deliver,goods_purity,sort);
                        break;
                    case 2:
                        getGoodsData(user_id,oneType,twoType,goods_deliver,goods_purity,sort);
                        break;
                    case 3:
                        getGoodsData(user_id,oneType,twoType,goods_deliver,goods_purity,sort);
                        break;
                    case 4:
                        getGoodsData(user_id,oneType,twoType,goods_deliver,goods_purity,sort);
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
                final PopLvAdapter adapter = new PopLvAdapter(ShopDetailActivity.this, goodsSortsBean.getData());
                lvLeft.setAdapter(adapter);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        oneType = goodsSortsBean.getData().get(position).getText();
                        currentPosition = position;
                        final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(ShopDetailActivity.this, goodsSortsBean.getData().get(position).getList());
                        lvRight.setAdapter(adapter1);
                        lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                twoType = goodsSortsBean.getData().get(currentPosition).getList().get(position).getText();
                                adapter1.setCurrentPosition(position);
                                adapter1.notifyDataSetChanged();
                            }
                        });
                        adapter.setCurrentPosition(currentPosition);
                        adapter.notifyDataSetChanged();
                    }
                });

                final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(ShopDetailActivity.this, goodsSortsBean.getData().get(0).getList());
                lvRight.setAdapter(adapter1);
                lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        oneType = goodsSortsBean.getData().get(0).getText();
                        twoType = goodsSortsBean.getData().get(0).getList().get(position).getText();
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

                final PopLvPurityAdapter adapter2 = new PopLvPurityAdapter(ShopDetailActivity.this, mList2);
                lvLeft.setAdapter(adapter2);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        adapter2.setCurrentPosition(position);
                        goods_purity = mList2.get(position);
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
                final PopLvPurityAdapter adapter3 = new PopLvPurityAdapter(ShopDetailActivity.this, mList3);
                lvLeft.setAdapter(adapter3);
                lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        goods_deliver = mList3.get(position);
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
        popWnd.setOnDismissListener(new ShopDetailActivity.poponDismissListener());

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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        startActivity(new Intent(this,GoodsDetailActivity.class).putExtra("mer_id",mList.get(i).getGoods_id()));
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
            tvShopProductFenlei.setTextColor(getResources().getColor(R.color.t333));
            ivShopProductFenlei.setImageResource(R.drawable.sj_down_grey);
            tvShopProductChundu.setTextColor(getResources().getColor(R.color.t333));
            ivShopProductChundu.setImageResource(R.drawable.sj_down_grey);
            tvShopProductHuoqi.setTextColor(getResources().getColor(R.color.t333));
            ivShopProductHuoqi.setImageResource(R.drawable.sj_down_grey);
            tvShopProductJiage.setTextColor(getResources().getColor(R.color.t333));
            ivShopProductPrice.setImageResource(R.drawable.sj_down_grey);
        }

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
}
