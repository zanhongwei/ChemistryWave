package com.zhw.chemistrywave.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
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
import android.provider.DocumentsContract;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.User;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SPUtils;
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

public class RealNameAuthenticationActivity extends BaseActivity {


    @BindView(R.id.rl_titlebar_back)
    RelativeLayout rlTitlebarBack;
    @BindView(R.id.iv_titlebar_line)
    ImageView ivTitlebarLine;
    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.rl_titlebar_search)
    RelativeLayout rlTitlebarSearch;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_id_num)
    EditText etIdNum;
    @BindView(R.id.et_id_num_s)
    EditText etIdNumS;
    @BindView(R.id.iv_id_card)
    ImageView ivIdCard;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.rl1)
    RelativeLayout rl1;
    @BindView(R.id.iv_id_card2)
    ImageView ivIdCard2;
    @BindView(R.id.tv_add2)
    TextView tvAdd2;
    @BindView(R.id.rl2)
    RelativeLayout rl2;
    @BindView(R.id.tv_save_infor)
    TextView tvSaveInfor;
    @BindView(R.id.ll)
    LinearLayout ll;
    private User user;
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
    private File opposite_pic;
    private File negative_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name_authentication);
        ButterKnife.bind(this);
        user = MyUtils.Touser((String) SPUtils.get("user", ""));
        initView();

    }

    private void initView() {
        tvTitlebarCenter.setText("实名认证");
        OkHttpUtils.post()
                .url(NetConfig.namecongifdetail)
                .addParams("user_id", user.getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa",
                                "(RealNameAuthenticationActivity.java:102)" + e.toString());

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaa",
                                "(RealNameAuthenticationActivity.java:107)" + response);
                        if (response.contains("failure")) {

                        } else {
                            try {
                                JSONObject job = new JSONObject(response);
                                JSONObject data = job.getJSONObject("data");
                                Log.e("aaa",
                                        "(RealNameAuthenticationActivity.java:117)" + job.getJSONObject("data").getString("opposite_pic"));
                                etName.setText(data.getString("real_name"));
                                etIdNum.setText(data.getString("id_card"));
                                etIdNumS.setText(data.getString("id_card"));
                                tvSaveInfor.setVisibility(View.INVISIBLE);
                                Glide.with(RealNameAuthenticationActivity.this)
                                        .load(NetConfig.baseurl + data.getString("opposite_pic"))
                                        .into(ivIdCard);
                                Glide.with(RealNameAuthenticationActivity.this)
                                        .load(NetConfig.baseurl + data.getString("negative_pic"))
                                        .into(ivIdCard2);
                                tvAdd.setVisibility(View.INVISIBLE);
                                tvAdd2.setVisibility(View.INVISIBLE);
                                rl1.setFocusable(false);
                                rl1.setClickable(false);
                                rl2.setFocusable(false);
                                rl2.setClickable(false);
                                MyUtils.disableSubControls(ll);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.rl1, R.id.tv_save_infor, R.id.rl2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.rl1:
                tag = "1";
                getHeadImage();
                break;
            case R.id.rl2:
                tag = "2";
                getHeadImage();
                break;
            case R.id.tv_save_infor:
                String user_id = user.getUser_id();
                final String real_name = etName.getText().toString();
                String id_card = etIdNum.getText().toString();
                String ids = etIdNumS.getText().toString();
                if (TextUtils.isEmpty(real_name) || TextUtils.isEmpty(id_card) || opposite_pic == null || negative_pic == null) {
                    Toast.makeText(this, "请完善个人信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!id_card.equals(ids)) {
                    Toast.makeText(this, "两次输入的身份证号不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.e("aaa",
                        "(RealNameAuthenticationActivity.java:178)" + NetConfig.namecongif);
                OkHttpUtils.post()
                        .url(NetConfig.namecongif)
                        .addParams("user_id", user_id)
                        .addParams("real_name", real_name)
                        .addParams("id_card", id_card)
                        .addParams("opposite_pic", "")
                        .addParams("negative_pic", "")
                        .addFile("opposite", "1.jpg", opposite_pic)
                        .addFile("negative", "2.jpg", negative_pic)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e("aaa",
                                        "(RealNameAuthenticationActivity.java:136)" + e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {

                                Log.e("aaa",
                                        "(RealNameAuthenticationActivity.java:142)" + response);
                                if (response.contains("success")) {
                                    Toast.makeText(RealNameAuthenticationActivity.this, "成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }
                        });
                break;
        }
    }

    /**
     * 添加头像
     */
    private void getHeadImage() {
        final CharSequence[] items = {"相册", "拍照"};
        AlertDialog dlg = new AlertDialog.Builder(this, R.style.MyDialogTheme)
                .setTitle("选择图片")
                .setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
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
        Uri newUri = Uri.parse("file:///" + getPath(this, data.getData()));
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
                Toast.makeText(this, "请到设置中开启相机权限", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //调用系统的剪裁处理图片并保存至imageUri中
    public void cropImageUri(Activity activity, Uri orgUri, Uri desUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(orgUri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", ivIdCard.getWidth());
        intent.putExtra("aspectY", ivIdCard.getHeight());
        intent.putExtra("scale", true);
        //将剪切的图片保存到目标Uri中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, desUri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
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
                        ivIdCard.setImageBitmap(bitmap1);
                        tvAdd.setVisibility(View.GONE);
                        opposite_pic = new File(getPath(this, outputUri));
                    }
                    if ("2".equals(tag)) {
                        bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivIdCard2.setImageBitmap(bitmap2);
                        tvAdd.setVisibility(View.GONE);
                        tvAdd2.setVisibility(View.GONE);
                        negative_pic = new File(getPath(this, outputUri));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
