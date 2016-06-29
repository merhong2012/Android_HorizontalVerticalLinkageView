package com.java.zhw.android_horizontalverticallinkageview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.java.zhw.android_horizontalverticallinkageview.R;
import com.java.zhw.android_horizontalverticallinkageview.adapter.SessionRoomAdapter;
import com.java.zhw.android_horizontalverticallinkageview.adapter.SessionTimeAdapter;
import com.java.zhw.android_horizontalverticallinkageview.listener.LableViewListener;
import com.java.zhw.android_horizontalverticallinkageview.utils.DUtils;
import com.java.zhw.android_horizontalverticallinkageview.utils.DateParser;
import com.java.zhw.android_horizontalverticallinkageview.utils.ScreenUtils;
import com.zhy.autolayout.AutoFrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SessionTilesByRoom extends AutoFrameLayout
        implements LableViewListener {
    SessionRoomAdapter sessionRoomAdapter;
    SessionTimeAdapter sessionTimeAdapter;
    int lableWidth, lableHeight;
    private LableViewListener lableViewListener;
    TypedArray a;

    public SessionTilesByRoom(Context paramContext) {
        super(paramContext);
        // init(null);
    }


    public SessionTilesByRoom(Context paramContext, AttributeSet attrs) {
        super(paramContext, attrs);
        init(attrs);
    }

    public SessionTilesByRoom(Context paramContext, AttributeSet attrs, int paramInt) {
        super(paramContext, attrs, paramInt);
        init(attrs);
    }

    public void setLableViewListener(LableViewListener lableViewListener) {
        this.lableViewListener = lableViewListener;
    }

    public void setAdapter(SessionRoomAdapter paramSessionRoomAdapter, SessionTimeAdapter
            paramSessionTimeAdapter) {
        this.sessionRoomAdapter = paramSessionRoomAdapter;
        this.sessionTimeAdapter = paramSessionTimeAdapter;
    }

    private void init(AttributeSet attrs) {
        a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.SessionLable, 0, 0);
        lableWidth = (ScreenUtils.getScreenWidth(getContext()) - (int) getContext().getResources
                ().getDimension(R.dimen
                .session_time_width)) / 3;
        lableHeight = (int) getContext().getResources().getDimension(R.dimen
                .session_time_height);
    }

    public void drawLine() {
        for (int j = 1; j <= sessionRoomAdapter.getCount(); j++) {
            for (int i = 1; i <= sessionTimeAdapter.getCount(); i++) {
                View rowLine = new View(getContext());
                LayoutParams layoutParams = new LayoutParams(lableWidth * j, 1);
                layoutParams.setMargins(0, lableHeight * i, 0, 0);
                rowLine.setBackgroundResource(R.color.color_list_dialog);
                addView(rowLine, layoutParams);

              /*  View heightLine = new View(getContext());
                LayoutParams ll = new LayoutParams(1, lableHeight * i);
                ll.setMargins(lableWidth * j, 0, 0, 0);
                heightLine.setBackgroundResource(R.color.color_line_login);
                addView(heightLine, ll);*/
            }
        }


    }

    private void drawContent(List<JSONObject> list) {
        if (list == null || list.size() < 0)
            return;
        String start = "08:00";
        try {
            for (int i = 0; i < list.size(); i++) {
                JSONObject object = list.get(i);
                String sString = object.getString("start");
                String eString = object.getString("end");
                String title = object.getString("name");
                int room_id = Integer.valueOf(object.getString("room"));
                int sTime = (int) DateParser.getDurationMinutes("kk:mm", start, sString);
                int DurationMinutes = (int) DateParser.getDurationMinutes("kk:mm", sString,
                        eString);
               /* L.i("location start: " + sTime);
                L.i("DurationMinutes: " + DurationMinutes);
                L.i("lableHeight: " + lableHeight);*/
                TextView tv = new TextView(getContext());//stime: 7:30   entime: 8:00
                tv.setText(title);
                tv.setTextColor(Color.WHITE);
                tv.setGravity(Gravity.CENTER);
                tv.setPadding(1, 1, 1, 1);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 28);
                tv.setBackgroundResource(R.drawable.s_bg_lable);
                final LinearLayout linearLayout = new LinearLayout(getContext());
                LayoutParams ll = new LayoutParams(lableWidth, DUtils.dp2px(getContext(),
                        DurationMinutes));
                ll.setMargins((room_id - 1) * lableWidth, DUtils.dp2px(getContext(), sTime), 0, 0);
                linearLayout.setPadding(1, 1, 1, 1);
                linearLayout.addView(tv, ll);
                linearLayout.setTag(R.id.tag_content, object);
                linearLayout.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        lableViewListener.onLableClickListener(linearLayout);
                    }
                });
                addView(linearLayout, ll);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLableClickListener(LinearLayout view) {
        if (lableViewListener != null) {
            lableViewListener.onLableClickListener(view);
        }
    }


    public void update(List<JSONObject> list) {
        removeAllViews();
        drawLine();
        drawContent(list);
    }

    private static class AttachView {
        LayoutParams layoutParam;
        View view;

        public AttachView(View paramView, LayoutParams paramLayoutParams) {
            this.view = paramView;
            this.layoutParam = paramLayoutParams;
        }

        public LayoutParams getLayoutParam() {
            return this.layoutParam;
        }

        public View getView() {
            return this.view;
        }
    }

}