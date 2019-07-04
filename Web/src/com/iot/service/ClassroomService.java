package com.iot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;

public interface ClassroomService {
	   long countByExample(ClassroomExample example);

	    int deleteByExample(ClassroomExample example);

	    int deleteByPrimaryKey(Long id);

	    int insert(Classroom record);

	    int insertSelective(Classroom record);

	    List<Classroom> selectByExample(ClassroomExample example);

	    Classroom selectByPrimaryKey(Long id);

	    int updateByExampleSelective(@Param("record") Classroom record, @Param("example") ClassroomExample example);

	    int updateByExample(@Param("record") Classroom record, @Param("example") ClassroomExample example);

	    int updateByPrimaryKeySelective(Classroom record);

	    int updateByPrimaryKey(Classroom record);
	    
	    void syncPool(Long recordId);
	    List<Classroom> selectByName(String name);//新加的
	    List<Classroom> selectByIP(String IP);
	    List<Classroom> checkedByIP(String IP,String classId,String id);
}
