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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;
import com.thuongnh.zprogresshud.ZProgressHUD;
import com.zhw.chemistrywave.MyApplication;
import com.zhw.chemistrywave.R;
import com.zhw.chemistrywave.base.BaseActivity;
import com.zhw.chemistrywave.bean.Shangpxiangqing;
import com.zhw.chemistrywave.utils.MyUtils;
import com.zhw.chemistrywave.utils.NetConfig;
import com.zhw.chemistrywave.utils.SelectImageUtils;
import com.zhy.base.fileprovider.FileProvider7;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class AddJianCeBaoGaoActivity extends BaseActivity {


    /**
     * 头像相关
     */
    private static final int PHOTO_REQUEST_CAREMA = 1;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT = 3;
    private static final int CODE_FOR_WRITE_PERMISSION = 11;
    private static final int CODE_FOR_CAMERA_PERMISSION = 22;
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
    @BindView(R.id.iv_jiancebaogao_uv)
    ImageView ivJiancebaogaoUv;
    @BindView(R.id.iv_jiancebaogao_ceseyi)
    ImageView ivJiancebaogaoCeseyi;
    @BindView(R.id.iv_jiancebaogao_yanpin)
    ImageView ivJiancebaogaoYanpin;
    @BindView(R.id.iv_jiancebaogao_jiancebaogao)
    ImageView ivJiancebaogaoJiancebaogao;
    @BindView(R.id.iv_jiancebaogao_jianceship)
    ImageView ivJiancebaogaoJianceship;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.iv_jiancebaogao_dahuotupian)
    ImageView ivJiancebaogaoDahuotupian;
    @BindView(R.id.tv_jiancebaogao_save)
    TextView tvJiancebaogaoSave;
    private String imageFilePath = "";
    private Uri imageFileUri;
    private Uri outputUri;
    private Intent data;
    private int state;
    private String tag;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private Bitmap bitmap5;
    private Bitmap bitmap6;
    private File uvData;
    private File ceseyiData;
    private File yanPin;
    private File jiancebaogao;
    private File daHuoTuPian;
    private File jianceship;
    //更新
    private String uvDataUp;
    private String ceseyiDataUp;
    private String yanPinUp;
    private String jiancebaogaoUp;
    private String daHuoTuPianUp;
    private String jianceshipUp;
    private String mer_id;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_ce_bao_gao);
        ButterKnife.bind(this);
        tvTitlebarCenter.setText("Examining report");
        mer_id = getIntent().getStringExtra("mer_id");
        type = getIntent().getStringExtra("type");
        switch (type) {
            case "EditGoodsActivity":
                getData();
                break;
            case "TianJiaShangPingActivity":
                break;
        }
    }

    /**
     * 得到商品基本信息详情
     */
    private void getData() {
        Log.e("aaa", "--商品详情参数--mer_id-->" + mer_id);
        OkHttpUtils.post().url(NetConfig.goodsdetail_url)
                .addParams("goods_id", String.valueOf(mer_id))
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "--商品详情返回--error->" + e.getMessage());
                        Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();
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
                                    String uv_data = shangpxiangqing.getData().getDetect_uv();
                                    if (!TextUtils.isEmpty(uv_data)) {
                                        Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + uv_data).into(ivJiancebaogaoUv);
                                    } else {
                                        ivJiancebaogaoUv.setImageResource(R.drawable.goodsone);
                                    }
                                    Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + shangpxiangqing.getData().getDetect_colourimeter()).apply(MyApplication.options).into(ivJiancebaogaoUv);
                                    Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + shangpxiangqing.getData().getDetect_sample_imgs()).apply(MyApplication.options).into(ivJiancebaogaoUv);
                                    Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + shangpxiangqing.getData().getDetect_report()).apply(MyApplication.options).into(ivJiancebaogaoUv);
                                    Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + shangpxiangqing.getData().getDetect_video()).apply(MyApplication.options).into(ivJiancebaogaoUv);
                                    Glide.with(AddJianCeBaoGaoActivity.this).load(NetConfig.baseurl + shangpxiangqing.getData().getDetect_bulk_imgs()).apply(MyApplication.options).into(ivJiancebaogaoUv);

                                } else {
                                    Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.rl_titlebar_back, R.id.tv_jiancebaogao_save, R.id.iv_jiancebaogao_uv, R.id.iv_jiancebaogao_ceseyi, R.id.iv_jiancebaogao_yanpin, R.id.iv_jiancebaogao_jiancebaogao, R.id.iv_jiancebaogao_jianceship, R.id.iv_jiancebaogao_dahuotupian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_titlebar_back:
                finish();
                break;
            case R.id.tv_jiancebaogao_save:
                updateReport();
                break;
            //uv
            case R.id.iv_jiancebaogao_uv:
                tag = "1";
                getHeadImage();
                break;
            //测色仪数据
            case R.id.iv_jiancebaogao_ceseyi:
                tag = "2";
                getHeadImage();
                break;
            //样品图片
            case R.id.iv_jiancebaogao_yanpin:
                tag = "3";
                getHeadImage();
                break;
            //检测报告
            case R.id.iv_jiancebaogao_jiancebaogao:
                tag = "4";
                getHeadImage();
                break;
            //检测视频
            case R.id.iv_jiancebaogao_jianceship:
                getVideo();
                break;
            //大货图片
            case R.id.iv_jiancebaogao_dahuotupian:
                tag = "5";
                getHeadImage();
                break;
        }
    }

    private void getVideo() {
        PictureSelector.create(AddJianCeBaoGaoActivity.this)
                .openGallery(PictureMimeType.ofVideo())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(3)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewVideo(true)// 是否可预览视频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif()// 是否显示gif图片 true or false
                .compressSavePath(getPath())//压缩图片保存地址
//                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
//                .circleDimmedLayer()// 是否圆形裁剪 true or false
//               .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
//                .openClickSound()// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
//                .previewEggs()// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .minimumCompressSize(100)// 小于100kb的图片不压缩
//                .synOrAsy(true)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
                .videoQuality(1)// 视频录制质量 0 or 1 int
//                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
                .recordVideoSecond(60)//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    /**
     * 自定义压缩存储地址
     *
     * @return
     */
    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/Luban/video/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }


    /**
     * 修改检测报告
     */
    private void updateReport() {
        if (TextUtils.isEmpty(uvDataUp)) {
            Toast.makeText(this, "Please upload UV data", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(ceseyiDataUp)) {
            Toast.makeText(this, "请上传测色仪数据Please upload colour photometer data", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(yanPinUp)) {
            Toast.makeText(this, "请上传样品图片Please upload specimen photo data", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(jiancebaogaoUp)) {
            Toast.makeText(this, "请上传检测报告Please upload examining report", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(jianceshipUp)) {
            Toast.makeText(this, "请上传检测视频Please upload detection of video data", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(daHuoTuPianUp)) {
            Toast.makeText(this, "请上传大货图片Please upload cargo photo", Toast.LENGTH_SHORT).show();
            return;
        }


        Bundle bundle = new Bundle();
        bundle.putString("uv_data", uvDataUp);
        bundle.putString("colourimeter_data", ceseyiDataUp);
        bundle.putString("sample_images", yanPinUp);
        bundle.putString("detect_report", jiancebaogaoUp);
        bundle.putString("detect_video", jianceshipUp);
        bundle.putString("big_photos", daHuoTuPianUp);

        setResult(4444,new Intent().putExtras(bundle));
        finish();
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
        intent.putExtra("aspectX", ivJiancebaogaoUv.getWidth());
        intent.putExtra("aspectY", ivJiancebaogaoUv.getHeight());
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
                        ivJiancebaogaoUv.setImageBitmap(bitmap1);
                        uvData = new File(SelectImageUtils.getPath(this, outputUri));
                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            uploadFile(uvData, "1");
                            progressHUD.dismiss();


                    }
                    if ("2".equals(tag)) {
                        bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivJiancebaogaoCeseyi.setImageBitmap(bitmap2);
                        ceseyiData = new File(SelectImageUtils.getPath(this, outputUri));
                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            uploadFile(ceseyiData, "2");
                            progressHUD.dismiss();


                    }
                    if ("3".equals(tag)) {
                        bitmap3 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivJiancebaogaoYanpin.setImageBitmap(bitmap3);
                        yanPin = new File(SelectImageUtils.getPath(this, outputUri));

                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            uploadFile(yanPin, "3");
                            progressHUD.dismiss();

                    }
                    if ("4".equals(tag)) {
                        bitmap4 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivJiancebaogaoJiancebaogao.setImageBitmap(bitmap4);
                        jiancebaogao = new File(SelectImageUtils.getPath(this, outputUri));

                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            uploadFile(jiancebaogao, "4");
                            progressHUD.dismiss();


                    }
                    if ("5".equals(tag)) {
                        bitmap5 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), outputUri);
                        ivJiancebaogaoDahuotupian.setImageBitmap(bitmap5);
                        daHuoTuPian = new File(SelectImageUtils.getPath(this, outputUri));

                            ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                            progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                            progressHUD.show();
                            uploadFile(daHuoTuPian, "5");
                            progressHUD.dismiss();

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == PictureConfig.CHOOSE_REQUEST) {
            // 图片选择结果回调

            List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
            // 例如 LocalMedia 里面返回三种 path
            // 1.media.getPath(); 为原图 path
            // 2.media.getCutPath();为裁剪后 path，需判断 media.isCut();是否为 true
            // 3.media.getCompressPath();为压缩后 path，需判断 media.isCompressed();是否为 true
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            String compressPath = selectList.get(0).getPath();
            jianceship = new File(compressPath);

                ZProgressHUD progressHUD = ZProgressHUD.getInstance(AddJianCeBaoGaoActivity.this);
                progressHUD.setSpinnerType(ZProgressHUD.SIMPLE_ROUND_SPINNER);
                progressHUD.show();
                uploadFile(jianceship, "6");
                progressHUD.dismiss();

//            RequestOptions options = new RequestOptions()
//                    ;
            Glide.with(AddJianCeBaoGaoActivity.this).load(compressPath).apply(MyApplication.options)
                    .into(ivJiancebaogaoJianceship);
            long duration = selectList.get(0).getDuration();
            tvDuration.setText(DateUtils.timeParse(duration));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void uploadFile(File file, final String s) {
        OkHttpUtils.post().url(NetConfig.SAVE_FILE)
                .addFile("file", "1.jpg", file)
                .addParams("user_id", MyUtils.getUser().getUser_id())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaa", "---上传文件返回--error-->" + e.getMessage());
                        Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();
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
                                    if ("1".equals(s)) {
                                        uvDataUp = data;
                                    } else if ("2".equals(s)) {
                                        ceseyiDataUp = data;
                                    } else if ("3".equals(s)) {
                                        yanPinUp = data;
                                    } else if ("4".equals(s)) {
                                        jiancebaogaoUp = data;
                                    } else if ("5".equals(s)) {
                                        daHuoTuPianUp = data;
                                    } else if ("6".equals(s)) {
                                        jianceshipUp = data;
                                    }
                                } else {
                                    Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }else {
                            Toast.makeText(AddJianCeBaoGaoActivity.this, "network error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
