package com.andrew.miutu.setting;

import java.io.File;

import android.content.Context;

import com.andrew.miutu.application.MApplication;
import com.lidroid.xutils.DbUtils.DaoConfig;

public class XConfig extends DaoConfig {

	private static XConfig config = null;
	private String DBPath = MApplication.getInstance().DB_PATH;
	private String DBName = "welfare.db";
	private int DBVersion = 1;

	public XConfig(Context context) {
		super(context);
		super.setDbDir(DBPath);
		super.setDbName(DBName);
		super.setDbVersion(DBVersion);
		super.setDbUpgradeListener(new DBVersionListener());
		File dbFolder = new File(DBPath);
		if (!dbFolder.exists()) {
			dbFolder.mkdirs();
		}
	}

	public static XConfig getInstance(Context context) {
		if (config == null) {
			config = new XConfig(context);
		}
		return config;
	}
}
