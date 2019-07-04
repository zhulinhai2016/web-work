package com.iot.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.dao.ClassroomMapper;
import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;
import com.iot.service.ClassroomService;

import ecapi.api.ClassesFactory;

@Service
@Transactional
public class ClassroomServiceImpl implements ClassroomService {
	@Autowired
	private ClassroomMapper classroomMapper;

	@Override
	public long countByExample(ClassroomExample example) {
		return this.classroomMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(ClassroomExample example) {
		return this.classroomMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {

		return this.classroomMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Classroom record) {

		return this.classroomMapper.insert(record);
	}

	@Override
	public int insertSelective(Classroom record) {

		return this.classroomMapper.insertSelective(record);
	}

	@Override
	public List<Classroom> selectByExample(ClassroomExample example) {

		return this.classroomMapper.selectByExample(example);
	}
    @Override 
	public List<Classroom> selectByName(String name) {

		return this.classroomMapper.selectByName(name);
	}//32-35新加的
	
	@Override
	public Classroom selectByPrimaryKey(Long id) {

		return this.classroomMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(Classroom record, ClassroomExample example) {

		return this.classroomMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(Classroom record, ClassroomExample example) {

		return this.classroomMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(Classroom record) {

		return this.classroomMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Classroom record) {

		return this.classroomMapper.updateByPrimaryKey(record);
	}

	@Override
	public void syncPool(Long recordId) {
		Classroom classroom = this.classroomMapper.selectByPrimaryKey(recordId);
		if (classroom!=null&&ClassesFactory.pool!=null){
			String classRoomKey = classroom.getClassIp() + "_485";
			ClassesFactory.pool.remove(classRoomKey);
		}
	}
	@Override
	public List<Classroom> selectByIP(String IP) {
		// TODO Auto-generated method stub
		return this.classroomMapper.selectByIP(IP);
	}

	@Override
	public List<Classroom> checkedByIP(String IP,String classId,String id) {
		Classroom room = new Classroom();
		room.setClassId(classId);
		room.setClassIp(IP);
		if (id != null && id != "") {
			
			room.setId(Long.parseLong(id));
		} else {
			room.setId(null);
		}
		return this.classroomMapper.checkedByIP(room);
	}
}
