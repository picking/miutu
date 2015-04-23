package com.andrew.miutu.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andrew.miutu.R;
import com.andrew.miutu.entity.ImageDetial;
import com.andrew.miutu.view.photoview.PhotoView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Andrew on 2015/4/22.
 */
public class ZoomPageAdapter  extends PagerAdapter {

    private List<ImageDetial> list;
    private PhotoView photoView;
    private static Bitmap mbitmap;
    private Context context;

    private int size;// 页数

    public ZoomPageAdapter(List<ImageDetial> list,Context context) {// 构造函数
        // 初始化viewpager的时候给的一个页面
        this.list = list;
        this.context = context;
        size = list == null ? 0 : list.size();
    }

    public void setListViews(List<ImageDetial> list) {// 自己写的一个方法用来添加数据
        this.list = list;
        size = list == null ? 0 : list.size();
    }

    public int getCount() {// 返回数量
        return size;
    }

    public int getItemPosition(Object object) {
        return 0;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        photoView = new PhotoView(container.getContext());
        ImageLoader.getInstance().displayImage(list.get(position).getThumbLargeTnUrl(), photoView, ls);
        container.addView(photoView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }


    public ImageLoadingListener ls = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String s, View view) {
            ((PhotoView) view).setImageResource(R.mipmap.pic_loading);
        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {
            ((PhotoView) view).setImageResource(R.mipmap.pic_loaderror);
        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
            mbitmap = bitmap;
            ((PhotoView) view).setImageBitmap(bitmap);
        }

        @Override
        public void onLoadingCancelled(String s, View view) {
//            ((ScaleImageView) view).setImageResource(R.mipmap.pic_loaderror);
        }
    };

    public Bitmap getCurrentBitmap(){
        if (mbitmap == null){
            mbitmap = BitmapFactory.decodeResource(context.getResources(), R.color.colorPrimary);
        }
        return  mbitmap;
    }

}
