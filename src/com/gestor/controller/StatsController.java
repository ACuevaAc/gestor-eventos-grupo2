package com.gestor.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.gestor.service.FreeChartService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.StatsAdminProducts;

public class StatsController {

	private StatsAdminProducts view;
	private FreeChartService givenProduct;

	public StatsController(StatsAdminProducts v) {
		this.view = v;
		this.givenProduct = new FreeChartService();
		startController();
		showStatistics(); 
	}

	private void startController() {
		view.setTitle("Gestor Eventos - Estadísticas del Sistema");
		view.getBtnVolver().addActionListener(e -> goBack());		
	}

	public void goBack() {
		view.dispose();
		AdminMainView v = new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	
	public void showStatistics() {
		DefaultCategoryDataset productsDataset = givenProduct.getMostOrderedProducts();
		JFreeChart graficoProductos = ChartFactory.createBarChart(
				"Top Productos Más Pedidos",
				"Productos",
				"Cantidad de Unidades",
				productsDataset,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		DefaultPieDataset tablesDataset = givenProduct.getTablesByCapacity();
		JFreeChart graficoMesas = ChartFactory.createPieChart(
				"Distribución de Mesas por Capacidad Máxima", // titulo
				tablesDataset,                                // Datos
				true,                                         // Mostrar leyenda de colores
				true,                                         // Tooltips activos
				false                                         // URLs desactivadas
		);

	
		view.cargarGraficosEnPestanas(graficoProductos, graficoMesas);
		view.setVisible(true);
	}
}
