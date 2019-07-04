package com.iot.service;

import java.util.List;

import com.iot.model.RunError;
import com.iot.model.RunErrorCountData;

import page.PageBean;

/**
 * 
 * @author LinHaiZhu
 *
 * 创建于：2018年7月11日-下午1:50:58
 */
public interface RunErrorService {
	public int saveRunError(RunError runError);
	
	public List<RunErrorCountData> getErrorDataByInfo(RunError runError);
	public List<RunError> findByRunError(RunError runError);
	public int updateByRunError(RunError runError);
	public int updateByRunError2(RunError runError);
	public int deleteByRunError(RunError runError);
	
	public PageBean findPage(RunError runError);
}
