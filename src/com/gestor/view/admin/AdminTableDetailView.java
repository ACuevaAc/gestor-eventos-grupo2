package com.gestor.view.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.SummaryOrders;

import java.awt.*;
import java.util.List;

public class AdminTableDetailView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable productsTable;
	private DefaultTableModel tableModel;
	private JLabel lblTotal;

	public AdminTableDetailView(JFrame parentFrame, String tableName, List<SummaryOrders> orders) {
		super(parentFrame, "Detalles de la " + tableName, true); // true = Modal

		setSize(500, 600);
		setLocationRelativeTo(parentFrame);
		setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(new Color(248, 249, 250));

		JLabel lblTitle = new JLabel("Ticket actual: " + tableName, SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
		add(lblTitle, BorderLayout.NORTH);

		String[] columnNames = { "Producto", "Cantidad", "Precio Total (€)" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Evita que el admin edite las celdas escribiendo
			}
		};
		productsTable = new JTable(tableModel);
		productsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		productsTable.setRowHeight(25);
		productsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(productsTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setOpaque(false);
		bottomPanel.setBorder(new EmptyBorder(15, 20, 20, 20));

		lblTotal = new JLabel("Total a pagar: 0.00 €");
		lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotal.setForeground(new Color(220, 38, 38));
		bottomPanel.add(lblTotal);

		add(bottomPanel, BorderLayout.SOUTH);

		loadData(orders);
	}

	private void loadData(List<SummaryOrders> orders) {
		double grandTotal = 0.0;

		for (SummaryOrders order : orders) {
			Object[] row = { order.getProductName(), order.getQuantity(),
					String.format("%.2f", order.getTotalProductPrice()) };
			tableModel.addRow(row);

			grandTotal += order.getTotalProductPrice();
		}

		lblTotal.setText(String.format("Total a pagar: %.2f €", grandTotal));
	}
}