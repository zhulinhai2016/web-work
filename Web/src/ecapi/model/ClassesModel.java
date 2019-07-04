package ecapi.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.iot.model.RunError;
import com.iot.model.RunInfo;

import conf.Defined;
import ecapi.api.Api;
import ecapi.api.ApiListen;
import ecapi.api.RunErrorFactory;
import ecapi.api.RunInfoFactory;
import util.Constans;
import util.Functions;
import util.MyFunc;
import util.MyLog;

/**
 * 班级相关的操作与监控
 * 
 * @author gary
 *
 */
public class ClassesModel {
	/** 通讯接口 */
	private Api api = null;

	/** 中控接口 wjd0823 */
	private Api centerApi = null;
	/** 中控网络状态 wjd0823 */
	private boolean center_network_status = false;

	/** 监控接口 wjd0823 */
	private Api monitorApi = null;
	/** 监控网络状态 wjd0823 */
	private boolean monitor_network_status = false;

	/** 设备状态 */
	private ClassesDevModel devStatus = null;
	private String host = "";
	private int port = 485;
	private String className = "";
	private boolean network_status = false;
	private long last_close_tm = 0;
	private static ArrayList<String> methods = null;
	private boolean statysSync = false;
	private AutoConnect autoConnected = null;
	private ClassesInfoModel c = null;
	private Integer syncFailTimes=0;
	private Timer time =null;
	public ClassesModel(ClassesInfoModel c) {
		this.c = c;
		this.host = c.getServerHost();
		this.port = c.getServerPort();
		this.className = c.getName();
		this.devStatus = new ClassesDevModel();

		/** 初始中控wjd0823 */
		initCenter();
		/** 初始wjd监控0823 */
		initMonitor();

		init(); 
//		autoConnected = new AutoConnect();
//		autoConnected.start();
		autoControl();// 自动逻辑控制
	}

	@SuppressWarnings("deprecation")
	public void destory() {
		if (null != autoConnected)
			try {
//				autoConnected.join();
				autoConnected.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (null != api) {
			api.close();
			api = null;
		}
		if (centerApi != null) {
			centerApi.close();
		}
		if (monitorApi != null) {
			monitorApi.close();
		}
	}

	public ClassesDevModel getDevStatus() {
		return devStatus;
	}

	public static ArrayList<String> getWebMethods() {
		if (methods == null) {
			methods = new ArrayList<String>();
			methods.add("openAirConditioner");
			methods.add("setAirConditioner16Degrees");
			methods.add("setAirConditioner23Degrees");
			methods.add("setAirConditioner26Degrees");
			methods.add("closeAirConditioner");

			methods.add("openAirSwitch");
			methods.add("closeAirSwitch");

			methods.add("openOtherSwitch");
			methods.add("closeOtherSwitch");

			methods.add("openTouy");
			methods.add("closeTouy");

			methods.add("openMonitor");
			methods.add("closeMonitor");
			methods.add("openCloudTerminal");
			methods.add("closeCloudTerminal");

			// methods.add("openAll");//8.28
			methods.add("batOpenNoAir");
			methods.add("batCloseNoAir");
			methods.add("batOpenHasAir");
			methods.add("batCloseHasAir");
			methods.add("syncCmd");
			methods.add("closeCenterCtrl");
			// methods.add("send");
			methods.add("sendTouyText");
			// methods.add("openAll");
		}

		return methods;
	}

	/**
	 * 关闭中控
	 * 
	 * @return
	 */
	public boolean closeCenterCtrl() {
		if (null != c) {
			Api a = new Api(c.getCenterHost(), c.getCenterPort(), new ApiListen() {
				@Override
				public void response(byte[] response) {
				}

				@Override
				public void connected() {
				}

				@Override
				public void closed() {
				}
			});
			if (null != a && a.init() && a.cmd(Defined.CMD_REQ_CLOSE_CLOUD)) {
				a.close();
				a = null;
				MyLog.debug("中控关闭成功 IP:" + c.getCenterHost() + " PORT" + c.getCenterPort());
				return true;
			} else {
				// 保存中控指令发送失败
				RunErrorFactory.saveCloseRunError(Constans.DEVICE_TYPE[1], c);
			}
		}
		MyLog.debug("关闭中控失败");
		return false;
	}

	public boolean sendTouyText(String sendText) throws UnsupportedEncodingException {
		if (null != c) {
			System.out.println(System.currentTimeMillis() + "-----");
			Api a = new Api(c.getCenterHost(), c.getCenterPort(), new ApiListen() {
				@Override
				public void response(byte[] response) {
				}

				@Override
				public void connected() {
				}

				@Override
				public void closed() {
				}
			});
			System.out.println(System.currentTimeMillis() + "-----");
			if (null != a && a.init() && a.cmd(new String(new String(
					Defined.CMD_REQ_CLOUD_TEXT.replace("REPLACE", sendText).getBytes("ISO8859-1"), "UTF-8")))) {
				a.close();
				a = null;
				return true;
			} else {
				// 记录中控发送码值指令失败
				RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[1], c);
			}
		}
		return false;
	}

	/* =======空调相关操作============== */
	/** 开启空调指令 */
	public boolean openAirConditioner() {
		return send(Defined.CMD_REQ_OPENORCLOSE_AIRCONDITIONER.getBytes());
	}

	/** 空调红外开机并设置16度高风强制冷模式 */
	public boolean setAirConditioner16Degrees() {
		if (send(Defined.CMD_REQ_SET16_AIRCONDITIONER.getBytes())) {
			// 开启空调
			try {

//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
//				RunInfoFactory.openDevice(runInfo);
//				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		// 保存指令发送失败
		RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[3], c);
		return false;
	};

	/** 空调红外开机并设置23度中风制冷模式 */
	public boolean setAirConditioner23Degrees() {
		if (send(Defined.CMD_REQ_SET23_AIRCONDITIONER.getBytes())) {
			// 开启空调
			try {
//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
//				RunInfoFactory.openDevice(runInfo);
//				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[3], c);
		// 保存指令发送失败
		return false;
	};

	/** 空调红外开机并设置26度自动风制冷模式 */
	public boolean setAirConditioner26Degrees() {
		if (send(Defined.CMD_REQ_SET26_AIRCONDITIONER.getBytes())) {
			// 开启空调
			try {
//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
//				RunInfoFactory.openDevice(runInfo);
//				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[3], c);
		// 保存指令发送失败
		return false;
	};

	/** 关闭空调指令 */
	public boolean closeAirConditioner() {
		if (send(Defined.CMD_REQ_OPENORCLOSE_AIRCONDITIONER.getBytes())) {
			// 修改数据，空调已关闭
			try {
//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[3]);
//				RunInfoFactory.closeDevice(runInfo);
//				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		RunErrorFactory.saveCloseRunError(Constans.DEVICE_TYPE[3], c);
		// 保存指令发送失败
		return false;
	}

	/* =======空调电闸相关操作============== */
	/** 开启空调电闸指令 */
	public boolean openAirSwitch() {
		return send(Defined.CMD_REQ_OPEN_AIRSWICH.getBytes());
	}

	/** 关闭空调电闸指令 */
	public boolean closeAirSwitch() {
		if (send(Defined.CMD_REQ_CLOSE_AIRSWICH.getBytes())) {
			try {
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		RunErrorFactory.saveCloseRunError(Constans.DEVICE_TYPE[3], c);
		// 保存指令发送失败
		return false;
	}

	/* =======其他电闸相关操作============== */
	/** 开启其他电闸指令 */
	public boolean openOtherSwitch() {
		// 表示开启了中控
		if (send(Defined.CMD_REQ_OPEN_OTHERSWICH.getBytes())) {
			try {
				RunInfo runInfo = new RunInfo();
				runInfo.setClassRoomId(Long.parseLong(c.getId()));
				runInfo.setDeviceType(Constans.DEVICE_TYPE[1]);
				RunInfoFactory.openDevice(runInfo);
				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
//			return true;
		}
		RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[1], c);
		// 保存指令发送失败
		
		return false;
	}

	/** 关闭其他电闸指令 */
	public boolean closeOtherSwitch() {
		// 表示关闭了中控
		if (send(Defined.CMD_REQ_CLOSE_OTHERSWICH.getBytes())) {
			try {
				// 关闭其他电闸指令发送成功后，关闭中控，并设置为断网状态
				closeCenter();
				RunInfo runInfo = new RunInfo();
				runInfo.setClassRoomId(Long.parseLong(c.getId()));
				runInfo.setDeviceType(Constans.DEVICE_TYPE[1]);
				RunInfoFactory.closeDevice(runInfo);
				runInfo = null;
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return true;
			}
		}
		RunErrorFactory.saveCloseRunError(Constans.DEVICE_TYPE[1], c);
		return false;
	}

	/** 开启投影仪指令 */
	public boolean openTouy() {
		if (send(Defined.CMD_REQ_OPEN_TOUY.getBytes())) {
//			try {
//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[2]);
//				RunInfoFactory.openDevice(runInfo);
//				runInfo = null;
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return true;
//			}
			return true;
		}
		RunErrorFactory.saveOpenRunError(Constans.DEVICE_TYPE[2], c);
		// 保存指令发送失败
		return false;
	}

	/** 关闭投影仪指令 */
	public boolean closeTouy() {
		if (send(Defined.CMD_REQ_CLOSE_TOUY.getBytes())) {
//			try {
//				RunInfo runInfo = new RunInfo();
//				runInfo.setClassRoomId(Long.parseLong(c.getId()));
//				runInfo.setDeviceType(Constans.DEVICE_TYPE[2]);
//				RunInfoFactory.closeDevice(runInfo);
//				runInfo = null;
//				return true;
//			} catch (Exception e) {
//				e.printStackTrace();
//				return true;
//			}
			return true;
		}
		// 保存指令发送失败
		RunErrorFactory.saveCloseRunError(Constans.DEVICE_TYPE[2], c);
		return false;
	}

	/** 开启监控指令 */
	public boolean openMonitor() {
		return false;
	}// 没有文档

	/** 关闭监控指令 */
	public boolean closeMonitor() {
		return false;
	}// 没有文档

	/** 开启云终端 */
	public boolean openCloudTerminal() {
		return false;
	}// 没有文档

	/** 关闭云终端 */
	public boolean closeCloudTerminal() {
		return send(Defined.CMD_REQ_CLOSE_CLOUD.getBytes());
	}

	/** 电箱蜂鸣指令 */
	public boolean electricBuzzer() {
		return send(Defined.CMD_REQ_ELECTRICBUZZER.getBytes());
	}

	/** 一键开启 (不含空调) */
	public boolean batOpenNoAir() {
		if (!network_status)
			return false;
		MyLog.debug("一键开启（不含空调） 开始");
		sleep(500);
		openOtherSwitch();// 开启其他电闸
		MyLog.debug("一键开启（不含空调） 休眠 1秒");
		sleep(1000);
		openAirSwitch();// 开启空调电闸
		sleep(500);
		MyLog.debug("一键开启（不含空调） 结束");

		return true;
		/*MyLog.debug("一键开启（含空调） 开始");
		sleep(500);
		openAirSwitch();// 开启空调电闸
		MyLog.debug("一键开启（含空调） 休眠 1秒");
		sleep(1000);
		openOtherSwitch();// 开启其他电闸
		MyLog.debug("一键开启（含空调） 休眠 4秒");
		sleep(4000);
		openAirConditioner();// 开启空调
		sleep(500);
		setAirConditioner26Degrees();// 开启空调，并设为26度//    这是含空调的
		sleep(500);
		MyLog.debug("一键开启（含空调） 结束");
		return true;*/
	}

	/** 一键关闭(不含空调) */
	public boolean batCloseNoAir() {
		if (!network_status)
			return false;
		MyLog.debug("一键关闭（不含空调） 开始");
		sleep(500);
		electricBuzzer();// 蜂鸣
		MyLog.debug("一键关闭（不含空调） 休眠 75秒");
		sleep(75000);
		closeTouy();// 关闭投影仪
		MyLog.debug("一键关闭（不含空调） 休眠 75秒");
		sleep(2000);
		closeCenterCtrl(); // closeCloudTerminal();//关闭云终端
		MyLog.debug("一键关闭（不含空调） 休眠 180秒");
		sleep(180000);
		closeOtherSwitch();// 关闭其他电闸
		MyLog.debug("一键关闭（不含空调） 休眠 1秒");
		sleep(2000);
		closeAirSwitch();// 关闭空调电闸
		sleep(500);
		MyLog.debug("一键关闭（不含空调） 结束");

		return true;
	}

	/** 一键开启 (含空调) */
	public boolean batOpenHasAir() {
		if (!network_status)
			return false;
		MyLog.debug("一键开启（含空调） 开始");
		sleep(500);
		openAirSwitch();// 开启空调电闸
		MyLog.debug("一键开启（含空调） 休眠 1秒");
		sleep(1000);
		openOtherSwitch();// 开启其他电闸
		MyLog.debug("一键开启（含空调） 休眠 4秒");
		/*sleep(4000);
		openAirConditioner();// 开启空调
		sleep(500);
		setAirConditioner26Degrees();// 开启空调，并设为26度//    这是含空调的
*/		sleep(500);
		MyLog.debug("一键开启（含空调） 结束");
		return true;
	}

	/** 一键关闭(含空调) */
	public boolean batCloseHasAir() {
		if (!network_status)
			return false;
		MyLog.debug("一键关闭（含空调） 开始");
		sleep(500);
		electricBuzzer();// 蜂鸣
		MyLog.debug("一键关闭（含空调） 休眠 75秒");
		sleep(75000);
		closeTouy();// 关闭投影仪
		MyLog.debug("一键关闭（含空调） 休眠 1秒");
		sleep(1000);
		closeCenterCtrl();// closeCloudTerminal();//关闭云终端
		MyLog.debug("一键关闭（含空调） 休眠 2秒");
		sleep(2000);
		closeAirConditioner();// 关闭空调
		MyLog.debug("一键关闭（含空调） 休眠 180秒");
		sleep(180000);
		closeAirSwitch();// 关闭空调电闸
		MyLog.debug("一键关闭（含空调） 休眠 1秒");
		sleep(1000);
		closeOtherSwitch();// 关闭其他电闸
		sleep(500);
		MyLog.debug("一键关闭（含空调） 结束");
		return true;
	}

	/** 初始化连接 */
	public void init() {
		if(api != null){
			try {
//				api.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else{
			
			api = new Api(host, port, new DevListen());
		}
		try {
//			api = new Api(host, port, new DevListen());
			boolean init = api.init();
			if (init) {
				
				syncFailTimes = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 初始化连接云中控 wjd0823 */
	public void initCenter() {
		if (centerApi != null) {
			try {
				centerApi.close();
				centerApi = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		centerApi = new Api(c.getCenterHost(), c.getCenterPort(), new DevListen() {

			@Override
			public void response(byte[] response) {

				MyLog.debug("云中控-网络连接=====:" + response);
			}

			@Override
			public void connected() {
				MyLog.debug("云中控-网络连接成功 IP:" + c.getCenterHost() + " PORT" + c.getCenterPort());
				center_network_status = true;
				if (null != devStatus) {
					MyLog.debug("修改云中控状态 设为off");
					devStatus.setCloud_terminal("off");

				}
				// syncStatus();//网络恢复后，同步状态

			}

			@Override
			public void closed() {
				MyLog.debug("中控---网络断开 IP:" + c.getCenterHost() + " PORT" + c.getCenterPort());
				// statysSync = false;
				center_network_status = false;
				if (null != devStatus && !center_network_status) {
					devStatus.setCloud_terminal("offline");
				}
			}

		});
		boolean centerInit = centerApi.centerInit();
		if (centerInit) {
			syncFailTimes = 0;
		}
	}
	private void closeCenter(){
		center_network_status = false;
		if (null != devStatus && !center_network_status) {
			devStatus.setCloud_terminal("offline");
		}
		try {
			centerApi.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 初始化连接监控 wjd0823 */
	public void initMonitor() {
		if (monitorApi != null) {
			try {
				monitorApi.close();
				monitorApi = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		monitorApi = new Api(c.getTouyUrl(), 554, new DevListen() {

			@Override
			public void response(byte[] response) {
				MyLog.debug("监控网络连接=====:" + response);
			}

			@Override
			public void connected() {

				MyLog.debug("监控-网络连接成功 IP:" + c.getTouyUrl() + " PORT" + 554);
				monitor_network_status = true;
				if (null != devStatus) {
					// MyLog.debug("修改状态 设为off");
					devStatus.setMonitor("off");

				}
				// 网络恢复后，同步状态
				// new ClassesModel(c).new DevListen().syncStatus();

			}

			@Override
			public void closed() {
				MyLog.debug("监控---网络断开 IP:" + c.getTouyUrl() + " PORT" + 554);
				// statysSync = false;
				monitor_network_status = false;
				if (null != devStatus && !monitor_network_status) {
					devStatus.setMonitor("offline");
				}
			}

		});
		boolean monitorInit = monitorApi.monitorInit();
		if (monitorInit) {
			syncFailTimes = 0;
		}
	}

	public void initMonitorApi(){
		
	}
	/** 发送指令 */
	public boolean send(byte[] cmd) {
		System.out.println("cmd-byte[]:" + MyFunc.ByteArrToHex(cmd));
		String logStr = new String(cmd);
		String hexString = Functions.byteToHexString(cmd);
		if (null == api)
			this.init();
		// TODO
		if (hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE)
				|| hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE)
				||hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2)
				|| hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2)) {
			logStr = hexString;
		}
		if (null != api) {
			if (!statysSync) {
				// TODO
				if (!hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE)
						&& !hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE)
						&& !hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2)
						&& !hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2)) {
					System.out.println("状态尚未同步：ip:"+host+",port:"+port +",cmd:"+ hexString);
					return false;
				}
			}
			if (api.cmd(cmd)) {
				MyLog.debug("发送请求【成功】1ip:"+host+",port:"+port+",cmd:" + Defined.getNote(logStr));
				return true;
			}
		}

		MyLog.debug("发送请求【失败】1ip:"+host+",port:"+port+",cmd:" + Defined.getNote(logStr));
		return false;
	}

	/** 发送字符串指令 */
	public boolean sendStr(String cmd) {
		String logStr = new String(cmd);
		String hexString = cmd;
		if (null == api)
			this.init();
		// TODO 
		if (hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE)
				|| hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE)
				||hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2)
				|| hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2)) {
			logStr = hexString;
		}
		if (null != api) {
			if (!statysSync) {
				// TODO
				if (!hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE)
						&& !hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE)
						&& !hexString.equals(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2)
						&& !hexString.equals(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2)) {
					System.out.println("状态尚未同步" + hexString);
					return false;
				}
			}
			if (api.cmdStr(cmd)) {
				MyLog.debug("发送请求【成功】2" + Defined.getNote(logStr));
				return true;
			}
		}

		MyLog.debug("发送请求【失败】2" + Defined.getNote(logStr));
		return false;
	}

	/** 发送指令 */
	public boolean send(String cmd) {
		return send(cmd.getBytes());
	}

	private void sleep(long tm) {
		try {
			Thread.sleep(tm);
		} catch (InterruptedException e) {
		}
	}

	/** 自动逻辑控制 */
	private void autoControl() {
		if(time == null){
			time = new Timer(true);
		}
		
		time.schedule(new TimerTask() {
			@Override
			public void run() {
				Calendar c = Calendar.getInstance();
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int min = c.get(Calendar.MINUTE);
				String cmd = Defined.CMD_REQ_FREE_CMD;
				if ((hour == 7 && min >= 30) || (hour > 7 && hour < 22))
					cmd = Defined.CMD_REQ_BUSY_CMD;
				send(cmd);
				System.gc();
			}
		}, 0, 30000);// 单位为ms
	}

	/** 自动关闭所有设备 */
	private void autoCloseAllDev() {
		Calendar c = Calendar.getInstance();
		Date date = new Date();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		if ((hour == 22 && min >= 30) || hour > 22 || hour < 6) {
			if (!devStatus.isAllClosed()) {
				last_close_tm = System.currentTimeMillis();
				new Thread() {
					public void run() {
						if (devStatus.getAir_conditioner().equals("on")) {
							batCloseHasAir();
						} else {
							batCloseHasAir();
						}
					}
				}.start();
			}
		}

	}

	public boolean syncCmd() {
		new Thread() {
			public void run() {
				if (c!= null && c.getVersionNum().equals("1")) {
					
					// TODO
					if (send(Functions.hexString2Byte(Defined.CMD_REQ_STA_SYNC_HEX2BYTE))) {
						statysSync = true;
						MyLog.debug("网络状态恢复,状态同步指令1:" + Defined.CMD_REQ_STA_SYNC_HEX2BYTE + " IP:" + host + " PORT" + port);
						MyLog.debug(
								"网络状态恢复,状态同步指令2:" + Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE + " IP:" + host + " PORT" + port);
					}
				} else if (c!= null && c.getVersionNum().equals("2")) {
					
					if (send(Functions.hexString2Byte(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2))
							&& send(Functions.hexString2Byte(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2))) {
						statysSync = true;
						MyLog.debug("网络状态恢复,状态同步指令2_1:" + Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2 + " IP:" + host + " PORT" + port);
						MyLog.debug(
								"网络状态恢复,状态同步指令2_2:" + Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2 + " IP:" + host + " PORT" + port);
					}
				} else{
					MyLog.debug("当前教室版本号错误:version=" + ((c!= null && c.getVersionNum()!=null)?c.getVersionNum():0) + ", IP:" + host + " PORT" + port);
				}
			}
		}.start();
		return true;
	}

	/** 侦听socket状态以及返回值 */
	private class DevListen extends ApiListen {

		/**
		 *  TODO
		 * 服务端响应请求
		 */
		@Override
		public void response(byte[] response) {
			MyLog.debug("response success,devStatus!=null-"+(devStatus!=null)+",network_status="+String.valueOf(network_status));
			if (null != response && null != devStatus && network_status) {
				String decString = Functions.byteToHexString(response);
				String[] dsf = null;
				String prix = "";
				if(decString.indexOf("2200") != -1){
					prix = "2200";
					dsf = decString.split("2200");
				} else if(decString.indexOf("2201") != -1){
					prix = "2201";
					dsf = decString.split("2201");
				} else if(decString.indexOf("0D0A") != -1){
					prix = "0D0A";
					dsf = decString.split("0D0A");
				} else{
					dsf = new String[1];
					dsf[0] = decString;
				}
				
				first:for(String str : dsf){
					if(str == "" || str.length() <= 8){
						continue first;
					}
					if(prix.equals("0D0A")){
						str = prix + str + prix;
					} else{
						if(str.length() > 12){
							str = str.substring(0, 12);
						}
						str = prix + str;
					}
					
					devStatus.changeStatus(Functions.hexString2Byte(str),c);
					MyLog.debug("服务端响应指令(16进制表示)_prix_1:" + Defined.getNote(str) + " IP:" + host
							+ " PORT" + port);
				}
					
			} else {
				if(response.length >= 6){
					devStatus.changeStatus(response,c);
					MyLog.debug("服务端响应指令(16进制表示):" + Defined.getNote(Functions.byteToHexString(response)) + " IP:" + host
							+ " PORT" + port);
				}
			}
		}
		/**
		 * 网络恢复后，同步状态
		 */
		private void syncStatus() {
			new Thread() {
				public void run() {
					// TODO
					
					if (c!= null && c.getVersionNum().equals("1")) {
						
						// TODO
						if (send(Functions.hexString2Byte(Defined.CMD_REQ_STA_SYNC_HEX2BYTE))
								&& send(Functions.hexString2Byte(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE))) {
							statysSync = true;
							MyLog.debug(
									"网络状态恢复,状态同步指令1:" + Defined.CMD_REQ_STA_SYNC_HEX2BYTE + " IP:" + host + " PORT" + port);
							MyLog.debug("网络状态恢复,状态同步指令2:" + Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE + " IP:" + host + " PORT"
									+ port);
						}
					} else if (c!= null && c.getVersionNum().equals("2")) {
						
						if (send(Functions.hexString2Byte(Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2))
								&& send(Functions.hexString2Byte(Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2))) {
							statysSync = true;
							MyLog.debug(
									"网络状态恢复,状态同步指令2_1:" + Defined.CMD_REQ_STA_SYNC_HEX2BYTE_2 + " IP:" + host + " PORT" + port);
							MyLog.debug("网络状态恢复,状态同步指令2_2:" + Defined.CMD_REQ_TOUYSTA_SYNC_HEX2BYTE_2 + " IP:" + host + " PORT"
									+ port);
						}
					}

				}
			}.start();
		}

		/**
		 * 网络恢复侦听
		 */
		@Override
		public void connected() {
			MyLog.debug("电箱网络连接成功 IP:" + host + " PORT" + port);
			network_status = true;
			if (null != devStatus) {
			}
			syncStatus();// 网络恢复后，同步状态
		}

		/**
		 * 网络关闭侦听
		 */
		@Override
		public void closed() {
			MyLog.debug("网络断开 IP:" + host + " PORT" + port);
			statysSync = false;
			network_status = false;
			if (null != devStatus && !network_status) {
				devStatus.setAir_conditioner("offline");
				devStatus.setAir_switch("offline");
				devStatus.setOther_switch("offline");
				devStatus.setTouy("offline");
			}

		}
	}

	public void autoConnect(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
//					if(c.getServerHost().equals("192.168.1.56")){
//						System.out.println("sdf");
//					}
//					if(c.getCenterHost().equals("192.168.1.205")){
//						System.err.println("192.168.1.205");
//					}
					if (!network_status|| (devStatus!=null&&( devStatus.getAir_switch().equals("offline")
							||devStatus.getOther_switch().equals("offline")))){
						if(api != null ){
							
							try {
								api.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						network_status = api.init();
					}
					/** wjd0823 */
					if(!monitor_network_status || (devStatus!= null &&(devStatus.getMonitor().equals("offline")))){
						if(monitorApi != null ){
							
							try {
								monitorApi.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						monitor_network_status = monitorApi.monitorInit();
					}
					/** wjd0823 */
					if(!center_network_status|| (devStatus!= null &&(devStatus.getCloud_terminal().equals("offline")))){
						if(centerApi != null ){
							
							try {
								centerApi.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						center_network_status = centerApi.centerInit();
					}
						syncFailTimes +=1; 
					if (System.currentTimeMillis() - last_close_tm > 60000) {
						// 这里可能会造成中控关机好
						autoCloseAllDev();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
	}
	/** */
	/** socket自动重连 */
	private class AutoConnect extends Thread {
		public void run() {
			while (true) {
				try {
					Thread.sleep(60000);
					if (!network_status)
						network_status = api.init();
					/** wjd0823 */
					if(!monitor_network_status)
					monitor_network_status = monitorApi.monitorInit();
					/** wjd0823 */
					if(!center_network_status)
					center_network_status = centerApi.centerInit();
					syncFailTimes +=1; 
				
					if (System.currentTimeMillis() - last_close_tm > 60000) {
						autoCloseAllDev();
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					e.printStackTrace();
				}
			}
		}
	}
//	public static void main(String[] args){
//		String s = "22017000000001940D0A";
//		s = s.substring(0, 16);
//		System.out.println(s);
//	}
/////////////////////////////////////////////

	public boolean isCenter_network_status() {
		return center_network_status;
	}

	public void setCenter_network_status(boolean center_network_status) {
		this.center_network_status = center_network_status;
	}

	public boolean isMonitor_network_status() {
		return monitor_network_status;
	}

	public void setMonitor_network_status(boolean monitor_network_status) {
		this.monitor_network_status = monitor_network_status;
	}

	public boolean isNetwork_status() {
		return network_status;
	}

	public void setNetwork_status(boolean network_status) {
		this.network_status = network_status;
	}

	public boolean isStatysSync() {
		return statysSync;
	}

	public void setStatysSync(boolean statysSync) {
		this.statysSync = statysSync;
	}

	public Integer getSyncFailTimes() {
		return syncFailTimes;
	}

	public void setSyncFailTimes(Integer syncFailTimes) {
		this.syncFailTimes = syncFailTimes;
	}

}





  
