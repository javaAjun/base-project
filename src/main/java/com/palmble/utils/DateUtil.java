/**
 * @author:
 * @DATE:
 *
 */
//package com.palmble.tianan.util;
package com.palmble.utils;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Comments: Company: 
 * @DATE:
 * @TIME: 
 */
public class DateUtil {
	public static final String formatPattern = "yyyy-MM-dd";

	public static final String formatPattern_all = "yyyy-MM-dd HH:mm:ss";
	
	public static final String format_all = "yyyy/MM/dd HH:mm:ss";
	
	public static final String formatPattern_Short = "yyyyMMdd";

	public static final String formatPattern_sml = "yyMMdd";

	// mysql 常用的日期转换
	public static final String formatMysqlByDay = "%Y-%c-%d"; // 按天
	public static final String formatMysqlByHonour = "%Y-%c-%d %H"; // 按小时
	public static final String formatMySqlByMonth = "%Y-%c"; // 按月

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(new Date());
	}

	/**
	 * 获取当前日期 时间
	 * 
	 * @return
	 */
	public static String getCurrentDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern_all);
		return format.format(new Date());
	}
	/**
	 * 获取当前日期 时间
	 * 
	 * @return
	 */
	public static String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(format_all);
		return format.format(new Date());
	}
	/**
	 * 获取制定毫秒数之前的日期
	 * 
	 * @param timeDiff
	 * @return
	 */
	public static String getDesignatedDate(long timeDiff) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		long nowTime = System.currentTimeMillis();
		long designTime = nowTime - timeDiff;
		return format.format(designTime);
	}
	/**
	 * YYYYMMDDHHSS
	 * @return
	 */
	public static String getNOWDate(){
		 Calendar now = Calendar.getInstance();  
	     String year= String.valueOf(now.get(Calendar.YEAR));//获取当前的年
	     String mouth=String.valueOf(now.get(Calendar.MONTH) + 1);  
	     String day= String.valueOf(now.get(Calendar.DAY_OF_MONTH));
	     String hour= String.valueOf(now.get(Calendar.HOUR_OF_DAY));  
	     String minute= String.valueOf(now.get(Calendar.MINUTE));  
	     String second= String.valueOf(now.get(Calendar.SECOND));
	     String nowDate=year+mouth+day+hour+minute+second;
	     return nowDate;
	}
	/**
	 * 
	 * 获取前几天的日期
	 */
	public static String getPrefixDate(String count) {
		Calendar cal = Calendar.getInstance();
		int day = 0 - Integer.parseInt(count);
		cal.add(Calendar.DATE, day); // int amount 代表天数
		Date datNew = cal.getTime();
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(datNew);
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(date);
	}

	/**
	 * 日期转换字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 日期时间转换成字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String datetimeToString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(formatPattern_all);
		return format.format(date);
	}
	/**
	 * 校验时间格式
	 * @param str
	 * @return
	 */
	public static boolean isValidDate(String str,String dateFormat) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
	/**
	 * 字符串转换日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		// str = " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if (!str.equals("") && str != null) {
			try {
				return format.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 字符串转换日期时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDateTime(String str) {
		return stringToDateTime(str, null);
	}

	/**
	 * 字符串转换日期时间
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDateTime(String str, String formatText) {

		if (formatText == null) {
			formatText = formatPattern_all;
		}

		SimpleDateFormat format = new SimpleDateFormat(formatText);
		if (!str.equals("") && str != null) {
			try {
				return format.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取当前的年份
	 * @return
	 */
	public static int getNowYear(){
		Calendar a=Calendar.getInstance();
		int  year=a.get(Calendar.YEAR);
		return year;
	}
	/**
	 * 计算时间之间的天数
	 * @param starttime
	 * @param endtime
	 * @return
	 */
public static Integer getDayCount(Date starttime,Date endtime){
	SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date begin = null;
	Date end   = null;
	try {
		begin = dfs.parse(DateUtil.dateToString(starttime,formatPattern_all));
		end = dfs.parse(DateUtil.dateToString(endtime,formatPattern_all));
	} catch (ParseException e) {
		e.printStackTrace();
	}

	long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

	long day1 = between / (24 * 3600);  //天
//	long hour1 = between % (24 * 3600) / 3600;//小时
//	long minute1 = between % 3600 / 60; //分钟
//	long second1 = between % 60;   //秒
	String days=String.valueOf(day1);
   return Integer.valueOf(days);
}
	// java中怎样计算两个时间如：“21:57”和“08:20”相差的分钟数、小时数 java计算两个时间差小时 分钟 秒 .
	public void timeSubtract() {
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date begin = null;
		Date end   = null;
		try {
			begin = dfs.parse("2004-01-02 11:30:24");
			end = dfs.parse("2004-03-26 13:31:40");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒

		long day1 = between / (24 * 3600);  //天
		long hour1 = between % (24 * 3600) / 3600;//小时
		long minute1 = between % 3600 / 60; //分钟
		long second1 = between % 60;   //秒
		System.out.println("" + day1 + "天" + hour1 + "小时" + minute1 + "分"
				+ second1 + "秒");
	}
	

	
	
	/**
	 * 获取默认的时间
	 * 
	 * @return
	 */
	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static Timestamp getTimeStatmpByString(String format,
			String dateString) {
		Date date = stringToDateTime(dateString,format);
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取当前日期再过x天的日期
	 * @param x 向后推迟的天数
	 * @return
	 */
	public static String getPastDate(Integer x){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(x==null)
			return format.format(new Date());
		 Calendar calendar = Calendar.getInstance();
         calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + x);
         Date today = calendar.getTime();
         
         String result = format.format(today);
         return result;
	}
	/**
	 * 时间增加  不传值不整加
	 * @param date
	 * @param addyear
	 * @param addDay
	 * @param addMonth
	 * @return
	 */
	public static String getAddYear(Date date,Integer addYear,Integer addDay,Integer addMonth){
		
		Calendar   calendar=new GregorianCalendar(); 
		//System.out.println("111111111::::"+cal.getTime());
		calendar.setTime(date); 
		if(addYear!=null)calendar.add(calendar.YEAR, addYear);//把日期往后增加一年.整数往后推,负数往前移动
	    if(addMonth!=null)calendar.add(calendar.DAY_OF_MONTH, addMonth);//把日期往后增加一个月.整数往后推,负数往前移动
	    if(addDay!=null)calendar.add(calendar.DATE,addDay);//把日期往后增加一天.整数往后推,负数往前移动 
//	    calendar.add(calendar.WEEK_OF_MONTH, 1);//把日期往后增加一个月.整数往后推,负数往前移动
	    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
	    return DateUtil.datetimeToString(date);
	}

	public static void main(String[] args) {
		String date=DateUtil.getAddYear(new Date(), 1, -1, null);
		System.out.println(date);
//		String curDate = DateUtil.dateToString(new Date());
//		String curTime = DateUtil.datetimeToString(new Date());
//		String curDt = DateUtil.dateToString(new Date(), formatPattern_sml);
//		
//		Date date = DateUtil.stringToDateTime(DateUtil
//				.datetimeToString(new Date()));
//		
//		System.out.println(DateUtil.datetimeToString(date));
//		
//		System.out.println(date);
//		
//		System.out.println(curDate);
//		System.out.println(curTime);
//		System.out.println(curDt + "01");
//		System.out.println(DateUtil.getPastDate(2));
	}
}
