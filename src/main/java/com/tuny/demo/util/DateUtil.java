package com.tuny.demo.util;

import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间帮助类
 *
 * @author wangzhaofeng
 * @date 2018-11-20 18:13:39
 */
public class DateUtil {

    /**
     * Date转换为LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime getLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        //An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        Instant instant = date.toInstant();
        //A time-zone ID, such as {@code Europe/Paris}.(时区)
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 时间字符串转LocalDateTime
     *
     * @param timeStr
     * @param format
     * @return
     */
    public static LocalDateTime getLocalDateTime(String timeStr, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * 时间字符串转LocalDateTime
     *
     * @param timeStr
     * @return
     */
    public static LocalDateTime getLocalDateTime(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return null;
        }
        DateTimeFormatter df;
        if (timeStr.length() == 10) {
            timeStr += "T00:00:00";
            df = DateTimeFormatter.ISO_DATE_TIME;
        } else if (timeStr.contains(" ")) {
            df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            df = DateTimeFormatter.ISO_DATE_TIME;
        }
        return LocalDateTime.parse(timeStr, df);
    }

    /**
     * LocalDateTime格式化字符串
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String toString(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(format));
    }

    /**
     * LocalDateTime格式化字符串（yyyy-MM-dd HH:mm:ss）
     *
     * @param localDateTime
     * @return
     */
    public static String toString(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return toString(localDateTime, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime
     */
    public static Date getDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        //Combines this date-time with a time-zone to create a  ZonedDateTime.
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * LocalDateTime转换为友好时间
     *
     * @param localDateTime 时间
     */
    public static String getFriendlyDate(LocalDateTime localDateTime) {
        return DateUtil.getFriendlyDate(localDateTime, "yyyy-MM-dd HH:mm");
    }

    /**
     * LocalDateTime转换为友好时间
     *
     * @param localDateTime 时间
     * @param format 时间格式
     */
    public static String getFriendlyDate(LocalDateTime localDateTime, String format) {
        if (localDateTime == null) {
            return null;
        }
        //以当天的0点为结束时间，计算传入开始时间的间隔
        Duration durationStartOfDay = Duration.between(localDateTime, LocalDate.now().atStartOfDay());
        //以当前时间为结束时间，计算传入开始时间的间隔
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        StringBuilder str = new StringBuilder();
        if (durationStartOfDay.toDays() > 6) {
            // 超过7天
            return toString(localDateTime, format);
        } else if (durationStartOfDay.toDays() > 1) {
            // 3-7天
            str.append(duration.toDays()).append("天之前");
        } else if (durationStartOfDay.toDays() == 1) {
            // 2天
            str.append("前天");
        } else if (durationStartOfDay.toNanos() > 0) {
            // 1天
            str.append("昨天");
        } else if (duration.toHours() > 0) {
            // 1-23小时
            str.append(duration.toHours()).append("小时之前");
        } else if (duration.toMinutes() > 0) {
            // 1-60分钟
            str.append(duration.toMinutes()).append("分钟之前");
        } else {
            // 0-1分钟
            str.append(duration.toMillis() / 1000).append("秒之前");
        }
        return str.toString();
    }

}
