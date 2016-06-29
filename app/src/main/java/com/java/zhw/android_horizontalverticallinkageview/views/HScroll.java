package com.java.zhw.android_horizontalverticallinkageview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.java.zhw.android_horizontalverticallinkageview.listener.ScrollViewListener;

/**
 * Author: Eddy.
 * Description:添加滚动监听
 */
public class HScroll extends HorizontalScrollView {
    private ScrollViewListener scrollViewListener;

    public HScroll(Context paramContext) {
        super(paramContext);
    }

    public HScroll(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    public HScroll(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
    }

    public ScrollViewListener getScrollViewListener() {
        return this.scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (this.scrollViewListener != null)
            this.scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
    }

    public void setScrollViewListener(ScrollViewListener paramScrollViewListener) {
        this.scrollViewListener = paramScrollViewListener;
    }


}
