package com.andrew.miutu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.andrew.miutu.R;

public class AnimationUtil {

	public static void startActivity(Context context, Intent intent) {
		context.startActivity(intent);
		((Activity) context).overridePendingTransition(R.anim.open_next, R.anim.close_main);
	}

	public static void startActivityForResult(Context context, Intent intent, int code) {
		((Activity) context).startActivityForResult(intent, code);
		((Activity) context).overridePendingTransition(R.anim.open_next, R.anim.close_main);
	}

	public static void backActivity(Context context) {
		((Activity) context).overridePendingTransition(R.anim.open_main, R.anim.close_next);
	}

	public static void ViewAnimation(Context context, View view, int anim) {
		Animation ani = AnimationUtils.loadAnimation(context, anim);
		view.startAnimation(ani);
	}
}
