package com.andrew.miutu.setting;

import android.database.sqlite.SQLiteDatabase;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.DbUtils.DbUpgradeListener;

public class DBVersionListener implements DbUpgradeListener {

	@Override
	public void onUpgrade(DbUtils dbUtils, int oldVersion, int newVersion) {
		SQLiteDatabase db = dbUtils.getDatabase();
		db.execSQL("");
	}

}
