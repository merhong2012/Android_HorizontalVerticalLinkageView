package com.java.zhw.android_horizontalverticallinkageview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.java.zhw.android_horizontalverticallinkageview.adapter.SessionLablePopListAdapter;
import com.java.zhw.android_horizontalverticallinkageview.adapter.SessionRoomAdapter;
import com.java.zhw.android_horizontalverticallinkageview.adapter.SessionTimeAdapter;
import com.java.zhw.android_horizontalverticallinkageview.contact.Contact;
import com.java.zhw.android_horizontalverticallinkageview.data.RoomDataProvider;
import com.java.zhw.android_horizontalverticallinkageview.listener.LableViewListener;
import com.java.zhw.android_horizontalverticallinkageview.listener.ScrollViewListener;
import com.java.zhw.android_horizontalverticallinkageview.utils.AssUtils;
import com.java.zhw.android_horizontalverticallinkageview.utils.ScreenUtils;
import com.java.zhw.android_horizontalverticallinkageview.views.AutoRadioGroup;
import com.java.zhw.android_horizontalverticallinkageview.views.HScroll;
import com.java.zhw.android_horizontalverticallinkageview.views.SessionTilesByRoom;
import com.java.zhw.android_horizontalverticallinkageview.views.VScroll;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoLayoutActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AutoLayoutActivity implements ScrollViewListener,
        LableViewListener, View.OnClickListener {
    private Context mContext;

    ImageView close;
    ListView listItems;

    AutoRadioGroup radioGroup;
    View navBar;
    LinearLayout sessionRoomContents;
    HScroll sessionRoomScroll;
    LinearLayout timeContents;
    VScroll timeScroll;
    SessionTilesByRoom sessionTiles;
    HScroll hScroll;
    VScroll vScroll;
    ImageView leftArrow;
    ImageView rightArrow;

    private LayoutInflater inflater;
    private View popView;
    private SessionLablePopListAdapter adapter;
    private JSONObject july22Obj, july23Obj, july24Obj;

    private List<JSONObject> july22ObjList, july23ObjList, july24ObjList, dataList;


    private Dialog dialog;
    private SessionRoomAdapter mSessionRoomAdapter;
    private SessionTimeAdapter mSessionTimeAdapter;

    private int mImageWidth;//导航条宽度
    private int currIndex;//当前位置


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        inflater = LayoutInflater.from(mContext);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initView();
        getLableData();
        cacheData22();

    }

    private void initView() {
        radioGroup = (AutoRadioGroup) findViewById(R.id.radioGroup);
        navBar = findViewById(R.id.nav_bar);
        sessionRoomContents = (LinearLayout) findViewById(R.id.sessionRoomContents);
        sessionRoomScroll = (HScroll) findViewById(R.id.sessionRoomScroll);
        timeContents = (LinearLayout) findViewById(R.id.timeContents);
        sessionTiles = (SessionTilesByRoom) findViewById(R.id.vhView);
        hScroll = (HScroll) findViewById(R.id.hScroll);
        vScroll = (VScroll) findViewById(R.id.vScroll);
        timeScroll = (VScroll) findViewById(R.id.timeScroll);
        leftArrow = (ImageView) findViewById(R.id.left_arrow);
        rightArrow = (ImageView) findViewById(R.id.right_arrow);

        intNavBar();

        dataList = new ArrayList<>();

        popView = inflater.inflate(R.layout.layout_list_lable, null);
        close = (ImageView) popView.findViewById(R.id.close);
        listItems = (ListView) popView.findViewById(R.id.list_items);

        dialog = new Dialog(mContext, R.style.GrayBackground);
        dialog.setContentView(popView);

        hScroll.setScrollViewListener(this);
        vScroll.setScrollViewListener(this);
        timeScroll.setScrollViewListener(this);
        sessionRoomScroll.setScrollViewListener(this);
        sessionTiles.setLableViewListener(this);
        close.setOnClickListener(this);

        mSessionTimeAdapter = new SessionTimeAdapter(this);
        for (int i = 0; i < mSessionTimeAdapter.getCount(); i++) {
            View timeView = mSessionTimeAdapter.getView(i, null, timeContents);
            timeContents.addView(timeView);
        }

        mSessionRoomAdapter = new SessionRoomAdapter(this);
        mSessionRoomAdapter.setRoomData(RoomDataProvider.getCurrentSessionRoomData());

        if (sessionRoomContents.getChildCount() <= 0) {
            for (int i = 0; i < mSessionRoomAdapter.getCount(); i++) {
                View roomView = mSessionRoomAdapter.getView(i, null, sessionRoomContents);
                sessionRoomContents.addView(roomView);
            }
            this.sessionTiles.setAdapter(mSessionRoomAdapter, mSessionTimeAdapter);
        }
        if (adapter == null) {
            adapter = new SessionLablePopListAdapter(mContext, mSessionRoomAdapter);
            listItems.setAdapter(adapter);
            listItems.setOnItemClickListener(lableItemListener);

        }
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            RadioButton radioButton = ((RadioButton) radioGroup.getChildAt(i));
            radioButton.setId(i);
        }
        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        leftArrow.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(radioGroupListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupListener = new RadioGroup
            .OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case 0:
                    if (july22ObjList != null) {
                        sessionTiles.update(july22ObjList);
                    } else {
                        cacheData22();
                    }
                    break;
                case 1:
                    if (july23ObjList != null) {
                        sessionTiles.update(july23ObjList);
                    } else {
                        cacheData23();
                    }
                    break;
                case 2:
                    if (july24ObjList != null) {
                        sessionTiles.update(july24ObjList);
                    } else {
                        cacheData24();
                    }
                    break;
                default:
                    break;
            }
            startAnimation(i);
        }
    };
    AdapterView.OnItemClickListener lableItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.i("MainActivity", adapter.getItem(i).optString("title"));
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    };

    private void getLableData() {
        july22Obj = AssUtils.getAssetsJson(mContext, Contact.SCHEDULESUMMARY_JULY22_JSON);

        july23Obj = AssUtils.getAssetsJson(mContext, Contact.SCHEDULESUMMARY_JULY23_JSON);

        july24Obj = AssUtils.getAssetsJson(mContext, Contact.SCHEDULESUMMARY_JULY24_JSON);

    }

    private void cacheData22() {
        if (july22ObjList == null) {
            july22ObjList = new ArrayList<>();
        } else
            july22ObjList.clear();
        if (july22Obj != null) {
            JSONArray array = july22Obj.optJSONArray("content");
            for (int i = 0; i < array.length(); i++) {
                july22ObjList.add(array.optJSONObject(i));
            }
        }
        sessionTiles.update(july22ObjList);
    }

    private void cacheData23() {
        if (july23ObjList == null) {
            july23ObjList = new ArrayList<>();
        } else
            july23ObjList.clear();
        if (july23Obj != null) {
            JSONArray array = july23Obj.optJSONArray("content");
            for (int i = 0; i < array.length(); i++) {
                july23ObjList.add(array.optJSONObject(i));

            }
        }
        sessionTiles.update(july23ObjList);
    }

    private void cacheData24() {
        if (july24ObjList == null) {
            july24ObjList = new ArrayList<>();
        } else
            july24ObjList.clear();
        if (july24Obj != null) {
            JSONArray array = july24Obj.optJSONArray("content");
            for (int i = 0; i < array.length(); i++) {
                july24ObjList.add(array.optJSONObject(i));
            }
        }
        sessionTiles.update(july24ObjList);
    }

    /**
     * 处理联动
     *
     * @param scrollView
     * @param x
     * @param y
     * @param oldx
     * @param oldy
     */
    @Override
    public void onScrollChanged(View scrollView, int x, int y, int oldx, int oldy) {
        if (scrollView == vScroll) {
            timeScroll.scrollTo(x, y);
        }
        if (scrollView == timeScroll) {
            vScroll.scrollTo(x, y);
        }
        if (scrollView == hScroll) {
            sessionRoomScroll.scrollTo(x, y);
        }
        if (scrollView == sessionRoomScroll) {
            hScroll.scrollTo(x, y);
        }
        updateMoreArrow();
    }

    @Override
    public void onLableClickListener(LinearLayout view) {
        String content = view.getTag(R.id.tag_content).toString();
        if (TextUtils.isEmpty(content))
            return;
        try {
            JSONObject object = new JSONObject(content);
            if (object.has("data")) {
                dataList.clear();
                JSONArray array = object.optJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    dataList.add(array.optJSONObject(i));
                }

            }
            adapter.updateSessionList(dataList);
            if (dialog != null && !dialog.isShowing()) {
                dialog.show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * 更新箭头指向
     */
    private void updateMoreArrow() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                doUpdateMoreArrow();
            }
        }, 100);
    }

    /**
     * 更新箭头指向
     */
    private void doUpdateMoreArrow() {
        int i = this.hScroll.getScrollX();
        int j = (ScreenUtils.getScreenWidth(mContext) - (int) getResources().getDimension(R.dimen
                .session_time_width))
                / 3;
        int scrollViewMeasuredWidth = hScroll.getChildAt(0).getMeasuredWidth();
        if (i < j) {
            leftArrow.setVisibility(View.GONE);
        } else {
            leftArrow.setVisibility(View.VISIBLE);
        }
        if (i + vScroll.getWidth() >= scrollViewMeasuredWidth - j) {
            rightArrow.setVisibility(View.GONE);
        } else {
            rightArrow.setVisibility(View.VISIBLE);
        }
    }

    private void intNavBar() {
        mImageWidth = ScreenUtils.getScreenWidth(mContext) / 3;

        AutoFrameLayout.LayoutParams layout = (AutoFrameLayout.LayoutParams) navBar
                .getLayoutParams();
        layout.width = mImageWidth;
        navBar.setLayoutParams(layout);
    }

    private void startAnimation(int checkedId) {
        int one = mImageWidth;
        Animation animation = new TranslateAnimation(one * currIndex, one * checkedId, 0, 0);//
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(300);
        navBar.startAnimation(animation);
        currIndex = checkedId;
    }

    @Override
    public void onClick(View v) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
