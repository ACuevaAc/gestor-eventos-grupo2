package com.gestor.view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;

public class AdminMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Color actionColor = new Color(51, 65, 85);
	private final Font tableFont = new Font("Segoe UI", Font.BOLD, 18);
	private final Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);
	
	private JButton btnNewProduct;
	private JButton btnNewAdmin;
	private JButton btnCreateTable;
	private JButton btnEmptyAllTables;
	private JButton btnStats;
	private JButton btnListUsers;
	private JButton btnSummary;
	
	private List<JButton> tablesList = new ArrayList<>();
	private JPanel SearchPanel;
	private JTextField FilterTXT;

	public AdminMainView() {
		super("Administrador de mesas");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1050, 1050);
		setMinimumSize(new Dimension(1050, 1050));
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(backgroundColor);
		contentPane.setBorder(new EmptyBorder(30, 50, 30, 50));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		JPanel tablesPanel = new JPanel(new GridLayout(4, 1, 0, 20));
		tablesPanel.setOpaque(false);

		int tablesCount = 1;
		int[] schema = { 3, 2, 3, 2 }; 

		for (int tablesNumber : schema) {
			JPanel file = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
			file.setOpaque(false);
			for (int i = 0; i < tablesNumber; i++) {
				JButton btn = createOvalButton ("Mesa " + tablesCount++);
				tablesList.add(btn);
				file.add(btn);
			}
			tablesPanel.add(file);
		}

		contentPane.add(tablesPanel, BorderLayout.CENTER);
		updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
			}
		});

		// Panel lateral izquierdo
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.Y_AXIS));
		actionPanel.setOpaque(false);
		actionPanel.setBorder(new EmptyBorder(10, 0, 10, 40));

		btnCreateTable = new JButton("Create Table");
		stabilizeAction(btnCreateTable);
		btnCreateTable.setToolTipText("Crea nueva mesa del restaurante");
		
		btnNewAdmin = new JButton("New Admin");
		btnNewAdmin.setToolTipText("Crea un nuevo administrador");
		stabilizeAction(btnNewAdmin);
		
		btnEmptyAllTables = new JButton("Empty All Tables");
		stabilizeAction(btnEmptyAllTables);
		btnEmptyAllTables.setToolTipText("Limpia todas las mesas del restaurante");
		
		btnNewProduct = new JButton("Create New Product");
		btnNewProduct.setToolTipText("Crea un nuevo producto a la carta");
		stabilizeAction(btnNewProduct);
		
		btnStats = new JButton("Stats");
		btnStats.setToolTipText("Comprueba los graficos de las estadisticas");
		stabilizeAction(btnStats);
		
		btnListUsers = new JButton("Users");
		btnListUsers.setToolTipText("Busca/Elimina usuarios ");
		stabilizeAction(btnListUsers);
		
		btnSummary = new JButton("Summary books");
		btnSummary.setToolTipText("Resumen de las reservas");
		stabilizeAction(btnSummary);
	
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnCreateTable);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnNewAdmin);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnEmptyAllTables);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnNewProduct);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnStats);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnListUsers);
		actionPanel.add(Box.createVerticalGlue());
		actionPanel.add(btnSummary);
		actionPanel.add(Box.createVerticalGlue());
		
		contentPane.add(actionPanel, BorderLayout.WEST);
	
		SearchPanel = new JPanel();
		contentPane.add(SearchPanel, BorderLayout.NORTH);
		SearchPanel.setLayout(new BorderLayout(0, 0));
		
		FilterTXT = new JTextField();
		FilterTXT.setToolTipText("Busca la mesa por su nombre");
		FilterTXT.setColumns(10);
		FilterTXT.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());				
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());								
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				FilterTables(FilterTXT.getText());				
			}
		});
		SearchPanel.add(FilterTXT);
	}
	
	public JButton getBtnSummary() {
		return btnSummary;
	}
	
	public JButton getBtnListUsers() {
		return btnListUsers;
	}

	public JButton getBtnStats() {
		return btnStats;
	}

	public void setBtnStats(JButton btnStats) {
		this.btnStats = btnStats;
	}

	public JButton getBtnNewProduct() {
		return btnNewProduct;
	}

	public void setBtnNewProduct(JButton btnNewProduct) {
		this.btnNewProduct = btnNewProduct;
	}

	public JButton getBtnEmptyAllTables() {
		return btnEmptyAllTables;
	}

	public void setBtnEmptyAllTables(JButton btnEmptyAllTables) {
		this.btnEmptyAllTables = btnEmptyAllTables;
	}
	
	private void FilterTables(String text) {
		String filter = text.toLowerCase().trim();

		for (JButton btn : tablesList) {
			String nombreMesa = btn.getText().toLowerCase();

			if (nombreMesa.contains(filter)) {
				btn.setVisible(true);  
			} else {
				btn.setVisible(false); 
			}
		}

		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void updateTablesSize (int widthPanel, int heightPanel) {
		int horizontalGap = 30;
		int width = (widthPanel - (horizontalGap * 4)) / 3;
		int height = (heightPanel - (20 * 4)) / 5;

		width = Math.max(120, Math.min(width, 250));
		height = Math.max(70, Math.min(height, 120));

		Dimension newDimension = new Dimension(width, height);

		for (JButton btn : tablesList) {
			btn.setPreferredSize(newDimension);
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

	public List<JButton> getTablesList() {
		return tablesList;
	}

	public void setTablesList(List<JButton> tablesList) {
		this.tablesList = tablesList;
	}

	private JButton createOvalButton(String text) {
		JButton btn = new JButton(text);
		btn.setFont(tableFont);
		btn.setForeground(Color.BLACK);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setFocusPainted(false);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 999);
		return btn;
	}

	private void stabilizeAction(JButton btn) {
		Dimension d = new Dimension(200, 50);
		btn.setPreferredSize(d);
		btn.setMaximumSize(d);
		btn.setMinimumSize(d);
		btn.setAlignmentX(Box.LEFT_ALIGNMENT);
		
		btn.setFont(buttonFont);
		btn.setBackground(actionColor);
		btn.setForeground(Color.WHITE);
		btn.putClientProperty("JButton.buttonType", "roundRect");
		btn.putClientProperty("JButton.cornerRadius", 20);
	}
}