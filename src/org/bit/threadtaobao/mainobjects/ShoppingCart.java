package org.bit.threadtaobao.mainobjects;

import java.util.ArrayList;
import java.util.Date;

public class ShoppingCart{

	private ArrayList<Goods> goodsList;
	private int allGoodsNum;
	private double totalAmount;
	
	public ShoppingCart(ArrayList<Goods> goodsList, int allGoodsNum,
			double totalAmount) {
		super();
		this.goodsList = goodsList;
		this.allGoodsNum = allGoodsNum;
		this.totalAmount = totalAmount;
	}
	
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
	
	public void delGoods(Goods goods) {
		goodsList.remove(goods);
		this.allGoodsNum--;
		Discount discount = goods.getDiscount();
		if ( discount != null && discount.getDeadline().after(new Date())) {
			this.totalAmount -= goods.getGoodsPrice() * discount.getValue()/10;
		} else {
			this.totalAmount -= goods.getGoodsPrice();
		}
	}
	
	public void addGoods(Goods goods) {
		goodsList.add(goods);
		this.allGoodsNum++;
		Discount discount = goods.getDiscount();
		if ( discount != null && discount.getDeadline().after(new Date())) {
			this.totalAmount += goods.getGoodsPrice() * discount.getValue()/10;
		} else {
			this.totalAmount += goods.getGoodsPrice();
		}
	}
	
	public void generateOrder() {
		
	}
}
