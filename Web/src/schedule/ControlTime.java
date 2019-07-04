package schedule;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import ecapi.model.KebiaoModel;
import util.TimerUtil;

/**
 * 用于记录每一大节课的控制时间 包括开启时间 关闭时间
 * @author wjd
 *
 */
public class ControlTime {
	
	public static Map<String,Map<String,String>> controlTimeMap = new HashMap<>();
	//第一次同步的课表控制时间关系
	private static Map<String,String> firstControlTimeMap = new HashMap<>();
	//第二次同步的课表控制时间关系
	private static Map<String,String> secondControlTimeMap = new HashMap<>();
	private static final String LateOpenTime = "7:15";
	//课表占用情况关系
	public static Map<String,String> keBiaoMap = new HashMap<>();
	static{
		controlTimeMap.put(TimerUtil.FIRST, firstControlTimeMap);
		controlTimeMap.put(TimerUtil.SECOND, secondControlTimeMap);
		firstControlTimeMap.put("01", "07:00-08:55");
		firstControlTimeMap.put("02", "08:20-09:45");
		firstControlTimeMap.put("0102", "07:00-09:45");
		firstControlTimeMap.put("010203", "07:00-10:50");
		    //firstControlTimeMap.put("0102", "15:32-19:45");//测试用的
		   // firstControlTimeMap.put("0102", "15:33-19:45");//测试用的
		firstControlTimeMap.put("01020304", "07:00-11:40");// 这个
		firstControlTimeMap.put("0102030405", "07:00-12:35");// 这个
		firstControlTimeMap.put("010203040506", "07:00-13:25");//这个
		firstControlTimeMap.put("01020304050607", "07:00-14:15");//这个
		firstControlTimeMap.put("0102030405060708", "07:00-15:45");//这个
		firstControlTimeMap.put("010203040506070809", "07:00-16:50");//这个
		firstControlTimeMap.put("01020304050607080910", "07:00-17:50");//这个
		firstControlTimeMap.put("0102030405060708091011", "07:00-19:35");//这个
		firstControlTimeMap.put("010203040506070809101112", "07:00-20:25");//这个
		firstControlTimeMap.put("01020304050607080910111213", "07:00-21:20");//这个
		firstControlTimeMap.put("0102030405060708091011121314", "07:00-22:10");//这个
		firstControlTimeMap.put("0203", "08:20-10:50");// 这个
		firstControlTimeMap.put("020304", "08:20-11:40");// 这个
		firstControlTimeMap.put("02030405", "08:20-12:35");// 这个
		firstControlTimeMap.put("0203040506", "08:20-13:25");//这个
		firstControlTimeMap.put("020304050607", "08:20-14:15");//这个
		firstControlTimeMap.put("02030405060708", "08:20-15:45");//这个
		firstControlTimeMap.put("0203040506070809", "08:20-16:50");//这个
		firstControlTimeMap.put("020304050607080910", "08:20-17:50");//这个
		firstControlTimeMap.put("02030405060708091011", "08:20-19:35");//这个
		firstControlTimeMap.put("0203040506070809101112", "08:20-20:25");//这个
		firstControlTimeMap.put("020304050607080910111213", "08:20-21:20");//这个
		firstControlTimeMap.put("02030405060708091011121314", "08:20-22:10");//这个
		   // firstControlTimeMap.put("0304", "11:49-11:55");//测试用的
		firstControlTimeMap.put("03", "09:25-10:50");
		firstControlTimeMap.put("04", "10:15-11:40");
		firstControlTimeMap.put("0304", "09:25-11:40");
		firstControlTimeMap.put("030405", "09:25-12:35");
		firstControlTimeMap.put("03040506", "09:25-13:25");
		firstControlTimeMap.put("0304050607", "09:25-14:15");
		firstControlTimeMap.put("030405060708", "09:25-15:45");
		firstControlTimeMap.put("03040506070809", "09:25-14:50");
		firstControlTimeMap.put("0304050607080910", "09:25-17:40");
		firstControlTimeMap.put("030405060708091011", "09:25-19:35");
		firstControlTimeMap.put("03040506070809101112", "09:25-20:25");
		firstControlTimeMap.put("0304050607080910111213", "09:25-21:20");
		firstControlTimeMap.put("030405060708091011121314", "09:25-22:10");
		firstControlTimeMap.put("0405", "10:15-12:35");
		firstControlTimeMap.put("040506", "10:15-13:25");
		firstControlTimeMap.put("04050607", "10:15-14:15");
		firstControlTimeMap.put("0405060708", "10:15-15:45");
		firstControlTimeMap.put("040506070809", "10:15-14:50");
		firstControlTimeMap.put("04050607080910", "10:15-17:40");
		firstControlTimeMap.put("0405060708091011", "10:15-19:35");
		firstControlTimeMap.put("040506070809101112", "10:15-20:25");
		firstControlTimeMap.put("04050607080910111213", "10:15-21:20");
		firstControlTimeMap.put("0405060708091011121314", "10:15-22:10");
		firstControlTimeMap.put("05", "11:10-12:35");
		firstControlTimeMap.put("06", "12:00-13:25");
		firstControlTimeMap.put("0506", "11:10-13:25");
		firstControlTimeMap.put("050607", "11:10-14:15");
		firstControlTimeMap.put("05060708", "11:10-15:45");
		firstControlTimeMap.put("0506070809", "11:10-16:50");
		firstControlTimeMap.put("050607080910", "11:10-17:40");//这个
		firstControlTimeMap.put("05060708091011", "11:10-19:35");//这个
		firstControlTimeMap.put("0506070809101112", "11:10-20:25");
		firstControlTimeMap.put("050607080910111213", "11:10-21:20");
		firstControlTimeMap.put("05060708091011121314", "11:10-22:10");
		firstControlTimeMap.put("0607", "12:00-14:15");
		firstControlTimeMap.put("060708", "12:00-15:45");
		firstControlTimeMap.put("06070809", "12:00-16:50");
		firstControlTimeMap.put("0607080910", "12:00-17:40");//这个
		firstControlTimeMap.put("060708091011", "12:00-19:35");//这个
		firstControlTimeMap.put("06070809101112", "12:00-20:25");
		firstControlTimeMap.put("0607080910111213", "12:00-21:20");
		firstControlTimeMap.put("060708091011121314", "12:00-22:10");
		firstControlTimeMap.put("07", "13:30-14:55");
		firstControlTimeMap.put("08", "14:20-15:45");
		firstControlTimeMap.put("0708", "13:30-15:45");
		firstControlTimeMap.put("070809", "13:30-16:50");//这个
		firstControlTimeMap.put("07080910", "13:30-17:40");//这个
		firstControlTimeMap.put("0708091011", "13:30-19:35");//这个
		firstControlTimeMap.put("070809101112", "13:30-20:25");//这个
		firstControlTimeMap.put("07080910111213", "13:30-21:20");//这个
		firstControlTimeMap.put("0708091011121314", "13:30-22:10");//这个
		firstControlTimeMap.put("080910", "14:20-17:40");//这个666
		firstControlTimeMap.put("08091011", "14:20-19:35");//这个666
		firstControlTimeMap.put("0809101112", "14:20-20:25");//这个666
		firstControlTimeMap.put("080910111213", "14:20-21:20");//这个666
		firstControlTimeMap.put("08091011121314", "14:20-22:10");//这个666
		firstControlTimeMap.put("09", "15:25-16:50");
		firstControlTimeMap.put("10", "16:15-17:40");
		firstControlTimeMap.put("0910", "15:25-17:40");
		firstControlTimeMap.put("091011", "15:25-19:35");
		firstControlTimeMap.put("09101112", "15:25-20:25");
		firstControlTimeMap.put("0910111213", "15:25-21:20");
		firstControlTimeMap.put("091011121314", "15:25-22:10");//这个
		firstControlTimeMap.put("1011", "16:15-19:35");//这个
		firstControlTimeMap.put("101112", "16:15-20:25");//这个
		firstControlTimeMap.put("10111213", "16:15-21:20");//这个
		firstControlTimeMap.put("1011121314", "16:15-22:10");//这个
		secondControlTimeMap.put("11", "18:10-19:35");
		secondControlTimeMap.put("12", "19:00-20:25");
		secondControlTimeMap.put("1112", "18:10-20:25");
		secondControlTimeMap.put("13", "19:55-21:20");
		secondControlTimeMap.put("14", "20:45-22:10");
		secondControlTimeMap.put("1314", "19:55-22:10"); 
		secondControlTimeMap.put("111213", "18:10-21:20");
		secondControlTimeMap.put("11121314", "18:10-22:10");//这个
		secondControlTimeMap.put("1213", "19:00-21:20");//这个
		secondControlTimeMap.put("121314", "19:00-22:10");//这个
		
		
		//===================================================================================
		keBiaoMap.put("01", "第一大节");
		keBiaoMap.put("02", "第二大节");
		keBiaoMap.put("0102", "第一二大节");
		keBiaoMap.put("010203", "第一二三大节");
		keBiaoMap.put("01020304", "第一二三四大节");//这个
		keBiaoMap.put("0102030405", "第一二三四五大节");//这个
		keBiaoMap.put("010203040506", "第一二三四五六大节");//这个
		keBiaoMap.put("01020304050607", "第一二三四五六七大节");//这个
		keBiaoMap.put("0102030405060708", "第一二三四五六七八大节");//这个
		keBiaoMap.put("010203040506070809", "第一二三四五六七八九大节");//这个
		keBiaoMap.put("01020304050607080910", "第一二三四五六七八九十大节");//这个
		keBiaoMap.put("0102030405060708091011", "第一二三四五六七八九十十一大节");//这个
		keBiaoMap.put("010203040506070809101112", "第一二三四五六七八九十十一十二大节");//这个
		keBiaoMap.put("01020304050607080910111213", "第一二三四五六七八九十十一十二大节");//这个
		keBiaoMap.put("0102030405060708091011121314", "第一二三四五六七八九十十一十二十三十四大节");//这个
		keBiaoMap.put("0203", "第二三大节");//这个
		keBiaoMap.put("020304", "第二三四大节");//这个
		keBiaoMap.put("02030405", "第二三四五大节");//这个
		keBiaoMap.put("0203040506", "第二三四五六大节");//这个
		keBiaoMap.put("020304050607", "第二三四五六七大节");//这个
		keBiaoMap.put("02030405060708", "第二三四五六七八大节");//这个
		keBiaoMap.put("0203040506070809", "第二三四五六七八九大节");//这个
		keBiaoMap.put("020304050607080910", "第二三四五六七八九十大节");//这个
		keBiaoMap.put("02030405060708091011", "第二三四五六七八九十十一大节");//这个
		keBiaoMap.put("0203040506070809101112", "第二三四五六七八九十十一十二大节");//这个
		keBiaoMap.put("020304050607080910111213", "第二三四五六七八九十十一十二十三大节");//这个
		keBiaoMap.put("02030405060708091011121314", "第二三四五六七八九十十一十二十三十四大节");//这个
		keBiaoMap.put("03", "第三大节");
		keBiaoMap.put("04", "第四大节");
		keBiaoMap.put("0304", "第三四大节");
		keBiaoMap.put("030405", "第三四五大节");
		keBiaoMap.put("03040506", "第三四五六大节");
		keBiaoMap.put("0304050607", "第三四五六七大节");
		keBiaoMap.put("030405060708", "第三四五六七八大节");
		keBiaoMap.put("03040506070809", "第三四五六七八九大节");
		keBiaoMap.put("0304050607080910", "第三四五六七八九十大节");
		keBiaoMap.put("030405060708091011", "第三四五六七八九十十一大节");
		keBiaoMap.put("03040506070809101112", "第三四五六七八九十十一十二大节");
		keBiaoMap.put("0304050607080910111213", "第三四五六七八九十十一十二十三大节");
		keBiaoMap.put("030405060708091011121314", "第三四五六七八九十十一十二十三十四大节");
		keBiaoMap.put("0405", "第四五大节");
		keBiaoMap.put("040506", "第四五六大节");
		keBiaoMap.put("04050607", "第四五六七大节");
		keBiaoMap.put("0405060708", "第四五六七八大节");
		keBiaoMap.put("040506070809", "第四五六七八九大节");
		keBiaoMap.put("04050607080910", "第四五六七八九十大节");
		keBiaoMap.put("0405060708091011", "第四五六七八九十十一大节");
		keBiaoMap.put("040506070809101112", "第四五六七八九十十一十二大节");
		keBiaoMap.put("04050607080910111213", "第四五六七八九十十一十二十三大节");
		keBiaoMap.put("0405060708091011121314", "第四五六七八九十十一十二十三十四大节");
		keBiaoMap.put("05", "第五大节");
		keBiaoMap.put("06", "第六大节");
		keBiaoMap.put("0506", "第五六大节");
		keBiaoMap.put("050607", "第五六七大节");
		keBiaoMap.put("05060708", "第五六七八大节");
		keBiaoMap.put("0506070809", "第五六七八九大节");
		keBiaoMap.put("050607080910", "第五六七八九十大节");//这个
		keBiaoMap.put("05060708091011", "第五六七八九十十一大节");//这个
		keBiaoMap.put("0506070809101112", "第五六七八九十十一十二大节");//这个
		keBiaoMap.put("050607080910111213", "第五六七八九十十一十二十三大节");//这个
		keBiaoMap.put("05060708091011121314", "第五六七八九十十一十二十三十四大节");//这个
		keBiaoMap.put("0607", "第六七大节");
		keBiaoMap.put("060708", "第六七八大节");
		keBiaoMap.put("06070809", "第六七八九大节");
		keBiaoMap.put("0607080910", "第六七八九十大节");//这个
		keBiaoMap.put("060708091011", "第六七八九十十一大节");//这个
		keBiaoMap.put("06070809101112", "第六七八九十十一十二大节");//这个
		keBiaoMap.put("0607080910111213", "第六七八九十十一十二十三大节");//这个
		keBiaoMap.put("060708091011121314", "第六七八九十十一十二十三十四大节");//这个
		keBiaoMap.put("07", "第七大节");
		keBiaoMap.put("08", "第八大节");
		keBiaoMap.put("0708", "第七八大节");
		keBiaoMap.put("070809", "第七八九大节");//这个
		keBiaoMap.put("07080910", "第七八九十大节");//这个
		keBiaoMap.put("0708091011", "第七八九十十一大节");//这个
		keBiaoMap.put("070809101112", "第七八九十十一十二大节");//这个
		keBiaoMap.put("07080910111213", "第七八九十十一十二十三大节");//这个
		keBiaoMap.put("0708091011121314", "第七八九十十一十二十三十四大节");//这个
		keBiaoMap.put("080910", "第八九十大节");//这个666
		keBiaoMap.put("08091011", "第八九十十一大节");//这个666
		keBiaoMap.put("0809101112", "第八九十十一十二大节");//这个666
		keBiaoMap.put("080910111213", "第八九十十一十二十三大节");//这个666
		keBiaoMap.put("08091011121314", "第八九十十一十二十三十四大节");//这个666
		keBiaoMap.put("09", "第九大节");
		keBiaoMap.put("10", "第十大节");
		keBiaoMap.put("0910", "第九十大节");
		keBiaoMap.put("091011", "第九十十一大节");//这个
		keBiaoMap.put("09101112", "第九十十一十二大节");//这个
		keBiaoMap.put("0910111213", "第九十十一十二十三大节");//这个
		keBiaoMap.put("091011121314", "第九十十一十二十三十四大节");//这个
		keBiaoMap.put("1011", "第十十一大节");//这个
		keBiaoMap.put("101112", "第十十一十二大节");//这个
		keBiaoMap.put("10111213", "第十十一十二十三大节");//这个
		keBiaoMap.put("1011121314", "第十十一十二十三十四大节");//这个
		keBiaoMap.put("11", "第十一大节");
		keBiaoMap.put("12", "第十二大节");
		keBiaoMap.put("1112", "第十一十二大节");
		keBiaoMap.put("13", "第十三大节");
		keBiaoMap.put("14", "第十四大节");
		keBiaoMap.put("1314", "第十三十四大节");
		keBiaoMap.put("111213", "第十一十二十三大节");
		keBiaoMap.put("11121314", "第十一十二十三十四大节");//这个
		keBiaoMap.put("1213", "第十二十三大节");//这个
		keBiaoMap.put("121314", "第十二十三十四大节");//这个
	}
	/**
	 * 根据当前课表和上课日期 获取对应当前上课开始结束时间
	 * @param controlTimeMap
	 * @param runDate
	 * @param kcsjmx
	 * @return
	 */
	public static String[] generateContrlTimeByKebiao(Map<String,String> controlTimeMap,String runDate,KebiaoModel kebiao){
    	String kcsjmx = Pattern.compile(",[\\d]").matcher(kebiao.getKcsjmx()).replaceAll("").replaceAll(",", "");
		String controlTime = controlTimeMap.get(kcsjmx);
		String openTime = runDate+" "+controlTime.split("-")[0];
		String flag = String.valueOf(kebiao.getJsid().charAt(2));
		if((flag.equals("A")||flag.equals("B"))&&kcsjmx.startsWith("01")){
			openTime = runDate+" "+LateOpenTime;
		}
		String closeTime = runDate+" "+controlTime.split("-")[1];
		return new String[]{openTime,closeTime};
	}
}
