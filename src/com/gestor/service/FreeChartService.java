package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.data.category.DefaultCategoryDataset;

import com.config.ConexionDB;

public class FreeChartService {

    public Connection conn;

    public FreeChartService() {

        try {
            this.conn = ConexionDB.obtener();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

	public DefaultCategoryDataset getMostOrderedProducts() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql = "SELECT P.ID_PRODUCTO, PRO.NOMBRE, P.CANTIDAD"
				+ "FROM PIDE P, PRODUCTS PRO"
				+ "WHERE P.ID_PRODUCTO = PRO.ID";

		try (PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String product = rs.getString("nombre");
				int amount = rs.getInt("total_pedido");
				dataset.addValue(amount, "Unidades Pedidas", product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}
}