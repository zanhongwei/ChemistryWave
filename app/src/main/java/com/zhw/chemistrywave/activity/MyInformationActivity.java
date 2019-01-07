package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.utils.FileUtilcll;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SelectImageUtils;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;

import static com.zhw.chemistrywave.utils.MyUtils.getUser;


public class MyInformationActivity extends BaseActivity {


    private static final int CAMERA_RESULT_CODE = 1;
    private static final int CROP_RESULT_CODE = 2;
    private static final int ALBUM_RESULT_CODE = 3;
    private static final int REQUEST_PERMISSIONS = 8;


    private static final int CHANGE_NICKNAME_CODE = 4;
    private static final int CHANGE_PHONE_CODE = 5;

    private static final int ChANGE_CONTACT_NAME = 6;
    private static final int CHANGE_PASSWORD_CODE = 7;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.civ_user_icon)
    CircleImageView civUserIcon;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_register_phone)
    TextView tvRegisterPhone;
    @BindView(R.id.tv_contact_name)
    TextView tvContactName;
    @BindView(R.id.tv_register_mail)
    TextView tvRegisterMail;
    @BindView(R.id.tv_mail_state)
    TextView tvMailState;
    private String imgName = "my_head";
    private File tempFile, file;
    private Uri imgUriOri;
    private String imgPathOri;
    private String userName;
    private String userPhoto;
    private String userPhone;
    private String contactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_information);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("My Information");

        getUserInfo();
    }

    private void getUserInfo() {

        Log.e("aaa",
                "(MyInformationActivity.java:89)<--个人信息详情的url-->" + NetConfig.user_detail);
        Log.e("aaa",
                "(MyInformationActivity.java:91)<--个人信息详情的参数-->" + getUser().getUser_id());

        OkHttpUtils.post()
                .url(NetConfig.user_detail)
                .addParams("user_id", getUser().getUser_id())
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(MyInformationActivity.java:96)<---->" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(MyInformationActivity.java:102)<---->" + response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                JSONObject result = jsonObject.getJSONObject("result");
                                userName = result.getString("user_name") == null ? "" : result.getString("user_name");//用户昵称
                                userPhoto = result.getString("user_photo") == null ? "" : result.getString("user_photo");//用户头像
                                userPhone = result.getString("user_phone") == null ? "" : result.getString("user_phone");//注册手机
                                contactName = result.getString("contact_name") == null ? "" : result.getString("contact_name");

                                if ("null".equals(userPhoto) || TextUtils.isEmpty(userPhoto)) {
                                    civUserIcon.setImageResource(R.drawable.default_userinfo);
                                } else {
                                    ImageLoader.getInstance().displayImage(NetConfig.baseurl + result.getString("user_photo"), civUserIcon);
                                }
                                tvUserName.setText(userName);
                                tvContactName.setText(contactName);
                                if (userPhone.length() == 11) {
                                    StringBuilder sb = new StringBuilder(userPhone);
                                    StringBuilder replace = sb.replace(3, 7, "****");
                                    tvRegisterPhone.setText(replace.toString());
                                } else {
                                    tvRegisterPhone.setText(userPhone);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                        civUserIcon.setImageBitmap(bitmap);
                        // 把裁剪后的图片保存至本地 返回路径
                        String urlpath = FileUtilcll.saveFile(this, "crop.jpg", bitmap);
                        Log.e("aaa", "(MainActivity.java:84)<---->" + "裁剪图片地址->" + urlpath);
                        File file = new File(urlpath);
                        saveFile(file);

                        // 裁剪时,这样设置 cropIntent.putExtra("return-data", false); 处理方案如下
                        //                try {
                        //                    ivHead.setImageBitmap(BitmapFactory.decodeStream(
                        // getActivity().getContentResolver().openInputStream(imageUri)));
                        //                } catch (FileNotFoundException e) {
                        //                    e.printStackTrace();
                        //                }
                    }
                }
                break;
            case ALBUM_RESULT_CODE:
                // 相册
                cropPic(data.getData());
                break;
                //修改昵称
            case CHANGE_NICKNAME_CODE:

                tvUserName.setText(data.getStringExtra("nickName"));
                break;
            case CHANGE_PHONE_CODE:
//                data.getStringExtra("nickName")
//                if (userPhone.length() == 11) {
//                    StringBuilder sb = new StringBuilder(userPhone);
//                    StringBuilder replace = sb.replace(3, 7, "****");
//                    tvRegisterPhone.setText(replace.toString());
//                } else {
//                    tvRegisterPhone.setText(userPhone);
//                }
                break;
            case ChANGE_CONTACT_NAME:
                tvContactName.setText(data.getStringExtra("contact"));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //成功
//                    Toast.makeText(this, "用户授权相机权限", Toast.LENGTH_SHORT).show();
                } else {
                    // 勾选了不再询问
                    Toast.makeText(this, "Please turn on camera permissions", Toast.LENGTH_SHORT).show();
                    /**
                     * 跳转到 APP 详情的权限设置页
                     * 可根据自己的需求定制对话框，点击某个按钮在执行下面的代码
                     */
//                    Intent intent = getAppDetailSettingIntent(MainActivity.this);
//                    startActivity(intent);
                }
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

    /**
     * 上传文件
     *
     * @param file
     */
    private void saveFile(File file) {
        OkHttpUtils.post().url(NetConfig.SAVE_FILE)
                .addFile("file", "1.jpg", file)
                .addParams("user_id", getUser().getUser_id())
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
                                    saveUser(data);
                                } else {
                                    Toast.makeText(MyInformationActivity.this, "The file upload failed", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    /**
     * 修改用户信息
     */
    private void saveUser(String userphoto) {

        Map<String, String> map = new HashMap<>();
        map.put("user_id", getUser().getUser_id());
        map.put("user_photo", userphoto);
        OkHttpUtils.post().url(NetConfig.user_update)
                .params(map)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "----修改用户信息返回-error--->" + e.getCause() + e.getMessage());
                        Toast.makeText(MyInformationActivity.this, "network error", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa", "----修改用户信息返回---->" + response);
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject JO1 = new JSONObject(response);
                                int code = JO1.getInt("code");
                                if (code == 0) {
                                    Toast.makeText(MyInformationActivity.this, "save successful", Toast.LENGTH_SHORT).show();
//                                    getUserInfo();
//                                    finish();
                                } else {
                                    Toast.makeText(MyInformationActivity.this, "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.ll_user_icon, R.id.ll_user_name, R.id.ll_register_phone, R.id.ll_register_mail, R.id.ll_address, R.id.ll_change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.ll_user_icon:
                getHeadImage();
                break;
            case R.id.ll_user_name:
                //  2018/4/11 修改昵称
                startActivityForResult(new Intent(MyInformationActivity.this, ChangeNickNameActivity.class)
                        .putExtra("nickName", userName)
                        .putExtra("flag", "name"), CHANGE_NICKNAME_CODE);
                break;
            case R.id.ll_register_phone:
                //2018/4/11 修改注册电话
                startActivity(new Intent(MyInformationActivity.this, ChangePhoneActivity.class)
                        .putExtra("phone", userPhone));
                break;
            case R.id.ll_register_mail:
                // TODO: 2018/4/11 修改注册邮箱
                break;
            case R.id.ll_address:
                // TODO: 2018/4/11 修改送货地址
                startActivity(new Intent(MyInformationActivity.this, MyAddressActivity.class));
                break;
            case R.id.ll_change_password:
                // TODO: 2018/4/11 修改联系人
                startActivityForResult(new Intent(MyInformationActivity.this, ChangeNickNameActivity.class)
                        .putExtra("contact", contactName)
                        .putExtra("flag", "contact"), ChANGE_CONTACT_NAME);
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

}
