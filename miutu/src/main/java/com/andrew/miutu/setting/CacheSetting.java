package com.andrew.miutu.setting;

import com.andrew.miutu.application.MApplication;
import com.andrew.miutu.utils.ACache;

import java.io.File;

public class CacheSetting {
	public static ACache cache;

	public static ACache instance() {
		if (cache == null) {
			File file = new File(MApplication.getInstance().DATA_CACHE_PATH);
			cache = ACache.get(file, 1000 * 1000 * 400, Integer.MAX_VALUE);// 400M
																			// 不限制条数
		}
		return cache;
	}
}
