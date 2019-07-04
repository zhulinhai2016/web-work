package com.iot.dao;

import java.util.List;

import com.iot.model.RunError;
import com.iot.model.RunErrorCountData;
import com.iot.model.RunErrorPage;

public interface RunErrorMapper {
	
	public int saveRunError(RunError runError);
	public List<RunErrorCountData> getErrorDataByInfo(RunError runError);
	public List<RunError> findByRunError(RunError runError);
	public int updateByRunError(RunError runError);
	public int updateByRunError2(RunError runError);
	public int deleteByRunError(RunError runError);
	public int selectTotal(RunError runError);
	public List<RunErrorPage> findPage(RunError runError);
}
