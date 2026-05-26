package com.gestor.view.admin;

import java.awt.BorderLayout;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		btnVolver = new JButton("Volver");
		panelBotones.add(btnVolver);
	}


	public void cargarGrafico(JFreeChart grafico) {
		if (panelGrafico != null) {
			contentPane.remove(panelGrafico);
		}

		panelGrafico = new ChartPanel(grafico);
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