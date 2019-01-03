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
import com.zhw.chemistrywave.adapters.UserMsgLvLvAdapter;
import com.zhw.chemistrywave.bean.NotificationMsg;
import com.zhw.chemistrywave.bean.User;
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
public class UserMsgFragment extends Fragment {


    private PullToRefreshListView mLvUserMsg;
    private List<NotificationMsg.DataBean.ListBean> mList;
    private UserMsgLvLvAdapter mAdapter;
    private User user;
    private int pageNo = 1;

    public UserMsgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_msg, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        mLvUserMsg.setMode(PullToRefreshBase.Mode.BOTH);
        mLvUserMsg.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (mList != null && mList.size() > 0) mList.clear();
                pageNo = 1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo++;
                getData();
            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        mList = new ArrayList<>();
        mAdapter = new UserMsgLvLvAdapter(getActivity(), mList);
        getData();
        mLvUserMsg.setAdapter(mAdapter);
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mLvUserMsg = (PullToRefreshListView) view.findViewById(R.id.lv_usermsgf);
    }

    /**
     * 加载网络数据
     */
    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("user_id", MyUtils.getUser().getUser_id());
        map.put("page", String.valueOf(pageNo));
        map.put("limit", "10");
        OkHttpUtils.post().url(NetConfig.notificationmsg)
                .addParams("jsonStr", MyUtils.getJson(map))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---得到通知消息---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---得到通知消息---->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int result = jo1.getInt("code");
                                String msg = jo1.getString("msg");
                                if (result == 0) {
                                    Gson gson = new Gson();
                                    NotificationMsg sySMsg = gson.fromJson(response, NotificationMsg.class);
                                    if (sySMsg.getData().getTotalPage() == sySMsg.getData().getCurrPage()) {

                                        mLvUserMsg.getLoadingLayoutProxy(false, true)
                                                .setPullLabel("Havn't more...");
                                        mLvUserMsg.getLoadingLayoutProxy(false, true)
                                                .setRefreshingLabel("Havn't more...");
                                        mLvUserMsg.getLoadingLayoutProxy(false, true)
                                                .setReleaseLabel("Havn't more...");
                                        mLvUserMsg.onRefreshComplete();
                                    }
                                    List<NotificationMsg.DataBean.ListBean> list = sySMsg.getData().getList();
                                    mList.addAll(list);
                                    mAdapter.notifyDataSetChanged();
                                    mLvUserMsg.onRefreshComplete();
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
