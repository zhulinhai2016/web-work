package com.iot.service;

import java.util.List;
import java.util.Map;

import com.iot.model.HomeCountData;
import com.iot.model.RunCountData;
import com.iot.model.RunInfo;

/**
 * 
 * @author LinHaiZhu
 *
 * 创建于：2018年7月6日-下午3:59:20
 */
public interface RunInfoService {
	
	/** 保存运行参数 */
	public int saveRunInfo(RunInfo info);
	/** 修改参数 */
	public int updateRunInfo(RunInfo info);
	/** 查询可以开启/关闭的设备 */
	public List<RunInfo> findToOpenOrClose(RunInfo info);
	/** 查询可以关闭的设备 */
	public List<RunInfo> findToClose(RunInfo info);
	public List<RunCountData> runDataCount(RunInfo info);
	public List<RunCountData> powerDataCount(RunInfo info);
	public List<RunInfo> findNewestData(RunInfo info);
	public List<HomeCountData> getUseOrNotUseData(RunInfo info);
	public Map<String, Object> getClassRoomRankings(RunInfo info,Integer showRowsNum);
	public Map<String, Object> getDeviceRankings(RunInfo info);
}
