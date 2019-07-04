package com.iot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.dao.BuildMapper;
import com.iot.model.Build;
import com.iot.model.BuildExample;
import com.iot.service.BuildService;
@Service
@Transactional
public class BuildServiceImpl implements BuildService {
	@Autowired
	private BuildMapper buildMapper;
	@Override
	public long countByExample(BuildExample example) {
		
		return this.buildMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(BuildExample example) {
		
		return this.buildMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		
		return this.buildMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Build record) {
		
		return this.buildMapper.insert(record);
	}

	@Override
	public int insertSelective(Build record) {
		
		return this.buildMapper.insertSelective(record);
	}

	@Override
	public List<Build> selectByExample(BuildExample example) {
		
		return this.buildMapper.selectByExample(example);
	}

	@Override
	public Build selectByPrimaryKey(Long id) {
		
		return this.buildMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Build record, BuildExample example) {
		
		return this.buildMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Build record, BuildExample example) {
		
		return this.buildMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Build record) {
		
		return this.buildMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Build record) {
		
		return this.buildMapper.updateByPrimaryKey(record);
	}

}
