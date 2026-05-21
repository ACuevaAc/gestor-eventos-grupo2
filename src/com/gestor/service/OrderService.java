package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.ConexionDB;
import com.gestor.model.entity.Order;

/**
 * @class OrderService
 * @description Manages database operations for the 'pide' table in the gegdb database.
 * Handles order placements, quantities, and financial calculations per table.
 */
public class OrderService {
    private Connection conn;

    /**
     * @method OrderService
     * @description Initializes the service and secures the connection to the PostgreSQL VPS.
     */
    public OrderService() {
        try {
            this.conn = ConexionDB.obtener();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error: Could not establish connection in OrderService.");
            e.printStackTrace();
        }
    }

    /**
     * @method createOrder
     * @description Inserts a new order record into the 'pide' table.
     * @param {int} tableId - ID of the table placing the order.
     * @param {int} productId - ID of the ordered product.
     * @param {int} quantity - Number of units requested.
     * @param {double} totalPrice - Calculated total price (quantity * unit price).
     * @public
     */
    public void createOrder(int tableId, int productId, int quantity, double totalPrice) {
        String sql = "INSERT INTO pide (id_mesa, id_producto, cantidad, precio_total) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            ps.setInt(2, productId);
            ps.setInt(3, quantity);
            ps.setDouble(4, totalPrice);

            ps.execute();
        } catch (SQLException e) {
            System.err.println("Error creating order for Table ID: " + tableId);
            e.printStackTrace();
        }
    }

    /**
     * @method getOrdersByTable
     * @description Retrieves all active orders associated with a single table.
     * @param {int} tableId - The identity of the table.
     * @returns {List<Pedido>} A list containing order entities mapped from the database rows.
     */
    public List<Order> getOrdersByTable(int tableId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT id, id_mesa, id_producto, cantidad, precio_total FROM pide WHERE id_mesa = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tableId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("id_mesa"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio_total")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}