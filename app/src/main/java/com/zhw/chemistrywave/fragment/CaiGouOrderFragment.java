package com.zhw.chemistrywave.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.OrderDetailActivity;
import com.zhw.chemistrywave.adapters.OrderfLvAdapter;
import com.zhw.chemistrywave.bean.Order;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhw.chemistrywave.view.MySelfDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
public class CaiGouOrderFragment extends Fragment {


    @BindView(R.id.tab_caigouorderf)
    TabLayout tabCaigouorderf;
    @BindView(R.id.lv_caigouorderf)
    ListView lvCaigouorderf;
    Unbinder unbinder;
    private List<Order.DataBean.ListBean> mList = new ArrayList<>();
    private OrderfLvAdapter mAdapter;
    private List<String> listTitle;  //tab名称列表

    public CaiGouOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cai_gou_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();

        return view;
    }

    private void initView() {
        mAdapter = new OrderfLvAdapter(getActivity(), mList);
        lvCaigouorderf.setAdapter(mAdapter);
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        setTabLayout();
        tabCaigouorderf.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getData();//全部
                        break;
                    case 1:
                        getData("1");//Obligation
                        break;
                    case 2:
                        getData("2");//待发货
                        break;
                    case 3:
                        getData("3");//待收货
                        break;
                    case 4:
                        getData("4");//已完成
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
        lvCaigouorderf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("goods", mList.get(position));
                startActivity(new Intent(getActivity(), OrderDetailActivity.class).putExtras(bundle));
            }
        });
        mList = new ArrayList<>();
        mAdapter = new OrderfLvAdapter(getActivity(), mList);
        getData();
        lvCaigouorderf.setAdapter(mAdapter);
        mAdapter.setQvXiaoClick(new OrderfLvAdapter.qvXiaoClick() {
            @Override
            public void onclick(int position) {
                showUnLoginDialog(position);
            }
        });
    }

    private void setTabLayout() {
        listTitle = new ArrayList<>();
        //初始化各fragment
        String[] title = getResources().getStringArray(R.array.ordertitle);
        //将fragment装进列表中
        for (int i = 0; i < title.length; i++) {
            listTitle.add(title[i]);
        }
        //设置TabLayout的模式

        tabCaigouorderf.setTabMode(TabLayout.MODE_SCROLLABLE);
        //为TabLayout添加tab名称
        Log.d("aaa", listTitle.toString());
        for (int i = 0; i < listTitle.size(); i++) {
            tabCaigouorderf.addTab(tabCaigouorderf.newTab().setText(listTitle.get(i)));//添加tab选项卡
        }


    }

    /**
     * 获取网络数据
     *
     * @param
     */
    private void getData() {
        mList.clear();
        mAdapter.notifyDataSetChanged();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("limit", "10000");
        map.put("buyer_id", MyUtils.getUser().getUser_id());
//        map.put("order_state",state);
        Log.e("aaa", "----orderlist----->" + NetConfig.order_list + "?page=1&limit=10000");
        Log.e("aaa", "----orderlist----->" + MyUtils.getJson(map).toString());
        OkHttpUtils.post().url(NetConfig.order_list)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---查询订单列表返回----error-->" + e.getMessage() + e.getCause());
                        Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(getActivity(), "network error", Toast.LENGTH_SHORT).show();
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
        mAdapter.notifyDataSetChanged();
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("limit", "10000");
        map.put("buyer_id", MyUtils.getUser().getUser_id());
        map.put("status", state);
        Log.e("aaa", "----orderlist----->" + NetConfig.order_list + "?page=1&limit=10000");
        Log.e("aaa", "----orderlist----->" + MyUtils.getJson(map).toString());
        OkHttpUtils.post().url(NetConfig.order_list)
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
                                    ToastUtil.showToastShort(getActivity(), "Network error");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            ToastUtil.showToastShort(getActivity(), "Network error");
                        }

                    }
                });
    }

    private void showUnLoginDialog(final int position) {
        final MySelfDialog selfDialog = new MySelfDialog(getActivity());

        selfDialog.setMessage("Whether to cancel the order or not?");
        selfDialog.setYesOnclickListener("Comfirm", new MySelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                cancelOrder(position);
                selfDialog.dismiss();
            }
        });

        selfDialog.setNoOnclickListener("Cancel", new MySelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {

                selfDialog.dismiss();
            }
        });
        backgroundAlpha(0.6f);
        selfDialog.setOnDismissListener(new myPoponDismissListener());
        selfDialog.show();
    }

    /**
     * 取消订单
     */
    private void cancelOrder(final int position) {

        String order_id = mList.get(position).getOrder_id();

        OkHttpUtils.post()
                .url(NetConfig.Cancel_order)
                .addParams("order_id", order_id)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(CaiGouOrderFragment.java:305)<--取消订单失败返回-->" + e.getMessage());
                        ToastUtil.showToastShort(getActivity(), "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(CaiGouOrderFragment.java:311)<--取消订单的成功返回-->" + response);
                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(getActivity(), "Network error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    mList.remove(position);
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
     * /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showPopUpWindow(TextView lpsm) {
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.pop_qvxiaodingdan, null);
        final PopupWindow popWnd = new PopupWindow(getActivity());
        popWnd.setContentView(contentView);
//    popWnd.setWidth(263);
//    popWnd.setHeight(320);
        popWnd.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        View contentView1 = popWnd.getContentView();


        popWnd.setTouchable(true);
        popWnd.setFocusable(true);
        popWnd.setOutsideTouchable(true);
        popWnd.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        backgroundAlpha(0.6f);

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

        //popWnd.showAsDropDown(mTvLine, 200, 0);
        popWnd.showAtLocation(lpsm, Gravity.CENTER, 0, 0);

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
            backgroundAlpha(1f);
        }

    }

    /**
     * 添加弹出的dialog关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class myPoponDismissListener implements Dialog.OnDismissListener {


        @Override
        public void onDismiss(DialogInterface dialog) {
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }
    }
}
