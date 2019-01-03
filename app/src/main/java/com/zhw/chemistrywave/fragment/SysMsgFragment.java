package com.zhw.chemistrywave.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.SysMsgLvAdapter;
import com.zhw.chemistrywave.bean.SySMsg;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * A simple {@link Fragment} subclass.
 */
public class SysMsgFragment extends Fragment {


    private PullToRefreshListView mLvSys;
    private List<SySMsg.DataBean.ListBean> mList;
    private SysMsgLvAdapter mAdapter;

    public SysMsgFragment() {
        // Required empty public constructor
    }

    private int pageNum = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sys_msg, container, false);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        mLvSys.setMode(PullToRefreshBase.Mode.BOTH);
        mLvSys.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mList != null && mList.size() > 0) mList.clear();
                pageNum = 1;
                getData();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum++;
                getData();
            }
        });
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mLvSys = ((PullToRefreshListView) view.findViewById(R.id.lv_sysmsg));
        mList = new ArrayList<>();
        mAdapter = new SysMsgLvAdapter(getActivity(), mList);
        getData();
        mLvSys.setAdapter(mAdapter);
    }

    /**
     * 加载网络数据
     */
    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(pageNum));
        map.put("limit", "10");
        String msg_publisher = MyUtils.getUser().getUser_id();
        map.put("msg_publisher", msg_publisher);
//        Gson gson=new Gson();
//       String para= gson.toJson(map);
        OkHttpUtils.post().url(NetConfig.sysmsg)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---得到系统消息--error-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---得到系统消息---->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int result = jo1.getInt("code");
                                String msg = jo1.getString("msg");
                                if (result == 0) {
                                    Gson gson = new Gson();
                                    SySMsg sySMsg = gson.fromJson(response, SySMsg.class);
                                    if (sySMsg.getData().getCurrPage() == sySMsg.getData().getTotalPage()) {
                                        mLvSys.getLoadingLayoutProxy(false, true)
                                                .setPullLabel("没有更多了...");
                                        mLvSys.getLoadingLayoutProxy(false, true)
                                                .setRefreshingLabel("没有更多了...");
                                        mLvSys.getLoadingLayoutProxy(false, true)
                                                .setReleaseLabel("没有更多了...");
                                        mLvSys.onRefreshComplete();
                                    }
                                    List<SySMsg.DataBean.ListBean> list = sySMsg.getData().getList();
                                    mList.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                    mLvSys.onRefreshComplete();
                                } else {
                                    Toast.makeText(getActivity(), "" + msg, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
    }

}
