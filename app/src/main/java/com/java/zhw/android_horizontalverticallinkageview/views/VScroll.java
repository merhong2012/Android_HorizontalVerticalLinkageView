package com.java.zhw.android_horizontalverticallinkageview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.java.zhw.android_horizontalverticallinkageview.listener.ScrollViewListener;

/**
 * Author: Eddy.
 * Description:自定义垂直滚动 添加滚动监听
 */
public class VScroll extends ScrollView {
    private ScrollViewListener scrollViewListener;

    public VScroll(Context paramContext) {
        super(paramContext);
    }

    public VScroll(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public VScroll(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public ScrollViewListener getScrollViewListener() {
        return this.scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }


    public void setScrollViewListener(ScrollViewListener paramScrollViewListener) {
        this.scrollViewListener = paramScrollViewListener;
    }

}