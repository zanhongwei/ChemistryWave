package com.zhw.chemistrywave.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.ShopCarAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Shop;
import com.zhw.chemistrywave.bean.ShopCar;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.ToastUtil;
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
import butterknife.OnClick;
import okhttp3.Call;

public class ShopCarActivity extends BaseActivity implements ShopCarAdapter.CheckInterface, ShopCarAdapter.ModifyCountInterface {


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
    @BindView(R.id.lv_list)
    ExpandableListView lvList;
    @BindView(R.id.cb_all)
    CheckBox cbAll;
    @BindView(R.id.tv_delect)
    TextView tvDelect;
    @BindView(R.id.ll_delete)
    LinearLayout llDelete;
    @BindView(R.id.tv_heji)
    TextView tvHeji;
    @BindView(R.id.tv_jiesuan)
    TextView tvJiesuan;
    @BindView(R.id.ll_jiesuan)
    LinearLayout llJiesuan;
    private List<ShopCar.DataBean.ListBean> groups = new ArrayList<ShopCar.DataBean.ListBean>();// 组元素数据列表
    private Map<String, List<ShopCar.DataBean.ListBean.CartBeansBean>> children = new HashMap<String, List<ShopCar.DataBean.ListBean.CartBeansBean>>();// 子元素数据列表
    private List<ShopCar.DataBean.ListBean.CartBeansBean> carBeans = new ArrayList<>();
    //    private List<ShopCar.DataBean.ListBean.OrderBeansBean> children = new ArrayList<>();// 子元素数据列表
    private ShopCarAdapter mAdapter;
    private double totalPrice;
    private ArrayList<String> orderIdList;
    private String flag = "1";//此时处于完成状态  显示编辑
    private List<ShopCar.DataBean.ListBean.CartBeansBean> orderBean;
    private List<Shop> listOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        tvTitlebarCenter.setText("Shop cart");
        tvTitlebarRight.setText("Edit");
        tvTitlebarRight.setVisibility(View.VISIBLE);
        getData();

    }


    private void getData() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("page", "1");
            jsonObject.put("limit", "100000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(NetConfig.shopcar_url)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--查询我的购物车返回-error-->" + e.toString());
                        ToastUtil.showToastShort(ShopCarActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--查询我的购物车返回--->" + response);
//                        carBeans.clear();
//                        groups.clear();
//                        children.clear();
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    ShopCar shopCar = gson.fromJson(response, ShopCar.class);
                                    List<ShopCar.DataBean.ListBean> list = shopCar.getData().getList();
                                    groups.addAll(list);
                                    for (int i = 0; i < groups.size(); i++) {
                                        children.put(groups.get(i).getShop_id() + "", groups.get(i).getCartBeans());
                                    }
                                    mAdapter = new ShopCarAdapter(groups, children, ShopCarActivity.this);
                                    lvList.setAdapter(mAdapter);
                                    initEvents();
                                    mAdapter.notifyDataSetChanged();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void initEvents() {
        mAdapter.setCheckInterface(this);// 关键步骤1,设置复选框接口
        mAdapter.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口

        for (int i = 0; i < mAdapter.getGroupCount(); i++) {
            lvList.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }

    }

    @OnClick({R.id.rl_titlebar_back, R.id.cb_all, R.id.tv_delect, R.id.tv_heji, R.id.tv_jiesuan, R.id.tv_titlebar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_titlebar_right:
                //完成、编辑点击的事件
                if (flag.equals("1")) {
                    tvTitlebarRight.setText("Complete");
                    llJiesuan.setVisibility(View.GONE);
                    llDelete.setVisibility(View.VISIBLE);
                    mAdapter.switchMode();
                    flag = "2";
                } else {
                    tvTitlebarRight.setText("Edit");
                    llJiesuan.setVisibility(View.VISIBLE);
                    llDelete.setVisibility(View.GONE);
                    mAdapter.switchNormalMode();
                    flag = "1";
                }
                break;
            case R.id.cb_all:
                doCheckAll();
                break;
            case R.id.tv_delect:
                doDelete();
                break;
            case R.id.tv_heji:
                break;
            case R.id.tv_jiesuan:
                orderBean = new ArrayList<>();
                listOrder = new ArrayList<>();
                for (int i = 0; i < groups.size(); i++) {
                    ShopCar.DataBean.ListBean group = groups.get(i);
                    List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id() + "");
                    List<ShopCar.DataBean.ListBean.CartBeansBean> orderBeanBean = new ArrayList<>();
                    for (int j = 0; j < childs.size(); j++) {
                        if (childs.get(j).isChecked()) {
                            Log.e("aaa", "(ShopCarActivity.java:199)<---->" + "aaaa");
                            orderBeanBean.add(childs.get(j));
                        }
                    }
                    if (orderBeanBean.size() > 0) {
                        Shop shop = new Shop(group.getShop_id() + "", orderBeanBean);
                        listOrder.add(shop);
                    }

//                    orderBean.put(shop, orderBeanBean);
                }

                for (int i = 0; i < listOrder.size(); i++) {
                    Shop shop = listOrder.get(i);
                    Log.e("aaa", "(ShopCarActivity.java:211)<---->" + "------------" + shop.getShopName());
                }
                String order = new Gson().toJson(listOrder);
                Intent intent = new Intent(ShopCarActivity.this, VertifyOrderActivity.class);
                intent.putExtra("order", order);
                intent.putExtra("type", "ShopCarActivity");
                startActivity(intent);
                break;
        }
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(cbAll.isChecked());
            ShopCar.DataBean.ListBean group = groups.get(i);
            List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id() + "");
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChecked(cbAll.isChecked());
            }
        }
        mAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        orderIdList = new ArrayList<>();
        List<ShopCar.DataBean.ListBean> toBeDeleteGroups = new ArrayList<>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            ShopCar.DataBean.ListBean group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<ShopCar.DataBean.ListBean.CartBeansBean> toBeDeleteProducts = new ArrayList<>();// 待删除的子元素列表
            List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChecked()) {
                    toBeDeleteProducts.add(childs.get(j));
//                    String order_id = childs.get(j).getOrder_id();
//                    orderIdList.add(order_id);
                }
            }
            childs.removeAll(toBeDeleteProducts);

        }
        groups.removeAll(toBeDeleteGroups);

        String orderIdS = "";
        for (int i = 0; i < orderIdList.size(); i++) {
            if (i == 0) {
                orderIdS = orderIdList.get(i);
            } else {
                orderIdS = orderIdS + "," + orderIdList.get(i);
            }
        }

        mAdapter.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalPrice = 0.00;
        for (int i = 0; i < groups.size(); i++) {
            ShopCar.DataBean.ListBean group = groups.get(i);
            List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id() + "");
            for (int j = 0; j < childs.size(); j++) {
                ShopCar.DataBean.ListBean.CartBeansBean product = childs.get(j);
                if (product.isChecked()) {
                    String money = "";
                    if (product.getGoods_price().contains("/")) {
                        money = product.getGoods_price().split("/")[0];
                    } else {
                        money = product.getGoods_price();
                    }
                    totalPrice += (Double.parseDouble(money) * Double.parseDouble(product.getGoods_num() + ""));
                }
            }
        }
        tvHeji.setText("Total: $" + totalPrice);
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        ShopCar.DataBean.ListBean group = groups.get(groupPosition);
        List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id() + "");
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChecked(isChecked);
        }
        if (isAllCheck())
            cbAll.setChecked(true);
        else
            cbAll.setChecked(false);
        mAdapter.notifyDataSetChanged();
        calculate();
    }

    //是否全选
    private boolean isAllCheck() {
        for (ShopCar.DataBean.ListBean group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    @Override
    public void checkChild(int groupPosition, int childPosition, boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        ShopCar.DataBean.ListBean group = groups.get(groupPosition);
        List<ShopCar.DataBean.ListBean.CartBeansBean> childs = children.get(group.getShop_id() + "");
        for (int i = 0; i < childs.size(); i++) {
            if (childs.get(i).isChecked() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck())
            cbAll.setChecked(true);
        else
            cbAll.setChecked(false);
        mAdapter.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ShopCar.DataBean.ListBean.CartBeansBean product = (ShopCar.DataBean.ListBean.CartBeansBean) mAdapter.getChild(groupPosition, childPosition);
        int currentCount = Integer.parseInt(product.getGoods_num() + "");
        int cart_id = product.getCart_id();
        int goods_id = product.getGoods_id();
        currentCount++;
        product.setGoods_num(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
//        mAdapter.notifyDataSetChanged();
        calculate();
        updateGoodsNum(currentCount, cart_id, goods_id);
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked) {
        ShopCar.DataBean.ListBean.CartBeansBean product = (ShopCar.DataBean.ListBean.CartBeansBean) mAdapter.getChild(groupPosition, childPosition);
        int currentCount = Integer.parseInt(product.getGoods_num() + "");
        int cart_id = product.getCart_id();
        int goods_id = product.getGoods_id();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setGoods_num(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
//        mAdapter.notifyDataSetChanged();
        calculate();
        updateGoodsNum(currentCount, cart_id, goods_id);
    }

    //更新购物车商品数量
    private void updateGoodsNum(int currentCount, int cart_id, int goods_id) {

        HashMap<String, String> params = new HashMap<>();
        params.put("cart_id", String.valueOf(cart_id));
        params.put("goods_id", String.valueOf(goods_id));
        params.put("goods_num", String.valueOf(currentCount));

        OkHttpUtils.post()
                .url(NetConfig.cart_update)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "(ShopCarActivity.java:392)<---->" + "网络错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "(ShopCarActivity.java:397)<---->" + response);

                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(ShopCarActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    mAdapter.notifyDataSetChanged();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

    }
}
