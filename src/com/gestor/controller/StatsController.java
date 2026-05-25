package com.gestor.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.gestor.service.FreeChartService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.StatsAdminProducts;

public class StatsController {

	private StatsAdminProducts vista;
	private FreeChartService productoDao;

	public StatsController(StatsAdminProducts v) {
		this.vista = v;
		this.productoDao = new FreeChartService();
		inicializarControlador();
	}


	private void inicializarControlador() {
		vista.setTitle("Gestor Eventos - Estadísticas de Productos");

		vista.getBtnVolver().addActionListener(e-> volver());		
	}

	public void volver() {
		vista.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
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