package org.bit.threadtaobao.mainobjects;

import java.util.Date;

public class Discount {

	private double value;
	private Date deadline;
	
	public Discount(double value, Date deadline) {
		super();
		this.value = value;
		this.deadline = deadline;
	}
	
	public double getValue() {
		return value;
	}
	
	public Date getDeadline() {
		return deadline;
	}
}
