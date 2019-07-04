package com.iot.service;

import java.util.List;

import com.iot.model.DevicePower;

/**
 * 
 * @author LinHaiZhu
 *
 * 创建于：2018年7月14日-上午11:22:30
 */
public interface DevicePowerService {
	public int savePower(DevicePower devicePower);
	public DevicePower selectAll();
}
