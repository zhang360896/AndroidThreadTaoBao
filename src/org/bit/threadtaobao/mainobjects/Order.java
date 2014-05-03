package org.bit.threadtaobao.mainobjects;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	private String orderId;
	private ArrayList<Goods> goodsList;
	private double totalAmount;
	private Date createTime;
	private Date payTime;
	private String state;
	
	public Order(String orderId, ArrayList<Goods> goodsList,
			double totalAmount, Date createTime, Date payTime, String state) {
		super();
		this.orderId = orderId;
		this.goodsList = goodsList;
		this.totalAmount = totalAmount;
		this.createTime = createTime;
		this.payTime = payTime;
		this.state = state;
	}

	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderId() {
		return orderId;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}
	
	public void submit() {

	}

	public void cancel() {

	}

	public void delete() {

	}
	
}
