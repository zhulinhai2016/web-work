package job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iot.model.RunError;
import com.iot.model.RunInfo;
import com.iot.service.RunErrorService;

import ecapi.api.ClassesFactory;
import ecapi.api.RunInfoFactory;
import ecapi.model.ClassesInfoModel;
import ecapi.model.ClassesModel;
import util.Constans;
import util.PingUtils;

/**
 * 
 * 定时发送ping指令的job
 * @author LinHaiZhu
 *
 * 创建于：2018年7月11日-上午10:05:00
 */
public class TimingPingJob {

	@Autowired
	private RunErrorService runErrorService;
	
	public void execute() {
		// 获取所有的教室
		List<ClassesInfoModel> cls = ClassesFactory.getClassesByBuildAndFloor("all","all",null);
		if (cls != null && !cls.isEmpty()) {
			RunError runError;
			RunInfo runInfo = null;
			for (ClassesInfoModel classModel : cls) {
				try {
					runInfo = new RunInfo();
					
					runError = new RunError();
					runError.setClassRoomId(Long.parseLong(classModel.getId()));
					runError.setErrorType(Constans.ERROR_TYPE[5]);
					
					runInfo.setClassRoomId(Long.parseLong(classModel.getId()));
					runInfo.setDeviceType(Constans.DEVICE_TYPE[4]);
					
					// 电箱IP ping通后记录恢复网络的时间
					if (PingUtils.ping(classModel.getServerHost(), 3, 5000)) {
						runError.setDeviceType(Constans.DEVICE_TYPE[4]);
						runError.setRecoveryTime(new Date());
						runErrorService.updateByRunError2(runError);
					}
//					ClassesModel  cm = ClassesFactory.getClassesObj(classModel.getId());
//					if (cm != null && cm.getDevStatus().getOther_switch().equals("on")) {
						
						
						// 中控IP ping通后记录恢复网络的时间
						if (PingUtils.ping(classModel.getCenterHost(), 3, 5000)) {
							runError.setDeviceType(Constans.DEVICE_TYPE[1]);
							runError.setRecoveryTime(new Date());
							runErrorService.updateByRunError2(runError);
						}
//					}
						
					/*
					// 电箱IP
					if (!PingUtils.ping(classModel.getServerHost(), 3, 2000)) {
						runError.setDeviceType(Constans.DEVICE_TYPE[4]);
						 
						// 查询是否已经记录了数据
						List<RunError> list = runErrorService.findByRunError(runError);
						long currentTime = System.currentTimeMillis();
						if (list != null && !list.isEmpty()) {
							first:for (RunError run : list) {
								if ((currentTime-run.getCreateDate().getTime())> 31*60*1000) {
									// 当时间超过了31分钟则为断网,并修改数据为断网及发送短信通知
									runErrorService.updateByRunError(runError);
									RunInfoFactory.closeDevice(runInfo);
									break first;
									
								}
							}
						} else {
							//先记录中间状态
							runErrorService.saveRunError(runError);
						}
					} else {
						runError.setDeviceType(Constans.DEVICE_TYPE[4]);
						runErrorService.deleteByRunError(runError);
						// 电箱ping 通了就表示启用了
						RunInfoFactory.openDevice(runInfo);
					}
					// 中控ip
					PingUtils.ping(classModel.getCenterHost(), 5, 5000);
					ClassesModel  cm = ClassesFactory.getClassesObj(classModel.getId());
					if (cm != null && cm.getDevStatus().getOther_switch().equals("on")) {
						// 设备打开的时候需要检查是否能ping通
						if (!PingUtils.ping(classModel.getCenterHost(), 5, 5000)) {
							
							runError.setDeviceType(Constans.DEVICE_TYPE[1]);
							// 查询是否已经记录了数据
							List<RunError> list = runErrorService.findByRunError(runError);
							long currentTime = System.currentTimeMillis();
							if (list != null && !list.isEmpty()) {
								first:for (RunError run : list) {
									if ((currentTime-run.getCreateDate().getTime())> 31*60*1000) {
										// 当时间超过了31分钟则为断网,并修改数据为断网及发送短信通知
										runErrorService.updateByRunError(runError);
										break first;
										
									}
								}
							} else {
								//先记录中间状态
								runErrorService.saveRunError(runError);
							}
						} else {
							runError.setDeviceType(Constans.DEVICE_TYPE[1]);
							runErrorService.deleteByRunError(runError);
						}
						
					}*/
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}
}
