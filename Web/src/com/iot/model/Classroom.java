package com.iot.model;

import java.io.Serializable;

public class Classroom implements Serializable {
    private Long id;

    private String name;

    private String classIp;

    private String classCenterIp;

    private String classId;

    private String classTouy;

    private Long buildId;

    private Long floorId;

    private String touytype;

    private String kongtype;

    private String versionNum;
    
    
    public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassIp() {
        return classIp;
    }

    public void setClassIp(String classIp) {
        this.classIp = classIp == null ? null : classIp.trim();
    }

    public String getClassCenterIp() {
        return classCenterIp;
    }

    public void setClassCenterIp(String classCenterIp) {
        this.classCenterIp = classCenterIp == null ? null : classCenterIp.trim();
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getClassTouy() {
        return classTouy;
    }

    public void setClassTouy(String classTouy) {
        this.classTouy = classTouy == null ? null : classTouy.trim();
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public String getTouytype() {
        return touytype;
    }

    public void setTouytype(String touytype) {
        this.touytype = touytype == null ? null : touytype.trim();
    }

    public String getKongtype() {
        return kongtype;
    }

    public void setKongtype(String kongtype) {
        this.kongtype = kongtype == null ? null : kongtype.trim();
    }
}