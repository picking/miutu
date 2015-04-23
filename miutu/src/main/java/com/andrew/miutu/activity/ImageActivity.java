package com.andrew.miutu.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.andrew.miutu.Adapter.ZoomPageAdapter;
import com.andrew.miutu.R;
import com.andrew.miutu.db.LoveImgDB;
import com.andrew.miutu.entity.ImageDetial;
import com.andrew.miutu.utils.AnimationUtil;
import com.andrew.miutu.view.photoview.ZoomPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.List;


public class ImageActivity extends BaseActivity {

    @ViewInject(R.id.zoom_pager)
    private ZoomPager zpager;
    @ViewInject(R.id.img_love)
    private Button img_love;
    @ViewInject(R.id.img_wallpaper)
    private Button img_wallpaper;
    @ViewInject(R.id.img_save)
    private Button img_save;
    @ViewInject(R.id.img_share)
    private Button img_share;
    @ViewInject(R.id.img_tv)
    private TextView img_tv;

    private ZoomPageAdapter adapter;
    private List<ImageDetial> list;
    private int cposition;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_image;
    }

    @Override
    public void initAfter() {
        ViewUtils.inject(this);
        list = (List<ImageDetial>) getIntent().getSerializableExtra("DataList");
        cposition = getIntent().getIntExtra("Position", 0);
        adapter = new ZoomPageAdapter(list, getApplicationContext());
        zpager.setAdapter(adapter);
        zpager.setCurrentItem(cposition);
        setTv();
        setLoveBg();
        zpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                cposition = position;
                setTv();
                setLoveBg();
                System.gc();
                colorChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setTv() {
        img_tv.setText(list.get(cposition).getTitle() + "  ");
    }
    public void setLoveBg(){
        if (LoveImgDB.isLoved(getApplicationContext(),list.get(cposition).getId())){
            img_love.setBackgroundResource(R.drawable.loved_bg);
        }else {
            img_love.setBackgroundResource(R.drawable.love_bg);
        }
    }

    @OnClick(R.id.img_love)
    public void likeSaveOrOnBack(View v){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scale_then);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!LoveImgDB.isLoved(getApplicationContext(),list.get(cposition).getId())){
                    try {
                        LoveImgDB.saveImg(getApplicationContext(),list.get(cposition));
                        img_love.setBackgroundResource(R.drawable.loved_bg);
                        showToast("收藏成功");
                    }catch (DbException e){
                        LogUtils.d("保存数据异常",e);
                        showToast("收藏失败");
                    }
                }else {
                    try {
                        LoveImgDB.deleteImg(getApplicationContext(), list.get(cposition));
                        img_love.setBackgroundResource(R.drawable.love_bg);
                        showToast("取消收藏成功");
                    }catch (DbException e){
                        LogUtils.d("删除数据异常",e);
                        showToast("取消收藏失败");
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        img_love.startAnimation(animation);
    }

    @OnClick(R.id.img_wallpaper)
    public void setWallPaper(View v){
        Intent intent = new Intent(getApplicationContext(),CropViewActivity.class);
        intent.putExtra("Durl",list.get(cposition).getDownloadUrl());
        AnimationUtil.startActivity(ImageActivity.this,intent);
    }

    /**
     * 界面颜色的更改
     */
    private void colorChange(int position) {
        // 用来提取颜色的Bitmap
        Bitmap bitmap = adapter.getCurrentBitmap();
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            /**
             * 提取完之后的回调方法
             */
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                if (vibrant != null) {
                    getToolbar().setBackgroundColor(vibrant.getRgb());
                    if (android.os.Build.VERSION.SDK_INT >= 21) {
                        Window window = getWindow();
                        // 新API才有的
                        window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                        window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                    }
                }
            }
        });
    }

    /**
     * 颜色加深处理
     *
     * @param RGBValues RGB的值，由alpha（透明度）、red（红）、green（绿）、blue（蓝）构成，
     *                  Android中我们一般使用它的16进制，
     *                  例如："#FFAABBCC",最左边到最右每两个字母就是代表alpha（透明度）、
     *                  red（红）、green（绿）、blue（蓝）。每种颜色值占一个字节(8位)，值域0~255
     *                  所以下面使用移位的方法可以得到每种颜色的值，然后每种颜色值减小一下，在合成RGB颜色，颜色就会看起来深一些了
     * @return
     */
    private int colorBurn(int RGBValues) {
        int alpha = RGBValues >> 24;
        int red = RGBValues >> 16 & 0xFF;
        int green = RGBValues >> 8 & 0xFF;
        int blue = RGBValues & 0xFF;
        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
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
        AnimationUtil.backActivity(ImageActivity.this);
    }
}
