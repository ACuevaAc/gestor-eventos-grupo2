package com.gestor.controller;

import java.awt.Color;
import java.util.List;

import javax.swing.JButton;

import com.gestor.model.entity.Table;
import com.gestor.service.TableService;
import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.GestorMesasView;

public class TableMenuController {
	private GestorMesasView view;
	private TableService ms;
	private AdminController aCont;
	
	public TableMenuController() {
		
	}
	public TableMenuController(GestorMesasView v,AdminController cont) {
		
		this.view=v;
		this.aCont=cont;
		
		ms = new TableService();
		loadTables();
		v.getBtnRegistrarModificar().addActionListener(e-> registerTable());
		v.getBtnAtras().addActionListener(e-> goBackMenu());
	}
	public void goBackMenu() {
		view.dispose();
		AdminMainView view = aCont.getAdminView();
		view.setVisible(true);
		
		aCont.updateTableColors();
	}
	public void registerTable() {
		String nom = view.getTxtNombre().getText();
		int num_max = (int) view.getcBgente().getSelectedItem();
		Table table = new Table();
		table.setMax(num_max);
		table.setName(nom);
		table.setBooked(false);
		ms.createTable(table);
		
		aCont.updateTableColors();
	}

	public void loadTables() {
	    List<JButton> list = aCont.getAdminView().getTablesList();
	    int totalTables = ms.getTableIds();
	    for (JButton button : list) {
	        button.setBackground(null); 
	    }
	    int limite = Math.min(totalTables, list.size());
	    for (int i = 0; i < limite; i++) {
	        list.get(i).setBackground(Color.GREEN);
	    }
	    aCont.getAdminView().repaint();
	    if (totalTables > list.size()) {
	        System.out.println("LOG GestionMesasController: Hay " + totalTables + " mesas en la BD, pero solo " + list.size() + " botones en la pantalla.");
	    }
	}



}
