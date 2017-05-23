package com.zwlj.common.support;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ElFunction {

    public static String longToDate(long time) {
        DateTime dateTime = new DateTime(time);
        DateTimeFormatter dateTimeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
        return dateTimeFormat.print(dateTime);
    }

    public static String longToDate(long time, String pattern) {
        DateTime dateTime = new DateTime(time);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
        return dateTimeFormatter.print(dateTime);
    }
}
