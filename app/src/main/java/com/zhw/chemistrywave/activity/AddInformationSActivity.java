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
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.MainActivity;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SelectImageUtils;
import com.zhw.chemistrywave.view.SelfDialog;
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

public class AddInformationSActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_contact_person)
    EditText etContactPerson;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(R.id.iv_add_img)
    ImageView ivAddImg;
    @BindView(R.id.iv_add_others)
    ImageView ivAddOthers;
    private String user_id;

    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;

    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    private String tag;
    private String businessLicense,others;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    private SelfDialog selfDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information_s);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");
    }

    @OnClick({R.id.rl_back, R.id.rl_add_img, R.id.rl_add_others, R.id.btn_register, R.id.tv_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add_img:
                tag = "0";
                getHeadImage();
                break;
            case R.id.rl_add_others:
                tag = "1";
                getHeadImage();
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_to_login:
                startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    private void register() {


        String company_name = etName.getText().toString().trim();
        String company_address = etAddress.getText().toString().trim();
        String contact_person = etContactPerson.getText().toString().trim();
        String contact_phone = etContactPhone.getText().toString().trim();
        
        if (TextUtils.isEmpty(company_name)){
            Toast.makeText(this, "please enter company name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(company_address)){
            Toast.makeText(this, "Please enter company address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contact_person)){
            Toast.makeText(this, "Please enter contact name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(contact_phone)){
            Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(businessLicense)){
            Toast.makeText(this, "The Business license upload failed", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(others)){
            Toast.makeText(this, "the attachment upload failed", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("user_id",user_id);
        params.put("company_name",company_name);
        params.put("company_address",company_address);
        params.put("contact_name",contact_person);
        params.put("contact_phone",contact_phone);
        params.put("license_src",businessLicense);
        params.put("attachment",others);
        params.put("user_type","com");
        params.put("user_state","normal");

        OkHttpUtils.post().url(NetConfig.register2)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddInformationSActivity.java:163)<---->"+e.getMessage());
                        Toast.makeText(AddInformationSActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddInformationSActivity.java:169)<---->"+response);
                        if (TextUtils.isEmpty(response)){

                            Toast.makeText(AddInformationSActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                int code = jsonObject.getInt("code");
                                if (code==0){
//                                    startActivity(new Intent(AddInformationSActivity.this,LoginActivity.class));
//                                    finish();
                                    showChooseStart();
                                }
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(AddInformationSActivity.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    //选择跳转位置
    private void showChooseStart() {
        selfDialog = new SelfDialog(AddInformationSActivity.this);
        selfDialog.setTitle("Do you want to open a shop");
        selfDialog.setYesOnclickListener("Confirm", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                startActivity(new Intent(AddInformationSActivity.this, MainActivity.class).putExtra("index",3));
                finish();
                selfDialog.dismiss();

            }
        });
        selfDialog.setNoOnclickListener("Cancel", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                startActivity(new Intent(AddInformationSActivity.this, MainActivity.class).putExtra("index",0));
                finish();
                selfDialog.dismiss();
            }
        });
        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                backgroundAlpha(1f);
            }
        });
        backgroundAlpha(0.6f);
        selfDialog.show();
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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

                switch (tag) {
                    case "0":
                        try {
                            bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                            ivAddImg.setImageBitmap(bitmap1);
                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationSActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            saveFile(new File(SelectImageUtils.getPath(this, outputUri)), tag);
                            progressHUD.dismiss();
                            // uploadHeadToServer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "1":
                        try {
                            bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                            ivAddOthers.setImageBitmap(bitmap2);
                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationSActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            saveFile(new File(SelectImageUtils.getPath(this, outputUri)), tag);
                            progressHUD.dismiss();
                            // uploadHeadToServer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                }

            }
        }
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

    private void saveFile(File file, final String tag) {
        OkHttpUtils.post().url(NetConfig.SAVE_FILE)
                .addFile("file", "1.jpg", file)
                .addParams("user_id", user_id)
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
                                    switch (tag) {
                                        case "0":
                                            businessLicense = data;
                                            break;
                                        case "1":
                                            others = data;
                                            break;
                                    }
//                                    saveUser(data);
                                } else {
                                    Toast.makeText(AddInformationSActivity.this, "this file upload failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(AddInformationSActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

    //调用系统的剪裁处理图片并保存至imageUri中
    public static void cropImageUri(Activity activity, Uri orgUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 400);
        intent.putExtra("outputY", 400);
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }
}
