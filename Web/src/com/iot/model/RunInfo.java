package com.iot.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 运行信息表
 * @author Zhu
 *
 * 创建于：2018年7月6日-下午3:24:09
 */
public class RunInfo {
	
	private Long runInfoId;
	private Long classRoomId;
	private String deviceType;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date openTime;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date closeTime;
	private String isOpened;
	private String isClosed;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	
	public Long getRunInfoId() {
		return runInfoId;
	}
	public void setRunInfoId(Long runInfoId) {
		this.runInfoId = runInfoId;
	}
	public Long getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public String getIsOpened() {
		return isOpened;
	}
	public void setIsOpened(String isOpened) {
		this.isOpened = isOpened;
	}
	public String getIsClosed() {
		return isClosed;
	}
	public void setIsClosed(String isClosed) {
		this.isClosed = isClosed;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
