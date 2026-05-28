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

public class AdminController {

	private AdminMainView view;
	private TableService ms;
	private OrderService os;

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

	public void summaryBooks() {
		view.dispose();
		ViewSummaryBook v = new ViewSummaryBook();
		v.setVisible(true);
		new SummaryController(v);
	}

	public void listUsers() {
		view.dispose();
		ListUsersView v = new ListUsersView();
		v.setVisible(true);
		new ListUserController(v);
	}

	public void checkStats() {
		view.dispose();
		StatsAdminProducts v = new StatsAdminProducts();
		new StatsController(v);
		v.setVisible(true);
	}

	public void createProduct() {
		view.dispose();
		FormCrearProducto v = new FormCrearProducto();
		v.setVisible(true);
		new CreateProductController(this, v);
	}

	public void deleteTable() {
		ms.deleteTable();
		resetTables();
	}

	public void resetTables() {
		List<JButton> buttons = view.getTablesList();

		for (int i = 0; i < buttons.size(); i++) {
			JButton button = buttons.get(i);
			button.setBackground(null);
		}
	}

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

	public AdminMainView getAdminView() {
		return view;
	}

	public void createTable() {
		view.setVisible(false);
		GestorMesasView v = new GestorMesasView();
		v.setVisible(true);
		new TableMenuController(v, this);
	}

	public void createNewAdmin() {
		view.setVisible(false);
		signupadminView v = new signupadminView();
		v.setVisible(true);
		new SignupAdminController(v, this);
	}
}