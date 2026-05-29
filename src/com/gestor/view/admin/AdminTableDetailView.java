package com.gestor.view.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.SummaryOrders;

import java.awt.*;
import java.util.List;

/**
 * @class AdminTableDetailView
 * @description Administrative modal dialog interface wrapper displaying real-time billing breakdowns.
 * Renders itemized product lists, total volume counts, and granular financial thresholds inside structured tables
 * mapping data states from custom view projections.
 */
public class AdminTableDetailView extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTable productsTable;
	private DefaultTableModel tableModel;
	private JLabel lblTotal;

	/**
     * @constructor
     * @description Constructs the modal detailed invoice view dashboard container, establishing modal focus boundaries,
     * building the visual list structures, configuring cell tracking constraints, and triggering local dataset injection hooks.
     * @param {JFrame} parentFrame - Root parent cockpit viewport frame managing context focus constraints.
     * @param {String} tableName - The descriptive layout title representing the targeted floor plan asset.
     * @param {List<SummaryOrders>} orders - Consolidated structural line-item projection collection payload.
     */
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
			/**
             * @method isCellEditable
             * @description Overrides standard table models parameters to isolate cells from structural manual data entry alterations.
             * @param {int} row - Horizontal sequence tracking entry coordinates.
             * @param {int} column - Vertical field type positioning index coordinates.
             * @returns {boolean} Absolute boolean false constraint preventing manual cell modifications.
             */
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

	/**
     * @method loadData
     * @private
     * @description Processes execution logic to populate grid layouts, running loops across entity lists,
     * formatting text numeric currencies into explicit precision formats, and calculating final balance summaries.
     * @param {List<SummaryOrders>} orders - Target data collection array holding data projection models.
     */
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