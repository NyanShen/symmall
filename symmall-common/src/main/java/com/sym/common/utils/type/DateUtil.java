package com.sym.common.utils.type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期时间工具类
 *
 * 提供常用的日期格式化、转换、计算等方法
 */
public class DateUtil {

    /** 默认日期格式: yyyy-MM-dd */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /** 默认时间格式: HH:mm:ss */
    public static final String TIME_PATTERN = "HH:mm:ss";

    /** 默认日期时间格式: yyyy-MM-dd HH:mm:ss */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 默认日期时间格式(含毫秒): yyyy-MM-dd HH:mm:ss.SSS */
    public static final String DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    /** 紧凑型日期格式: yyyyMMdd */
    public static final String COMPACT_DATE_PATTERN = "yyyyMMdd";

    /** 紧凑型日期时间格式: yyyyMMddHHmmss */
    public static final String COMPACT_DATETIME_PATTERN = "yyyyMMddHHmmss";

    private DateUtil() {
    }

    /**
     * 获取当前日期字符串 (yyyy-MM-dd)
     *
     * @return 当前日期字符串
     */
    public static String getCurrentDate() {
        return formatDate(new Date(), DATE_PATTERN);
    }

    /**
     * 获取当前时间字符串 (HH:mm:ss)
     *
     * @return 当前时间字符串
     */
    public static String getCurrentTime() {
        return formatDate(new Date(), TIME_PATTERN);
    }

    /**
     * 获取当前日期时间字符串 (yyyy-MM-dd HH:mm:ss)
     *
     * @return 当前日期时间字符串
     */
    public static String getCurrentDateTime() {
        return formatDate(new Date(), DATETIME_PATTERN);
    }

    /**
     * 格式化日期为字符串
     *
     * @param date 日期对象
     * @param pattern 日期格式
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 格式化日期为字符串 (默认格式: yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期对象
     * @return 格式化后的日期字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATETIME_PATTERN);
    }

    /**
     * 将字符串解析为日期对象
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return 日期对象
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("日期格式不正确: " + dateStr, e);
        }
    }

    /**
     * 将字符串解析为日期对象 (默认格式: yyyy-MM-dd HH:mm:ss)
     *
     * @param dateStr 日期字符串
     * @return 日期对象
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, DATETIME_PATTERN);
    }

    /**
     * Date 转 LocalDate
     *
     * @param date Date对象
     * @return LocalDate对象
     */
    public static LocalDate toLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date Date对象
     * @return LocalDateTime对象
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate LocalDate对象
     * @return Date对象
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime LocalDateTime对象
     * @return Date对象
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取两个日期之间的天数差
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 天数差
     */
    public static long daysBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return 0;
        }
        LocalDate startLocalDate = toLocalDate(startDate);
        LocalDate endLocalDate = toLocalDate(endDate);
        return ChronoUnit.DAYS.between(startLocalDate, endLocalDate);
    }

    /**
     * 获取两个日期时间之间的小时差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime 结束日期时间
     * @return 小时差
     */
    public static long hoursBetween(Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        LocalDateTime startLocalDateTime = toLocalDateTime(startDateTime);
        LocalDateTime endLocalDateTime = toLocalDateTime(endDateTime);
        return ChronoUnit.HOURS.between(startLocalDateTime, endLocalDateTime);
    }

    /**
     * 获取两个日期时间之间的分钟差
     *
     * @param startDateTime 开始日期时间
     * @param endDateTime 结束日期时间
     * @return 分钟差
     */
    public static long minutesBetween(Date startDateTime, Date endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return 0;
        }
        LocalDateTime startLocalDateTime = toLocalDateTime(startDateTime);
        LocalDateTime endLocalDateTime = toLocalDateTime(endDateTime);
        return ChronoUnit.MINUTES.between(startLocalDateTime, endLocalDateTime);
    }

    /**
     * 在指定日期上增加天数
     *
     * @param date 原始日期
     * @param days 要增加的天数(可为负数)
     * @return 新的日期
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date).plusDays(days);
        return toDate(localDate);
    }

    /**
     * 在指定日期上增加月数
     *
     * @param date 原始日期
     * @param months 要增加的月数(可为负数)
     * @return 新的日期
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date).plusMonths(months);
        return toDate(localDate);
    }

    /**
     * 在指定日期上增加年数
     *
     * @param date 原始日期
     * @param years 要增加的年数(可为负数)
     * @return 新的日期
     */
    public static Date addYears(Date date, int years) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date).plusYears(years);
        return toDate(localDate);
    }

    /**
     * 在指定日期时间上增加小时数
     *
     * @param dateTime 原始日期时间
     * @param hours 要增加的小时数(可为负数)
     * @return 新的日期时间
     */
    public static Date addHours(Date dateTime, int hours) {
        if (dateTime == null) {
            return null;
        }
        LocalDateTime localDateTime = toLocalDateTime(dateTime).plusHours(hours);
        return toDate(localDateTime);
    }

    /**
     * 在指定日期时间上增加分钟数
     *
     * @param dateTime 原始日期时间
     * @param minutes 要增加的分钟数(可为负数)
     * @return 新的日期时间
     */
    public static Date addMinutes(Date dateTime, int minutes) {
        if (dateTime == null) {
            return null;
        }
        LocalDateTime localDateTime = toLocalDateTime(dateTime).plusMinutes(minutes);
        return toDate(localDateTime);
    }

    /**
     * 判断日期1是否在日期2之后
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true: date1在date2之后
     */
    public static boolean isAfter(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.after(date2);
    }

    /**
     * 判断日期1是否在日期2之前
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true: date1在date2之前
     */
    public static boolean isBefore(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.before(date2);
    }

    /**
     * 判断两个日期是否相等
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return true: 两个日期相等
     */
    public static boolean isEqual(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        return date1.equals(date2);
    }

    /**
     * 获取指定日期所在月份的第一天
     *
     * @param date 指定日期
     * @return 月份第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date);
        LocalDate firstDay = localDate.withDayOfMonth(1);
        return toDate(firstDay);
    }

    /**
     * 获取指定日期所在月份的最后一天
     *
     * @param date 指定日期
     * @return 月份最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date);
        LocalDate lastDay = localDate.withDayOfMonth(localDate.lengthOfMonth());
        return toDate(lastDay);
    }

    /**
     * 获取指定日期所在年份的第一天
     *
     * @param date 指定日期
     * @return 年份第一天
     */
    public static Date getFirstDayOfYear(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date);
        LocalDate firstDay = localDate.withDayOfYear(1);
        return toDate(firstDay);
    }

    /**
     * 获取指定日期所在年份的最后一天
     *
     * @param date 指定日期
     * @return 年份最后一天
     */
    public static Date getLastDayOfYear(Date date) {
        if (date == null) {
            return null;
        }
        LocalDate localDate = toLocalDate(date);
        LocalDate lastDay = localDate.withDayOfYear(localDate.lengthOfYear());
        return toDate(lastDay);
    }

    /**
     * 获取当前时间戳(毫秒)
     *
     * @return 当前时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取指定日期的时间戳(毫秒)
     *
     * @param date 指定日期
     * @return 时间戳
     */
    public static long getTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
        return date.getTime();
    }

    /**
     * 时间戳(毫秒)转Date
     *
     * @param timestamp 时间戳
     * @return Date对象
     */
    public static Date timestampToDate(long timestamp) {
        return new Date(timestamp);
    }

    /**
     * 使用Java 8 DateTimeFormatter格式化LocalDateTime
     *
     * @param localDateTime LocalDateTime对象
     * @param pattern 日期格式
     * @return 格式化后的字符串
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    /**
     * 使用Java 8 DateTimeFormatter解析字符串为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern 日期格式
     * @return LocalDateTime对象
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || dateTimeStr.isEmpty()) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateTimeStr, formatter);
    }
}
