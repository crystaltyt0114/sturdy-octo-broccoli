package com.tytont.test2020.utils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 时间 工具类
 * 
 * @author Administrator
 *
 */
public class DateUtils extends PropertyEditorSupport {
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat("yyyy年MM月dd日");
	/*
	 * 中文全称 如：2010年12月01日 23时15分06秒
	 */
	public static final SimpleDateFormat data_sdf_wz_long_cn = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static final SimpleDateFormat data_sdf_wz_full_cn = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒SSS毫秒");

	public static final SimpleDateFormat time_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");

	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat("HH:mm");

	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final long ONE_MINUTE = 60;
	private static final long ONE_HOUR = 3600;
	private static final long ONE_DAY = 86400;
	private static final long ONE_MONTH = 2592000;
	private static final long ONE_YEAR = 31104000;

	private static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}

	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}

	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date(millis));
		return cal;
	}

	public static Date getDate() {
		return new Date();
	}

	public static Date getDate(long millis) {
		return new Date(millis);
	}

	public static Timestamp str2Timestamp(String str) {
		Date date = str2Date(str, date_sdf);
		return new Timestamp(date.getTime());
	}

	public static Date str2Date(String str, SimpleDateFormat sdf) {
		if ((str == null) || ("".equals(str))) {
			return null;
		}
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String date2Str(SimpleDateFormat date_sdf) {
		Date date = getDate();
		if (date == null) {
			return null;
		}
		return date_sdf.format(date);
	}

	public static String dataformat(String data, String format) {
		SimpleDateFormat sformat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = sformat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sformat.format(date);
	}

	public static String date2Str(Date date, SimpleDateFormat date_sdf) {
		if (date == null) {
			return null;
		}
		return date_sdf.format(date);
	}

	public static String getDate(String format) {
		Date date = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}

	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	public static Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static Timestamp getTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

	public static Timestamp getCalendarTimestamp(Calendar cal) {
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime);
		return buydate;
	}

	public static long getMillis() {
		return new Date().getTime();
	}

	public static long getMillis(Calendar cal) {
		return cal.getTime().getTime();
	}

	public static long getMillis(Date date) {
		return date.getTime();
	}

	public static long getMillis(Timestamp ts) {
		return ts.getTime();
	}

	public static String formatDate() {
		return date_sdf.format(getCalendar().getTime());
	}

	public static String getDataString(SimpleDateFormat formatstr) {
		return formatstr.format(getCalendar().getTime());
	}

	public static String formatDate(Calendar cal) {
		return date_sdf.format(cal.getTime());
	}

	public static String formatDate(Date date) {
		return date_sdf.format(date);
	}

	public static String formatDate(long millis) {
		return date_sdf.format(new Date(millis));
	}

	public static String formatDate(String pattern) {
		return getSDFormat(pattern).format(getCalendar().getTime());
	}

	public static String formatDate(Calendar cal, String pattern) {
		return getSDFormat(pattern).format(cal.getTime());
	}

	public static String formatDate(Date date, String pattern) {
		return getSDFormat(pattern).format(date);
	}

	public static String formatTime() {
		return time_sdf.format(getCalendar().getTime());
	}

	public static String formatTime(long millis) {
		return time_sdf.format(new Date(millis));
	}

	public static String formatTime(Calendar cal) {
		return time_sdf.format(cal.getTime());
	}

	public static String formatTime(Date date) {
		return time_sdf.format(date);
	}

	public static String formatShortTime() {
		return short_time_sdf.format(getCalendar().getTime());
	}

	public static String formatShortTime(long millis) {
		return short_time_sdf.format(new Date(millis));
	}

	public static String formatShortTime(Calendar cal) {
		return short_time_sdf.format(cal.getTime());
	}

	public static String formatShortTime(Date date) {
		return short_time_sdf.format(date);
	}

	public static Date parseDate(String src, String pattern) throws ParseException {
		return getSDFormat(pattern).parse(src);
	}

	public static Calendar parseCalendar(String src, String pattern) throws ParseException {
		Date date = parseDate(src, pattern);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String formatAddDate(String src, String pattern, int amount) throws ParseException {
		Calendar cal = parseCalendar(src, pattern);
		cal.add(5, amount);
		return formatDate(cal);
	}

	public static Timestamp parseTimestamp(String src, String pattern) throws ParseException {
		Date date = parseDate(src, pattern);
		return new Timestamp(date.getTime());
	}

	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {
		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return calSrc.get(1) - calDes.get(1);
		}

		if (flag == 'd') {
			return (int) (millisDiff / 86400000L);
		}

		if (flag == 'h') {
			return (int) (millisDiff / 3600000L);
		}

		if (flag == 'm') {
			return (int) (millisDiff / 60000L);
		}

		if (flag == 's') {
			return (int) (millisDiff / 1000L);
		}

		return 0;
	}

	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.hasText(text))
			try {
				if ((text.indexOf(":") == -1) && (text.length() == 10)) {
					setValue(date_sdf.parse(text));
					return;
				}
				if ((text.indexOf(":") > 0) && (text.length() == 19)) {
					setValue(datetimeFormat.parse(text));
					return;
				}
				throw new IllegalArgumentException("Could not parse date, date format is error ");
			} catch (ParseException ex) {
				IllegalArgumentException iae = new IllegalArgumentException("Could not parse date: " + ex.getMessage());
				iae.initCause(ex);
				throw iae;
			}
		else
			setValue(null);
	}

	public static int getYear() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(getDate());
		return calendar.get(1);
	}

	/**
	 * 距离今天多久
	 * 
	 * @param date
	 * @return
	 * 
	 */
	public static String fromToday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		long time = date.getTime() / 1000;
		long now = new Date().getTime() / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟前";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟前";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_DAY * 3)
			return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
		else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			return month + "个月" + day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE)
					+ "分";
		} else {
			long year = ago / ONE_YEAR;
			int month = calendar.get(Calendar.MONTH) + 1;// JANUARY which is 0
															// so month+1
			return year + "年前" + month + "月" + calendar.get(Calendar.DATE) + "日";
		}

	}

	/**
	 * 距离截止日期还有多长时间
	 * 
	 * @param date
	 * @return
	 */
	public static String fromDeadline(Date date) {
		long deadline = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long remain = deadline - now;
		if (remain <= ONE_HOUR)
			return "只剩下" + remain / ONE_MINUTE + "分钟";
		else if (remain <= ONE_DAY)
			return "只剩下" + remain / ONE_HOUR + "小时" + (remain % ONE_HOUR / ONE_MINUTE) + "分钟";
		else {
			long day = remain / ONE_DAY;
			long hour = remain % ONE_DAY / ONE_HOUR;
			long minute = remain % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return "只剩下" + day + "天" + hour + "小时" + minute + "分钟";
		}

	}

	/**
	 * 距离今天的绝对时间
	 * 
	 * @param date
	 * @return
	 */
	public static String toToday(Date date) {
		long time = date.getTime() / 1000;
		long now = (new Date().getTime()) / 1000;
		long ago = now - time;
		if (ago <= ONE_HOUR)
			return ago / ONE_MINUTE + "分钟";
		else if (ago <= ONE_DAY)
			return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟";
		else if (ago <= ONE_DAY * 2)
			return "昨天" + (ago - ONE_DAY) / ONE_HOUR + "点" + (ago - ONE_DAY) % ONE_HOUR / ONE_MINUTE + "分";
		else if (ago <= ONE_DAY * 3) {
			long hour = ago - ONE_DAY * 2;
			return "前天" + hour / ONE_HOUR + "点" + hour % ONE_HOUR / ONE_MINUTE + "分";
		} else if (ago <= ONE_MONTH) {
			long day = ago / ONE_DAY;
			long hour = ago % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return day + "天前" + hour + "点" + minute + "分";
		} else if (ago <= ONE_YEAR) {
			long month = ago / ONE_MONTH;
			long day = ago % ONE_MONTH / ONE_DAY;
			long hour = ago % ONE_MONTH % ONE_DAY / ONE_HOUR;
			long minute = ago % ONE_MONTH % ONE_DAY % ONE_HOUR / ONE_MINUTE;
			return month + "个月" + day + "天" + hour + "点" + minute + "分前";
		} else {
			long year = ago / ONE_YEAR;
			long month = ago % ONE_YEAR / ONE_MONTH;
			long day = ago % ONE_YEAR % ONE_MONTH / ONE_DAY;
			return year + "年前" + month + "月" + day + "天";
		}

	}

	/**
	 * 根据一个日期, 获得这个日期后的某一天的日期, 就是日期的计算 比如: 日期20121213 + 30天的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param formart
	 *            格式化的格式
	 * @param days
	 *            天数
	 * @return
	 */
	public static String getSpecifiedDayAfterDate(Date date, String formart, Integer days) {
		SimpleDateFormat sdf = new SimpleDateFormat(formart);
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
		return sdf.format(now.getTime());
	}

	/**
	 * 获得指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @param format
	 *            yyyy-MM-dd / yyyy-MM-dd hh:mm:ss
	 * @return
	 * @throws Exception
	 */
	public static String getSpecifiedDayBefore(String specifiedDay, String format) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat(format).format(c.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 * 
	 * @param specifiedDay
	 * @param format
	 *            yyyy-MM-dd / yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static String getSpecifiedDayAfter(String specifiedDay, String format) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat(format).format(c.getTime());
		return dayAfter;
	}

	/**
	 * 根据日期获得所在周的日期
	 * 
	 * @param mdate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static List<Date> dateToWeek(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date mdate;
		List<Date> list = new ArrayList<Date>();
		try {
			mdate = sdf.parse(dateString);
			int b = mdate.getDay();
			Date fdate;
			Long fTime = mdate.getTime() - b * 24 * 3600000;
			for (int a = 0; a <= 7; a++) {
				fdate = new Date();
				fdate.setTime(fTime + (a * 24 * 3600000));
				list.add(a, fdate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 获得最近七天的日期
	 * 
	 * @return
	 */
	public static List<Date> getRecently7Days() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> the7Days = new ArrayList<Date>();
		try {
			String currentDate = sd.format(new Date());
			String specDay = currentDate;
			String specBeforeDay = currentDate;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < 7; i++) {
				specDay = specBeforeDay;
				specBeforeDay = getSpecifiedDayBefore(specDay, "yyyy-MM-dd");
				the7Days.add(i, sdf.parse(specBeforeDay));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return the7Days;
	}

	/**
	 * 根据一个日期获得一个起始日期和一个结束日期
	 * 
	 * @param specDay
	 *            日期
	 * @param timeStamp
	 *            时间戳
	 * @return
	 */
	public static String[] getStartAndEndTime(String specDay, String timeStamp) {
		String[] stratAndEndTime = new String[2];
		String specifiedDay = specDay + " " + timeStamp;
		stratAndEndTime[0] = getSpecifiedDayBefore(specifiedDay, "yyyy-MM-dd") + " " + timeStamp;
		stratAndEndTime[1] = specifiedDay;
		return stratAndEndTime;
	}

	/**
	 * 增加天数
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date plusDate(Date date, int n) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(5, n);
		return gc.getTime();
	}

	/**
	 * 增加小时
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date plusHour(Date date, int n) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.HOUR, n);
		return gc.getTime();
	}

	/**
	 * 增加分钟数
	 * 
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date plusMinute(Date date, int n) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.MINUTE, n);
		return gc.getTime();
	}

	public static String diffDate(Date date1, Date date2) {
		long a = date1.getTime();
		long b = date2.getTime();
		int c = (int) ((a - b) / 1000);
		// 超时
		if (c < 0)
			return "0";
		int hour = c / 3600;
		int minute = (c - 3600 * hour) / 60;
		int second = c - 3600 * hour - 60 * minute;
		return "剩余" + hour + "小时" + minute + "分" + second + "秒";
	}

	/**
	 * 得到某一天的该星期的第一日 00:00:00
	 * 
	 * @param date
	 * @param firstDayOfWeek
	 *            一个星期的第一天为星期几
	 * 
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date, int firstDayOfWeek) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		cal.setFirstDayOfWeek(firstDayOfWeek);// 设置一星期的第一天是哪一天
		cal.set(Calendar.DAY_OF_WEEK, firstDayOfWeek);// 指示一个星期中的某天
		cal.set(Calendar.HOUR_OF_DAY, 0);// 指示一天中的小时。HOUR_OF_DAY 用于 24
											// 小时制时钟。例如，在 10:04:15.250 PM
											// 这一时刻，HOUR_OF_DAY 为 22。
		cal.set(Calendar.MINUTE, 0);// 指示一小时中的分钟。例如，在 10:04:15.250 PM
									// 这一时刻，MINUTE 为 4。
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date lastMonday() {
		Calendar calendar = Calendar.getInstance();
		while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			calendar.add(Calendar.DAY_OF_WEEK, -1);
		}
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int offset = 1 - dayOfWeek;
		calendar.add(Calendar.DATE, offset - 7);
		return getFirstDayOfWeek(calendar.getTime(), 2);
	}

	/**
	 * 获取当天 00:00:00时间
	 * 
	 * @return
	 */
	public static Date getDayZero() {
		return getDayZeroByDate(new Date());
	}

	/**
	 * 获取某一天的凌晨时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayZeroByDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当天的后一天 00:00:00时间
	 * 
	 * @return
	 */
	public static Date getAfterDayZero(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取当天的前一天
	 * 
	 * @return
	 */
	public static Date getBeforeDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime();
	}

	public static boolean isBetweenTime(Date target, Date startTime, Date endTime) {
		if (target.compareTo(startTime) >= 0 && target.compareTo(endTime) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取 该日期的月份第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		// 获取前月的第一天
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		return getDayZeroByDate(calendar.getTime());
	}

	/**
	 * 获取 该日期的月份最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getMonthAfterDay(Date date) {
		// 获取前月的第一天
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getDayZeroByDate(calendar.getTime());
	}

	/**
	 * 获取本周第一天 也就是本周星期一
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekFirst(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return calendar.getTime();
	}

	/**
	 * 获取指定月份的天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 获取本周最后一天 也就是本周星期日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getWeekLast(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return calendar.getTime();
	}

	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();

		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		// Date firstDayOfWeek = getFirstDayOfWeek(new Date(), 1);
		// System.out.println(data_sdf_wz_full_cn.format(firstDayOfWeek));
	}

}
