package com.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.dao.FloorMapper;
import com.iot.model.Floor;
import com.iot.model.FloorExample;
import com.iot.service.FloorService;
@Service
@Transactional
public class FloorServiceImpl implements FloorService{
	@Autowired
	private FloorMapper floorMapper;
	@Override
	public long countByExample(FloorExample example) {
		
		return this.floorMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(FloorExample example) {
		
		return this.floorMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return this.floorMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Floor record) {
		
		return this.floorMapper.insert(record);
	}

	@Override
	public int insertSelective(Floor record) {
		
		return this.floorMapper.insertSelective(record);
	}

	@Override
	public List<Floor> selectByExample(FloorExample example) {
		
		return this.floorMapper.selectByExample(example);
	}

	@Override
	public Floor selectByPrimaryKey(Long id) {
		
		return this.floorMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Floor record, FloorExample example) {
		
		return this.floorMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Floor record, FloorExample example) {
		
		return this.floorMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Floor record) {
		
		return this.floorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Floor record) {
		
		return this.floorMapper.updateByPrimaryKey(record);
	}

}
