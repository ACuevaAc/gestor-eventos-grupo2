
package com.gestor.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gestor.model.entity.SummaryOrders;
import com.gestor.model.entity.User;
import com.gestor.service.BookService;
import com.gestor.service.OrderService;
import com.gestor.service.TableService;

/**
 * @class UserPaymentSummaryDialog
 * @description Operational modal dialog framing individual billing processing routines and transaction details summaries.
 * Binds explicit line-item consumption models onto immutable tabulated interfaces, calculates global financial totals,
 * and executes cascading state rollbacks across booking registers, table allocation records, and active window containers.
 */
public class UserPaymentSummaryDialog extends JDialog {

	private JTable productsTable;
	private DefaultTableModel tableModel;
	private JLabel lblTotal;
	private JButton btnConfirmPay;

	private TableService tableService;
	private BookService bookService;
	private OrderService orderService;
	private int tableId;
	private JFrame parentFrame;
	private User user;

	/**
     * @constructor
     * @description Constructs the modal balance review dialog box, mapping contextual frame ownership matrices, 
     * initializing underlying storage layout frameworks, configuring isolated table cell behaviors, 
     * setting structural currency labels, and populating row values from summary indices.
     * @param {JFrame} parentFrame - The supervisor view layout tracking element context.
     * @param {int} tableId - Target physical asset location unique sequence index.
     * @param {User} user - Active authorization credential profile token reference.
     * @param {List<SummaryOrders>} orders - Collection containing structured consumer order summaries data models.
     */
	public UserPaymentSummaryDialog(JFrame parentFrame, int tableId, User user, List<SummaryOrders> orders) {
		super(parentFrame, "Finalizar Pedido & Cuenta", true);
		this.bookService = new BookService();
		this.tableService = new TableService();
		this.parentFrame = parentFrame;
		this.tableId = tableId;
		this.user = user;
		this.orderService = new OrderService();

		setSize(500, 650);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(new Color(248, 249, 250));

		JLabel lblTitle = new JLabel("Resumen de tu Cuenta", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
		lblTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
		add(lblTitle, BorderLayout.NORTH);

		String[] columnNames = { "Producto", "Cantidad", "Total (€)" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productsTable = new JTable(tableModel);
		productsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		productsTable.setRowHeight(25);
		productsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(productsTable);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
		add(scrollPane, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel(new BorderLayout(0, 15));
		bottomPanel.setOpaque(false);
		bottomPanel.setBorder(new EmptyBorder(15, 20, 20, 20));

		lblTotal = new JLabel("Total a pagar: 0.00 €");
		lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblTotal.setForeground(new Color(37, 99, 235));
		bottomPanel.add(lblTotal, BorderLayout.NORTH);

		btnConfirmPay = new JButton("Confirmar Pago y Terminar");
		btnConfirmPay.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnConfirmPay.setBackground(new Color(22, 163, 74));
		btnConfirmPay.setForeground(Color.WHITE);
		btnConfirmPay.setPreferredSize(new Dimension(250, 45));
		btnConfirmPay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnConfirmPay.putClientProperty("JButton.buttonType", "roundRect");
		btnConfirmPay.putClientProperty("JButton.cornerRadius", 15);

		btnConfirmPay.addActionListener(e -> processPayment());

		JPanel btnWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnWrapper.setOpaque(false);
		btnWrapper.add(btnConfirmPay);
		bottomPanel.add(btnWrapper, BorderLayout.SOUTH);

		add(bottomPanel, BorderLayout.SOUTH);

		loadData(orders);
	}

	/**
     * @method loadData
     * @private
     * @description Parses order history summaries records, dynamically appending formatted visual metadata metrics 
     * onto data grid models while tallying global monetary constraints.
     * @param {List<SummaryOrders>} orders - Source collection payload mapped for rendering execution.
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

	/**
     * @method processPayment
     * @private
     * @description Intercepts finalization interaction sequences, dispatching visual confirmations before firing 
     * state erasures, purging operational table orders logs, dropping reservation dependencies, and safely recycling component windows.
     */
	private void processPayment() {
		int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmas que deseas realizar el pago y abandonar la mesa?",
				"Proceder al Pago", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (confirm == JOptionPane.YES_OPTION) {
			orderService.deleteTableOrder(tableId);

			JOptionPane.showMessageDialog(this, "¡Pago procesado con éxito!\nGracias por su visita.", "Ticket Cerrado",
					JOptionPane.INFORMATION_MESSAGE);

			orderService.deleteTableOrder(tableId);

			bookService.deleteFromBooking(tableId);
			
			tableService.releaseTable(tableId);
			this.dispose();
			if (parentFrame != null) {
				parentFrame.dispose();
			}

			UserMainView refreshMap = new UserMainView(user);
			refreshMap.setVisible(true);
		}
	}
}
