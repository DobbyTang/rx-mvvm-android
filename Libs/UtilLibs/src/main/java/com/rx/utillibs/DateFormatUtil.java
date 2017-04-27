package com.rx.utillibs;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateFormatUtil
 * @author create by Tang
 * @date 2017/4/27 下午2:59
 * @description:
 */
public class DateFormatUtil {


    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
//    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
//    private static final String ONE_YEAR_AGO = "年前";

//    public static void main(String[] args) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
//        Date date = format.parse("2013-11-11 18:35:35");
//        System.out.println(format(date));
//    }

    public static String format(Date date) {
        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("今天 hh:mm");
            return simpleDateFormat.format(date);
        }
        if (delta < 48L * ONE_HOUR) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("昨天 hh:mm");
            return simpleDateFormat.format(date);
        }
        if (!(delta < 12L * 4L * ONE_WEEK)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
            return simpleDateFormat.format(date);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 hh:mm");
        return simpleDateFormat.format(date);


    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }

}
