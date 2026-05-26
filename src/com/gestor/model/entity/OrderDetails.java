package com.gestor.model.entity;

/**
 * @deprecated This class is a duplicate of the Order class and should not be used.
 */
public class OrderDetails {
	
	private int id;
	private int orderId;
	private int productId;
	private int amount;
	
	/**
	 * This class is duplicate of Order Class.
	 * @deprecated
	 */
	public OrderDetails (int id, int orderId, int productId,int amount) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	

}
