package com.zhw.chemistrywave.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.FenleiDetailActivity;
import com.zhw.chemistrywave.view.AutoGridView;
import com.zhw.chemistrywave.adapters.ClassifyfGvAdapter;
import com.zhw.chemistrywave.adapters.ClassifyfGvShangjiaAdapter;
import com.zhw.chemistrywave.bean.FenLeiJsonBean;
import com.zhw.chemistrywave.bean.GetJsonDataUtil;
import com.zhw.chemistrywave.bean.GoodsSortsBean;
import com.zhw.chemistrywave.bean.ShangJia;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;

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
public class ClassifyFragment extends Fragment {


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
    @BindView(R.id.lv_classifyf)
    ListView lvClassifyf;
    @BindView(R.id.iv_classifyf_logo)
    ImageView ivClassifyfLogo;
    @BindView(R.id.tv_classifyf_fenlei)
    TextView tvClassifyfFenlei;
    @BindView(R.id.gv_classifyf_jianxingranliao)
    AutoGridView gvClassifyfJianxingranliao;
    @BindView(R.id.gv_classifyf_tuijainshangjia)
    GridView gvClassifyfTuijainshangjia;
    Unbinder unbinder;
    private List<GoodsSortsBean.DataBean> mList = new ArrayList<>();
    private List<GoodsSortsBean.DataBean.ListBean> mGvList = new ArrayList<>();
    private List<ShangJia> mGvShangJiaList;
    private ClassifyfLvAdapter mAdapter;
    private ClassifyfGvAdapter mGvAdapter;
    private ClassifyfGvShangjiaAdapter mShangJiaGvAdapter;
    private int currentItemPosition = 0;
    private Map<String, List<String>> map = new HashMap<>();
    private List<String> erjifenlei = new ArrayList<>();
    private ArrayList<FenLeiJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private GoodsSortsBean goodsSortsBean;

    public ClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classify, container, false);
        unbinder = ButterKnife.bind(this, view);
//        initJsonData();
//        Log.e("aaa", "=========?" + options2Items.toString());

        initView();
        initJsonDataFromService();//获取分类数据


        return view;
    }

    private void initView() {
        tvTitlebarCenter.setText("Classify");
        rlTitlebarBack.setVisibility(View.GONE);

        mAdapter = new ClassifyfLvAdapter(getActivity(), mList);
        lvClassifyf.setAdapter(mAdapter);
        mGvAdapter = new ClassifyfGvAdapter(getActivity(), mGvList);
        gvClassifyfJianxingranliao.setAdapter(mGvAdapter);
        lvClassifyf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentItemPosition = position;
                mGvList.clear();
                tvClassifyfFenlei.setText(goodsSortsBean.getData().get(position).getText());
                mGvList.addAll(goodsSortsBean.getData().get(position).getList());
                mGvAdapter.notifyDataSetChanged();
                mAdapter.notifyDataSetChanged();
            }
        });

        gvClassifyfJianxingranliao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), FenleiDetailActivity.class);
                    intent.putExtra("onetype", tvClassifyfFenlei.getText().toString().trim());
                    intent.putExtra("twotype", mGvList.get(position).getText());
                    startActivity(intent);
            }
        });

    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(getActivity(), "feilei.json");//获取assets目录下的json文件数据

        ArrayList<FenLeiJsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> erjiList = new ArrayList<>();//该省的城市列表（第二级）
//            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getErji().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getErji().get(c);
                erjiList.add(CityName);//添加城市
            }

            /**
             * 添加城市数据
             */
            options2Items.add(erjiList);


        }

//        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    /**
     * 获取物品分类的数据
     */
    private void initJsonDataFromService() {

        mList.clear();
        mGvList.clear();
        Log.e("aaa",
                "(ClassifyFragment.java:176)<--货物分类的接口-->" + NetConfig.SORT_GOODS);
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
                            tvClassifyfFenlei.setText(goodsSortsBean.getData().get(0).getText());
                            mList.addAll(goodsSortsBean.getData());
                            mGvList.addAll(goodsSortsBean.getData().get(0).getList());
                            mAdapter.getItem(currentItemPosition);
                            mAdapter.notifyDataSetChanged();
                            mGvAdapter.notifyDataSetChanged();
                        }
                    }
                });
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class ClassifyfLvAdapter extends BaseAdapter {
        private Context mContext;
        private List<GoodsSortsBean.DataBean> mList;

        public ClassifyfLvAdapter(Context mContext, List<GoodsSortsBean.DataBean> mList) {
            this.mContext = mContext;
            this.mList = mList;
        }

        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            if (mList != null && mList.size() > 0) {
                return mList.get(position);
            }
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_classyfyf, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
                AutoUtils.autoSize(convertView);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position == currentItemPosition) {
                holder.llClassifyfitemBj.setBackgroundColor(getResources().getColor(R.color.f0f4f7));
                holder.tvClassifyfitemName.setTextColor(getResources().getColor(R.color.a02a9f7));
            } else {
                holder.llClassifyfitemBj.setBackgroundColor(getResources().getColor(R.color.white));
                holder.tvClassifyfitemName.setTextColor(getResources().getColor(R.color.a313233));
            }
            holder.tvClassifyfitemName.setText(mList.get(position).getText());
            return convertView;
        }


        class ViewHolder {
            @BindView(R.id.tv_classifyfitem_name)
            TextView tvClassifyfitemName;
            @BindView(R.id.ll_classifyfitem_bj)
            LinearLayout llClassifyfitemBj;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
