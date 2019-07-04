package com.iot.model;

import java.util.List;

public class ResultVO {
  public int page;
  public int total;
  public List rows;
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public List getRows() {
	return rows;
}
public void setRows(List rows) {
	this.rows = rows;
}
 
}
