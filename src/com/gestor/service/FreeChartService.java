package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.config.DatabaseConnection;

/**
 * @class FreeChartService
 * @description Domain service architectural component managing business intelligence visualization models,
 * querying reporting metrics, executing relational join aggregate queries, and mapping data snapshots 
 * directly into specialized JFreeChart dataset structures (Category and Pie datasets).
 */
public class FreeChartService {

    public Connection conn;

    /**
     * @constructor
     * @description Initializes the data analytics reporting sub-system context, pulling active relational database 
     * connections via shared transactional connection utilities.
     */
    public FreeChartService() {
        try {
            this.conn = DatabaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @method getMostOrderedProducts
     * @description Issues a synchronous relational query matching sales lines and catalog items, compiling volumetric 
     * item aggregations grouped by unique object identifiers to feed categorical layout bar chart structures.
     * @returns {DefaultCategoryDataset} Data representation matrix specifying units sold mapped against structural product labels.
     */
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

    /**
     * @method getTablesByCapacity
     * @description Executes inventory analytical checks, isolating physical location entities by matching configuration bounds, 
     * applying statistical group counting metrics based on capacity rules, and translating records into categorical pie charts.
     * @returns {DefaultPieDataset} Proportional data allocation map tracking venue asset ratios categorized by peak seating capacities.
     */
    public DefaultPieDataset getTablesByCapacity() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        
        String sql = "SELECT numero_max, COUNT(*) AS total_mesas "
                   + "FROM mesa "
                   + "GROUP BY numero_max "
                   + "ORDER BY numero_max ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int capacity = rs.getInt("numero_max");
                int totalTables = rs.getInt("total_mesas");
                dataset.setValue("Mesas para " + capacity + " pers.", totalTables);
            }
        } catch (SQLException e) {
            System.err.println("Error en getTablesByCapacity: " + e.getMessage());
            e.printStackTrace();
        }
        return dataset;
    }
}
