package com.gestor.controller;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.gestor.service.FreeChartService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.StatsAdminProducts;

/**
 * @class StatsController
 * @description Controller architectural component managing the system analytics subsystem,
 * orchestrating the retrieval of business intelligence data models, instantiating JFreeChart graphical 
 * telemetry components (Bar and Pie charts), and synchronizing their rendering contexts across layout tab views.
 */
public class StatsController {

	private StatsAdminProducts view;
	private FreeChartService givenProduct;

	/**
     * @constructor
     * @description Initializes the statistical analytical execution context, instantiates underlying telemetry service layers, 
     * configures structural window window properties, and initiates synchronous chart compilation metrics.
     * @param {StatsAdminProducts} v - The dedicated data metrics dashboard frame view component interface wrapper.
     */
	public StatsController(StatsAdminProducts v) {
		this.view = v;
		this.givenProduct = new FreeChartService();
		startController();
		showStatistics(); 
	}

	/**
     * @method startController
     * @private
     * @description Applies structural header text variations to frame environments and binds navigation actions 
     * toward exit state routes.
     */
	private void startController() {
		view.setTitle("Gestor Eventos - Estadísticas del Sistema");
		view.getBtnVolver().addActionListener(e -> goBack());		
	}

	/**
     * @method goBack
     * @description Tears down the active tracking statistics window to return visual application flow focus 
     * back to primary administration system management dashboard contexts.
     */
	public void goBack() {
		view.dispose();
		AdminMainView v = new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
	}
	
	/**
     * @method showStatistics
     * @description Queries dataset models through downstream reporting services, instantiates vertical bar chart 
     * specifications and dimensional pie rendering charts, and passes compiled graphical objects into layout pane elements.
     */
	public void showStatistics() {
		DefaultCategoryDataset productsDataset = givenProduct.getMostOrderedProducts();
		JFreeChart productsGraphic = ChartFactory.createBarChart(
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
				"Distribución de Mesas por Capacidad Máxima",
				tablesDataset,
				true,
				true,
				false
		);

	
		view.cargarGraficosEnPestanas(productsGraphic, graficoMesas);
		view.setVisible(true);
	}
}
