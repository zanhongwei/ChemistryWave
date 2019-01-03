package com.zhw.chemistrywave.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.activity.JingJiaDetailActivity;
import com.zhw.chemistrywave.activity.OneKeyJingBiaoActivity;
import com.zhw.chemistrywave.adapters.HomeJjItemAdapter;
import com.zhw.chemistrywave.bean.SdcgAllOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sdjjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sdjjFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.tv_jjitem_ordnum)
    TextView tvJjitemOrdnum;
    @BindView(R.id.lv_jignjiaitem)
    ListView lvJignjiaitem;
    @BindView(R.id.tv_jjitem_checkdetail)
    TextView tvJjitemCheckdetail;
    @BindView(R.id.tv_jjitem_onkeyjing)
    TextView tvJjitemOnkeyjing;
    Unbinder unbinder;


    // TODO: Rename and change types of parameters
    private SdcgAllOrder.DataBean.ListBean mParam1;
    private String mParam2;
    private HomeJjItemAdapter hjjItemAdapter;
    private List<SdcgAllOrder.DataBean.ListBean.CargoBeansBean> mList;

    public sdjjFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sdjjFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static sdjjFragment newInstance(SdcgAllOrder.DataBean.ListBean param1, String param2) {
        sdjjFragment fragment = new sdjjFragment();
        Bundle args = new Bundle();
        args.putSerializable("listbean", param1);
//        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (SdcgAllOrder.DataBean.ListBean) getArguments().getSerializable("listbean");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sdjj, container, false);
        tvJjitemOrdnum.setText("订单编号：" + mParam1.getSpon_id());
        mList = new ArrayList<>();
        hjjItemAdapter = new HomeJjItemAdapter(getActivity(), mList);
        hjjItemAdapter.setSponsor_id(mParam1.getSponsor_id());
        hjjItemAdapter.setSpon_serl(mParam1.getSpon_serl());
        lvJignjiaitem.setAdapter(hjjItemAdapter);
        mList.addAll(mParam1.getCargoBeans());
        hjjItemAdapter.notifyDataSetChanged();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_jjitem_checkdetail, R.id.tv_jjitem_onkeyjing})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_jjitem_checkdetail:
                startActivity(new Intent(getActivity(), JingJiaDetailActivity.class).putExtra("spon_id", mParam1.getSpon_id() + "")
                        .putExtra("spon_serl", mParam1.getSpon_serl() + ""));
                break;
            case R.id.tv_jjitem_onkeyjing:
                MyApplication.setNtype("5");
                startActivity(new Intent(getActivity(), OneKeyJingBiaoActivity.class).putExtra("spon_id", mParam1.getSpon_id() + ""));
                break;
        }
    }
}
