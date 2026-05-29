package com.gestor.view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.gestor.controller.UserTableController;
import com.gestor.model.entity.Table;
import com.gestor.model.entity.User;
import com.gestor.service.BookService;
import com.gestor.service.TableService;

/**
 * @class UserMainView
 * @description Primary client dashboard presentation layer providing an interactive structural grid floor plan viewport.
 * Fetches layout asset tracking catalogs, processes transactional booking requests with real-time capacity thresholds checks, 
 * maps stateful visual color codes matching account privileges, triggers proportional matrix resizing recalculations,
 * and tracks string filtering inputs to selectively toggle button visibility matrices.
 */
public class UserMainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private BookService rs;
	private TableService ms;
	private JComboBox<Integer> cb;

	private final Color backgroundColor = new Color(248, 249, 250);
	private final Font tableFont = new Font("Segoe UI", Font.BOLD, 18);

	private JPanel SearchPanel;
	private JTextField FilterTXT;
	private List<JButton> tablesList = new ArrayList<>();

	/**
     * @constructor
     * @description Initializes the core floor plan matrix, binds local business logic engines, maps multi-tier 
     * structural layout schema nodes, instantiates interactive button entities wrapped inside conditional transaction boundaries, 
     * and installs structural text input monitors and window resize tracking hooks.
     * @param {User} user - The active customer session profile mapping execution scopes.
     */
	public UserMainView(User user) {

		super("Reservar mesas");

		this.rs = new BookService();
		this.ms = new TableService();

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
		List<Table> tables = ms.getCreatedTables();

		int[] scheme = { 3, 2, 3, 2 };
		int tableIndex = 0;

		for (int numMesas : scheme) {
			JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
			row.setOpaque(false);

			for (int s = 0; s < numMesas && tableIndex < tables.size(); s++) {
				Table table = tables.get(tableIndex++);
				JButton btn = createOvalButton(table.getName());
				btn.putClientProperty("idMesa", table.getId());
				int userId = user.getId();
				int actualTableID = (int) btn.getClientProperty("idMesa");

				if (table.isBooked()) {
					btn.setBackground(Color.RED);
				} else {
					btn.setBackground(Color.GREEN);
				}
				SwingUtilities.invokeLater(() -> {
					if (rs.isUserAlreadyBooked(userId, actualTableID)) {
						btn.setBackground(Color.BLUE);
					}
				});

				btn.addActionListener(e -> {
					JButton button = (JButton) e.getSource();
					int tableId = (int) button.getClientProperty("idMesa");
					if (button.getBackground().equals(Color.GREEN)) {
						int option = JOptionPane.showConfirmDialog(this, "¿Reservar " + table.getName() + "?",
								"Reserva", JOptionPane.YES_NO_OPTION);
						int[] Quantity = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
						cb = new JComboBox<Integer>();
						for (int j = 0; j < 9; j++) {
							cb.addItem(Quantity[j]);
						}
						int selectedOptionQuantity = JOptionPane.showConfirmDialog(null, cb,
								"Selecciona cuantas personas sois", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE);

						if (option == JOptionPane.YES_OPTION) {
							if (selectedOptionQuantity == JOptionPane.OK_OPTION) {
								int QuantitySelected = (int) cb.getSelectedItem();
								int maxQuantityforTable = ms.getMaxQuantity(tableId);
								if (QuantitySelected > maxQuantityforTable) {
									JOptionPane.showMessageDialog(this,
											"Lo sentimos pero esta mesa solo se pueden reservar hasta "
													+ maxQuantityforTable + " personas");
									return;
								}
							}
							rs.makeReservation(user.getId(), tableId, LocalDateTime.now());
							ms.bookTable(tableId);
							button.setBackground(Color.BLUE); 
							JOptionPane.showMessageDialog(this, "Mesa reservada correctamente");
							dispose();
							ListaDeProductos v = new ListaDeProductos();
							v.setVisible(true);
							new UserTableController(v, tableId, user);

						}
					} else {

						boolean sameuserBooked = rs.isUserAlreadyBooked(userId, tableId);
						if (sameuserBooked == true) {
							dispose();
							ListaDeProductos v = new ListaDeProductos();
							v.setVisible(true);
							new UserTableController(v, tableId, user);
						} else {
							JOptionPane.showMessageDialog(null, "Esta no es tu reserva", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});

				tablesList.add(btn);
				row.add(btn);
			}

			tablesPanel.add(row);
		}

		contentPane.add(tablesPanel, BorderLayout.CENTER);
		updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				updateTablesSize(tablesPanel.getWidth(), tablesPanel.getHeight());
			}
		});

		SearchPanel = new JPanel();
		contentPane.add(SearchPanel, BorderLayout.NORTH);
		SearchPanel.setLayout(new BorderLayout(0, 0));

		FilterTXT = new JTextField();
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

	/**
     * @method updateTablesSize
     * @private
     * @description Recalculates dimensional scale indices relative to parent canvas size configurations, 
     * bounding absolute geometric ratios and enforcing safe proportional thresholds across registered button arrays.
     * @param {int} widthPanel - Dynamic width boundaries of the active layout tracking container.
     * @param {int} heightPanel - Dynamic height boundaries of the active layout tracking container.
     */
	private void updateTablesSize(int widthPanel, int heightPanel) {
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

	/**
     * @method getTablesList
     * @description Returns the core tracking collection holding registered structural floor button elements.
     * @returns {List<JButton>} Dynamic vector list holding system button handles.
     */
	public List<JButton> getTablesList() {
		return tablesList;
	}

	/**
     * @method setTablesList
     * @description Replaces or registers a collection array mapping target visual dashboard asset triggers.
     * @param {List<JButton>} tablesList - Source list array holding layout tracking button references.
     */
	public void setTablesList(List<JButton> tablesList) {
		this.tablesList = tablesList;
	}

	/**
     * @method FilterTables
     * @private
     * @description Iterates through stored button arrays evaluating current nomenclature labels against matching
     * lowercase query strings, selectively toggling element visibility layers.
     * @param {String} text - Alphanumeric lookup criterion string token.
     */
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

	/**
     * @method createOvalButton
     * @private
     * @description Builds a structured custom button instance configured with precise typeface settings,
     * hand cursor vectors, focus border suppressions, and native round-rect parameters.
     * @param {String} text - Descriptive labeling string destined for visual rendering.
     * @returns {JButton} Formatted button component instance wrapper.
     */
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
}