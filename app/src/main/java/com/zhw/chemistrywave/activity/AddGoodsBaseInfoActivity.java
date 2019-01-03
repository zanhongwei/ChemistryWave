package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tu.loadingdialog.LoadingDailog;
import com.blankj.utilcode.utils.KeyboardUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.adapters.PopLvAdapter;
import com.zhw.chemistrywave.adapters.PopLvErJiAdapter;
import com.zhw.chemistrywave.adapters.SpinerAdapter;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.AddGoodsBaseInfo;
import com.zhw.chemistrywave.bean.FenLeiJsonBean;
import com.zhw.chemistrywave.bean.GetJsonDataUtil;
import com.zhw.chemistrywave.bean.GoodsSortsBean;
import com.zhw.chemistrywave.bean.Shangpxiangqing;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SelectImageUtils;
import com.zhw.chemistrywave.view.MyPopWindow;
import com.zhw.chemistrywave.view.SpinerPopWindow;
import com.zhy.base.fileprovider.FileProvider7;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddGoodsBaseInfoActivity extends BaseActivity {


    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_need_yingwenname)
    EditText etNeedName;
    @BindView(R.id.et_need_cino)
    EditText etNeedCINO;
    @BindView(R.id.et_need_cas)
    EditText etNeedCAS;
    @BindView(R.id.et_need_fenziliang)
    EditText etNeedFZL;
    @BindView(R.id.et_need_psa)
    EditText etNeedPSA;
    @BindView(R.id.et_need_jingquezhiliang)
    EditText etNeedJQZL;
    @BindView(R.id.et_need_guige)
    EditText etNeedGuige;
    @BindView(R.id.et_need_chundu)
    EditText etNeedChundu;
    @BindView(R.id.et_need_timequest)
    EditText etNeedTimequest;
    @BindView(R.id.et_need_numquest)
    EditText etNeedNumquest;
    @BindView(R.id.tv_need_dun)
    TextView tvNeedDun;
    @BindView(R.id.iv_register_type)
    ImageView ivRegisterType;
    @BindView(R.id.ll_need_shuliangdanwei)
    LinearLayout llNeedShuliangdanwei;
    @BindView(R.id.et_need_package)
    EditText etNeedPackage;
    @BindView(R.id.et_need_packrequest)
    TextView etNeedPackrequest;
    @BindView(R.id.et_need_money)
    EditText etNeedMoney;
    @BindView(R.id.rb1)
    RadioButton rb1;
    @BindView(R.id.rb2)
    RadioButton rb2;
    @BindView(R.id.rb3)
    RadioButton rb3;
    @BindView(R.id.rb4)
    RadioButton rb4;
    @BindView(R.id.rb5)
    RadioButton rb5;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.tv_need_scyyfa)
    ImageView tvNeedScyyfa;
    @BindView(R.id.iv_need_sccptp)
    ImageView ivNeedSccptp;
    @BindView(R.id.iv_need_sccczt)
    ImageView ivNeedSccczt;
    @BindView(R.id.tv_goodbaseinfo_save)
    TextView tvGoodbaseinfoSave;
    @BindView(R.id.et_need_fenlei)
    TextView tvNeedFenleiOne;
    @BindView(R.id.et_need_erji)
    TextView tvNeedErji;
    private String type;
    private ArrayList<String> listFour;
    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private Bitmap bitmap1;
    private String tag;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private String yingyongfanan;
    private String chanpintupian;
    private String shengchanzhuangtai;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private Thread thread;
    private ArrayList<FenLeiJsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(AddGoodsBaseInfoActivity.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;


            }
        }
    };
    private String mer_id;
    private String onetype;
    private String twotype;
    private GoodsSortsBean goodsSortsBean;
    private LoadingDailog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_base_info);
        ButterKnife.bind(this);


        //暂时只支持线下支付方式 start-------------------
        rb2.setEnabled(false);
        rb3.setEnabled(false);
        rb4.setEnabled(false);
        rb5.setEnabled(false);
        //暂时只支持线下支付方式 end-------------------

//        initJsonData();
        initJsonDataFromService();
        tvTitlebarCenter.setText("goods base information");
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "EditGoodsActivity":
                mer_id = getIntent().getStringExtra("mer_id");
                getData();
                break;
            case "TianJiaShangPingActivity":
                setViewData();
                break;
        }

        LoadingDailog.Builder loadBuilder = new LoadingDailog.Builder(this)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(true);
        dialog = loadBuilder.create();


    }

    private void setViewData() {

        if (getIntent().getExtras() != null && getIntent().getExtras().getSerializable("baseInfo") != null) {
            Bundle extras = getIntent().getExtras();
            AddGoodsBaseInfo baseInfo = (AddGoodsBaseInfo) extras.getSerializable("baseInfo");

            etNeedName.setText(baseInfo.getCargo_name());
            etNeedGuige.setText(baseInfo.getCargo_specification());
            etNeedMoney.setText(baseInfo.getCurrent_price());
            etNeedChundu.setText(baseInfo.getCargo_purity());
            etNeedTimequest.setText(baseInfo.getCargo_huoqi());
            etNeedNumquest.setText(baseInfo.getCargo_num());
            etNeedPackage.setText(baseInfo.getCargo_package());
            etNeedPackrequest.setText(baseInfo.getTransport_way());
            tvNeedFenleiOne.setText(baseInfo.getOne_type());
            tvNeedErji.setText(baseInfo.getTwo_type());
            etNeedCINO.setText(baseInfo.getCino());
            etNeedCAS.setText(baseInfo.getCas());
            etNeedFZL.setText(baseInfo.getMolecular_weight());
            etNeedPSA.setText(baseInfo.getPas());
            etNeedJQZL.setText(baseInfo.getPrecise_quality());
            etNeedChundu.setText(baseInfo.getCargo_purity());
            String payment_way = baseInfo.getPayment_way();
            switch (payment_way) {
                case "offline":
                    rb1.setChecked(true);
                    break;
                case "alipay":
                    rb2.setChecked(true);
                    break;
                case "wechat":
                    rb3.setChecked(true);
                    break;
                case "unionpay":
                    rb4.setChecked(true);
                    break;
            }
            chanpintupian = baseInfo.getProduct_picture();
            yingyongfanan = baseInfo.getApplication_scheme();
            shengchanzhuangtai = baseInfo.getProduction_state();

            Glide.with(this).load(NetConfig.baseurl + yingyongfanan).apply(MyApplication.options).into(tvNeedScyyfa);
            Glide.with(this).load(NetConfig.baseurl + chanpintupian).apply(MyApplication.options).into(ivNeedSccptp);
            Glide.with(this).load(NetConfig.baseurl + yingyongfanan).apply(MyApplication.options).into(ivNeedSccczt);
        }
    }

    //获取网上分类数据
    private void initJsonDataFromService() {

        OkHttpUtils.get().url(NetConfig.SORT_GOODS).build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddGoodsBaseInfoActivity.java:222)<---->" + e.getMessage());
                        Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddGoodsBaseInfoActivity.java:228)<---->" + response);
                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        } else {

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    goodsSortsBean = new Gson().fromJson(response, GoodsSortsBean.class);
                                } else {
                                    Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_need_shuliangdanwei, R.id.et_need_packrequest,
            R.id.tv_need_scyyfa, R.id.iv_need_sccptp, R.id.iv_need_sccczt, R.id.tv_goodbaseinfo_save,
            R.id.ll_need_fenlei})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            //上传应用方案
            case R.id.tv_need_scyyfa:
                tag = "1";
                getHeadImage();
                break;
            //上传产品图片
            case R.id.iv_need_sccptp:
                tag = "2";
                getHeadImage();
                break;
            //上传生成状态
            case R.id.iv_need_sccczt:
                tag = "3";
                getHeadImage();
                break;
            case R.id.tv_goodbaseinfo_save:
                saveShiDanCaiGou();
                break;
            case R.id.ll_need_shuliangdanwei:
                showSpinWindowAddress();
                break;
            case R.id.et_need_packrequest:
                showPackPoP();
                break;
            case R.id.ll_need_fenlei:
                KeyboardUtils.hideSoftInput(AddGoodsBaseInfoActivity.this);
                showPopUpWindow();
                break;

        }
    }


    private void showPackPoP() {
        View view2 = View.inflate(this, R.layout.pop_package, null);
        TextView tv1 = (TextView) view2.findViewById(R.id.tv1);
        TextView tv2 = (TextView) view2.findViewById(R.id.tv2);
        TextView tv3 = (TextView) view2.findViewById(R.id.tv3);
        TextView tv4 = (TextView) view2.findViewById(R.id.tv4);
        TextView tv5 = (TextView) view2.findViewById(R.id.tv5);
        TextView[] tvs = {tv1, tv2, tv3, tv4, tv5};
        final MyPopWindow transportpop = new MyPopWindow(this, view2);
        transportpop.showAtLocation(etNeedName, Gravity.BOTTOM, 0, 0);
        for (int i = 0; i < tvs.length; i++) {

        }
        for (final TextView tv :
                tvs) {
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    etNeedPackrequest.setText(tv.getText().toString());
                    transportpop.dismiss();
                }
            });
        }

    }

    private void saveShiDanCaiGou() {

        final String name = etNeedName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please fill in goods name", Toast.LENGTH_SHORT).show();
            return;
        }

        final String needCINO = etNeedCINO.getText().toString().trim();
        if (TextUtils.isEmpty(needCINO)) {
            Toast.makeText(this, "Please fill in CINO", Toast.LENGTH_SHORT).show();
            return;
        }
        final String needCAS = etNeedCAS.getText().toString().trim();
        if (TextUtils.isEmpty(needCAS)) {
            Toast.makeText(this, "Please fill in CAS", Toast.LENGTH_SHORT).show();
            return;
        }
        final String needFZL = etNeedFZL.getText().toString().trim();
        if (TextUtils.isEmpty(needFZL)) {
            Toast.makeText(this, "Please fill in molecular weight", Toast.LENGTH_SHORT).show();
            return;
        }
        final String needPSA = etNeedPSA.getText().toString().trim();
        if (TextUtils.isEmpty(needPSA)) {
            Toast.makeText(this, "Please fill in PSA", Toast.LENGTH_SHORT).show();
            return;
        }
        final String needJQZL = etNeedJQZL.getText().toString().trim();
        if (TextUtils.isEmpty(needJQZL)) {
            Toast.makeText(this, "Please fill in precise quality", Toast.LENGTH_SHORT).show();
            return;
        }
        final String firstSort = tvNeedFenleiOne.getText().toString().trim();
        if (TextUtils.isEmpty(firstSort)) {
            Toast.makeText(this, "Please fill in first sort", Toast.LENGTH_SHORT).show();
            return;
        }
        final String secondSort = tvNeedErji.getText().toString().trim();
        if (TextUtils.isEmpty(secondSort)) {
            Toast.makeText(this, "Please fill in second sort", Toast.LENGTH_SHORT).show();
            return;
        }

        String guige = etNeedGuige.getText().toString().trim();
        if (TextUtils.isEmpty(guige)) {
            Toast.makeText(this, "Please fill in goods specification", Toast.LENGTH_SHORT).show();
            return;
        }
        String chundu = etNeedChundu.getText().toString().trim();
        if (TextUtils.isEmpty(chundu)) {
            Toast.makeText(this, "Please fill in goods purity", Toast.LENGTH_SHORT).show();
            return;
        }
        String huoqi = etNeedTimequest.getText().toString().trim();
        if (TextUtils.isEmpty(huoqi)) {
            Toast.makeText(this, "Please fill in delivery time", Toast.LENGTH_SHORT).show();
            return;
        }
        String num = etNeedNumquest.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "Please fill in goods number", Toast.LENGTH_SHORT).show();
            return;
        }
        String goods_unit = tvNeedDun.getText().toString().trim();
        String baozhuang = etNeedPackage.getText().toString().trim();
        if (TextUtils.isEmpty(baozhuang)) {
            Toast.makeText(this, "Please fill in goods package", Toast.LENGTH_SHORT).show();
            return;
        }
        String yunshufangshi = etNeedPackrequest.getText().toString().trim();
        if (TextUtils.isEmpty(yunshufangshi)) {
            Toast.makeText(this, "Please fill in transportation way", Toast.LENGTH_SHORT).show();
            return;
        }
        String zuidijiage = etNeedMoney.getText().toString().trim();
        if (TextUtils.isEmpty(zuidijiage)) {
            Toast.makeText(this, "Please fill in the bottom price", Toast.LENGTH_SHORT).show();
            return;
        }
        String fukuangyaoqiul = null;//付款要求（offline线下 alipay支付宝 wechat微信 unionpay银联）
        if (rb1.isChecked()) {
            fukuangyaoqiul = "offline";
        } else if (rb2.isChecked()) {
            fukuangyaoqiul = "alipay";
        } else if (rb3.isChecked()) {
            fukuangyaoqiul = "wechat";
        } else if (rb4.isChecked()) {
            fukuangyaoqiul = "unionpay";
        } else {
            Toast.makeText(this, "Please choose payment way", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(yingyongfanan)) {
            Toast.makeText(this, "Please upload the app scheme", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(chanpintupian)) {
            Toast.makeText(this, "Please upload this production photos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(shengchanzhuangtai)) {
            Toast.makeText(this, "Please upload this production state", Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        //货物名称
        bundle.putString("goods_name", name);
        //商品分类一级分类
        bundle.putString("one_type", firstSort);
        //商品分类二级分类
        bundle.putString("two_type", secondSort);
        //货物纯度
        bundle.putString("cargo_purity", chundu);
        //货物规格
        bundle.putString("cargo_specification", guige);
        //货物数量
        bundle.putString("cargo_num", num);
        //货物单位
        bundle.putString("goods_unit", goods_unit);
        //货物包装
        bundle.putString("cargo_package", baozhuang);
        //货物运输方式
        bundle.putString("transport_way", yunshufangshi);
        //货物最低价格
        bundle.putString("current_price", zuidijiage);
        //货物货期
        bundle.putString("cargo_huoqi", huoqi);
        //货物付款要求
        bundle.putString("payment_way", fukuangyaoqiul);
        //货物应用方案
        bundle.putString("application_scheme", yingyongfanan);
        //货物产品图案
        bundle.putString("product_picture", chanpintupian);
        //生产状态
        bundle.putString("production_state", shengchanzhuangtai);
        bundle.putString("cino", needCINO);
        bundle.putString("cas", needCAS);
        bundle.putString("molecular_weight", needFZL);
        bundle.putString("pas", needPSA);
        bundle.putString("precise_quality", needJQZL);

        setResult(1111, new Intent().putExtras(bundle));
        finish();
    }


    /**
     * 发布位置弹框
     */
    private void showSpinWindowAddress() {
        listFour = new ArrayList<String>();
        listFour.add("吨");
        listFour.add("千克");
        SpinerAdapter mAdapter4 = new SpinerAdapter(this, listFour);
        mAdapter4.refreshData(listFour, 0);

        //显示第一条数据
        tvNeedDun.setText(listFour.get(0));
        //初始化PopWindow
        SpinerPopWindow mSpinerPopWindow4 = new SpinerPopWindow(this);
        mSpinerPopWindow4.setAdatper(mAdapter4);
        mSpinerPopWindow4.setItemListener(new SpinerAdapter.IOnItemSelectListener() {
            @Override
            public void onItemClick(int pos) {
                if (pos >= 0 && pos <= listFour.size()) {
                    String value = listFour.get(pos);
                    tvNeedDun.setText(value);
                }
            }
        });
        mSpinerPopWindow4.setWidth(llNeedShuliangdanwei.getWidth());
        mSpinerPopWindow4.showAsDropDown(llNeedShuliangdanwei);
    }


    /**
     * 添加头像
     */
    private void getHeadImage() {
        final CharSequence[] items = {"Photos", "Camera"};
        AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Choose picture")
                .setItems(new String[]{"Photos", "Camera"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        if (item == 0) {
                            gallery();
                        } else {
                            checkPermission(3);
                        }
                    }
                }).create();
        dlg.show();
    }

    /*
      * 从相册获取
      */
    public void gallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, PHOTO_REQUEST_GALLERY);
    }

    /*
     * 从相机获取
     */
    public void camera() {
        // 拍照
        //设置图片的保存路径,作为全局变量
        Intent intent;
        Date date = new Date(System.currentTimeMillis());
        File pictureFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg");
        // 判断当前系统
        imageFileUri = FileProvider7.getUriForFile(this, pictureFile);
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // 去拍照
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

    public void checkPermission(int state) {
        if (state == 3) {
            int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                        CODE_FOR_CAMERA_PERMISSION);
                return;
            } else {
                camera();
            }
        } else {
            int hasWriteContactsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        CODE_FOR_WRITE_PERMISSION);
                return;
            } else {
                if (state == 1) {
                    fun1();
                } else if (state == 2) {
                    fun2();
                }
            }
        }
    }

    private void fun1() {
        //从相册选取成功后，需要从Uri中拿出图片的绝对路径，再调用剪切
        Uri newUri = Uri.parse("file:///" + SelectImageUtils.getPath(this, data.getData()));
        Date date = new Date(System.currentTimeMillis());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg";
        File temp = new File(imageFilePath);
        //获取文件的Uri
        outputUri = FileProvider7.getUriForFile(this, temp);
        cropImageUri(this, newUri, outputUri);
    }

    private void fun2() {
        Date date = new Date(System.currentTimeMillis());
        imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + String.valueOf(date.getTime()) + ".jpg";
        File temp = new File(imageFilePath);
        //获取文件的Uri
        outputUri = FileProvider7.getUriForFile(this, temp);
        cropImageUri(this, imageFileUri, outputUri);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
                if (state == 1) {
                    fun1();
                } else if (state == 2) {
                    fun2();
                }
            } else {
                //用户不同意，自行处理即可
            }
        }

        if (requestCode == CODE_FOR_CAMERA_PERMISSION) {
            if (permissions[0].equals(Manifest.permission.CAMERA)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意使用write
                camera();
            } else {
                //用户不同意，自行处理即可
                Toast.makeText(this, "open the camera permission in settings", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //调用系统的剪裁处理图片并保存至imageUri中
    public void cropImageUri(Activity activity, Uri orgUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", ivNeedSccczt.getWidth());
        intent.putExtra("aspectY", ivNeedSccczt.getHeight());
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (resultCode == -1) {
                this.data = data;
                state = 1;
                checkPermission(1);
            }

        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (resultCode == -1) {
                //从相机拍摄保存的Uri中取出图片，调用系统剪裁工具
                if (imageFilePath != null) {
                    state = 2;
                    checkPermission(2);
                }
            }

        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                try {
                    if ("1".equals(tag)) {
                        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        tvNeedScyyfa.setImageBitmap(bitmap1);
                        ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddGoodsBaseInfoActivity.this);
                        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                        progressHUD.show();
                        saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "1");
                        progressHUD.dismiss();
                    }
                    if ("2".equals(tag)) {
                        bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivNeedSccptp.setImageBitmap(bitmap2);
                        ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddGoodsBaseInfoActivity.this);
                        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                        progressHUD.show();
                        saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "2");
                        progressHUD.dismiss();
                    }
                    if ("3".equals(tag)) {
                        bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivNeedSccczt.setImageBitmap(bitmap3);
                        ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddGoodsBaseInfoActivity.this);
                        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                        progressHUD.show();
                        saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "3");
                        progressHUD.dismiss();
                    }
                    // uploadHeadToServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void saveFile(File file, final String s) {
        dialog.show();
        OkHttpUtils.post().url(NetConfig.SAVE_FILE)
                .addFile("file", "1.jpg", file)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---上传文件返回--error-->" + e.getMessage());
                        dialog.dismiss();
                        Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---上传文件返回---->" + response);
                        /**
                         * {"code":0,"data":"upload/1/20171223120941476.jpg"}
                         */
                        dialog.dismiss();
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    String data = jo1.getString("data");
                                    if ("1".equals(s)) {
                                        yingyongfanan = data;
                                    } else if ("2".equals(s)) {
                                        chanpintupian = data;
                                    } else if ("3".equals(s)) {
                                        shengchanzhuangtai = data;
                                    }
                                } else {
                                    Toast.makeText(AddGoodsBaseInfoActivity.this, "upload fialed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

//    private void ShowPickerView() {// 弹出选择器
//
//        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText() +
//                        options2Items.get(options1).get(options2);
//                etNeedFenlei.setText(tx);
//            }
//        })
//
//                .setTitleText("分类选择")
//                .setDividerColor(Color.BLACK)
//                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
//                .setContentTextSize(20)
//                .setTitleColor(getResources().getColor(R.color.logo))//标题文字颜色
//                .setSubmitColor(getResources().getColor(R.color.logo))//确定按钮文字颜色
//                .setCancelColor(getResources().getColor(R.color.logo))//取消按钮文字颜色
//                .setCyclic(false, false, false)//循环与否
//                .setSelectOptions(4, 2)  //设置默认选中项
//                .setDividerColor(getResources().getColor(R.color.white))
//                .setTextColorCenter(getResources().getColor(R.color.b3ee))//设置选中项的颜色
//                .isDialog(false)
//                .build();
//
//        /*pvOptions.setPicker(options1Items);//一级选择器
//        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
//        pvOptions.setPicker(options1Items, options2Items);//三级选择器
//        pvOptions.show();
//    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "feilei.json");//获取assets目录下的json文件数据

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

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

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
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void showPopUpWindow() {

        if (null == goodsSortsBean) {
            initJsonDataFromService();
        } else {
            View contentView = LayoutInflater.from(AddGoodsBaseInfoActivity.this).inflate(R.layout.item_pop_fenlei, null);
            final PopupWindow popWnd = new PopupWindow(AddGoodsBaseInfoActivity.this);
            popWnd.setContentView(contentView);
//    popWnd.setWidth(263);
//    popWnd.setHeight(320);
            popWnd.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            popWnd.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            View contentView1 = popWnd.getContentView();
//        contentView1.findViewById(R.id.tv_fenlei_sure).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popWnd.dismiss();
//            }
//        });
//        contentView1.findViewById(R.id.tv_fenlei_reset).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popWnd.dismiss();
//            }
//        });
            ListView lvLeft = ((ListView) contentView1.findViewById(R.id.lv_fenlei1));
            final ListView lvRight = ((ListView) contentView1.findViewById(R.id.lv_fenlei2));
            final PopLvAdapter adapter = new PopLvAdapter(AddGoodsBaseInfoActivity.this, goodsSortsBean.getData());
            lvLeft.setAdapter(adapter);
            lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    adapter.setCurrentPosition(position);
                    final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(AddGoodsBaseInfoActivity.this, goodsSortsBean.getData().get(position).getList());
                    lvRight.setAdapter(adapter1);
                    lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            onetype = goodsSortsBean.getData().get(MyApplication.currentposition).getText();
                            twotype = goodsSortsBean.getData().get(MyApplication.currentposition).getList().get(position).getText();
                            tvNeedFenleiOne.setText(onetype);
                            tvNeedErji.setText(twotype);
                            adapter1.notifyDataSetChanged();
                            popWnd.dismiss();

                        }
                    });
                }
            });

            final PopLvErJiAdapter adapter1 = new PopLvErJiAdapter(AddGoodsBaseInfoActivity.this, goodsSortsBean.getData().get(0).getList());
            lvRight.setAdapter(adapter1);
            lvRight.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MyApplication.currentpositionerji = position;
                    onetype = goodsSortsBean.getData().get(0).getText();
                    twotype = goodsSortsBean.getData().get(0).getList().get(position).getText();
                    adapter1.notifyDataSetChanged();
                    tvNeedFenleiOne.setText(onetype);
                    tvNeedErji.setText(twotype);
                    popWnd.dismiss();
                }
            });
            lvLeft.setAdapter(adapter);

            popWnd.setTouchable(true);
            popWnd.setFocusable(false);
            popWnd.setOutsideTouchable(false);
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

//        popWnd.showAsDropDown(etNeedFenlei, 0, 0);
            popWnd.showAtLocation(AddGoodsBaseInfoActivity.this.findViewById(R.id.et_need_fenlei),
                    Gravity.BOTTOM, 0, 0);

        }

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
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void getData() {
        Log.e("aaa", "--商品详情参数--mer_id-->" + mer_id);
        OkHttpUtils.post().url(NetConfig.goodsdetail_url)
                .addParams("goods_id", String.valueOf(mer_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--商品详情返回--error->" + e.getMessage());
                        Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "--商品详情返回--->" + response);
                        if (response != null) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    Gson gson = new Gson();
                                    Shangpxiangqing shangpxiangqing = gson.fromJson(response, Shangpxiangqing.class);

                                    etNeedName.setText(shangpxiangqing.getData().getGoods_name() != null ? shangpxiangqing.getData().getGoods_name() : "");
                                    etNeedGuige.setText(shangpxiangqing.getData().getSpecification() != null ? "" + shangpxiangqing.getData().getSpecification() : "");
                                    etNeedMoney.setText(shangpxiangqing.getData().getCurrent_price() != null ? "" + shangpxiangqing.getData().getCurrent_price() : "");
                                    etNeedChundu.setText(shangpxiangqing.getData().getGoods_purity() != null ? shangpxiangqing.getData().getGoods_purity() : "");
                                    etNeedTimequest.setText(shangpxiangqing.getData().getGoods_deliver() != null ? shangpxiangqing.getData().getGoods_deliver() : "");
                                    etNeedNumquest.setText(shangpxiangqing.getData().getMolecular_weight() != null ? shangpxiangqing.getData().getMolecular_weight() : "");
                                    etNeedPackage.setText(shangpxiangqing.getData().getPackage_opt() != null ? shangpxiangqing.getData().getPackage_opt() : "");
                                    etNeedPackrequest.setText(shangpxiangqing.getData().getTransport_opt() != null ? shangpxiangqing.getData().getTransport_opt() : "");
                                    chanpintupian = shangpxiangqing.getData().getProduct_picture();
                                    yingyongfanan = shangpxiangqing.getData().getApplication_scheme();
                                    shengchanzhuangtai = shangpxiangqing.getData().getProduction_state();

                                    String productState = shangpxiangqing.getData().getProduction_state();
                                    String application_scheme = shangpxiangqing.getData().getApplication_scheme();
                                    String picture = shangpxiangqing.getData().getProduct_picture();
                                    if (!TextUtils.isEmpty(picture)) {
                                        Glide.with(AddGoodsBaseInfoActivity.this).load(NetConfig.baseurl + picture).into(ivNeedSccptp);
                                    } else {
                                        ivNeedSccptp.setImageResource(R.drawable.goodsone);
                                    }
                                    if (!TextUtils.isEmpty(application_scheme)) {
                                        Glide.with(AddGoodsBaseInfoActivity.this).load(NetConfig.baseurl + picture).into(tvNeedScyyfa);
                                    } else {
                                        tvNeedScyyfa.setImageResource(R.drawable.goodsone);
                                    }
                                    if (!TextUtils.isEmpty(productState)) {
                                        Glide.with(AddGoodsBaseInfoActivity.this).load(NetConfig.baseurl + picture).into(ivNeedSccczt);
                                    } else {
                                        ivNeedSccczt.setImageResource(R.drawable.goodsone);
                                    }

                                } else {
                                    Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddGoodsBaseInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
