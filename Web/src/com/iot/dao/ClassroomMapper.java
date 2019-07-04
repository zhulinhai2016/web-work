package com.iot.dao;

import com.iot.model.Classroom;
import com.iot.model.ClassroomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClassroomMapper {
    long countByExample(ClassroomExample example);

    int deleteByExample(ClassroomExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Classroom record);

    int insertSelective(Classroom record);
    
    List<Classroom> selectByExample(ClassroomExample example);

    Classroom selectByPrimaryKey(Long id);
  //新增通过教室名称查看教室
    List<Classroom> selectByName(String name);
    //新增通过IP查询占用教室
    List<Classroom> selectByIP(String IP);

    int updateByExampleSelective(@Param("record") Classroom record, @Param("example") ClassroomExample example);

    int updateByExample(@Param("record") Classroom record, @Param("example") ClassroomExample example);

    int updateByPrimaryKeySelective(Classroom record);

    int updateByPrimaryKey(Classroom record);
    List<Classroom> checkedByIP(Classroom room);
}