package com.gestor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.gestor.service.FreeChartService;
import com.gestor.view.admin.StatsAdminProducts;

public class StatsController {

	private StatsAdminProducts vista;
	private FreeChartService productoDao;

	public StatsController(Connection conexion) {
		this.vista = new StatsAdminProducts();
		this.productoDao = new FreeChartService(conexion);
		inicializarControlador();
	}


	private void inicializarControlador() {
		vista.setTitle("Gestor Eventos - Estadísticas de Productos");

		vista.getBtnVolver().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vista.dispose();
			}
		});
	}

	
	public void mostrarEstadisticas() {
		DefaultCategoryDataset dataset = productoDao.obtenerProductosMasPedidos();

		JFreeChart grafico = ChartFactory.createBarChart(
				"Top Productos Más Pedidos",
				"Productos",
				"Cantidad de Unidades",
				dataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);

		vista.cargarGrafico(grafico);
		vista.setVisible(true);
	}
}