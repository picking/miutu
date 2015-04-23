package com.andrew.miutu.utils;

import com.lidroid.xutils.util.LogUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Andrew on 2015/4/21.
 */
public class UrlToolUilts {
    public static final String BASE_URL = "http://image.baidu.com/data/imgs?";
    public static final String TAG = "UrlToolUilts";

    public static String EncodeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtils.d(TAG, e);
            return "";
        }

    }
}
