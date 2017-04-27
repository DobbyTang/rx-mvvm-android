package com.rx.utillibs;


import android.annotation.SuppressLint;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateFormatUtil
 * @author create by Tang
 * @date 2017/4/27 下午2:59
 * @description: 时间处理工具类
 * 把时间转换成更容易看懂的格式
 */
public class DateFormatUtil {


    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";

    @SuppressLint("SimpleDateFormat")
    public static String format(Date date) {
        DateTime nowDateTime = DateTime.now();
        DateTime dateTime = new DateTime(date);

        int seconds = Seconds.secondsBetween(dateTime,nowDateTime).getSeconds();
        if (seconds < 60) {
            return seconds + ONE_SECOND_AGO;
        }

        int minutes = Minutes.minutesBetween(dateTime,nowDateTime).getMinutes();
        if (minutes < 60) {
            return minutes + ONE_MINUTE_AGO;
        }

        int day = nowDateTime.getDayOfYear() - dateTime.getDayOfYear();
        int year = nowDateTime.getYear() - dateTime.getYear();
        if (year < 1 && day < 1) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("今天 HH:mm");
            return simpleDateFormat.format(date);
        }

        if (year < 1 && day < 2) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("昨天 HH:mm");
            return simpleDateFormat.format(date);
        }
        if (year < 1) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");
            return simpleDateFormat.format(date);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        return simpleDateFormat.format(date);


    }


}
