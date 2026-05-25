package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.config.ConexionDB;
import com.gestor.model.entity.Product;

/**
 * @class ProductService
 * @description Manages database operations for Product entities within the gegdb database.
 */
public class ProductService {
    private Connection conn;
    
    /**
     * @method ProductService
     * @description Initializes the connection to the PostgreSQL VPS.
     */
    public ProductService() {
        try {
            this.conn = ConexionDB.obtener();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: Could not establish connection in ProductoService.");
            e.printStackTrace();
        }
    }
    
    /**
     * @method getAllProducts
     * @description Retrieves all products currently stored in the database.
     * @returns {List<Producto>} A list of Product objects.
     */
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT id, nombre, imagen, precio FROM producto";
        
        try (Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            
            while(rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getBytes("imagen")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @method createProduct
     * @description Inserts a new product into the database.
     * @param {Producto} p - The product entity to persist.
     * @public
     */
    public void createProduct(Product p) {
        String sql = "INSERT INTO producto (nombre, imagen, precio) VALUES (?, ?, ?)";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setBytes(2, p.getImage());
            ps.setDouble(3, p.getPrice());
            
            ps.execute();
        } catch (SQLException e) {
            System.err.println("Error inserting product: " + p.getName());
            e.printStackTrace();
        }
    }

    /**
     * @method deleteProduct
     * @description Removes a product from the database by its ID.
     * @param {int} id - The ID of the product to delete.
     */
    public void deleteProduct(int id) {
        String sql = "DELETE FROM producto WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}