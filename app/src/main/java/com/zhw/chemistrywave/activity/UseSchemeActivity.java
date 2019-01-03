package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.LoveGoods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseSchemeActivity extends BaseActivity {

    @BindView(R.id.lv_user_scheme)
    ListView lvUserScheme;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.ll_download)
    LinearLayout llDownload;
    @BindView(R.id.rl_to_download)
    RelativeLayout rlToDownload;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    private List<LoveGoods> mList;
    private MyAdapter mAdapter;
    private String application_scheme;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_scheme);
        ButterKnife.bind(this);

        name = getIntent().getStringExtra("name");
        tvTitlebarCenter.setText(name);
        Log.e("aaa", "-application_scheme--usescheme-->" + application_scheme);
        initView();
        lvUserScheme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void initView() {
        mList = new ArrayList<>();
        if (!TextUtils.isEmpty(application_scheme)) {
            mList.add(new LoveGoods(R.drawable.jiancebaogao_jpg, "检测报告", application_scheme, "7kb"));
            mAdapter = new MyAdapter();
            lvUserScheme.setAdapter(mAdapter);
        }


    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_cancel, R.id.ll_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_cancel:
                rlToDownload.setVisibility(View.GONE);
                break;
            case R.id.ll_download:
                break;
        }
    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
    }


    class MyAdapter extends BaseAdapter {

        private int size = 0;
        private int CurrentMode = 0;
        private int NormalMode = 0;
        private int SelectMode = 2;
        private HashMap<Integer, Boolean> deleteMap = new HashMap<>();

        public MyAdapter() {
            for (int i = 0; i < mList.size(); i++) {
                deleteMap.put(i, false);
            }
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
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(UseSchemeActivity.this).inflate(R.layout.item_use_scheme_lv, parent, false);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.e("aaa", "--lllllllll-->" + mList.size() + mList.get(0).getName());
            holder.tvFileImg.setImageResource(mList.get(position).getLogo());
            holder.tvFileName.setText(mList.get(position).getName());
            holder.tvFileSize.setText(mList.get(position).getSize());
            holder.cbSelect.setVisibility(View.VISIBLE);
            holder.cbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (rlToDownload.getVisibility() == View.GONE) {

                        rlToDownload.setVisibility(View.VISIBLE);

                    }
                    if (isChecked) {
                        mList.get(position).setCheck(true);
                    } else {
                        mList.get(position).setCheck(false);
                    }
                    deleteMap.put(position, isChecked);
                    ArrayList<Boolean> tempList = new ArrayList<>();
                    for (int i = 0; i < mList.size(); i++) {
                        if (deleteMap.get(i)) {
                            tempList.add(deleteMap.get(i));
                        }
                    }
                }
            });

            holder.cbSelect.setChecked(mList.get(position).isCheck());
            return convertView;
        }


        @Override
        public void notifyDataSetChanged() {
            deleteMap.clear();
            if (mList != null) {
                for (int i = 0; i < mList.size(); i++) {
                    deleteMap.put(i, false);
                }
            }
            super.notifyDataSetChanged();
        }

        public HashMap<Integer, Boolean> getDeleteMap() {
            return deleteMap;
        }

        public void setList() {
            if (mList != null) {
                deleteMap.clear();
                for (int i = 0; i < mList.size(); i++) {
                    deleteMap.put(i, false);
                }
            }
        }

        class ViewHolder {
            @BindView(R.id.tv_file_img)
            ImageView tvFileImg;
            @BindView(R.id.tv_file_name)
            TextView tvFileName;
            @BindView(R.id.tv_file_size)
            TextView tvFileSize;
            @BindView(R.id.cb_select)
            CheckBox cbSelect;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
