package ecapi.api;

public abstract class ApiListen {
	/**
	 * 收到服务端相应
	 * 
	 * 5、当电箱第一路输出从闭合到断开时，发送数据：0D 0A 00 01 03 01 00 0D 0A
	 * 6、当电箱第一路输出从断开到闭合时，发送数据：0D 0A 00 01 03 01 01 0D 0A
	 * 7、当电箱第二路输出从闭合到断开时，发送数据：0D 0A 00 01 03 02 00 0D 0A
	 * 8、当电箱第二路输出从断开到闭合时，发送数据：0D 0A 00 01 03 02 01 0D 0A
	 * 
	 * 13 A 当空调被红外遥控打开后，电箱检测到空调电流很大   0D 0A 00 01 00 01 01 0D 0A
	 * 13 B 当空调被红外遥控关闭后，电箱检测到空调的电流很微弱    0D 0A 00 01 00 01 00 0D 0A
	 * 15 投影及幕布同时关闭  0001020300
	 * 
	 * 17 反馈继电器各个输出脚状态信息，数据格式类似于 22 01 10 00 00 00 01 33 0D 0A	
	 * 18 反馈继电器幕布以及投影状态，数据格式类似于：22 01 70 00 00 00 01 94 0D 0A
	 * 
	 * @param response
	 */
	abstract public void response(byte[] response);
	/**
	 * 连接开启
	 */
	abstract public void connected();
	/**
	 * 连接中断
	 */
	abstract public void closed();
}
