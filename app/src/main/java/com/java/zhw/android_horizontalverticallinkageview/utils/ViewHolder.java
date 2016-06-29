package com.java.zhw.android_horizontalverticallinkageview.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * Author: Eddy.
 * Description:
 */
public class ViewHolder {
    public static final String tag = "ViewHolder";
    @SuppressWarnings("unchecked")
    public static <T extends View> T getHolderView(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        View childView;
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
            // 先创建的ViewHolder自然没有缓存View所以不用判断
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        } else {
            childView = viewHolder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewHolder.put(id, childView);
            }
        }
        return (T) childView;
    }
}
