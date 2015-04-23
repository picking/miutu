package com.andrew.miutu.setting;

import android.content.Context;
import com.lidroid.xutils.HttpUtils;

public class HttpSetting {
	public static HttpUtils http = null;

	public static HttpUtils instance(Context context) {
		if (http == null) {
			http = new HttpUtils();
			http.configTimeout(30000);
			http.configDefaultHttpCacheExpiry(1000 * 10);
			http.configHttpCacheSize(0);
			http.configRequestThreadPoolSize(5);
		}
		return http;
	}

	public static void setNull() {
		http = null;
	}
}
