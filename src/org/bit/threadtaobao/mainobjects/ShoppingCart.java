package org.bit.threadtaobao.mainobjects;

import java.util.ArrayList;

import android.app.Activity;

public class ShoppingCart extends Activity {

	private ArrayList<Goods> goodsList;
	private int allGoodsNum;
	private double totalAmount;

	public ArrayList<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(ArrayList<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public int getAllGoodsNum() {
		return allGoodsNum;
	}

	public void setAllGoodsNum(int allGoodsNum) {
		this.allGoodsNum = allGoodsNum;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void generateOrder() {
		
	}
}
