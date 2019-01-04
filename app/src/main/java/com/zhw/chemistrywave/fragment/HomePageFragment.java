package com.zhw.chemistrywave.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.CWServiceActivity;
import com.zhw.chemistrywave.activity.GoodsDetailActivity;
import com.zhw.chemistrywave.activity.KuaiXunActivity;
import com.zhw.chemistrywave.activity.MessageActivity;
import com.zhw.chemistrywave.activity.SearchActivity;
import com.zhw.chemistrywave.activity.ShopDetailActivity;
import com.zhw.chemistrywave.activity.WebServiceActivity;
import com.zhw.chemistrywave.adapters.HomeJingJiaAdapter;
import com.zhw.chemistrywave.adapters.HomeXhgyGvAdapter;
import com.zhw.chemistrywave.adapters.HotSupplierAdapter;
import com.zhw.chemistrywave.adapters.PriceExpressAdapter;
import com.zhw.chemistrywave.bean.HotSaleProduct;
import com.zhw.chemistrywave.bean.HotSuppliers;
import com.zhw.chemistrywave.bean.JingJiaBean;
import com.zhw.chemistrywave.bean.Marquee;
import com.zhw.chemistrywave.bean.PriceExpressBean;
import com.zhw.chemistrywave.bean.SdcgAllOrder;
import com.zhw.chemistrywave.bean.TradeNews;
import com.zhw.chemistrywave.bean.ZiYingGoods;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.TimeTaskScroll;
import com.zhw.chemistrywave.view.MarqueeView;
import com.zhw.chemistrywave.view.MyListViews;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import static com.zhy.http.okhttp.OkHttpUtils.post;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements AdapterView.OnItemClickListener {

    Unbinder unbinder;
    @BindView(R.id.homecb)
    ConvenientBanner homecb;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.iv_home_news)
    ImageView ivHomeNews;
    @BindView(R.id.iv_isnews)
    ImageView ivIsnews;
    @BindView(R.id.mqv)
    MarqueeView mqv;
    @BindView(R.id.lv_express_price)
    ListView lvExpressPrice;
    @BindView(R.id.lv_hot_sale)
    MyListViews lvHotSale;
    @BindView(R.id.lv_supplier)
    MyListViews lvSupplier;
    private List<HotSaleProduct.DataBean.ListBean> mList = new ArrayList<>();
    private List<PriceExpressBean.DataBean.ListBean> mPriceExpress = new ArrayList<>();//价格拓展的集合
    private HomeXhgyGvAdapter xhgyAdapter;//hot-sale adapter
    private List<SdcgAllOrder.DataBean.ListBean> listSdjj;//Mail是我自己定义的一个对象，如果使用，可以换成String或者自己的对象
    private PriceExpressAdapter mPriceAdapter;
    private List<String> bannerImgs = new ArrayList<>();//声明一个banner图像集合
    private List<String> hotSupplierUserIdList = new ArrayList<>();
    private ArrayList<Marquee> marquees;
    //声明一个热门供销商的集合
    private List<HotSuppliers.DataBean> mHotSupplierList = new ArrayList<>();
    private HotSupplierAdapter mHotSupplier;
    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        homecb = (ConvenientBanner) view.findViewById(R.id.homecb);
        initView();
        initData();
        initListener();
        return view;
    }

    private void initView() {

        // list的初始化；
        mPriceAdapter = new PriceExpressAdapter(mPriceExpress, getActivity());
        xhgyAdapter = new HomeXhgyGvAdapter(getActivity(), mList);
        lvHotSale.setAdapter(xhgyAdapter);
        mHotSupplier = new HotSupplierAdapter(getActivity(), mHotSupplierList);
        lvSupplier.setAdapter(mHotSupplier);
        setMarqueeView();

        lvHotSale.setOnItemClickListener(this);
        lvSupplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String user_id = mHotSupplierList.get(i).getUser_id() + "";
                startActivity(new Intent(getActivity(), ShopDetailActivity.class).putExtra("user_id", user_id));
            }
        });


    }

    private void initData() {

        //get advertised banner img
        getBannerImg();
        //get price-express list
        getPriceExpress();
        //get hot-sale list
        getHotSale();
        //get hot-suppliers list
        getHotSuppliers();
        //get trade news
        getTradeNews();

        listSdjj = new ArrayList<>();
    }

    /**
     * 初始化事件监听
     */
    private void initListener() {
        mqv.setOnClick(new MarqueeView.OnClick() {
            @Override
            public void onClick() {
                startActivity(new Intent(getActivity(), KuaiXunActivity.class));
            }
        });

    }

    //垂直跑马
    private void setMarqueeView() {
        List<Marquee> marquees = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0 && i != 6 - 1) {
                Marquee marquee = new Marquee();
                marquee.setFirstimgUrl("News");
                marquee.setFirsttitle("" + i);
                marquee.setImgUrl("HarLan" + (i + 1));
                marquee.setTitle(" " + (i + 1));
                marquees.add(marquee);
            }
        }
        mqv.startWithList(marquees);
    }

    /**
     * banner images
     */
    private void getBannerImg() {

        bannerImgs.clear();

        post()
                .url(NetConfig.ADVERTISED_BANNER)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:246)<--轮播图 result error-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:252)<--轮播图 result success-->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    JSONArray data = jsonObject.getJSONArray("data");
                                    for (int i = 0; i < data.length(); i++) {
                                        String banner_path = data.getJSONObject(i).getString("banner_path");
                                        bannerImgs.add(NetConfig.baseurl + banner_path);
                                    }
                                } else {
                                    Toast.makeText(getActivity(), "NO Result!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        setConvenientBanner(bannerImgs);

                    }
                });
    }

    private void getPriceExpress() {
        mPriceExpress.clear();
        JSONObject js = new JSONObject();
        try {
            js.put("page", "1");
            js.put("limit", "100");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("jsonStr", js.toString());

        OkHttpUtils
                .post()
                .url(NetConfig.PRICE_EXPRESS)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:258)<--price express result error-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:264)<--price express result success-->" + response);

                        PriceExpressBean priceExpressBean = new Gson().fromJson(response, PriceExpressBean.class);
                        mPriceExpress.addAll(priceExpressBean.getData().getList());
                        new Timer().schedule(new TimeTaskScroll(getActivity(), lvExpressPrice, mPriceExpress), 20, 20);
                        mPriceAdapter.notifyDataSetChanged();

                    }
                });
    }

    private void getHotSale() {

        mList.clear();

        JSONObject js = new JSONObject();
        try {
            js.put("goods_type", "hot");
            js.put("page", "1");
            js.put("limit", "1000");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("jsonStr", js.toString());

        Log.e("aaa",
                "(HomePageFragment.java:338)<--hot-sale params-->" + params);

        OkHttpUtils
                .post()
                .url(NetConfig.HOT_SALE)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:261)<--hot-sale result error-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:267)<--hot-sale result success-->" + response);

                        HotSaleProduct ziYingGoods = new Gson().fromJson(response, HotSaleProduct.class);
                        mList.addAll(ziYingGoods.getData().getList());

                        Log.e("aaa",
                                "(HomePageFragment.java:364)<--mlist.size() === -->" + mList.size());
                        xhgyAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void getHotSuppliers() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("limit", "3");
            jsonObject.put("page", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("aaa",
                "(HomePageFragment.java:280)<--获取热门供应商的参数-->" + jsonObject.toString());

        hotSupplierUserIdList.clear();
        mHotSupplierList.clear();
        OkHttpUtils
                .post()
                .url(NetConfig.HOT_SUPPLIER)
                .addParams("jsonStr", jsonObject.toString())
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("aaa",
                        "(HomePageFragment.java:245)<--hot-suppliers result error-->" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                Log.e("aaa",
                        "(HomePageFragment.java:251)<--hot suppliers result success-->" + response);

                if (TextUtils.isEmpty(response)) {

                } else {
                    try {
                        JSONObject jo = new JSONObject(response);
                        int code = jo.getInt("code");
                        if (code == 0) {
                            HotSuppliers hotSuppliers = new Gson().fromJson(response, HotSuppliers.class);
                            mHotSupplierList.addAll(hotSuppliers.getData());
                            mHotSupplier.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }

    private void getTradeNews() {

        marquees = new ArrayList<>();

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("news_type", "2");
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.get()
                .url(NetConfig.TRADE_NEWS)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:236)<--行业资讯的失败返回-->" + e.getMessage());
                        Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:242)<--行业资讯的成功返回-->" + response);

                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject1 = new JSONObject(response);
                                int code = jsonObject1.getInt("code");
                                if (code == 0) {
                                    TradeNews tradeNews = new Gson().fromJson(response, TradeNews.class);
                                    for (int i = 0; i < tradeNews.getData().getList().size(); i++) {
                                        if (i % 2 == 0 && i != tradeNews.getData().getList().size() - 1) {
                                            Marquee marquee = new Marquee();
                                            marquee.setFirstimgUrl("News");
                                            marquee.setFirsttitle(tradeNews.getData().getList().get(i).getNews_title());
                                            marquee.setImgUrl("News");
                                            marquee.setTitle(tradeNews.getData().getList().get(i + 1).getNews_title());
                                            marquees.add(marquee);
                                        }
                                    }
                                    mqv.startWithList(marquees);
                                } else {
                                    String msg = jsonObject1.getString("msg");
                                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

    //轮播图
    private void setConvenientBanner(List<String> strs) {

        homecb.setPages(
                new CBViewHolderCreator<NetWorkImageHolderView>() {
                    @Override
                    public NetWorkImageHolderView createHolder() {
                        return new NetWorkImageHolderView();
                    }
                }, strs)

                .setPageIndicator(new int[]{R.drawable.conven_point_grey, R.drawable.conven_point_blue})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * advertising information
     */
    private void getAdvertising() {

        OkHttpUtils
                .post()
                .url(NetConfig.ADVERYISING_INFO)
                .addParams("display", "1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:245)<--首页的广告失败返回-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(HomePageFragment.java:251)<--首页的广告成功返回-->" + response);
                    }
                });

    }

    @OnClick({R.id.et_search, R.id.iv_search, R.id.iv_home_news, R.id.ll_sdjj, R.id.ll_sdcg, R.id.ll_xhgy,
            R.id.ll_zkzq, R.id.ll_mfzh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
            case R.id.et_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.iv_home_news:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            //投诉纠纷
            case R.id.ll_sdjj:
//                startActivity(new Intent(getActivity(), CWServiceActivity.class).putExtra("name", "Complaint And Dispute"));
                break;
            //化浪之眼
            case R.id.ll_sdcg:
                if (MyUtils.getUser() == null || MyUtils.getUser().getUser_id().isEmpty()) {
                    Toast.makeText(getActivity(), "Please login first", Toast.LENGTH_SHORT).show();
                } else
                    startActivity(new Intent(getActivity(), WebServiceActivity.class)
                            .putExtra("name","Harlan Eye ")
                            .putExtra("url","harlan_eye.html?user_id=" + MyUtils.getUser().getUser_id()));
                break;
            //价格速递
            case R.id.ll_xhgy:
                startActivity(new Intent(getActivity(), WebServiceActivity.class)
                        .putExtra("name","Price Express")
                        .putExtra("url","price.html"));
                break;
            //平台监测
            case R.id.ll_zkzq:
                startActivity(new Intent(getActivity(), CWServiceActivity.class)
                        .putExtra("url","platform.html")
                        .putExtra("name", "Platform Guarantee"));
                break;
            //供应链金融
            case R.id.ll_mfzh:
                startActivity(new Intent(getActivity(), CWServiceActivity.class).putExtra("name", "Supply chain finance"));
                break;

//            case R.id.rl_hot_supplier3:
//                String userId3 = hotSupplierUserIdList.get(2);
//                startActivity(new Intent(getActivity(), ShopDetailActivity.class).putExtra("user_id", userId3));
//                break;
//            case R.id.rl_hot_supplier1:
//                String userId1 = hotSupplierUserIdList.get(0);
//                startActivity(new Intent(getActivity(), ShopDetailActivity.class).putExtra("user_id", userId1));
//                break;
//            case R.id.rl_hot_supplier2:
//                String userId2 = hotSupplierUserIdList.get(1);
//                startActivity(new Intent(getActivity(), ShopDetailActivity.class).putExtra("user_id", userId2));
//                break;

        }
    }

    class NetWorkImageHolderView implements Holder<String> {
        private ImageView imageView;

        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
//            imageView.setImageResource(data);
            if (data != null) {
                Glide.with(getActivity()).load(data).into(imageView);
//                 ImageLoader.getInstance().displayImage(data, imageView, options);
            } else {
                imageView.setImageResource(R.drawable.ban1);
            }
        }
    }

    class viewPagerAdapter extends FragmentStatePagerAdapter {

        public viewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("aaa", "--listSdjj.get(position).toString()-->" + listSdjj.get(position).toString());
            return new sdjjFragment().newInstance(listSdjj.get(position), "jjj");

        }

        @Override
        public int getCount() {
            return listSdjj == null ? 0 : listSdjj.size();
        }
    }

    class PageChangeL implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            curPos=position;
//            sdjjFragment f=(sdjjFragment) sdjjAdapter
//                    .instantiateItem(vpHomeSdjj, position);;
//            final int currentSelectedPosition = vpHomeSdjj.getCurrentItem();
//            View view = vpHomeSdjj.getChildAt(currentSelectedPosition);
//            int height = view.getMeasuredHeight();
//            LayoutParams layoutParams = (LinearLayout.LayoutParams) vpHomeSdjj.getLayoutParams();
//            layoutParams.height = height;
//            vpHomeSdjj.setLayoutParams(layoutParams);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        startActivity(new Intent(getActivity(), GoodsDetailActivity.class).putExtra("mer_id", mList.get(i).getGoods_id()));
    }


}
