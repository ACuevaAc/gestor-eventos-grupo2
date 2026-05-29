package com.gestor.model.entity;

/**
 * @class SummaryOrders
 * @description Plain Old Java Object (POJO) model entity representing a data projection schema,
 * aggregating normalized relational information from transaction line-items and inventory records 
 * into a single unified itemized billing row data mapping wrapper for structural interface elements.
 */
public class SummaryOrders {

    /**
     * @private
     * @type {String}
     */
    private String productName;

    /**
     * @private
     * @type {int}
     */
    private int quantity;

    /**
     * @private
     * @type {double}
     */
    private double totalProductPrice;

    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize itemized line-item analytics logs 
     * matching custom database relational query join data projection view schemas.
     * @param {String} productName - The descriptive naming parameter extracted from the resolved catalog item.
     * @param {int} quantity - The incremental total volume count allocated to this specific invoice stream tracking index.
     * @param {double} totalProductPrice - Double-precision accumulated financial transaction value mapping for the total line items.
     */
    public SummaryOrders(String productName, int quantity, double totalProductPrice) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalProductPrice = totalProductPrice;
    }

    /**
     * @method getProductName
     * @description Extracts the de-normalized string description representing the target catalog item name property.
     * @returns {String} The structural commercial title identifier text mapping.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @method setProductName
     * @description Binds the de-normalized text description tracking value to the product line-item mapping layers.
     * @param {String} productName - Absolute descriptive naming property assigned to the interface text component.
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @method getQuantity
     * @description Retrieves the total units scalar count assigned to the itemized transaction index mapping wrapper.
     * @returns {int} Volumetric transaction sequence number value.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @method setQuantity
     * @description Assigns the scalar metric counting parameter specifying total allocations for the line-item record layer.
     * @param {int} quantity - Target transaction volume metric parameter constraint.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @method getTotalProductPrice
     * @description Resolves the computational double financial tracking parameter representing accumulated rows pricing values.
     * @returns {double} Specific multi-unit financial transaction data threshold.
     */
    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    /**
     * @method setTotalProductPrice
     * @description Standardizes and applies the double pricing accumulation threshold for unified accounting layers.
     * @param {double} totalProductPrice - Target calculated currency matrix boundary parameter configuration.
     */
    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }
}