package com.iot.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;

public class RunCountData {
	private static DecimalFormat format = new DecimalFormat("#.00");
	private String name;
	private Double value;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		try {
			this.value = Double.parseDouble(format.format(value));
		} catch (Exception e) {
			e.printStackTrace();
			this.value =value;
		}
		
	}
	
	public static void main(String[] args) throws ParseException {
		
		DecimalFormat format = new DecimalFormat("#.00");
		BigDecimal bigDecimal = new BigDecimal(1114111111.021);
		Double d = 1111111.4;
		double double1 = Double.parseDouble(format.format(d));
		System.out.println(double1);
	}
}
