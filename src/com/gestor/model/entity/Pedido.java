package com.gestor.model.entity;

/**
 * @class Pedido
 * @description Entity class representing a record in the 'pide' table. 
 * Maps database order transactions directly to Java objects.
 */
public class Pedido {
    private int orderId;
    private int tableId;
    private int productId;
    private int amount;
    private double totalPrice;
    
    /**
     * @method Pedido
     * @description Parameterized constructor to instantiate a complete Order model.
     * @param {int} orderId - Unique identifier of the order transaction.
     * @param {int} tableId - Foreign key identifier referencing the table.
     * @param {int} productId - Foreign key identifier referencing the product.
     * @param {int} amount - Quantity of products requested.
     * @param {double} totalPrice - Aggregated price total for this specific entry.
     */
    public Pedido(int orderId, int tableId, int productId, int amount, double totalPrice) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.productId = productId;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }

    /**
     * @method getOrderId
     * @returns {int} The unique identifier of the order.
     */
    public int getOrderId() {
        return this.orderId;
    }

	/**
	 * @method setOrderId
	 * @param orderId
	 */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * @method getTableId
     * @returns {int} The table identifier linked to this order.
     */
    public int getTableId() {
        return this.tableId;
    }

	/**
	 * @method setTableId
	 * @param tableId
	*/
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    /**
     * @method getProductId
     * @returns {int} The product identifier linked to this order.
     */
    public int getProductId() {
        return this.productId;
    }

    /**
     * @method setProductId
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * @method getAmount
     * @returns {int} The total units of the product.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @method setAmount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @method getTotalPrice
     * @returns {double} The financial total calculated for this entry.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * @method setTotalPrice
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}