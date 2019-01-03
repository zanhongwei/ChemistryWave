package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.ShopInformation;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
import com.zhw.chemistrywave.utils.SelectImageUtils;
import com.zhw.chemistrywave.utils.ToastUtil;
import com.zhy.base.fileprovider.FileProvider7;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddOpenShopInfoActivity extends BaseActivity {

    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    @BindView(R.id.ll_id_number)
    LinearLayout llIdNumber;
    @BindView(R.id.ll_gerendizhi)
    LinearLayout llGerendizhi;
    @BindView(R.id.et_gg_name)
    EditText etGgName;
    @BindView(R.id.ll_gg_name)
    LinearLayout llGgName;
    @BindView(R.id.et_gg_address)
    EditText etGgAddress;
    @BindView(R.id.ll_gg_dizhi)
    LinearLayout llGgDizhi;
    @BindView(R.id.ll_yyzz)
    LinearLayout llYyzz;
    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private Bitmap bitmap1, bitmap2;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;

    private String flag = "";
    private String imgStrYYZZ;
    private String imgStrLogo;

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.et_shop_contact)
    EditText etShopContact;
    @BindView(R.id.et_shop_phone)
    EditText etShopPhone;
    @BindView(R.id.et_id_number)
    EditText etIdNumber;
    @BindView(R.id.et_shop_address)
    EditText etShopAddress;
    @BindView(R.id.iv_add_zz)
    ImageView ivAddZz;
    @BindView(R.id.et_shop_name)
    EditText etShopName;
    @BindView(R.id.iv_add_logo)
    ImageView ivAddLogo;

    //声明一个标志  tag = com  为公司  per为个人
    private String tag = "per";
    private String flag1;
    private String url;
    private int shop_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_open_shop_info);
        ButterKnife.bind(this);

        if (TextUtils.isEmpty(getIntent().getStringExtra("flag"))){
            flag1 = "add";
        }else {
            getShopDetail();
            flag1 = "edit";
        }

        initView();
    }

    private void getShopDetail() {
        OkHttpUtils
                .post()
                .url(NetConfig.SHOP_DETAIL)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(VertifyOrderActivity.java:211)<---->" + e.getMessage());
                        ToastUtil.showToastShort(AddOpenShopInfoActivity.this, "Network error");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(VertifyOrderActivity.java:217)<---->" + response);

                        if (TextUtils.isEmpty(response)) {
                            ToastUtil.showToastShort(AddOpenShopInfoActivity.this, "Network error");
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    ShopInformation shopDetail = new Gson().fromJson(response, ShopInformation.class);
                                    setViewValue(shopDetail);
                                } else if (code == 500) {
                                    startActivity(new Intent(AddOpenShopInfoActivity.this, OpenAShopActivity.class));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    /**
     * 重新填写页面的原始数据
     * @param shopDetail
     */
    private void setViewValue(ShopInformation shopDetail) {

//        params.put("shop_name",shopName);
//        params.put("contact_name",contactPerson);
//        params.put("contact_mobile",shopPhone);
//        params.put("com_logo",imgStrLogo);
        shop_id = shopDetail.getData().getShop_id();
        etShopName.setText(shopDetail.getData().getShop_name());
        etShopContact.setText(shopDetail.getData().getContact_name());
        etShopPhone.setText(shopDetail.getData().getContact_mobile());
        Glide.with(this).load(NetConfig.baseurl+shopDetail.getData().getCom_logo()).apply(MyApplication.options).into(ivAddLogo);
        imgStrLogo = shopDetail.getData().getCom_logo();

        if (tag.equals("per")){
//            params.put("per_card",grIdNumber);
//            params.put("per_addr",grAddress);
            etIdNumber.setText(shopDetail.getData().getPer_card());
            etShopAddress.setText(shopDetail.getData().getPer_addr());
        }else {

//            params.put("com_addr",ggDizhi);
//            params.put("com_name",ggName);
//            params.put("com_license",imgStrYYZZ);

            etGgAddress.setText(shopDetail.getData().getCom_addr());
            etGgName.setText(shopDetail.getData().getCom_name());
            Glide.with(this).load(NetConfig.baseurl+shopDetail.getData().getCom_logo()).apply(MyApplication.options).into(ivAddZz);
            imgStrYYZZ = shopDetail.getData().getCom_license();
        }

    }

    private void initView() {
        tvTitlebarCenter.setText("Apply A Store");
        /**
         * 实例化View视图
         *
         * 如果是个人供货商  显示身份证号码  个人地址
         * 如果是公司供货商  显示的是公司名称、公司地址、营业执照
         *
         * 暂时默认为个人供货商
         */

        String user_state = (String) SPUtils.get(this, "user_state", "1");

        if (user_state.equals("1")){
            tag = "per";
            llGerendizhi.setVisibility(View.VISIBLE);
            llGgDizhi.setVisibility(View.GONE);
            llGgName.setVisibility(View.GONE);
            llIdNumber.setVisibility(View.VISIBLE);
            llYyzz.setVisibility(View.GONE);
        }else {
            tag = "com";
            llGerendizhi.setVisibility(View.GONE);
            llGgDizhi.setVisibility(View.VISIBLE);
            llGgName.setVisibility(View.VISIBLE);
            llIdNumber.setVisibility(View.GONE);
            llYyzz.setVisibility(View.VISIBLE);
        }

    }

    public void addLogo(View view) {
        // TODO: 2018/5/29 上传logo
        flag = "1";
        getHeadImage();
    }

    public void addZhizhao(View view) {
        // TODO: 2018/5/29 上传营业执照
        flag = "0";
        getHeadImage();
    }

    public void commitApply(View view) {
        String shopName = etShopName.getText().toString().trim();//店铺名称
        String shopPhone = etShopPhone.getText().toString().trim();//联系人电话
        String contactPerson = etShopContact.getText().toString().trim();//联系人姓名
        String grAddress = etShopAddress.getText().toString().trim();//个人供货商的地址
        String ggDizhi = etGgAddress.getText().toString().trim();//公司供货商的地址
        String grIdNumber = etIdNumber.getText().toString().trim();//个人供货商的身份证
        String ggName = etGgName.getText().toString().trim();//公司供货商的公司名称

        HashMap<String, String> params = new HashMap<>();

        if (TextUtils.isEmpty(shopName)){
            Toast.makeText(this, "please fill in shop name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(shopPhone)){
            Toast.makeText(this, "please fill in contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contactPerson)){
            Toast.makeText(this, "please fill in contact name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(imgStrLogo)){
            Toast.makeText(this, "this logo upload failed", Toast.LENGTH_SHORT).show();
            return;
        }

        params.put("user_id",MyUtils.getUser().getUser_id());
        params.put("shop_name",shopName);
        params.put("contact_name",contactPerson);
        params.put("contact_mobile",shopPhone);
        params.put("com_logo",imgStrLogo);

//        params.put("")
        if (tag.equals("com")){
            if (TextUtils.isEmpty(ggDizhi)){
                Toast.makeText(this, "Please fill in company address", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(ggName)){
                Toast.makeText(this, "Please fill in company name", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(imgStrYYZZ)){
                Toast.makeText(this, "this Business license upload failed", Toast.LENGTH_SHORT).show();
                return;
            }

            params.put("com_addr",ggDizhi);
            params.put("com_name",ggName);
            params.put("com_license",imgStrYYZZ);
        }else {
            if (TextUtils.isEmpty(grIdNumber)){
                Toast.makeText(this, "Please fill in ID Card NO.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(grAddress)){
                Toast.makeText(this, "Please fill in address", Toast.LENGTH_SHORT).show();
                return;
            }

            params.put("per_card",grIdNumber);
            params.put("per_addr",grAddress);
        }

        if (flag1.equals("add")){
            url = NetConfig.woyaokaidian_url;
        }else {
            url =NetConfig.woyaokaidian_urls;
            params.put("shop_state","1");
            params.put("shop_id",shop_id+"");
        }

        OkHttpUtils.post()
                .url(url)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddOpenShopInfoActivity.java:227)<---->"+e.getMessage());
                        Toast.makeText(AddOpenShopInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddOpenShopInfoActivity.java:233)<---->"+response);

                        if (TextUtils.isEmpty(response)){
                            Toast.makeText(AddOpenShopInfoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    startActivity(new Intent(AddOpenShopInfoActivity.this,ApplyResultActivity.class));
                                    finish();
                                }
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(AddOpenShopInfoActivity.this,msg,Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });



    }

    @OnClick(R.id.rl_titlebar_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

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
                if ("0".equals(flag)) {
                    try {
                        bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivAddZz.setImageBitmap(bitmap1);
                        ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddOpenShopInfoActivity.this);
                        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                        progressHUD.show();
                        saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "0");
                        progressHUD.dismiss();
                        // uploadHeadToServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivAddLogo.setImageBitmap(bitmap2);
                        ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddOpenShopInfoActivity.this);
                        progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                        progressHUD.show();
                        saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "1");
                        progressHUD.dismiss();
                        // uploadHeadToServer();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    private void saveFile(File file, final String s) {

        OkHttpUtils.post().url(NetConfig.SAVE_FILE)
                .addFile("file", "1.jpg", file)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---上传文件返回--error-->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "---上传文件返回---->" + response);
                        /**
                         * {"code":0,"data":"upload/1/20171223120941476.jpg"}
                         */
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jo1 = new JSONObject(response);
                                int code = jo1.getInt("code");
                                if (code == 0) {
                                    String data = jo1.getString("data");
                                    if ("0".equals(s)) {
                                        imgStrYYZZ = data;
                                    } else if ("1".equals(s)) {
                                        imgStrLogo = data;
                                    }
                                } else {
                                    Toast.makeText(AddOpenShopInfoActivity.this, "the file upload failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

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
                Toast.makeText(this, "open this camera permission in settings", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //调用系统的剪裁处理图片并保存至imageUri中
    public static void cropImageUri(Activity activity, Uri orgUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
