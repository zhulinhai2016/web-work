package com.iot.dao;

import com.iot.model.Build;
import com.iot.model.BuildExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuildMapper {
    long countByExample(BuildExample example);

    int deleteByExample(BuildExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Build record);

    int insertSelective(Build record);

    List<Build> selectByExample(BuildExample example);

    Build selectByPrimaryKey(Long id);
    Build selectByPrimaryName(String name);//新加的
    int updateByExampleSelective(@Param("record") Build record, @Param("example") BuildExample example);

    int updateByExample(@Param("record") Build record, @Param("example") BuildExample example);

    int updateByPrimaryKeySelective(Build record);

    int updateByPrimaryKey(Build record);
}