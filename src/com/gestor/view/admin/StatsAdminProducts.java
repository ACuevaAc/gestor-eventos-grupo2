package com.gestor.view.admin;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.gestor.controller.StatsController;

/**
 * @class StatsAdminProducts
 * @description Administrative analytics frame component rendering visual chart matrices and graphical metrics.
 * Provides embedded layout structures leveraging third-party visualization chart libraries, wraps interaction layers 
 * inside responsive tabbed pane selectors, handles dynamic layout repaints, and exposes navigational callback handles.
 */
public class StatsAdminProducts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private ChartPanel panelGrafico;

	/**
     * @constructor
     * @description Constructs the analytical statistics display viewport canvas, setting window placement constraints,
     * registering default cleanup window-close operations, and mounting the foundational interface panel configuration.
     */
	public StatsAdminProducts() {
		super("Estadísticas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		btnVolver = new JButton("Volver");
		panelBotones.add(btnVolver);
	}

	/**
     * @method cargarGraficosEnPestanas
     * @description Destroys existing presentation components within the central window zone, spawning a tab-based navigation matrix
     * to host multiple operational visualization elements like top item charts or active floor layouts data vectors.
     * @param {JFreeChart} graficoProd - Dataset presentation layer rendering item metric summaries.
     * @param {JFreeChart} graficoMesas - Dataset presentation layer tracking resource allocation charts.
     */
	public void cargarGraficosEnPestanas(JFreeChart graficoProd, JFreeChart graficoMesas) {
	    BorderLayout layout = (BorderLayout) contentPane.getLayout();
	    java.awt.Component componenteCentral = layout.getLayoutComponent(BorderLayout.CENTER);
	    if (componenteCentral != null) {
	        contentPane.remove(componenteCentral);
	    }

	    javax.swing.JTabbedPane pestanas = new javax.swing.JTabbedPane();
	    org.jfree.chart.ChartPanel panelProd = new org.jfree.chart.ChartPanel(graficoProd);
	    panelProd.setMouseWheelEnabled(true);
	    pestanas.addTab("Productos Más Pedidos", panelProd);
	    org.jfree.chart.ChartPanel panelMesas = new org.jfree.chart.ChartPanel(graficoMesas);
	    panelMesas.setMouseWheelEnabled(true);
	    pestanas.addTab("Capacidad de Mesas", panelMesas);
	    contentPane.add(pestanas, BorderLayout.CENTER);
	    contentPane.revalidate();
	    contentPane.repaint();
	}

	/**
     * @method cargarGrafico
     * @description Disposes of current chart panel instances to clean up framework hooks, mounting a single independent 
     * chart instance while enforcing explicit display parameters and enabling input wheel scaling actions.
     * @param {JFreeChart} grafico - The target presentation layer graphic configuration payload destined for canvas display.
     */
	public void cargarGrafico(JFreeChart grafico) {
		if (panelGrafico != null) {
			contentPane.remove(panelGrafico);
		}
		panelGrafico = new ChartPanel(grafico);
		panelGrafico.setPreferredSize(new java.awt.Dimension(600, 350));
		panelGrafico.setMouseWheelEnabled(true);
		contentPane.add(panelGrafico, BorderLayout.CENTER);

		contentPane.revalidate();
		contentPane.repaint();
	}

	/**
     * @method getBtnVolver
     * @description Exposes the operational visual interaction node deployed to clear current statistics and revert display hierarchies.
     * @returns {JButton} Navigation fallback trigger component reference.
     */
	public JButton getBtnVolver() {
		return btnVolver;
	}

	/**
     * @method setBtnVolver
     * @description Installs the explicit visual interaction node intended to dismiss analytics menus.
     * @param {JButton} btnVolver - Target navigational rollback button asset.
     */
	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

}