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

public class StatsAdminProducts extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnVolver;
	private ChartPanel panelGrafico;

	/**
	 * Create the frame.
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

	public JButton getBtnVolver() {
		return btnVolver;
	}

	public void setBtnVolver(JButton btnVolver) {
		this.btnVolver = btnVolver;
	}

}