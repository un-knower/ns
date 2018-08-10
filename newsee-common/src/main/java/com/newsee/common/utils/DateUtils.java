package com.newsee.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;

public class DateUtils {

    /**
     * yyyy
     */
    public static final String YYYY = "yyyy";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYYMMDD_CROSS_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYYMMDD_CROSS_HHMMSS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String YYYYMMDDCROSSHHMMSSSSS_NOSYBOL = "yyyyMMddHHmmssSSS";

    /**
     * yyyyMMddhhmmss
     */
    public static final String YYYYMMDDHHMMSS_NOSYBOL = "yyyyMMddhhmmss";

    /**
     * yyyy-MM-dd
     */
    public static final String YYYYMMDD_CROSS = "yyyy-MM-dd";

    public static final String YYYYMM_CROSS = "yyyy-MM";

    /**
     * HH:mm:ss
     */
    public static final String HHMMSS_COLON = "HH:mm:ss";

    /**
     * 月
     */
    public static final String MONTH = "MONTH";

    /**
     * 周
     */
    public static final String WEEK = "WEEK";

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 根据格式获取当前时间
     *
     * @param fromat
     * @return
     */
    public static String getNowDate(String fromat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(fromat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_CROSS);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_CROSS);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }


    /**
     * 根据数据长度将string转换成date类型
     *
     * @param strDate
     * @return
     */
    public static Date strToDateByLength(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        Date date = null;
        SimpleDateFormat formatter = null;
        ParsePosition pos = new ParsePosition(0);
        if (strDate.length() == 4) {
            formatter = new SimpleDateFormat(YYYY);
            date = formatter.parse(strDate, pos);
            return date;
        }
        if (strDate.length() == 19) {
            formatter = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS);
            date = formatter.parse(strDate, pos);
            return date;
        }
        if (strDate.length() == 23) {
            formatter = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS_SSS);
            date = formatter.parse(strDate, pos);
            return date;
        }
        if (strDate.length() == 10) {
            formatter = new SimpleDateFormat(YYYYMMDD_CROSS);
            date = formatter.parse(strDate, pos);
            return date;
        }
        if (strDate.length() == 7) {
            formatter = new SimpleDateFormat(YYYYMM_CROSS);
            date = formatter.parse(strDate, pos);
            return date;
        }
        if (strDate.length() == 8) {
            formatter = new SimpleDateFormat(HHMMSS_COLON);
            date = formatter.parse(strDate, pos);
            return date;
        }
        return date;
    }

    /**
     * 将时间格式字符串转换为时间
     * 根据具体的时间格式字符串来装换
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate, String format) {
        if (strDate.length() != format.length()) {
            return new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat(HHMMSS_COLON);
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMddhhmmss。
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        if (sformat == null || "".equals(sformat.trim())) {
            sformat = YYYYMMDDHHMMSS_NOSYBOL;
        }
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * date
     *
     * @param target
     * @param format
     * @return
     */
    public static String dateToString(Date target, String format) {
        if (target == null) {
            return "";
        }
        if (format == null || "".equals(format.trim())) {
            format = YYYYMMDD_CROSS;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(target);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:mm"的格式，返回字符型的分钟
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60;
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60;
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 两时间只差
     *
     * @param beforeDate 减数
     * @param afterDate  被减数
     * @return
     */
    public static Long diffTimes(Date beforeDate, Date afterDate) {
        //这样得到的差值是微秒级别
        Long diffTime = null;
        try {
            diffTime = beforeDate.getTime() - afterDate.getTime();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return diffTime;
    }

    /**
     * 具体两时间只差
     *
     * @param beforeDate 减数
     * @param afterDate  被减数
     * @param type       (0 得到 days， 1 得到 hours， 2 得到 minutes)
     * @return
     */
    public static Long diffTimes(Date beforeDate, Date afterDate, Integer type) {
        Long diffTime = null;
        try {
            diffTime = DateUtils.diffTimes(beforeDate, afterDate);
            long days = diffTime / (1000 * 60 * 60 * 24);
            long hours = (diffTime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diffTime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            switch (type) {
                case 0:
                    diffTime = days;
                    break;
                case 1:
                    diffTime = hours;
                    break;
                case 2:
                    diffTime = minutes;
                    break;
                default:
                    diffTime = minutes;
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return diffTime;
    }

    /**
     * 判断两时间相差天数
     *
     * @param beforeDay "2017-05-10 16:36:30"
     * @param afterDay  "2017-05-19 16:02:30"
     * @return 9
     */
    public static int differentDays(Date beforeDay, Date afterDay) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beforeDay);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(afterDay);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) { //不同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) {  //闰年
                    timeDistance += 366;
                } else { //不是闰年
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else { //同年
            return day2 - day1;
        }
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(YYYYMMDD_CROSS);
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 时间前推或后推分钟,其中minutes表示分钟.
     */
    public static String getPreTime(String dateStr, String minutes) {
        SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD_CROSS_HHMMSS);
        String mydate1 = "";
        try {
            Date date1 = format.parse(dateStr);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(minutes) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
            return "";
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
     */
    public static String getNextDay(String nowdate, int delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(YYYYMMDD_CROSS);
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间
     *
     * @param nowdate
     * @param delay   前移或后延的天数
     * @return
     */
    public static Date getNextDay(Date nowdate, int delay) {
        try {
            String dateStr = DateUtils.dateToStrLong(nowdate);
            dateStr = DateUtils.getNextDay(dateStr, delay);
            nowdate = DateUtils.strToDate(dateStr);
        } catch (Exception e) {
            return null;
        }

        return nowdate;
    }

    /**
     * 得到一个时间延后或前移几个月的时间
     *
     * @param date
     * @param delay
     * @return
     */
    public static String getDelayMonth(Date date, int delay) {
        Date formNow3Month = getDelayMonthToDate(date, delay);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_CROSS);
        return sdf.format(formNow3Month);
    }

    /**
     * 得到一个时间延后或前移几个月的时间
     *
     * @param date
     * @param delay
     * @return
     */
    public static Date getDelayMonthToDate(Date date, int delay) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) calendar.setTime(date);
        calendar.add(Calendar.MONTH, delay); // 得到前3个月 -3
        return calendar.getTime();
    }

    /**
     * 得到一个时间延后或前移几个月的时间
     *
     * @param date
     * @param delay
     * @return
     */
    public static String getDelayMonth(String strDate, int delay) {
        Date date = null;
        if (strDate == null || "".equals(strDate.trim())) {
            date = new Date();
        } else {
            date = strToDateLong(strDate);
        }
        return getDelayMonth(date, delay);
    }

    /**
     * 判断是否润年
     *
     * @param ddate
     * @return
     */
    public static boolean isLeapYear(String ddate) {
        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 获取一个月的最后一天
     *
     * @param dat yyyy-MM-dd
     * @return
     */
    public static String getEndDateOfMonth(String dat) {
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周
     *
     * @return
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2017年2月17日所在周的星期一是几号
     *
     * @param sdate
     * @param num   （0 周日 1 周一）
     * @return
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if (num.equals("1")) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if (num.equals("2")) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if (num.equals("3")) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if (num.equals("4")) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if (num.equals("5")) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if (num.equals("6")) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if (num.equals("0")) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    private static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
     *
     * @param k 表示是取几位随机数，可以自己定
     */

    public static String getNo(int k) {
        return getUserDate(YYYYMMDDHHMMSS_NOSYBOL) + getRandom(k);
    }

    /**
     * 返回一个随机数
     *
     * @param i
     * @return
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 根据ymd维度获取当天或当月或当年开始时间
     * 例:ymd==DAY，今天时间为2017/2/12 返回的时间为 2017/2/12 00:00:00
     * ymd==MONTH，今天时间为2017/2/28 返回的时间为 2017/2/1 00:00:00
     * ymd==YEAR，今天时间为2017/2/28 返回的时间为 2017/1/1 00:00:00
     *
     * @param ymd DAY:按天获取 MONTH:按月获取 YEAR:按年获取
     * @return Date
     * @author xiaosisi on 2017/2/28
     */
    public static Date getStartDateByYMD(Date date, String ymd) {
        Date newDate = null;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        //当天开始时间
        if ("DAY".equals(ymd)) {
            calendar.set(year, month, day, 0, 0, 0);
        }
        //本周第一天
        if ("WEEK".equals(ymd)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
            calendar.set(year, month, day, 0, 0, 0);
        }
        //本月
        if ("MONTH".equals(ymd)) {
            calendar.set(year, month, 1, 0, 0, 0);
        }
        //当年的第一天
        if ("YEAR".equals(ymd)) {
            calendar.set(year, 0, 1, 0, 0, 0);
        }
        newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 根据ymd维度获取当天或当月或当年结束时间
     * 例:ymd==DAY，今天时间为2017/2/12 返回的时间为 2017/2/12 23:59:59
     * ymd==MONTH，今天时间为2017/2/12 返回的时间为 2017/2/28 23:59:59
     * ymd==YEAR，今天时间为2017/2/12 返回的时间为 2017/12/31 23:59:59
     *
     * @param date 如果传值，则获取date对应的结束时间
     * @param ymd  DAY:按天获取 MONTH:按月获取 YEAR:按年获取
     * @return Date
     * @author xiaosisi on 2017/2/28
     */
    public static Date getLastDateByYmdType(Date date, String ymd) {
        Date newDate;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        if ("DAY".equals(ymd)) {
            calendar.set(year, month, day, 23, 59, 59);
        }
        //获取当前时间所在周的周日
        if ("WEEK".endsWith(ymd)) {
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DATE);
            calendar.set(year, month, day, 23, 59, 59);
        }
        if ("MONTH".equals(ymd)) {
            calendar.set(year, month, 1);
            int maxDate = calendar.getActualMaximum(Calendar.DATE);
            calendar.set(year, month, maxDate, 23, 59, 59);
        }
        if ("YEAR".equals(ymd)) {
            calendar.set(year, 11, 1);
            int maxDate = calendar.getActualMaximum(Calendar.DATE);
            calendar.set(year, 11, maxDate, 23, 59, 59);
        }
        newDate = calendar.getTime();
        return newDate;
    }

    /**
     * 根据日期字符串获取当前日期的最后一个时刻
     *
     * @param ymd: 如果有4位，表示为年，获取改年最后一天的最后一个时刻
     *             如果有7位，如：2017-01，表示为月，获取该月的最后一天的最后一个时刻
     *             如果有10位，表示为天，，如：2017-10-01，表示为日，获取该天的最后一个时刻
     * @return
     * @author xiaosisi add on 2017/5/17
     */
    public static Date getLastDateByYMDStr(String ymd) {
        if (CommonUtils.isNullOrBlank(ymd)) {
            return null;
        }
        if (ymd.length() < 4) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        int year = Integer.parseInt(ymd.substring(0, 4));
        if (ymd.trim().length() == 4) {
            calendar.set(year, 11, 1);
            int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(year, 11, maxDate, 23, 59, 59);
        }
        if (ymd.trim().length() == 7) {
            int month = Integer.parseInt(ymd.substring(5, 7)) - 1;
            calendar.set(year, month, 1);
            int maxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(year, month, maxDate, 23, 59, 59);
        }
        if (ymd.trim().length() == 10) {
            int month = Integer.parseInt(ymd.substring(5, 7)) - 1;
            int date = Integer.parseInt(ymd.substring(8, 10));
            calendar.set(year, month, date, 23, 59, 59);
        }
        return calendar.getTime();
    }


    public static Date strToSpecialDate(String str) {
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            date = formatter.parse(str);
        } catch (Exception e) {
            date = new Date();
        }
        return date;
    }


    public static String dateToSpecialStr(Date d) {
        String str = "";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        str = formatter.format(d);
        return str;

    }

    public static void main(String[] args) throws ParseException {
        Date date = new Date(2018,3,1);
        String str = "2013-01-21 15-10-20";
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        System.out.println(formatter.format(date));
//        ParsePosition pos = new ParsePosition(0);
//        Date strtodate = formatter.parse(str, pos);
//        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date reTime = format1.parse(strtodate.toString());

//        formatter.parse(str);
//        System.out.println(formatter.parse(str));
        Date end = getLastDateByYmdType(date, "MONTH");
        System.out.println("");
    }
}
