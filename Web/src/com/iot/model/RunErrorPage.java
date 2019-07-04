package com.iot.model;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RunErrorPage {
	private static DecimalFormat format = new DecimalFormat("#.00");
	
	private Long runErrorId;
	private Long classRoomId;
	private String deviceName;
	private String className;
	private String deviceType;
	private String errorType;
	private String createYear;
	private Long createMonth;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date recoveryTime;
	private Double errorTime;
	private String errorTimeStr;
	public Long getRunErrorId() {
		return runErrorId;
	}
	public void setRunErrorId(Long runErrorId) {
		this.runErrorId = runErrorId;
	}
	public Long getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	public String getCreateYear() {
		return createYear;
	}
	public void setCreateYear(String createYear) {
		this.createYear = createYear;
	}
	public Long getCreateMonth() {
		return createMonth;
	}
	public void setCreateMonth(Long createMonth) {
		this.createMonth = createMonth;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getRecoveryTime() {
		return recoveryTime;
	}
	public void setRecoveryTime(Date recoveryTime) {
		this.recoveryTime = recoveryTime;
	}
	public Double getErrorTime() {
		return errorTime;
	}
	public void setErrorTime(Double errorTime) {
		try {
			if (errorTime != null) {
				this.errorTime = Double.parseDouble(format.format(errorTime));
				if (this.errorTime.doubleValue() >= 1) {
					this.errorTimeStr = String.valueOf(this.errorTime.doubleValue())+"小时";
				} else {
					this.errorTimeStr = String.valueOf(this.errorTime.doubleValue()*60)+"分钟";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (errorTime != null) {
				this.errorTime =errorTime;
				this.errorTimeStr = String.valueOf(this.errorTime.doubleValue())+"小时";
			} else {
				this.errorTime = null;
			}
		}
		
		this.errorTime = errorTime;
	}
	public String getErrorTimeStr() {
		return errorTimeStr;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
