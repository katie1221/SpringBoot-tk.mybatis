package com.example.tkmybatisdemo.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    /**
     * Format:yyyy-MM-dd
     */
    public static final String DATESHOWFORMAT = "yyyy-MM-dd";

    public static final String DATEYMFORMAT = "yyyy-MM";

    /**
     * Format:yyyyMMdd
     */
    public static final String DATESTOREFORMAT = "yyyyMMdd";

    /**
     * Format:MMdd
     */
    public static final String DATEMDSTOREFORMAT = "MMdd";

    /**
     * Format:HH:mm:ss
     */
    public static final String TIMESHOWFORMAT = "HH:mm:ss";

    /**
     * FormatHHmmss
     */
    public static final String TIMESTOREFORMAT = "HHmmss";

    /**
     * Format:yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIMESHOWFORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * Format:yyyyMMddHHmmss
     */
    public static final String DATETIMESTOREFORMAT = "yyyyMMddHHmmss";
    /**
     * Format:yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    public static final String DATETIMEFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    /**
     * 获取当前时间
     * @return
     */
    public static Date nowDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @param startDate
     *            开始时间，如：2008-12-03 11:00:00
     * @param endDate
     *            结束时间，如：2009-12-31 11:00:00
     * @return long 间隔天数(long)
     */
    public static long getBetweenDays(String startDate, String endDate) {
        if (endDate == null || startDate == null) {
            return -1;
        }
        Date dateStart = isDate(startDate);
        if (null == dateStart) {
            return -1;
        }
        Date dateEnd = isDate(endDate);
        if (null == dateEnd) {
            return -1;
        }
        return getBetweenDays(dateStart, dateEnd);
    }

    /**
     * 计算两个日期的间隔天数
     *
     * @param startDate
     *            开始时间，如：2008-12-03 11:00:00
     * @param endDate
     *            结束时间，如：2009-12-31 11:00:00
     * @return long 间隔天数(long)
     */
    public static long getBetweenDays(Date startDate, Date endDate) {
        if (endDate == null || startDate == null) {
            return -1;
        }
        Long days = endDate.getTime() - startDate.getTime();
        days = days / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 获取与指定日期相差指定 天数 的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param dayCount
     *            向前或向后的天数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterDate(String baseDate, int dayCount, String patternString) {
        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.set(year, month - 1, date);
        calendar.add(Calendar.DATE, dayCount);
        Date _date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(_date);
        return dateString;
    }

    /**
     * 字符串转成日期
     * @param value
     * @param pattern
     * @return
     */
    public static Date str2Date(String value, String pattern) {
        if (value == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return new Date(sdf.parse(value).getTime());
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 获取与指定日期相差指定 天数 的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param dayCount
     *            向前或向后的天数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterDate(Date baseDate, int dayCount, String patternString) {
        String _baseDate = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getAfterDate(_baseDate, dayCount, patternString);
    }

    /**
     * 获取与指定日期相差指定 月数 的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterMonth(String baseDate, int monthCount, String patternString) {
        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(Calendar.MONTH, monthCount);
        Date _date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(_date);
        return dateString;
    }

    /**
     * 获取指定月第一天
     *
     * @return
     */
    public static Date getFirstThisDate(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = ca.getTime();
        String dateString = getDateString(firstDate, DATESHOWFORMAT);
        return getDateToString(dateString, DATESHOWFORMAT);
    }

    /**
     * 获取指定时间的月份最后一天
     * @param date
     * @return
     */
    public static Date getLastThisDate(Date date)
    {
        int year = getYear(date);
        int month = getMonth(date);
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime()) + " 23:59:59";
        return getDateToString(lastDayOfMonth, DATETIMESHOWFORMAT);
    }


    /**
     * 获取当前时间
     * @return
     */
    public static long getCurrentTimeLong() {
        return System.currentTimeMillis();
    }


    public static String getAfterHour(String baseDate, int hourCount, String patternString) {
        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(Calendar.HOUR, hourCount);
        Date _date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(_date);
        return dateString;
    }


    /**
     * 获取与指定日期相差指定 时间 的日期(通用)
     * @param baseDate
     * @param count 指定时间差
     * @param calendarType 时间差类型
     * @param patternString
     * @return
     */
    public static String getAfterCalendarType(String baseDate, int count, int calendarType, String patternString) {
        int year = Integer.parseInt(baseDate.substring(0, 4));
        int month = Integer.parseInt(baseDate.substring(5, 7));
        int date = Integer.parseInt(baseDate.substring(8, 10));
        Calendar calendar = Calendar.getInstance();
        if (DATETIMESHOWFORMAT.equals(patternString)) {
            int hour = Integer.parseInt(baseDate.substring(11, 13));
            int minute = Integer.parseInt(baseDate.substring(14, 16));
            int second = Integer.parseInt(baseDate.substring(17, 19));
            calendar.set(year, month - 1, date, hour, minute, second);
        } else {
            calendar.set(year, month - 1, date);
        }
        calendar.add(calendarType, count);
        Date _date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String dateString = formatter.format(_date);
        return dateString;
    }

    /**
     * 获取与指定日期相差指定 月数 的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负数
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 处理后的日期字符
     */
    public static String getAfterMonth(Date baseDate, int monthCount, String patternString) {
        String _baseDate = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getAfterMonth(_baseDate, monthCount, patternString);
    }

    /**
     * 获取与指定日期相差指定 月数 并减去天数的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param dateCount
     *            加或减去的天数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getEndDate(String baseDate, int monthCount, int dateCount, String patternString) {
        int day = Integer.parseInt(baseDate.substring(8, 10));
        String endDate = getAfterMonth(baseDate, monthCount, patternString);
        int endDay = Integer.parseInt(endDate.substring(8, 10));
        // 说明日期没变
        if (endDay == day) {
            // 月数为正则为减一
            if (monthCount > 0) {
                endDate = getAfterDate(endDate, dateCount, patternString);
            } else {
                endDate = getAfterDate(endDate, dateCount, patternString);
            }
        } else { // 日期已变
            if (monthCount < 0) {
                endDate = getAfterDate(endDate, dateCount, patternString);
            }
        }
        return endDate;
    }

    /**
     * 获取与指定日期相差指定 月数 并减去天数的日期
     *
     * @param baseDate
     *            时间字符串，如：2008-12-03 11:00:00
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param dateCount
     *            加或减去的天数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getEndDate(Date baseDate, int monthCount, int dateCount, String patternString) {
        String _baseDate = getDateString(baseDate, DATETIMESHOWFORMAT);
        return getEndDate(_baseDate, monthCount, dateCount, patternString);
    }

    /**
     * 当前日期转换为指定月数后 的日期
     *
     * @param monthCount
     *            向前或向后的月数，向后为正数，向前为负
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return String 转换后的日期
     */
    public static String getBeforeMonth(int monthCount, String patternString) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount);
        Date _date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        return formatter.format(_date);
    }

    /**
     * 日期格式化(String转换为Date)
     *
     * @param dateStr
     *            日期字符串
     * @param patten
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static Date getDateToString(String dateStr, String patten) {
        if (Tools.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(patten,Locale.CHINA);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化(String转换为String)
     *
     * @param date
     *            日期字符串
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getDateString(String date, String patternString) {
        if (date == null) {
            return "";
        }
        if (date.length() < 10) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(patternString, Locale.CHINA);
        int len = patternString.length();
        if (len > date.length()) {
            patternString = patternString.substring(0, date.length());
        }
        return formatter.format(getDateToString(date, patternString));
    }

    /**
     * 日期格式化(Date转换为String)
     *
     * @param _date
     *            日期
     * @param patternString
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static String getDateString(Date _date, String patternString) {
        String dateString = "";
        if (_date != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(patternString);
            dateString = formatter.format(_date);
        }
        return dateString;
    }

    /**
     * 日期格式转换 DATE to DATE
     *
     * @param _date
     *            日期
     * @param patten
     *            处理结果日期的显示格式，如："YYYY-MM-DD"
     * @return
     */
    public static Date dateToDate(Date _date, String patten) {
        String dateStr = "";
        SimpleDateFormat formatter = new SimpleDateFormat(patten);
        try {
            if (_date != null) {
                dateStr = formatter.format(_date);
            }
            return formatter.parse(dateStr);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 获得格式化日期之后的 String数据
     *
     * @param dateLong
     * @param patten
     * @return
     */
    public static String getDateOfString(Long dateLong, String patten) {
        if (dateLong != null) {
            return (new SimpleDateFormat(patten).format(new Date(dateLong.longValue()))).toString();
        }
        return "";
    }

    /**
     * 文本时间转换为时间对象
     *
     * @param baseDate
     *            日期字符串
     * @return
     */
    public static java.sql.Date getSqlDate(String baseDate) {
        if (baseDate == null || baseDate.length() == 0) {
            return null;
        }
        Date date = getDateToString(baseDate, DATESHOWFORMAT);
        return new java.sql.Date(date.getTime());
    }

    /**
     * java.util.Date对象转换为java.sql.Date对象
     *
     * @param date
     *            java.util.Date对象
     * @return Date java.sql.Date对象
     */
    public static java.sql.Date UtilDateToSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取到指定样式的年月日(年月日参数为int型)
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param patternString
     *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
     * @return 格式化后的字符串
     */
    public static String getDateString(int year, int month, int date, String patternString) {
        String dateString = "";
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        Date showDate = calendar.getTime();
        dateString = formatter.format(showDate);
        return dateString;
    }

    /**
     * 获取到指定样式的年月日(年月日参数为String型)
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param patternString
     *            日期格式，如：yyyy-MM-dd HH:mm:ss EEE
     * @return 格式化后的字符串
     */
    public static String getDateString(String year, String month, String date, String patternString) {
        String dateString = "";
        try {
            int y = Integer.parseInt(year);
            int m = Integer.parseInt(month);
            int d = Integer.parseInt(date);
            dateString = getDateString(y, m, d, patternString);
        } catch (Exception e) {
        }
        return dateString;
    }

    /**
     * 获取当前日期
     *
     * @param patternString
     *            日期格式，如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getDateStr(String patternString) {
        SimpleDateFormat formatter = new SimpleDateFormat(patternString);
        String date = formatter.format(new Date(System.currentTimeMillis()));
        return date;
    }

    /**
     * 验证输入的文本信息日期是否合
     *
     * @param dateStr
     * @return
     */
    public static Date isDate(String dateStr) {
        String date_format_1 = "yyyy/MM/dd";
        String date_format_2 = "yyyy-MM-dd";
        String date_format_3 = "yyyyMMdd";
        String date_format_4 = "yyyy.MM.dd";
        String[] date_format = { date_format_1, date_format_2, date_format_3, date_format_4 };
        for (int i = 0; i < date_format.length; i++) {
            Date tempDate = isDate(dateStr, date_format[i]);
            if (null != tempDate) {
                return tempDate;
            }
        }
        return null;
    }

    /**
     * 验证输入的文本信息日期是否合
     *
     * @param dateStr
     * @return
     */
    public static Date isDate(String dateStr, String patternString) {
        if (Tools.isEmpty(patternString)) {
            patternString = DATESHOWFORMAT;
        }
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat(patternString);
            formatDate.setLenient(false);
            ParsePosition pos = new ParsePosition(0);
            Date tempDate = formatDate.parse(dateStr, pos);
            tempDate.getTime();
            return tempDate;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 把Date转换为Calendar对象
     *
     * @param d
     *            Date对象
     * @return Calendar对象
     */
    public static Calendar getCalendar(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        return cal;
    }

    /**
     * 将时间对象转换成指定的格式有小时
     *
     * @param date
     * @return
     */
    public static String parseDateTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATETIMESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 将时间对象转换成指定的格式有
     *
     * @param date
     * @return
     */
    public static String parsTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(TIMESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 将时间对象转换成指定的格式无小时
     *
     * @param date
     * @return
     */
    public static String parseDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(DATESHOWFORMAT);
        return bartDateFormat.format(date);
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String firstDate() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = ca.getTime();
        return getDateString(firstDate, DATESHOWFORMAT);
    }

    /**
     * 获取当前月第一天
     *
     * @return
     */
    public static String lastDate() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = ca.getTime();
        return getDateString(lastDate, DATESHOWFORMAT);
    }

    /**
     * 获取当前数据里的时间参数
     *
     * @return
     */
    public static String getDateStr() {
        return "sysdate";
    }

    /**
     * 获取上一个月的日期
     *
     * @param date
     * @return
     */
    public static Date getUpMouth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, -1);
        return ca.getTime();
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DATE);
    }

    /**
     * 获取日期事第几周
     *
     * @param date
     * @return
     */
    public static int getWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取上一个月的日期
     *
     * @param date
     * @return
     */
    public static Date getUpMouth(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
        ca.add(Calendar.MONTH, -1);
        return ca.getTime();
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static int getMonth(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.DATE);
    }

    /**
     * 获取日期的第几周
     *
     * @param date
     * @return
     */
    public static int getWeek(String date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtils.getDateToString(date, DATESHOWFORMAT));
        return ca.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取日期的第几小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的第几分钟
     *
     * @param date
     * @return
     */
    public static int getMin(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.MINUTE);
    }

    /**
     * 检测d1 是否大于等于d2
     *
     * @param d1
     * @param d2
     * @return true d1 是否大于等于d2
     */
    public static boolean checkMax(Date d1, Date d2) {
        boolean flag = false;
        if (null != d1) {
            if (null != d2) {
                String d1s = getDateString(d1, "yyyyMMdd");
                String d12s = getDateString(d2, "yyyyMMdd");
                if (Double.valueOf(d1s) >= Double.valueOf(d12s)) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 检测d1 是否大于d2
     *
     * @param d1
     * @param d2
     * @return true d1 是否大于d2
     */
    public static boolean checkMaxNotEqual(Date d1, Date d2) {
        boolean flag = false;
        if (null != d1) {
            if (null != d2) {
                String d1s = getDateString(d1, "yyyyMMdd");
                String d12s = getDateString(d2, "yyyyMMdd");
                if (Double.valueOf(d1s) > Double.valueOf(d12s)) {
                    flag = true;
                }
            } else {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK) || Calendar.SATURDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
            return true;
        }
        return false;
    }

    /**
     * 给定时间往后延给定分钟
     *
     * @param date
     * @param minute
     * @return
     * @author doumingjun
     * @create date 2012-06-27
     */
    public static Date addMinutes(Date date, int minute) {
        if (null == date) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return (Date) calendar.getTime();
    }

    /**
     * 获取日期相差天数,精确到秒
     * @param beginDate  开始时间    格式： yyyyMMdd
     * @param endDate	   结束时间    格式： yyyyMMdd
     * @return 天数
     */
    public static Long getDiffDay(String beginDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATESTOREFORMAT);
        Long checkday = 0L;
        // 开始结束相差天数
        try {
            checkday = (formatter.parse(endDate).getTime() - formatter.parse(beginDate).getTime())/(1000 * 24 * 60 * 60);
        } catch (ParseException e) {
        }
        return checkday;
    }

    /**
     * 精度列表,只有在这个列表里指定的字段才会被截取
     */
    private static int[] units = { Calendar.YEAR, Calendar.MONTH, Calendar.DATE, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND,
            Calendar.MILLISECOND };

    /**
     * 截取到指定精度
     *
     * @param cal
     * @param field
     *            保留的最小字段
     * @return
     */
    public static Date roundDate(Calendar cal, int field) {
        boolean clearFlag = false;
        for (int i = 0; i < units.length; i++) {
            if (clearFlag) {
                cal.set(units[i], cal.getMinimum(units[i]));
            } else if (units[i] == field) {
                clearFlag = true;
            }
        }
        return cal.getTime();
    }



    public static String getShortTime(String time) {
        String shortString = null;
        long now = Calendar.getInstance().getTimeInMillis();
        Date date = getDateByString(time);
        if(date == null) {
            return shortString;
        }
        long delTime = (now - date.getTime()) / 1000;
        if (delTime > 24 * 60 * 60) {
            shortString = null;
        } else if (delTime > 60 * 60) {
            shortString = (int) (delTime / (60 * 60)) + "小时前";
        } else if (delTime > 60) {
            shortString = (int) (delTime / (60)) + "分钟前";
        } else {
            shortString = "刚刚";
        }
        System.out.println(shortString);
        return shortString;
    }

    public static Date getDateByString(String time) {
        Date date = null;
        if(time == null) {
            return date;
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static boolean isSameDay(long millis1, long millis2) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(millis1);
        String format1 = dateFormat.format(millis2);
        return format.equals(format1);
    }

    public static long getMinTime(long timestamp){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }

    public static long getMaxTime(long timestamp){
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant()).getTime();
    }

	/*public static void main(String[] args) throws Exception {
		// Date pprice =new Date();
		// System.out.println(pprice);
		// System.out.println(pprice.getTime());
		// Long a=pprice.getTime()+480000;
		// System.out.println(a);
		// System.out.println(new Date(a));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		System.out.println(getDateStr(DateUtils.DATESHOWFORMAT));


	}*/
}
