package conf;

import util.Functions;
public class Defined {
	/** 设备响应 空调电闸开关关闭 */
	public final static String CMD_RESP_AIR_SWITCH_ON = "0D0A00010301010D0A";
	/** 设备响应 空调电闸开关开启 */
	public final static String CMD_RESP_AIR_SWITCH_OFF = "0D0A00010301000D0A";
	/** 设备响应 其他电闸开关关闭 */
	public final static String CMD_RESP_OTHER_SWITCH_OFF = "0D0A00010302000D0A";
	/** 设备响应 其他电闸开关开启 */
	public final static String CMD_RESP_OTHER_SWITCH_ON = "0D0A00010302010D0A";
	/** 空调开机 */
	public final static String CMD_RESP_AIR_ON = "0D0A00010001010D0A";
	/** 空调关机 */
	public final static String CMD_RESP_AIR_OFF = "0D0A00010001000D0A";
	/** 投影仪布幕下来 */
	public final static String CMD_RESP_TOUY_DOWN = "0D0A00010002010D0A";//0D0A00010002010D0A
	/** 投影仪布幕上升 */
	public final static String CMD_RESP_TOUY_UP = "0D0A00010002000D0A";//0D0A00010002000D0A
	/** 监控开启 */
	public final static String CMD_RESP_MONITOR_ON = "";
	/** 监控关闭 */
	public final static String CMD_RESP_MONITOR_OFF = "";
	/** 繁忙时间监控指令 */
	public final static String CMD_REQ_BUSY_CMD = "0000040001";
	/** 空闲时间监控指令 */
	public final static String CMD_REQ_FREE_CMD = "00000400FF";
	/** 状态同步指令 */
	public final static String CMD_REQ_STA_SYNC_HEX2BYTE = "5501100000000066";//状态同步指令，继电器电脚状态
	/** 投影仪状态 */
	public final static String CMD_REQ_TOUYSTA_SYNC_HEX2BYTE = "55017000000001C7";//状态同步指令，投影仪状态
	
	/** 状态同步指令 */
	//public final static String CMD_REQ_STA_SYNC_HEX2BYTE = "5500100000000166";//状态同步指令，继电器电脚状态
	/** 投影仪状态 */
	//public final static String CMD_REQ_TOUYSTA_SYNC_HEX2BYTE = "55007000000001C6";//状态同步指令，投影仪状态
	/** 状态同步指令70套版本 */
	public final static String CMD_REQ_STA_SYNC_HEX2BYTE_2 = "5500100000000166 ";//状态同步指令，继电器电脚状态
	/** 投影仪状态 70套版本*/
	public final static String CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2 = "55007000000001C6";//状态同步指令，投影仪状态
	
	/** 开启/关闭空调指令 */
	public final static String CMD_REQ_OPENORCLOSE_AIRCONDITIONER = "1001010104";//
	/** 空调红外开机并设置16度高风强制冷模式 */
	public final static String CMD_REQ_SET16_AIRCONDITIONER = "1001010101";//
	/** 空调红外开机并设置23度高风强制冷模式 */
	public final static String CMD_REQ_SET23_AIRCONDITIONER = "1001010102";//
	/** 空调红外开机并设置26度高风强制冷模式 */
	public final static String CMD_REQ_SET26_AIRCONDITIONER = "1001010103";//
	/** 开启空调电闸指令*/
	public final static String CMD_REQ_OPEN_AIRSWICH = "0001010101";//
	/** 开启空调电闸指令*/
	public final static String CMD_REQ_CLOSE_AIRSWICH = "0001010100";//
	/** 开启其他电闸指令*/
	public final static String CMD_REQ_OPEN_OTHERSWICH = "0001010201";//
	/** 关闭其他电闸指令*/
	public final static String CMD_REQ_CLOSE_OTHERSWICH = "0001010200";//
	/** 开启投影仪指令*/
	public final static String CMD_REQ_OPEN_TOUY = "0001020301";//
	/** 关闭投影仪指令*/
	public final static String CMD_REQ_CLOSE_TOUY = "0001020300";//
	/** 关闭云终端*/
	public final static String CMD_REQ_CLOSE_CLOUD = "@SHUTDOWN\r\n";//
	/** 云终端文字消息*/
	public final static String CMD_REQ_CLOUD_TEXT= "@SHOW_MESSAGE#@#REPLACE";//
	/** 电箱蜂鸣指令*/
	public final static String CMD_REQ_ELECTRICBUZZER = "0001010501";//
	
	public final static String CLASSES_SAVE_PATH = "classes.obj";
	public final static String BUILD_SAVE_PATH = "build.obj";
	public final static String FLOOR_SAVE_PATH = "floor.obj";
	public final static String SYNC_OLD_STATUS = "2201700000000194";
	public static String getNote(String cmd){
		String out = "";
		switch (cmd) {
		case CMD_REQ_OPENORCLOSE_AIRCONDITIONER:out = "开启/关闭空调指令:"+CMD_REQ_OPENORCLOSE_AIRCONDITIONER;break;
		case CMD_REQ_BUSY_CMD:out = "繁忙时间监控指令 :"+CMD_REQ_BUSY_CMD;break;
		case CMD_REQ_CLOSE_AIRSWICH:out = "关闭空调电闸指令 :"+CMD_REQ_CLOSE_AIRSWICH;break;
		case CMD_REQ_CLOSE_CLOUD:out = "关闭云终端 :"+CMD_REQ_CLOSE_CLOUD;break;
		case CMD_REQ_CLOSE_OTHERSWICH:out = "关闭其他电闸指令 :"+CMD_REQ_CLOSE_OTHERSWICH;break;
		case CMD_REQ_CLOSE_TOUY:out = "关闭投影仪指令 :"+CMD_REQ_CLOSE_TOUY;break;
		case CMD_REQ_ELECTRICBUZZER:out = "电箱蜂鸣指令 :"+CMD_REQ_ELECTRICBUZZER;break;
		case CMD_REQ_FREE_CMD:out = "空闲时间监控指令 :"+CMD_REQ_FREE_CMD;break;
		case CMD_REQ_OPEN_AIRSWICH:out = "开启空调电闸指令 :"+CMD_REQ_OPEN_AIRSWICH;break;
		case CMD_REQ_OPEN_OTHERSWICH:out = "开启其他电闸指令 :"+CMD_REQ_OPEN_OTHERSWICH;break;
		case CMD_REQ_OPEN_TOUY:out = "开启投影仪指令 :"+CMD_REQ_OPEN_TOUY;break;
		case CMD_REQ_SET16_AIRCONDITIONER:out = "空调红外开机并设置16度高风强制冷模式 :"+CMD_REQ_SET16_AIRCONDITIONER;break;
		case CMD_REQ_SET23_AIRCONDITIONER:out = "空调红外开机并设置23度高风强制冷模式 :"+CMD_REQ_SET23_AIRCONDITIONER;break;
		case CMD_REQ_SET26_AIRCONDITIONER:out = "空调红外开机并设置26度高风强制冷模式 :"+CMD_REQ_SET26_AIRCONDITIONER;break;
		case CMD_REQ_STA_SYNC_HEX2BYTE:out = "状态同步指令:"+CMD_REQ_STA_SYNC_HEX2BYTE;break;
		case CMD_REQ_TOUYSTA_SYNC_HEX2BYTE :out = "投影仪状态同步:"+CMD_REQ_TOUYSTA_SYNC_HEX2BYTE;break;
		
		case CMD_RESP_AIR_OFF :out = "空调关机:"+CMD_RESP_AIR_OFF;break;
		case CMD_RESP_AIR_ON :out = "空调开机:"+CMD_RESP_AIR_ON;break;
		case CMD_RESP_AIR_SWITCH_OFF :out = "设备响应 空调电闸开关开启:"+CMD_RESP_AIR_SWITCH_OFF;break;
		case CMD_RESP_AIR_SWITCH_ON :out = "设备响应 空调电闸开关关闭:"+CMD_RESP_AIR_SWITCH_ON;break;
		case CMD_RESP_TOUY_DOWN :out = "投影仪布幕下来:"+CMD_RESP_TOUY_DOWN;break;
		case CMD_RESP_TOUY_UP :out = "投影仪布幕上升:"+CMD_RESP_TOUY_UP;break;
		case CMD_RESP_OTHER_SWITCH_OFF :out = "设备响应 其他电闸开关关闭:"+CMD_RESP_OTHER_SWITCH_OFF;break;
		case CMD_RESP_OTHER_SWITCH_ON :out = "设备响应 其他电闸开关开启:"+CMD_RESP_OTHER_SWITCH_ON;break;
		case SYNC_OLD_STATUS:out="同步旧模组状态成功***********："+SYNC_OLD_STATUS;break;
		default:
			break;
		}
		if(out.equals("")) out = "未识别指令(16进制):"+cmd;
		return out;
	}
	
	public static void main(String[] args) {
		String info="12 24 9A";
		System.out.println(info.getBytes()[1]);
	}
	
}
