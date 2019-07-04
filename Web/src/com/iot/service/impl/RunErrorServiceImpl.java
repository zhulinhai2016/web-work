package com.iot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.dao.RunErrorMapper;
import com.iot.model.RunError;
import com.iot.model.RunErrorCountData;
import com.iot.model.RunErrorPage;
import com.iot.service.RunErrorService;

import page.PageBean;
import util.DateUtil;

@Service
public class RunErrorServiceImpl implements RunErrorService {
	@Autowired
	private RunErrorMapper runErrorMapper;
	
	@Override
	public int saveRunError(RunError runError) {
		// 保存年份等信息
		runError.setCreateDate(new Date());
		runError.setCreateYear(DateUtil.getYear());
		runError.setCreateMonth(DateUtil.getMonth());
		return runErrorMapper.saveRunError(runError);
	}

	@Override
	public List<RunErrorCountData> getErrorDataByInfo(RunError runError) {
		if (runError.getCreateYear() == null || runError.getCreateYear()=="") {
			// 默认查询当前年份的数据
			runError.setCreateYear(DateUtil.getYear());
		}
		return runErrorMapper.getErrorDataByInfo(runError);
	}

	@Override
	public List<RunError> findByRunError(RunError runError) {
		return runErrorMapper.findByRunError(runError);
	}

	@Override
	public int updateByRunError(RunError runError) {
		return runErrorMapper.updateByRunError(runError);
	}

	@Override
	public int deleteByRunError(RunError runError) {
		return runErrorMapper.deleteByRunError(runError);
	}

	@Override
	public int updateByRunError2(RunError runError) {
		return runErrorMapper.updateByRunError2(runError);
	}

	@Override
	public PageBean findPage(RunError runError) {
		if (runError.getDeviceName() == null || runError.getDeviceName() == "") {
			runError.setDeviceName(null);
		} else {
			runError.setDeviceName("%"+runError.getDeviceName()+"%");
		}
		
		int count = runErrorMapper.selectTotal(runError);
		List<RunErrorPage> list = runErrorMapper.findPage(runError);
		PageBean page = new PageBean(runError.getOffset(), runError.getLimit(), count, list);
		return page;
	}

}
