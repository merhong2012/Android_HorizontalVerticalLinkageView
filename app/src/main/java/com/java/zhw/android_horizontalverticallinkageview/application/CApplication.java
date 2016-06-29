package com.java.zhw.android_horizontalverticallinkageview.application;

import android.app.Application;

import com.zhy.autolayout.config.AutoLayoutConifg;

/**
 * Author: Zhw
 * Description:
 */
public class CApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().useDeviceSize();
    }
}
