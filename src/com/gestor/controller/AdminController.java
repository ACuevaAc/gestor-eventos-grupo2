package com.gestor.controller;

import com.gestor.view.admin.AdminMainView;
import com.gestor.view.admin.GestorMesasView;

public class AdminController {
	private AdminMainView view;
	
	
	public AdminController(AdminMainView v) {
		this.view=v;
		view.getBtnCreateTable().addActionListener(e-> crearMesa());
		view.getBtnNewAdmin().addActionListener(e-> createNewAdmin());
	}
	public AdminMainView getAdminView() {
		return view;
	}
	public void crearMesa() {
		view.setVisible(false);
		GestorMesasView v=new GestorMesasView();
		v.setVisible(true);
		new gestorMesasController(v,this);
	}
	public void createNewAdmin() {
		
	}
}

