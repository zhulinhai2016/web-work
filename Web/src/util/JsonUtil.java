package util;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	/**
	 * object转为json
	 * 
	 * @param response
	 * @param data
	 */
	public static String toJson(Object data) {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.disableHtmlEscaping()// 避免Html特殊字符转为Unicode
				.serializeNulls()// 值为null的key-value也输出
				.create();
		return g.toJson(data);
	}

	/**
	 * json转为object
	 * @param <T>
	 * 
	 * @param data
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObject(String data,Class<?> clzze) {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.disableHtmlEscaping()// 避免Html特殊字符转为Unicode
				.serializeNulls()// 值为null的key-value也输出
				.create();
		return (T) ((data == null || data.equals(""))?null:g.fromJson(data, clzze));
	}
	
	@SuppressWarnings("unchecked")
	public static Object jsonToObject(String data) {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.disableHtmlEscaping()// 避免Html特殊字符转为Unicode
				.serializeNulls()// 值为null的key-value也输出
				.create();
		return ((data == null || data.equals(""))?null:g.fromJson(data, Object.class));
	}
}
