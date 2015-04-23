package com.andrew.miutu.activity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.andrew.miutu.R;
import com.andrew.miutu.setting.BitmapSetting;
import com.andrew.miutu.utils.AnimationUtil;
import com.edmodo.cropper.CropImageView;
import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonFloatSmall;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.lidroid.xutils.bitmap.callback.DefaultBitmapLoadCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.IOException;
import java.lang.reflect.Method;


public class CropViewActivity extends BaseActivity {

    @ViewInject(R.id.CropImageView)
    private CropImageView cropImageView;
    @ViewInject(R.id.crop_bg)
    private ButtonFloatSmall crop_prev;
    @ViewInject(R.id.crop_bg)
    private ButtonFlat crop_bg;
    @ViewInject(R.id.crop_lbg)
    private ButtonFlat crop_lbg;

    private BitmapUtils bitmapUtils;

    private BitmapDisplayConfig bigPicDisplayConfig;

    public static Bitmap mbitmap;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_crop_view;
    }

    @Override
    public void initAfter() {
        ViewUtils.inject(this);
        cropImageView.setAspectRatio(9, 16);
        cropImageView.setFixedAspectRatio(false);
        initBitmap();
    }

    public void initBitmap() {
        String imgUrl = getIntent().getStringExtra("Durl");

        bitmapUtils = BitmapSetting.getBitmapUtils(CropViewActivity.this);
        bigPicDisplayConfig = new BitmapDisplayConfig();
        bigPicDisplayConfig.setShowOriginal(true); // 显示原始图片，不压缩
//        bigPicDisplayConfig.setBitmapConfig(Bitmap.Config.RGB_565);
//        bigPicDisplayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(this));
        bigPicDisplayConfig.setLoadingDrawable(getResources().getDrawable(R.mipmap.pic_loading));
        bigPicDisplayConfig.setLoadFailedDrawable(getResources().getDrawable(R.mipmap.pic_loaderror));

        BitmapLoadCallBack<CropImageView> callback = new DefaultBitmapLoadCallBack<CropImageView>() {
            @Override
            public void onLoadStarted(CropImageView container, String uri, BitmapDisplayConfig config) {
                super.onLoadStarted(container, uri, config);
            }

            @Override
            public void onLoadCompleted(CropImageView container, String uri, Bitmap bitmap, BitmapDisplayConfig config, BitmapLoadFrom from) {
//                super.onLoadCompleted(container, uri, bitmap, config, from);
                LogUtils.d("图片大小：" + bitmap.getWidth() + "*" + bitmap.getHeight());
                container.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadFailed(CropImageView container, String uri, Drawable drawable) {
                super.onLoadFailed(container, uri, drawable);
            }
        };
        bitmapUtils.display(cropImageView, imgUrl, bigPicDisplayConfig, callback);
    }

    @OnClick(R.id.crop_bg)
    public void cropWallPaper(View v) {
        WallpaperManager mWallManager = WallpaperManager.getInstance(this);
        try {
            Bitmap bitmap = cropImageView.getCroppedImage();
            mWallManager.setBitmap(bitmap);
            showToast("壁纸设置成功");
        } catch (IOException e) {
            LogUtils.d("壁纸设置异常", e);
            showToast("壁纸设置失败");
        }
    }

    @OnClick(R.id.crop_prev)
    public void cropPreView(View v) {
        mbitmap = cropImageView.getCroppedImage();
        Intent intent = new Intent(getApplicationContext(), PreViewActivity.class);
        AnimationUtil.startActivity(CropViewActivity.this, intent);
    }

    @OnClick(R.id.crop_lbg)
    public void cropLockPaper(View v) {
        try {
            WallpaperManager mWallManager = WallpaperManager.getInstance(this);
            Class class1 = mWallManager.getClass();//获取类名
            Method setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper", Bitmap.class);//获取设置锁屏壁纸的函数
            Bitmap bitmap = cropImageView.getCroppedImage();
            setWallPaperMethod.invoke(mWallManager, bitmap);//调用锁屏壁纸的函数，并指定壁纸的路径imageFilesPath
            showToast("锁屏壁纸设置成功");
        } catch (Throwable e) {
            LogUtils.d("锁屏壁纸设置异常", e);
            showToast("锁屏壁纸设置失败[魅族接口]");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_wall_paper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        AnimationUtil.backActivity(CropViewActivity.this);
    }
}
