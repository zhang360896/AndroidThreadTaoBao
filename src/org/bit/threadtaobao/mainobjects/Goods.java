package org.bit.threadtaobao.mainobjects;

public class Goods {
	private String goodsId;
	private String goodsName;
	private String goodsBrand;
	private double goodsPrice;
	private Discount discount;
	private Supermarket supermarket;


	public Goods() {
		
	}
	public Goods(String goodsId, String goodsName, String goodsBrand,
			double goodsPrice, Discount discount, Supermarket supermarket) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsBrand = goodsBrand;
		this.goodsPrice = goodsPrice;
		this.discount = discount;
		this.supermarket = supermarket;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public Discount getDiscount() {
		return discount;
	}

	public Supermarket getSupermarket() {
		return supermarket;
	}

}
