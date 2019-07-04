package com.iot.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iot.model.Build;
import com.iot.model.BuildExample;

public interface BuildService {
    long countByExample(BuildExample example);

    int deleteByExample(BuildExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Build record);

    int insertSelective(Build record);

    List<Build> selectByExample(BuildExample example);

    Build selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Build record, @Param("example") BuildExample example);

    int updateByExample(@Param("record") Build record, @Param("example") BuildExample example);

    int updateByPrimaryKeySelective(Build record);

    int updateByPrimaryKey(Build record);
}
