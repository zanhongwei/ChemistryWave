package com.zhw.chemistrywave;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class MyApplication extends Application {

    private static MyApplication application;
    private List<Activity> activityList = new LinkedList();
    public static String ntype = "3";
    public static int currentposition=0;
    public static int currentpositionerji=0;

    public static int VERSION_CODE = -1;
    public static String VERSION_NAME = "";

    public static Toast sToast;
    public static Context sContext;
    public static RequestOptions options;


    public static Context getContext() {
        return application;
    }

    public static MyApplication getInstance() {
        if (null == application) {
            application = new MyApplication();
        }
        return application;
    }

    private void initGlide() {
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.color.color_f6)
                .error(R.color.color_f6)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        initImageLoader(getApplicationContext());
        init(this);
        initGlide();

    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        context = context.getApplicationContext();
        sContext = context;
        sToast = Toast.makeText(sContext, "", Toast.LENGTH_SHORT);
        try {
            PackageManager manager = sContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(sContext.getPackageName(), 0);
            VERSION_CODE = info.versionCode;
            VERSION_NAME = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish

    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    public static String getNtype() {
        return ntype;
    }

    public static void setNtype(String ntype) {
        MyApplication.ntype = ntype;
    }

    private void initImageLoader(Context applicationContext) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.c6c6c6)    // 正在加载图片时显示的默认图片
                .showImageForEmptyUri(R.color.c6c6c6)    // 图片Url为空时显示的默认图片
                .showImageOnFail(R.color.c6c6c6)        // 图片加载失败时显示的默认图片
                .cacheInMemory(true)        // 缓存到内存
                .cacheOnDisk(true)        // 缓存到磁盘
                .considerExifParams(true)    //考虑exif参数
                //	.displayer(new RoundedBitmapDisplayer(20))	// 显示圆角
                .build();

        File cacheDir = new File(Environment.getExternalStorageDirectory(), "ccImage");
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(applicationContext)
                .threadPriority(Thread.NORM_PRIORITY - 2)        // 线程优先级
                .denyCacheImageMultipleSizesInMemory()        // 禁止在内存中把一张图片缓存多种尺寸
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())    // 缓存文件名的生成器
                .tasksProcessingOrder(QueueProcessingType.LIFO)    // 下载图片的任务的顺序
//                .writeDebugLogs() // Remove for release app	// 写出调试信息，发布apk时注释掉
                .defaultDisplayImageOptions(options)        // 默认显示图片显示选项
                .diskCacheFileCount(5000)
                .diskCacheSize(1000 * 1024 * 1024)
                .diskCache(new UnlimitedDiscCache(cacheDir, null, new Md5FileNameGenerator()))
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);                // 初始化ImageLoader
    }

}
