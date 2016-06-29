package com.java.zhw.android_horizontalverticallinkageview.data;

import org.json.JSONObject;

public class SessionRoomData extends BaseData {
    private static final long serialVersionUID = 4064966270600654890L;
    private boolean mSelected = false;

    public SessionRoomData(RoomData paramRoomData) {
        setId(paramRoomData.getId());
        setName(paramRoomData.getName());
    }

    public SessionRoomData(SessionRoomData paramSessionRoomData) {
        setId(paramSessionRoomData.getId());
        setName(paramSessionRoomData.getName());
    }

    public SessionRoomData(String paramString1, String paramString2) {
        setId(paramString1);
        setName(paramString2);
    }

    public SessionRoomData(String paramString, JSONObject paramJSONObject)
            throws Exception {
        super(paramString, paramJSONObject);
    }


    protected void init() {
        super.init();
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    protected void parseJSONObject(JSONObject paramJSONObject)
            throws Exception {
        super.parseJSONObject(paramJSONObject);
    }

    public void setSelected(boolean paramBoolean) {
        this.mSelected = paramBoolean;
    }


    public String toString() {
        return "SessionGroupData []" + getId() + " , " + getName();
    }
}