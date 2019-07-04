package ecapi.api;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

class StringUtil {
	private static String hexString = "0123456789ABCDEF";
	
	public static String hexString2Str(String bytes) 
	{ 
		ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2); 
		//将每2位16进制整数组装成一个字节 
		for(int i=0;i<bytes.length();i+=2) 
		baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1)))); 
		return new String(baos.toByteArray()); 
	} 
	
	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String str2HexString(String str) {
	    // 根据默认编码获取字节数组
	    byte[] bytes = str.getBytes();
	    StringBuilder sb = new StringBuilder(bytes.length * 2);
	    // 将字节数组中每个字节拆解成2位16进制整数
	    for (int i = 0; i < bytes.length; i++) {
	    sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
	    sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
	    }
	    return sb.toString();
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int[] b = new int[]{0x55 ,0x01 ,0x70 ,0x00 ,0x00 ,0x00 ,0x01 ,0xc7};
		for(int a : b){
			buf.write(a);
		}
		byte[] bb = buf.toByteArray();
		for(int ss : bb){
			System.out.println(Integer.toHexString(ss&0xff));
		}
		
	}
}
