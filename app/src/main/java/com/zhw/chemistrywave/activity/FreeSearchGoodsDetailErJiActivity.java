package com.zhw.chemistrywave.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.XTXPOrder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FreeSearchGoodsDetailErJiActivity extends BaseActivity {


    @BindView(R.id.iv_freedetection_back)
    ImageView ivFreedetectionBack;
    @BindView(R.id.tv_freedetection_title)
    TextView tvFreedetectionTitle;
    @BindView(R.id.tv_freesearchgoodsdetailerji_qiangdu)
    TextView tvFreesearchgoodsdetailerjiQiangdu;
    @BindView(R.id.tv_freesearchgoodsdetailerji_seguang)
    TextView tvFreesearchgoodsdetailerjiSeguang;
    @BindView(R.id.tv_freesearchgoodsdetailerji_waiguang)
    TextView tvFreesearchgoodsdetailerjiWaiguang;
    @BindView(R.id.tv_freesearchgoodsdetailerji_shuifen)
    TextView tvFreesearchgoodsdetailerjiShuifen;
    @BindView(R.id.tv_freesearchgoodsdetailerji_burongwu)
    TextView tvFreesearchgoodsdetailerjiBurongwu;
    @BindView(R.id.tv_freesearchgoodsdetailerji_rongjiedu)
    TextView tvFreesearchgoodsdetailerjiRongjiedu;
    @BindView(R.id.tv_freesearchgoodsdetailerji_lvlizi)
    TextView tvFreesearchgoodsdetailerjiLvlizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_yanfen)
    TextView tvFreesearchgoodsdetailerjiYanfen;
    @BindView(R.id.tv_freesearchgoodsdetailerji_diandaolv)
    TextView tvFreesearchgoodsdetailerjiDiandaolv;
    @BindView(R.id.tv_freesearchgoodsdetailerji_mishitong)
    TextView tvFreesearchgoodsdetailerjiMishitong;
    @BindView(R.id.tv_freesearchgoodsdetailerji_guhanliang)
    TextView tvFreesearchgoodsdetailerjiGuhanliang;
    @BindView(R.id.tv_freesearchgoodsdetailerji_tonglizi)
    TextView tvFreesearchgoodsdetailerjiTonglizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_qianlizi)
    TextView tvFreesearchgoodsdetailerjiQianlizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_xinlizi)
    TextView tvFreesearchgoodsdetailerjiXinlizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_xilizi)
    TextView tvFreesearchgoodsdetailerjiXilizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_nielizi)
    TextView tvFreesearchgoodsdetailerjiNielizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_gulizi)
    TextView tvFreesearchgoodsdetailerjiGulizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_tilizi)
    TextView tvFreesearchgoodsdetailerjiTilizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_gonglizi)
    TextView tvFreesearchgoodsdetailerjiGonglizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_gelizi)
    TextView tvFreesearchgoodsdetailerjiGelizi;
    @BindView(R.id.tv_freesearchgoodsdetailerji_bilizi)
    TextView tvFreesearchgoodsdetailerjiBilizi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_search_goods_detail_er_ji);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据源
     */
    private void initData() {
        tvFreedetectionTitle.setText("System inquiry details");
        XTXPOrder.DataBean.ListBean list = (XTXPOrder.DataBean.ListBean) getIntent().getSerializableExtra("list");
        tvFreesearchgoodsdetailerjiQiangdu.setText("Intension(Color)："+list.getColor_power());
        tvFreesearchgoodsdetailerjiSeguang.setText("Colored light："+list.getColor_light());
        tvFreesearchgoodsdetailerjiWaiguang.setText("Appearance："+list.getQuality_aspect());
        tvFreesearchgoodsdetailerjiShuifen.setText("Moisture："+list.getQuality_moisture());
        tvFreesearchgoodsdetailerjiBurongwu.setText("Insolubles："+list.getQuality_insoluble());
        tvFreesearchgoodsdetailerjiRongjiedu.setText("Solubility："+list.getQuality_solubility());
        tvFreesearchgoodsdetailerjiLvlizi.setText("Chloridion："+list.getQuality_clContent());
        tvFreesearchgoodsdetailerjiYanfen.setText("Salinity："+list.getQuality_salinity());
        tvFreesearchgoodsdetailerjiDiandaolv.setText("Conductivity："+list.getQuality_conductivity());
        tvFreesearchgoodsdetailerjiMishitong.setText("Michler's ketone："+list.getQuality_michlerKetone());
        tvFreesearchgoodsdetailerjiGuhanliang.setText("Solid content："+list.getQuality_solidContent());
        tvFreesearchgoodsdetailerjiTonglizi.setText("Cu："+list.getMetal_cu());
        tvFreesearchgoodsdetailerjiQianlizi.setText("Pb："+list.getMetal_pb());
        tvFreesearchgoodsdetailerjiXinlizi.setText("Zn："+list.getMetal_zn());
        tvFreesearchgoodsdetailerjiXilizi.setText("Sn："+list.getMetal_sn());
        tvFreesearchgoodsdetailerjiNielizi.setText("Ni："+list.getMetal_ni());
        tvFreesearchgoodsdetailerjiGulizi.setText("Co："+list.getMetal_co());
        tvFreesearchgoodsdetailerjiTilizi.setText("Sb："+list.getMetal_sb());
        tvFreesearchgoodsdetailerjiGonglizi.setText("Hg："+list.getMetal_hg());
        tvFreesearchgoodsdetailerjiGelizi.setText("Cd："+list.getMetal_cd());
        tvFreesearchgoodsdetailerjiBilizi.setText("Bi："+list.getMetal_bi());

    }

    @OnClick(R.id.iv_freedetection_back)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
