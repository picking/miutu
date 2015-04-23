package com.andrew.miutu.Adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.andrew.miutu.R;
import com.andrew.miutu.entity.ImageDetial;
import com.andrew.miutu.view.waterfall.ScaleImageView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by Andrew on 2015/4/21.
 */
public class StaggeredAdapter extends BaseAdapter {

    private StaggeredHolder holder;
    private List<ImageDetial> dataset;
    private ImageDetial idetial;

    public StaggeredAdapter(List<ImageDetial> dataset) {
        this.dataset = dataset;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.infos_item, parent, false);
            holder = new StaggeredHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (StaggeredHolder) convertView.getTag();
        }

        idetial = dataset.get(position);
        if (!idetial.getThumbnailUrl()
                .equals(holder.img.getTag())) {
            holder.img.setImageWidth(idetial.getThumbnailWidth());
            holder.img.setImageHeight(idetial.getThumbnailWidth());
            ImageLoader.getInstance()
                    .displayImage(idetial.getThumbnailUrl(),
                            holder.img, ls);
            holder.img.setTag(idetial.getThumbnailUrl());
        }
        holder.tv.setText(idetial.getTitle() + " ");
        return convertView;
    }

    public class StaggeredHolder {
        @ViewInject(R.id.tag_pic)
        private ScaleImageView img;
        @ViewInject(R.id.tag_title)
        private TextView tv;

        public StaggeredHolder(View v) {
            ViewUtils.inject(this, v);
        }
    }


    public ImageLoadingListener ls = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String s, View view) {
            ((ScaleImageView) view).setImageResource(R.mipmap.pic_loading);
        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {
            ((ScaleImageView) view).setImageResource(R.mipmap.pic_loaderror);
        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {
            ((ScaleImageView) view).setImageBitmap(bitmap);
        }

        @Override
        public void onLoadingCancelled(String s, View view) {
//            ((ScaleImageView) view).setImageResource(R.mipmap.pic_loaderror);
        }
    };


}
