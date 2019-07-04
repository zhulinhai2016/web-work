package util;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

public class MyLog {
	/**
	 * 获取时间
	 * @return
	 */
	public static String getTimeStr(){
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		sf.setTimeZone(TimeZone.getTimeZone("PRC"));
		return sf.format(new Date());		
	}
	
	/**
	 * throw out access message to access log
	 * 
	 * @param message
	 */ 
	public static void debug(String message){
//		message = "[" + (new Date()).toString() + "] " + message;
//		File f = new File("C:/log.txt");
//		try {
//			if(!f.exists()){
//				f.createNewFile();
//			}
//			java.util.List<String> list =FileUtils.readLines(f);
//			list.add(message);
//			FileUtils.writeLines(f,"UTF-8", list);
//		} catch (IOException e) {		
//			e.printStackTrace();
//		}
		Logger logger = Logger.getLogger(MyLog.class);
		String logStr;
		try {
			logStr = new String(message.getBytes(),"UTF-8");
			logger.info(logStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(message);
	}
	
}
