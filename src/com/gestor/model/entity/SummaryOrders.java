package com.gestor.model.entity;

public class SummaryOrders {

	private String productName;
	private int quantity;
	private double totalProductPrice;

	public SummaryOrders(String productName, int quantity, double totalProductPrice) {
		this.productName = productName;
		this.quantity = quantity;
		this.totalProductPrice = totalProductPrice;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalProductPrice() {
		return totalProductPrice;
	}

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}
}
