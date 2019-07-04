package ecapi.model;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.json.JSONObject;

import com.iot.model.RunInfo;

import conf.Defined;
import ecapi.api.RunInfoFactory;
import util.Constans;
import util.Functions;
import util.MyFunc;
import util.MyLog;

public class ClassesDevModel {
	/** 空调电闸 */
	private String air_switch = "off";
	/** 其他电闸 */
	private String other_switch = "off";
	/** 空调电流 */
	private String air_conditioner = "off";
	/** 投影仪  */
	private String touy = "off";
	/** 监控  */
	private String monitor = "off";
	/** 云终端 */
	private String cloud_terminal = "off";
	
//	public static void main(String[] args){
//		ClassesDevModel d = new ClassesDevModel();
//		d.setAir_conditioner("on");
//		JSONObject j = new JSONObject(d.getMap());
//		System.out.println(j.toString());
//	}
	
	public HashMap<String, String> getMap(){
		HashMap<String, String> out = new HashMap<String, String>();
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields){				
				String k = f.getName();
				String v = (String) f.get(this);
				out.put(k, v);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return out;
	}
	
	public JSONObject getJSONObject(){
		return new JSONObject(getMap());
	}
	
	public boolean isAllClosed(){
		
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for(Field f : fields){
				if(f.get(this).equals("on")) return false;
			}
		} catch (Exception e) {}		
		return true;
	}
	
	public void changeStatus(byte[] cmd,ClassesInfoModel c){
		MyLog.debug("修改状态");
		try{
			String str = Functions.byteToHexString(cmd);//16进制字符串
			String decString = Functions.byteToHexString(cmd);//十进制字符串
			// TODO 同步继电器状态 22 00 10 00 00 00 03 35
			// 22 00 70 00 00 01 01 94
			// 22 01 10 00 00 00 03 36 0D 0A  
			// 22 00 10 00 00 00 00 32
			
			// 22 00 70 00 00 00 01 93
			// 22 01 70 00 00 00 01 94 0D 0A 
			if((str.startsWith("220110") || str.startsWith("220010")) && cmd.length>=8){
				//返回的状态同步指令 16进制字符串
				sync220110HexString(Functions.hexString2Byte(str));
			}else if((cmd.length>=3)&&(cmd[0]==0x22 && cmd[1]==0x01 && cmd[2]==0x10 ) || (cmd[0]==0x22 && cmd[1]==0x00 && cmd[2]==0x10 )){
				//返回的状态同步指令 16进制
				sync220110HexString(cmd);
			}else if((decString.startsWith("220170")||decString.startsWith("220070") || decString.indexOf("220070") != -1) && decString.length()>=10){
				if(decString.startsWith("220070") || decString.indexOf("220070") != -1){
//					22 00 70 00 00 01 01 94
					//2201700000000194
					if((cmd[5]&0x01) == 1)setTouy("on");//投影
					if((cmd[5]&0x01) == 0)setTouy("off");//投影
				} else{
					
					if((cmd[5]&0x01) == 1)setTouy("on");//投影
					if((cmd[5]&0x01) == 0)setTouy("off");//投影
				}
			}else if(cmd[0]==0x22 && cmd[1]==0x01 && cmd[2]==0x70){
				if((cmd[5] & 0x01) == 1)
					setTouy("on");//投影			
				else if((cmd[5] & 0x01) == 0)
					setTouy("off");//投影			
			}else if(str.equals(Defined.CMD_RESP_AIR_SWITCH_ON)){
				setAir_switch("on");
			}else if(str.equals(Defined.CMD_RESP_AIR_SWITCH_OFF)){
				setAir_switch("off");
			}else if(str.equals(Defined.CMD_RESP_OTHER_SWITCH_ON)){
				setOther_switch("on");
			}else if(str.equals(Defined.CMD_RESP_OTHER_SWITCH_OFF)){
				setOther_switch("off");
			}else if(str.equals(Defined.CMD_RESP_AIR_ON)){
				setAir_conditioner("on");
			}else if(str.equals(Defined.CMD_RESP_AIR_OFF)){
				setAir_conditioner("off");
			}else if(str.equals(Defined.CMD_RESP_TOUY_DOWN)){
				setTouy("on");		
			}else if(str.equals(Defined.CMD_RESP_TOUY_UP)){
				setTouy("off");	
			}else{
				//最后返回的其他指令			
			}
			
			if(this.getTouy().equals("on")){
				// 表示开启了投影
				try {
					RunInfo runInfo = new RunInfo();
					runInfo.setClassRoomId(Long.parseLong(c.getId()));
					runInfo.setDeviceType(Constans.DEVICE_TYPE[2]);
					RunInfoFactory.openDevice(runInfo);
					runInfo = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (this.getTouy().equals("off")) {
				// 表示关闭了投影
				try {
					RunInfo runInfo = new RunInfo();
					runInfo.setClassRoomId(Long.parseLong(c.getId()));
					runInfo.setDeviceType(Constans.DEVICE_TYPE[2]);
					RunInfoFactory.closeDevice(runInfo);
					runInfo = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				if(this.getAir_conditioner().equals("on")){
					RunInfo runInfo = new RunInfo();
					runInfo.setClassRoomId(Long.parseLong(c.getId()));
					runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
					RunInfoFactory.openDevice(runInfo);
					runInfo = null;
				} else {
					RunInfo runInfo = new RunInfo();
					runInfo.setClassRoomId(Long.parseLong(c.getId()));
					runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
					RunInfoFactory.closeDevice(runInfo);
					runInfo = null;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	/**
	 * 处理返回同步220110指令（10进制数据）
	 * @param decString
	 */
	private void sync220110DecString(String decString){
		//返回的状态同步指令
		if(decString.charAt(13) == '1')setAir_switch("on");//空调开关
		else if(decString.charAt(13) == '0')setAir_switch("off");//空调开关
		
		if(decString.charAt(10) == '1')setAir_switch("on");//空调
		else if(decString.charAt(10) == '0')setAir_switch("off");//空调
		
		if(decString.charAt(13) == '2')setAir_switch("on");//其他电闸
		else if(decString.charAt(13) == '0')setAir_switch("off");//其他电闸
		
		if(decString.charAt(13) == '4');//监控
		else if(decString.charAt(13) == '0');//监控		
	}
	
	/**
	 * 处理返回同步220110指令（10进制数据）
	 * @param decString
	 */
	private void sync220110HexString(byte[] cmd){
		// 22 01 10 00 00 00 03 36 0D 0A 
		// 0000 0010 & 
		// 0000 0001 = 0000 00000 != 1
		//返回的状态同步指令
		if((cmd[6]&0x01) == 1)
			setAir_switch("on");//空调开关
		if((cmd[6]&0x01) == 0)
			setAir_switch("off");//空调开关
		if((cmd[4]&0x01) == 1)
			setAir_conditioner("on");//空调
		if((cmd[4]&0x01) == 0)
			setAir_conditioner("off");//空调
		if((cmd[6]&0x02) == 2)
			setOther_switch("on");//其他电闸
		if((cmd[6]&0x02) == 0)
			setOther_switch("off");//其他电闸
		if((cmd[6]&0x04) == 4);//监控
		if((cmd[6]&0x04) == 0);//监控	
	}
	
	public String getAir_switch() {
		return air_switch;
	}
	public void setAir_switch(String air_switch) {
		this.air_switch = air_switch;
	}
	public String getOther_switch() {
		return other_switch;
	}
	public void setOther_switch(String other_switch) {
		this.other_switch = other_switch;
	}
	public String getAir_conditioner() {
		return air_conditioner;
	}
	public void setAir_conditioner(String air_conditioner) {
		this.air_conditioner = air_conditioner;
	}
	
	public String getTouy() {
		return touy;
	}

	public void setTouy(String touy) {
		this.touy = touy;
	}

	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getCloud_terminal() {
		return cloud_terminal;
	}
	public void setCloud_terminal(String cloud_terminal) {
		this.cloud_terminal = cloud_terminal;
	}
	public static void main(String[] agrs){
		String decString = "220010000000033522007000000001930D0A";
		String[] dsf = decString.split("2200");
		
		String des = decString.substring(decString.indexOf("220070"));
		System.out.println(des);
	}
}
