package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
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
import com.zhw.chemistrywave.utils.FileUtilcll;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddInformationActivity extends BaseActivity {


    private static final int CAMERA_RESULT_CODE = 1;
    private static final int ALBUM_RESULT_CODE = 2;
    private static final int CROP_RESULT_CODE = 3;
    private static final int REQUEST_PERMISSIONS = 4;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_img1)
    ImageView ivImg1;
    @BindView(R.id.iv_img2)
    ImageView ivImg2;
    @BindView(R.id.iv_img3)
    ImageView ivImg3;
    @BindView(R.id.iv_add_others)
    ImageView ivAddOthers;
    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private String IdCardFront, IdCardBack, IdCardByHand, others;
    private String tag = "";
    private String user_id;
    private SelfDialog selfDialog;
    private File file;
    private Uri imgUriOri;
    private String imgPathOri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_information);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        user_id = extras.getString("user_id");
        initPermission();

    }

    @OnClick({R.id.rl_back, R.id.rl_add_imgs1, R.id.rl_add_imgs2, R.id.rl_add_imgs3, R.id.rl_add_others, R.id.btn_register, R.id.tv_to_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_add_imgs1:
                tag = "0";
                getHeadImage();
                break;
            case R.id.rl_add_imgs2:
                tag = "1";
                getHeadImage();
                break;
            case R.id.rl_add_imgs3:
                tag = "2";
                getHeadImage();
                break;
            case R.id.rl_add_others:
                tag = "3";
                getHeadImage();
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_to_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    /**
     * 添加头像
     */
    private void getHeadImage() {
        AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("Choose picture")
                .setItems(new String[]{"Photos", "Camera"}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // 这里item是根据选择的方式，
                        if (item == 0) {
                            openSysAlbum();
                        } else {
                            openSysCamera();
                        }
                    }
                }).create();
        dlg.show();
    }

    /**
     * 注册
     */
    private void register() {
        String name = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter contact number", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(IdCardFront)) {
            Toast.makeText(this, "Identity card front upload failed", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(IdCardBack)) {
            Toast.makeText(this, "Identity card back upload failed", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(IdCardByHand)) {
            Toast.makeText(this, "the Identity card by hand upload failed", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(others)) {
            Toast.makeText(this, "The attachment upload failed", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> paramas = new HashMap<>();
        paramas.put("user_id", user_id);
        paramas.put("real_name", name);
        paramas.put("user_state", "normal");
        paramas.put("cardo_src", IdCardFront);
        paramas.put("cardn_src", IdCardBack);
        paramas.put("cardh_src", IdCardByHand);
        paramas.put("contact_phone", phone);
        paramas.put("attachment", others);
        paramas.put("user_type", "per");

        Log.e("aaa",
                "(AddInformationActivity.java:326)<--注册的参数-->" + paramas.toString());
        Log.e("aaa",
                "(AddInformationActivity.java:328)<--注册的地址-->" + NetConfig.register);

        OkHttpUtils.post()
                .url(NetConfig.register2)
                .params(paramas)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(AddInformationActivity.java:338)<--register error-->" + e.getMessage());
                        Toast.makeText(AddInformationActivity.this, "network error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(AddInformationActivity.java:344)<--register success->" + response);

                        if (TextUtils.isEmpty(response)) {
                            Toast.makeText(AddInformationActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String msg = jsonObject.getString("msg");
                                Toast.makeText(AddInformationActivity.this, msg, Toast.LENGTH_SHORT).show();
                                if (0 == jsonObject.getInt("code")) {

                                    showChooseStart();
//                                    startActivity(new Intent(AddInformationActivity.this,LoginActivity.class));
//                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 打开系统相册
     */
    private void openSysAlbum() {
        Intent albumIntent = new Intent(Intent.ACTION_PICK);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, ALBUM_RESULT_CODE);
    }

    /**
     * 打开系统相机
     */
    private void openSysCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
//                Uri.fromFile(new File(Environment.getExternalStorageDirectory(), imgName)));
        try {
            file = createOriImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                imgUriOri = Uri.fromFile(file);
            } else {
                imgUriOri = FileProvider.getUriForFile(this, getPackageName() + ".provider", file);
            }
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgUriOri);
            startActivityForResult(cameraIntent, CAMERA_RESULT_CODE);
        }
    }

    //选择跳转位置
    private void showChooseStart() {
        selfDialog = new SelfDialog(AddInformationActivity.this);
        selfDialog.setTitle("Do you want to open a shop");
        selfDialog.setYesOnclickListener("Confirm", new SelfDialog.onYesOnclickListener() {
            @Override
            public void onYesClick() {
                startActivity(new Intent(AddInformationActivity.this, MainActivity.class).putExtra("index", 3));
                finish();
                selfDialog.dismiss();

            }
        });
        selfDialog.setNoOnclickListener("Cancel", new SelfDialog.onNoOnclickListener() {
            @Override
            public void onNoClick() {
                startActivity(new Intent(AddInformationActivity.this, MainActivity.class).putExtra("index", 0));
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
     * 初始化相机相关权限
     * 适配6.0+手机的运行时权限
     */
    private void initPermission() {
        String[] permissions = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        };
        //检查权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 之前拒绝了权限，但没有点击 不再询问 这个时候让它继续请求权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "用户曾拒绝打开相机权限", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
            } else {
                // 注册相机权限
                ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
            }
        }
    }

    /**
     * 创建原图像保存的文件
     *
     * @return
     * @throws IOException
     */
    private File createOriImageFile() throws IOException {
        String imgNameOri = "HomePic_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File pictureDirOri = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/OriPicture");
        if (!pictureDirOri.exists()) {
            pictureDirOri.mkdirs();
        }
        File image = File.createTempFile(imgNameOri, /* prefix */ ".jpg", /* suffix */ pictureDirOri /* directory */);
        imgPathOri = image.getAbsolutePath();
        return image;
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

        switch (requestCode) {
            case CAMERA_RESULT_CODE:
                cropPic(getImageContentUri(file));
                break;
            case CROP_RESULT_CODE:
                // 裁剪时,这样设置 cropIntent.putExtra("return-data", true); 处理方案如下
                if (data != null) {
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap bitmap = bundle.getParcelable("data");
//
                        // 把裁剪后的图片保存至本地 返回路径
                        String urlpath = FileUtilcll.saveFile(this, "crop.jpg", bitmap);
                        Log.e("aaa", "(MainActivity.java:84)<---->" + "裁剪图片地址->" + urlpath);
                        File file = new File(urlpath);
//                        saveFile(file);

                        // 裁剪时,这样设置 cropIntent.putExtra("return-data", false); 处理方案如下
                        //                try {
                        //                    ivHead.setImageBitmap(BitmapFactory.decodeStream(
                        // getActivity().getContentResolver().openInputStream(imageUri)));
                        //                } catch (FileNotFoundException e) {
                        //                    e.printStackTrace();
                        //                }

                        switch (tag) {
                            case "0":
                                try {
//                                    bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
//                                    ivImg1.setImageBitmap(bitmap1);
                                    ivImg1.setImageBitmap(bitmap);
                                    ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationActivity.this);
                                    progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                                    progressHUD.show();
                                    saveFile(file, tag);
                                    progressHUD.dismiss();
                                    // uploadHeadToServer();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "1":
                                try {
//                                    bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                                    ivImg2.setImageBitmap(bitmap);
                                    ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationActivity.this);
                                    progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                                    progressHUD.show();
                                    saveFile(file, tag);
                                    progressHUD.dismiss();
                                    // uploadHeadToServer();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "2":
                                try {
//                                    bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                                    ivImg3.setImageBitmap(bitmap);
                                    ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationActivity.this);
                                    progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                                    progressHUD.show();
                                    saveFile(file, tag);
                                    progressHUD.dismiss();
                                    // uploadHeadToServer();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case "3":
                                try {
//                                    bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                                    ivAddOthers.setImageBitmap(bitmap);
                                    ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddInformationActivity.this);
                                    progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                                    progressHUD.show();
                                    saveFile(file, tag);
                                    progressHUD.dismiss();
                                    // uploadHeadToServer();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                }
                break;
            case ALBUM_RESULT_CODE:
                // 相册
                cropPic(data.getData());
                break;
        }

    }

    /**
     * 裁剪图片
     *
     * @param data
     */
    private void cropPic(Uri data) {
        if (data == null) {
            return;
        }
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(data, "image/*");
        // 开启裁剪：打开的Intent所显示的View可裁剪
        cropIntent.putExtra("crop", "true");
        // 裁剪宽高比
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        // 裁剪输出大小
        cropIntent.putExtra("outputX", 300);
        cropIntent.putExtra("outputY", 300);
        cropIntent.putExtra("scale", true);
        /**
         return -data
         * 这个属性决定我们在 onActivityResult 中接收到的是什么数据，
         *如果设置为true 那么data将会返回一个bitmap
         *如果设置为false，则会将图片保存到本地并将对应的uri返回，当然这个uri得有我们自己设定。
         *系统裁剪完成后将会将裁剪完成的图片保存在我们所这设定这个uri地址上。我们只需要在裁剪完成后直接调用该uri来设置图片，就可以了。
         */
        cropIntent.putExtra("return-data", true);
        // 当 return-data 为 false 的时候需要设置这句
        //        cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        // 图片输出格式
        //        cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 头像识别 会启动系统的拍照时人脸识别
        //        cropIntent.putExtra("noFaceDetection", true);
        startActivityForResult(cropIntent, CROP_RESULT_CODE);
    }

    /**
     * 7.0以上获取裁剪 Uri
     *
     * @param imageFile
     * @return
     */
    private Uri getImageContentUri(File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ", new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
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
                                            IdCardFront = data;
                                            break;
                                        case "1":
                                            IdCardBack = data;
                                            break;
                                        case "2":
                                            IdCardByHand = data;
                                            break;
                                        case "3":
                                            others = data;
                                            break;
                                    }
//                                    saveUser(data);
                                } else {
                                    Toast.makeText(AddInformationActivity.this, "Uploading file failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

}
