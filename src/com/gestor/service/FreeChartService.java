package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.config.DatabaseConnection;

public class FreeChartService {

    public Connection conn;

    public FreeChartService() {
        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public DefaultCategoryDataset getMostOrderedProducts() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String sql = "SELECT pro.nombre AS producto_nombre, SUM(p.cantidad) AS total_pedido "
                   + "FROM pide p "
                   + "JOIN producto pro ON p.id_producto = pro.id "
                   + "GROUP BY pro.id, pro.nombre";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String product = rs.getString("producto_nombre");
                int amount = rs.getInt("total_pedido");
                dataset.addValue(amount, "Unidades Pedidas", product);
            }
        } catch (SQLException e) {
            System.err.println("Error en getMostOrderedProducts: " + e.getMessage());
            e.printStackTrace();
        }
        return dataset;
    }

  // Agrupa mesa por numero de comensales
    public DefaultPieDataset getTablesByCapacity() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        String sql = "SELECT numero_max, COUNT(*) AS total_mesas "
                   + "FROM mesa "
                   + "GROUP BY numero_max "
                   + "ORDER BY numero_max ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int capacidad = rs.getInt("numero_max");
                int totalMesas = rs.getInt("total_mesas");
                dataset.setValue("Mesas para " + capacidad + " pers.", totalMesas);
            }
        } catch (SQLException e) {
            System.err.println("Error en getTablesByCapacity: " + e.getMessage());
            e.printStackTrace();
        }
        return dataset;
    }
}
