package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Table;
import com.gestor.service.TableService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.GestorMesasView;

/**
 * @class TableMenuController
 * @description Controller architectural component managing the structural table setup inventory sub-system,
 * handling input extraction parsing, inventory metric updates, dynamic grid UI baseline initialization,
 * and navigation redirection routines.
 */
public class TableMenuController {
	private GestorMesasView view;
	private TableService ms;
	private AdminController aCont;
	
	/**
     * @constructor
     * @description Initializes the contextual layout creation and configuration wizard flow,
     * building core downstream services and hooking action triggers.
     * @param {GestorMesasView} v - Dynamic creation management frame view interface container.
     * @param {AdminController} cont - Main orchestration administrative context tracking parent synchronization states.
     */
	public TableMenuController() {}
	public TableMenuController(GestorMesasView v,AdminController cont) {
		
		this.view=v;
		this.aCont=cont;
		
		ms = new TableService();
		loadTables();
		v.getBtnRegistrarModificar().addActionListener(e-> registerTable());
		v.getBtnAtras().addActionListener(e-> goBackMenu());
	}

	/**
     * @method goBackMenu
     * @description Terminates active creation layout nodes to restore focus parameters 
     * on parent management dashboards while triggering a panel refresh.
     */
	public void goBackMenu() {
		view.dispose();
		AdminMainView view = aCont.getAdminView();
		view.setVisible(true);
		
		aCont.updateTableColors();
	}

	/**
     * @method registerTable
     * @description Extracts raw asset attributes from selection boxes, initializes the operational data model,
     * routes entity data models to persistence layers, and executes structural view refreshes.
     */
	public void registerTable() {
		String nom = view.getTxtNombre().getText();
		int num_max = (int) view.getcBgente().getSelectedItem();
		Table table = new Table();
		table.setMax(num_max);
		table.setName(nom);
		table.setBooked(false);
		ms.createTable(table);
		view.dispose();
		AdminMainView v=new AdminMainView();
		v.setVisible(true);
		new AdminController(v);
		aCont.updateTableColors();
	}

	/**
     * @method loadTables
     * @description Synchronizes system entity tracking structures by resetting graphical grid elements, 
     * calculating bounds dynamically, updating allocation indices, and evaluating component mismatches.
     */
	public void loadTables() {
	    List<JButton> list = aCont.getAdminView().getTablesList();
	    int totalTables = ms.getTableIds();
	    for (JButton button : list) {
	        button.setBackground(null); 
	    }
	    int limit = Math.min(totalTables, list.size());
	    for (int i = 0; i < limit; i++) {
	        list.get(i).setBackground(Color.GREEN);
	    }
	    aCont.getAdminView().repaint();
	    if (totalTables > list.size()) {
	        System.out.println("LOG GestionMesasController: Hay " + totalTables + " mesas en la BD, pero solo " + list.size() + " botones en la pantalla.");
	    }
	}



}
