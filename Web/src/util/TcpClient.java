package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ResourceBundle;
import java.util.Timer;



/**
 * tcp请求类
 * 
 * @author LinHaiZhu
 *
 *         创建于：2017年11月17日-下午5:30:10
 */
public class TcpClient {

	private  String ip;
	private  int port ;

	/**
	 * 发送并接收数据
	 * @param task 任务对象
	 * @return
	 */
	public static void sendTcpMsg(String code) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			// 1. 创建Socket实例
		//	socket = new Socket(ip, port);
			socket.setSoTimeout(1000);
			// 2. 获取输出流,向服务器发生数据
			outputStream = socket.getOutputStream();

			byte[] bytes = MyFunc.hexStringToBytes("77 03 0D 2A 70 6F 77 3D 6F 6E 23 0D F2 35");
			// 3. 获取输入流,获取服务器的响应
			inputStream = socket.getInputStream();
			// 4. 通过端口写出数据
			outputStream.write(bytes);
			int len = 0;
			len = inputStream.read();
			byte[] rev = new byte[len];
			inputStream.read(rev);
			System.out.println(MyFunc.ByteArrToHex(rev));
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			try {
				inputStream.close();
				outputStream.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	//	TcpClient.sendTcpMsg();
	}
	public TcpClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
}
