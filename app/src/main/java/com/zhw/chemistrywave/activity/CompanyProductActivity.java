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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class CompanyProductActivity extends BaseActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.iv_product)
    ImageView ivProduct;

    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;

    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private Bitmap bitmap1, bitmap2;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    private String productStr = "";
    private String shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_product);
        ButterKnife.bind(this);

        tvTitlebarCenter.setText("Production Photos");
        tvTitlebarRight.setText("Complete");
        tvTitlebarRight.setVisibility(View.VISIBLE);

        shopId = getIntent().getStringExtra("shopId");

        getData();
    }


    private void getData() {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page","1");
            jsonObject.put("limit","100");
            jsonObject.put("shop_id",shopId);
            jsonObject.put("env_type","produce");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OkHttpUtils.post()
                .url(NetConfig.getComEnvironment)
                .addParams("jsonStr",jsonObject.toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(CompanyEnvironmentActivity.java:105)<--查询生产照片失败返回-->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(CompanyEnvironmentActivity.java:111)<--查询生产照片成功返回-->"+response);

                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_titlebar_right, R.id.btn_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_titlebar_right:
                commit();
                break;
            case R.id.btn_add:
                getHeadImage();
                break;
        }
    }

    private void commit() {
        OkHttpUtils
                .post()
                .url(NetConfig.tianjiagongsi)
                .addParams("environment_src",productStr)
                .addParams("env_type","office")
                .addParams("shop_id",shopId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(CompanyEnvironmentActivity.java:108)<--上传生产环境 失败返回-->"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(CompanyEnvironmentActivity.java:114)<--上传生产环境 成功返回-->"+response);
                        if (TextUtils.isEmpty(response)){
                            ToastUtil.showToastShort(CompanyProductActivity.this,"Network error");
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String msg = jsonObject.getString("msg");
                                ToastUtil.showToastShort(CompanyProductActivity.this,msg);
                                int code = jsonObject.getInt("code");
                                if (code==0){
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });
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
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                    ivProduct.setImageBitmap(bitmap1);
                    ZProgressHUD progressHUD = ZProgressHUD.getInstance(CompanyProductActivity.this);
                    progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                    progressHUD.show();
                    saveFile(new File(SelectImageUtils.getPath(this, outputUri)), "0");
                    progressHUD.dismiss();
                    // uploadHeadToServer();
                } catch (IOException e) {
                    e.printStackTrace();
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
                                    productStr = data;
                                } else {
                                    Toast.makeText(CompanyProductActivity.this, "the file upload failed", Toast.LENGTH_SHORT).show();
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
