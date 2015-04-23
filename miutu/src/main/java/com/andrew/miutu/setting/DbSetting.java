package com.andrew.miutu.setting;

import android.content.Context;
import com.lidroid.xutils.DbUtils;

public class DbSetting {
	public static DbUtils db = null;

	public static DbUtils getDB(Context context) {
		if (db == null) {
			db = DbUtils.create(XConfig.getInstance(context));
			db.configDebug(true);
		}
		return db;
	}
}
