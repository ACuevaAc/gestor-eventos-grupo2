package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.config.DatabaseConnection;
import com.gestor.model.entity.Order;
import com.gestor.model.entity.SummaryOrders;

/**
 * @class OrderService
 * @description Domain service architectural component orchestrating client transactional operations,
 * handling persistence logic for transaction line-items, executing aggregated database metrics computations,
 * mapping relational join sub-queries, and handling cascade order removals.
 */
public class OrderService {
	private Connection conn;

	/**
	 * @method OrderService
	 * @description Initializes the service and secures the connection to the
	 *              PostgreSQL VPS.
	 */
	public OrderService() {
		try {
			this.conn = DatabaseConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Error: Could not establish connection in OrderService.");
			e.printStackTrace();
		}
	}

	/**
	 * @method createOrder
	 * @description Inserts a new order record into the 'pide' table.
	 * @param {int}    tableId - ID of the table placing the order.
	 * @param {int}    productId - ID of the ordered product.
	 * @param {int}    quantity - Number of units requested.
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
	 * @returns {List<Pedido>} A list containing order entities mapped from the
	 *          database rows.
	 */
	public List<Order> getOrdersByTable(int tableId) {
		List<Order> list = new ArrayList<>();
		String sql = "SELECT id, id_mesa, id_producto, cantidad, precio_total FROM pide WHERE id_mesa = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, tableId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(new Order(rs.getInt("id"), rs.getInt("id_mesa"), rs.getInt("id_producto"),
							rs.getInt("cantidad"), rs.getDouble("precio_total")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * @method calculateTableTotal
	 * @description Computes the aggregated financial sum of all active orders for a
	 *              given table using SUM.
	 * @param {int} tableId - The identity of the target table.
	 * @returns {double} The total accumulated price of all items ordered by the
	 *          table. Returns 0.0 if empty or error.
	 * @public
	 */
	public double calculateTableTotal(int tableId) {
		String sql = "SELECT SUM(precio_total) FROM pide WHERE id_mesa = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, tableId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getDouble(1);
			}
		} catch (SQLException e) {
			System.err.println("Error calculating total for Table ID: " + tableId);
			e.printStackTrace();
		}
		return 0.0;
	}

	/**
     * @method getOrderDetailsByTable
     * @description Compiles de-normalized data projection structures by joining transaction lines with structural catalog indices,
     * building itemized descriptive summaries for invoicing components.
     * @param {int} tableId - Destination operational primary validation identifier.
     * @returns {List<SummaryOrders>} Flattened data visualization metrics array listing descriptive itemized details.
     */
	public List<SummaryOrders> getOrderDetailsByTable(int tableId) {
	    List<SummaryOrders> orderDetails = new ArrayList<>();
	    
	    String sql = "SELECT p.nombre AS producto, o.cantidad, o.precio_total " +
	                   "FROM pide o " +
	                   "JOIN producto p ON o.id_producto = p.id " +
	                   "WHERE o.id_mesa = ?";

	    try (PreparedStatement pS = conn.prepareStatement(sql)) {
	        
	        pS.setInt(1, tableId);
	        try (ResultSet rs = pS.executeQuery()) {
	            while (rs.next()) {
	                String productName = rs.getString("producto");
	                int quantity = rs.getInt("cantidad");
	                double totalProductPrice = rs.getDouble("precio_total");
	                
	                orderDetails.add(new SummaryOrders(productName, quantity, totalProductPrice));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error en getOrderDetailsByTable para la mesa ID: " + tableId);
	        e.printStackTrace();
	    }
	    
	    return orderDetails;
	}
	
	/**
     * @method deleteTableOrder
     * @description Executes targeted transactional clear commands to erase historical line-item rows 
     * containing identical physical asset allocation tracking numbers.
     * @param {int} tableId - Unique inventory index parameter tracking properties slated for purging.
     */
	public void deleteTableOrder(int tableId) {
		String sql = "DELETE FROM pide WHERE id_mesa=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, tableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    			
    }
}