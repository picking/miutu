package com.andrew.miutu.view.photoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ZoomPager extends ViewPager {

	public ZoomPager(Context context) {
		super(context);
	}

	public ZoomPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		try {
			super.onTouchEvent(arg0);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		boolean b = false;
		try {
			b = super.onInterceptTouchEvent(arg0);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
		}
		return b;
	}

}
