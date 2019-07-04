package com.iot.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.dao.RunInfoMapper;
import com.iot.model.HomeCountData;
import com.iot.model.RunCountData;
import com.iot.model.RunInfo;
import com.iot.service.RunInfoService;

/**
 * 
 * @author LinHaiZhu
 *
 *         创建于：2018年7月4日-下午3:59:38
 */
@Service
@Transactional
public class RunInfoServiceImpl implements RunInfoService {

	@Autowired
	private RunInfoMapper runInfoMapper;

	@Override
	public int saveRunInfo(RunInfo info) {
		return runInfoMapper.saveRunInfo(info);
	}

	@Override
	public int updateRunInfo(RunInfo info) {
		return runInfoMapper.updateById(info);
	}

	@Override
	public List<RunInfo> findToOpenOrClose(RunInfo info) {
		return runInfoMapper.findToOpenOrClose(info);
	}

	@Override
	public List<RunInfo> findToClose(RunInfo info) {
		return runInfoMapper.findToOpenOrClose(info);
	}

	@Override
	public List<RunCountData> runDataCount(RunInfo info) {
		return runInfoMapper.runDataCount(info);
	}

	@Override
	public List<RunCountData> powerDataCount(RunInfo info) {
		return runInfoMapper.powerDataCount(info);
	}

	@Override
	public List<RunInfo> findNewestData(RunInfo info) {
		return runInfoMapper.findNewestData(info);
	}

	@Override
	public List<HomeCountData> getUseOrNotUseData(RunInfo info) {
		return runInfoMapper.getUseOrNotUseData(info);
	}

	@Override
	public Map<String, Object> getClassRoomRankings(RunInfo info, Integer showRowsNum) {
		Random random = new Random();
		List<RunCountData> list = runInfoMapper.getClassRoomRankings(info);

		Map<String, Object> map = new HashMap<>();
		List<String> names = new ArrayList<>();
		List<Double> values = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			if (showRowsNum != null) {
				if (!showRowsNum.equals(-1)) {
					if (list.size() > showRowsNum) {
						List<RunCountData> newList = list.subList(0, showRowsNum);
						list = newList;
					}
				}

			}
			for (int i = 1; i < 2; i++) {
				Collections.shuffle(list);
			}
			for (RunCountData data : list) {
				if (data.getName() != null && data.getName() != "") {
					names.add(data.getName());
				} else {
					names.add("null-" + String.valueOf(random.nextInt(100)));
				}
				if (data.getValue() != null) {

					values.add(data.getValue());
				} else {
					values.add(0.00);
				}
			}
		}

		map.put("names", names);
		map.put("values", values);
		return map;
	}

	@Override
	public Map<String, Object> getDeviceRankings(RunInfo info) {
		List<RunCountData> list = runInfoMapper.getDeviceRankings(info);
		Map<String, Object> map = new HashMap<>();
		map.put("deviceRankings", list);
		return map;
	}
}
