package com.iot.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 远程调用失败，或者断网
 * @author LinHaiZhu
 *
 * 创建于：2018年7月11日-下午1:38:19
 */
public class RunError {
	private Long runErrorId;
	private Long classRoomId;
	private String deviceType;
	private String deviceName;
	private String errorType;
	private String createYear;
	private Long createMonth;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date recoveryTime;
	private int limit; // 每页多少行数据
	private int offset; // 第几页
	
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
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
