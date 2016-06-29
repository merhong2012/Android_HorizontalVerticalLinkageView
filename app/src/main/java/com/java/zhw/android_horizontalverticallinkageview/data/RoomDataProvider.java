package com.java.zhw.android_horizontalverticallinkageview.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zhw
 * Description:
 */
public class RoomDataProvider {
    private static List<SessionRoomData> sessionRoomData = new ArrayList<>();

    public static List<SessionRoomData> getCurrentSessionRoomData() {
        sessionRoomData.clear();
        sessionRoomData.add(new SessionRoomData("1", "地点一"));
        sessionRoomData.add(new SessionRoomData("2", "地点二"));
        sessionRoomData.add(new SessionRoomData("3", "地点三"));
        sessionRoomData.add(new SessionRoomData("4", "地点四"));
        sessionRoomData.add(new SessionRoomData("5", "地点五"));
        sessionRoomData.add(new SessionRoomData("6", "地点六"));
        sessionRoomData.add(new SessionRoomData("7", "地点七"));
        sessionRoomData.add(new SessionRoomData("8", "地点八"));
        sessionRoomData.add(new SessionRoomData("9", "地点九"));
        sessionRoomData.add(new SessionRoomData("10", "地点十"));
        sessionRoomData.add(new SessionRoomData("11", "地点十一"));
        return sessionRoomData;
    }

}
