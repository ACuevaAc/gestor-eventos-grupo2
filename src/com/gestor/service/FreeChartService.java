package com.gestor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.data.category.DefaultCategoryDataset;

public class FreeChartService {

	private Connection conexion;

	public FreeChartService(Connection conexion) {
		this.conexion = conexion;
	}

	public DefaultCategoryDataset obtenerProductosMasPedidos() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String sql = "SELECT P.ID_PRODUCTO, PRO.NOMBRE, P.CANTIDAD"
				+ "FROM PIDE P, PRODUCTS PRO"
				+ "WHERE P.ID_PRODUCTO = PRO.ID";
		

		try (PreparedStatement ps = conexion.prepareStatement(sql);
		     ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				String producto = rs.getString("nombre");
				int cantidad = rs.getInt("total_pedido");
				dataset.addValue(cantidad, "Unidades Pedidas", producto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataset;
	}
}