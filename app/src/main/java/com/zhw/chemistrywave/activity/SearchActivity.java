package com.zhw.chemistrywave.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.SearchResultGlvAdapter;
import com.zhw.chemistrywave.adapters.SearchResultSlvAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.NewBaoJia;
import com.zhw.chemistrywave.bean.SearchGoods;
import com.zhw.chemistrywave.bean.SearchSupplier;
import com.zhw.chemistrywave.utils.ListDataSave;
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

public class SearchActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private final String SG_TAG = "searchGoods";
    private final String SS_TAG = "searchSupplier";
    @BindView(R.id.iv_search_back)
    ImageView ivSearchBack;
    @BindView(R.id.et_search_cintent)
    EditText etSearchCintent;
    @BindView(R.id.iv_search_search)
    ImageView ivSearchSearch;
    @BindView(R.id.tv_search_shidanjingjia)
    TextView tvSearchShidanjingjia;
    @BindView(R.id.tv_search_shidanjingjialine)
    TextView tvSearchShidanjingjialine;
    @BindView(R.id.rl_search_shidanjingjia)
    RelativeLayout rlSearchShidanjingjia;
    @BindView(R.id.tv_search_shidancaigou)
    TextView tvSearchShidancaigou;
    @BindView(R.id.tv_search_shidancaigouline)
    TextView tvSearchShidancaigouline;
    @BindView(R.id.rl_search_shidancaigou)
    RelativeLayout rlSearchShidancaigou;
    @BindView(R.id.lv_search_lishijilu)
    ListView lvSearchLishijilu;
    @BindView(R.id.tv_search_clearhistory)
    TextView tvSearchClearhistory;
    @BindView(R.id.lv_search_result)
    PullToRefreshListView lvSearchResult;
    @BindView(R.id.ll_search_result)
    LinearLayout llSearchResult;
    @BindView(R.id.ll_history)
    LinearLayout llHistory;
    private SearchResultSlvAdapter mSupplierAdapter;
    private SearchResultGlvAdapter mGoodsAdapter;
    private List<SearchGoods.DataBean.ListBean> mSearchGoodsList = new ArrayList<>();
    private List<SearchSupplier.DataBean> mSearchSupplierList = new ArrayList<>();
    private ListDataSave dataSaveGoods;
    private ListDataSave dataSaveSupplier;
    private boolean isGoods = false;
    private List<String> searchGoodsList = new ArrayList<>();
    private List<String> searchSuppList = new ArrayList<>();

    private boolean isNeedAdd = false;
    private ArrayAdapter<String> arrayGoodsAdapter;
    private ArrayAdapter<String> arraySupplierAdapter;
    private int pageNo = 1;

    private String searchGoods = "";//要搜索的商品
    private String searchSupplier = "";//要搜索的供应商

    /**
     * 隐藏软键盘
     *
     * @param :上下文环境，一般为Activity实例
     * @param view                 :一般为EditText
     */
    public static void hideKeyboard(View view) {
        InputMethodManager manager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        dataSaveGoods = new ListDataSave(this, "HisGoods");
        dataSaveSupplier = new ListDataSave(this, "HisSupp");

        Log.e("aaa", "(SearchActivity.java:99)<---->" + "保存本地保存的商品-->" + dataSaveGoods.getDataList(SG_TAG).size());
        Log.e("aaa", "(SearchActivity.java:100)<---->" + "保存本地的供应商-->" + dataSaveSupplier.getDataList(SS_TAG).size());

        if (dataSaveGoods.getDataList(SG_TAG) != null) {
            List<String> searchsGoods = dataSaveGoods.getDataList(SG_TAG);
            Log.e("aaa",
                    "(SearchActivity.java:101)<--搜索商品的集合大小-->" + searchsGoods.size());
            this.searchGoodsList.addAll(searchsGoods);
        }

        if (dataSaveSupplier.getDataList(SS_TAG) != null) {
            List<String> searchSuppers = dataSaveSupplier.getDataList(SS_TAG);
            searchSuppList.addAll(searchSuppers);
        }
        initView();
        initData();

        initListener();

        lvSearchLishijilu.setOnItemClickListener(this);
    }

    private void initListener() {

        lvSearchResult.setMode(PullToRefreshBase.Mode.BOTH);
        lvSearchResult.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo = 1;
                if (isGoods) {
                    if (mSearchGoodsList != null && mSearchGoodsList.size() > 0)
                        mSearchGoodsList.clear();
                    searchGoods();
                } else {
                    if (mSearchSupplierList != null && mSearchSupplierList.size() > 0)
                        mSearchSupplierList.clear();
                    searchSupplier();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNo++;
                if (isGoods)
                    searchGoods();
                else searchSupplier();
            }
        });


    }

    private void initView() {
        mSupplierAdapter = new SearchResultSlvAdapter(this, mSearchSupplierList);
        mGoodsAdapter = new SearchResultGlvAdapter(this, mSearchGoodsList);
        lvSearchResult.setAdapter(mSupplierAdapter);

        etSearchCintent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (isGoods) {
                        searchGoods = etSearchCintent.getText().toString().trim();
                        searchGoods();
                    } else {
                        searchSupplier = etSearchCintent.getText().toString().trim();
                        searchSupplier();
                    }
                    hideKeyboard(etSearchCintent);
                    return true;
                }
                return false;
            }
        });

        lvSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isGoods) {
                    startActivity(new Intent(SearchActivity.this, GoodsDetailActivity.class)
                            .putExtra("mer_id", mSearchGoodsList.get(i).getGoods_id()));
                } else {
                    String userId = mSearchSupplierList.get(i).getUser_id() + "  ";
                    startActivity(new Intent(SearchActivity.this, ShopDetailActivity.class).putExtra("user_id", userId));
                }
            }
        });
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        arrayGoodsAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.item_lv_lishijilu);
        for (int i = 0; i < searchGoodsList.size(); i++) {
            arrayGoodsAdapter.add(searchGoodsList.get(i));
        }
        arraySupplierAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.item_lv_lishijilu);
        for (int i = 0; i < searchSuppList.size(); i++) {
            arraySupplierAdapter.add(searchSuppList.get(i));
        }

        lvSearchLishijilu.setAdapter(arraySupplierAdapter);
    }

    @OnClick({R.id.iv_search_back, R.id.iv_search_search, R.id.rl_search_shidanjingjia, R.id.rl_search_shidancaigou, R.id.tv_search_clearhistory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_search_back:
                onBackPressed();
                break;
            case R.id.iv_search_search:
                if (isGoods) {
                    searchGoods = etSearchCintent.getText().toString().trim();
                    searchGoods();
                } else {
                    searchSupplier = etSearchCintent.getText().toString().trim();
                    searchSupplier();
                }

                break;
            //供货商
            case R.id.rl_search_shidanjingjia:
                tvSearchShidanjingjia.setTextColor(getResources().getColor(R.color.b82db));
                tvSearchShidanjingjialine.setBackgroundColor(getResources().getColor(R.color.b82db));
                tvSearchShidancaigou.setTextColor(getResources().getColor(R.color.be));
                tvSearchShidancaigouline.setBackgroundColor(getResources().getColor(R.color.be));
                isGoods = false;
                lvSearchResult.setAdapter(mSupplierAdapter);
                break;
            //商品
            case R.id.rl_search_shidancaigou:
                tvSearchShidancaigou.setTextColor(getResources().getColor(R.color.b82db));
                tvSearchShidancaigouline.setBackgroundColor(getResources().getColor(R.color.b82db));
                tvSearchShidanjingjia.setTextColor(getResources().getColor(R.color.be));
                tvSearchShidanjingjialine.setBackgroundColor(getResources().getColor(R.color.be));
                isGoods = true;
                lvSearchResult.setAdapter(mGoodsAdapter);
                break;
            //清空历史记录
            case R.id.tv_search_clearhistory:
                if (isGoods) {
                    searchGoodsList.clear();
                    dataSaveGoods.clearDataList(SG_TAG);
                    arrayGoodsAdapter.notifyDataSetChanged();
                } else {
                    searchSuppList.clear();
                    dataSaveSupplier.clearDataList(SS_TAG);
                    arraySupplierAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void searchGoods() {

        isNeedAdd = true;
        for (int i = 0; i < searchGoodsList.size(); i++) {
            if (searchGoodsList.get(i).equals(searchGoods)) {
                isNeedAdd = false;
            }
        }

        if (TextUtils.isEmpty(searchGoods)) {
            Toast.makeText(this, "请输入关键字！", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page", String.valueOf(pageNo));
            jsonObject.put("limit", "10");
            jsonObject.put("searchParam", searchGoods);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("aaa", "(SearchActivity.java:241)<---->" + jsonObject.toString());

        OkHttpUtils.post()
                .url(NetConfig.SEARCG_GOODS)
                .addParams("jsonStr", jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(SearchActivity.java:147)<--搜索商品的失败返回-->" + e.getMessage());
                        lvSearchResult.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(SearchActivity.java:154)<--搜索商品的成功返回-->" + response);
                        llHistory.setVisibility(View.GONE);
                        llSearchResult.setVisibility(View.VISIBLE);
                        lvSearchLishijilu.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject1 = new JSONObject(response);
                                int code = jsonObject1.getInt("code");
                                if (code == 0) {
                                    if (modifyIsNeedAdd(searchGoods, searchGoodsList)) {
                                        searchGoodsList.add(searchGoods);
                                        dataSaveGoods.setDataList(SG_TAG, searchGoodsList);
                                    }
                                    SearchGoods searchGoods = new Gson().fromJson(response, SearchGoods.class);
                                    Log.e("aaa",
                                            "(SearchActivity.java:305)<--sadasdad-->" + searchGoods.getData().getList().size());
                                    mSearchGoodsList.addAll(searchGoods.getData().getList());
                                    mGoodsAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(SearchActivity.this, "No Result！", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lvSearchResult.onRefreshComplete();
                    }
                });

    }

    private void searchSupplier() {
        final String search = etSearchCintent.getText().toString().trim();

        if (TextUtils.isEmpty(search)) {
            Toast.makeText(this, "请输入关键字！", Toast.LENGTH_SHORT).show();
            return;
        }
        OkHttpUtils
                .post()
                .url(NetConfig.SEARCH_SUPPLIER)
                .addParams("searchParam", search)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(SearchActivity.java:148)<--搜索供应商的失败返回-->" + e.getMessage());
                        lvSearchResult.onRefreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(SearchActivity.java:154)<--搜索供应商的成功返回-->" + response);

                        llHistory.setVisibility(View.GONE);
                        llSearchResult.setVisibility(View.VISIBLE);
                        lvSearchLishijilu.setVisibility(View.GONE);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    if (modifyIsNeedAdd(search, searchSuppList)) {
                                        searchSuppList.add(search);
                                        dataSaveSupplier.setDataList(SS_TAG, searchSuppList);
                                    }
                                    SearchSupplier searchSupplier = new Gson().fromJson(response, SearchSupplier.class);
                                    mSearchSupplierList.addAll(searchSupplier.getData());
                                    mSupplierAdapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(SearchActivity.this, "No Result!", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        lvSearchResult.onRefreshComplete();
                    }
                });

    }

    private boolean modifyIsNeedAdd(String search, List<String> mlist) {
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).equals(search)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 历史记录的Item点击事件
     *
     * @param adapterView
     * @param view
     * @param i
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (isGoods) {
            searchGoods = searchGoodsList.get(i);
            searchGoods();
        } else {
            searchSupplier = searchSuppList.get(i);
            searchSupplier();
        }
    }


}
