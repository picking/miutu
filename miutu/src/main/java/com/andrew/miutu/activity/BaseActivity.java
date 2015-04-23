package com.andrew.miutu.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.andrew.miutu.R;
import com.andrew.miutu.setting.SystemBarTintManager;

/**
 * Created by Andrew on 2015/4/21.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle arg0) {
        doSomeFeature();
        super.onCreate(arg0);
        setContentView(getLayoutResource());

        // 设定状态栏的颜色，当版本大于4.4时起作用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            // 此处可以重新指定状态栏颜色
            tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
        }

        // 设置ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Title
//        toolbar.setTitle("测试");
        // App Logo
        // toolbar.setLogo(R.drawable.ic_launcher);
        // Sub Title
        // toolbar.setSubtitle("Sub title");

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initAfter();
    }

    public void doSomeFeature(){}

    public abstract int getLayoutResource();

    public abstract void initAfter();

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, 1500).show();
    }
}
