package org.jiumao.order.domin;

import base.BaseBean;

public class Order extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8219217272554797701L;
	private Integer id;
	private String goodsIds; //商品id
	private String goodsName;  //商品名称
	private String goodsNumbers;   //商品数量
	private String goodsPrice;
	public String getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	private double price;   //商品价格

	public String getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(String goodsIds) {
		this.goodsIds = goodsIds;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNumbers() {
		return goodsNumbers;
	}
	public void setGoodsNumbers(String goodsNumbers) {
		this.goodsNumbers = goodsNumbers;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
  
}        
