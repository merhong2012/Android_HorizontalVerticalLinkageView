package com.java.zhw.android_horizontalverticallinkageview.listener;

import android.view.View;

/**
 * Author: Eddy.
 * Description: 滑动监听
 */
public abstract interface ScrollViewListener {
    public abstract void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy);
}
