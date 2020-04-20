package com.yoozoo.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间相关的工具类
 * Created by guoxx on 2018/11/30.
 */
public class DateUtils {


    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int day){
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 得到几天后的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfter(Date d,int day){
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return now.getTime();
    }

    /**
     * 将日期类型转换为时间戳
     * @param date 日期
     * @return 时间错
     */
    public static Timestamp convertDateToTimestamp(Date date){
        return new Timestamp(date.getTime());
    }

    /**
     * 将日期类型格式化后返回
     * @param date  日期
     * @return 格式化后的字符串
     */
    public static String formatDateToString(Date date){
        String str = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        str = format.format(date);
        return  str;
    }
}
