package com.gestor.model.entity;

/**
 * @class Product
 * @description Plain Old Java Object (POJO) model entity representing a data schema mapping 
 * for commercial items, managing catalog naming parameters, specific double-precision data pricing values, 
 * and dynamic binary raw streams for raw asset asset tracking.
 */
public class Product {
    
    /**
     * @private
     * @type {int}
     */
    private int id;
    
    /**
     * @private
     * @type {String}
     */
    private String name;
    
    /**
     * @private
     * @type {double}
     */
    private double price;
    
    /**
     * @private
     * @type {byte[]}
     */
    private byte[] image;
    
    /**
     * @constructor
     * @description Full structural constructor blueprint to initialize commercial catalog parameters 
     * matching ongoing operational database entity schemas.
     * @param {int} id - Unique primary key identifier for the catalog record tracking index.
     * @param {String} name - The descriptive naming parameter assigned to the commercial asset.
     * @param {double} price - Double-precision financial threshold assigned to unit transactions.
     * @param {byte[]} image - Raw database binary stream byte array containing the product image asset.
     */
    public Product(int id, String name, double price, byte[] image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    /**
     * @method getId
     * @description Retrieves the unique primary key tracker index for this product instance.
     * @returns {int} The catalog entity sequence key data metric.
     */
    public int getId() {
        return id;
    }

    /**
     * @method setId
     * @description Assigns the operational primary key tracker reference to this product instance mapping.
     * @param {int} id - Target unique sequence identity indexing value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @method getName
     * @description Exposes the text naming property representing this catalog asset.
     * @returns {String} The structural asset description text.
     */
    public String getName() {
        return name;
    }

    /**
     * @method setName
     * @description Binds the target descriptive string value to the product profile definition layers.
     * @param {String} name - Absolute textual assignment tracking metric.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @method getPrice
     * @description Resolves the computational financial pricing parameter mapped to the product model.
     * @returns {double} The specific scalar financial data threshold.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @method setPrice
     * @description Standardizes and applies the commercial transaction pricing double metric layer.
     * @param {double} price - Target numeric transaction schema boundary parameter.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @method getImage
     * @description Extracts raw persistent byte arrays containing storage image layout binary metrics.
     * @returns {byte[]} Encapsulated data allocation stream mapping blocks, or null if unallocated.
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @method setImage
     * @description Injects raw relational binary data blocks representing asset tracking streams into persistent variables.
     * @param {byte[]} image - Targeting database operational byte array snapshot mapping.
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
}