package com.andrew.miutu.application;

import java.io.File;

import com.andrew.miutu.R;
import com.andrew.miutu.setting.MConstants;
import com.lidroid.xutils.util.LogUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;

/**
 * Created by Andrew on 2015/4/21.
 */
public class MApplication extends Application {

    private static MApplication instance;

    private static DisplayImageOptions options;

    public static int ScreenW;
    public static int ScreenH;
    public static int BottomH;

    public static MApplication getInstance() {
        if (instance == null) {
            instance = new MApplication();
        }
        return instance;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        if (MConstants.Config.DEVELOPER_MODE
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll().penaltyDeath().build());
        }
        super.onCreate();

        CachePath = getApplicationContext().getExternalCacheDir().getPath();
        FilePath = getApplicationContext().getExternalFilesDir("/fs/")
                .getPath();

        // 得到屏幕的宽度和高度
        DisplayMetrics dm = getResources().getDisplayMetrics();
        ScreenW = dm.widthPixels;
        ScreenH = dm.heightPixels;

        Resources resources = this.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            BottomH =  resources.getDimensionPixelSize(resourceId);
        }

        // 图片下载处理
        initImageLoader(getApplicationContext());
    }

    /** 默认资料缓存路径 */
    private static String CachePath = "/storage/sdcard0/Android/data/com.andrew.miutu/cache";
    /** 默认文件缓存路径 */
    private static String FilePath = "/storage/sdcard0/Android/data/com.andrew.miutu/files/fs";
    /** 图片缓存路径 */
    public String IMG_CACHE_PATH = CachePath + "/imgcache";
    /** 图片备份缓存路径 */
    public String IMG_RCACHE_PATH = FilePath + "/imgcache";
    /** 缓存资料路径 */
    public String DATA_CACHE_PATH = CachePath + "/datacache";
    /** 数据库路径 */
    public String DB_PATH = CachePath + "/db/";

    public DisplayImageOptions getImageOptions(BitmapDisplayer displayer) {
        if (displayer == null) {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.pic_loading)
                    .showImageForEmptyUri(R.mipmap.pic_loaderror)
                    .showImageOnFail(R.mipmap.pic_loaderror)
                    .resetViewBeforeLoading(true).cacheInMemory(true)
                    .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true).build();
        } else {
            options = new DisplayImageOptions.Builder()
                    .showImageOnLoading(R.mipmap.pic_loading)
                    .showImageForEmptyUri(R.mipmap.pic_loaderror)
                    .showImageOnFail(R.mipmap.pic_loaderror)
                    .resetViewBeforeLoading(true).cacheInMemory(true)
                    .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true).displayer(displayer).build();
        }
        return options;
    }

    // 初始化图片加载
    public void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCache(
                        new UnlimitedDiscCache(new File(IMG_CACHE_PATH),
                                new File(IMG_RCACHE_PATH),
                                new Md5FileNameGenerator()))
                .diskCacheSize(50 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                        // .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }

}
