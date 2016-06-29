package com.java.zhw.android_horizontalverticallinkageview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.java.zhw.android_horizontalverticallinkageview.R;
import com.java.zhw.android_horizontalverticallinkageview.data.SessionRoomData;
import com.java.zhw.android_horizontalverticallinkageview.utils.ViewHolder;

import org.json.JSONObject;

import java.util.List;

/**
 * Author: Eddy.
 * Description:
 */
public class SessionLablePopListAdapter extends BaseAdapter {
    public static final String tag = "SessionLablePopListAdapter";
    private Context mContext;
    private List<JSONObject> list;
    private LayoutInflater inflater;

    private SessionRoomAdapter roomAdapter;


    public SessionLablePopListAdapter(Context mContext,
                                      SessionRoomAdapter roomAdapter) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.roomAdapter = roomAdapter;
    }

    public void updateSessionList(List<JSONObject> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    @Override
    public JSONObject getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_layout_dialog_list, null);
        }
        TextView time = ViewHolder.getHolderView(view, R.id.tv_time);
        TextView place = ViewHolder.getHolderView(view, R.id.tv_place);
        TextView title = ViewHolder.getHolderView(view, R.id.tv_title);
        int place_id = Integer.valueOf(getItem(i).optString("place"));
        time.setText(getItem(i).optString("time"));
        if (roomAdapter != null && roomAdapter.getCount() > place_id - 1) {
            place.setText(((SessionRoomData) (roomAdapter.getItem(place_id - 1))).getName());
        }
        title.setText(getItem(i).optString("title"));
        return view;
    }
}
