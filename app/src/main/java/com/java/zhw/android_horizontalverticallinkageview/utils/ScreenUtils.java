package com.java.zhw.android_horizontalverticallinkageview.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Author: Zhw
 * Description:
 */
public class ScreenUtils {
    public static int getScreenWidth(Context mContext) {
        WindowManager manager = (WindowManager) mContext.getSystemService(Context
                .WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
