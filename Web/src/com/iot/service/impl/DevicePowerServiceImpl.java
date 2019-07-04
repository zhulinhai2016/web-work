package com.iot.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iot.dao.DevicePowerMapper;
import com.iot.model.DevicePower;
import com.iot.service.DevicePowerService;

import util.Constans;

@Service
@Transactional
public class DevicePowerServiceImpl implements DevicePowerService {
	
	@Autowired
	private DevicePowerMapper devicePowerMapper;
	
	
	@Override
	public int savePower(DevicePower devicePower) {
		if (!(devicePower.getPowerNumber1()!=null && devicePower.getPowerNumber2() != null && devicePower.getPowerNumber3() != null)) {
			return 0;
		}
		DevicePower devicePower1= new DevicePower();
		DevicePower devicePower2= new DevicePower();
		DevicePower devicePower3= new DevicePower();
		devicePower1.setCreateDate(new Date());
		devicePower1.setDeviceType(Constans.DEVICE_TYPE[1]);
		devicePower1.setPowerNumber(devicePower.getPowerNumber1());
		
		devicePower2.setCreateDate(new Date());
		devicePower2.setDeviceType(Constans.DEVICE_TYPE[2]);
		devicePower2.setPowerNumber(devicePower.getPowerNumber2());
		
		devicePower3.setCreateDate(new Date());
		devicePower3.setDeviceType(Constans.DEVICE_TYPE[3]);
		devicePower3.setPowerNumber(devicePower.getPowerNumber3());

		devicePowerMapper.deleteAll();
		
		devicePowerMapper.savePower(devicePower1);
		devicePowerMapper.savePower(devicePower2);
		devicePowerMapper.savePower(devicePower3);
		return 1;
	}

	@Override
	public DevicePower selectAll() {
		List<DevicePower> list = devicePowerMapper.selectAll();
		DevicePower power = new DevicePower();
		if (list != null && !list.isEmpty()) {
			for (DevicePower devicePower : list) {
				if (devicePower.getDeviceType().equals(Constans.DEVICE_TYPE[1])) {
					power.setPowerNumber1(devicePower.getPowerNumber());
				}
				if (devicePower.getDeviceType().equals(Constans.DEVICE_TYPE[2])) {
					power.setPowerNumber2(devicePower.getPowerNumber());
				}
				if (devicePower.getDeviceType().equals(Constans.DEVICE_TYPE[3])) {
					power.setPowerNumber3(devicePower.getPowerNumber());
				}
			}
		}
		return power;
	}

}
