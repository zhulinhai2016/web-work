package com.iot.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 设备电量
 * @author LinHaiZhu
 *
 * 创建于：2018年7月14日-上午10:58:00
 */
public class DevicePower {
	private Long devicePowerId;
	private String deviceType;
	private Long powerNumber;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	// 
	private Long powerNumber1;
	private Long powerNumber2;
	private Long powerNumber3;
	public Long getDevicePowerId() {
		return devicePowerId;
	}
	public void setDevicePowerId(Long devicePowerId) {
		this.devicePowerId = devicePowerId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public Long getPowerNumber() {
		return powerNumber;
	}
	public void setPowerNumber(Long powerNumber) {
		this.powerNumber = powerNumber;
	}
	public Long getPowerNumber1() {
		return powerNumber1;
	}
	public void setPowerNumber1(Long powerNumber1) {
		this.powerNumber1 = powerNumber1;
	}
	public Long getPowerNumber2() {
		return powerNumber2;
	}
	public void setPowerNumber2(Long powerNumber2) {
		this.powerNumber2 = powerNumber2;
	}
	public Long getPowerNumber3() {
		return powerNumber3;
	}
	public void setPowerNumber3(Long powerNumber3) {
		this.powerNumber3 = powerNumber3;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
