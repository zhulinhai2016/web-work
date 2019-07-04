package com.iot.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseAction {
    protected HttpServletRequest request;
    protected ServletContext application;
    protected HttpServletResponse response;
    protected HttpSession session;
	
    protected String limit;//每页显示的记录数               
	protected String offset ;//当前第几�?
    protected int id;//当前第几�?
    
    protected String cjr;
    protected String cjrmc;
    //springmvc 每次方法调用前执行的方法
    @ModelAttribute
    protected void generateServletRelated(HttpServletRequest request,HttpServletResponse response){
    	this.request = request;
    	this.response = response;
    	this.session = request.getSession();
    	this.limit = this.getParameter("limit", "10");
    	this.offset = this.getParameter("offset", "1");
    }
    
    public String getUsertype() {
    	return session.getAttribute("usertype")!=null?session.getAttribute("usertype").toString():"";
	}

	public String getCurrentFullTime() {
		String pat1 = "yyyy-MM-dd HH:mm:ss" ;
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;
		return sdf1.format(new Date());
	}


 
	public String getCjrmc() {
    	return session.getAttribute("username")!=null?session.getAttribute("username").toString():"";
	}

	public void setCjrmc(String cjrmc) {
		this.cjrmc = cjrmc;
	}

	protected String cjrq;
    
	public String getCjr() {
		return session.getAttribute("userid")!=null?session.getAttribute("userid").toString():"";
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getCjrq() {
		String pat1 = "yyyy-MM-dd" ;
		SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;
		return sdf1.format(new Date());
	}

	public void setCjrq(String cjrq) {
		this.cjrq = cjrq;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 
   public String getParameter(String name,String defaultvalue)
   {
	   return StringUtils.isBlank(request.getParameter(name))?defaultvalue:request.getParameter(name).toString().trim();
   }

	
}
