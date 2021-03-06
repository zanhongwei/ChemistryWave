package com.zhw.chemistrywave.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.OrderDetailActivity;
import com.zhw.chemistrywave.activity.TianxiewuliuxinxiActivity;
import com.zhw.chemistrywave.adapters.GonghuoOrderfLvAdapter;
import com.zhw.chemistrywave.bean.Order;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class GonghuoOrderFragment extends Fragment {


    private static final int DELIVERY_CODE = 1;
    @BindView(R.id.tab_gonghuoorderf)
    TabLayout tabGonghuoorderf;
    @BindView(R.id.lv_gonghuoorderf)
    ListView lvGonghuoorderf;
    Unbinder unbinder;
    private List<Order.DataBean.ListBean> mList = new ArrayList<>();
    private GonghuoOrderfLvAdapter mAdapter;
    private List<String> listTitle;  //tab名称列表
    private String state = "";

    public GonghuoOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (TextUtils.isEmpty(state)) {
            getData();
        } else {
            getData(state);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gonghuo_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        mAdapter = new GonghuoOrderfLvAdapter(getActivity(), mList);
        lvGonghuoorderf.setAdapter(mAdapter);
        initData();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        setTabLayout();
        tabGonghuoorderf.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        state = "";
                        getData();
                        break;
                    case 1:
                        state = "2";
                        getData(state);
                        break;
                    case 2:
                        state = "3";
                        getData(state);
                        break;
                    case 3:
                        state = "4";
                        getData(state);
                        break;
                    case 4:
                        state = "5";
                        getData(state);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mList = new ArrayList<>();
        getData();
        mAdapter = new GonghuoOrderfLvAdapter(getActivity(), mList);
        lvGonghuoorderf.setAdapter(mAdapter);
        lvGonghuoorderf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods", mList.get(position));
                startActivity(new Intent(getActivity(), OrderDetailActivity.class).putExtras(bundle));
            }
        });
        mAdapter.setListener(new GonghuoOrderfLvAdapter.AdapterClick() {
            @Override
            public void deliveryGoods(int position) {
                Intent intent = new Intent(getActivity(), TianxiewuliuxinxiActivity.class);
                intent.putExtra("order_id", mList.get(position).getOrder_id());
                startActivityForResult(intent, DELIVERY_CODE);
            }
        });
    }

    private void setTabLayout() {
        listTitle = new ArrayList<>();
        //初始化各fragment
        String[] title = getResources().getStringArray(R.array.ordertitlegonghuo);
        //将fragment装进列表中
        for (int i = 0; i < title.length; i++) {
            listTitle.add(title[i]);
        }
        //设置TabLayout的模式

        tabGonghuoorderf.setTabMode(TabLayout.MODE_SCROLLABLE);
        //为TabLayout添加tab名称
        Log.d("aaa", listTitle.toString());
        for (int i = 0; i < listTitle.size(); i++) {
            tabGonghuoorderf.addTab(tabGonghuoorderf.newTab().setText(listTitle.get(i)));//添加tab选项卡
        }


    }

    /**
     * 获取网络数据
     *
     * @param
     */
    private void getData() {
        mList.clear();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("limit", "10000");
        map.put("user_id", MyUtils.getUser().getUser_id());
//        map.put("order_state",state);
        OkHttpUtils.post().url(NetConfig.ghorder_list)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---查询订单列表返回----error-->" + e.getMessage() + e.getCause());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---查询订单列表返回------>" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Order order = gson.fromJson(response, Order.class);
                                    List<Order.DataBean.ListBean> listBeen = order.getData().getList();
                                    mList.addAll(listBeen);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {

                        }
                    }
                });
    }

    /**
     * 获取网络数据
     *
     * @param
     */
    private void getData(String state) {
        mList.clear();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("limit", "10000");
        map.put("user_id", MyUtils.getUser().getUser_id());
        map.put("status", state);
        OkHttpUtils.post().url(NetConfig.ghorder_list)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---查询订单列表返回----error-->" + e.getMessage() + e.getCause());
                        ToastUtil.showToastShort(getActivity(), "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---查询订单列表返回------>" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Order order = gson.fromJson(response, Order.class);
                                    List<Order.DataBean.ListBean> listBeen = order.getData().getList();
                                    mList.addAll(listBeen);
                                    mAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {

                        }
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loginSuccess(String obj) {
        String msg = "onEventMainThread收到了消息：" + obj;
        Log.e("aaa", "---onEventMainThread收到了消息----->" + obj);
        if ("背景".equals(obj)) {
            backgroundAlpha(0.6f);
        } else if ("背景1".equals(obj)) {
            backgroundAlpha(1f);
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

}
