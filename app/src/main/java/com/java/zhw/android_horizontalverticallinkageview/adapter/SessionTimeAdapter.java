package com.java.zhw.android_horizontalverticallinkageview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.java.zhw.android_horizontalverticallinkageview.R;

import java.util.ArrayList;

public class SessionTimeAdapter extends BaseAdapter {
    private ArrayList<SessionTimeData> currentSessionTileData = new ArrayList();
    private Context mContext;
    private LayoutInflater mInflater = null;

    public SessionTimeAdapter(Context paramContext) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(paramContext);
        init();
    }

    private void init() {
        this.currentSessionTileData.clear();
        //  this.currentSessionTileData.add(new SessionTimeData("07:00 AM"));
        this.currentSessionTileData.add(new SessionTimeData("08:00"));
        this.currentSessionTileData.add(new SessionTimeData("08:30"));
        this.currentSessionTileData.add(new SessionTimeData("09:00"));
        this.currentSessionTileData.add(new SessionTimeData("09:30"));
        this.currentSessionTileData.add(new SessionTimeData("10:00"));
        this.currentSessionTileData.add(new SessionTimeData("10:30"));
        this.currentSessionTileData.add(new SessionTimeData("11:00"));
        this.currentSessionTileData.add(new SessionTimeData("11:30"));
        this.currentSessionTileData.add(new SessionTimeData("12:00"));
        this.currentSessionTileData.add(new SessionTimeData("12:30"));
        this.currentSessionTileData.add(new SessionTimeData("13:00"));
        this.currentSessionTileData.add(new SessionTimeData("13:30"));
        this.currentSessionTileData.add(new SessionTimeData("14:00"));
        this.currentSessionTileData.add(new SessionTimeData("14:30"));
        this.currentSessionTileData.add(new SessionTimeData("15:00"));
        this.currentSessionTileData.add(new SessionTimeData("15:30"));
        this.currentSessionTileData.add(new SessionTimeData("16:00"));
        this.currentSessionTileData.add(new SessionTimeData("16:30"));
        this.currentSessionTileData.add(new SessionTimeData("17:00"));
        this.currentSessionTileData.add(new SessionTimeData("17:30"));
        this.currentSessionTileData.add(new SessionTimeData("18:00"));
        this.currentSessionTileData.add(new SessionTimeData("18:30"));
        this.currentSessionTileData.add(new SessionTimeData("19:00"));
        this.currentSessionTileData.add(new SessionTimeData("19:30"));
        this.currentSessionTileData.add(new SessionTimeData("20:00"));
        this.currentSessionTileData.add(new SessionTimeData("20:30"));
        this.currentSessionTileData.add(new SessionTimeData("21:00"));
    }

    public int getCount() {
        ArrayList localArrayList = this.currentSessionTileData;
        int i = 0;
        if (localArrayList != null)
            i = this.currentSessionTileData.size();
        return i;
    }

    public Object getItem(int paramInt) {
        if (this.currentSessionTileData != null)
            return this.currentSessionTileData.get(paramInt);
        return null;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null)
            paramView = this.mInflater.inflate(R.layout.session_time_item, null);
        SessionTimeData localSessionTimeData = (SessionTimeData) getItem(paramInt);
        if ((localSessionTimeData != null) && (paramView != null))
            ((TextView) paramView.findViewById(R.id.txt_time)).setText(localSessionTimeData
                    .getTime());
        return paramView;
    }

    public static class SessionTimeData {
        String time;

        public SessionTimeData(String time) {
            this.time = time;
        }

        public String getTime() {
            return this.time;
        }
    }
}