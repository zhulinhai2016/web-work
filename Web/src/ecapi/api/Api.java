package ecapi.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import util.Functions;

public class Api{
	private Client c = null;
	private String host;
	private int port;
	private ApiListen callback;
	
	public static void main(String[] args) {
		
	}
	
	public Api(String host,int port,ApiListen callback){
		this.host = host;
		this.port = port;
		this.callback = callback;
	}
	
	/**
	 * 初始化连接
	 * @return
	 */
	public boolean init(){
		if(null==host || port<0) return false;
		if(null == c) c = new Client(host, port, callback);
		if(null!=c) return c.connect(); 
		return false;
	}
	/**
	 * 监控初始化连接
	 * @return
	 */
	public boolean monitorInit(){
		if(null==host || port<0) return false;
		if(null == c) c = new Client(host, port, callback);
		if(null!=c) return c.connect();
		return false;
	}
	/**
	 * 中控初始化连接
	 * @return
	 */
	public boolean centerInit(){
		if(null==host || port<0) return false;
		if(null == c) c = new Client(host, port, callback);
		if(null!=c) return c.connect();
		return false;
	}
	
	/**
	 * 关闭连接
	 */
	public void close(){
		if(null!=c)	c.Close();		
	}
	
	/**
	 * 开启空调电闸 
	 * 
	 * 0001010101
	 */
	public boolean cmd1(){
		String cmd = "0001010101";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 关闭空调电闸 
	 * 0001010100
	 */
	public boolean cmd2(){
		String cmd = "0001010100";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 开启其他电器电闸
	 * 0001010201
	 */
	public boolean cmd3(){
		String cmd = "0001010201";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 关闭其他电器电闸
	 * 0001010201
	 */
	public boolean cmd4(){
		String cmd = "0001010200";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 蜂鸣器响15秒
	 * 0001010501
	 */
	public boolean cmd9(){
		String cmd = "0001010501";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 空调红外开机并设置16度高风强制冷模式
	 * 1001010101
	 */
	public boolean cmd10(){
		String cmd = "1001010101";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 空调红外开机并设置23度中风制冷模式
	 * 1001010103
	 */
	public boolean cmd11(){
		String cmd = "1001010103";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 空调红外开机并设置26度自动风制冷模式
	 * 1001010103
	 */
	public boolean cmd12(){
		String cmd = "1001010103";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 空调红外关机
	 * 1001010104
	 */
	public boolean cmd13(){
		String cmd = "1001010104";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 投影及幕布同时开启
	 * 1001010104
	 */
	public boolean cmd14(){
		String cmd = "0001020301";
		return c.write(cmd.getBytes());
	}
	
	/**
	 * 反馈继电器各个输出脚状态信息     cmd18 跟cmd17换过来了  
	 */
	public boolean cmd17(){
		byte[] b = new byte[]{0x55, 0x01, 0x10 ,0x00, 0x00 ,0x00 ,0x00 ,0x66};
		return c.write(b);		
	}
	
/*	public boolean cmd18(){
		int[] b = new int[]{0x55, 0x01, 0x10 ,0x00, 0x00 ,0x00 ,0x00 ,0x66};
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		for(int a : b) buf.write(a);
		boolean r = c.write(buf.toByteArray());	
		try {
			buf.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return r;
	}
	*/
	/**
	 * 反馈继电器各个输出脚状态信息
	 */
	public boolean cmd18(){
		int[] b = new int[]{0x55 ,0x01 ,0x70 ,0x00 ,0x00 ,0x00 ,0x01 ,0xc7};
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		for(int a : b) buf.write(a);
		boolean r = c.write(buf.toByteArray());	
		try {
			buf.flush();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return r;
	}
	
	
	public boolean cmd(String s){
		return cmd(s.getBytes());
	}
	
	/**
	 * 输入其他指令
	 * 
	 * @param b
	 */
	public boolean cmd(byte[] b){
		System.out.println("request:"+Functions.byteToHexString(b));
		if(c == null && !this.init()) return false;
		if(c!=null && !c.isConnected() && !c.connect()) return false;//如果未连接尝试连接
		if(c!=null && c.isConnected()) return c.write(b);	
		return false;
	}
	/**
	 * 输入其他zifuchuan指令
	 * 
	 * @param b
	 */
	public boolean cmdStr(String b){
		if(c == null && !this.init()) return false;
		if(c!=null && !c.isConnected() && !c.connect()) return false;//如果未连接尝试连接
		if(c!=null && c.isConnected()) return c.writeStr(b);	
		return false;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}	
}
