package com.iot.dao;

import java.util.List;

import com.iot.model.HomeCountData;
import com.iot.model.RunCountData;
import com.iot.model.RunInfo;

/**
 * 运行信息接口
 * @author LinHaiZhu
 *
 * 创建于：2018年7月6日-下午3:58:24
 */
public interface RunInfoMapper {
	
	public int saveRunInfo(RunInfo info);
	public int updateById(RunInfo info);
	public List<RunInfo> findToOpenOrClose(RunInfo info);
	public List<RunCountData> runDataCount(RunInfo info);
	public List<RunCountData> powerDataCount(RunInfo info);
	public List<RunInfo> findNewestData(RunInfo info);
	public List<HomeCountData> getUseOrNotUseData(RunInfo info);
	public List<RunCountData> getClassRoomRankings(RunInfo info);
	public List<RunCountData> getDeviceRankings(RunInfo info);
}
