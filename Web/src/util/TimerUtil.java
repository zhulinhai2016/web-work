package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.collections.CollectionUtils;

import com.iot.model.RunError;
import com.iot.model.RunInfo;

import ecapi.api.ClassesFactory;
import ecapi.api.RunErrorFactory;
import ecapi.api.RunInfoFactory;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;
import ecapi.model.KebiaoModel;

/**
 * 
 * @author wjd
 *
 */
public class TimerUtil {
	// 第一次同步、第二次同步
	public static String FIRST = "first";
	public static String SECOND = "second";

	/**
	 * 获取指定时间对应的毫秒数
	 * 
	 * @param time
	 *            "HH:mm:ss"
	 * @return
	 */
	public static long getTimeMillis(String time) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
			return curDate.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 调用设备开关程序
	 * 每次调用创建一个线程，任务执行完后System.gc
	 * 
	 * @param runDate
	 * @param open  true  打开 false 关闭
	 */
	public static void ScheduleControlDevice(final String runDate,
			final KebiaoModel kebiao, final boolean open) {
		TimerTask task = new TimerTask() {
			public void run() {
				MyLog.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())
						+ "正在控制开关，"	+ runDate	+ "--教室号--"+ kebiao.getJsh() + "===开关状态==" + open);
				  ArrayList<ClassesInfoModel> classesByClassId = ClassesFactory.getClassesByClassId(kebiao.getJsid());
				  if(CollectionUtils.isNotEmpty(classesByClassId)){
					 ClassesModel classModel = ClassesFactory.getClassesObj(classesByClassId.get(0));
					if(classModel!=null){
						if(open){
							
							classModel.batOpenNoAir();
							
							try {
								RunError runError = new RunError();
								RunInfo runInfo = new RunInfo();
								
								runError.setClassRoomId(Long.parseLong(classesByClassId.get(0).getId()));
								runError.setErrorType(Constans.ERROR_TYPE[3]);
								
								runInfo.setClassRoomId(Long.parseLong(classesByClassId.get(0).getId()));
								
								
								// ping 电箱的IP 如果不通则记录数据
								// 电箱IP
								if (!PingUtils.ping(classesByClassId.get(0).getServerHost(), 3, 5000)) {
									runError.setDeviceType(Constans.DEVICE_TYPE[4]);
									runInfo.setDeviceType(Constans.DEVICE_TYPE[4]);
									// 记录断网数据，并发送短信通知
									RunErrorFactory.saveRunError(runError);
									RunInfoFactory.closeDevice(runInfo);
								} else {
									runInfo.setDeviceType(Constans.DEVICE_TYPE[4]);
									// 电箱ping 通了就表示启用了
									RunInfoFactory.openDevice(runInfo);
								}
								// ping 中控 的IP 如果不通则记录数据
								// 中控 IP
								if (!PingUtils.ping(classesByClassId.get(0).getCenterHost(), 3, 5000)) {
									runError.setDeviceType(Constans.DEVICE_TYPE[1]);
									runInfo.setDeviceType(Constans.DEVICE_TYPE[1]);
									// 记录断网数据，并发送短信通知
									RunErrorFactory.saveRunError(runError);
									RunInfoFactory.closeDevice(runInfo);
								} else {
									runInfo.setDeviceType(Constans.DEVICE_TYPE[1]);
									// 中控 ping 通了就表示启用了
									RunInfoFactory.openDevice(runInfo);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							//classModel.batOpenNoAir();
						}else{
							classModel.batCloseHasAir();
							// 关闭空调
							
							// 关闭中控及电箱
							RunInfo runInfo = new RunInfo();
							runInfo.setClassRoomId(Long.parseLong(classesByClassId.get(0).getId()));
							runInfo.setDeviceType(Constans.DEVICE_TYPE[4]);
							RunInfoFactory.closeDevice(runInfo);
							runInfo.setDeviceType(Constans.DEVICE_TYPE[1]);
							RunInfoFactory.closeDevice(runInfo);
						}
					}
				  }
				//当前任务执行完后 ，建议GC进行垃圾回收
				System.gc();
		}
		};

	Timer timer = new Timer();
	Date runTime;try

	{
		runTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(runDate);
		long firstDelay = runTime.getTime() - System.currentTimeMillis();
		if (firstDelay >= 0) {// 如果执行时间还没到 才会执行
			// 在规定的毫秒后只执行一次
			timer.schedule(task, firstDelay);
		}
	} catch(

	ParseException e)

	{
		e.printStackTrace();
	}
}}
