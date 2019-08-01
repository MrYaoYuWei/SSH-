package com.university.haha.util;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 时间转换工具
 */
public class DateUtil {
    private  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 时间转换工具
     * @param date
     * @return
     */
    public  String DateToStr(Date date) {
        return sdf.format(date);
    }
}
