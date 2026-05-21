package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatLightLaf;

public class AdminMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final Color COLOR_FONDO = new Color(248, 249, 250);
	private final Color COLOR_MESA = new Color(245, 158, 11);
	private final Color COLOR_ACCION = new Color(51, 65, 85);
	private final Font FUENTE_MESAS = new Font("Segoe UI", Font.BOLD, 18);
	private final Font FUENTE_BOTONES = new Font("Segoe UI", Font.BOLD, 14);

	private JButton btnNewAdmin;
	private JButton btnCreateTable;
	private JButton btnEmptyAllTables;
	private List<JButton> mesasList = new ArrayList<>();

	public AdminMainView() {
		super("Administrador de mesas");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(850, 850);
		setMinimumSize(new Dimension(650, 750));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(COLOR_FONDO);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel mesasPanel = new JPanel(new GridLayout(4, 1, 0, 20));
		mesasPanel.setOpaque(false);

		int contadorMesas = 1;
		int[] esquema = { 3, 2, 3, 2 }; 

		for (int numMesas : esquema) {
			JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
			fila.setOpaque(false);
			for (int i = 0; i < numMesas; i++) {
				JButton btn = crearBotonOvalado("Mesa " + contadorMesas++);
				mesasList.add(btn);
				fila.add(btn);
			}
			mesasPanel.add(fila);
		}
		contentPane.add(mesasPanel, BorderLayout.CENTER);
		actualizarTamanoMesas(mesasPanel.getWidth(), mesasPanel.getHeight());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				actualizarTamanoMesas(mesasPanel.getWidth(), mesasPanel.getHeight());
			}
		});

		JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 40));
		actionPanel.setOpaque(false);

		btnCreateTable = new JButton("Create Table");
		estilizarAccion(btnCreateTable);
		btnNewAdmin = new JButton("New Admin");
		estilizarAccion(btnNewAdmin);
		btnEmptyAllTables = new JButton("Empty All Tables");
		estilizarAccion(btnEmptyAllTables);

		actionPanel.add(btnCreateTable);
		actionPanel.add(btnNewAdmin);
		actionPanel.add(btnEmptyAllTables);
		contentPane.add(actionPanel, BorderLayout.SOUTH);
	}

	public JButton getBtnEmptyAllTables() {
		return btnEmptyAllTables;
	}

	public void setBtnEmptyAllTables(JButton btnEmptyAllTables) {
		this.btnEmptyAllTables = btnEmptyAllTables;
	}

	private void actualizarTamanoMesas(int anchoPanel, int altoPanel) {
		int gapHorizontal = 30;
		int anchoIdeal = (anchoPanel - (gapHorizontal * 4)) / 3;

		int altoIdeal = (altoPanel - (20 * 4)) / 5;

		anchoIdeal = Math.max(120, Math.min(anchoIdeal, 250));
		altoIdeal = Math.max(70, Math.min(altoIdeal, 120));

		Dimension nuevaDimension = new Dimension(anchoIdeal, altoIdeal);

		for (JButton btn : mesasList) {
			btn.setPreferredSize(nuevaDimension);
		}

		contentPane.revalidate();
	}

	public JButton getBtnNewAdmin() {
		return btnNewAdmin;
	}

	public void setBtnNewAdmin(JButton btnNewAdmin) {
		this.btnNewAdmin = btnNewAdmin;
	}

	public JButton getBtnCreateTable() {
		return btnCreateTable;
	}

	public void setBtnCreateTable(JButton btnCreateTable) {
		this.btnCreateTable = btnCreateTable;
	}

	public List<JButton> getMesasList() {
		return mesasList;
	}

	public void setMesasList(List<JButton> mesasList) {
		this.mesasList = mesasList;
	}

	private JButton crearBotonOvalado(String texto) {
		JButton btn = new JButton(texto);
		btn.setFont(FUENTE_MESAS);
		//btn.setBackground(COLOR_MESA);
		btn.setForeground(Color.BLACK);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setFocusPainted(false);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 999);
		return btn;
	}

	private void estilizarAccion(JButton btn) {
		btn.setPreferredSize(new Dimension(200, 50));
		btn.setFont(FUENTE_BOTONES);
		btn.setBackground(COLOR_ACCION);
		btn.setForeground(Color.WHITE);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 20);
	}

	
}