package com.zhw.chemistrywave.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.MyAddress;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.view.MyListViews;
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

public class MyAddressActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.lv_address_list)
    MyListViews lvAddressList;

    private List<MyAddress.DataBean.ListBean> mList = new ArrayList<>();
    private MyAdapter mAdapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        ButterKnife.bind(this);

        initView();

        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initData() {
        mList.clear();
        getAddressList();
        mAdapter.notifyDataSetChanged();
    }

    private void getAddressList() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("user_id", MyUtils.getUser().getUser_id());
            jsonObject.put("page", "1");
            jsonObject.put("limit", "1000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpUtils.post()
                .url(NetConfig.addresslist)
                .addParams("jsonStr",jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:79)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:86)<---->"+response);
                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                        }else {
                            MyAddress myAddress = new Gson().fromJson(response, MyAddress.class);
                            mList.addAll(myAddress.getData().getList());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {
        context = this;
        tvTitlebarCenter.setText("Address");
        mAdapter = new MyAdapter();
        lvAddressList.setAdapter(mAdapter);
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.ll_add_address:
                startActivityForResult(new Intent(this,ChangeAddressActivity.class),1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        initData();
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;
            if (view == null) {
                view = View.inflate(context, R.layout.item_address_details_lv, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            }else {
                holder = (ViewHolder) view.getTag();
            }

            holder.tvName.setText(mList.get(i).getConsignee());
            holder.tvPhone.setText(mList.get(i).getMobile());
            holder.tvAddressDetail.setText(mList.get(i).getDetail());
            if (mList.get(i).isIs_default()){
                holder.ivMoren.setImageResource(R.drawable.address_moren_selected);
            }else {
                holder.ivMoren.setImageResource(R.drawable.address_moren_unselected);
            }

            holder.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delect(i);
                }
            });

            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delect(i);
                }
            });

            holder.tvEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editAddress(i);
                }
            });

            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editAddress(i);
                }
            });

            holder.tvMoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDefault(i);
                }
            });

            holder.ivMoren.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDefault(i);
                }
            });

            return view;
        }

         class ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_phone)
            TextView tvPhone;
            @BindView(R.id.tv_address_detail)
            TextView tvAddressDetail;
            @BindView(R.id.iv_moren)
            ImageView ivMoren;
            @BindView(R.id.tv_moren)
            TextView tvMoren;
            @BindView(R.id.iv_edit)
            ImageView ivEdit;
            @BindView(R.id.tv_edit)
            TextView tvEdit;
            @BindView(R.id.iv_delete)
            ImageView ivDelete;
            @BindView(R.id.tv_delete)
            TextView tvDelete;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    private void setDefault(int position) {
        MyAddress.DataBean.ListBean listBean = mList.get(position);
        int addr_id = listBean.getAddr_id();


        OkHttpUtils.post()
                .url(NetConfig.changeaddress)
                .addParams("addr_id",addr_id+"")
                .addParams("user_id",MyUtils.getUser().getUser_id())
                .addParams("is_default","1")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:252)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:258)<---->"+response);

                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(context, "网络错误", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    initData();
                                }
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void editAddress(int position) {
        MyAddress.DataBean.ListBean listBean = mList.get(position);
        int addr_id = listBean.getAddr_id();
        String consignee = listBean.getConsignee();//联系人
        String mobile = listBean.getMobile();//联系人电话
        String detail = listBean.getDetail();//收货人地址
        boolean is_default = listBean.isIs_default();

        Bundle bundle = new Bundle();
        bundle.putString("addr_id",addr_id+"");
        bundle.putString("name",consignee);
        bundle.putString("mobile",mobile);
        bundle.putString("detail",detail);
        bundle.putBoolean("is_default",is_default);
        startActivity(new Intent(context,ChangeAddressActivity.class).putExtras(bundle));
    }

    private void delect(final int position) {

        MyAddress.DataBean.ListBean listBean = mList.get(position);
        int addr_id = listBean.getAddr_id();

        OkHttpUtils.post()
                .url(NetConfig.deleteaddress)
                .addParams("addr_id",addr_id+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:251)<---->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyAddressActivity.java:257)<---->"+response);
                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(context, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject js = new JSONObject(response);
                                int code = js.getInt("code");
                                if (code==0){
                                    mList.remove(position);
                                    Toast.makeText(context, "Delect successfully", Toast.LENGTH_SHORT).show();
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
