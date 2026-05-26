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

	private StatsAdminProducts vista;
	private FreeChartService productoDao;

	public StatsController(StatsAdminProducts v) {
		this.vista = v;
		this.productoDao = new FreeChartService();
		inicializarControlador();
		mostrarEstadisticas(); 
	}

	private void inicializarControlador() {
		vista.setTitle("Gestor Eventos - Estadísticas del Sistema");
		vista.getBtnVolver().addActionListener(e -> volver());		
	}

	public void volver() {
		vista.dispose();
		AdminMainView v = new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	
	public void mostrarEstadisticas() {
		DefaultCategoryDataset datasetProductos = productoDao.getMostOrderedProducts();
		JFreeChart graficoProductos = ChartFactory.createBarChart(
				"Top Productos Más Pedidos",
				"Productos",
				"Cantidad de Unidades",
				datasetProductos,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
		);
		DefaultPieDataset datasetMesas = productoDao.getTablesByCapacity();
		JFreeChart graficoMesas = ChartFactory.createPieChart(
				"Distribución de Mesas por Capacidad Máxima", // titulo
				datasetMesas,                                 // Datos
				true,                                         // Mostrar leyenda de colores
				true,                                         // Tooltips activos
				false                                         // URLs desactivadas
		);

	
		vista.cargarGraficosEnPestanas(graficoProductos, graficoMesas);
		vista.setVisible(true);
	}
}
