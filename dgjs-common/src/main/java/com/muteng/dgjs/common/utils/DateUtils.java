package com.muteng.dgjs.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by snow on 2015/8/1.
 * 时间工具类
 */
public abstract class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private final static Logger logger =  LoggerFactory.getLogger(DateUtils.class);

    public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
    public static final String DATE_TIME_SHORT_FORMAT = "yyyyMMddHHmm";
    public static final String DATE_TIMESTAMP_SHORT_FORMAT = "yyyyMMddHHmmss";
    public static final String DATE_TIMESTAMP_LONG_FORMAT = "yyyyMMddHHmmssS";
    public static final String DATE_CH_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIME_SHORT_FORMAT = "HHmmss";

    public static final String DAYTIME_START = "00:00:00";
    public static final String DAYTIME_END = "23:59:59";

    private DateUtils() {
    }

    private static final String[] FORMATS = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "HH:mm",
            "HH:mm:ss", "yyyy-MM", "yyyy-MM-dd HH:mm:ss.S","yyyy-MM-dd HH:mm:ss" ,"yyyyMMddHHmmss"};

    public static Date convert(String str) {
        if (str != null && str.length() > 0) {
            if (str.length() > 10 && str.charAt(10) == 'T') {
                str = str.replace('T', ' '); // 去掉json-lib加的T字母
            }
            for (String format : FORMATS) {
                if (str.length() == format.length()) {
                    try {
                        Date date = new SimpleDateFormat(format).parse(str);
                        return date;
                    } catch (ParseException e) {
                        logger.warn(e.getMessage(),e);
                    }
                }
            }
        }
        return null;
    }

    public static Date convert(String str, String format) {
        if (!StringUtils.isEmpty(str)) {
            try {
                Date date = new SimpleDateFormat(format).parse(str);
                return date;
            } catch (ParseException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * 时间拼接 将日期和实现拼接 ymd 如2012-05-15 hm 如0812
     *
     *
     * @date 2012-11-22 下午4:48:43
     */
    public static Date concat(String ymd, String hm) {
        if (!StringUtils.isEmpty(ymd) && !StringUtils.isEmpty(hm)) {
            try {
                String dateString = ymd.concat(" ").concat(
                        hm.substring(0, 2).concat(":").concat(hm.substring(2, 4)).concat(":00"));
                Date date = DateUtils.convert(dateString, DATE_TIME_FORMAT);
                return date;
            } catch (NullPointerException e) {
                logger.warn(e.getMessage(),e);
            }
        }
        return null;
    }

    /**
     * 根据传入的日期返回年月日的6位字符串，例：20101203
     *
     * @date 2012-11-28 下午8:35:55
     */
    public static String getDay(Date date) {
        return convert(date, DATE_SHORT_FORMAT);
    }

    /**
     * 根据传入的日期返回中文年月日字符串，例：2010年12月03日
     *
     * @date 2012-11-28 下午8:35:55
     */
    public static String getChDate(Date date) {
        return convert(date, DATE_CH_FORMAT);
    }


    public static String convert(Date date) {
        return convert(date, DATE_TIME_FORMAT);
    }

    public static String convert(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }

        if (null == dateFormat) {
            dateFormat = DATE_TIME_FORMAT;
        }

        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * 返回该天从00:00:00开始的日期
     *
     * @param date
     * @return
     */
    public static Date getStartDatetime(Date date) {
        String thisdate = convert(date, DATE_FORMAT);
        return convert(thisdate + " " + DAYTIME_START);

    }

    /**
     * 返回n天后从00:00:00开始的日期
     *
     * @param date
     * @return
     */
    public static Date getStartDatetime(Date date, Integer diffDays) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String thisdate = df.format(date.getTime() + 1000L * 24 * 60 * 60 * diffDays);
        return convert(thisdate + " " + DAYTIME_START);
    }

    /**
     * 返回该天到23:59:59结束的日期
     *
     * @param date
     * @return
     */
    public static Date getEndDatetime(Date date) {
        String thisdate = convert(date, DATE_FORMAT);
        return convert(thisdate + " " + DAYTIME_END);

    }

    /**
     * 返回n天到23:59:59结束的日期
     *
     * @param date
     * @return
     */
    public static Date getEndDatetime(Date date, Integer diffDays) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
        String thisdate = df.format(date.getTime() + 1000L * 24 * 60 * 60 * diffDays);
        return convert(thisdate + " " + DAYTIME_END);

    }

    /**
     * 返回该日期的最后一刻，精确到纳秒
     *
     * @return
     */
    public static Timestamp getLastEndDatetime(Date endTime) {
        Timestamp ts = new Timestamp(endTime.getTime());
        ts.setNanos(999999999);
        return ts;
    }

    /**
     * 返回该日期加1秒
     *
     * @return
     */
    public static Timestamp getEndTimeAdd(Date endTime) {
        Timestamp ts = new Timestamp(endTime.getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(ts);
        c.add(Calendar.MILLISECOND, 1000);
        c.set(Calendar.MILLISECOND, 0);
        return new Timestamp(c.getTimeInMillis());
    }

    /**
     * 相对当前日期，增加或减少天数
     *
     * @param date
     * @param day
     * @return
     */
    public static String addDay(Date date, int day) {
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

        return df.format(new Date(date.getTime() + 1000L * 24 * 60 * 60 * day));
    }

    /**
     * 相对当前日期，增加或减少天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDayToDate(Date date, long day) {
        return new Date(date.getTime() + 1000 * 24 * 60 * 60 * day);
    }

    /**
     *
     * 格式转换<br>
     * yyyy-MM-dd hh:mm:ss 和 yyyyMMddhhmmss 相互转换<br>
     * yyyy-mm-dd 和yyyymmss 相互转换
     * @param value 日期
     * @return String
     */
    public static String formatString(String value) {
        String sReturn = "";
        if (value == null || "".equals(value)) {
            return sReturn;
        }
        if (value.length() == 14) {   //长度为14格式转换成yyyy-mm-dd hh:mm:ss
            sReturn = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8) + " "
                    + value.substring(8, 10) + ":" + value.substring(10, 12) + ":" + value.substring(12, 14);
            return sReturn;
        }
        if (value.length() == 19) {   //长度为19格式转换成yyyymmddhhmmss
            sReturn = value.substring(0, 4) + value.substring(5, 7) + value.substring(8, 10) + value.substring(11, 13)
                    + value.substring(14, 16) + value.substring(17, 19);
            return sReturn;
        }
        if(value.length() == 10){     //长度为10格式转换成yyyymmhh
            sReturn = value.substring(0, 4) + value.substring(5,7) + value.substring(8,10);
        }
        if(value.length() == 8){      //长度为8格式转化成yyyy-mm-dd
            sReturn = value.substring(0, 4) + "-" + value.substring(4, 6) + "-" + value.substring(6, 8);
        }
        return sReturn;
    }

    /**
     * 返回两个时间的相差天数
     *
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差天数
     */
    @Deprecated
    public static Long getTimeDiff(String startTime, String endTime) {

        return getDayDiff(startTime,endTime);
    }

    /**
     * 返回两个时间的相差天数
     *
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差天数
     */
    @Deprecated
    public static Long getTimeDiff(Date startTime, Date endTime) {
        return getDayDiff(startTime,endTime);
    }

    /**
     * 返回两个时间的相差天数
     *
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差天数
     */
    public static Long getDayDiff(String startTime, String endTime) {
        Long days = null;
        Date startDate = null;
        Date endDate = null;
        try {
            if (startTime.length() == 10 && endTime.length() == 10) {
                startDate = new SimpleDateFormat(DATE_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_FORMAT).parse(endTime);
            } else {
                startDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(endTime);
            }

            days = getDayDiff(startDate, endDate);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
            days = null;
        }
        return days;
    }

    /**
     * 返回两个时间的相差天数
     *
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差天数
     */
    public static Long getDayDiff(Date startTime, Date endTime) {
        Long days = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE,0);
        c.set(Calendar.SECOND,0);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        c.set(Calendar.HOUR_OF_DAY,0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        long l_e = c.getTimeInMillis();
        days = (l_e - l_s) / 86400000;
        return days;
    }

    /**
     * 返回两个时间的相差分钟数
     *
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差分钟数
     */
    public static Long getMinuteDiff(Date startTime, Date endTime) {
        Long minutes = null;

        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        minutes = (l_e - l_s) / (1000L * 60);
        return minutes;
    }

    /**
     * 返回两个时间的相差秒数
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差秒数
     */
    public static Long getSecondDiff(Date startTime, Date endTime) {
        Long minutes = null;
        Calendar c = Calendar.getInstance();
        c.setTime(startTime);
        long l_s = c.getTimeInMillis();
        c.setTime(endTime);
        long l_e = c.getTimeInMillis();
        minutes = (l_e - l_s) / 1000L;
        return minutes;
    }
    /**
     * 返回两个时间的相差秒数
     * @param startTime
     *            对比的开始时间
     * @param endTime
     *            对比的结束时间
     * @return 相差秒数
     */
    public static Long getSecondDiff(String startTime, String endTime) {
        Long seconds = null;
        Date startDate = null;
        Date endDate = null;
        try {
            if (startTime.length() == 10 && endTime.length() == 10) {
                startDate = new SimpleDateFormat(DATE_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_FORMAT).parse(endTime);
            } else {
                startDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(startTime);
                endDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(endTime);
            }

            seconds = getSecondDiff(startDate, endDate);
        } catch (ParseException e) {
            logger.warn(e.getMessage());
            seconds = null;
        }
        return seconds;
    }

    public static String getPidFromDate(Date date) {
        if (date == null) {
            return "";
        }

        String m = convert(date, "yyyyMM");
        String d = convert(date, "dd");

        if (Integer.valueOf(d) <= 10) {
            d = "01";
        } else if (Integer.valueOf(d) <= 20) {
            d = "02";
        } else {
            d = "03";
        }

        return m.concat(d);
    }
}
