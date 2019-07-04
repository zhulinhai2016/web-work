package ecapi.api;

import java.util.List;

import com.iot.model.RunError;
import com.iot.service.impl.RunErrorServiceImpl;

import ecapi.model.ClassesInfoModel;
import util.Constans;
import util.SpringContextUtil;

/**
 * 
 * @author LinHaiZhu
 *
 * 创建于：2018年7月3日-下午4:03:41
 */
public class RunErrorFactory {

	private static RunErrorServiceImpl runErrorServiceImpl = (RunErrorServiceImpl) SpringContextUtil
			.getBean("runErrorServiceImpl");

	/**
	 * 保存开机指令失败
	 * 2018年7月11日 created by z
	 * @param runError
	 * @return
	 * return_type int
	 */
	public static int saveOpenRunError(String deviceType,ClassesInfoModel c) {
		try {
			RunError runError = new RunError();
			runError.setClassRoomId(Long.parseLong(c.getId()));
			runError.setDeviceType(deviceType);
			runError.setErrorType(Constans.ERROR_TYPE[1]);
			return runErrorServiceImpl.saveRunError(runError);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	/**
	 * 保存关机指令失败
	 * 2018年7月11日 created by z
	 * @param runError
	 * @return
	 * return_type int
	 */
	public static int saveCloseRunError(String deviceType,ClassesInfoModel c) {
		try {
			RunError runError = new RunError();
			runError.setClassRoomId(Long.parseLong(c.getId()));
			runError.setDeviceType(deviceType);
			runError.setErrorType(Constans.ERROR_TYPE[2]);
			return runErrorServiceImpl.saveRunError(runError);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	public static List<RunError> findByRunError(RunError runError){
		return runErrorServiceImpl.findByRunError(runError);
	}
	
	public static int updateByRunError(RunError runError){
		return runErrorServiceImpl.updateByRunError(runError);
	}
	public static int saveRunError(RunError runError){
		return runErrorServiceImpl.saveRunError(runError);
	}
	public static int deleteByRunError(RunError runError){
		return runErrorServiceImpl.deleteByRunError(runError);
	}
}
