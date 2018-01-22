package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils {
	
	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_MIN = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_DAY = "yyyy-MM-dd";
	public static final String PATTERN_ONLYTIME = "HH:mm:ss";
	public static final String PATTERN_ONLMIN = "HH:mm";
	
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date getYearBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();	
	}
	public static Date getYearEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.MONTH, 11);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	public static Date getMonthBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();	
	}
	public static Date getMonthEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	public static Date getWeekBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();	
	}
	
	public static Date getWeekEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.add(Calendar.DATE, 6);
		return c.getTime();	
	}
	
	public static Date getHourBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();	
	}
	public static Date getHourEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		//c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	public static Date getDayBegin(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
		
	}
	public static Date getDayBegin(String date){
		try {
			return getDayBegin(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getDayEnd(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	public static Date getDayEnd(String date){
		try {
			return getDayEnd(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	public static Date getHourBegin(String date,int hour){
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
			return c.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date getHourEnd(String date,int hour){
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.set(Calendar.HOUR_OF_DAY, hour);
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);
			return c.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取只有年月日的时间
	 * @param in
	 * @return
	 */
	public static Date getYmd(Date in){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取一天中的开始时间
	 * @param in	输入的时间
	 * @return
	 */
	public static Date dateDayBegin(Date in){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取一天中的结束时间
	 * @param in	输入的时间
	 * @return
	 */
	public static Date dateDayEnd(Date in){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	public static Date getTerm(Date in,int type,int term){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.add(type, term);
		return c.getTime();
	}
	/**
	 * 时间往后推一段时间
	 * @param in	摇改变的时间
	 * @param type	时间类型（时，分，秒）
	 * @param term	时间间隔
	 * @return
	 */
	public static Date add(Date in,int type,int term){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.add(type, term);
		return c.getTime();
	}
	/**
	 * 时间往前推一段时间
	 * @param in	摇改变的时间
	 * @param type	时间类型（时，分，秒）
	 * @param term	时间间隔
	 * @return
	 */
	public static Date sub(Date in,int type,int term){
		Calendar c = Calendar.getInstance();
		c.setTime(in);
		c.add(type, -term);
		return c.getTime();
	}
	
	public static String format(Date in){
		if(null==in) 
			return null;
		return df.format(in);
	}
	
	public static String format(Date in,String pattern){
		if(null==in) 
			return null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(in);
	}
	
	public static Date parse(String str,String pattern){
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static String parse(String str,String sourcepattern, String descpattern){
		SimpleDateFormat df = new SimpleDateFormat(sourcepattern);
		try {
			Date d = df.parse(str);
			return format(d, descpattern);
		} catch (ParseException e) {
			return str;
		}
	}
	
	/*public static String between(Date min,Date max){
		Calendar c = Calendar.getInstance();
		c.setTime(min);
		int yMin = c.get(Calendar.YEAR);
		int mMin = c.get(Calendar.MONTH);
		int dMin = c.get(Calendar.DAY_OF_MONTH);
	}*/
	
	public static long betweenDay(Date min,Date max){
		return (max.getTime()-min.getTime())/(1000*3600*24);
	}
	public static long betweenMonth(Date min,Date max){
		Calendar c = Calendar.getInstance();
		c.setTime(min);
		int mMin = c.get(Calendar.MONTH);
		int dMin = c.get(Calendar.DAY_OF_MONTH);
		c.setTime(max);
		int mMax = c.get(Calendar.MONTH);
		int dMax = c.get(Calendar.DAY_OF_MONTH);
		if(dMin<dMax){
			return mMax-mMin;
		}else{
			return mMax-mMin-1;
		}
	}
	public static long betweenDayExcludeMonth(Date min,Date max){
		Calendar c = Calendar.getInstance();
		c.setTime(min);
		int mMin = c.get(Calendar.MONTH);
		int dMin = c.get(Calendar.DAY_OF_MONTH);
		c.setTime(max);
		int mMax = c.get(Calendar.MONTH);
		int dMax = c.get(Calendar.DAY_OF_MONTH);
		if(dMin<=dMax){
			return dMax-dMin;
		}else{
			return betweenDay(add(min, Calendar.MONTH, mMax-mMin-1), max);
		}
	}
	
	public static long betweenYear(Date min,Date max){
		Calendar c = Calendar.getInstance();
		c.setTime(min);
		int yMin = c.get(Calendar.YEAR);
		int mMin = c.get(Calendar.MONTH);
		c.setTime(max);
		int yMax = c.get(Calendar.YEAR);
		int mMax = c.get(Calendar.MONTH);
		if(mMin<mMax){
			return yMax-yMin;
		}else{
			return yMax-yMin-1;
		}
	}
	
	public static long betweenMonthExcludeYear(Date min,Date max){
		Calendar c = Calendar.getInstance();
		c.setTime(min);
		int yMin = c.get(Calendar.YEAR);
		int mMin = c.get(Calendar.MONTH);
		c.setTime(max);
		int yMax = c.get(Calendar.YEAR);
		int mMax = c.get(Calendar.MONTH);
		return (yMax-yMin)*12+(mMax-mMin);
	}
	
	public static String babyDesc(Date dueDate,Date birth){
		//dueDate = getYmd(dueDate);
		
		Date now = getYmd(new Date());
		if(null==dueDate && null==birth){
			return "你还未填写宝宝信息";
		}else if(birth != null){
			birth = getYmd(birth);
			Date manyue = add(birth,Calendar.MONTH,1);
			Date zhousui = add(birth,Calendar.YEAR,1);
			if(now.compareTo(manyue)<0){//小于1个月
				return "宝宝出生"+betweenDay(birth,now)+"天了";
			}else if(now.compareTo(manyue)==0){//等于1个月
				return "您的宝宝今天满月了";
			}else if(now.compareTo(manyue)>0 && now.compareTo(zhousui)<0){//大于1个月小于1年
				long day = betweenDayExcludeMonth(birth,now);
				if(0==day){
					return "宝宝出生"+betweenMonth(birth,now)+"个月了";
				}else{
					return "宝宝出生"+betweenMonth(birth,now)+"个月"+day+"天了";
				}				
			}else if(now.compareTo(zhousui)==0){//等于1年
				return "今天是您的宝宝周岁生日哦";
			}else{//大于1年
				long month = betweenMonthExcludeYear(birth,now);
				long left = month%12;
				if(0==left){
					return "宝宝"+month/12+"周岁了";
				}else{
					return "宝宝"+month/12+"周岁"+left+"个月了";
				}
				
			}
		}else{
			if(dueDate.compareTo(now)>=0){
				long day = betweenDay(now,dueDate);
				if(0==day){
					return "宝宝今天就要出生了,注意哦！";
				}else{
					return "宝宝还有"+day+"天就要出生了";
				}
			}else{
				long day = betweenDay(dueDate,now);
				return "宝宝已经出生"+day+"天了,快去完善宝宝信息吧！";
			}
			
		}
	}
	
	public static String dateFormat(Date in){
		if(null==in) 
			return null;
		return sdf.format(in);
	}

	/**
	 * 获取某个月份的天数
	 * @param in
	 * @return
     */
	public static int getDaysOfMonth(Date in) {
		if (in == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取此日期是几号
	 * @param in
	 * @return
     */
	public static int getCurrentDays(Date in) {
		if (in == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取此日期的年份
	 * @param in
	 * @return
     */
	public static int getYear(Date in) {
		if (in == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 根据时间获取月份
	 * @param in
	 * @return
     */
	public static int getMonth(Date in) {
		if (in == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(in);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * n<0:获取n天前日期
	 * n>0:获取n天后日期
	 * n=0:获取当前日期
	 * @param n
	 * @return
     */
	public static Date getBeforOrFutureDate(final int n) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	/**
	 * n<0:获取n月前日期
	 * n>0:获取n月后日期
	 * n=0:获取当前日期
	 * @param date 指定日期
	 * @param n
	 * @return
	 */
	public static Date getBeforOrFutureDateOfMonth(Date date, final int n) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/**
	 * 计算两个日期相差的月份数
	 *
	 * @param minDate
	 * @param maxDate
	 * @return
	 */
	public static int countMonths(Date minDate, Date maxDate) {
		if (minDate == null || maxDate == null) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(minDate);
		c2.setTime(maxDate);
		int year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
		int minDay = c1.get(Calendar.DAY_OF_MONTH);
		int maxDay = c2.get(Calendar.DAY_OF_MONTH);
		int month = year * 12 + c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		if (maxDay < minDay) {
			if (month > 0) {
				month--;
			}
		}
		return month;
	}

	/**
	 * 将指定日期按照指定格式转成Integer类型
	 *
	 * @param in
	 * @param pattern
	 * @return
	 */
	public static Integer formatInt(Date in, String pattern) {
		if (null == in)
			return null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return Integer.valueOf(df.format(in));
	}

	/**
	 * 根据当前日期获得所在周的日期区间（周一和周日日期）
	 *
	 * @return
	 * @author zhaoxuepu
	 * @throws ParseException
	 */
	public String getTimeInterval(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// System.out.println("要计算日期为:" + sdf.format(cal.getTime())); // 输出要计算日期
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		String imptimeBegin = sdf.format(cal.getTime());
		// System.out.println("所在周星期一的日期：" + imptimeBegin);
		cal.add(Calendar.DATE, 6);
		String imptimeEnd = sdf.format(cal.getTime());
		// System.out.println("所在周星期日的日期：" + imptimeEnd);
		return imptimeBegin + "," + imptimeEnd;
	}


	/**
	 * 根据当前日期获得上周的日期区间（上周周一和周日日期）
	 *
	 * @return
	 * @author zhaoxuepu
	 */
	public String getLastTimeInterval() {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
		int offset1 = 1 - dayOfWeek;
		int offset2 = 7 - dayOfWeek;
		calendar1.add(Calendar.DATE, offset1 - 7);
		calendar2.add(Calendar.DATE, offset2 - 7);
		// System.out.println(sdf.format(calendar1.getTime()));// last Monday
		String lastBeginDate = sdf.format(calendar1.getTime());
		// System.out.println(sdf.format(calendar2.getTime()));// last Sunday
		String lastEndDate = sdf.format(calendar2.getTime());
		return lastBeginDate + "," + lastEndDate;
	}

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(format(dateDayBegin(now),"yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(format(dateDayEnd(now),"yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(format(add(now,Calendar.YEAR,1),"yyyy-MM-dd HH:mm:ss.SSS"));
	}
	

}
