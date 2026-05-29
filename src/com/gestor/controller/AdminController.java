package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.gestor.model.entity.SummaryOrders;
import com.gestor.model.entity.Table;
import com.gestor.service.OrderService;
import com.gestor.service.TableService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.FormCrearProducto;
import com.gestor.view.admin.GestorMesasView;
import com.gestor.view.admin.ListUsersView;
import com.gestor.view.admin.StatsAdminProducts;
import com.gestor.view.admin.ViewSummaryBook;
import com.gestor.view.admin.signupadminView;
import com.gestor.view.admin.AdminTableDetailView;

/**
 * @class AdminController
 * @description Controller layer architectural component managing administrative system coordination, 
 * UI dashboard events, multi-view redirection routing pipelines, and graphical layout status state synchronization.
 */
public class AdminController {

	/**
     * @private
     * @type {AdminMainView}
     */
	private AdminMainView view;

	/**
     * @private
     * @type {TableService}
     */
	private TableService ms;

	/**
     * @private
     * @type {OrderService}
     */
	private OrderService os;

	/**
     * @constructor
     * @description Initializes the structural dashboard administrative lifecycle controller context 
     * mapping data stream operations, listeners orchestration, and view telemetry state setup.
     * @param {AdminMainView} v - Main infrastructure user interface control panel reference container.
     */
	public AdminController(AdminMainView v) {

		this.view = v;
		this.ms = new TableService();
		this.os = new OrderService();

		updateTableColors();
		setupTableListeners();

		view.getBtnCreateTable().addActionListener(e -> createTable());
		view.getBtnNewAdmin().addActionListener(e -> createNewAdmin());
		view.getBtnEmptyAllTables().addActionListener(e -> deleteTable());
		view.getBtnNewProduct().addActionListener(e -> createProduct());
		view.getBtnStats().addActionListener(e -> checkStats());
		view.getBtnListUsers().addActionListener(e -> listUsers());
		view.getBtnSummary().addActionListener(e -> summaryBooks());
	}

	public AdminMainView getAdminView() {
		return view;
	}

	/**
     * @method setupTableListeners
     * @private
     * @description Dynamic iteration mapping assigning real-time event triggers to physical desktop UI buttons, 
     * coordinating contextual mapping to detailed modal summary overlays if state collisions match active occupancy.
     */
	private void setupTableListeners() {
		List<JButton> buttons = view.getTablesList();
		List<Table> tablesDB = ms.getCreatedTables();

		for (int i = 0; i < buttons.size() && i < tablesDB.size(); i++) {
			JButton btnTable = buttons.get(i);
			Table table = tablesDB.get(i);
			int idMesa = table.getId();
			String tableName = table.getName();

			btnTable.addActionListener(e -> {
				if (Color.RED.equals(btnTable.getBackground())) {
					List<SummaryOrders> orders = os.getOrderDetailsByTable(idMesa);

					AdminTableDetailView detailView = new AdminTableDetailView(view, tableName, orders);

					detailView.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(view,
							"La " + tableName + " está libre actualmente. No hay pedidos que mostrar.", "Mesa Libre",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	}

	/**
     * @method summaryBooks
     * @description Dismantles active visualization frameworks to trigger the routing pipeline redirection 
     * towards reservation structural overview sheets and operational booking logging panels.
     */
	public void summaryBooks() {
		view.dispose();
		ViewSummaryBook v = new ViewSummaryBook();
		v.setVisible(true);
		new SummaryController(v);
	}

	/**
     * @method listUsers
     * @description Disposes current contextual parent windows to handle user database registries management tracking layout sub-systems.
     */
	public void listUsers() {
		view.dispose();
		ListUsersView v = new ListUsersView();
		v.setVisible(true);
		new ListUserController(v);
	}

	/**
     * @method checkStats
     * @description Routes administrative data telemetry pipelines to the visualization interface tracking platform data summaries.
     */
	public void checkStats() {
		view.dispose();
		StatsAdminProducts v = new StatsAdminProducts();
		new StatsController(v);
		v.setVisible(true);
	}

	/**
     * @method createProduct
     * @description Disposes active graphical modules to instantiate the catalog administration input wizard form view.
     */
	public void createProduct() {
		view.dispose();
		FormCrearProducto v = new FormCrearProducto();
		v.setVisible(true);
		new CreateProductController(this, v);
	}

	/**
     * @method deleteTable
     * @description Triggers global deletion routines across operational inventory maps, purging states before calling local clear reset processes.
     */
	public void deleteTable() {
		
		int response=JOptionPane.showConfirmDialog(null, "¿Deseas borrar todas las mesas?");
		if(response==0) {
			ms.deleteTable();
			resetTables();
		}
	}

	/**
     * @method resetTables
     * @description Standardizes layout elements structural tracking by resetting dynamic interface styling values to core presets.
     */
	public void resetTables() {
		List<JButton> buttons = view.getTablesList();

		for (int i = 0; i < buttons.size(); i++) {
			JButton button = buttons.get(i);
			button.setBackground(null);
		}
	}

	/**
     * @method updateTableColors
     * @description Evaluates relational physical inventory telemetry states across target database entities 
     * and maps layout component color thresholds (RED/GREEN) to signify allocation changes.
     */
	public void updateTableColors() {
		List<JButton> buttons = view.getTablesList();
		List<Table> tablesDB = ms.getCreatedTables();

		for (int i = 0; i < buttons.size() && i < tablesDB.size(); i++) {
			JButton button = buttons.get(i);
			Table table = tablesDB.get(i);

			if (table.isBooked()) {
				button.setBackground(Color.RED);
			} else {
				button.setBackground(Color.GREEN);
			}
		}
		view.repaint();
	}

	/**
     * @method getAdminView
     * @description Exposes structural container views parent reference.
     * @returns {AdminMainView} The current active frame view panel element mapping.
     */
	public void createTable() {
		view.setVisible(false);
		GestorMesasView v = new GestorMesasView();
		v.setVisible(true);
		new TableMenuController(v, this);
	}

	/**
     * @method createNewAdmin
     * @description Transitions current navigation contexts into background queues to establish registration pipelines for identity authorization units.
     */
	public void createNewAdmin() {
		view.setVisible(false);
		signupadminView v = new signupadminView();
		v.setVisible(true);
		new SignupAdminController(v, this);
	}
}