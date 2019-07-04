package util;

/**
 * 常量类
 * @author LinHaiZhu
 *
 * 创建于：2018年7月7日-上午11:41:02
 */
public class Constans {
	/**
	 * 设备类型<BR/>
	 * 0：0其他，1：1中控，2：2投影，3：3空调，4：4电箱
	 */
	public static final String[] DEVICE_TYPE = {"0","1","2","3","4"};
	/**
	 * 设备类型<BR/>
	 * 0：0其他，1：1开机指令失败，2：2关机指令失败，3：3断网，4：远程调用失败（包括1,2），5：断网中间状态
	 */
	public static final String[] ERROR_TYPE = {"0","1","2","3","4","5"};
}
