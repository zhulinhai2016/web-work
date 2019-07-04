package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author LinHaiZhu
 *
 *         创建于：2018年7月11日-上午9:55:53
 */
public class PingUtils {

	public static boolean ping(String ipAddress, int pingTimes, int timeOut) {
		BufferedReader in = null;
		Runtime r = Runtime.getRuntime(); // 将要执行的ping命令,此命令是windows格式的命令
		String pingCommand = "ping " + ipAddress + " -n " + pingTimes + " -w " + timeOut;
		try { // 执行命令并获取输出
			System.out.println(pingCommand);
			Process p = r.exec(pingCommand);
			if (p == null) {
				return false;
			}
			in = new BufferedReader(new InputStreamReader(p.getInputStream())); // 逐行检查输出,计算类似出现=23ms
																				// TTL=62字样的次数
			String line = null;
			while ((line = in.readLine()) != null) {
				if (getCheckResult(line) > 0) {
					return true;
				}
			} // 如果出现类似=23ms TTL=62这样的字样
			return false;
		} catch (Exception ex) {
			ex.printStackTrace(); // 出现异常则返回假
			return false;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 若line含有=18ms TTL=16字样,说明已经ping通,返回1,否則返回0.
	private static int getCheckResult(String line) { 
		Pattern pattern = Pattern.compile("(\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
		String ipAddress = "127.0.0.1";
		System.out.println(ping(ipAddress, 5, 5000));
	}
}
