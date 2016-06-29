package com.java.zhw.android_horizontalverticallinkageview.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateParser {

    public static long getDurationMinutes(String paramString1, String startTime, String endTime) {
        SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat(paramString1);
        SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat(paramString1);
        long l1 = 0L;
        try {
            localSimpleDateFormat1.format(localSimpleDateFormat1.parse(startTime));
            Calendar localCalendar = localSimpleDateFormat1.getCalendar();
            localSimpleDateFormat2.format(localSimpleDateFormat2.parse(endTime));
            l1 = localSimpleDateFormat2.getCalendar().getTimeInMillis() - localCalendar
                    .getTimeInMillis();
            long l2 = l1 / 30000L;
            return l2;
        } catch (ParseException localParseException) {
            localParseException.printStackTrace();
        }
        return l1;
    }
}