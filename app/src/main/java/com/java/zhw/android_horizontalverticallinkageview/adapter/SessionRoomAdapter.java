package com.java.zhw.android_horizontalverticallinkageview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.java.zhw.android_horizontalverticallinkageview.R;
import com.java.zhw.android_horizontalverticallinkageview.data.SessionRoomData;
import com.java.zhw.android_horizontalverticallinkageview.utils.ScreenUtils;
import com.java.zhw.android_horizontalverticallinkageview.utils.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SessionRoomAdapter extends BaseAdapter {
    private List<SessionRoomData> currentSessionRoomData = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater = null;
    private int lableWidth = 0;

    public SessionRoomAdapter(Context paramContext) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(paramContext);
        lableWidth = (ScreenUtils.getScreenWidth(paramContext) - (int) paramContext.getResources
                ().getDimension(R.dimen
                .session_time_width)) / 3;
    }

    public void setRoomData(List<SessionRoomData> roomData) {
        this.currentSessionRoomData = roomData;
        notifyDataSetChanged();
    }

    public int getCount() {
        return currentSessionRoomData.size();
    }

    public Object getItem(int paramInt) {
        if (this.currentSessionRoomData != null)
            return this.currentSessionRoomData.get(paramInt);
        return null;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null)
            paramView = this.mInflater.inflate(R.layout.session_room_item, null);
        TextView textView = ViewHolder.getHolderView(paramView, R.id.txt_room);
        SessionRoomData localSessionRoomData = (SessionRoomData) getItem(paramInt);
        textView.setText(localSessionRoomData.getName());
        FrameLayout.LayoutParams fl = (FrameLayout.LayoutParams) textView.getLayoutParams();
        if (fl != null) {
            fl.width = lableWidth;
            textView.setLayoutParams(fl);
        }
        return paramView;
    }

}