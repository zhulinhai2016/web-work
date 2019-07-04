package com.iot.dao;

import java.util.List;

import com.iot.model.DevicePower;

public interface DevicePowerMapper {
	
	public int savePower(DevicePower devicePower);
	public int deleteAll();
	public List<DevicePower> selectAll();
}
