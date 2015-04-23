package com.andrew.miutu.db;

import android.content.Context;

import com.andrew.miutu.entity.ImageDetial;
import com.andrew.miutu.setting.DbSetting;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.util.LogUtils;

/**
 * Created by Andrew on 2015/4/23.
 */
public class LoveImgDB {

    public static void saveImg(Context context, ImageDetial detial) throws DbException {
        DbSetting.getDB(context).saveOrUpdate(detial);
    }

    public static void deleteImg(Context context, ImageDetial detial) throws DbException {
        DbSetting.getDB(context).delete(detial);
    }

    public static boolean isLoved(Context context, String id) {
        boolean result = false;
        try {
            ImageDetial detial = DbSetting.getDB(context).findFirst(Selector.from(ImageDetial.class).where("id", "=", id));
            if (detial != null) {
                result = true;
            }
        }catch (DbException e){
            LogUtils.d("查询图片是否收集失败",e);
        }finally {
            return result;
        }
    }
}
