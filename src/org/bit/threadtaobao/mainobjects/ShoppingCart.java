package org.bit.threadtaobao.mainobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.bit.threadtaobao.globalEntity.GlobalObjects;

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
	//生成订单
	public boolean generateOrder() {
		ArrayList<Goods> orderGoodsList = (ArrayList<Goods>)goodsList.clone();
		Random random = new Random();
		int x = random.nextInt(89999999);
		int flag = 0;
		while (true) {
			flag = 0;
			x += 10000000;
			for (Order order : GlobalObjects.orderList) {
				if (order.getOrderId().equals(String.valueOf(x))) {
					flag = 1;
					break;
				}
			}
			if (flag == 0) {
				break;
			}
			x = random.nextInt(89999999);
		}
		Order order = new Order(String.valueOf(x), orderGoodsList, this.totalAmount, null, null, null);
		if (order.submit()) {
			clear();
			GlobalObjects.orderList.add(order);
			return true;
		}
		return false;
	}
	
	public void clear() {
		this.goodsList.clear();
		this.allGoodsNum = 0;
		this.totalAmount = 0;
	}
}
