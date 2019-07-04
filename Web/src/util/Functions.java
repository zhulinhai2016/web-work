package util;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

public class Functions {
	public static void main(String[] args){
		String hexString = "0D 0A 00 01 00 02 00 0D 0A";
		
		System.out.println(hexString2Byte(hexString));
	}
	/** 
     * 字符串转换为16进制字符串 
     *  
     * @param s 
     * @return 
     */  
    public static String stringToHexString(String s) {  
        String str = "";  
        for (int i = 0; i < s.length(); i++) {  
            int ch = (int) s.charAt(i);  
            String s4 = Integer.toHexString(ch);  
            str = str + s4;  
        }  
        return str;  
    } 
	public  static String byteArray2String(byte[] cmd){
		StringBuffer buff = new StringBuffer();
		for(int b : cmd) buff.append(b);
		return buff.toString();
	}
	
	public static String byteToHexString(byte[] byteArr){
		StringBuffer buff = new StringBuffer();
		for(byte b : byteArr) buff.append(String.format("%02X", b));
		return buff.toString();
	}
	
	public static byte[] hexString2Byte(String hexString){
		ByteArrayOutputStream buff = new ByteArrayOutputStream();
		int len = hexString.length();
		if(hexString.matches("[0-9a-fA-F]+") && len%2==0){
			for(int i=0; i<len; i+=2){
				buff.write(Integer.parseInt(hexString.substring(i, i+2), 16));
			}
		}
		return buff.toByteArray();
	}
	
	public final static String MD5(String s) {
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            //e.printStackTrace();
            return null;
        }
    }
}
