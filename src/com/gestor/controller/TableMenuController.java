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
		
		ms= new TableService();
		cargarMesas();
		v.getBtnRegistrarModificar().addActionListener(e-> registrarMesa());
		v.getBtnAtras().addActionListener(e-> volverMenu());
		
		
	}
	public void volverMenu() {
		view.dispose();
		AdminMainView view=aCont.getAdminView();
		view.setVisible(true);
		
		aCont.actualizarColoresMesas();
	}
	public void registrarMesa() {
		String nom=view.getTxtNombre().getText();
		int num_max=(int) view.getcBgente().getSelectedItem();
		Table mesa=new Table();
		mesa.setMax(num_max);
		mesa.setName(nom);
		mesa.setBooked(false);
		ms.createTable(mesa);
		
		aCont.actualizarColoresMesas();
	}
	public void cargarMesas() {
	    List<JButton> lista = aCont.getAdminView().getTablesList();
	    int totalMesas = ms.getTableIds();
	    for (JButton boton : lista) {
	        boton.setBackground(null); 
	    }
	    int limite = Math.min(totalMesas, lista.size());
	    for (int i = 0; i < limite; i++) {
	        lista.get(i).setBackground(Color.GREEN);
	    }
	    aCont.getAdminView().repaint();
	    if (totalMesas > lista.size()) {
	        System.out.println("LOG GestionMesasController: Hay " + totalMesas + " mesas en la BD, pero solo " + lista.size() + " botones en la pantalla.");
	    }
	}



}
