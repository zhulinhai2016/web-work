package util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author wjd
 *
 */
public class DateUtil {
	/**
	 * 返回date2-date1之间的分钟数
	 */
	public static long distanceMinuteBetweenDates (Date date1,Date date2){
		return (date2.getTime()-date1.getTime())/60000;
	}
	
	/**
	 * 获取当前时间的年份
	 * 2018年7月11日 created by z
	 * @return
	 * return_type String
	 */
	public static String getYear(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		return format.format(new Date());
	}
	
	/**
	 * 获取当前时间的月份
	 * 2018年7月11日 created by z
	 * @return
	 * return_type String
	 */
	public static long getMonth(){
		Date date = new Date();
		return (long)(date.getMonth()+1);
	}
}
