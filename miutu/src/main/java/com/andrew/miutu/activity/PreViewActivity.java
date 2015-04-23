package com.andrew.miutu.activity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.andrew.miutu.R;
import com.andrew.miutu.application.MApplication;
import com.andrew.miutu.utils.PreferencesUtils;
import com.andrew.miutu.view.waterfall.ScaleImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;


public class PreViewActivity extends BaseActivity {

    @ViewInject(R.id.pre_img)
    private ScaleImageView pre_img;

    @Override
    public void doSomeFeature() {
        super.doSomeFeature();
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_pre_view;
    }

    @Override
    public void initAfter() {
        ViewUtils.inject(this);
        LogUtils.e(MApplication.ScreenW + " ---nima--  " + MApplication.ScreenH);
        pre_img.setImageWidth(MApplication.ScreenW);
        pre_img.setImageHeight(MApplication.ScreenH);
//        pre_img.setImageHeight(MApplication.ScreenH + MApplication.BottomH);
        pre_img.setImageBitmap(CropViewActivity.mbitmap);

        fullScreen();
        pre_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullScreenChange();
            }
        });
    }

    public void fullScreen() {
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public void fullScreenChange() {
        boolean fullScreen = PreferencesUtils.getBoolean(getApplicationContext(), "fullScreen", false);
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        if (fullScreen) {
            attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(attrs);
            //取消全屏设置
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            PreferencesUtils.putBoolean(getApplicationContext(), "fullScreen", false);
        } else {
            attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(attrs);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            PreferencesUtils.putBoolean(getApplicationContext(), "fullScreen", true);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pre_view, menu);
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
}
