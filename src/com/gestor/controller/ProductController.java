package com.gestor.controller;

import com.gestor.model.entity.Producto;
import java.util.List;
import java.util.ArrayList;

/**
 * @class ProductController
 * @description Controller responsible for orchestrating product-related actions between the View and the Service layer.
 */
public class ProductController {
    private List<Producto> ProductCache;

    /**
     * @method ProductController
     * @description Initializes the controller and loads the initial product list.
     */
    public ProductController() {
        this.ProductCache = new ArrayList<>();
    }

    /**
     * @method addProduct
     * @description Adds a new product to the system.
     * @param {String} name - Product name.
     * @param {double} price - Unit price.
     * @param {byte[]} image - Byte array of the product image.
     * @returns {boolean} True if the product was successfully added.
     */
    public boolean addProduct(String name, double price, byte[] image) {
        if (name == null || name.isEmpty() || price < 0) return false;
        
    }

    /**
     * @method getAllProducts
     * @description Retrieves the list of all available products.
     * @returns {List<Producto>} List of product entities.
     */
    public List<Producto> getAllProducts() {
        return ProductCache;
    }
}