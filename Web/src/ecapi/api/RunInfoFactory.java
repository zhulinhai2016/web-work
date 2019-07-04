package ecapi.api;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.iot.model.RunInfo;
import com.iot.service.impl.RunInfoServiceImpl;

import util.Constans;
import util.SpringContextUtil;

/**
 * 
 * @author LinHaiZhu
 *
 * 创建于：2018年7月3日-下午4:03:41
 */
public class RunInfoFactory {

	private static RunInfoServiceImpl runInfoServiceImpl = (RunInfoServiceImpl) SpringContextUtil
			.getBean("runInfoServiceImpl");

	/**
	 * 
	 * 2018年7月3日 created by z
	 * @return
	 * return_type RunInfo
	 */
	public static int saveInfo(RunInfo info) {
		return runInfoServiceImpl.saveRunInfo(info);
	}

	/**
	 * 
	 * 2018年7月3日 created by z
	 * @return
	 * return_type RunInfo
	 */
	public static RunInfo getInfoBySql() {

		return new RunInfo();
	}

	public static List<RunInfo> findToOpenOrClose(RunInfo runInfo) {
		return runInfoServiceImpl.findToOpenOrClose(runInfo);
	}

	/**
	 * 记录打开设备时间 2018年7月7日 created by z
	 * 
	 * @param runInfo
	 * @return return_type boolean
	 */
	public static boolean openDevice(RunInfo runInfo) {

		try {
			List<RunInfo> runList = findToOpenOrClose(runInfo);
			if (runList == null || runList.isEmpty()) {
				// 当前教室的设备类型没有开启，需要开启设备并记录数据
				// 需要记录数据
				runInfo.setIsOpened("1");
				runInfo.setOpenTime(new Date());
				runInfo.setCreateDate(new Date());
				runInfo.setUpdateDate(new Date());
				runInfoServiceImpl.saveRunInfo(runInfo);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 记录关闭设备时间 2018年7月7日 created by z
	 * 
	 * @param runInfo
	 * @return return_type boolean
	 */
	public static boolean closeDevice(RunInfo runInfo) {
		try {
			List<RunInfo> toClose = runInfoServiceImpl.findToClose(runInfo);
			for (RunInfo run : toClose) {
				runInfo = new RunInfo();
				runInfo.setRunInfoId(run.getRunInfoId());
				runInfo.setIsClosed("1");
				runInfo.setCloseTime(new Date());
				runInfo.setUpdateDate(new Date());
				runInfoServiceImpl.updateRunInfo(runInfo);
			}
			runInfo=null;
			toClose.clear();
			toClose = null;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
